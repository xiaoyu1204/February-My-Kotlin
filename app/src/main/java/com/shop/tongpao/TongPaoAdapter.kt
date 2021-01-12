package com.shop.tongpao

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.basemvvm.model.bean.tongpao.FilePathList
import com.example.myshop.base.BaseAdapter
import com.shop.R

class TongPaoAdapter(context: Context, list: List<FilePathList>, layouts:SparseArray<Int>)
    : BaseAdapter<FilePathList>(context,list,layouts){

    override fun layoutId(position: Int): Int {
        var url = list.get(position).filePathList
        for (i in url.indices){
            val length = url.size
            if(length == 3){
                return R.layout.layout_tongpao_item2
            }else{
                return R.layout.layout_tongpao_item1
            }
        }
        return R.layout.layout_tongpao_item
    }

    override fun bindData(binding: ViewDataBinding, data: FilePathList) {
        var url = data.filePathList
        for (i in url.indices){
            val length = url.size
            if(length == 3){
                Glide.with(context).load(url.get(0).filePath).into(binding.root.findViewById(R.id.img11))
                Glide.with(context).load(url.get(1).filePath).into(binding.root.findViewById(R.id.img22))
                Glide.with(context).load(url.get(2).filePath).into(binding.root.findViewById(R.id.img33))
            }else if(length == 1){
                Glide.with(context).load(url.get(0).filePath).into(binding.root.findViewById(R.id.img12))
            }
        }
    }

    fun refreshData(lt : List<FilePathList>){
        list = lt
        notifyDataSetChanged()
    }

}