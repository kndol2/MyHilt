package com.mh.st.myhilt.repository

import com.mh.st.myhilt.repository.model.PlacesModel

interface Repository {
    fun fetchPlaces(type: String, location: String, name: String, opennow: Boolean, rankby: String, key: String): PlacesModel
}