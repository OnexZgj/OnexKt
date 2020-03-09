package com.onexzgj.inspur.onexkt.ui.system

import com.onexzgj.inspur.onexkt.model.ProjectTab
import com.onexzgj.inspur.onexkt.model.SystemTree
import com.onexzgj.inspur.onexkt.mvp.IView

/**
 * des：项目协调布局
 * author：onexzgj
 * time：2020-03-04
 */
interface SystemContract {
    interface View : IView {

        fun showTrees(list: List<SystemTree>?)

    }

    interface Presenter {
        fun getTree()
    }
}