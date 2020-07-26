package com.mh.st.myhilt

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mh.st.myhilt.repository.Repository
import com.mh.st.myhilt.repository.model.Documents
import kotlinx.coroutines.flow.Flow

class MainViewModel @ViewModelInject constructor(val repository: Repository): ViewModel() {

    fun fetchImages(searchText: String): Flow<PagingData<Documents>> {
        return repository.fetchImages(searchText)
            .cachedIn(viewModelScope)
    }
}