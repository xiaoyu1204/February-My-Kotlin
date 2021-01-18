package com.shop.adapter.main.detail

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.BR
import com.example.basemvvm.model.bean.main.Issue
import com.example.basemvvm.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.shop.R

//常见问题
class DetailIssueAdapter(
    context: Context,
    list:List<Issue>,
    layouts:SparseArray<Int>,
    var click:IItemClick<Issue>
):BaseAdapter<Issue>(context,list,layouts,click){
    override fun layoutId(position: Int): Int {
        return R.layout.detail_issue_item
    }

    override fun bindData(binding: ViewDataBinding, data: Issue, layId: Int) {
        binding.setVariable(BR.attibuteclick,click)
    }
}