package com.onexzgj.inspur.onexkt.ui.project.detail

import com.onexzgj.inspur.onexkt.model.Project
import com.onexzgj.inspur.onexkt.model.ProjectTab
import com.onexzgj.inspur.onexkt.mvp.IView

/**
 * des：项目协调布局
 * author：onexzgj
 * time：2020-03-04
 */
interface ProjectPageContract {
    interface View : IView {

        fun showPageData(curPage: Int, list: List<Project>?)

    }

    interface Presenter {
        /**
         * 获取页面的详细数据
         */
        fun getPageData(mCurPage: Int,cid: Int)
    }
}