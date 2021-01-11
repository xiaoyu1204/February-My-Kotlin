package com.shop.viewmodel.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basemvvm.utils.MyMmkv
import com.google.gson.Gson
import com.shop.model.bean.home.ChannelTypeCategory
import com.shop.model.bean.home.HomeChannelTypeData
import com.example.basemvvm.utils.SpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class HomeChannelTypeViewModel: ViewModel() {

    //分类居家
    //https://cdplay.cn/api/catalog/index/pages/category/category?id=1005000
    //https://cdplay.cn/api/catalog/index?mur=url(传过来的url)

    var home_tree_name:String? = "home_tree_name"
    var home_tree_front_name:String? = "home_tree_front_name"
    // 定义tablayout  vp数据对象
    var category:MutableLiveData<List<ChannelTypeCategory>> = MutableLiveData()

    //网络请求的状态值  -1 网络请求错误
    var loadStatue:MutableLiveData<Int> = MutableLiveData()

    //TODO 加载数据
    fun loadChanneltypeData(){
        GlobalScope.launch {
            loadData()
        }
    }

    suspend fun loadData(){
        val url = MyMmkv.getString("murl")
        var homechanneltypeData = get("https://cdplay.cn/api/catalog/index?mur="+url)
        if(homechanneltypeData != null){
            category.postValue(homechanneltypeData.data.categoryList)
            home_tree_name = homechanneltypeData.data.categoryList.get(0).name
            home_tree_front_name = homechanneltypeData.data.categoryList.get(0).front_name
        }else{
            loadStatue.postValue(-1)
        }
    }

    //网络请求
    suspend fun get(str:String) = withContext(Dispatchers.IO){
        var result = URL(str).readText(charset("utf-8"))
        return@withContext Gson().fromJson(result,HomeChannelTypeData::class.java)
    }

}