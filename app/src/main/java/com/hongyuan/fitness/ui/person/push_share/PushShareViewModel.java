package com.hongyuan.fitness.ui.person.push_share;
import android.app.Activity;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityPushShareBinding;
import com.hongyuan.fitness.ui.main.main_person.PersonBean;
import com.hongyuan.fitness.util.ScreenshotUtil;
import com.hongyuan.fitness.util.TimeUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.List;

public class PushShareViewModel extends CustomViewModel {

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
        binding.grandTotal.setText("本月已累计打卡"+getBundle().getString("leiji_days")+"天");

        mActivity.getMainTitle().setLeftImage(R.mipmap.close_heise_img);
        mActivity.getMainTitle().setRightTextColor("分享",mActivity.getResources().getColor(R.color.color_FF333333));
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
                    new ShareAction(mActivity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                            .withMedia(new UMImage(mActivity, ScreenshotUtil.getViewBp(binding.shareContent)))
                            .setCallback(new UMShareListener() {
                                @Override
                                public void onStart(SHARE_MEDIA platform) {
                                    // 分享开始的回调
                                }

                                @Override
                                public void onResult(SHARE_MEDIA platform) {
                                    goShare();
                                }

                                @Override
                                public void onError(SHARE_MEDIA platform, Throwable t) {
                                    Toast.makeText(mActivity,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancel(SHARE_MEDIA platform) {
                                    Toast.makeText(mActivity,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .share();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }


        binding.shareContent.startAnimation(animationSet);
    }

    /*
    * 动画还原
    * */
    public void cancelAnit(){
        if(scaleAnimation != null){
            scaleAnimation.cancel();
        }
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        //获取分享图片
        Controller.myRequest(Constants.SHARE_IMGS,Controller.TYPE_POST,getParams(), PushShareBeans.class,this);
        //获取个人信息
        Controller.myRequest(Constants.GET_MEMBER_INDEX_INFO,Controller.TYPE_POST,getParams(), PersonBean.class,this);
    }

    /*
    * 分享之后需要调取的接口
    * */
    private void goShare(){
        clearParams();
        //获取个人信息
        Controller.myRequest(Constants.KA_SHARE,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
    * 选择自定义照片
    * */
    private void chooseMyImg(){
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())
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
    }
}
