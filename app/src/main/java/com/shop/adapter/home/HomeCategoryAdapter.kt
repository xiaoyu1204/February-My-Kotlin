package com.shop.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shop.R
import com.shop.model.bean.home.Goods
import kotlinx.android.synthetic.main.layout_categoty_rlv_item.view.*

//参数   继承RecyclerView.Adapter           名字 : 类型   类名后面加了一个? 意思就是这个类可以为空 即可以 = null
class HomeCategoryAdapter(private val categorylist:List<Goods?>?,private val mContext:Context?)
    :RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder>(){

    //找布局
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeCategoryAdapter.ViewHolder {
        val inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_categoty_rlv_item, null)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        // ?: 左侧表达式非空，elvis操作符就返回其左侧表达式，否则返回右侧表达式
        //请注意，当且仅当左侧为空时，才会对右侧表达式求值。
        //对象A ?: 对象B 表达式，意思为，当对象 A值为 null 时，那么它就会返回后面的对象 B。

        // ?.意思是这个参数可以为空,并且程序继续运行下去
        return categorylist?.size?:0
    }

    override fun onBindViewHolder(holder: HomeCategoryAdapter.ViewHolder, position: Int) {
        //  !!. 的意思是这个参数如果为空,就抛出异常
        // !!加在变量名后，表示当前对象不为空的情况下执行 放在对象后面代表该对象如果为null则抛异常
        with(holder?.itemView!!){
            category_rlv_name!!.text = categorylist!!.get(position)!!.name
            category_rlv_price!!.text = categorylist!!.get(position)!!.retail_price
            Glide.with(mContext!!).load(categorylist!!.get(position)!!.list_pic_url).into(category_rlv_img)
        }
    }

    //定义ViewHolder    名字 : 类型
    class ViewHolder(item: View):RecyclerView.ViewHolder(item)

}