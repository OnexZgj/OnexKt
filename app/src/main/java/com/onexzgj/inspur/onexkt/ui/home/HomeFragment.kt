package com.onexzgj.inspur.onexkt.ui.home

import android.os.Bundle
import android.view.View
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.model.Article
import com.onexzgj.inspur.onexkt.model.Banner
import com.onexzgj.inspur.onexkt.mvp.BaseMvpFragment

/**
 * des：
 * author：onexzgj
 * time：2020-03-04
 */

class HomeFragment : BaseMvpFragment<HomeContract.View, HomePresenter>(), HomeContract.View {
    override fun createPresent(): HomePresenter {
        return HomePresenter()
    }

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onBanner(list: List<Banner>?) {

    }

    override fun onArticles(page: Int, list: List<Article>?) {

    }


}