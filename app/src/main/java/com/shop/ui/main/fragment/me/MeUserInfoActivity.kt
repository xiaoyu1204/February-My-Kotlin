package com.shop.ui.main.fragment.me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.basemvvm.BR
import com.example.basemvvm.utils.SpUtils
import com.example.basemvvm.utils.SystemUtils
import com.example.basemvvm.utils.ToastUtils
import com.shop.R
import com.shop.base.BaseActivity
import com.shop.databinding.ActivityMeUserInfoBinding
import com.shop.viewmodel.main.mine.MeUserInfoViewModel
import kotlinx.android.synthetic.main.activity_me_user_info.*

class MeUserInfoActivity : BaseActivity<MeUserInfoViewModel, ActivityMeUserInfoBinding>(
    R.layout.activity_me_user_info,
    MeUserInfoViewModel::class.java
), View.OnClickListener {

    var bucketName = "2002a02" //Bucket 名
    var ossPoint = "http://oss-cn-beijing.aliyuncs.com" //Bucket 名

    //这个通常使用密钥传输，直接放着不安全
    var key = "LTAI4G3x7KtcnYUxxSmYK17e" //appkey
    var secret = "D1PLamKr1tuzcMC0J4dGrXTfvAe9Jq" //密码

    var nickname_1:String? = null
    var birthday_1:String? = null

    override fun initView() {
        initClick()

        var sp_username = SpUtils.instance!!.getString("username")     //名称
        var sp_nickname = SpUtils.instance!!.getString("nickname")     //昵称
        var sp_birthday = SpUtils.instance!!.getString("birthday")     //生日
        var sp_avatar = SpUtils.instance!!.getString("avatar")     //头像

        //赋值
        txt_username.setText(sp_username)
        txt_nickname.setText(sp_nickname)
        txt_birthday.setText(sp_birthday)
        if (!TextUtils.isEmpty(sp_avatar)) {
            Glide.with(this).load(sp_avatar).apply(RequestOptions().circleCrop()).into(img_avatar!!)
        }

    }

    fun initClick(){
        mDataBinding!!.imgAvatar.setOnClickListener(this)
        mDataBinding!!.layoutNickname.setOnClickListener(this)
        mDataBinding!!.layoutBirthday.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.img_avatar -> {       //头像图片
                ImgAvater()
            }
            R.id.layout_nickname -> {       //昵称
                NickName()
            }
            R.id.layout_birthday -> {       //生日
                Birthday()
            }
        }
    }

    //头像图片
    private fun ImgAvater() {

    }
    //昵称
    private fun NickName() {
        updateName()
        btn_save!!.setOnClickListener {
            nickname_1 = txt_input.text.toString()
            if(!TextUtils.isEmpty(nickname_1)){
                val map = HashMap<String,String>()
                map["nickname"] = nickname_1!!
                mViewModel.MeUserInfo(map)
                layout_input.visibility = View.GONE
                txt_nickname.setText(nickname_1)
                txt_input.setText("")
            }
        }
    }
    //生日
    private fun Birthday() {
        updateName()
        btn_save!!.setOnClickListener {
            birthday_1 = txt_input.text.toString()
            if (!TextUtils.isEmpty(birthday_1)) {
                val map = HashMap<String,String>()
                map["birthday"] = birthday_1!!
                mViewModel.MeUserInfo(map)
                layout_input.visibility = View.GONE
                txt_birthday.setText(birthday_1)
                txt_input.setText("")
            }
        }
    }

    private fun updateName() {
        layout_input!!.visibility = View.VISIBLE
        txt_input!!.isFocusable = true
        SystemUtils.openSoftKeyBoard(this)
    }

    override fun initVM() {
        mViewModel.meuserinfo.observe(this, Observer {
            if (it != null && it.size > 0) {
                SystemUtils.closeSoftKeyBoard(this)
                layout_input!!.visibility = View.GONE
                SpUtils.instance!!.setValue("nickname", nickname_1)
                SpUtils.instance!!.setValue("birthday", birthday_1)
            }
        })
    }

    override fun initData() {

    }


    override fun initVariable() {
    }

}