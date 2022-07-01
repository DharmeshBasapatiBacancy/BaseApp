package com.example.baseapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast

object ViewUtils {

    fun View.show(){
        visibility = View.VISIBLE
    }
    fun View.hide(){
        visibility = View.GONE
    }

    fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}