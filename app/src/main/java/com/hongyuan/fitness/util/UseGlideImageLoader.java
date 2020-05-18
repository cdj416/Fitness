package com.hongyuan.fitness.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.youth.banner.loader.ImageLoader;

/**
 *
 * Describe:提供Banner图片加载器
 */
public class UseGlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2).centerCrop();
        Glide.with(context).load(path).apply(options).into(imageView);
    }
}
