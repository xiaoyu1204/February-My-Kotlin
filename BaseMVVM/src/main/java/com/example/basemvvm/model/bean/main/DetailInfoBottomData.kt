package com.example.basemvvm.model.bean.main

data class DetailInfoBottomData(
    val goodsList: List<Goods>
)

data class Goods(
    val id: Int,
    val list_pic_url: String,
    val name: String,
    val retail_price: Int
)