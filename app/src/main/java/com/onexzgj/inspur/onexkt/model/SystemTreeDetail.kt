package com.onexzgj.inspur.onexkt.model

data class SystemTreeDetail(
    val curPage: Int,
    val datas: List<Data>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)