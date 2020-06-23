package com.hongyuan.fitness.ui.shop.sadapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.nine_gridimg.NineGridImageView;
import com.hongyuan.fitness.custom_view.nine_gridimg.NineGridImageViewAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.ShopCommentBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;

import java.util.ArrayList;
import java.util.List;

public class SGDCommentsAdapter extends BaseQuickAdapter<ShopCommentBeans.DataBean.ListBean, BaseViewHolder> {

    public SGDCommentsAdapter(){
        super(R.layout.item_sgd_comments);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCommentBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));
        helper.setText(R.id.userName,item.getM_name())
                .setText(R.id.commentContent, BaseUtil.isValue(item.getEvaluation_content()) ? item.getEvaluation_content() : "此用户没有填写评论！")
                .setText(R.id.commentTime, TimeUtil.friendly_time(item.getEvaluation_date()))
                .setText(R.id.barText,getBarString(Float.valueOf(item.getEvaluation_score())));
        RatingBar ratingBar = helper.getView(R.id.myRat);
        ratingBar.setRating(Float.valueOf(item.getEvaluation_score()));


        if(item.getEvaluation_img() != null && item.getEvaluation_img().size() > 0){
            helper.getView(R.id.nineGridImg).setVisibility(View.VISIBLE);
            NineGridImageView nineGridImageView = helper.getView(R.id.nineGridImg);
            nineGridImageView.setAdapter(mAdapter);
            nineGridImageView.setImagesData(item.getEvaluation_img());
        }else{
            helper.getView(R.id.nineGridImg).setVisibility(View.GONE);
        }
    }

    /*
    * 根据星级返回相应的显示字段
    * */
    private String getBarString(float bar){

        if(bar <= 1){
            return "不满意";
        }
        if(bar > 1 && bar <=2){
            return "一般";
        }
        if(bar > 2 && bar <=3){
            return "满意";
        }
        if(bar > 3 && bar <=4){
            return "非常满意";
        }
        if(bar >= 5){
            return "超出预期";
        }
        return "超出预期";
    }


    /*
     * 九宫格适配器
     * */
    private NineGridImageViewAdapter<ShopCommentBeans.DataBean.ListBean.EvaluationImgBean> mAdapter = new NineGridImageViewAdapter<ShopCommentBeans.DataBean.ListBean.EvaluationImgBean>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, ShopCommentBeans.DataBean.ListBean.EvaluationImgBean s) {
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img).centerCrop();
            Glide.with(mContext).load(s.getImg_url()).apply(options).into(imageView);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context context, List<ImageView> mImageViewList, ImageView imageView, int index, List<ShopCommentBeans.DataBean.ListBean.EvaluationImgBean> list) {

            //点击查看大图功能
            GPreviewBuilder.from((Activity) context)
                    .setData(getInfoList(list,mImageViewList))
                    .setCurrentIndex(index)
                    .setType(GPreviewBuilder.IndicatorType.Dot)
                    .start();
        }
    };

    /*
     * 获取图片集和图片所处位置
     * */
    private List<UserViewInfo> getInfoList(List<ShopCommentBeans.DataBean.ListBean.EvaluationImgBean> list,List<ImageView> mImageViewList){
        List<UserViewInfo> imgList = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            imgList.add(new UserViewInfo(list.get(i).getImg_url()));
            Rect bounds = new Rect();
            mImageViewList.get(i).getGlobalVisibleRect(bounds);
            imgList.get(i).setBounds(bounds);
        }

        return imgList;
    }
}
