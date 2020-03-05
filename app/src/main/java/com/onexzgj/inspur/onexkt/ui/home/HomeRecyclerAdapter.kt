package com.onexzgj.inspur.onexkt.ui.home

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.model.Article
import com.onexzgj.inspur.onexkt.utils.format

class HomeRecyclerAdapter :
    BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_home_recycler) {

    override fun convert(helper: BaseViewHolder?, item: Article?) {
        // type： 是否是置顶    type = 1 -> 置顶 ， type = 0 -> 非置顶
        val type = item?.type ?: 0
        // refresh: 是否是最新， true -> 最新 ， false -> 非最新
        val isRefresh = item?.fresh ?: false
        var tip: String? = null
        if (type == 1) {
            tip = "置顶"
        } else if (isRefresh) {
            tip = "最新"
        }
        helper?.setText(R.id.tv_home_title, item?.title)
            ?.setText(R.id.tv_home_author, item?.author)
            ?.setText(
                R.id.tv_home_public_time,
                format(item?.publishTime ?: System.currentTimeMillis())
            )
            ?.setText(R.id.tv_home_category, item?.superChapterName)
            ?.setGone(
                R.id.tv_home_recent,
                (type == 1 || isRefresh) && !TextUtils.isEmpty(tip)
            )   // true 显示，false 隐藏
            ?.setText(R.id.tv_home_recent, tip)
    }
}