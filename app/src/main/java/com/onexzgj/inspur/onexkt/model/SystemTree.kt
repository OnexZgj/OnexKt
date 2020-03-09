package com.onexzgj.inspur.onexkt.model

/**
 * des：
 * author：onexzgj
 * time：2020-03-09
 */
data class SystemTree (

    /**
     *         {
    "children": [],
    "courseId": 13,
    "id": 269,
    "name": "官方发布",
    "order": 1002,
    "parentChapterId": 150,
    "userControlSetTop": false,
    "visible": 1
    }
    ],
    "courseId": 13,
    "id": 150,
    "name": "开发环境",
    "order": 1,
    "parentChapterId": 0,
    "userControlSetTop": false,
    "visible": 1
     */

    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int,
    val children: ArrayList<SystemTree>

)