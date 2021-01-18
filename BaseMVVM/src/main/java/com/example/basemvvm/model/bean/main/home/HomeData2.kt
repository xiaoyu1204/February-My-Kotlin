package com.shop.model.bean.home

//data class HomeData(
//    val `data`: Data,
//    val errmsg: String,
//    val errno: Int
//)

data class HomeData2(
    val banner: List<Banner2>,
    val brandList: List<Brand2>,
    val categoryList: List<Category2>,
    val channel: List<Channel2>,
    val hotGoodsList: List<HotGoods2>,
    val newGoodsList: List<NewGoods2>,
    val topicList: List<Topic2>
)

data class HotGoods2(
    val goods_brief: String,
    val id: Int,
    val list_pic_url: String,
    val name: String,
    val retail_price: String
)

data class Brand2(
    val app_list_pic_url: String,
    val floor_price: String,
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

data class Channel2(
    val categoryid: Int,
    val icon_url: String,
    val id: Int,
    val name: String,
    val sort_order: Int,
    val url: String
)

data class Category2(
    val goodsList: List<Goods2>,
    val id: Int,
    val name: String
)

data class Goods2(
    val id: Int,
    val list_pic_url: String,
    val name: String,
    val retail_price: String
)

data class Topic2(
    val avatar: String,
    val content: String,
    val id: Int,
    val is_show: Int,
    val item_pic_url: String,
    val price_info: String,
    val read_count: String,
    val scene_pic_url: String,
    val sort_order: Int,
    val subtitle: String,
    val title: String,
    val topic_category_id: Int,
    val topic_tag_id: Int,
    val topic_template_id: Int
)

data class NewGoods2(
    val id: Int,
    val list_pic_url: String,
    val name: String,
    val retail_price: String
)

data class Banner2(
    val ad_position_id: Int,
    val content: String,
    val enabled: Int,
    val end_time: Int,
    val id: Int,
    val image_url: String,
    val link: String,
    val media_type: Int,
    val name: String
)