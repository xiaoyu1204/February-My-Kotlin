package com.shop.ui.sort

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import com.shop.R
import com.shop.adapter.main.sort.SortMarqueeAdapter
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentSortBinding
import com.shop.ui.main.fragment.sort.SortCategoryFragment
import com.shop.viewmodel.sort.SortViewModel
import kotlinx.android.synthetic.main.fragment_sort.*
import kotlinx.android.synthetic.main.home_toolbar.view.*

class SortFragment:BaseFragment<SortViewModel,FragmentSortBinding>(R.layout.fragment_sort,SortViewModel::class.java) {

    //采用伴生对象 companion object==static
    companion object{
        val instance by lazy { SortFragment() }
    }

    override fun initView() {
        //禁止滑动
        sort_vp.setScanScroll(false)
    }

    override fun initVM() {
        val fragments = ArrayList<SortCategoryFragment>()
        if(!isAdded)return
        mViewModel.sortnav.observe(this, Observer {
            if (it != null && it.size > 0) {

                val fragments = ArrayList<SortCategoryFragment>()
                for (i in it.indices) {
                    var f = SortCategoryFragment(it.get(i).id)
                    fragments.add(f)
                }

                //创建适配器
                sort_vp!!.adapter = object : FragmentStatePagerAdapter(childFragmentManager) {
                    override fun getCount(): Int {
                        return fragments.size
                    }

                    override fun getItem(position: Int): Fragment {
                        return fragments[position]
                    }

                    override fun getPageTitle(position: Int): CharSequence? {
                        return it[position].name
                    }
                }
                sort_tab!!.setupWithViewPager(sort_vp)

            }
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
        mDataBinding.homeToolbar.marquee_item.setAdapter(sortMarqueeAdapter)

    }

}