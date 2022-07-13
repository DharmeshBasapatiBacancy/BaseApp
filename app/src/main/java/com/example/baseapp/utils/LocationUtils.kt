package com.example.baseapp.utils

import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import androidx.fragment.app.FragmentActivity
import com.example.baseapp.models.UserLocation
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.common.util.CollectionUtils
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import java.util.*

object LocationUtils {

    val locationRequest = LocationRequest.create().apply {
        interval = 5000
        fastestInterval = 5000
        priority = Priority.PRIORITY_HIGH_ACCURACY
    }

    fun isLocationEnabled(activityContext: FragmentActivity): Boolean {
        val locationManager: LocationManager =
            activityContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    fun getAddressFromName(
        context: Context,
        locationName: String,
        callback: LoadDataCallback<UserLocation>
    ) {
        var address = ""
        var cityName = ""
        var areaName = ""
        var building = ""
        val UserLocation: UserLocation
        try {
            val addresses: MutableList<Address>
            val geocoder = Geocoder(context, Locale.ENGLISH)

            addresses = geocoder.getFromLocationName(locationName, 1)
            if (!CollectionUtils.isEmpty(addresses)) {
                val fetchedAddress = addresses[0]
                if (fetchedAddress.maxAddressLineIndex > -1) {
                    address = fetchedAddress.getAddressLine(0)

                    fetchedAddress.locality?.let {
                        cityName = it
                    }
                    fetchedAddress.subLocality?.let {
                        areaName = it
                    }
                    fetchedAddress.featureName?.let {
                        building = it
                    }
                }
                UserLocation = UserLocation().apply {
                    locationAddress = address
                    city = cityName
                    area = areaName
                    buildingName = building
                    latitude = fetchedAddress.latitude
                    longitude = fetchedAddress.longitude
                }
                callback.onDataLoaded(UserLocation)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            callback.onDataNotAvailable(1, "Something went wrong!")
        }
    }

    fun getAddressFromLatLong(
        context: Context,
        lat: String,
        longi: String,
        callback: LoadDataCallback<UserLocation>
    ) {
        var address = ""
        var cityName = ""
        var areaName = ""
        var building = ""
        val UserLocation: UserLocation
        try {
            val addresses: MutableList<Address>
            val geocoder = Geocoder(context, Locale.ENGLISH)

            addresses =
                geocoder.getFromLocation(lat.toDouble(), longi.toDouble(), 1)
            if (!CollectionUtils.isEmpty(addresses)) {
                val fetchedAddress = addresses[0]
                if (fetchedAddress.maxAddressLineIndex > -1) {
                    address = fetchedAddress.getAddressLine(0)

                    fetchedAddress.locality?.let {
                        cityName = it
                    }
                    fetchedAddress.subLocality?.let {
                        areaName = it
                    }
                    fetchedAddress.featureName?.let {
                        building = it
                    }
                }
                UserLocation = UserLocation().apply {
                    locationAddress = address
                    city = cityName
                    area = areaName
                    buildingName = building
                    latitude = fetchedAddress.latitude
                    longitude = fetchedAddress.longitude
                }
                callback.onDataLoaded(UserLocation)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            callback.onDataNotAvailable(1, "Something went wrong!")
        }
    }

    fun Activity.requestDeviceLocationSettings(onSuccess: (String) -> Unit,onFailure: (String) -> Unit) {

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client: SettingsClient = LocationServices.getSettingsClient(this)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener { locationSettingsResponse ->
            val state = locationSettingsResponse.locationSettingsStates

            val label =
                "GPS >> (Present: ${state?.isGpsPresent}  | Usable: ${state?.isGpsUsable} ) \n\n" +
                        "Network >> ( Present: ${state?.isNetworkLocationPresent} | Usable: ${state?.isNetworkLocationUsable} ) \n\n" +
                        "Location >> ( Present: ${state?.isLocationPresent} | Usable: ${state?.isLocationUsable} )"

            onSuccess("Fetching location...")
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    exception.startResolutionForResult(this, 100)
                } catch (sendEx: IntentSender.SendIntentException) {
                    onFailure(sendEx.message.toString())
                }
            }
        }

    }
}

interface LoadDataCallback<T> {
    fun onDataLoaded(response: T)
    fun onDataNotAvailable(errorCode: Int, reasonMsg: String)
}