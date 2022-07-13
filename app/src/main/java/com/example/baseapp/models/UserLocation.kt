package com.example.baseapp.models

import com.google.android.gms.maps.model.LatLng
import java.io.Serializable

data class UserLocation(
    var locationAddress: String = "",
    var city: String = "",
    var area: String = "",
    var buildingName: String = "",
    var latLng: LatLng? = LatLng(0.0,0.0)
): Serializable