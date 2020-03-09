package com.onexzgj.inspur.onexkt.ui.system

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.model.SystemTree
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter

/**
 * des：
 * author：onexzgj
 * time：2020-03-09
 */
class TagAdapte(private val mDatas: List<SystemTree>) :
    TagAdapter<Any?>(mDatas) {

    override fun getView(
        parent: FlowLayout,
        position: Int,
        o: Any?
    ): View {
        val textView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_tag,
            null,
            false
        ) as TextView
        textView.text = (o as SystemTree?)!!.name
        return textView
    }

}
