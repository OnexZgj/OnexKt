package com.onexzgj.inspur.onexkt.ui.project

import com.onexzgj.inspur.onexkt.model.ProjectTab
import com.onexzgj.inspur.onexkt.mvp.IView

/**
 * des：项目协调布局
 * author：onexzgj
 * time：2020-03-04
 */
interface ProjectContract {
    interface View : IView {

        fun showTabs(list: List<ProjectTab>?)

    }

    interface Presenter {
        fun getTabs()
    }
}