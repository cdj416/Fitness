package com.hongyuan.fitness.ui.promt_success;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityV3SuccessBinding;
import com.hongyuan.fitness.ui.main.MainActivity;

public class V3SuccessViewModel extends CustomViewModel {

    private ActivityV3SuccessBinding binding;
    private V3SuccessAdapter adapter;
    private V3SuccessBeans beans;

    public V3SuccessViewModel(CustomActivity mActivity, ActivityV3SuccessBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        beans = (V3SuccessBeans) getBundle().getSerializable("successBeans");


        LinearLayoutManager menuManager = new LinearLayoutManager(mActivity);
        menuManager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(menuManager);
        adapter = new V3SuccessAdapter();
        binding.mRecycler.setAdapter(adapter);

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
                .into(binding.showImg);

        setData();
    }

    /*
    * 赋值
    * */

    @Override
    protected void setData() {
        mActivity.getMainTitle().setCentreText(beans.getTitleText());
        binding.showText.setText(beans.getShowText());
        binding.goCarryOn.setText(beans.getBtn2Text());
        adapter.setNewData(beans.getItemContens());

        binding.goCarryOn.setOnClickListener(v -> startActivity(MainActivity.class,null));

    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.CANCEL_COURSE_SUPER_ORDER){
            showSuccess("团课取消成功！");
        }
    }
}
