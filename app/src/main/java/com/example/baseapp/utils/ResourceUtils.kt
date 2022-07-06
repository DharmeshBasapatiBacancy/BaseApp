package com.example.baseapp.utils

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.annotation.*
import androidx.core.content.res.ResourcesCompat

object ResourceUtils {

    fun Context.color(@ColorRes colorId: Int) =
        ResourcesCompat.getColor(resources, colorId, null)

    fun Context.drawable(@DrawableRes resId: Int) =
        ResourcesCompat.getDrawable(resources, resId, null)

    fun Context.font(@FontRes resId: Int) =
        ResourcesCompat.getFont(this, resId)

    fun Context.dimen(@DimenRes resId: Int) =
        resources.getDimension(resId)

    fun Context.anim(@AnimRes resId: Int) =
        AnimationUtils.loadAnimation(this, resId)

}