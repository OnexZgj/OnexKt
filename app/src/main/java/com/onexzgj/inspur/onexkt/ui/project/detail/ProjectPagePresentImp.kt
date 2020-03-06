package com.onexzgj.inspur.onexkt.ui.project.detail

import com.onexzgj.inspur.onexkt.api.ApiService
import com.onexzgj.inspur.onexkt.http.BaseObserver
import com.onexzgj.inspur.onexkt.model.ProjectTab
import com.onexzgj.inspur.onexkt.mvp.BasePresent

/**
 * des：
 * author：onexzgj
 * time：2020-03-06
 */
class ProjectPagePresentImp : BasePresent<ProjectPageContract.View>(),
    ProjectPageContract.Presenter {
    override fun getTabs() {


        addSubscribe(create(ApiService::class.java).getProjectTab(),
            object : BaseObserver<List<ProjectTab>>() {
                override fun onSuccess(data: List<ProjectTab>?) {
                    getView()?.showTabs(data)
                }

            }
        )
    }
}