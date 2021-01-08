package com.shop.ceshi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shop.R
import com.shop.ceshi.model.callback.IoncliItem
import kotlinx.android.synthetic.main.layout_kotlin_item.view.*

//参数   继承RecyclerView.Adapter
class KoListAdapter(private val list: List<String>,private val mContext : Context)
    : RecyclerView.Adapter<KoListAdapter.ViewHolder>() {

    //找布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KoListAdapter.ViewHolder {
        val inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_kotlin_item, null)
        return ViewHolder(inflate)
    }

    //集合长度
    override fun getItemCount(): Int {
        return list.size
    }

    //循环赋值
    override fun onBindViewHolder(holder: KoListAdapter.ViewHolder, position: Int) {
        with (holder?.itemView!!){
            txt_list?.text = list.get(position)
        }
        //设置接口
        holder.itemView.setOnClickListener {IoncliItem !!. Onclipos(position) }
    }

    //定义ViewHolder
    class ViewHolder(item : View) : RecyclerView.ViewHolder(item)

    //定义条目回调接口
    private var IoncliItem : IoncliItem? = null
    fun setOncli(
        IoncliItem: IoncliItem){
        this.IoncliItem = IoncliItem
    }

}