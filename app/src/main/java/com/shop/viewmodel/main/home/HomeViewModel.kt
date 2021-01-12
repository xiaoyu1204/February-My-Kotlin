package com.shop.viewmodel.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shop.base.BaseViewModel
import com.shop.model.bean.home.*
import com.shop.net.Injection
import kotlinx.coroutines.launch

class HomeViewModel: BaseViewModel(Injection.repository){

    var banner:MutableLiveData<List<Banner2>> = MutableLiveData()
    var brend:MutableLiveData<List<Brand2>> = MutableLiveData()
    var hotGoods: MutableLiveData<List<HotGoods2>> = MutableLiveData()
    var newGoods:MutableLiveData<List<NewGoods2>> = MutableLiveData()
    var channel:MutableLiveData<List<Channel2>> = MutableLiveData()
    var category:MutableLiveData<List<Category2>> = MutableLiveData()
    var topic:MutableLiveData<List<Topic2>> = MutableLiveData()
    var loadStatue:MutableLiveData<Int> = MutableLiveData()

    //获取首页的数据
    fun getHome(){
        viewModelScope.launch {
            var result = repository.getHome()
            if(result.errno == 0){
                banner.postValue(result.data.banner)
                brend.postValue(result.data.brandList)
                hotGoods.postValue(result.data.hotGoodsList)
                newGoods.postValue(result.data.newGoodsList)
                channel.postValue(result.data.channel)
                category.postValue(result.data.categoryList)
                topic.postValue(result.data.topicList)
            }else if(result.errno == 665){
                refreshToken
            }
        }
    }

}
