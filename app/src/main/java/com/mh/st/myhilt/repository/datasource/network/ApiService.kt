package com.mh.st.myhilt.repository.datasource.network

import com.mh.st.myhilt.repository.model.KakaoImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("/v2/search/image")
    fun fetchImage(@Header("Authorization") appKey: String,
                   @Query("query") query: String,
                   @Query("page") page: Int = 1,
                   @Query("size") size: Int = 10): Single<KakaoImageResponse>
}