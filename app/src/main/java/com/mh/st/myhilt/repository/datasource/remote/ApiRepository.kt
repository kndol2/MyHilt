package com.mh.st.myhilt.repository.datasource.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mh.st.myhilt.repository.Repository
import com.mh.st.myhilt.repository.model.Documents
import kotlinx.coroutines.flow.Flow

class ApiRepository(private val apiService: ApiService) : Repository {
    companion object {
        const val SERVER_HOST = "https://dapi.kakao.com"
        const val KAKAO_APP_KEY = ""

        private const val NETWORK_PAGE_SIZE = 10
    }

    override fun fetchImages(query: String, page: Int, size: Int): Flow<PagingData<Documents>> = Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ImagePagingDataSource(apiService, query) }
        ).flow
}