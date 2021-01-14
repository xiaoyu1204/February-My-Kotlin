package com.shop.ui.main.fragment.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvm.model.bean.main.home.DataX
import com.example.basemvvm.model.myitem.IItemClick
import com.shop.BR
import com.shop.R
import com.shop.adapter.home.BrandInAdapter
import com.shop.base.BaseActivity
import com.shop.databinding.ActivityBrandInfoBinding
import com.shop.viewmodel.main.home.BrandViewModel

class BrandInfoActivity: BaseActivity<BrandViewModel, ActivityBrandInfoBinding>(R.layout.activity_brand_info,
    BrandViewModel::class.java){

    var mAdapter : BrandInAdapter? = null
    var list: List<DataX> = arrayListOf()

    override fun initView() {
        val layoutManager = LinearLayoutManager(this)
        mDataBinding!!.rlvBrandInfo.layoutManager = layoutManager
    }

    override fun initVM() {
        mViewModel!!.dataX.observe(this, Observer {
            mAdapter!!.refreshData(it)
        })
    }

    override fun initData() {
        mViewModel.getBrand()

        //封装xml布局界面的id和界面中需要绑定的数据对应的id
        var array = SparseArray<Int>()
        array.put(R.layout.item_tv_brand, BR.vmBrandInfo)
        mAdapter = BrandInAdapter(this,list,array,ItemClickImpl())
        mDataBinding!!.rlvBrandInfo.adapter = mAdapter

    }

    inner class ItemClickImpl: IItemClick<DataX> {
        override fun itemClick(data: DataX) {
            Log.i("TAG",data.name)
        }

    }

    override fun initVariable() {

    }

}