package com.shop.ui.main.fragment.home

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shop.R
import com.shop.adapter.home.MyBannerAdapter
import com.shop.adapter.main.home.HomeBrandAdapter
import com.shop.adapter.main.home.HomeHotGoodsAdapter
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentHome2Binding
import com.shop.viewmodel.main.home.HomeViewModel

class HomeFragment:BaseFragment<HomeViewModel, FragmentHome2Binding>(R.layout.fragment_home2,HomeViewModel::class.java) {

    var mBanner: MyBannerAdapter? = null
    var mBrand:HomeBrandAdapter? = null
    var mAdapter : HomeHotGoodsAdapter? = null

    /**
     * 提供Homefragment实例
     */
    companion object{
        val instance by lazy { HomeFragment() }
    }

    override fun initView() {

        //banner
        mBanner = MyBannerAdapter()

        //brand
        val layoutManager = GridLayoutManager(activity,2)
        mDataBinding!!.recyBrand.layoutManager = layoutManager
        mBrand = HomeBrandAdapter(context)
        mDataBinding!!.recyBrand.adapter = mBrand

        //hot
        val layoutManager2 = LinearLayoutManager(context)
        mDataBinding!!.recyHotgoods.layoutManager = layoutManager2
        mAdapter = HomeHotGoodsAdapter(context)
        mDataBinding!!.recyHotgoods.adapter = mAdapter

    }

    override fun initVM() {
        initBan()
        initBrand()
        initHotGoods()
    }

    override fun initData() {
        mViewModel.getHome()
    }

    override fun initVariable() {

    }

    private fun initBan() {
        mViewModel!!.banner.observe(this, Observer {
            mDataBinding!!.homeBanner.setImages(it).setImageLoader(mBanner).start()
        })
    }


    private fun initBrand(){
        mViewModel!!.brend.observe(this, Observer {
            mBrand!!.refreshData(it)
        })
    }

    private fun initHotGoods() {
        mViewModel.hotGoods.observe(this, Observer {
            mAdapter!!.refreshData(it)
        })
    }

}