package com.onexzgj.inspur.onexkt.api

import com.onexzgj.inspur.onexkt.model.Banner
import com.onexzgj.inspur.onexkt.model.BaseResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {


    @GET("banner/json")
    fun getBanner(): Observable<BaseResponse<List<Banner>>>


}