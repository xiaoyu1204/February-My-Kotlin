package com.shop.ui.main.fragment.me

import android.content.Intent
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.basemvvm.utils.SpUtils
import com.example.basemvvm.utils.ToastUtils
import com.shop.R
import com.shop.base.BaseActivity
import com.shop.databinding.ActivityMineLoginBinding
import com.shop.viewmodel.main.mine.MineLoginViewModel
import kotlinx.android.synthetic.main.activity_mine_login.*

class MineLoginActivity(
    var lid: Int = R.layout.activity_mine_login
): BaseActivity<MineLoginViewModel, ActivityMineLoginBinding>(lid, MineLoginViewModel::class.java),
    View.OnClickListener{

    private lateinit var username:String
    private lateinit var password:String

    override fun initView() {
        initClick()
        initResult()
    }

    private fun initResult() {
        //眼睛
        me_login_img_pw!!.tag = 1
        me_login_input_pw!!.transformationMethod = PasswordTransformationMethod.getInstance()
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

    private fun initPwImg() {
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

    private fun initLogin() {
        username = me_login_input_username!!.text.toString()
        password = me_login_input_pw!!.text.toString()
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            var token = SpUtils.instance!!.getString("token")
            if (token != null) {
                mViewModel.MeLogin(username,password)
            }else {
                ToastUtils.s(this, getString(R.string.tips_login))
            }
        } else {
            ToastUtils.s(this, getString(R.string.tips_login_))
        }
    }

    private fun initRegist(){
        val intent = Intent(this, MeRegistActivity::class.java)
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == 100 && requestCode == 100) {

            var regist_username = data!!.getStringExtra("username").toString()
            var regist_password = data!!.getStringExtra("password").toString()

            //赋值s
            if (!TextUtils.isEmpty(regist_username) && !TextUtils.isEmpty(regist_password)) {
                me_login_input_username.setText(regist_username)
                me_login_input_pw.setText(regist_password)
            } else {
                Log.e("TAG", "initResult: " + "注册回传值为空")
            }

        }
    }
    override fun initVM() {
        mViewModel.melogin.observe(this, Observer {
            if (it != null && it.size > 0) {
                var token = it.get(0).token
                if(!TextUtils.isEmpty(token)){
                    SpUtils.instance!!.setValue("token",token)
                    SpUtils.instance!!.setValue("uid",it.get(0).userInfo.uid)
                    ToastUtils.s(this, getString(R.string.tips_login_ok))
                    finish()
                }
            }
        })
    }

    override fun initData() {

    }

    override fun initVariable() {

    }

}