package com.example.baseapp.views.activities

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.baseapp.databinding.ActivityLocationHandlerBinding
import com.example.baseapp.models.UserLocation
import com.example.baseapp.utils.CommonUtils.openAppInfo
import com.example.baseapp.utils.LoadDataCallback
import com.example.baseapp.utils.LocationUtils
import com.example.baseapp.utils.LocationUtils.requestDeviceLocationSettings
import com.example.baseapp.utils.PermissionUtils
import com.example.baseapp.utils.PermissionUtils.checkForPermission
import com.example.baseapp.utils.PermissionUtils.launchMultiplePermission
import com.example.baseapp.utils.PermissionUtils.registerPermission
import com.example.baseapp.utils.ViewUtils.hide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

class LocationHandlerActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LocationHandlerActivity"
    }

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    private lateinit var binding: ActivityLocationHandlerBinding

    private val locationPermission = registerPermission {
        onLocationPermissionResult(it)
    }

    private val goToSettingsRequest = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        stepOneCheckPermissionGrantedOrNot()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)

        stepOneCheckPermissionGrantedOrNot()

        setupLocationCallback()
    }

    private fun setupLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationresult: LocationResult) {
                Log.d(TAG, "onLocationResult: ${locationresult.locations}")
                fetchAddressFromLatLong(
                    LatLng(
                        locationresult.lastLocation?.latitude!!,
                        locationresult.lastLocation?.longitude!!
                    )
                )

            }
        }
    }

    private fun stepOneCheckPermissionGrantedOrNot() {
        checkForPermission(this, PermissionUtils.locationPermissions).let { missingPermissionList ->
            if (missingPermissionList.size > 0) {
                binding.messageTextView.text = "Location Permission Required"
                binding.actionButton.text = "Allow"
                binding.actionButton.setOnClickListener {
                    requestPermissions(missingPermissionList)
                }
            } else {
                openDeviceLocationDialog()
            }
        }
    }

    private fun openDeviceLocationDialog(){
        requestDeviceLocationSettings({ onSuccess(it) }, { onFailure(it) })
    }

    private fun requestPermissions(missingPermission: ArrayList<String>) {
        locationPermission.launchMultiplePermission(missingPermission)
    }

    override fun onDestroy() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        super.onDestroy()
    }

    private fun onLocationPermissionResult(permissionState: PermissionUtils.PermissionState) {
        when (permissionState) {
            PermissionUtils.PermissionState.Denied -> {
                Log.d(TAG, "onLocationPermissionResult: Denied")
            }
            PermissionUtils.PermissionState.Granted -> {
                Log.d(TAG, "onLocationPermissionResult: Granted")
                openDeviceLocationDialog()
            }
            PermissionUtils.PermissionState.PermanentlyDenied -> {
                Log.d(TAG, "onLocationPermissionResult: PermanentlyDenied")
                binding.messageTextView.text =
                    "To move ahead, location permission is required.\nTo enable, go to App Info and allow Location permission."
                binding.actionButton.text = "Open App Info"
                binding.actionButton.setOnClickListener {
                    openAppInfo(goToSettingsRequest)
                }
            }
        }
    }

    private fun fetchAddressFromLatLong(location: LatLng) {
        LocationUtils.getAddressFromLatLong(this,
            location.latitude.toString(),
            location.longitude.toString(),
            object : LoadDataCallback<UserLocation> {
                override fun onDataLoaded(response: UserLocation) {
                    Log.d(TAG, "onDataLoaded: $response")
                    binding.messageTextView.text = "Your Location - ${response.locationAddress}"
                }

                override fun onDataNotAvailable(errorCode: Int, reasonMsg: String) {
                    Log.d(TAG, "onDataNotAvailable: $reasonMsg")
                    binding.messageTextView.text = "Your Location - $reasonMsg"
                }
            })
    }

    private fun startLocationUpdates() {
        LocationUtils.locationRequest.let {
            fusedLocationProviderClient.requestLocationUpdates(
                it, locationCallback, Looper.getMainLooper()
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {//When user clicked "Ok" on Device Location dialog
                openDeviceLocationDialog()
            } else {//When user clicked "No Thanks" on Device Location dialog
                binding.messageTextView.text = "GPS TURNED OFF"
                binding.actionButton.text = "TURN ON GPS"
                binding.actionButton.setOnClickListener {
                    openDeviceLocationDialog()
                }
            }
        }
    }

    private fun onFailure(message: String) {
        binding.messageTextView.text = message
    }

    private fun onSuccess(message: String) {
        binding.messageTextView.text = message
        binding.actionButton.hide()
        startLocationUpdates()
    }

}