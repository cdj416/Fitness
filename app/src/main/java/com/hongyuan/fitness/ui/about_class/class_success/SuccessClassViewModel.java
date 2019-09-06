package com.hongyuan.fitness.ui.about_class.class_success;

import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityClassSuccessBinding;
import com.hongyuan.fitness.ui.main.MainActivity;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class SuccessClassViewModel extends CustomViewModel {

    private ActivityClassSuccessBinding binding;

    public SuccessClassViewModel(CustomActivity mActivity , ActivityClassSuccessBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        mActivity.getMainTitle().setCentreText(getBundle().getString("titleName",""));
        binding.successText.setText(getBundle().getString("successText",""));
        binding.goHome.setText(getBundle().getString("buttonText"));

        if("预约私教课".equals(getBundle().getString("titleName",""))){
            binding.goCarryOn.setVisibility(View.VISIBLE);
            binding.goCarryOn.setOnClickListener(v -> mActivity.finish());
        }else{
            binding.goCarryOn.setVisibility(View.GONE);
        }

        //Glide.with(mActivity).load(R.drawable.success_gif_img).into(binding.successImg);

        RequestOptions options = new RequestOptions().skipMemoryCache(true);//配置

        Glide.with(mActivity).asGif()
                .apply(options)//应用配置
                .load(R.drawable.success_gif_img)
                .listener(new RequestListener<GifDrawable>() {//添加监听，设置播放次数
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (resource instanceof GifDrawable) {
                            resource.setLoopCount(1);//只播放一次
                        }
                        return false;
                    }
                })
                .into(binding.successImg);
    }

    //跳转到首页
    public BindingCommand goMain = new BindingCommand(() -> startActivity(MainActivity.class,null));

    @Override
    public void onSuccess(Object data) {

    }
}
