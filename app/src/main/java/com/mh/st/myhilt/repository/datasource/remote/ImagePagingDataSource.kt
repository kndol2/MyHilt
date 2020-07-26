package com.mh.st.myhilt.repository.datasource.remote

import androidx.paging.PagingSource
import com.mh.st.myhilt.repository.model.Documents
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1

class ImagePagingDataSource(
    private val service: ApiService,
    private val query: String
) : PagingSource<Int, Documents>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Documents> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
        val header = "KakaoAK ${ApiRepository.KAKAO_APP_KEY}"
        return try {
            val response = service.fetchImage(header, query, position, params.loadSize)
            val repos = response.documents
            LoadResult.Page(
                data = repos,
                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}