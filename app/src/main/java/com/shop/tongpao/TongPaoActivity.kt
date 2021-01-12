package com.shop.tongpao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvm.model.bean.tongpao.FilePathList
import com.shop.BR
import com.shop.R
import com.shop.base.BaseActivity
import com.shop.databinding.ActivityTongPaoBinding

class TongPaoActivity : BaseActivity<TongPaoViewModel,ActivityTongPaoBinding>(R.layout.activity_tong_pao,TongPaoViewModel::class.java) {

    var mAdapter : TongPaoAdapter? = null
    var list: List<FilePathList> = arrayListOf()

    override fun initView() {
        val layoutManager = LinearLayoutManager(this)
        mDataBinding!!.tongpaoRlv.layoutManager = layoutManager
    }

    override fun initVM() {
        mViewModel.stu.observe(this, Observer {
            mAdapter!!.refreshData(it)
        })
    }

    override fun initData() {
        mViewModel.getMore()

        //封装xml布局界面的id和界面中需要绑定的数据对应的id
        var array = SparseArray<Int>()
        array.put(R.layout.layout_tongpao_item, BR.tong_item)
        array.put(R.layout.layout_tongpao_item1, BR.tong_item1)
        array.put(R.layout.layout_tongpao_item2, BR.tongpao_item2)
        mAdapter = TongPaoAdapter(this,list,array)
        mDataBinding!!.tongpaoRlv.adapter = mAdapter

    }

    override fun initVariable() {

    }

}