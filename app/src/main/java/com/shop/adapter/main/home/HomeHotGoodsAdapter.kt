package com.shop.adapter.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basemvvm.mvvm.BaseViewHolder
import com.shop.BR
import com.shop.R
import com.shop.model.bean.home.HotGoods2
import kotlinx.android.synthetic.main.layout_hotgoods_item.view.*

//参数   继承RecyclerView.Adapter           名字 : 类型   类名后面加了一个? 意思就是这个类可以为空 即可以 = null
class HomeHotGoodsAdapter(var mContext:Context?,var hotgoodslist: List<HotGoods2> = listOf<HotGoods2>())
    :RecyclerView.Adapter<BaseViewHolder>(){

    //找布局
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,parent,false
            )
        )
    }

    override fun getItemCount(): Int {
        // ?: 左侧表达式非空，elvis操作符就返回其左侧表达式，否则返回右侧表达式
        //请注意，当且仅当左侧为空时，才会对右侧表达式求值。
        //对象A ?: 对象B 表达式，意思为，当对象 A值为 null 时，那么它就会返回后面的对象 B。

        // ?.意思是这个参数可以为空,并且程序继续运行下去
        return hotgoodslist?.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_hotgoods_item2
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        var binding : ViewDataBinding = holder.dataBinding
        binding.setVariable(BR.hotgoods2,hotgoodslist.get(position))

        //  !!. 的意思是这个参数如果为空,就抛出异常
        // !!加在变量名后，表示当前对象不为空的情况下执行 放在对象后面代表该对象如果为null则抛异常
        with(holder?.itemView!!){
            Glide.with(mContext!!).load(hotgoodslist!!.get(position)!!.list_pic_url).into(hotgoods_item_iv)
        }
    }

    fun refreshData(lt : List<HotGoods2>){
        hotgoodslist = lt
        notifyDataSetChanged()
    }

}