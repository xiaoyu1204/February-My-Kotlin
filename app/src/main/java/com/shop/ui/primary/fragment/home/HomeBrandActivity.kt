package com.shop.ui.primary.fragment.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shop.BR
import com.shop.R
import com.shop.databinding.ActivityHomeBrandBinding
import com.shop.viewmodel.home.HomeBrandViewModel

class HomeBrandActivity : AppCompatActivity() {

    var mBinding:ActivityHomeBrandBinding? = null
    var vm:HomeBrandViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_home_brand)
        initVM()

    }

    fun initVM(){
        vm = ViewModelProvider(this).get(HomeBrandViewModel::class.java)
        vm !!.homebrandData()
        vm !!.stauts.observe(this, Observer {
            if (it == 0){
                mBinding!!.setVariable(BR.brand,vm)
            }
        })
    }

}