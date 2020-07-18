package com.mh.st.myhilt.repository.datasource.network

import com.mh.st.myhilt.repository.model.PlacesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("place/nearbysearch/json?")
    fun fetchPlaces(@Query(value = "type", encoded = true) type: String,
                    @Query(value = "location",encoded = true) location: String,
                    @Query(value = "name", encoded = true) name: String,
                    @Query(value = "opennow",encoded = true) opennow: Boolean,
                    @Query(value = "rankby", encoded = true) rankby: String,
                    @Query(value = "key", encoded = true) key: String
    ): PlacesModel
}