package com.shop.ui.main.fragment.sort

import android.content.Intent
import android.util.Log
import android.util.SparseArray
import android.widget.ScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.basemvvm.model.myitem.IItemClick
import com.example.myshop.model.bean.shop.sort.SortDataBean
import com.shop.BR
import com.shop.R
import com.shop.adapter.main.sort.SortCategroysAdapter
import com.shop.base.BaseFragment
import com.shop.databinding.SortDataItemBinding
import com.shop.viewmodel.sort.SortViewModel
import kotlinx.android.synthetic.main.sort_data_item.*

class SortCategoryFragment(var Iid:Int) : BaseFragment<SortViewModel, SortDataItemBinding>
    (R.layout.sort_data_item, SortViewModel::class.java){

    var list:MutableList<SortDataBean.SubCategory> = mutableListOf()
    var sortcateadapter: SortCategroysAdapter? = null

    override fun initView() {
        val layoutManager =  GridLayoutManager(activity,3)
        //设置布局管理器
        mDataBinding!!.sortDataMRlv.layoutManager = layoutManager
        //封装xml布局界面的id和界面中需要绑定的数据对应的id
        var sortDataArr = SparseArray<Int>()
        sortDataArr.put(R.layout.layout_data_item, BR.SubCategory)
        //设置适配器
        sortcateadapter = SortCategroysAdapter(context!!,list,sortDataArr,itemClick())
        //绑定适配器
        mDataBinding!!.sortDataMRlv.adapter = sortcateadapter

        //返回顶部
        sort_data_info_nsl.fullScroll(ScrollView.FOCUS_UP)

    }

    inner class itemClick: IItemClick<SortDataBean.SubCategory> {
        override fun itemClick(data: SortDataBean.SubCategory) {
            var id = data.id
            var name = data.name
            val intent = Intent(activity,SortDataInfoActivity::class.java)
            intent.putExtra("id",id)
            intent.putExtra("name",name)
            startActivity(intent)
        }
    }

    override fun initVM() {
        mViewModel!!.sortdata. observe(this, Observer {
            sortcateadapter!!.refreshData(it.subCategoryList)
            mDataBinding.setVariable(BR.SortData,it)
        })
    }

    override fun initData() {
        if (Iid != null) {
            mViewModel.getSortData(Iid)
        }
    }

    override fun initVariable() {

    }

}