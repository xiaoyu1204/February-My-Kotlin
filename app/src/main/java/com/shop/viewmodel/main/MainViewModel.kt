package com.shop.viewmodel.main

import androidx.fragment.app.Fragment
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import com.shop.ui.main.fragment.car.CarFragment
import com.shop.ui.main.fragment.home.HomeFragment
import com.shop.ui.main.fragment.me.MineFragment
import com.shop.ui.main.fragment.topic.TopicFragment
import com.shop.ui.sort.SortFragment

class MainViewModel:BaseViewModel(Injection.repository){

    var fragments:MutableList<Fragment> = mutableListOf()

    init {
        fragments.add(HomeFragment.instance)
        fragments.add(TopicFragment.instance)
        fragments.add(SortFragment.instance)
        fragments.add(CarFragment.instance)
        fragments.add(MineFragment.instance)
    }

}