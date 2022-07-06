package com.example.baseapp.utils

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.example.baseapp.databinding.CustomSnackbarLayoutBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

object ViewUtils {

    fun View.show(){
        visibility = View.VISIBLE
    }

    fun View.hide(){
        visibility = View.GONE
    }

    fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, length).show()
    }

    fun Fragment.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
        requireContext().toast(msg, length)
    }

    fun View.showCustomSnackBar(alertMessage: String, layoutInflater: LayoutInflater) {
        val snackBarView = Snackbar.make(this, "", Snackbar.LENGTH_LONG)
        val view = snackBarView.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
        view.layoutParams = params

        val bindingView = CustomSnackbarLayoutBinding.inflate(layoutInflater)
        bindingView.alertMessageTextView.text = alertMessage

        snackBarView.view.setBackgroundColor(Color.TRANSPARENT)

        val snackbarLayout: Snackbar.SnackbarLayout = snackBarView.view as Snackbar.SnackbarLayout
        snackbarLayout.layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT
        snackbarLayout.setPadding(0, 0, 0, 0)
        snackbarLayout.addView(bindingView.root)

        snackBarView.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
        snackBarView.show()
    }

}