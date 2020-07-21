package com.mh.st.myhilt.repository.datasource.network

import com.mh.st.myhilt.repository.Repository
import com.mh.st.myhilt.repository.model.KakaoImageResponse
import io.reactivex.Single

class ApiRepository(private val apiService: ApiService) : Repository {
    companion object {
        const val SERVER_HOST = "https://dapi.kakao.com"
        const val KAKAO_APP_KEY = ""
    }

    private val header = "KakaoAK $KAKAO_APP_KEY"

    override fun fetchImages(query: String, page: Int, size: Int): Single<KakaoImageResponse> {
        return apiService.fetchImage(header, query, page, size)
    }
}