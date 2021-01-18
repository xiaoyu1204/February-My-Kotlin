package com.shop.ui.main.fragment.sort

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import com.shop.R
import com.shop.base.BaseActivity
import com.shop.databinding.ActivitySortDataInfoBinding
import com.shop.viewmodel.main.sort.SortDataInfoViewModel
import kotlinx.android.synthetic.main.activity_sort_data_info.*
import java.util.*
import kotlin.collections.ArrayList

class SortDataInfoActivity : BaseActivity<SortDataInfoViewModel,ActivitySortDataInfoBinding>(
    R.layout.activity_sort_data_info,
    SortDataInfoViewModel::class.java
) {

    private lateinit var name:String
    var id:Int?= null

    override fun initView() {
        id = intent.getIntExtra("id", 0)
        name = intent.getStringExtra("name").toString()
    }

    override fun initVM() {
        mViewModel.getSortDataInfo(id!!)
    }

    override fun initData() {
        mViewModel.sortdatainfo.observe(this, Observer {
            if (it != null && it.size > 0) {

                val fragments = ArrayList<SortDataInfoFragment>()
                for (i in it.indices) {
                    var sortDataInfoFragment = SortDataInfoFragment(it.get(i).id, it.get(i).name, it.get(i).front_name)
                    fragments.add(sortDataInfoFragment)
                }

                //创建适配器
                sort_data_info_vp!!.adapter =
                    object : FragmentStatePagerAdapter(supportFragmentManager) {
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
                sort_data_info_tab!!.setupWithViewPager(sort_data_info_vp)

                //点那个跳那个
                for (i in it.indices) {
                    //如果获取的name == 集合的name
                    if (name == it[i].name) {
                        //被选中   select
                        sort_data_info_tab!!.getTabAt(i)!!.select()
                    }
                }

            }
        })
    }

    override fun initVariable() {

    }

}