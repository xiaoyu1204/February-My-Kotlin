package com.shop.tongpao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvm.model.bean.tongpao.FilePathList
import com.example.basemvvm.model.myitem.IItemClick
import com.shop.BR
import com.shop.R
import com.shop.base.BaseActivity
import com.shop.databinding.ActivityTongPaoBinding
import kotlinx.android.synthetic.main.activity_tong_pao.*

class TongPaoActivity : BaseActivity<TongPaoViewModel,ActivityTongPaoBinding>(R.layout.activity_tong_pao,TongPaoViewModel::class.java) {

    //lateinit 延迟初始化
    var mAdapter : TongPaoAdapter? = null
    var list: List<FilePathList> = arrayListOf()

    override fun initView() {
        //设置布局管理器
        val layoutManager = LinearLayoutManager(this)
        mDataBinding!!.tongpaoRlv.layoutManager = layoutManager
        //分割线
        tongpao_rlv!!.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
    }

    override fun initVM() {
        //观察者模式
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
        mAdapter = TongPaoAdapter(this,list,array,ItemClickImpl())
        //绑定适配器
        mDataBinding!!.tongpaoRlv.adapter = mAdapter

    }

    override fun initVariable() {

    }

    inner class ItemClickImpl: IItemClick<FilePathList> {
        override fun itemClick(data: FilePathList) {
            Log.e("TAG",data.title)
        }
    }

}