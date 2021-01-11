package com.shop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shop.R
import com.shop.viewmodel.home.HomeViewModel

class HomeActivity : AppCompatActivity() {

    lateinit var homeVM: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initVM()
        homeVM.loadHomeData()
    }

    private fun initVM(){
        //初始化ViewModel
        homeVM = ViewModelProvider(this).get(HomeViewModel::class.java)
        //监听网络状态的变化
        homeVM.loadStatue.observe(this, Observer { status ->
            if (status == -1) {
                Log.e("TAG", "HomeActivity: "+"数据加载失败" )
            }
        })

        //监听轮播图数据的变化
        homeVM.banner.observe(this, Observer { banner ->
            Log.e("TAG", "banner" )
        })

        //品牌直供
        homeVM.brand.observe(this, Observer {
            Log.e("TAG","brend")
        })

        //热门商品
        homeVM.hotGoods.observe(this, Observer {
            Log.e("TAG","hotGoods")
        })

    }

}