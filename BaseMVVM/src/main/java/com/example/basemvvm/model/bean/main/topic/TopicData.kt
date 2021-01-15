package com.example.basemvvm.model.bean.main.topic

//data class TopicData(
//    val `data`: Data,
//    val errmsg: String,
//    val errno: Int
//)
    data class TopicData(
        val count: Int,
        val currentPage: Int,
        val `data`: List<DataX>,
        val pageSize: Int,
        val totalPages: Int
    )
{
    data class DataX(
        val id: Int,
        val price_info: String,
        val scene_pic_url: String,
        val subtitle: String,
        val title: String
    )
}