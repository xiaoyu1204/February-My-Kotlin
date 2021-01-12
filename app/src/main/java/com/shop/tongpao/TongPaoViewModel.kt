package com.shop.tongpao

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.bean.tongpao.FilePathList
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class TongPaoViewModel :BaseViewModel(Injection.repository){

    var stu:MutableLiveData<List<FilePathList>> = MutableLiveData(listOf())

    fun getMore(){
        viewModelScope.launch {
            var result = repository.getMore()
            if (result.status.statusCode == 100){
                stu.postValue(result.data.list)
            }
        }
    }

}