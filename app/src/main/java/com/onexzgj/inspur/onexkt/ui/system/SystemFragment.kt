package com.onexzgj.inspur.onexkt.ui.system

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.model.ProjectTab
import com.onexzgj.inspur.onexkt.model.SystemTree
import com.onexzgj.inspur.onexkt.mvp.BaseMvpFragment
import com.onexzgj.inspur.onexkt.widget.ItemHorDecoration
import com.onexzgj.inspur.onexkt.widget.TopItemDecoration
import com.onexzgj.inspur.onexkt.widget.TopSmoothScroller
import kotlinx.android.synthetic.main.fragment_system.*

/**
 * des：
 * author：onexzgj
 * time：2020-03-09
 */
class SystemFragment : BaseMvpFragment<SystemContract.View, SystemPresentImp>(),
    SystemContract.View {
    private var mDatas: List<SystemTree>? = null
    private lateinit var mLeftAdapter: SystemLeftAdapter

    private lateinit var mRightAdapter: SystemRightAdapter

    private var firstItemPosition: Int = 0

    override fun createPresent(): SystemPresentImp {
        return SystemPresentImp()
    }

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {

        rv_left.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        rv_right.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)

        mLeftAdapter = SystemLeftAdapter()
        rv_left.adapter = mLeftAdapter


        mRightAdapter = SystemRightAdapter()
        rv_right.adapter = mRightAdapter

        val rightManager = rv_right.layoutManager as LinearLayoutManager
        mLeftAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                mLeftAdapter.setChoose(position)
//                rightManager?.scrollToPosition(position)

                var lss = TopSmoothScroller(mActivity)
                lss.setTargetPosition(position)
                rightManager.startSmoothScroll(lss)

            }


        //右边联动左边
        rv_right.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                firstItemPosition = rightManager.findFirstVisibleItemPosition()
                if (firstItemPosition != -1) {
                    rv_left.smoothScrollToPosition(firstItemPosition)
                    mLeftAdapter.setChoose(firstItemPosition)
                }
            }

        })

        val top = TopItemDecoration(context as Activity).apply {
            this.tagListener = {
                mDatas?.get(it)?.name.toString()
            }
        }
        rv_right.addItemDecoration(top)


        rv_right.addItemDecoration(ItemHorDecoration(mContext))


    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }

    override fun showTrees(datas: List<SystemTree>?) {
        showInfo("" + datas?.size)
        mLeftAdapter.setNewData(datas)
        mRightAdapter.setNewData(datas)
        mDatas = datas
    }

    override fun initData() {
        super.initData()
        mPresent.getTree()
    }
}