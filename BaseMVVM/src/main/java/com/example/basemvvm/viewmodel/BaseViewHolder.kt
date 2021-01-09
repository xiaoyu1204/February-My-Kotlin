package com.example.basemvvm.viewmodel

import android.os.Binder
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(var dataBinding: ViewDataBinding):RecyclerView.ViewHolder(dataBinding.root){


}