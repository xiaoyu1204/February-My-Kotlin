package com.shop.viewmodel.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.bean.main.home.DataX
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class BrandViewModel: BaseViewModel(Injection.repository) {

    var dataX : MutableLiveData<List<DataX>> = MutableLiveData(listOf())


    fun getBrand() {
        viewModelScope.launch {
            var result = repository.getBrand()
            if(result.errno == 0){
                dataX.postValue(result.data.data)
            }
        }

    }

}