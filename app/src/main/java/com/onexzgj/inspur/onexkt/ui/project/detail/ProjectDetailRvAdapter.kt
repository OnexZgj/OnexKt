package com.onexzgj.inspur.onexkt.ui.project.detail


import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.onexzgj.inspur.onexkt.R

import com.onexzgj.inspur.onexkt.model.Project
import com.onexzgj.inspur.onexkt.utils.createColorDrawable


class ProjectDetailRvAdapter :
    BaseQuickAdapter<Project, BaseViewHolder>(R.layout.item_project) {

    override fun convert(helper: BaseViewHolder?, item: Project?) {

        helper?.setText(R.id.tv_project_title, item?.title)
            ?.setText(R.id.tv_project_author, item?.author)
            ?.setText(R.id.tv_project_time, item?.niceDate)


        val colorDrawable = createColorDrawable(mContext)
        Glide.with(mContext)
            .load(item?.envelopePic)
            .placeholder(colorDrawable)
            .error(colorDrawable)
            .into(helper!!.getView(R.id.iv_project_image))

    }
}