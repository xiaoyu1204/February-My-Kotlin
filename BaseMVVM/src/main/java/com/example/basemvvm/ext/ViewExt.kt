package com.example.basemvvm.ext

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment

// 封装Activity界面组件获取
fun <V:View> Activity.bindView(id:Int):Lazy<V> = lazy {
    viewFinder(id) as V
}

// 查找activity中的组件
private val Activity.viewFinder:Activity.(Int) -> View?
    get() = {
        findViewById(it)
    }

// 封装fragment界面组件获取
fun <V:View> Fragment.bindView(id:Int):Lazy<V> = lazy {
    viewFinder(id) as V
}

private val Fragment.viewFinder:Fragment.(Int) -> View?
    get() = {
        view!!.findViewById(it)
    }
