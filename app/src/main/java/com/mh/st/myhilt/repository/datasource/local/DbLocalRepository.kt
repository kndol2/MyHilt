/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mh.st.myhilt.repository.datasource.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mh.st.myhilt.repository.datasource.remote.ApiService

/**
 * Repository implementation that uses a database backed [androidx.paging.PagingSource] and
 * [androidx.paging.RemoteMediator] to load pages from network when there are no more items cached
 * in the database to load.
 */
class DbLocalRepository(val db: AppDatabase, val apiService: ApiService) : LocalRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchImages(query: String, size: Int) = Pager(
        config = PagingConfig(size),
        remoteMediator = ImageRemoteMediator(db, apiService, query)
    ) {
        db.searchDao().getSearchData(query)
    }.flow
}
