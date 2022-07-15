package com.example.baseapp.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher

object CommonUtils {

    fun Activity.openAppInfo(goToSettingsRequest: ActivityResultLauncher<Intent>) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:" + this.packageName)
        goToSettingsRequest.launch(intent)
    }

}