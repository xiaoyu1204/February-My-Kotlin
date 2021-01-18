package com.shop.app

import android.os.Environment
import com.example.basemvvm.utils.SpUtils
import java.io.File

class Constants {

    companion object{

        var token = SpUtils.instance!!.getString("token")

        //基础地址
        val BASE_URL:String = "https://cdplay.cn/api/"
//        val BASE_URL:String = "http://cdwan.cn:7000/tongpao/"

        //网络缓存的地址
        val PATH_DATA = MyApp.app!!.cacheDir.absolutePath + File.separator + "data"
        //拼接一个地址
        @JvmField
        val PATH_IMGS = PATH_DATA + "/tp/imgs"
        @JvmField
        val APK_PATH = Environment.getStorageDirectory().toString() + File.separator + "apk/"
        const val HEAD_WIDTH = 160
        const val HEAD_HEIGHT = 160

    }

}