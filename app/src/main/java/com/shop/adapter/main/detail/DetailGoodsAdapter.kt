package com.shop.adapter.main.detail

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.BR
import com.example.basemvvm.model.bean.main.Goods
import com.example.basemvvm.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.shop.R

class DetailGoodsAdapter(
    context: Context,
    list:List<Goods>,
    layouts:SparseArray<Int>,
    var click:IItemClick<Goods>
): BaseAdapter<Goods>(context,list,layouts,click) {
    override fun layoutId(position: Int): Int {
        return R.layout.detail_goods_item
    }

    override fun bindData(binding: ViewDataBinding, data: Goods, layId: Int) {
        binding.setVariable(BR.detailgoodsclick,click)
    }
}