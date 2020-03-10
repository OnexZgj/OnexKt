package com.onexzgj.inspur.onexkt.ui.system.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.model.Data
import com.onexzgj.inspur.onexkt.model.SystemTreeDetail
import com.onexzgj.inspur.onexkt.mvp.BaseMvpActivity
import com.onexzgj.inspur.onexkt.ui.detail.WebViewActivity
import com.onexzgj.inspur.onexkt.utils.*
import com.onexzgj.inspur.onexkt.widget.HorItemHorDecoration
import com.onexzgj.inspur.onexkt.widget.ItemHorDecoration
import com.onexzgj.inspur.onexkt.widget.TopItemDecoration
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_system_detail.*
import kotlinx.android.synthetic.main.fragment_system.*

/**
 * 知识体系详情界面
 */
class SystemDetailActivity : BaseMvpActivity<SystemDetailContract.View, SystemDeatilPresentImp>(),
    SystemDetailContract.View {
    private lateinit var mAdapter: SystemDetailAdapter
    private var mDatas: ArrayList<Data> = ArrayList()
    /**
     * 当前显示的页码
     */
    var mCpage: Int = 0

    /**
     * 标签title
     */
    var title: String? =""

    /**
     * 标签ID
     */
    var cid: Int = 0

    override fun initData() {

        val bundle = intent.extras
        title = bundle.getString(TITLE)
        cid = bundle.getInt(CID)
        showInfo(title + cid)

        srl_system.setEnableRefresh(true)

        mAdapter = SystemDetailAdapter()

        rv_system.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)

        rv_system.adapter = mAdapter

        mAdapter.setOnItemClickListener { adapter, view, position ->
            val bundle = Bundle()
            val bean = mDatas[position]
            bundle.putString(URL, bean.link)
            bundle.putInt(ID, bean.id)
            bundle.putString(AUTHOR, bean.author)
            bundle.putString(LINK, bean.link)
            bundle.putString(TITLE, bean.title)

            val intent = Intent(mActivity, WebViewActivity::class.java)
            intent.putExtras(bundle)
            mActivity.startActivity(intent)
        }


        srl_system.setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                mCpage = 0
                mPresenter.getSystemData(mCpage, cid)
            }
        })

        srl_system.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mPresenter.getSystemData(mCpage, cid)
            }
        })


//        rv_system.addItemDecoration(ItemHorDecoration(mContext))
        rv_system.addItemDecoration(HorItemHorDecoration(mContext))

        mPresenter.getSystemData(mCpage, cid)
    }

    override fun createPresent(): SystemDeatilPresentImp {
        return SystemDeatilPresentImp()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_system_detail
    }

    override fun showData(page: Int, systemTreeDetail: SystemTreeDetail) {

        srl_system.finishLoadMore()
        srl_system.finishRefresh()

        if (systemTreeDetail.datas.size > 0) {
            if (mCpage == 0)
                mDatas.clear()
            mDatas.addAll(systemTreeDetail.datas)
        }
        mCpage = page
        mAdapter.setNewData(mDatas)
    }


}
