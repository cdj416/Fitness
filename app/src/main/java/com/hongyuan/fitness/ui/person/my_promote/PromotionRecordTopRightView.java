package com.hongyuan.fitness.ui.person.my_promote;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.custom_view.share_view.ShareUtil;
import com.hongyuan.fitness.ui.person.my_promote.promote_record.PromotionRecordActivity;

public class PromotionRecordTopRightView extends LinearLayout {

    private CustomActivity mActivity;
    private PromotionCodeBeans.DataBean.InfoBean infoBean;

    private ImageView recordImg,shareImg;

    public PromotionRecordTopRightView(CustomActivity mActivity,PromotionCodeBeans.DataBean.InfoBean infoBean){
        super(mActivity);
        this.mActivity = mActivity;
        this.infoBean = infoBean;

        initLayoutView();
    }


    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_promotion_code_top_right, this);
        recordImg = view.findViewById(R.id.recordImg);
        shareImg = view.findViewById(R.id.shareImg);

        recordImg.setOnClickListener(v -> mActivity.startActivity(PromotionRecordActivity.class));
        shareImg.setOnClickListener(v -> {
            ShareUtil.setQrShare(infoBean.getOss_url());
            ShareUtil.showShare(mActivity);
        });
    }

}
