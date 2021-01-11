package com.shop.ui.primary.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.shop.R
import com.shop.adapter.home.HomeTreeAdapter
import com.shop.model.bean.home.ChannelTreeDataX
import com.example.basemvvm.utils.SpUtils
import com.shop.BR
import com.shop.databinding.FragmentHomeChannelTreeBinding
import com.shop.viewmodel.home.HomeChannelTreeViewModel
import com.shop.viewmodel.home.HomeChannelTypeViewModel
import kotlinx.android.synthetic.main.fragment_home_channel_tree.*

class HomeChannelTreeFragment : Fragment() {

    var mBinding: FragmentHomeChannelTreeBinding? = null
    var vm: HomeChannelTypeViewModel?= null
    lateinit var homeVM: HomeChannelTreeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val id = arguments!!.getInt("id", id)
        SpUtils.instance!!.setValue("id",id)

        mBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_home_channel_tree,container,false);

        var view = inflater.inflate(R.layout.fragment_home_channel_tree,container,false)

        mBinding = DataBindingUtil.inflate<FragmentHomeChannelTreeBinding>(inflater,R.layout.fragment_home_channel_tree,container,false)

        initVm()
        homeVM.loadChanneltreeData()

        return view

    }

    fun initVm(){

        //初始化ViewModel
        homeVM = ViewModelProvider(this).get(HomeChannelTreeViewModel::class.java)
        //监听网络状态的变化
        homeVM.loadStatue.observe(this, Observer { status ->
            if (status == -1) {
                Log.e("TAG", "HomeChannelTreeFragment: "+"数据加载失败" )
            }
        })

        //监听底部rlv数据的变化
        homeVM.dataX.observe(this, Observer {
            //网格布局
            home_tree_rlv!!.layoutManager = GridLayoutManager(activity, 2)
            //分割线
            home_tree_rlv!!.addItemDecoration(
                DividerItemDecoration(
                    activity,
                    LinearLayout.VERTICAL
                )
            )
            //val 不可变参数
            val data = arrayListOf<ChannelTreeDataX>()
            //添加值
            data.addAll(it)
            //设置适配器
            val homeTreeAdapter = HomeTreeAdapter(data, activity)
            //绑定适配器
            home_tree_rlv!!.adapter = homeTreeAdapter
        })

        vm = ViewModelProvider(this).get(HomeChannelTypeViewModel::class.java)
        vm!!.loadChanneltypeData()
        vm!!.loadStatue.observe(this, Observer {
            mBinding !!.setVariable(BR.channeltree,vm)

        })

    }

}