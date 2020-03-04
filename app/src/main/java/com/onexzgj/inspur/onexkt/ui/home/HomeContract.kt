package com.onexzgj.inspur.onexkt.ui.home

import com.onexzgj.inspur.onexkt.model.Article
import com.onexzgj.inspur.onexkt.model.Banner
import com.onexzgj.inspur.onexkt.mvp.IPresenter
import com.onexzgj.inspur.onexkt.mvp.IView

/**
 * des：
 * author：onexzgj
 * time：2020-03-04
 */
interface HomeContract {
    interface View : IView {

        fun onBanner(list: List<Banner>?)

        fun onArticles(page: Int, list: List<Article>?)
    }

    interface Presenter {
        fun getBanner()
        fun getArticle(page: Int)
    }
}