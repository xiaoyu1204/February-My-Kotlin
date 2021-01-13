package com.shop.tongpao

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.basemvvm.BR
import com.example.basemvvm.model.bean.tongpao.FilePathList
import com.example.basemvvm.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.shop.R

//todo 同袍多布局适配器
class TongPaoAdapter(context: Context, list: List<FilePathList>, layouts:SparseArray<Int>, var clicks: IItemClick<FilePathList>)
    : BaseAdapter<FilePathList>(context,list,layouts,clicks){

    override fun layoutId(position: Int): Int {
        //todo  多布局判断当前使用有图 无图 多张图
        var url = list.get(position).filePathList
        when (url.size) {
            0 -> return R.layout.layout_tongpao_item
            1 -> return R.layout.layout_tongpao_item1
            else -> return R.layout.layout_tongpao_item2
        }
    }

    //刷新条目的数据
    fun refreshData(lt : List<FilePathList>){
        list = lt
        notifyDataSetChanged()
    }

    override fun bindData(binding: ViewDataBinding, data: FilePathList, layId: Int) {
        when(layId){
            R.layout.layout_tongpao_item1 -> {
                binding.setVariable(BR.tongpao_click1,itemClick)
            }
            R.layout.layout_tongpao_item2 -> {
                binding.setVariable(BR.tongpao_click2,clicks)
            }
        }
//        var url = data.filePathList
//        for (i in url.indices){
//            val length = url.size
//            if(length == 3){
//                Glide.with(context).load(url.get(0).filePath).into(binding.root.findViewById(R.id.img11))
//                Glide.with(context).load(url.get(1).filePath).into(binding.root.findViewById(R.id.img22))
//                Glide.with(context).load(url.get(2).filePath).into(binding.root.findViewById(R.id.img33))
//            }else if(length == 1){
//                Glide.with(context).load(url.get(0).filePath).into(binding.root.findViewById(R.id.img12))
//            }
//        }
    }

}