package com.shop.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.shop.R
import com.shop.adapter.home.MyBannerAdapter
import com.shop.model.bean.home.Banner
import com.shop.model.bean.home.HomeData
import com.shop.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class HomeActivity : AppCompatActivity() {

    lateinit var homeVM:HomeViewModel

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