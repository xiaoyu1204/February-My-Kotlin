package com.shop.ui.sort

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.myshop.model.bean.shop.sort.Category
import com.shop.R
import com.shop.adapter.main.sort.SortMarqueeAdapter
import com.shop.adapter.main.sort.SortNavAdapter
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentSortBinding
import com.shop.ui.main.fragment.sort.SortCategoryFragment
import com.shop.viewmodel.sort.SortViewModel
import kotlinx.android.synthetic.main.fragment_sort.*

class SortFragment:BaseFragment<SortViewModel,FragmentSortBinding>(R.layout.fragment_sort,SortViewModel::class.java) {

    //采用伴生对象 companion object==static
    companion object{
        val instance by lazy { SortFragment() }
    }

    override fun initView() {
        //禁止滑动
        mVp_type.setScanScroll(false)
    }

    override fun initVM() {
        val fragments = ArrayList<SortCategoryFragment>()
        if(!isAdded)return
        mViewModel.sortnav.observe(this, Observer {categroy ->
            for (i  in categroy.indices){
                var id = categroy.get(i).id
                var categoryFragment = SortCategoryFragment()
                var bundle = Bundle()
                bundle.putInt("id",id)
                categoryFragment.arguments = bundle
                fragments.add(categoryFragment)
            }
            var sortNavAdapter = SortNavAdapter(childFragmentManager)
            mVp_type.adapter = sortNavAdapter
            sortNavAdapter.addList(fragments, categroy as ArrayList<Category>)
            sort_tab.setupWithViewPager(mVp_type)
        })
        //跑马灯
        initMarque()
    }

    override fun initData() {
        mViewModel.getSortNav()
    }

    override fun initVariable() {
    }

    //跑马灯
    private fun initMarque(){
        val marqueeViewListOf = mutableListOf<String>()
        marqueeViewListOf.add("商品搜索，共239款好物")
        marqueeViewListOf.add("夏日炎炎")
        marqueeViewListOf.add("第一波福利还有30秒到达战场")
        marqueeViewListOf.add("新用户立领1000元优惠卷")

        var sortMarqueeAdapter = SortMarqueeAdapter(context!!, marqueeViewListOf)
        mDataBinding.sortMarqueevItem.setAdapter(sortMarqueeAdapter)

    }

}