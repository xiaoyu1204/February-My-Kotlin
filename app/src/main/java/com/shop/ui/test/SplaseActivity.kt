package com.shop.ui.test

import android.content.Intent
import com.shop.R
import com.shop.base.BaseActivity
import com.shop.databinding.ActivitySplaseBinding
import com.shop.ui.main.MainActivity
import com.shop.viewmodel.test.SplaseViewModel

class SplaseActivity : BaseActivity<SplaseViewModel,ActivitySplaseBinding>(R.layout.activity_splase,
    SplaseViewModel::class.java) {

    override fun initView() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {

    }


}