package com.example.basemvvm.model.bean.main.home

//data class GoodTopData(
//    val `data`: Data,
//    val errmsg: String,
//    val errno: Int
//)

data class GoodTopData(
    val bannerInfo: BannerInfo
)

data class BannerInfo(
    val img_url: String,
    val name: String,
    val url: String
)
