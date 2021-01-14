package com.shop.ui.main.fragment.home

import android.content.Intent
import android.util.Log
import android.util.SparseArray
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvm.model.myitem.IItemClick
import com.shop.BR
import com.shop.R
import com.shop.adapter.home.MyBannerAdapter
import com.shop.adapter.main.home.HomeBrandAdapter
import com.shop.adapter.main.home.HomeHotGoodsAdapter
import com.shop.adapter.main.home.NewGoodAdapter
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentHome2Binding
import com.shop.model.bean.home.NewGoods2
import com.shop.viewmodel.main.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.home_brand_text
import kotlinx.android.synthetic.main.fragment_home2.*

class HomeFragment:BaseFragment<HomeViewModel, FragmentHome2Binding>(R.layout.fragment_home2,HomeViewModel::class.java) {

    var mBanner: MyBannerAdapter? = null
    var mBrand:HomeBrandAdapter? = null
    var mAdapter : HomeHotGoodsAdapter? = null

    lateinit var newGoodAdapter: NewGoodAdapter
    var newgoodList:MutableList<NewGoods2> = mutableListOf()

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

        //品牌的跳转
        home_brand_text.setOnClickListener(View.OnClickListener {
            var intent = Intent(context,
                BrandInfoActivity::class.java)
            startActivity(intent)
        })

    }

    override fun initVM() {
        initBan()
        initBrand()
        initNewGood()
        initHotGoods()
        mDataBinding.setVariable(BR.homeClick,TitleClick())
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

    //品牌制造
    private fun initBrand(){
        mViewModel!!.brend.observe(this, Observer {
            mBrand!!.refreshData(it)
        })
    }

    //初始化新品发布
    fun initNewGood(){
        mDataBinding.recyNewgood.layoutManager = GridLayoutManager(context,2)
        var newGoodArr = SparseArray<Int>()
        newGoodArr.put(R.layout.layout_newgood_item2,com.example.basemvvm.BR.vmNewGood)
        var newGoodClick = NewGoodItemClick()
        newGoodAdapter = NewGoodAdapter(context!!, newgoodList, newGoodArr, newGoodClick)
        mDataBinding.recyNewgood.adapter = newGoodAdapter
    }

    //热门商品
    private fun initHotGoods() {
        mViewModel.hotGoods.observe(this, Observer {
            mAdapter!!.refreshData(it)
        })
    }

    /**
     * 新品发布
     */
    inner class NewGoodItemClick: IItemClick<NewGoods2> {
        override fun itemClick(data: NewGoods2) {

        }

    }

    inner class TitleClick{
        //新品发布
        fun clickNewGood(){
            var intent = Intent(context,GoodListDetailActivity::class.java)
            startActivity(intent)
        }
    }

}