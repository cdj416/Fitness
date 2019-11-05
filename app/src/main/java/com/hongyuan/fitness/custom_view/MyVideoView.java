package com.hongyuan.fitness.custom_view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.video.LandLayoutVideo;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.GSYVideoProgressListener;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

public class MyVideoView extends LinearLayout{

    private boolean isPlay;
    private boolean isPause;

    private LandLayoutVideo video;
    private OrientationUtils orientationUtils;
    public MyVideoView(Context context) {
        super(context);
        initLayoutView();
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_my_video, this);
        video = view.findViewById(R.id.video);
    }

    /*
    * 初始化基础设置
    * */
    public void setMyVideo(String videoUrl,String imgUrl){
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils((Activity) getContext(), video);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        GSYVideoOptionBuilder gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption.setThumbImageView(getVideoImg(imgUrl))
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1)
                .setUrl(videoUrl)
                .setCacheWithPlay(false)
                .setVideoTitle("测试视频")
                .setVideoAllCallBack(new GSYSampleCallBack() {

                    @Override
                    public void onPrepared(String url, Object... objects) {
                        Debuger.printfError("***** onPrepared **** " + objects[0]);
                        Debuger.printfError("***** onPrepared **** " + objects[1]);
                        super.onPrepared(url, objects);
                        //开始播放了才能旋转和全屏
                        orientationUtils.setEnable(false);
                        isPlay = true;
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                        Debuger.printfError("***** onEnterFullscreen **** " + objects[0]);//title
                        Debuger.printfError("***** onEnterFullscreen **** " + objects[1]);//当前全屏player
                    }

                    @Override
                    public void onAutoComplete(String url, Object... objects) {
                        super.onAutoComplete(url, objects);
                    }

                    @Override
                    public void onClickStartError(String url, Object... objects) {
                        super.onClickStartError(url, objects);
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[0]);//title
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[1]);//当前非全屏player
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                    }
                })
                .setLockClickListener(new LockClickListener() {
                    @Override
                    public void onClick(View view, boolean lock) {
                        if (orientationUtils != null) {
                            //配合下方的onConfigurationChanged
                            orientationUtils.setEnable(!lock);
                        }
                    }
                })
                .setGSYVideoProgressListener(new GSYVideoProgressListener() {
                    @Override
                    public void onProgress(int progress, int secProgress, int currentPosition, int duration) {
                        Debuger.printfLog(" progress " + progress + " secProgress " + secProgress + " currentPosition " + currentPosition + " duration " + duration);
                    }
                })
                .build(video);

        video.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                video.startWindowFullscreen(getContext(), true, true);
            }
        });
        video.setLinkScroll(true);
    }

    /*
    * 添加视频封面
    * */
    private ImageView getVideoImg(String imgUrl){
        //增加封面
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img).centerCrop();
        Glide.with(this).load(imgUrl).apply(options).into(imageView);
        return imageView;
    }

    /*
    * 是否显示播放标题
    * */
    private void resolveNormalVideoUI() {
        //增加title
        video.getTitleTextView().setVisibility(View.GONE);
        video.getBackButton().setVisibility(View.GONE);
    }

    /*
    * 按返回
    * */
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (GSYVideoManager.backFromWindowFull(getContext())) {
            return;
        }
    }


    /*
    * 暂停状态
    * */
    public void onPause() {
        getCurPlay().onVideoPause();
        isPause = true;
    }

    /*
    * onResume
    * */
    public void onResume() {
        getCurPlay().onVideoResume();
        isPause = false;
    }

    /*
    * 销毁状态
    * */
    public void onDestroy() {
        if (isPlay) {
            getCurPlay().release();
        }
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }


    public void onConfigurationChanged(Configuration newConfig) {
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            video.onConfigurationChanged((Activity) getContext(), newConfig, orientationUtils, true, true);
        }
    }


    private GSYVideoPlayer getCurPlay() {
        if (video.getFullWindowPlayer() != null) {
            return video.getFullWindowPlayer();
        }
        return video;
    }


}
