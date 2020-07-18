package com.mh.st.myhilt.repository.datasource.network

import com.mh.st.myhilt.repository.Repository
import com.mh.st.myhilt.repository.model.PlacesModel

class ApiRepository(private val apiService: ApiService) : Repository {
    override fun fetchPlaces(
        type: String,
        location: String,
        name: String,
        opennow: Boolean,
        rankby: String,
        key: String
    ): PlacesModel {
        return apiService.fetchPlaces(type, location, name, opennow, rankby, key)
    }
}