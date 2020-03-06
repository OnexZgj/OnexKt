package com.onexzgj.inspur.onexkt.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

/**
 * des：
 * author：onexzgj
 * time：2020-03-06
 */

class StateBar {

    companion object {

        fun fitSystemBar(activity: Activity) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return

            val window = activity.window
            val decorView = window.decorView
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
        }

        fun lightStatusBar(activity: Activity, light: Boolean) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return
            val window = activity.window
            val decorView = window.decorView
            var visibility = decorView.systemUiVisibility
            visibility = if (light) {
                visibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                visibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
            decorView.systemUiVisibility = visibility

        }
    }

}