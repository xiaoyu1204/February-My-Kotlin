package com.shop.app

import android.app.Application

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        map = HashMap()
    }

    companion object{
        var instance:MyApp? = null
        var map:HashMap<String,Any>? = null
    }

}