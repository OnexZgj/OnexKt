package com.onexzgj.inspur.onexkt.http

import android.util.Log
import com.onexzgj.inspur.onexkt.mvp.IView
import com.onexzgj.inspur.onexkt.model.BaseResponse
import com.xing.wanandroid.http.ApiException
import com.xing.wanandroid.http.ExceptionHandler
import io.reactivex.observers.DisposableObserver

abstract class BaseObserver<T> : DisposableObserver<BaseResponse<T>> {

    private var baseView: IView? = null

    constructor() : super()

    constructor(view: IView?) : super() {
        baseView = view
    }

    override fun onStart() {
        super.onStart()
        baseView?.showLoading()
    }

    override fun onNext(response: BaseResponse<T>) {
        baseView?.dismissLoading()
        Log.e("debug", "response = ${response.errorCode}")
        val errorCode: Int = response.errorCode ?: -1
        val errorMsg: String = response.errorMsg ?: ""
        val error: Boolean = response.error ?: true
        if ((errorCode == 0) or (errorCode == 200)) {
            onSuccess(response.data)
        } else if (!error) {
            onSuccess(response.results)
        } else {
            onError(ApiException(errorCode, errorMsg))
        }
    }

    override fun onError(e: Throwable) {
        ExceptionHandler.handleException(e)
    }

    abstract fun onSuccess(data: T?)

    override fun onComplete() {
        baseView?.dismissLoading()
    }

}