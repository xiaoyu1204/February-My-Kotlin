package com.shop.model.bean.home

data class HomeChannelTypeData(
    val `data`: ChannelTypeData,
    val errmsg: String,
    val errno: Int
)

data class ChannelTypeData(
    val categoryList: List<ChannelTypeCategory>,
    val currentCategory: ChannelTypeCurrentCategory
)

data class ChannelTypeCategory(
    val banner_url: String,
    val front_desc: String,
    val front_name: String,
    val icon_url: String,
    val id: Int,
    val img_url: String,
    val is_show: Int,
    val keywords: String,
    val level: String,
    val name: String,
    val parent_id: Int,
    val show_index: Int,
    val sort_order: Int,
    val type: Int,
    val wap_banner_url: String
)

data class ChannelTypeCurrentCategory(
    val banner_url: String,
    val front_desc: String,
    val front_name: String,
    val icon_url: String,
    val id: Int,
    val img_url: String,
    val is_show: Int,
    val keywords: String,
    val level: String,
    val name: String,
    val parent_id: Int,
    val show_index: Int,
    val sort_order: Int,
    val subCategoryList: List<ChannelTypeSubCategory>,
    val type: Int,
    val wap_banner_url: String
)

data class ChannelTypeSubCategory(
    val banner_url: String,
    val front_desc: String,
    val front_name: String,
    val icon_url: String,
    val id: Int,
    val img_url: String,
    val is_show: Int,
    val keywords: String,
    val level: String,
    val name: String,
    val parent_id: Int,
    val show_index: Int,
    val sort_order: Int,
    val type: Int,
    val wap_banner_url: String
)