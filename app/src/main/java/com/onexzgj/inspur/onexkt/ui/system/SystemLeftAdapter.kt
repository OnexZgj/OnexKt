package com.onexzgj.inspur.onexkt.ui.system


import android.graphics.Typeface
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.onexzgj.inspur.onexkt.R

import com.onexzgj.inspur.onexkt.model.SystemTree


class SystemLeftAdapter :
    BaseQuickAdapter<SystemTree, BaseViewHolder>(R.layout.item_left_system) {

    private var mClickPosition: Int = 0

    override fun convert(helper: BaseViewHolder?, item: SystemTree?) {

        val position = helper?.layoutPosition

        if (position == mClickPosition) {
            helper.setTextColor(
                R.id.tv_ils_info,
                ContextCompat.getColor(mContext, R.color.colorAccent)
            )
            helper.setVisible(R.id.view_indicator, true)
            // 加粗体
            helper.setTypeface(R.id.tv_ils_info, Typeface.defaultFromStyle(Typeface.BOLD))
        } else {
            helper?.setTextColor(
                R.id.tv_ils_info,
                ContextCompat.getColor(mContext, R.color.color_f32)
            )?.setTypeface(R.id.tv_ils_info, Typeface.DEFAULT)
                ?.setVisible(R.id.view_indicator, false)
        }


        helper?.setText(R.id.tv_ils_info, item?.name)


    }

    fun setChoose(position: Int) {
        mClickPosition = position
        notifyDataSetChanged()

    }
}