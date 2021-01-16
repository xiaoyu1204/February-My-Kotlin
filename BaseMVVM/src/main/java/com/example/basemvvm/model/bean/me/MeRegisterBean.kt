package com.example.basemvvm.model.bean.me

//data class MeRegisterBean(
//    val `data`: Data,
//    val errmsg: String,
//    val errno: Int
//)

data class MeRegisterBean(
    val token: String,
    val userInfo: UserInfo
)
{
    data class UserInfo(
        val avatar: String,
        val birthday: Int,
        val gender: Int,
        val nickname: Any,
        val uid: String,
        val username: String
    )
}
