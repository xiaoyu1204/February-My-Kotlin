package com.shop.api

import com.example.basemvvm.model.bean.main.home.BrandData
import com.example.basemvvm.model.bean.main.home.GoodListData
import com.example.basemvvm.model.bean.main.home.GoodTopData
import com.example.basemvvm.model.bean.tongpao.TongPaoData
import com.example.myshop.model.bean.shop.sort.SortDataBean
import com.example.myshop.model.bean.shop.sort.SortNavBean
import com.shop.model.bean.home.HomeData2
import com.shop.model.bean.topic.TopicData
import com.shop.net.BaseResp
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.HashMap

interface ServiceApi {

    @GET("index")
    suspend fun getHome():BaseResp<HomeData2>   // BaseResp抽取的一个bean类的外层结构 homeData当前接口返回的具体

    @POST("auth/refreshToken")  //刷新token
    suspend fun refreshToken(): BaseResp<String>

    //专题
    @GET("topic/list?page=1&size=10")
    suspend fun getTopic():BaseResp<TopicData>

    //同袍多布局?categoryId=1005007&page=1&size=100
    @GET("discover/hot.json")
    suspend fun getMore():TongPaoData

    //品牌制造
    @GET("brand/list")
    suspend fun getBrand():BaseResp<BrandData>

    //商品列表详情
    @GET("goods/list")
    suspend fun getGoodList(@QueryMap map: HashMap<String, String>):BaseResp<GoodListData>

    //商品详情上面数据
    @GET("goods/hot")
    suspend fun getGoodTop():BaseResp<GoodTopData>

    //https://cdplay.cn/api/catalog/index 分类竖着导航
    @GET("catalog/index")
    suspend fun getSortNav() : SortNavBean

    // https://cdplay.cn/api/  用来请求当前分类的列表数据
    @GET("catalog/current")
    suspend fun getSortData(@Query("id")id : Int) : SortDataBean

}