package com.shop.ui.home.fragment.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shop.R
import com.shop.utils.SpUtils
import com.shop.viewmodel.home.HomeChannelTypeViewModel
import kotlinx.android.synthetic.main.activity_home_channel_type.*

class HomeChannelTypeActivity : AppCompatActivity() {

    lateinit var homeVM: HomeChannelTypeViewModel
    var name:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_channel_type)
        initVM()
        showCategory()
        homeVM.loadChanneltypeData()

    }

    //显示数据
    private fun showCategory(){
        //禁止滑动
        home_type_vp_no!!.setScanScroll(false)

        //获取的name
        name = intent.getStringExtra("name")

    }

    fun initVM(){

        //初始化ViewModel
        homeVM = ViewModelProvider(this).get(HomeChannelTypeViewModel::class.java)
        //监听网络状态的变化
        homeVM.loadStatue.observe(this, Observer { stauts ->
            if (stauts == -1) {
                Log.e("TAG", "HomeChannelTypeActivity: "+"数据加载失败" )
            }
        })

        //监听数据的变化
        homeVM.category.observe(this, Observer { category ->
            val fs = ArrayList<HomeChannelTreeFragment>()
            for (i in category.indices) {
                val id = category[i].id
                val name1 = category[i].name
                val frontName = category[i].front_name
                var f = HomeChannelTreeFragment()
                SpUtils.instance!!.setValue("id",id)
                val bundle = Bundle()
                bundle.putString("name", name1)
                bundle.putString("front_name", frontName)
                f.arguments = bundle
                fs.add(f)

            }

            //创建适配器
            home_type_vp_no!!.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
                override fun getCount(): Int {
                    return fs.size
                }

                override fun getItem(position: Int): Fragment {
                    return fs[position]
                }

                override fun getPageTitle(position: Int): CharSequence? {
                    return category[position].name
                }
            }
            home_type_tab!!.setupWithViewPager(home_type_vp_no)

            for (i in category.indices){
                //如果获取的name == 集合的name
                if (name == category[i].name) {
                    //被选中   select
                    home_type_tab!!.getTabAt(i)!!.select()
                }
            }

        })
    }

}