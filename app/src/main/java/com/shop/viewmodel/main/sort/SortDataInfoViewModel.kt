package com.shop.viewmodel.main.sort

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.bean.main.sort.BrotherCategory
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class SortDataInfoViewModel:BaseViewModel(Injection.repository) {

    //分类右边数据详情
    var sortdatainfo:MutableLiveData<List<BrotherCategory>> = MutableLiveData()

    //获取分类右边数据详情
    fun getSortDataInfo(id:Int){
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result=repository.getSortDataInfo(id)
            if(result.errno == 0){
                sortdatainfo.postValue(result.data.brotherCategory)
            }
        }
    }

}