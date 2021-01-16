package com.shop.ui.main.fragment.me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.basemvvm.utils.CodeUtils
import com.example.basemvvm.utils.MyMmkv
import com.example.basemvvm.utils.ToastUtils
import com.shop.R
import com.shop.base.BaseActivity
import com.shop.databinding.ActivityMeRegistBinding
import com.shop.databinding.ActivityMineLoginBinding
import com.shop.viewmodel.main.mine.MineRegisterViewModel
import com.shop.viewmodel.mine.MineViewModel
import kotlinx.android.synthetic.main.activity_me_regist.*

///MyMmkv.getString("token")
class MeRegistActivity (
    var lid: Int = R.layout.activity_me_regist
): BaseActivity<MineRegisterViewModel, ActivityMeRegistBinding>(lid, MineRegisterViewModel::class.java),
    View.OnClickListener{

    var username:String? = null

    override fun initView() {
        //验证码
        initCode()
        //点击
        initClick()
    }

    fun initCode(){
        val bitmap = CodeUtils.instance!!.createBitmap()
        me_regist_btn_verification!!.setImageBitmap(bitmap)
    }

    fun initClick(){
        mDataBinding!!.meRegistBtnVerification.setOnClickListener(this)
        mDataBinding!!.meRegistBtnRegist.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.me_regist_btn_verification -> {        //验证码
                initCode()
            }
            R.id.me_regist_btn_regist -> {      //注册
                initRegist()
            }
        }
    }

    private fun initRegist() {
        username = me_regist_input_username!!.text.toString()
        val pw = me_regist_input_pw!!.text.toString()
        val pw2 = me_regist_input_pw2!!.text.toString()
        val ver = me_regist_input_verification!!.text.toString()

        //判断不能为空
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw) && !TextUtils.isEmpty(pw2)) {
            //密码和确认密码必须一致
            if (pw == pw2) {
                //密码大于6位
                if (pw.length >= 6) {
                    //验证码不能为空
                    if (ver == "" || ver.length != 0) {
                        initRe()
                    }else {
                        //验证码不能为空
                        ToastUtils.s(this, getString(R.string.tips_regist_ver))
                    }
                }else {
                    //密码大于6位
                    ToastUtils.s(this, getString(R.string.tips_regist_pw_6))
                }
            }else {
                //密码不一样
                ToastUtils.s(this, getString(R.string.tips_regist_pw_pw2))
            }
        }else {
            //null
            ToastUtils.s(this, getString(R.string.tips_regist))
        }
    }

    fun initRe(){
        //取出MyMmkv中存入的username
        MyMmkv.getString(username!!)
        //判断sp中是否有存入的username
    }

    override fun initVM() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun initVariable() {
        TODO("Not yet implemented")
    }




}