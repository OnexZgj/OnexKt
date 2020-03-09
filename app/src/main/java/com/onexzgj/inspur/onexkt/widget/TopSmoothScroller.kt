package com.onexzgj.inspur.onexkt.widget

import android.app.Activity
import androidx.recyclerview.widget.LinearSmoothScroller

/**
 * des：
 * author：onexzgj
 * time：2020-03-09
 */

class TopSmoothScroller(activity: Activity) : LinearSmoothScroller(activity) {




    override fun getVerticalSnapPreference(): Int {
        return SNAP_TO_START
    }


    override fun getHorizontalSnapPreference(): Int {
        return SNAP_TO_START
    }
}