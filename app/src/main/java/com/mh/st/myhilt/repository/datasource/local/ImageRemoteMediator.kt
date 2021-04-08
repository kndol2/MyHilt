package com.mh.st.myhilt.repository.datasource.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mh.st.myhilt.repository.datasource.remote.ApiRepository
import com.mh.st.myhilt.repository.datasource.remote.ApiService
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ImageRemoteMediator(
    private val database: AppDatabase,
    private val networkService: ApiService,
    private val query: String
) : RemoteMediator<Int, SearchEntity>() {
    val searchDao = database.searchDao()

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
//        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)
//        return if (System.currentTimeMillis() - database.lastUpdated() >= cacheTimeout)
//        {
//            // Cached data is up-to-date, so there is no need to re-fetch
//            // from the network.
//            InitializeAction.SKIP_INITIAL_REFRESH
//        } else {
//            // Need to refresh cached data from network; returning
//            // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
//            // APPEND and PREPEND from running until REFRESH succeeds.
//            InitializeAction.LAUNCH_INITIAL_REFRESH
//        }
    }

    private suspend fun refresh(): MediatorResult {
        Timber.d("refresh")
        try {
            val list = networkService.fetchImage(
                appKey = "KakaoAK ${ApiRepository.KAKAO_APP_KEY}",
                page = 1,
                query = query,
                size =  10
            )

            if (list.documents.isNotEmpty()) {
                database.withTransaction {
                    database.searchDao().deleteSearchData(query)
                    database.searchDao().insertAll(list.documents.asDatabaseModel(query))
                }
            }
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
        return MediatorResult.Success(endOfPaginationReached = true)
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SearchEntity>
    ): MediatorResult {
        try {
            // Get the closest item from PagingState that we want to load data around.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    // Query DB for SubredditRemoteKey for the subreddit.
                    // SubredditRemoteKey is a wrapper object we use to keep track of page keys we
                    // receive from the Reddit API to fetch the next or previous page.
                    val remoteKey = database.withTransaction {
                        searchDao.getLatestPost(query)
                    }

                    // We must explicitly check if the page key is null when appending, since the
                    // Reddit API informs the end of the list by returning null for page key, but
                    // passing a null key to Reddit API will fetch the initial page.
                    if (remoteKey.isNullOrEmpty()) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    remoteKey.size
                }
            }

            val data = networkService.fetchImage(
                appKey = "KakaoAK ${ApiRepository.KAKAO_APP_KEY}",
                page = loadKey,
                query = query,
                size =  when (loadType) {
                    LoadType.REFRESH -> state.config.initialLoadSize
                    else -> state.config.pageSize
                }
            )

            val items = data.documents.asDatabaseModel(query)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    searchDao.deleteSearchData(query)
                }

                searchDao.insertAll(items)
            }

            return MediatorResult.Success(endOfPaginationReached = items.isEmpty())
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}