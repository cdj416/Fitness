package com.hongyuan.fitness.custom_view.share_view;

public class ShareContentBean {


    /**
     * shareTitle : 分享的标题
     * shareUrl : 网页地址
     * shareImg : 图片地址
     * shareContent : 分享的内容
     */

    private String shareTitle;
    private String shareUrl;
    private String shareImg;
    private String shareContent;

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }
}
