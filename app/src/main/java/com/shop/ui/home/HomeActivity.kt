package com.shop.ui.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.shop.R
import com.shop.model.bean.home.Banner
import com.shop.model.bean.home.HomeData
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.activity_home.*
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

    //显示banner
    private fun showBanner(banner: List<Banner>) {
        // !!.  抛出空指针异常
        home_banner!!.setImages(banner).setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                //todo   转换path类型
                //  as  强转
                var pic = path as Banner
                Glide.with(context!!).load(pic.image_url).into(imageView!!)
            }
        }).start()
    }

    private fun loadHomeData() {
        var thread_name = Thread.currentThread().name
        MainScope().launch {
            var thread_name1 = Thread.currentThread().name
            var result = homeData()
//            Log.e("TAG",result.data.toString());
            showBanner(result.data.banner)
        }
//        Log.e("TAG","loadHomeData")
    }

    suspend fun homeData(): HomeData {
        var url = "https://cdplay.cn/api/index"
        return get(url)
    }

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
            val homeData = Gson().fromJson<HomeData>(sb.toString(),HomeData::class.java)
            return@run homeData
        }
    }

}