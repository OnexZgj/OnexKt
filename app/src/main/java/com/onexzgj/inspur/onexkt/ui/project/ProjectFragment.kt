package com.onexzgj.inspur.onexkt.ui.project

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.model.ProjectTab
import com.onexzgj.inspur.onexkt.mvp.BaseMvpFragment

class ProjectFragment : BaseMvpFragment<ProjectContract.View, ProjectPresentImp>(),
    ProjectContract.View {


    private var tablayou: TabLayout? = null

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
        tablayou = rootView?.findViewById(R.id.tl_fp_tablayout)
//        vp_fp_viewpager.adapter = ProjectPagerAdapter(childFragmentManager,)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun createPresent(): ProjectPresentImp {
        return ProjectPresentImp()
    }

    override fun showTabs(datas: List<ProjectTab>?) {
        showInfo(" " + datas?.size)
    }


    override fun initData() {
        super.initData()
        mPresent.getTabs()

    }
}