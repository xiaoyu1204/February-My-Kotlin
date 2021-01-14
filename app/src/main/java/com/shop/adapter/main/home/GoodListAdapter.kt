package com.shop.adapter.main.home

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.BR
import com.example.basemvvm.model.bean.main.home.GoodListData
import com.example.basemvvm.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.shop.R

class GoodListAdapter(
    context: Context,
    list:List<GoodListData.Goods>,
    layouts: SparseArray<Int>,
    var click: IItemClick<GoodListData.Goods>
): BaseAdapter<GoodListData.Goods>(context,list,layouts,click){

    override fun layoutId(position: Int): Int {
        return R.layout.layout_goodlist_item2
    }

    override fun bindData(binding: ViewDataBinding, data: GoodListData.Goods, layId: Int) {
        binding.setVariable(BR.goodClick,click)
    }

    fun refreshData(lt : List<GoodListData.Goods>){
        list = lt
        notifyDataSetChanged()
    }

}