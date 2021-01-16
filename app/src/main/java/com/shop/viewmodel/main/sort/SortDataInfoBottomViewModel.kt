package com.shop.viewmodel.main.sort

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.bean.main.sort.DataX
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class SortDataInfoBottomViewModel:BaseViewModel(Injection.repository) {

    //分类右边详情rlv
    var sortdatainfobottom:MutableLiveData<List<DataX>> = MutableLiveData()

    //获取分类右边数据详情    rlv
    fun getSortInfoItem(id:Int){
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.getSortInfoItem(id)
            if(result.errno == 0){
                sortdatainfobottom.postValue(result.data.data)
            }
        }
    }

}