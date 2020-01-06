package com.hongyuan.fitness.ui.promt_success;

import android.os.Bundle;
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
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityV3SuccessBinding;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.main.main_person.PersonBean;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayViewModel;
import com.hongyuan.fitness.ui.person.edit_information.take_photo.TakePhotoActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

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

        if(BaseUtil.isValue(GoodsPayViewModel.successBeans)){
            beans = GoodsPayViewModel.successBeans;
            GoodsPayViewModel.successBeans = null;
        }else{
            beans = (V3SuccessBeans) getBundle().getSerializable("successBeans");
        }


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
    * 请求是否需要人脸是否接口
    * */
    private void getData(){
        clearParams();
        Controller.myRequest(Constants.GET_MEMBER_INDEX_INFO,Controller.TYPE_POST,getParams(), PersonBean.class,this);
    }

    /*
    * 赋值
    * */
    @Override
    protected void setData() {
        mActivity.getMainTitle().setCentreText(beans.getTitleText());
        binding.showText.setText(beans.getShowText());
        adapter.setNewData(beans.getItemContens());

        if(!"人脸识别录入".equals(beans.getBtn2Text())){
            binding.goCarryOn.setOnClickListener(v -> startActivity(MainActivity.class,null));
            binding.goCarryOn.setText(beans.getBtn2Text());
        }else{
            getData();
        }

    }

    @Override
    protected void forResult(Bundle bundle) {
        boolean isSuccess = bundle.getBoolean("isSuccess");
        if(isSuccess){
            mActivity.showSuccess("录入成功！",MainActivity.class,null);
        }

    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PersonBean){
            PersonBean.DataBean.InfoBean bean = ((PersonBean)data).getData().getInfo();
            if(!BaseUtil.isValue(bean.getMi_face())){
                binding.cancel.setVisibility(View.VISIBLE);
                binding.cancel.setText(beans.getBtn1Text());
                binding.goCarryOn.setText(beans.getBtn2Text());
                binding.cancel.setOnClickListener(v -> startActivity(MainActivity.class,null));
                //弹出拍照提示框
                binding.goCarryOn.setOnClickListener(v -> CustomDialog.showTakePhoto(mActivity, v13 -> {
                    startActivityForResult(TakePhotoActivity.class,null);
                }));
            }else{
                binding.goCarryOn.setText("完成");
                binding.goCarryOn.setOnClickListener(v -> startActivity(MainActivity.class,null));
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.CANCEL_COURSE_SUPER_ORDER){
            showSuccess("团课取消成功！");
        }
    }
}
