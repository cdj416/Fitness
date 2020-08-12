package com.hongyuan.fitness.ui.person.push_share;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.share_view.ShareUtil;
import com.hongyuan.fitness.databinding.ActivityPushShareBinding;
import com.hongyuan.fitness.ui.find.circle.edit_post.EditPostActivity;
import com.hongyuan.fitness.ui.main.main_person.PersonBean;
import com.hongyuan.fitness.ui.person.daily_punch.DailyPunchCheckBean;
import com.hongyuan.fitness.ui.person.daily_punch.ShareSuccessLinstener;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.GlideEngine;
import com.hongyuan.fitness.util.ImageFactory;
import com.hongyuan.fitness.util.ScreenshotUtil;
import com.hongyuan.fitness.util.TimeUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.umeng.socialize.bean.SHARE_MEDIA;
import java.io.File;
import java.util.List;

public class PushShareViewModel extends CustomViewModel implements ShareSuccessLinstener {

    private ActivityPushShareBinding binding;
    private PushShareBeans shareBeans;
    private PushShareAdapter adapter;
    private RequestOptions options;

    private AnimationSet  animationSet;
    private ScaleAnimation scaleAnimation;

    public PushShareViewModel(CustomActivity mActivity, ActivityPushShareBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        //mActivity.getMainTitle().setLeftImage(R.mipmap.close_heise_img).setCentreText("打卡分享");


        mActivity.getMainTitle().getRightView().setOnClickListener(v -> startShare());

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new PushShareAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            binding.customImg.setBackgroundResource(R.drawable.shape_radius6_ffffff);
            setShowImg(shareBeans.getData().getImgList().get(position).getImgUrl());
            clearRecSelect();
            shareBeans.getData().getImgList().get(position).setSelect(true);
            adapter.notifyDataSetChanged();
        });

        options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);

        binding.nowDays.setText(TimeUtil.getTodays());
        binding.nowMonth.setText(TimeUtil.getNowMonth()+"月");


        binding.customImg.setOnClickListener(v ->{
            clearRecSelect();
            adapter.notifyDataSetChanged();
            chooseMyImg();
        });

    }

    /*
    * 执行缩放动画
    * */
    private void startShare(){
        if(animationSet == null && scaleAnimation == null){
            //创建AnimationSet 对象
            animationSet = new AnimationSet(true);
            //创建 ScaleAnimation 对象
            scaleAnimation = new ScaleAnimation(1.0f,0.8f,1.0f,0.8f,
                    Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            //设置动画持续
            scaleAnimation.setDuration(300);
            scaleAnimation.setFillAfter(true);
            //动画插入器
            scaleAnimation.setInterpolator(mActivity,android.R.anim.decelerate_interpolator);
            //添加到AnimationSet
            animationSet.addAnimation(scaleAnimation);
            animationSet.setFillAfter(true);


            scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    CustomDialog.showShare(mActivity, v -> {
                        //scaleAnimation.cancel();

                        if(v.getId() == R.id.sd_find){
                            //写入手机中
                            File file = ImageFactory.saveImageToGallery(mActivity,ScreenshotUtil.getViewBp(binding.shareContent));
                            Bundle bundle = new Bundle();
                            bundle.putString("shareFile",file.getPath());
                            startActivity(EditPostActivity.class,bundle);
                            goShare();
                        }
                        if(v.getId() == R.id.weiXin){
                            ShareUtil.shareImg(mActivity,ScreenshotUtil.getViewBp(binding.shareContent),SHARE_MEDIA.WEIXIN,PushShareViewModel.this);
                        }
                        if(v.getId() == R.id.wxCircle){
                            ShareUtil.shareImg(mActivity,ScreenshotUtil.getViewBp(binding.shareContent),SHARE_MEDIA.WEIXIN_CIRCLE,PushShareViewModel.this);
                        }
                        if(v.getId() == R.id.qq){
                            ShareUtil.shareImg(mActivity,ScreenshotUtil.getViewBp(binding.shareContent),SHARE_MEDIA.QQ,PushShareViewModel.this);
                        }
                        if(v.getId() == R.id.qqZone){
                            ShareUtil.shareImg(mActivity,ScreenshotUtil.getViewBp(binding.shareContent),SHARE_MEDIA.QZONE,PushShareViewModel.this);
                        }
                    });
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }


        binding.shareContent.startAnimation(animationSet);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        //获取分享图片
        Controller.myRequest(Constants.SHARE_IMGS,Controller.TYPE_POST,getParams(), PushShareBeans.class,this);
        //获取个人信息
        Controller.myRequest(Constants.GET_MEMBER_INDEX_INFO,Controller.TYPE_POST,getParams(), PersonBean.class,this);

        //检查是否签到
        Controller.myRequest(Constants.CHECK_IS_QD,Controller.TYPE_POST,getParams(), DailyPunchCheckBean.class,this);
    }

    /*
    * 分享之后需要调取的接口
    * */
    private void goShare(){
        clearParams();
        //获取个人信息
        Controller.myRequest(ConstantsCode.KA_SHARE,Constants.KA_SHARE,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
    * 选择自定义照片
    * */
    private void chooseMyImg(){
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .previewVideo(true)//是否可预览视频
                .isZoomAnim(true)//图片列表点击 缩放效果 默认true
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /*
     * 图片选择回调
     * */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    setShowImg(selectList.get(0).getCompressPath());
                    binding.customImg.setBackgroundResource(R.drawable.shape_radius6_border_ef5b48);
                    break;
            }
        }
    }

    /*
    * 设置选中图片的显示
    * */
    private void setShowImg(String imgUrl){
        try {
            Glide.with(mActivity).load(imgUrl).apply(options).into(binding.showImg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    * 清楚系统照片选着项
    * */
    private void clearRecSelect(){
        for(PushShareBeans.DataBean.ImgsBean imgsBean : shareBeans.getData().getImgList()){
            imgsBean.setSelect(false);
        }
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PushShareBeans){
            shareBeans = (PushShareBeans)data;
            Glide.with(mActivity).load(shareBeans.getData().getDownload()).apply(options).into(binding.qrCodeImg);
            binding.note.setText(shareBeans.getData().getNote());
            if(shareBeans.getData().getImgList() != null && shareBeans.getData().getImgList().size() > 0){
                setShowImg(shareBeans.getData().getImgList().get(0).getImgUrl());
                adapter.setNewData(shareBeans.getData().getImgList());
            }
            mActivity.closeLoading();
        }
        if(data instanceof PersonBean){
            PersonBean personBean = (PersonBean)data;
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
            Glide.with(mActivity).load(personBean.getData().getInfo().getMi_head()).apply(options).into(binding.headImg);
            binding.userName.setText(personBean.getData().getInfo().getM_name());
        }

        if(data instanceof DailyPunchCheckBean){
            DailyPunchCheckBean.DataBean dailyPunchCheckBean = ((DailyPunchCheckBean)data).getData();
            binding.grandTotal.setText(String.valueOf(dailyPunchCheckBean.getItem().getLeiji_days()));
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.KA_SHARE){

        }
    }

    @Override
    public void shareLinstener() {
        goShare();
    }
}
