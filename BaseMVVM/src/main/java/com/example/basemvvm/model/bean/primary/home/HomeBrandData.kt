package com.shop.model.bean.home

data class HomeBrandData(
    val `data`: Brand_Data,
    val errmsg: String,
    val errno: Int
)

data class Brand_Data(
    val brand: Brand_Brand
)

data class Brand_Brand(
    val app_list_pic_url: String,
    val floor_price: Double,
    val id: Int,
    val is_new: Int,
    val is_show: Int,
    val list_pic_url: String,
    val name: String,
    val new_pic_url: String,
    val new_sort_order: Int,
    val pic_url: String,
    val simple_desc: String,
    val sort_order: Int
)