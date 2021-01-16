package com.shop.ui.main.fragment.me

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.example.basemvvm.utils.MyMmkv
import com.shop.R
import com.shop.app.Constants
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentMineBinding
import com.shop.viewmodel.mine.MineViewModel

class MineFragment:BaseFragment<MineViewModel, FragmentMineBinding>(R.layout.fragment_mine,MineViewModel::class.java),View.OnClickListener {

    companion object{
        val instance by lazy { MineFragment() }
    }

    override fun initView() {
        initClick()
    }

    fun initClick(){
        mDataBinding!!.meHeadImg.setOnClickListener(this)
        mDataBinding!!.meHeadLogin.setOnClickListener(this)
        mDataBinding!!.meHeadJtImg.setOnClickListener(this)
        mDataBinding!!.meLlShoucang.setOnClickListener(this)
        mDataBinding!!.btnLoginout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.me_head_img -> {       //头像图片
                initLogin()
            }
            R.id.me_head_login -> { //点击登录
                initLogin()
            }
            R.id.me_head_jt_img -> {    //右边箭头
                initLogin()
            }
            R.id.me_ll_shoucang -> {    //收藏

            }
            R.id.btn_loginout -> {  //退出登录

            }
        }
    }

    //判断是否登录
    fun initLogin(){
        var token = MyMmkv.getString(Constants.token)
        if(!TextUtils.isEmpty(token)){
            //进入个人主页
            openUserInfoDetail()
        }else{
            //跳转登录界面
            val intent = Intent(activity,MineLoginActivity::class.java)
            startActivity(intent)
            isLogin(false)
        }
    }

    fun openUserInfoDetail(){

    }

    override fun initVM() {

    }

    override fun initData() {
        var token = MyMmkv.getString(Constants.token)
        if(!TextUtils.isEmpty(token)){
            isLogin(true)
        }else{
            isLogin(false)
        }
    }
    /**
     * 登录状态的界面控制
     * @param bool
     */
    private fun isLogin(bool: Boolean) {
        if (bool) {     //登录

        }else{      //未登录

        }
    }


    override fun initVariable() {

    }



}