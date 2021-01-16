package com.shop.adapter.main.sort

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.BR
import com.example.basemvvm.model.bean.main.sort.DataX
import com.example.basemvvm.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.shop.R

class SortDataInfoAdapter(
    context: Context,
    list:List<DataX>,
    layouts:SparseArray<Int>,
    var click:IItemClick<DataX>
): BaseAdapter<DataX>(context,list,layouts,click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_sort_data_item
    }

    override fun bindData(binding: ViewDataBinding, data: DataX, layId: Int) {
        binding.setVariable(BR.sortdataitemclick,click)
    }

}