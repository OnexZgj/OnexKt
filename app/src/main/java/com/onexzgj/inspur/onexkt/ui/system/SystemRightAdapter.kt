package com.onexzgj.inspur.onexkt.ui.system

import android.content.Intent
import android.os.Bundle
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.model.SystemTree
import com.onexzgj.inspur.onexkt.ui.system.detail.SystemDetailActivity
import com.onexzgj.inspur.onexkt.utils.CID
import com.onexzgj.inspur.onexkt.utils.TITLE
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagFlowLayout


class SystemRightAdapter :
    BaseQuickAdapter<SystemTree, BaseViewHolder>(R.layout.item_right_system) {

    private lateinit var flowLayout: FlowLayout

    override fun convert(helper: BaseViewHolder?, item: SystemTree?) {
        var flowLayout = helper!!.getView<TagFlowLayout>(R.id.id_flowlayout)
        flowLayout.adapter = item?.children?.let { TagAdapte(it) }
        flowLayout.setOnTagClickListener { view, position, parent ->

            if (item != null) {
                var bundle = Bundle()

                bundle.putString(TITLE, item.children[position].name)
                bundle.putInt(CID, item.children[position].id)

                var intent = Intent(mContext, SystemDetailActivity::class.java)
                intent.putExtras(bundle)
                mContext.startActivity(intent)
            }
            true
        }
    }
}
