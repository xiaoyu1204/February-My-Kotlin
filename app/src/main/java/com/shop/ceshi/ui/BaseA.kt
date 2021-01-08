package com.shop.ceshi.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.shop.R
import com.shop.ceshi.model.bean.User
import com.shop.ui.login.Result
import kotlinx.coroutines.*
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import java.io.BufferedReader
import java.io.InputStreamReader

class BaseA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        MainScope().launch {
            getHome()
        }

        testExt()

    }

    fun testExt(){
        var user = User("小雨", 20);
    }

    //suspend 定义的方法
    suspend fun getHome(){
        val result = get("https://cdplay.cn/api/index")
//        Log.e("TAG", "getHome: "+result.toString())
    }
    suspend fun get(net:String) = withContext(Dispatchers.IO){
        val url = URL(net)
        var httpConnect = url.openConnection() as? HttpsURLConnection
        httpConnect.run {

        }
        (url.openConnection() as? HttpsURLConnection)?.run{
            requestMethod = "GET"
            setRequestProperty("Content-Type","application/json;utf-8")
            setRequestProperty("Accept","application/json")
//            Log.e("TAG", "get: "+responseCode.toString())
            //定义接收数据的sb
            var data = StringBuffer()
            // InputStreamReader 读取httpurlconnection拿到的数据
            val streaReader = InputStreamReader(this.inputStream,"utf-8")
            // new BufferedReader(streamReader);  -> java
            val reader = BufferedReader(streaReader)
            // while() -> java
            reader.use {
                var temp = it.readLine()    //读取数据的一行
                if(temp != null) data.append(temp)
            }
            reader.close()
            streaReader.close()
            inputStream.close()
            return@run Result.Success(data)
        }
//        //return@withContext Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    suspend fun fetchDownDoc() = coroutineScope {
        val one = async { getHome() }
        var two = async {  }
    }

}