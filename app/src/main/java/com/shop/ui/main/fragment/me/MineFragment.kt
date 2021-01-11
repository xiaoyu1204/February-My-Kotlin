package com.shop.ui.main.fragment.me

import com.shop.R
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentMine2Binding
import com.shop.viewmodel.mine.MineViewModel

class MineFragment:BaseFragment<MineViewModel,FragmentMine2Binding>(R.layout.fragment_mine2,MineViewModel::class.java) {

    companion object{
        val instance by lazy { MineFragment() }
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