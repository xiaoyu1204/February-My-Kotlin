package com.shop.ui.main.fragment.car

import android.util.Log
import android.util.SparseArray
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvm.BR
import com.example.basemvvm.model.bean.main.car.CarData
import com.example.basemvvm.model.myitem.IItemClick
import com.shop.R
import com.shop.adapter.main.car.CarAdapter
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentShopBinding
import com.shop.viewmodel.car.CarViewModel
import kotlinx.android.synthetic.main.fragment_shop.*

class CarFragment:BaseFragment<CarViewModel, FragmentShopBinding>(R.layout.fragment_shop,CarViewModel::class.java),
    View.OnClickListener{

    //采用伴生对象 companion object==static 保证只加载一次
    companion object{
        val instance by lazy { CarFragment() }
    }

    lateinit var editValue:String

    //购物车列表数据
    lateinit var list:MutableList<CarData.Cart>
    lateinit var carAdapter: CarAdapter
    //是否是编辑状态
    private var isEdit:Boolean = true

    //懒加载
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            list!!.clear()
            initData()
        }
    }

    override fun initView() {
//        initClick()
        list = mutableListOf()
        //设置布局管理器
        mRlv_Shopping_Car!!.layoutManager = LinearLayoutManager(context)
        //适配器
        var carArr = SparseArray<Int>()
        carArr.put(R.layout.layout_car_item,BR.caritem)
        carAdapter = CarAdapter(context!!,list,carArr,CarClikcItem())
        mRlv_Shopping_Car!!.adapter = carAdapter

        mDataBinding.setVariable(BR.Shopping_CartClick, ClickEvt())
        carAdapter!!.addChangeEvt(ItemChangeSelect())

    }

    private fun initClick() {
        mDataBinding.cbShoppingCarAll.setOnClickListener(this)
        mDataBinding.tvShoppingCarEdit.setOnClickListener(this)
        mDataBinding.tvShoppingCarSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.cb_Shopping_car_all -> {
                initCkAll()
            }

            R.id.tv_Shopping_Car_edit -> {
                initEdit()
            }

            R.id.tv_Shopping_Car_submit -> {
                initSubmit()
            }
        }
    }

    //boolean true权限  false 非全选
    private fun initCkAll() {
        if(isEdit){
            mDataBinding.cbShoppingCarAll.isChecked = false
        }else{
            mDataBinding.cbShoppingCarAll.isChecked = true
        }
    }

    //boolean true编辑 false完成
    private fun initEdit() {
        editValue = tv_Shopping_Car_edit.text.toString()
        if(editValue == "编辑"){
            mDataBinding.tvShoppingCarEdit.setText("完成")
            mDataBinding.tvShoppingCarSubmit.setText("删除所选")
        }else{
            mDataBinding.tvShoppingCarEdit.setText("编辑")
            mDataBinding.tvShoppingCarSubmit.setText("下单")
        }
    }

    //是否是提交
    private fun initSubmit() {
        editValue  = tv_Shopping_Car_edit.text.toString()
        if(editValue == "下单"){
            Log.e("TAG", "clickAll: "+"下单" )
        }else{
            //删除对应的数据
        }
    }

    //TODO 修改列表条目选择状态更新总价和数量
    inner class ItemChangeSelect : CarAdapter.ChangeEvt {
        override fun click() {
            var arr = mViewModel.getCarTotalNormal()//计算当前购物车的总价和总数量
            //获得数组
            if (arr.size == 3) {
                mDataBinding.cbShoppingCarAll.setText("全选(" + arr[0] + ")")
                mDataBinding.tvShoppingCarTotalPrice.setText("￥" + arr[1])
                mDataBinding.cbShoppingCarAll.isChecked = if (arr[2] == 0) true else false
            }
        }
    }

    inner class CarClikcItem:IItemClick<CarData.Cart>{
        override fun itemClick(data: CarData.Cart) {
            Log.e("TAG", "CarClikcItem: "+data.goods_name )
        }
    }

    //TODO 获取数据
    override fun initVM() {
        mViewModel.carData.observe(this, Observer {
            list.clear()
            list.addAll(it.cartList)
            carAdapter.notifyDataSetChanged()
        })
    }

    //TODO 调用方法
    override fun initData() {
        //购物车列表
        mViewModel.getCar()
    }

    //TODO 当前界面的点击事件
    inner class ClickEvt {
        //条目上的选中状态的点击事件
        fun itemCheckboxClick(view: View) {
            if (view is CheckBox) {
                var checkbox = view as CheckBox
                var car: CarData.Cart = checkbox.tag as CarData.Cart
                if (isEdit) {
                    car.selectEdit = checkbox.isChecked
                } else {
                    car.selectOrder = checkbox.isChecked
                }
            }
        }

        //boolean true全选  false 非全选
        fun clickAll(view: View) {
            if (view is CheckBox) {
                var checkbox = view as CheckBox
                if (isEdit) {
                    mViewModel.updateCarStateEidtor(checkbox.isChecked)
                } else {
                    mViewModel.updateCarStateNormal(checkbox.isChecked)
                }

                //刷新总价和总数量
                var arr = mViewModel.getCarTotalNormal()
                if (arr.size == 2) {
                    mDataBinding.cbShoppingCarAll.setText("全选(" + arr[0] + ")")
                    mDataBinding.tvShoppingCarTotalPrice.setText("￥" + arr[1])
                }
                carAdapter!!.notifyDataSetChanged()
            }
        }

        //boolean true编辑 false完成
        fun clickEdit(view: View) {
            if (view is TextView) {
                Log.e("TAG", "clickEdit: "+"编辑" )
                var txt = view as TextView
                if (txt.text.toString() == "编辑") {
                    mDataBinding.tvShoppingCarEdit.setText("完成")
                    mDataBinding.tvShoppingCarSubmit.setText("删除所选")
                    isEdit = true
                } else {
                    mDataBinding.tvShoppingCarEdit.setText("编辑")
                    mDataBinding.tvShoppingCarSubmit.setText("下单")
                    isEdit = false
                }
                //控制界面在编辑和非编辑状态下的显示
                changeEditUI()
            }
        }

        //是否是提交
        fun clickSubit(view: View) {
            Log.e("TAG", "clickSubit: "+"下单" )
            if (view is TextView) {
                var txt = view as TextView
                if (txt.text.toString() == "下单") {
                    //下单
                } else {
                    //删除对应的数据
                }
            }
        }
    }

    //TODO 控制界面在编辑和非编辑状态下的显示
    fun changeEditUI() {
        carAdapter!!.isEditor = isEdit
        carAdapter!!.notifyDataSetChanged()
    }

    override fun initVariable() {

    }

}