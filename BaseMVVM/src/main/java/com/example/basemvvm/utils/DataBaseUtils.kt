package com.example.basemvvm.utils

import androidx.room.Room
import com.example.basemvvm.test.AppDatabase
import com.shop.app.MyApp

class DataBaseUtils {
    companion object{
        var db = Room.databaseBuilder(MyApp.instance!!.baseContext, AppDatabase::class.java,"shop")
            .allowMainThreadQueries()  //允许在主线程中查询数据
            .build()
    }
}