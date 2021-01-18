package com.shop.adapter.main.home

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.BR
import com.example.basemvvm.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.shop.R
import com.shop.model.bean.home.NewGoods2

class NewGoodAdapter (
    context: Context,
    list:List<NewGoods2>,
    layouts: SparseArray<Int>,
    var click: IItemClick<NewGoods2>
): BaseAdapter<NewGoods2>(context,list,layouts,click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_newgood_item2
    }

    override fun bindData(binding: ViewDataBinding, data: NewGoods2, layId: Int) {
        binding.setVariable(BR.Goodclick,click)
    }

}