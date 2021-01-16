package com.shop.ui.main.fragment.home

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.util.SparseArray
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.example.basemvvm.model.bean.main.home.GoodListData
import com.example.basemvvm.model.myitem.IItemClick
import com.kekstudio.dachshundtablayout.HelperUtils
import com.shop.BR
import com.shop.R
import com.shop.adapter.main.home.GoodListAdapter
import com.shop.app.Constants
import com.shop.base.BaseActivity
import com.shop.databinding.ActivityGoodListDetailBinding
import com.shop.databinding.ActivityTongPaoBinding
import com.shop.viewmodel.main.home.GoodListViewModel

class GoodListDetailActivity(
    var lid: Int = R.layout.activity_good_list_detail
): BaseActivity<GoodListViewModel, ActivityGoodListDetailBinding>(lid, GoodListViewModel::class.java),
    View.OnClickListener, Parcelable {

    var list:MutableList<GoodListData.Goods> = mutableListOf()
    lateinit var goodlistAdapter: GoodListAdapter

    //接口参数
    val ASC = "asc"
    val DESC = "desc"
    val DEFAULT = "default"
    val PRICE = "price"
    val CATEGORY = "category"

    //是否是新品
    var isNew = 1
    var page = 1
    var size = 100
    var order: String? = null
    var sort: String? = null
    var categoryId = 0

    constructor(parcel: Parcel) : this(parcel.readInt()) {
        isNew = parcel.readInt()
        page = parcel.readInt()
        size = parcel.readInt()
        order = parcel.readString()
        sort = parcel.readString()
        categoryId = parcel.readInt()
    }

    override fun initView() {
        mDataBinding.mRlvNewGoodList.layoutManager = GridLayoutManager(this,2)
        var goodListArr = SparseArray<Int>()
        goodListArr.put(R.layout.layout_goodlist_item2, BR.gooditem);
        goodlistAdapter = GoodListAdapter(this, list, goodListArr, itemClick())
        mDataBinding.mRlvNewGoodList.adapter = goodlistAdapter

        order = ASC
        sort = DEFAULT
        categoryId = 0
        mDataBinding.layoutPrice.setTag(0)

        mDataBinding.tvNewgoodsListAll.setTextColor(Color.parseColor(getString(R.color.red)))
        mDataBinding.setVariable(R.layout.activity_good_list_detail,BR.goodList)

        initClick()

    }

    private fun initClick() {
        mDataBinding!!.layoutPrice.setOnClickListener(this)
        mDataBinding!!.tvNewgoodsListAll.setOnClickListener(this)
        mDataBinding!!.tvNewgoodsListSort.setOnClickListener(this)
    }

    override fun initVM() {

        mViewModel.goodListData.observe(this, Observer {
            updateGoodList(it.goodsList)
            goodlistAdapter!!.refreshData(it.goodsList)
            if (sort === CATEGORY) {
                sort = DEFAULT
                getCategory(it.filterCategory) //获取类别
            }
        })

        mViewModel.goodTopData.observe(this, Observer {
            mDataBinding.setVariable(BR.goodList,it)
        })
    }

    /**
     * 商品类别
     */
    private fun getCategory(filter: List<GoodListData.FilterCategory>) {
        val view = View.inflate(this, R.layout.popup_hot_good, null)
        val goodLl = view.findViewById<LinearLayout>(R.id.good_ll)
        val window = PopupWindow(view, -1, -2)

        if (filter.isNotEmpty()) {
            for (i in filter.indices) {
                val textView = buildLabel(filter[i].name)
                goodLl.addView(textView)
                textView!!.tag = i
                val finalI: Int = i
                textView.setOnClickListener { //获取 类别 id 加载数据
                    val stringHashMap =
                        HashMap<String, String>()
                    stringHashMap["isNew"] = 1.toString()
                    stringHashMap["categoryId"] =
                        java.lang.String.valueOf(filter[finalI].id)
                    getParam()?.let { mViewModel.getGoodList(stringHashMap) }
                    window.dismiss()
                }
            }
            window.setBackgroundDrawable(null)
            window.isOutsideTouchable = true
            window.showAsDropDown(mDataBinding.tvNewgoodsListSort, 0, 0)
        }
    }

    fun updateGoodList(list: List<GoodListData.Goods>){
        this.list.clear()
        this.list.addAll(list)
        goodlistAdapter.notifyDataSetChanged()
    }

    override fun initData() {
        //商品列表数据
        var map = HashMap<String, String>()
        getParam()?.let { mViewModel.getGoodList(map) }
        //商品详情上面数据
        mViewModel.getGoodTop()
    }

    override fun initVariable() {

    }

    inner class itemClick: IItemClick<GoodListData.Goods> {
        override fun itemClick(data: GoodListData.Goods) {
            Log.e("TAG",data.name)
        }
    }

    /**
     * 组装当前的接口参数
     * @return
     */
    private fun getParam(): HashMap<String, String>? {
        val map = HashMap<String, String>()
        map["isNew"] = isNew.toString()
        map["page"] = page.toString()
        map["size"] = size.toString()
        map["order"] = order!!
        map["sort"] = sort!!
        map["category"] = categoryId.toString()
        return map
    }

    /**
     * 界面的点击事件
     */
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.layout_price -> {
                val tag = mDataBinding.layoutPrice.getTag() as Int
                if (tag == 0) {
                    resetPriceState()
                    priceStateUp()
                    mDataBinding.layoutPrice.setTag(1)
                    order = ASC
                } else if (tag == 1) {
                    resetPriceState()
                    priceStateDown()
                    mDataBinding.layoutPrice.setTag(0)
                    order = DESC
                }
                sort = PRICE
                getParam()?.let { mViewModel.getGoodList(it) }
            }
            R.id.tv_newgoods_list_all -> {
                resetPriceState()
                mDataBinding.tvNewgoodsListAll.setTextColor(Color.parseColor("#ff0000"))
                sort = DEFAULT
                categoryId = 0
                getParam()?.let { mViewModel.getGoodList(it) }
            }
            R.id.tv_newgoods_list_sort -> {
                resetPriceState()
                mDataBinding.tvNewgoodsListSort.setTextColor(Color.parseColor("#ff0000"))
                sort = CATEGORY
                getParam()?.let { mViewModel.getGoodList(it) }
            }
        }
    }

    /**
     * 按价格升序排序
     */
    @SuppressLint("ResourceType")
    private fun priceStateUp() {
        mDataBinding.imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_select)
        mDataBinding.imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal)
        mDataBinding.tvNewgoodsListPrice.setTextColor(Color.parseColor(getString(R.color.red)))
    }

    /**
     * 价格的降序排列
     */
    @SuppressLint("ResourceType")
    private fun priceStateDown() {
        mDataBinding.imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal)
        mDataBinding.imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_select)
        mDataBinding.tvNewgoodsListPrice.setTextColor(Color.parseColor(getString(R.color.red)))
    }

    /**
     * 重置条件选择的所有状态
     */
    @SuppressLint("ResourceType")
    private fun resetPriceState() {
        mDataBinding.imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal)
        mDataBinding.imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal)
        mDataBinding.tvNewgoodsListPrice.setTextColor(Color.parseColor(getString(R.color.black)))
        mDataBinding.tvNewgoodsListAll.setTextColor(Color.parseColor(getString(R.color.black)))
        mDataBinding.tvNewgoodsListSort.setTextColor(Color.parseColor(getString(R.color.black)))
        mDataBinding.layoutPrice.setTag(0)
    }

    /**
     * 流布局
     */
    private fun buildLabel(text: String): TextView? {
        val textView = TextView(this)
        textView.text = text
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        textView.setPadding(
            HelperUtils.dpToPx(20),
            HelperUtils.dpToPx(10),
            HelperUtils.dpToPx(20),
            HelperUtils.dpToPx(10)
        )
        textView.marginBottom.compareTo(6)
        textView.marginTop.compareTo(6)
        textView.marginLeft.compareTo(10)
        textView.marginRight.compareTo(10)

        textView.gravity = Gravity.CENTER
        textView.setBackgroundResource(R.drawable.shape_home_newgoods_filter_item_banckground_black)
        return textView
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(lid)
        parcel.writeInt(isNew)
        parcel.writeInt(page)
        parcel.writeInt(size)
        parcel.writeString(order)
        parcel.writeString(sort)
        parcel.writeInt(categoryId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GoodListDetailActivity> {
        override fun createFromParcel(parcel: Parcel): GoodListDetailActivity {
            return GoodListDetailActivity(parcel)
        }

        override fun newArray(size: Int): Array<GoodListDetailActivity?> {
            return arrayOfNulls(size)
        }
    }


}