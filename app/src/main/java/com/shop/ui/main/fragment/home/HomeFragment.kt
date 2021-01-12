package com.shop.ui.main.fragment.home

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.shop.R
import com.shop.adapter.home.MyBannerAdapter
import com.shop.adapter.main.home.HomeBrandAdapter
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentHome2Binding
import com.shop.viewmodel.main.home.HomeViewModel

class HomeFragment:BaseFragment<HomeViewModel, FragmentHome2Binding>(R.layout.fragment_home2,HomeViewModel::class.java) {

    var mBrand:HomeBrandAdapter? = null

    /**
     * 提供Homefragment实例
     */
    companion object{
        val instance by lazy { HomeFragment() }
    }

    override fun initView() {


        //brand
        val layoutManager = GridLayoutManager(activity,2)
        mDataBinding!!.recyBrand.layoutManager = layoutManager
        mBrand = HomeBrandAdapter(context)
        mDataBinding!!.recyBrand.adapter = mBrand

    }

    override fun initVM() {
        initBrand()
    }

    override fun initData() {
        mViewModel.getHome()
    }

    override fun initVariable() {

    }

    private fun initBrand(){
        mViewModel!!.brend.observe(this, Observer {
            mBrand!!.refreshData(it)
        })
    }

}