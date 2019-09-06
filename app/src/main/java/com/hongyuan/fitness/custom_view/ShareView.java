package com.hongyuan.fitness.custom_view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hongyuan.fitness.R;
import com.xyzlf.share.library.bean.ShareEntity;
import com.xyzlf.share.library.interfaces.ShareConstant;
import com.xyzlf.share.library.util.ShareUtil;

public class ShareView extends RelativeLayout implements View.OnClickListener {

    private Drawable showImageDrawble;
    private ImageView showImg;

    private Dialog dialog;
    private RelativeLayout wechatBox,wxcircleBox,qqBox,qqZoneBox;

    public ShareView(Context context) {
        super(context);
        initLayoutView();
    }

    public ShareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public ShareView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShareView);
        showImageDrawble = a.getDrawable(R.styleable.ShareView_shareShowImg);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_share, this);
        showImg = view.findViewById(R.id.showImg);

        if(showImageDrawble != null){
            showImg.setImageDrawable(showImageDrawble);
        }
        showImg.setOnClickListener(v -> {
            selectLocation(getContext());
        });
    }

    /*
     * 选择定位地址弹框
     * */
    public void selectLocation(Context mContext){
        if(dialog == null){
            dialog = new Dialog(mContext, R.style.DialogTheme);
            View view = View.inflate(mContext, R.layout.dialog_share,null);
            dialog.setContentView(view);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.main_menu_animStyle);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

            wechatBox = view.findViewById(R.id.wechatBox);
            wxcircleBox = view.findViewById(R.id.wxcircleBox);
            qqBox = view.findViewById(R.id.qqBox);
            qqZoneBox = view.findViewById(R.id.qqZoneBox);

            wechatBox.setOnClickListener(this);
            wxcircleBox.setOnClickListener(this);
            qqBox.setOnClickListener(this);
            qqZoneBox.setOnClickListener(this);
        }
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wechatBox:
                shareUtil(ShareConstant.SHARE_CHANNEL_WEIXIN_FRIEND);
                break;
            case R.id.wxcircleBox:
                shareUtil(ShareConstant.SHARE_CHANNEL_WEIXIN_CIRCLE);
                break;
            case R.id.qqBox:
                //shareUtil(ShareConstant.SHARE_CHANNEL_QQ);
                break;
            case R.id.qqZoneBox:
                //shareUtil(ShareConstant.SHARE_CHANNEL_QZONE);
                break;
        }
    }


    private void shareUtil(int shareType){
        ShareEntity testBean = new ShareEntity("身边的健康管理专家", "发现同好 默契圈友趣分享 线上私教");
        testBean.setUrl("http://www.hongyuangood.com/app/suidong.html?from=singlemessage&isappinstalled=0"); //分享链接
        testBean.setImgUrl("https://www.baidu.com/img/bd_logo1.png");
        ShareUtil.startShare((Activity) getContext(), shareType, testBean, ShareConstant.REQUEST_CODE);
    }
}
