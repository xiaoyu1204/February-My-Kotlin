package com.shop.adapter.main.topic

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.BR
import com.example.basemvvm.model.bean.main.topic.TopicData
import com.example.basemvvm.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.example.myshop.model.bean.shop.sort.SortDataBean
import com.shop.R

class TopicAdapter (
    context: Context,
    list:List<TopicData.DataX>,
    layouts: SparseArray<Int>,
    var click: IItemClick<TopicData.DataX>
)
    : BaseAdapter<TopicData.DataX>(context,list,layouts,click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_topic_item2
    }

    override fun bindData(binding: ViewDataBinding, data: TopicData.DataX, layId: Int) {
        binding.setVariable(BR.topicitemClick,click)
    }

    //刷新适配器
    fun refreshData(lt : List<TopicData.DataX>){
        list = lt
        notifyDataSetChanged()
    }

}