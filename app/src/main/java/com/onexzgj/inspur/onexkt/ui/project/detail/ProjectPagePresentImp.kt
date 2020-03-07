package com.onexzgj.inspur.onexkt.ui.project.detail

import com.onexzgj.inspur.onexkt.api.ApiService
import com.onexzgj.inspur.onexkt.http.BaseObserver
import com.onexzgj.inspur.onexkt.model.ProjectResponse
import com.onexzgj.inspur.onexkt.model.ProjectTab
import com.onexzgj.inspur.onexkt.mvp.BasePresent

/**
 * des：
 * author：onexzgj
 * time：2020-03-06
 */
class ProjectPagePresentImp : BasePresent<ProjectPageContract.View>(),
    ProjectPageContract.Presenter {

    override fun getPageData(mCurPage: Int, cid: Int) {

        addSubscribe(create(ApiService::class.java).getProjectDetail(mCurPage, cid),
            object : BaseObserver<ProjectResponse>() {
                override fun onSuccess(data: ProjectResponse?) {
                    if (data != null) {
                        getView()?.showPageData(data.curPage, data.datas)
                    }
                }

            }
        )
    }
}