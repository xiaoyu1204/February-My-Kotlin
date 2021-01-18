package com.shop.viewmodel.main.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.bean.main.DetailInfoData
import com.example.basemvvm.model.bean.main.Goods
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class DetailInfoViewModel: BaseViewModel(Injection.repository) {

    var detailinfo: MutableLiveData<DetailInfoData> = MutableLiveData()
    var detailinfobottom: MutableLiveData<List<Goods>> = MutableLiveData()

    //商品详情
    fun getDetailInfo(id:Int){
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.getDetailInfo(id)
            if(result.errno == 0){
                detailinfo.postValue(result.data)
            }
        }
    }

    //商品详情下面
    fun getDetailInfoBottom(id:Int){
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.getDetailInfoBottom(id)
            if(result.errno == 0){
                detailinfobottom.postValue(result.data.goodsList)
            }
        }
    }

}