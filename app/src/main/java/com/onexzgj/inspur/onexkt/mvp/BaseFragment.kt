package com.onexzgj.inspur.onexkt.mvp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * des：
 * author：onexzgj
 * time：2020-03-04
 */

abstract class BaseFragment : Fragment() {

    protected lateinit var mContext: Context
    protected lateinit var mActivity: FragmentActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = this!!.activity!!
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mContext = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getLayoutId(), container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view, savedInstanceState)
        initData()
    }

    abstract fun initData()

    abstract fun initView(rootView: View?, savedInstanceState: Bundle?)

    abstract fun getLayoutId(): Int
}
