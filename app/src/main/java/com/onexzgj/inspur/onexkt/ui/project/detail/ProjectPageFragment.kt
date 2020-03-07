package com.onexzgj.inspur.onexkt.ui.project.detail

import android.media.SoundPool
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.model.Project
import com.onexzgj.inspur.onexkt.mvp.BaseMvpFragment
import com.onexzgj.inspur.onexkt.utils.CID
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import kotlinx.android.synthetic.main.project_page.*

/**
 * des：
 * author：onexzgj
 * time：2020-03-06
 */
class ProjectPageFragment : BaseMvpFragment<ProjectPageContract.View, ProjectPagePresentImp>(),
    ProjectPageContract.View {


    private lateinit var mAdapter: ProjectDetailRvAdapter
    private var cid: Int = 0
    private var mCurPage: Int = 0

    override fun createPresent(): ProjectPagePresentImp {
        return ProjectPagePresentImp()
    }

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
        arguments?.let {
            cid = it.getInt(CID)
        }
        srl_project?.setEnableRefresh(true)
        val itemDecoration = DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL)

        rv_project?.addItemDecoration(itemDecoration)
        rv_project?.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)

        mAdapter = ProjectDetailRvAdapter()
        rv_project?.adapter = mAdapter


        srl_project.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mPresent.getPageData(mCurPage, cid)
            }
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.project_page
    }


    companion object {
        @JvmStatic
        fun newInstance(cid: Int) =
            ProjectPageFragment().apply {
                arguments = Bundle().apply {
                    putInt(CID, cid)
                }
            }
    }


    override fun initData() {
        super.initData()
        mPresent.getPageData(mCurPage, cid)
    }

    override fun showPageData(curPage: Int, data: List<Project>?) {
        mCurPage = curPage
        if (data != null) {
            if (mCurPage == 0) {
                mAdapter.setNewData(data)
            } else {
                mAdapter.addData(data)
            }
        }

    }
}