package com.onexzgj.inspur.onexkt.ui.system.detail

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.model.Data

/**
 * des：知识体系下的文章
 * author：onexzgj
 * time：2020-03-10
 */

class SystemDetailAdapter : BaseQuickAdapter<Data, BaseViewHolder>(R.layout.item_system_tree) {
    override fun convert(helper: BaseViewHolder?, item: Data?) {
        if (item != null) {

            if (!TextUtils.isEmpty(item.author)) {
                helper?.setText(R.id.tv_ist_author, item.author)
            } else {
                helper?.setText(R.id.tv_ist_author, item.shareUser)
            }

            helper?.setText(R.id.tv_ist_public_time, item.niceDate)
                ?.setText(R.id.tv_ist_title, item.title)
                ?.setText(R.id.tv_ist_category, item.chapterName)
        }
    }
}