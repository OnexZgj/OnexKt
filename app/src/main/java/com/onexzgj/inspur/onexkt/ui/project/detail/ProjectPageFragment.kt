package com.onexzgj.inspur.onexkt.ui.project.detail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.mvp.BaseMvpFragment
import com.onexzgj.inspur.onexkt.utils.CID

/**
 * des：
 * author：onexzgj
 * time：2020-03-06
 */
class ProjectPageFragment : BaseMvpFragment<ProjectPageContract.View, ProjectPagePresentImp>() {


    private var cid: Int = 0

    private var tvInfo: TextView? = null
    override fun createPresent(): ProjectPagePresentImp {
        return ProjectPagePresentImp()
    }

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
        arguments?.let {
            cid = it.getInt(CID)
        }

        tvInfo = rootView?.findViewById(R.id.tv_info)
        tvInfo?.setText("" + cid)
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

}