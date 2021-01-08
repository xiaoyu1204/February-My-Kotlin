package com.shop.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shop.R
import com.shop.model.bean.home.HotGoods
import kotlinx.android.synthetic.main.layout_hotgoods_item.view.*
import java.nio.channels.Pipe

//参数   继承RecyclerView.Adapter           名字 : 类型   类名后面加了一个? 意思就是这个类可以为空 即可以 = null
class HomeHotGoodsAdapter(private val hotgoodslist:List<HotGoods?>?,private val mContext:Context?)
    :RecyclerView.Adapter<HomeHotGoodsAdapter.ViewHolder>(){

    //找布局
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): HomeHotGoodsAdapter.ViewHolder {
        val inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_hotgoods_item, null)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        // ?: 左侧表达式非空，elvis操作符就返回其左侧表达式，否则返回右侧表达式
        //请注意，当且仅当左侧为空时，才会对右侧表达式求值。
        //对象A ?: 对象B 表达式，意思为，当对象 A值为 null 时，那么它就会返回后面的对象 B。

        // ?.意思是这个参数可以为空,并且程序继续运行下去
        return hotgoodslist?.size?:0
    }

    override fun onBindViewHolder(holder: HomeHotGoodsAdapter.ViewHolder, position: Int) {
        //  !!. 的意思是这个参数如果为空,就抛出异常
        // !!加在变量名后，表示当前对象不为空的情况下执行 放在对象后面代表该对象如果为null则抛异常
        with(holder?.itemView!!){
            hotgoods_item_tv_name!!.text = hotgoodslist!!.get(position)!!.name
            hotgoods_item_tv_brif!!.text = hotgoodslist!!.get(position)!!.goods_brief
            hotgoods_item_tv_price!!.text = "￥"+hotgoodslist!!.get(position)!!.retail_price
            Glide.with(mContext!!).load(hotgoodslist!!.get(position)!!.list_pic_url).into(hotgoods_item_iv)
        }
    }

    //定义ViewHolder    名字 : 类型
    class ViewHolder(item: View):RecyclerView.ViewHolder(item)


}