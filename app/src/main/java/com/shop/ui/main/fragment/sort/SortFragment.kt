package com.shop.ui.sort

import androidx.fragment.app.Fragment
import com.shop.R
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentSort2Binding
import com.shop.viewmodel.sort.SortViewModel

class SortFragment:BaseFragment<SortViewModel,FragmentSort2Binding>(R.layout.fragment_sort2,SortViewModel::class.java) {

    companion object{
        val instance by lazy { SortFragment() }
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