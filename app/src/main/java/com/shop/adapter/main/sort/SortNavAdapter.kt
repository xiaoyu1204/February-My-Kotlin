package com.shop.adapter.main.sort

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myshop.model.bean.shop.sort.Category
import com.shop.ui.main.fragment.sort.SortCategoryFragment

class SortNavAdapter(fm:FragmentManager):FragmentStatePagerAdapter(fm){

    private var arrayList = ArrayList<SortCategoryFragment>()
    private var category = ArrayList<Category>()

    fun addList(arrayList:ArrayList<SortCategoryFragment>,category:ArrayList<Category>){
        this.arrayList = arrayList
        this.category = category
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Fragment {
        return arrayList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return category[position]?.name
    }

}