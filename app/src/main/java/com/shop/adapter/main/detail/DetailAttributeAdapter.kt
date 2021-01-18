package com.shop.adapter.main.detail

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.BR
import com.example.basemvvm.model.bean.main.Attribute
import com.example.basemvvm.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.shop.R

//商品参数
class DetailAttributeAdapter(
    context: Context,
    list:List<Attribute>,
    layouts:SparseArray<Int>,
    var click:IItemClick<Attribute>
): BaseAdapter<Attribute>(context,list,layouts,click) {
    override fun layoutId(position: Int): Int {
        return R.layout.detail_attibute_item
    }

    override fun bindData(binding: ViewDataBinding, data: Attribute, layId: Int) {
        binding.setVariable(BR.attibuteclick,click)
    }

}