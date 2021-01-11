package com.shop.api

import com.shop.model.bean.topic.TopicData
import com.shop.net.BaseResp
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceApi {

    @POST("auth/refreshToken")  //刷新token
    suspend fun refreshToken(): BaseResp<String>

    //专题
    @GET("topic/list?page=1&size=10")
    suspend fun getTopic():BaseResp<TopicData>

}