package com.onexzgj.inspur.onexkt.ui.system


import android.util.Log
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.model.SystemTree
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import com.zhy.view.flowlayout.TagView


class SystemRightAdapter :
    BaseQuickAdapter<SystemTree, BaseViewHolder>(R.layout.item_right_system) {

    private lateinit var flowLayout: FlowLayout

    override fun convert(helper: BaseViewHolder?, item: SystemTree?) {

//        helper?.setText(R.id.tv_irs_title, item?.name)
//
//        var str: String? = ""
//        for (temp in item?.children!!) {
//            str += temp.name + "\n"
//        }
//        helper?.setText(R.id.tv_irl_children, str)

        var flowLayout = helper!!.getView<TagFlowLayout>(R.id.id_flowlayout)
        flowLayout.adapter = item?.children?.let { TagAdapte(it) }


        flowLayout.setOnTagClickListener { view, position, parent ->

            if (item != null) {
                Log.d("TAG",item.children[position].name)
            }

            true
        }

    }
}
