package com.shop.adapter.home

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shop.model.bean.home.Banner
import com.shop.model.bean.home.Banner2
import com.youth.banner.loader.ImageLoader

class MyBannerAdapter: ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        var item = path as Banner2
        Glide.with(context!!).load(item.image_url).into(imageView!!)
    }
}