package com.mh.st.myhilt.repository

import androidx.paging.PagingData
import com.mh.st.myhilt.repository.model.Documents
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchImages(query: String, page: Int = 1, size: Int = 10): Flow<PagingData<Documents>>
}