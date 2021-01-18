package com.shop.viewmodel.main.mine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.bean.me.MeLoginBean
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class MineLoginViewModel:BaseViewModel(Injection.repository) {

    //登录
    var melogin : MutableLiveData<List<MeLoginBean>> = MutableLiveData()

    //获取登录接口
    fun MeLogin(username:String,password:String){
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.MeLogin(username,password)
            if(result.errno == 0){
                //切换线程
                melogin.postValue(listOf(result.data))
            }else if(result.errno == 665){
                //刷新token
                refreshToken
            }
        }
    }

}