package com.onexzgj.inspur.onexkt.http

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

fun logInterceptor(): Interceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}