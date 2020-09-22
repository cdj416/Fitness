package com.hongyuan.fitness.ui.person.my_promote;

import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityPromotionCodeBinding;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.ImageFactory;
import com.hongyuan.fitness.util.ScreenshotUtil;

public class PromotionCodeViewModel extends CustomViewModel {

    private ActivityPromotionCodeBinding binding;
    private PromotionCodeBeans.DataBean.InfoBean infoBean;

    public PromotionCodeViewModel(CustomActivity mActivity, ActivityPromotionCodeBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);

        binding.box.setOnLongClickListener(v -> {
            ImageFactory.saveImageToGallery(mActivity, ScreenshotUtil.getViewBp(binding.box));
            CustomDialog.showMessage(mActivity,"图片保存成功！");
            return false;
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.TGINFO,Controller.TYPE_POST,getParams(), PromotionCodeBeans.class,this);
    }

    @Override
    protected void setData() {
        PromotionRecordTopRightView rightView = new PromotionRecordTopRightView(mActivity,infoBean);
        //把需要截屏的view传递过去
        rightView.setShareBitmp(binding.box);
        mActivity.getMainTitle().addRightContentView(rightView);

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        RequestOptions optionsQr = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img).centerCrop();
        Glide.with(mActivity).load(infoBean.getMi_head()).apply(options).into(binding.headImg);
        Glide.with(mActivity).load(infoBean.getOss_url()).apply(optionsQr).into(binding.qrCodeImg);

        binding.name.setText(infoBean.getM_name());
        binding.desText.setText(infoBean.getTxt());

    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();
        if(data instanceof PromotionCodeBeans){
            infoBean = ((PromotionCodeBeans)data).getData().getInfo();

            setData();
        }
    }


}
