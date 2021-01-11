package com.shop.ui.main.fragment.home

import com.shop.R
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentHome2Binding
import com.shop.viewmodel.main.home.HomeViewModel

class HomeFragment:BaseFragment<HomeViewModel, FragmentHome2Binding>(R.layout.fragment_home2,HomeViewModel::class.java) {

    /**
     * 提供Homefragment实例
     */
    companion object{
        val instance by lazy { HomeFragment() }
    }

    override fun initView() {

    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {

    }

}