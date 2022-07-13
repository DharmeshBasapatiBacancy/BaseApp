package com.example.baseapp.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

object PermissionUtils {

    val locationPermissions = arrayListOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    @JvmInline
    value class Permission(val result: ActivityResultLauncher<Array<String>>)

    sealed class PermissionState {
        object Granted : PermissionState()
        object Denied : PermissionState()
        object PermanentlyDenied : PermissionState()
    }

    private fun getPermissionState(
        activity: Activity?,
        result: MutableMap<String, Boolean>
    ): PermissionState {
        val deniedList: List<String> = result.filter {
            it.value.not()
        }.map {
            it.key
        }

        var state = when (deniedList.isEmpty()) {
            true -> PermissionState.Granted
            false -> PermissionState.Denied
        }

        if (state == PermissionState.Denied) {
            val permanentlyMappedList = deniedList.map {
                activity?.let { activity ->
                    shouldShowRequestPermissionRationale(activity, it)
                }
            }

            if (permanentlyMappedList.contains(false)) {
                state = PermissionState.PermanentlyDenied
            }
        }
        return state
    }

    fun Fragment.registerPermission(onPermissionResult: (PermissionState) -> Unit): Permission {
        return Permission(
            this.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                onPermissionResult(getPermissionState(activity, it as MutableMap<String, Boolean>))
            }
        )
    }

    fun Activity.checkForPermission(context: Context, permissionList: java.util.ArrayList<String>) : java.util.ArrayList<String> {

        val missingPermission = java.util.ArrayList<String>()
        for (permission in permissionList) {

            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                missingPermission.add(permission)
            }
        }
        return missingPermission
    }

    fun Fragment.checkForPermission(
        context: Context,
        permissionList: ArrayList<String>
    ): ArrayList<String> {

        val missingPermission = ArrayList<String>()
        for (permission in permissionList) {

            if (ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                missingPermission.add(permission)
            }
        }
        return missingPermission
    }

    fun AppCompatActivity.registerPermission(onPermissionResult: (PermissionState) -> Unit): Permission {
        return Permission(
            this.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                onPermissionResult(getPermissionState(this, it as MutableMap<String, Boolean>))
            }
        )
    }

    fun Permission.launchSinglePermission(permission: String) {
        this.result.launch(arrayOf(permission))
    }

    fun Permission.launchMultiplePermission(permissionList: ArrayList<String>) {
        this.result.launch(permissionList.toTypedArray())
    }

}