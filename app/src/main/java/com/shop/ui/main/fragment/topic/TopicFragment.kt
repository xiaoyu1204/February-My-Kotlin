package com.shop.ui.main.fragment.topic

import android.util.Log
import android.util.SparseArray
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvm.model.bean.main.topic.TopicData
import com.example.basemvvm.model.myitem.IItemClick
import com.example.myshop.model.bean.shop.sort.SortDataBean
import com.shop.BR
import com.shop.R
import com.shop.adapter.main.sort.SortCategroysAdapter
import com.shop.adapter.main.topic.TopicAdapter
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentTopicBinding
import com.shop.viewmodel.topic.TopicViewModel
import kotlinx.android.synthetic.main.fragment_topic.*
import kotlinx.android.synthetic.main.sort_data_item.*

class TopicFragment: BaseFragment<TopicViewModel, FragmentTopicBinding>(R.layout.fragment_topic,TopicViewModel::class.java),
    View.OnClickListener{

    var list:MutableList<TopicData.DataX> = mutableListOf()
    var topicadapter: TopicAdapter? = null
    var page:Int? = null
    val ONE:Int = 1
    val TWO:Int = 2

    //采用伴生对象 companion object==static
    companion object{
        val instance by lazy { TopicFragment() }
    }

    override fun initView() {
        val layoutManager =  LinearLayoutManager(activity)
        //设置布局管理器
        mDataBinding!!.topicRlv.layoutManager = layoutManager
        //封装xml布局界面的id和界面中需要绑定的数据对应的id
        var TopicaArr = SparseArray<Int>()
        TopicaArr.put(R.layout.layout_topic_item2, BR.topicitem2)
        //设置适配器
        topicadapter = TopicAdapter(context!!,list,TopicaArr,itemClick())
        //绑定适配器
        mDataBinding!!.topicRlv.adapter = topicadapter

        initClick()

    }

    private fun initClick() {
        mDataBinding!!.topicBtnShang.setOnClickListener(this)
        mDataBinding!!.topicBtnXia.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
       when(v?.id){
           R.id.topic_btn_shang->{
                //更换page页
               page = ONE
               //显示加载中...      白版
               lfr_topic_loading!!.visibility = View.VISIBLE
               fr_topic_all!!.visibility = View.VISIBLE
               //请求数据
               mViewModel.getTopic(page!!)
               // 返回顶部
               fr_topic_sc!!.fullScroll(ScrollView.FOCUS_UP)
           }
           R.id.topic_btn_xia->{
               //更换page页
               page = TWO
               //显示加载中...  白板
               lfr_topic_loading!!.visibility = View.VISIBLE
               fr_topic_all!!.visibility = View.VISIBLE
               //请求数据
               mViewModel.getTopic(page!!)
               // 返回顶部
               fr_topic_sc!!.fullScroll(ScrollView.FOCUS_UP)
           }
        }
    }

    inner class itemClick: IItemClick<TopicData.DataX> {
        override fun itemClick(data: TopicData.DataX) {
            Log.e("TAG::TopicDatax",data.title)
        }
    }

    override fun initVM() {
        mViewModel!!.datax.observe(this, Observer {
            topicadapter!!.refreshData(it)
            //隐藏加载中...
            lfr_topic_loading!!.visibility = View.GONE
            fr_topic_all!!.visibility = View.GONE
        })
    }

    override fun initData() {
        page = ONE
        mViewModel.getTopic(page!!)
    }

    override fun initVariable() {

    }



}