package com.example.basemvvm.model.bean.me

//data class MeLoginBean(
//    val `data`: Data,
//    val errmsg: String,
//    val errno: Int
//)

    data class MeLoginBean(
        val code: Int,
        val token: String,
        val userInfo: UserInfo
    )
    {
    data class UserInfo(
        val avatar: String,
        val birthday: Int,
        val gender: Int,
        val nickname: String,
        val uid: String,
        val username: String
    )
}

