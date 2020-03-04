package com.onexzgj.inspur.onexkt.mvp

import android.os.Bundle
import android.view.View

/**
 * des：
 * author：onexzgj
 * time：2020-03-04
 */
abstract class BaseLazyFragment<in V : IView, P : IPresenter<in V>> : BaseMvpFragment<V, P>() {
    /**
     * Fragment 中的 View 是否创建完毕
     */
    protected var isViewCreated: Boolean = false
    /**
     * Fragment 是否对用户可见
     */
    protected var isVisibled: Boolean = false
    /**
     * Fragment 左右切换时，只在第一次显示时请求数据
     */
    protected var isFirstLoad: Boolean = true


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        isViewCreated = true
        lazyLoad()
    }

    private fun lazyLoad() {
        if (isViewCreated && isVisibled && isFirstLoad) {
            loadData()
            isFirstLoad = false
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser) {
            isVisibled = true
            onVisable()
        } else {
            isVisibled = false
            onInVisable()
        }
    }

    private fun onInVisable() {
    }


    private fun onVisable() {
        lazyLoad()
    }

    abstract fun loadData()
}