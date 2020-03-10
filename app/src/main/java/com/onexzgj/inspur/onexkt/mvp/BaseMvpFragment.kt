package com.onexzgj.inspur.onexkt.mvp

import android.widget.Toast
import com.onexzgj.inspur.onexkt.MainApp

/**
 * des：
 * author：onexzgj
 * time：2020-03-04
 */

abstract class BaseMvpFragment<in V : IView, P : IPresenter<in V>> : BaseFragment(), IView {

    protected lateinit var mPresent: P

    override fun initData() {
        mPresent = createPresent()
        mPresent.attachView(this as V)
    }

    abstract fun createPresent(): P


    override fun showLoading() {
        showInfo("正在加载中...")
    }


    override fun dismissLoading() {
        showInfo("加载完成...")
    }


    override fun showInfo(info: String) {
        Toast.makeText(MainApp.getContext(), info, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresent.detachView()
    }
}