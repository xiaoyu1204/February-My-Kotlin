package com.shop.api

import com.example.basemvvm.model.bean.tongpao.TongPaoData
import com.shop.model.bean.home.HomeData2
import com.shop.model.bean.topic.TopicData
import com.shop.net.BaseResp
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceApi {

    @GET("index")
    suspend fun getHome():BaseResp<HomeData2>   // BaseResp抽取的一个bean类的外层结构 homeData当前接口返回的具体

    @POST("auth/refreshToken")  //刷新token
    suspend fun refreshToken(): BaseResp<String>

    //专题
    @GET("topic/list?page=1&size=10")
    suspend fun getTopic():BaseResp<TopicData>

    //同袍多布局
    @GET("discover/hot.json")
    suspend fun getMore():TongPaoData



}