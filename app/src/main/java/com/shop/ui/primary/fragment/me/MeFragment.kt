package com.shop.ui.primary.fragment.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shop.R

class MeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        var inflate = inflater.inflate(R.layout.fragment_me,container,false)
        //TODO 协程  挂起的状态
        return inflate
    }

}