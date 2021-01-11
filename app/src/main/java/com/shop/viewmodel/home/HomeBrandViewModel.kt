package com.shop.viewmodel.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.shop.model.bean.home.HomeBrandData
import com.example.basemvvm.utils.SpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class HomeBrandViewModel: ViewModel() {

    var stauts:MutableLiveData<Int> = MutableLiveData()
    var fr_topic_info_tv_title:String? = "fr_topic_info_tv_title"
    var fr_topic_info_tv_desc:String? = "fr_topic_info_tv_desc"

    fun homebrandData(){
        GlobalScope.launch {
            loadData()
        }
    }

    suspend fun loadData(){
        var id = SpUtils.instance!!.getInt("id")
        var homebranddata = get("https://cdplay.cn/api/brand/detail?id="+id)
        if(homebranddata != null){
            fr_topic_info_tv_title = homebranddata.data.brand.name
            fr_topic_info_tv_desc = homebranddata.data.brand.simple_desc
            stauts.postValue(0)
        }
    }

    //网络请求
    suspend fun get(str:String) = withContext(Dispatchers.IO){
        val result = URL(str).readText(charset("utf-8"))
        Log.e("TAG", "loadData: "+result)
        return@withContext Gson().fromJson(result,HomeBrandData::class.java)
    }

}