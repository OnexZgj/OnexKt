package com.onexzgj.inspur.onexkt.ui.home

import com.onexzgj.inspur.onexkt.api.ApiService
import com.onexzgj.inspur.onexkt.http.BaseObserver
import com.onexzgj.inspur.onexkt.model.Banner
import com.onexzgj.inspur.onexkt.mvp.BasePresent
import java.util.*

/**
 * des：
 * author：onexzgj
 * time：2020-03-04
 */
class HomePresenter : BasePresent<HomeContract.View>(), HomeContract.Presenter {

    override fun getBanner() {
        addSubscribe(
            create(ApiService::class.java).getBanner(),
            object : BaseObserver<List<Banner>>() {
                override fun onSuccess(data: List<Banner>?) {

                    println(" " + data?.size)

                }
            })
    }

    override fun getArticle(page: Int) {

    }

}