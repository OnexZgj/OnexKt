package com.onexzgj.inspur.onexkt.ui.home

import android.annotation.SuppressLint
import com.onexzgj.inspur.onexkt.api.ApiService
import com.onexzgj.inspur.onexkt.http.BaseObserver
import com.onexzgj.inspur.onexkt.model.Article
import com.onexzgj.inspur.onexkt.model.ArticleResponse
import com.onexzgj.inspur.onexkt.model.Banner
import com.onexzgj.inspur.onexkt.model.BaseResponse
import com.onexzgj.inspur.onexkt.mvp.BasePresent
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.*
import kotlin.collections.ArrayList

/**
 * des：
 * author：onexzgj
 * time：2020-03-04
 */
class HomePresenter : BasePresent<HomeContract.View>(), HomeContract.Presenter {

    private var dataList = ArrayList<Article>()

    override fun getBanner() {
        addSubscribe(
            create(ApiService::class.java).getBanner(),
            object : BaseObserver<List<Banner>>() {
                override fun onSuccess(data: List<Banner>?) {
                    getView()?.onBanner(data)

                }
            })
    }

    @SuppressLint("CheckResult")
    override fun getArticle(page: Int) {

        val apiService = create(ApiService::class.java)
        val observable = Observable.zip(
            apiService.getTopArticle(), apiService.getArticles(page),
            object :
                BiFunction<BaseResponse<List<Article>>, BaseResponse<ArticleResponse>, BaseResponse<List<Article>>> {
                override fun apply(
                    t1: BaseResponse<List<Article>>,
                    t2: BaseResponse<ArticleResponse>
                ): BaseResponse<List<Article>> {
                    if (page == 0) {
                        dataList.clear()

                        t1.data?.let { dataList.addAll(t1.data!!) }
                    }

                    val data = t2.data
                    if (data != null) {
                        val articles = data.datas
                        if (articles != null) {
                            dataList.addAll(articles)
                        }
                    }
                    // 因为 BaseObserver 范型指定了为 BaseResponse， 所以这里重新构造 BaseResponse 对象作为返回值
                    return BaseResponse(dataList, dataList, t1.errorMsg, t1.errorCode, false)
                }
            }
        )

        addSubscribe(observable, object : BaseObserver<List<Article>>() {
            override fun onSuccess(data: List<Article>?) {
                getView()?.onArticles(page, data)

            }

        })
    }

}