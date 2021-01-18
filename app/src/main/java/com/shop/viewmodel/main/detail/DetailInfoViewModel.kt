package com.shop.viewmodel.main.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.bean.main.DetailInfoData
import com.example.basemvvm.model.bean.main.Goods
import com.example.basemvvm.model.bean.main.car.CarAddBean
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class DetailInfoViewModel: BaseViewModel(Injection.repository) {

    var detailinfo: MutableLiveData<DetailInfoData> = MutableLiveData()
    var detailinfobottom: MutableLiveData<List<Goods>> = MutableLiveData()
    //加入购物车
    var addCar:MutableLiveData<List<CarAddBean>> = MutableLiveData()

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

    //加入购物车
    fun AddCar(map: HashMap<String,String>){
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.AddCar(map)
            if(result.errno == 0){
                addCar.postValue(listOf(result.data))
            }else{
                Log.e("TAG", "AddCar: "+result.errmsg )
            }
        }
    }

}