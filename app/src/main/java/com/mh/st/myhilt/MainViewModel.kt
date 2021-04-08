package com.mh.st.myhilt

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mh.st.myhilt.repository.datasource.local.LocalRepository
import com.mh.st.myhilt.repository.datasource.local.SearchEntity
import kotlinx.coroutines.flow.Flow

class MainViewModel @ViewModelInject constructor(val repository: LocalRepository): ViewModel() {

    fun fetchImages(searchText: String): Flow<PagingData<SearchEntity>> {
        return repository.fetchImages(searchText, 10)
            .cachedIn(viewModelScope)
    }
}