package com.shop.ui.main.fragment.car

import com.shop.R
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentShopBinding
import com.shop.viewmodel.car.CarViewModel

class CarFragment:BaseFragment<CarViewModel, FragmentShopBinding>(R.layout.fragment_shop,CarViewModel::class.java) {

    companion object{
        val instance by lazy { CarFragment() }
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