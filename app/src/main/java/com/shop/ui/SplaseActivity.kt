package com.shop.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shop.R
import com.shop.base.BaseActivity
import com.shop.databinding.ActivitySplaseBinding
import com.shop.ui.main.MainActivity
import com.shop.viewmodel.SplaseViewodel

class SplaseActivity : BaseActivity<SplaseViewodel,ActivitySplaseBinding>(R.layout.activity_splase,SplaseViewodel::class.java) {

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