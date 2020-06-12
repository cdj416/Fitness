package com.hongyuan.fitness.custom_view.share_view;

import android.graphics.Bitmap;
import android.widget.Toast;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.ui.person.daily_punch.ShareSuccessLinstener;
import com.hongyuan.fitness.util.BaseUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import me.goldze.mvvmhabit.utils.KLog;

public class ShareUtil {

    private static UMShareListener umShareListener;

    private static ShareBeans shareBeans;

    /*
     * 分享面板
     * */
    public static void showShare(CustomActivity mActivity){
        new ShareAction(mActivity)
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback((snsPlatform, share_media) -> {
                    if (share_media == SHARE_MEDIA.QQ) {
                        KLog.e("点击QQ");
                        new ShareAction(mActivity).setPlatform(SHARE_MEDIA.QQ)
                                .withMedia(setDataWeb(mActivity,shareBeans))
                                .setCallback(umShareListener)
                                .share();
                    } else if (share_media == SHARE_MEDIA.WEIXIN) {
                        KLog.e("点击微信");
                        new ShareAction(mActivity).setPlatform(SHARE_MEDIA.WEIXIN)
                                .withMedia(setDataWeb(mActivity,shareBeans))
                                .setCallback(umShareListener)
                                .share();
                    } else if (share_media == SHARE_MEDIA.QZONE) {
                        new ShareAction(mActivity).setPlatform(SHARE_MEDIA.QZONE)
                                .withMedia(setDataWeb(mActivity,shareBeans))
                                .setCallback(umShareListener)
                                .share();
                    } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                        new ShareAction(mActivity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                .withMedia(setDataWeb(mActivity,shareBeans))
                                .setCallback(umShareListener)
                                .share();
                    }
                }).open();


        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                // 分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(mActivity, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(mActivity,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(mActivity,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public static void shareImg(CustomActivity mActivity, Bitmap shareImg, SHARE_MEDIA type, ShareSuccessLinstener successLinstener){
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                // 分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                //Toast.makeText(mActivity, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                successLinstener.shareLinstener();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(mActivity,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(mActivity,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            }
        };

        new ShareAction(mActivity).setPlatform(type)
                .withMedia(new UMImage(mActivity, shareImg))
                .setCallback(umShareListener)
                .share();
    }


    private static UMWeb setDataWeb(CustomActivity mActivity,ShareBeans shareBeans){
        if(!BaseUtil.isValue(shareBeans)){
            shareBeans = getShareBean();
        }
        UMImage image = new UMImage(mActivity, shareBeans.getShareImgUrl());//分享图标
        final UMWeb web = new UMWeb(shareBeans.getShareWebsite()); //切记切记 这里分享的链接必须是http开头
        web.setTitle(shareBeans.getShareTitle());//标题
        web.setThumb(image);  //缩略图
        web.setDescription(shareBeans.getShareInfo());//描述

        return web;
    }


    /*组装默认分享数据*/
    private static ShareBeans getShareBean(){
        ShareBeans beans = new ShareBeans();
        beans.setShareImgUrl("https://www.baidu.com/img/bd_logo1.png");
        beans.setShareInfo("发现同好 默契圈友趣分享 线上私教");
        beans.setShareTitle("身边的健康管理专家");
        beans.setShareWebsite("http://www.hongyuangood.com/app/suidong.html?from=singlemessage&isappinstalled=0");
        return beans;
    }

    /*
    * 二维码推广分享
    * */
    public static void setQrShare(String qrUrl){
        shareBeans = new ShareBeans();
        shareBeans.setShareImgUrl(qrUrl);
        shareBeans.setShareInfo("扫码加入随动运动社区,燃烧你的卡路里.");
        shareBeans.setShareTitle("推广码");
        shareBeans.setShareWebsite("http://www.hongyuangood.com/app/suidong.html?from=singlemessage&isappinstalled=0");
    }

}
