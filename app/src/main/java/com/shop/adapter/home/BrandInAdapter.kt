package com.shop.adapter.home

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.model.bean.main.home.DataX
import com.example.basemvvm.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.shop.BR
import com.shop.R

class BrandInAdapter (context: Context, list: List<DataX>, layouts: SparseArray<Int>, var click: IItemClick<DataX>): BaseAdapter<DataX>(context, list,layouts,click) {
    override fun layoutId(position: Int): Int {
        return R.layout.item_tv_brand
    }

    override fun bindData(binding: ViewDataBinding, data: DataX, layId: Int) {
        binding.setVariable(BR.BrandClick,click)
    }

    fun refreshData(lt : List<DataX>){
        list = lt
        notifyDataSetChanged()
    }

}