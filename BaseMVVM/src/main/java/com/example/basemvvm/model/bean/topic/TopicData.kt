package com.shop.model.bean.topic

data class TopicData(
    val count: Int,
    val currentPage: Int,
    val `data`: List<Data>,
    val pageSize: Int,
    val totalPages: Int
)

data class Data(
    val id: Int,
    val price_info: Int,
    val scene_pic_url: String,
    val subtitle: String,
    val title: String
)