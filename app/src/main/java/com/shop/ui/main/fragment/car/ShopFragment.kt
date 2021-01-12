package com.shop.ui.main.fragment.car

import com.shop.R
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentShopBinding
import com.shop.viewmodel.shop.ShopViewModel

class ShopFragment:BaseFragment<ShopViewModel, FragmentShopBinding>(R.layout.fragment_shop,ShopViewModel::class.java) {

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