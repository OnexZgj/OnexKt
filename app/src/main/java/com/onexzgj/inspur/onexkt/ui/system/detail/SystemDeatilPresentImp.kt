package com.onexzgj.inspur.onexkt.ui.system.detail

import com.onexzgj.inspur.onexkt.api.ApiService
import com.onexzgj.inspur.onexkt.http.BaseObserver
import com.onexzgj.inspur.onexkt.model.BaseResponse
import com.onexzgj.inspur.onexkt.model.SystemTreeDetail
import com.onexzgj.inspur.onexkt.mvp.BasePresent

/**
 * des：
 * author：onexzgj
 * time：2020-03-10
 */
class SystemDeatilPresentImp : BasePresent<SystemDetailContract.View>(),
    SystemDetailContract.Presenter {

    override fun getSystemData(page: Int, cid: Int) {
        addSubscribe(create(ApiService::class.java).getSystemTreeArticle(page, cid),
            object : BaseObserver<SystemTreeDetail>() {
                override fun onSuccess(data: SystemTreeDetail?) {
                    data?.let { getView()?.showData(it.curPage, it) }
                }
            })
    }
}