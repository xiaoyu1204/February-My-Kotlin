package com.example.basemvvm.model.bean.me

//data class MeUserInfoBean(
//    val `data`: Data,
//    val errmsg: String,
//    val errno: Int
//)

data class MeUserInfoBean(
    val avatar: String,
    val birthday: String,
    val mark: Any,
    val nickname: String,
    val uid: String,
    val username: String
)