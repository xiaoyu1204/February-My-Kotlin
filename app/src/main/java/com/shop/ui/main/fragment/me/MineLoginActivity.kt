package com.shop.ui.main.fragment.me

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.example.basemvvm.utils.MyMmkv
import com.example.basemvvm.utils.ToastUtils
import com.shop.R
import com.shop.base.BaseActivity
import com.shop.databinding.ActivityGoodListDetailBinding
import com.shop.databinding.ActivityMineLoginBinding
import com.shop.viewmodel.main.home.GoodListViewModel
import com.shop.viewmodel.mine.MineViewModel
import kotlinx.android.synthetic.main.activity_mine_login.*

class MineLoginActivity(
    var lid: Int = R.layout.activity_mine_login
): BaseActivity<MineViewModel, ActivityMineLoginBinding>(lid, MineViewModel::class.java),
    View.OnClickListener{

    override fun initView() {
        //眼睛
        me_login_img_pw!!.tag = 2
        me_login_input_pw!!.transformationMethod = HideReturnsTransformationMethod.getInstance()
        initClick()
    }

    fun initClick(){
        mDataBinding!!.meLoginImgPw.setOnClickListener(this)
        mDataBinding!!.meLoginBtnLogin.setOnClickListener(this)
        mDataBinding!!.meLoginRegist.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.me_login_img_pw -> {       //眼睛
                initPwImg()
            }
            R.id.me_login_btn_login -> {        //登录
                initLogin()
            }
            R.id.me_login_regist -> {        //注册
                initRegist()
            }
        }
    }

    fun initPwImg() {
        //点击的第几次
        val tag = me_login_img_pw!!.tag as Int
        //第一次显示
        if (tag == 1) {
            me_login_img_pw!!.setImageResource(R.mipmap.ic_pw_open)
            me_login_img_pw!!.tag = 2
            me_login_input_pw!!.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {  //第一次显示
            me_login_img_pw!!.setImageResource(R.mipmap.ic_pw_close)
            me_login_img_pw!!.tag = 1
            me_login_input_pw!!.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }

    fun initLogin() {
        val username = me_login_input_username!!.text.toString()
        val pw = me_login_input_pw!!.text.toString()
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw)) {
            val token = MyMmkv.getString("token")
            if (token != null) {
                //请求数据
                MyMmkv.setValue("name", username)
                MyMmkv.setValue("password", pw)
                }
            } else {
                ToastUtils.s(this, getString(R.string.tips_login_))
            }
        }

    fun initRegist(){
        val intent = Intent(this, MeRegistActivity::class.java)
        startActivityForResult(intent, 100)
    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {

    }
}