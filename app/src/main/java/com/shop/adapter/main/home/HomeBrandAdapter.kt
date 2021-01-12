package com.shop.adapter.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shop.R
import com.example.basemvvm.model.myitem.MyItemClick
import com.example.basemvvm.mvvm.BaseViewHolder
import com.shop.BR
import com.shop.model.bean.home.Brand2
import kotlinx.android.synthetic.main.layout_brand_item.view.*

//参数   继承RecyclerView.Adapter
                                    //类名后面加了一个? 意思就是这个类可以为空 即可以 = null
class HomeBrandAdapter(var mContext:Context?,var brandlist:List<Brand2> = listOf<Brand2>())
    :RecyclerView.Adapter<BaseViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        )
    }

    override fun getItemCount(): Int {
        // ?: 左侧表达式非空，elvis操作符就返回其左侧表达式，否则返回右侧表达式
        //请注意，当且仅当左侧为空时，才会对右侧表达式求值。
        //对象A ?: 对象B 表达式，意思为，当对象 A值为 null 时，那么它就会返回后面的对象 B。

        // ?.意思是这个参数可以为空,并且程序继续运行下去
        return brandlist?.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        var binding: ViewDataBinding = holder.dataBinding
        //把数据绑定到item的xml界面
        binding.setVariable(BR.vmBrand,brandlist.get(position))

        //  !!. 的意思是这个参数如果为空,就抛出异常
        // !!加在变量名后，表示当前对象不为空的情况下执行 放在对象后面代表该对象如果为null则抛异常
        with(holder?.itemView!!){
            Glide.with(mContext!!).load(brandlist!!.get(position)!!.new_pic_url).into(brand_item_iv)
        }

        //设置接口
        holder.itemView.setOnClickListener{
            myItemClick!!.onItemCilck(position)
        }

    }

/*    class ViewHolder(var rootView: View) : RecyclerView.ViewHolder(rootView) {
        var brand_item_tv_name: TextView
        var brand_item_tv_price: TextView

        init {
            brand_item_tv_name = rootView.findViewById<View>(R.id.brand_item_tv_name) as TextView
            brand_item_tv_price = rootView.findViewById<View>(R.id.brand_item_tv_price) as TextView
        }
    }*/

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_brand_item2
    }

    //定义条目回调接口
    private var myItemClick: MyItemClick? = null
    //set方法
    fun setOnItemClick(myItemClick: MyItemClick){
        this.myItemClick = myItemClick
    }

    /**
     * 加载完数据刷新到界面的data
     */
    fun refreshData(lt:List<Brand2>){
        brandlist = lt
        notifyDataSetChanged()
    }

}