package com.example.basemvvm.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * 通过DataBindingAdapter 扩展Adapter中组件的使用
 * imageView 图片加载
 */
public class LoadAdapter {
    /**
     * 图片加载的注解
     * @param img
     * @param url
     */
    @BindingAdapter(value = "app:imageUrl",requireAll = false)
    public static void loadImage(ImageView img, String url){
        Glide.with(img).load(url).into(img);
    }

    /**
     * 圆角图片的加载  自定义加载
     * @param img
     * @param url
     * @param number
     */
    @BindingAdapter(value = {"app:cornerUrl","app:corner"},requireAll = true)
    public static void loadCorner(ImageView img,String url,String number){
        RequestOptions options = new RequestOptions();
        options.transform(new RoundedCorners(Integer.valueOf(number)));
        Glide.with(img).load(url).apply(options).into(img);
    }

}
