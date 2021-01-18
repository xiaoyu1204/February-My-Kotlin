package com.shop.adapter.main.home

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.BR
import com.example.basemvvm.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.shop.R
import com.shop.model.bean.home.Goods2

class CategoryAdapter(
    context: Context,
    list: List<Goods2>,
    layouts:SparseArray<Int>,
    var click:IItemClick<Goods2>
): BaseAdapter<Goods2>(context,list,layouts,click) {
    override fun layoutId(position: Int): Int {
        return R.layout.home2_item_tlv_item
    }

    override fun bindData(binding: ViewDataBinding, data: Goods2, layId: Int) {
        binding.setVariable(BR.homegoos2click,click)
    }

}