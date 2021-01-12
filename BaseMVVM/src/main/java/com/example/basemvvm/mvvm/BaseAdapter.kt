package com.example.myshop.base

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * adapter基类
 * layouts 布局id与界面绑定name的id匹配使用
 */
open abstract class BaseAdapter<D>(val context: Context, var list:List<D>, val layouts:SparseArray<Int>):RecyclerView.Adapter<BaseAdapter<D>.BaseVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
        return BaseVH(DataBindingUtil.inflate(LayoutInflater.from(parent.context),viewType,parent,false))
    }

    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        var layoutId = getItemViewType(position)
        var type = layouts.get(layoutId)
        holder.dataBinding.setVariable(type,list.get(position))
        bindData(holder.dataBinding,list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return layoutId(position)
    }

    /**
     * 获取对应的布局
     */
    protected abstract fun layoutId(position: Int):Int

    protected abstract fun bindData(binding: ViewDataBinding,data:D)

    inner class BaseVH(val dataBinding:ViewDataBinding) :RecyclerView.ViewHolder(dataBinding.root){

    }
}