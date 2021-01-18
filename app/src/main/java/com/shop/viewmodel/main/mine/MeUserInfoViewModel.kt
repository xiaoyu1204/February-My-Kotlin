package com.shop.viewmodel.main.mine

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.bean.me.MeUserInfoBean
import com.example.basemvvm.utils.ToastUtils
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class MeUserInfoViewModel:BaseViewModel(Injection.repository) {

    var meuserinfo:MutableLiveData<List<MeUserInfoBean>> = MutableLiveData()

    fun MeUserInfo(map: HashMap<String, String>){
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.MeUserInfo(map)
            if(result.errno == 0){
                meuserinfo.postValue(listOf(result.data))
            }else{
                Log.e("TAG", "MeUserInfo: "+result.errmsg )
            }
        }
    }

}