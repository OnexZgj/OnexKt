package com.onexzgj.inspur.onexkt.mvp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.onexzgj.inspur.onexkt.MainApp
import com.onexzgj.inspur.onexkt.utils.StateBar

/**
 * des：
 * author：onexzgj
 * time：2020-03-10
 */

abstract class BaseMvpActivity<V : IView, P : IPresenter<in V>> : AppCompatActivity(), IView {
    lateinit var mPresenter: P
    lateinit var mContext: Context
    lateinit var mActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StateBar.fitSystemBar(this)
        mContext = this
        mActivity = this
        setContentView(getLayoutId())
        mPresenter = createPresent()
        mPresenter.attachView(this as V)
        initData()
    }

    abstract fun initData()

    abstract fun createPresent(): P

    abstract fun getLayoutId(): Int


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
        mPresenter.detachView()
    }
}