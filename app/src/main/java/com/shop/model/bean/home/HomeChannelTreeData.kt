package com.shop.model.bean.home

data class HomeChannelTreeData(
    val `data`: ChannelTreeData,
    val errmsg: String,
    val errno: Int
)

data class ChannelTreeData(
    val count: Int,
    val currentPage: Int,
    val `data`: List<ChannelTreeDataX>,
    val filterCategory: List<ChannelTreeFilterCategory>,
    val goodsList: List<ChannelTreeGoods>,
    val pageSize: Int,
    val totalPages: Int
)

data class ChannelTreeDataX(
    val id: Int,
    val list_pic_url: String,
    val name: String,
    val retail_price: String
)

data class ChannelTreeFilterCategory(
    val checked: Boolean,
    val id: Int,
    val name: String
)

data class ChannelTreeGoods(
    val id: Int,
    val list_pic_url: String,
    val name: String,
    val retail_price: String
)