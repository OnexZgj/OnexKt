package com.onexzgj.inspur.onexkt.ui.system

import com.onexzgj.inspur.onexkt.api.ApiService
import com.onexzgj.inspur.onexkt.http.BaseObserver
import com.onexzgj.inspur.onexkt.model.ProjectTab
import com.onexzgj.inspur.onexkt.model.SystemTree
import com.onexzgj.inspur.onexkt.mvp.BasePresent

/**
 * des：知识体系
 * author：onexzgj
 * time：2020-03-06
 */
class SystemPresentImp : BasePresent<SystemContract.View>(), SystemContract.Presenter {

    override fun getTree() {
        addSubscribe(create(ApiService::class.java).getSystemTree(),
            object : BaseObserver<List<SystemTree>>() {
                override fun onSuccess(data: List<SystemTree>?) {
                    getView()?.showTrees(data)
                }
            }
        )
    }
}