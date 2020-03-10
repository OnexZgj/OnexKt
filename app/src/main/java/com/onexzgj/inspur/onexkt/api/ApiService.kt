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


    /**
     * 获取项目体系
     */
    @GET("project/tree/json")
    fun getProjectTab(): Observable<BaseResponse<List<ProjectTab>>>


    /**
     * 获取项目下面的完整的数据
     */
    @GET("project/list/{page}/json")
    fun getProjectDetail(@Path("page") page: Int, @Query("cid") cid: Int): Observable<BaseResponse<ProjectResponse>>


    /**
     * 获取知识体系
     */
    @GET("tree/json")
    fun getSystemTree(): Observable<BaseResponse<List<SystemTree>>>

    /**
     * 获取知识体系下的文章列表
     */
    @GET("article/list/{page}/json")
    fun getSystemTreeArticle(@Path("page") page: Int, @Query("cid") cid: Int):Observable<BaseResponse<SystemTreeDetail>>


}