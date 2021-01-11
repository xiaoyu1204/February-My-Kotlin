package com.shop.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.shop.R
import com.shop.base.BaseActivity
import com.shop.databinding.ActivityMain2Binding
import com.shop.viewmodel.main.MainViewModel
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity:BaseActivity<MainViewModel, ActivityMain2Binding>(
    R.layout.activity_main2,
    MainViewModel::class.java) {

    var pagerAdapter:MyViewPagerAdapter? = null

    override fun initView() {
        pagerAdapter = MyViewPagerAdapter(supportFragmentManager, mViewModel.fragments)
        viewPager.adapter = pagerAdapter
        viewPager.addOnPageChangeListener(MyPagerChangeListener())
        nav_view.setOnNavigationItemSelectedListener{ item->  //默认 it
            when(item.itemId){
                R.id.navigation_home -> {
                    item.setIcon(R.mipmap.ic_menu_choice_pressed)
                    viewPager.currentItem = 0
                }
                R.id.navigation_topic -> {
                    item.setIcon(R.mipmap.ic_menu_topic_pressed)
                    viewPager.currentItem = 1
                }
                R.id.navigation_sort -> {
                    item.setIcon(R.mipmap.ic_menu_sort_pressed)
                    viewPager.currentItem = 2
                }
                R.id.navigation_shop -> {
                    item.setIcon(R.mipmap.ic_menu_shoping_pressed)
                    viewPager.currentItem = 3
                }
                R.id.navigation_me -> {
                    item.setIcon(R.mipmap.ic_menu_me_pressed)
                    viewPager.currentItem = 4
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {

    }

    /**
     * innner修饰的内部类访问外部类
     */
    inner class MyPagerChangeListener: ViewPager.OnPageChangeListener{
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        override fun onPageSelected(position: Int) {
            nav_view.getMenu().getItem(position).setChecked(true)
        }

        override fun onPageScrollStateChanged(state: Int) {
        }

    }

    /**
     * viewpager适配器
     */
    class MyViewPagerAdapter(val fm: FragmentManager, val list: List<Fragment>):
        FragmentPagerAdapter(
        fm
    ){
        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(position: Int): Fragment {
            return list.get(position)
        }

    }

}