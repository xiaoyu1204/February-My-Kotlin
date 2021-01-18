package com.shop.ui.main.fragment.me

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.basemvvm.BR
import com.example.basemvvm.utils.SpUtils
import com.shop.R
import com.example.basemvvm.utils.ImageLoader
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentMineBinding
import com.shop.viewmodel.mine.MineViewModel
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment:BaseFragment<MineViewModel, FragmentMineBinding>(R.layout.fragment_mine,MineViewModel::class.java),View.OnClickListener {

    var token:String? = null
    companion object{
        val instance by lazy { MineFragment() }
    }

    override fun initView() {
        initClick()
        token = SpUtils.instance!!.getString("token")
    }

    //懒加载
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden){
            initData()
            initLogin()
        }
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
                //跳转界面
            }
            R.id.btn_loginout -> {  //退出登录
                //请求退出登录数据
            }
        }
    }

    //判断是否登录
    fun initLogin(){
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
        //进入个人主页
        val intent = Intent(activity, MeUserInfoActivity::class.java)
        startActivity(intent)
        isLogin(true)
    }

    override fun initVM() {

    }

    override fun initData() {
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
            me_head_login!!.visibility = View.GONE      //点击登录
            me_head_nickname!!.visibility = View.VISIBLE   //昵称
            me_head_brithday!!.visibility = View.VISIBLE       //签名

            var username = SpUtils.instance!!.getString("username")     //名称
            var nickname = SpUtils.instance!!.getString("nickname")     //昵称
            var birthday = SpUtils.instance!!.getString("birthday")     //生日
            var avatar = SpUtils.instance!!.getString("avatar")     //头像

            if(!TextUtils.isEmpty(nickname)){
                me_head_nickname.setText(nickname)
            }else{
                me_head_nickname.setText(username)
            }
            me_head_brithday.setText(birthday)
            ImageLoader.loadImage(avatar,me_head_img)
            if(!TextUtils.isEmpty(avatar)){
                Glide.with(this).load(avatar).apply(RequestOptions().circleCrop()).into(me_head_img)
            }

        }else{      //未登录
            me_head_login!!.visibility = View.VISIBLE      //点击登录
            me_head_nickname!!.visibility = View.GONE   //昵称
            me_head_brithday!!.visibility = View.GONE       //签名
            Glide.with(this).load(R.mipmap.f9).apply(RequestOptions().circleCrop()).into(me_head_img)
        }

    }


    override fun initVariable() {

    }



}