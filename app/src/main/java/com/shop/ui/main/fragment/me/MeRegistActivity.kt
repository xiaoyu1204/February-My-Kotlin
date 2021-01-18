package com.shop.ui.main.fragment.me

import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.basemvvm.utils.CodeUtils
import com.example.basemvvm.utils.SpUtils
import com.example.basemvvm.utils.ToastUtils
import com.shop.R
import com.shop.base.BaseActivity
import com.shop.databinding.ActivityMeRegistBinding
import com.shop.viewmodel.main.mine.MineRegisterViewModel
import kotlinx.android.synthetic.main.activity_me_regist.*

///MyMmkv.getString("token")
class MeRegistActivity (
    var lid: Int = R.layout.activity_me_regist
): BaseActivity<MineRegisterViewModel, ActivityMeRegistBinding>(lid, MineRegisterViewModel::class.java),
    View.OnClickListener{

    private lateinit var username:String
    private lateinit var pw:String

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
        pw = me_regist_input_pw!!.text.toString()
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
                        initRe(pw)
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

    fun initRe(pw: String) {
        //取出Sp中存入的username
        var string = SpUtils.instance!!.getString(username)
        //判断Sp中是否有存入的username
        if(!TextUtils.isEmpty(string)){
            //如果有存入的
            //用户名已经注册
            ToastUtils.s(this,getString(R.string.tips_regist_))
            return
        }else{
            //没有存入的
            //注册方法
            zhuce(username!!,pw)
        }
    }

    private fun zhuce(username: String, pw: String) {
        /**
         * 1.注册
         * 2.将用户名最为key 密钥（token）作为value 存入sp (sp.....set)
         */
        mViewModel.MeRegister(username,pw)
    }

    override fun initVM() {
        mViewModel.meregister.observe(this, Observer {
            if(it!=null && it.size>0){
                var token = it.get(0).token
                if(!TextUtils.isEmpty(token)){
                    //保存到sp中
                    SpUtils.instance!!.setValue(username,token)
                    SpUtils.instance!!.setValue("token",token)
                    SpUtils.instance!!.setValue("uid",it.get(0).userInfo.uid)

                    //回传值
                    val intent = intent
                    intent.putExtra("username",username)
                    intent.putExtra("password",pw)
                    setResult(100,intent)
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