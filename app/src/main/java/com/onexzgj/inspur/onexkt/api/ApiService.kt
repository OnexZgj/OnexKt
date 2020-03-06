package com.onexzgj.inspur.onexkt.api

import com.onexzgj.inspur.onexkt.model.*
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    /**
     * 首页banner图片
     */
    @GET("banner/json")
    fun getBanner(): Observable<BaseResponse<List<Banner>>>

    /**
     * 获取置顶文章
     */
    @GET("article/top/json")
    fun getTopArticle(): Observable<BaseResponse<List<Article>>>

    /**
     * 获取文章列表
     */
    @GET("article/list/{page}/json")
    fun getArticles(@Path("page") page: Int): Observable<BaseResponse<ArticleResponse>>


    @GET("project/tree/json")
    fun getProjectTab(): Observable<BaseResponse<List<ProjectTab>>>


}