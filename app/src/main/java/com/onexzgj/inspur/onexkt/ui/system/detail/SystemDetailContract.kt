package com.onexzgj.inspur.onexkt.ui.system.detail

import com.onexzgj.inspur.onexkt.model.SystemTreeDetail
import com.onexzgj.inspur.onexkt.mvp.IView

/**
 * des：
 * author：onexzgj
 * time：2020-03-10
 */

interface SystemDetailContract {
    interface View : IView {

        /**
         * 展示体系结构数据
         */
        fun showData(page: Int,systemTreeDetail: SystemTreeDetail)
    }

    interface Presenter {
        /**
         * 获取体系结构下的文章
         */
        fun getSystemData(page: Int, cid: Int)
    }
}