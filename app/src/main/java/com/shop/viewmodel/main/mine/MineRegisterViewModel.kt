package com.shop.viewmodel.main.mine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.bean.me.MeRegisterBean
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class MineRegisterViewModel:BaseViewModel(Injection.repository) {

    //注册
    var meregister : MutableLiveData<List<MeRegisterBean>> = MutableLiveData()

    //获取注册接口
    fun MeRegister(username:String,password:String){
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.MeRegist(username,password)
            if(result.errno == 0){
                //切换线程
                meregister.postValue(listOf(result.data))
            }else if(result.errno == 665){
                //刷新token
                refreshToken
            }
        }
    }

}