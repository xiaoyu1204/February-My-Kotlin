package com.shop.viewmodel.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.bean.main.home.BannerInfo
import com.example.basemvvm.model.bean.main.home.GoodListData
import com.example.basemvvm.model.bean.main.home.GoodTopData
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class GoodListViewModel:BaseViewModel(Injection.repository){

    var goodListData: MutableLiveData<GoodListData> = MutableLiveData()   //商品列表的数据
    var goodTopData: MutableLiveData<BannerInfo> = MutableLiveData()   //商品上面的数据

    /**
     * 获取商品数据列表
     */
    fun getGoodList(map:HashMap<String,String>){
        viewModelScope.launch {
            var result = repository.getGoodList(map)
            goodListData.postValue(result.data)
        }
    }

    /**
     * 商品详情上面数据
     */
     fun getGoodTop(){
        viewModelScope.launch {
            val goodTop = repository.getGoodTop()
            goodTopData.postValue(goodTop.data.bannerInfo)
        }
    }

}