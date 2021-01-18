package com.shop.ui.main.fragment.sort

import android.content.Intent
import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.basemvvm.model.bean.main.sort.DataX
import com.example.basemvvm.model.myitem.IItemClick
import com.shop.BR
import com.shop.R
import com.shop.adapter.main.sort.SortDataInfoAdapter
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentSortBinding
import com.shop.databinding.FragmentSortdataInfoBinding
import com.shop.ui.main.fragment.detail.DetailInfoActivity
import com.shop.viewmodel.main.sort.SortDataInfoBottomViewModel
import kotlinx.android.synthetic.main.fragment_sortdata_info.*

class SortDataInfoFragment(var Iid:Int,var Iname:String,var Ifront_name:String):
    BaseFragment<SortDataInfoBottomViewModel,FragmentSortdataInfoBinding>(
        R.layout.fragment_sortdata_info,
        SortDataInfoBottomViewModel::class.java
    ) {

    override fun initView() {
        sort_item_name!!.text = Iname
        sort_item_front_name!!.text = Ifront_name

        sort_item_rlv.layoutManager = GridLayoutManager(context,2)

    }

    override fun initVM() {
        mViewModel.getSortInfoItem(Iid)
    }

    override fun initData() {
        mViewModel.sortdatainfobottom.observe(this, Observer {
            if(it != null && it.size >0 ){
                var sortArr = SparseArray<Int>()
                sortArr.put(R.layout.layout_sort_data_item,BR.sortdataitem)
                var sortdatainfoclick = SortDataInfoClik()
                sort_item_rlv!!.adapter = SortDataInfoAdapter(context!!,it,sortArr,sortdatainfoclick)
            }
        })
    }

    inner class SortDataInfoClik:IItemClick<DataX>{
        override fun itemClick(data: DataX) {
            var id = data.id
            var intent = Intent(activity, DetailInfoActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }

    }

    override fun initVariable() {

    }


}