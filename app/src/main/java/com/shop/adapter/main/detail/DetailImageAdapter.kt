package com.shop.adapter.main.detail

import android.content.Context
import android.util.Log
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.basemvvm.BR
import com.example.basemvvm.model.myitem.IItemClick
import com.example.basemvvm.utils.MyMmkv
import com.example.basemvvm.utils.SpUtils
import com.example.myshop.base.BaseAdapter
import com.shop.R
import kotlinx.android.synthetic.main.layout_detail_bigimage_item.view.*

class DetailImageAdapter(
    context: Context,
    list: List<String>,
    layouts: SparseArray<Int>,
    var click: IItemClick<String>
) : BaseAdapter<String>(context, list, layouts, click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_detail_bigimage_item
    }

    override fun bindData(binding: ViewDataBinding, data: String, layId: Int) {
        Glide.with(context).load(data).into(binding.root.iv_bigimage_img)
        binding.setVariable(BR.Detail_bigimageClick, click)
//        MyMmkv.setValue("detail_image",layId)
        SpUtils.instance!!.setValue("detail_image",layId)
    }

}