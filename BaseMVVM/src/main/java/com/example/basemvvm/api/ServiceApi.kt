package com.shop.api

import com.example.basemvvm.model.bean.main.DetailInfoBottomData
import com.example.basemvvm.model.bean.main.DetailInfoData
import com.example.basemvvm.model.bean.main.home.BrandData
import com.example.basemvvm.model.bean.main.home.GoodListData
import com.example.basemvvm.model.bean.main.home.GoodTopData
import com.example.basemvvm.model.bean.main.sort.SortDataInfo
import com.example.basemvvm.model.bean.main.sort.SortDataInfoBottomData
import com.example.basemvvm.model.bean.main.topic.TopicData
import com.example.basemvvm.model.bean.me.MeLoginBean
import com.example.basemvvm.model.bean.me.MeRegisterBean
import com.example.basemvvm.model.bean.tongpao.TongPaoData
import com.example.myshop.model.bean.shop.sort.SortDataBean
import com.example.myshop.model.bean.shop.sort.SortNavBean
import com.shop.model.bean.home.HomeData2
import com.shop.net.BaseResp
import retrofit2.http.*
import java.util.HashMap

interface ServiceApi {

    //首页
    @GET("index")
    suspend fun getHome():BaseResp<HomeData2>   // BaseResp抽取的一个bean类的外层结构 homeData当前接口返回的具体

    //刷新token
    @POST("auth/refreshToken")
    suspend fun refreshToken(): BaseResp<String>

    //同袍多布局
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

    @GET("catalog/index")
    suspend fun getSortNav() : SortNavBean

    @GET("catalog/current")
    suspend fun getSortData(@Query("id")id : Int) : SortDataBean

    //分类右边数据点击详情
    @GET("goods/category")
    suspend fun getSortDataInfo(@Query("id")id:Int):BaseResp<SortDataInfo>

    //分类右边数据点击详情rlv
    @GET("goods/list?page=1&size=100")
    suspend fun getSortInfoItem(@Query("categoryId")id:Int):BaseResp<SortDataInfoBottomData>

    //专题
    @GET("topic/list")
    suspend fun getTopic(@Query("page")page:Int):BaseResp<TopicData>

    //登录接口
    @POST("auth/login")
    @FormUrlEncoded
    suspend fun MeLogin(@Field("username")username:String,@Field("password")password:String):BaseResp<MeLoginBean>

    //注册接口
    @POST("auth/registernew")
    @FormUrlEncoded
    suspend fun MeRegist(@Field("username")username:String,@Field("password")password:String):BaseResp<MeRegisterBean>

    //商品详情  id=1155000
    @GET("goods/detail")
    suspend fun getDetailInfo(@Query("id")id:Int):BaseResp<DetailInfoData>

    //商品详情底部数据  id=1155000
    @GET("goods/related")
    suspend fun getDetailInfoBottom(@Query("id")id:Int):BaseResp<DetailInfoBottomData>

}