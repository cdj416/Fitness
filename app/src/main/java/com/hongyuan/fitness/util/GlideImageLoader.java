package com.hongyuan.fitness.util;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 *
 * Describe:提供Banner图片加载器
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        /*Glide.with(context)
                .load(path)
                .dontAnimate()
                .fitCenter().into(imageView);*/
        imageView.setImageResource((int)path);
    }
}
