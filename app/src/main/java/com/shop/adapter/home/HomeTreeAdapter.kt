package com.shop.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shop.R
import com.shop.model.bean.home.ChannelTreeDataX
import kotlinx.android.synthetic.main.layout_channeltreedatax_item.view.*

class HomeTreeAdapter(private val datalist: List<ChannelTreeDataX>,private val mContext:Context?)
    :RecyclerView.Adapter<HomeTreeAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTreeAdapter.ViewHolder {
        val inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_channeltreedatax_item, null)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: HomeTreeAdapter.ViewHolder, position: Int) {
        with(holder?.itemView!!){
            tv_home_tree_name!!.text = datalist!!.get(position)!!.name
            tv_home_tree_price!!.text = datalist!!.get(position)!!.retail_price+"元起"
            Glide.with(mContext!!).load(datalist!!.get(position)!!.list_pic_url).into(iv_home_tree_img)
        }
    }

    override fun getItemCount(): Int {
        return datalist?.size?:0
    }

    //定义ViewHolder    名字 : 类型
    class ViewHolder(item:View):RecyclerView.ViewHolder(item)


}