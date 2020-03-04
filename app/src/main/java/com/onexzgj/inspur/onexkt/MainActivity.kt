package com.onexzgj.inspur.onexkt

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.onexzgj.inspur.onexkt.api.ApiService
import com.onexzgj.inspur.onexkt.http.BaseObserver
import com.onexzgj.inspur.onexkt.http.RetrofitClient
import com.onexzgj.inspur.onexkt.model.Banner
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main1.*

class MainActivity : AppCompatActivity() {

    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = this
        btn_request.setOnClickListener {
            tv_info.text = "点击了"
            Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show()
            initData()
        }


    }

    @SuppressLint("CheckResult")
    private fun initData() {

        val apiService = RetrofitClient.get().retrofit.create(ApiService::class.java)

        apiService.getBanner().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                println(result.data?.size)
                tv_info.text = result.data.toString()
            }, { error ->
                println(error.message.toString())

            })


    }
}
