package com.onexzgj.inspur.onexkt.ui.project.detail

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.model.Project
import com.onexzgj.inspur.onexkt.mvp.BaseMvpFragment
import com.onexzgj.inspur.onexkt.utils.CID
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.project_page.*

/**
 * des：
 * author：onexzgj
 * time：2020-03-06
 */
class ProjectPageFragment : BaseMvpFragment<ProjectPageContract.View, ProjectPagePresentImp>(),
    ProjectPageContract.View {


    private var datas = ArrayList<Project>()
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

        srl_project.setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                mCurPage = 0
                datas.clear()
                mPresent.getPageData(mCurPage, cid)
            }
        })


        mAdapter.setOnItemClickListener { adapter, view, position ->
            showInfo("123" + position)
        }
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
        showLoading()
        mPresent.getPageData(mCurPage, cid)
    }

    override fun showPageData(curPage: Int, data: List<Project>?) {
        srl_project?.finishRefresh()
        srl_project?.finishLoadMore()

        dismissLoading()

        mCurPage = curPage + 1
        if (data != null) {
            datas.addAll(data)
        }
        mAdapter.setNewData(datas)


//            if (mCurPage == 0) {
//                mAdapter.setNewData(data)
//            } else {
//                mAdapter.addData(data)
//            }

        dismissLoading()


    }
}