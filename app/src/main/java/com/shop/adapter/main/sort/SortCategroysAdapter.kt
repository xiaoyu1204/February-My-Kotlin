package com.shop.adapter.main.sort

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.BR
import com.example.basemvvm.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.example.myshop.model.bean.shop.sort.SortDataBean
import com.shop.R

class SortCategroysAdapter(
    context: Context,
    list:List<SortDataBean.SubCategory>,
    layouts: SparseArray<Int>,
    var click: IItemClick<SortDataBean.SubCategory>)
    : BaseAdapter<SortDataBean.SubCategory>(context,list,layouts,click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_data_item
    }

    override fun bindData(binding: ViewDataBinding, data: SortDataBean.SubCategory, layId: Int) {
        binding.setVariable(BR.SubClick,click)
    }

    //刷新适配器
    fun refreshData(lt : List<SortDataBean.SubCategory>){
        list = lt
        notifyDataSetChanged()
    }

}