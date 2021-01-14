package com.shop.viewmodel.sort

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myshop.model.bean.shop.sort.*
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class SortViewModel:BaseViewModel(Injection.repository) {

    //竖导航
    var sortnav : MutableLiveData<List<Category>> = MutableLiveData()
    //边上数据
    var sortdata : MutableLiveData<SortDataBean.CurrentCategory> = MutableLiveData()

    //获取分类
    fun getSortNav(){
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.getSortNav()
            if(result.errno == 0){
                //切换线程
                sortnav.postValue(result.data.categoryList)
            }else if(result.errno == 665){
                //刷新token
                refreshToken
            }
        }
    }

    //获取分类上面的数据
    fun getSortData(id : Int){
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.getSortData(id)
            if(result.errno == 0){
                //切换线程
                sortdata.postValue(result.data.currentCategory)
            }else if(result.errno == 665){
                //刷新token
                refreshToken
            }
        }
    }

}