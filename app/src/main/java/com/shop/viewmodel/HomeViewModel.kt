package com.shop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.shop.model.bean.home.Banner
import com.shop.model.bean.home.Brand
import com.shop.model.bean.home.HomeData
import com.shop.model.bean.home.HotGoods
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class HomeViewModel: ViewModel() {

    // 定义轮播图数据对象
    var banner:MutableLiveData<List<Banner>> = MutableLiveData()

    // 定义一个品牌制造商直供
    var brand:MutableLiveData<List<Brand>> = MutableLiveData()

    // 定义一个热门数据
    var hotGoods:MutableLiveData<List<HotGoods>> = MutableLiveData()

    //网络请求的状态值  -1 网络请求错误
    var loadStatue:MutableLiveData<Int> = MutableLiveData()

    //TODO 加载首页数据
    fun loadHomeData(){
        GlobalScope.launch {
            loadData()
        }
    }

    suspend fun loadData(){
        var homeData = get("https://cdplay.cn/api/index")
        if(homeData != null){
            banner.postValue(homeData.data.banner)
            brand.postValue(homeData.data.brandList)
            hotGoods.postValue(homeData.data.hotGoodsList)
        }else{
            loadStatue.postValue(-1)
        }
    }

    //TODO 网络请求
    suspend fun get(str:String) = withContext(Dispatchers.IO){
        val result = URL(str).readText(charset("utf-8"))
        return@withContext Gson().fromJson(result, HomeData::class.java)
    }

}