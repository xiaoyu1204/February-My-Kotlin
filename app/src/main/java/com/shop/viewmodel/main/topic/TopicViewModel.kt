package com.shop.viewmodel.topic

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.bean.main.topic.TopicData
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class TopicViewModel:BaseViewModel(Injection.repository) {

    //专题数据
    var datax : MutableLiveData<List<TopicData.DataX>> = MutableLiveData(listOf())

    fun getTopic(page:Int){
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.getTopic(page)
            if(result.errno == 0){
                //切换线程
                datax.postValue(result.data.data)
            }else if(result.errno == 665){
                //刷新token
                refreshToken
            }
        }
    }

}