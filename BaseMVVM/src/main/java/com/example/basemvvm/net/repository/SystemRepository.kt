package com.shop.net.repository

import com.shop.api.ServiceApi
import com.shop.net.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 数据仓库
 * 用来连接ViewModel和Model
 * 定义业务相关的网络请求接口的api
 */
class SystemRepository {

    private lateinit var serviceApi: ServiceApi

    //初始化的方法
    init {
        serviceApi = RetrofitFactory.instance.create(ServiceApi::class.java)
    }

    /**
     * 刷新token
     */
    suspend fun refreshToken() = withContext(Dispatchers.IO){
        serviceApi.refreshToken()
    }


    /**
     * 获取主页数据
     */
    suspend fun getHome() = withContext(Dispatchers.IO){
        serviceApi.getHome()
    }

    //品牌制造商
    suspend fun getBrand() = withContext(Dispatchers.IO){
        serviceApi.getBrand()
    }

    /**
     * 获取专题数据
     */
    suspend fun getTopic(page:Int) = withContext(Dispatchers.IO){
        serviceApi.getTopic(page)
    }

    /**
     * 获取同袍多布局
     */
    suspend fun getMore() = withContext(Dispatchers.IO){
        serviceApi.getMore()
    }

    /**
     * 获取商品列表数据
     */
    suspend fun getGoodList(map:HashMap<String,String>) = withContext(Dispatchers.IO){
        serviceApi.getGoodList(map)
    }

    /**
     * 获取商品详情上面数据
     */
    suspend fun getGoodTop() = withContext(Dispatchers.IO){
        serviceApi.getGoodTop()
    }

    /**
     * 分类竖着导航
     */
    suspend fun getSortNav() = withContext(Dispatchers.IO){
        serviceApi.getSortNav()
    }

    /**
     * 分类 用来请求当前分类的列表数据
     */
    suspend fun getSortData(id:Int) = withContext(Dispatchers.IO){
        serviceApi.getSortData(id)
    }

    /**
     * 分类   右边数据点击详情
     */
    suspend fun getSortDataInfo(id: Int) = withContext(Dispatchers.IO){
        serviceApi.getSortDataInfo(id)
    }

    /**
     * 分类   右边数据点击详情    rlv
     */
    suspend fun getSortInfoItem(id:Int) = withContext(Dispatchers.IO){
        serviceApi.getSortInfoItem(id)
    }

    /**
     * 登录
     */
    suspend fun MeLogin(username:String,password:String) = withContext(Dispatchers.IO){
        serviceApi.MeLogin(username,password)
    }

    /**
     * 注册
     */
    suspend fun MeRegist(username:String,password:String) = withContext(Dispatchers.IO){
        serviceApi.MeRegist(username,password)
    }

}