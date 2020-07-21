package com.mh.st.myhilt.repository

import com.mh.st.myhilt.repository.model.KakaoImageResponse
import io.reactivex.Single

interface Repository {
    fun fetchImages(query: String, page: Int = 1, size: Int = 10): Single<KakaoImageResponse>
}