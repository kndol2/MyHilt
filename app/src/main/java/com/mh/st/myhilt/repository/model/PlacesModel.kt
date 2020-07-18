package com.mh.st.myhilt.repository.model

import com.google.gson.annotations.SerializedName

data class PlacesModel(
    @SerializedName("results")
    var customA: List<CustomA> = ArrayList(),
    @SerializedName("status")
    var status: String? = null
)

data class CustomA (
    @SerializedName("geometry")
    var geometry: Geometry? = null,
    @SerializedName("vicinity")
    var vicinity: String? = null,
    @SerializedName("name")
    var name: String? = null
)

data class Geometry (
    @SerializedName("location")
    var locationA: LocationA? = null
)

data class LocationA (

    @SerializedName("lat")
    var lat: String? = null,
    @SerializedName("lng")
    var lng: String? = null
)