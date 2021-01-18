package com.example.basemvvm.model.bean.main

data class DetailInfoData(
    val attribute: List<Attribute>,
    val brand: Brand,
    val comment: Comment,
    val gallery: List<Gallery>,
    val info: Info,
    val issue: List<Issue>,
    val productList: List<Product>,
    val specificationList: List<Any>,
    val userHasCollect: Int
)

data class Attribute(
    val name: String,
    val value: String
)

class Brand(
)

data class Comment(
    val count: Int,
    val `data`: DataX
)

data class Gallery(
    val goods_id: Int,
    val id: Int,
    val img_desc: String,
    val img_url: String,
    val sort_order: Int
)

data class Info(
    val add_time: Int,
    val app_exclusive_price: Int,
    val attribute_category: Int,
    val brand_id: Int,
    val category_id: Int,
    val counter_price: Int,
    val extra_price: Int,
    val goods_brief: String,
    val goods_desc: String,
    val goods_number: Int,
    val goods_sn: String,
    val goods_unit: String,
    val id: Int,
    val is_app_exclusive: Int,
    val is_delete: Int,
    val is_hot: Int,
    val is_limited: Int,
    val is_new: Int,
    val is_on_sale: Int,
    val keywords: String,
    val list_pic_url: String,
    val name: String,
    val primary_pic_url: String,
    val primary_product_id: Int,
    val promotion_desc: String,
    val promotion_tag: String,
    val retail_price: Int,
    val sell_volume: Int,
    val sort_order: Int,
    val unit_price: Int
)

data class Issue(
    val answer: String,
    val goods_id: String,
    val id: Int,
    val question: String
)

data class Product(
    val goods_id: Int,
    val goods_number: Int,
    val goods_sn: String,
    val goods_specification_ids: String,
    val id: Int,
    val retail_price: Int
)

data class DataX(
    val add_time: String,
    val avatar: String,
    val content: String,
    val nickname: String,
    val pic_list: List<Pic>
)

data class Pic(
    val comment_id: Int,
    val id: Int,
    val pic_url: String,
    val sort_order: Int
)