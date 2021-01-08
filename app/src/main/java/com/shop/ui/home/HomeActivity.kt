package com.shop.ui.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.shop.R
import com.shop.adapter.home.*
import com.shop.model.bean.home.*
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_category_item.view.*
import kotlinx.android.synthetic.main.layout_channel_item.*
import kotlinx.android.synthetic.main.layout_channel_item.view.*
import kotlinx.android.synthetic.main.layout_channel_item.view.channel_img
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadHomeData()
    }

    //显示banner           名字   :    类型
    private fun showBanner(banner: List<Banner>) {
//        home_banner!!.setImages(banner)
//        home_banner!!.setImageLoader(MyBannerAdapter())
//        home_banner!!.start()
        // !!.  抛出空指针异常
        home_banner!!.setImages(banner).setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                //todo   转换path类型
                // as关键字可以用于对象的类型转换
                var pic = path as Banner
                Glide.with(context!!).load(pic.image_url).into(imageView!!)
            }
        }).start()
    }

    //显示品牌制作商直供
    private fun showBrand(brand : List<Brand>){
        //网格布局
        recy_brand!!.layoutManager = GridLayoutManager(this,2)
        //val 不可变参数
        val brandlist = arrayListOf<Brand>()
        //添加值
        brandlist.addAll(brand)
        //设置适配器
        val homeBrandAdapter = HomeBrandAdapter(brandlist, this)
        //绑定适配器
        recy_brand.adapter = homeBrandAdapter
    }

    //显示新品首发
    private fun showNewGoods(newgoods:List<NewGoods>){
        //网格布局
        recy_newgood!!.layoutManager = GridLayoutManager(this,2)
        //val 不可变参数
        val newgoodslist = arrayListOf<NewGoods>()
        //添加值
        newgoodslist.addAll(newgoods)
        //设置适配器
        val homeNewGoodsAdapter = HomeNewGoodsAdapter(newgoodslist, this)
        //绑定适配器
        recy_newgood.adapter = homeNewGoodsAdapter
    }

    //显示人气推荐
    private fun showHotGoods(hotgoods:List<HotGoods>){
        //线性布局
        recy_hotgoods.layoutManager = LinearLayoutManager(this)
        //分割线
        recy_hotgoods!!.addItemDecoration(DividerItemDecoration(this,LinearLayout.VERTICAL))
        //val 不可变参数
        val hotgoodslist = arrayListOf<HotGoods>()
        //添加值
        hotgoodslist.addAll(hotgoods)
        //设置适配器
        val homeHotGoodsAdapter = HomeHotGoodsAdapter(hotgoodslist, this)
        //绑定适配器
        recy_hotgoods.adapter = homeHotGoodsAdapter
    }

    //显示专题精选
    private fun showTopic(topic:List<Topic>){
        //横向线性
        recy_topic!!.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        //val 不可变参数
        val topiclist = arrayListOf<Topic>()
        //添加值
        topiclist.addAll(topic)
        //设置适配器
        val homeTopicAdapter = HomeTopicAdapter(topiclist, this)
        //绑定适配器
        recy_topic.adapter = homeTopicAdapter
    }

    //显示动态栏
    private fun showChannel(channel:List<Channel>){
        //清除所有布局
        layout_tab.removeAllViews()
        //val 不可变参数
        val layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f)
        //循环
        // 检测一个值在（in）或者不在（!in）一个区间或者集合中
        //  in修饰  从i开始  到  indices(个数) == size
        for (i in channel.indices){
            //找布局
            val inflate = LayoutInflater.from(this).inflate(R.layout.layout_channel_item, null)
            //赋值
            inflate.channel_name!!.text = channel!!.get(i)!!.name
            Glide.with(this).load(channel!!.get(i)!!.icon_url).into(inflate.channel_img)
            //文字居中
            inflate.channel_name.gravity = Gravity.CENTER
            //占位
            inflate.layoutParams = layoutParams
            //添加布局
            layout_tab.addView(inflate)
        }
    }

    //显示底部居家
    private fun showCategory(category:List<Category>){
        //循环放入布局    布局嵌套布局
        for ((index,value) in category.withIndex()){
            //找布局
            val inflate = LayoutInflater.from(this).inflate(R.layout.layout_category_item, null)
            //赋值
            inflate.cateory_title!!.text = value.name
            //添加值
            val goodslist = value.goodsList
            //网格布局
            inflate.categoty_rlv.layoutManager = GridLayoutManager(this,2)
            //设置适配器
            val homeCategoryAdapter = HomeCategoryAdapter(goodslist, this)
            //绑定适配器
            inflate.categoty_rlv.adapter = homeCategoryAdapter
            //添加布局
            mLl_category.addView(inflate)
        }
    }

    //请求到的数据
    private fun loadHomeData() {
        var thread_name = Thread.currentThread().name
        MainScope().launch {
            var thread_name1 = Thread.currentThread().name
            var result = homeData()
            Log.e("TAG",result.data.toString())
            //banner
            showBanner(result.data.banner)
            //品牌制作商直供
            showBrand(result.data.brandList)
            //新品首发
            showNewGoods(result.data.newGoodsList)
            //人气推荐
            showHotGoods(result.data.hotGoodsList)
            //专题精选
            showTopic(result.data.topicList)
            //动态栏
            showChannel(result.data.channel)
            //居家
            showCategory(result.data.categoryList)
        }
    }

    //网址
    suspend fun homeData(): HomeData {
        var url = "https://cdplay.cn/api/index"
        return get(url)
    }

    //网络请求
    suspend fun get(netUrl:String) = withContext(Dispatchers.IO){
        var url = URL(netUrl)
        (url.openConnection() as? HttpsURLConnection).run {
            var sb = StringBuffer()
            var streamReader = InputStreamReader(this!!.inputStream,"utf-8")
            var reader = BufferedReader(streamReader)
            reader.use {
                var temp = reader.readLine()
                if(temp != null) sb.append(temp)
            }
            streamReader.close()
            reader.close()
            inputStream.close()
            // :: 表示把一个方法当做一个参数，传递到另一个方法中进行使用，通俗的来讲就是引用一个方法。
            val homeData = Gson().fromJson<HomeData>(sb.toString(),HomeData::class.java)
            return@run homeData
        }
    }

}