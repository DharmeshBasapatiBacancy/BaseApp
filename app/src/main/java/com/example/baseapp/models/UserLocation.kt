package com.example.baseapp.models

import java.io.Serializable

data class UserLocation(
    var locationAddress: String = "",
    var city: String = "",
    var area: String = "",
    var buildingName: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
): Serializable