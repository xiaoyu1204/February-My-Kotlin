package com.shop.ui.main.fragment.topic

import com.shop.R
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentTopic2Binding
import com.shop.viewmodel.topic.TopicViewModel

class TopicFragment: BaseFragment<TopicViewModel, FragmentTopic2Binding>(R.layout.fragment_topic2,TopicViewModel::class.java){

    companion object{
        val instance by lazy { TopicFragment() }
    }

    override fun initView() {

    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {

    }

}