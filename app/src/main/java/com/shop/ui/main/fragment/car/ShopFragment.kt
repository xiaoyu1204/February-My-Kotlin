package com.shop.ui.main.fragment.car

import com.shop.R
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentShop2Binding
import com.shop.viewmodel.shop.ShopViewModel

class ShopFragment:BaseFragment<ShopViewModel,FragmentShop2Binding>(R.layout.fragment_shop2,ShopViewModel::class.java) {

    companion object{
        val instance by lazy { ShopFragment() }
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