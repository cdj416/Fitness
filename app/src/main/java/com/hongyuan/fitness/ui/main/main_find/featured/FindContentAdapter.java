package com.hongyuan.fitness.ui.main.main_find.featured;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.nine_gridimg.NineGridImageView;
import com.hongyuan.fitness.custom_view.nine_gridimg.NineGridImageViewAdapter;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.ViewChangeUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;

import java.util.ArrayList;
import java.util.List;

public class FindContentAdapter extends BaseQuickAdapter<FeatureBean.DataBean.ListBean, BaseViewHolder> {

    public FindContentAdapter(){
        super(R.layout.item_find_content);
    }

    @Override
    protected void convert(final BaseViewHolder helper, FeatureBean.DataBean.ListBean item) {

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.fName,item.getM_name()).setText(R.id.address,"")
                .setText(R.id.postContent,item.getCircle_content())
                .setText(R.id.timeAfter, TimeUtil.friendly_time(item.getAdd_date()))
                .setText(R.id.tvLike,String.valueOf(item.getPraise_num()))
                .setText(R.id.tvComment,String.valueOf(item.getReview_num()));

        if(item.getIs_praise() == 0){
            ViewChangeUtil.changeLeftDrawable(mContext,helper.getView(R.id.tvLike),R.mipmap.like_huise_img);
        }else{
            ViewChangeUtil.changeLeftDrawable(mContext,helper.getView(R.id.tvLike),R.mipmap.like_chengse_img);
        }

        if(item.getCi() != null && item.getCi().size() > 0 && item.getCi().get(0).getCircle_type() == 2){
            helper.getView(R.id.videoBox).setVisibility(View.VISIBLE);
            helper.getView(R.id.nineGridImg).setVisibility(View.GONE);
            helper.addOnClickListener(R.id.videoBox);
            Glide.with(mContext).load(item.getCircle_img()).into((RoundedImageView)helper.getView(R.id.videoImg));
        }else if(item.getCi() != null && item.getCi().size() > 0 && item.getCi().get(0).getCircle_type() == 1){
            helper.getView(R.id.videoBox).setVisibility(View.GONE);
            helper.getView(R.id.nineGridImg).setVisibility(View.VISIBLE);
            NineGridImageView nineGridImageView = helper.getView(R.id.nineGridImg);
            nineGridImageView.setAdapter(mAdapter);
            nineGridImageView.setImagesData(item.getCi());
        }else{
            helper.getView(R.id.videoBox).setVisibility(View.GONE);
            helper.getView(R.id.nineGridImg).setVisibility(View.GONE);
        }


        helper.addOnClickListener(R.id.jumpDetails).addOnClickListener(R.id.tvLike).addOnClickListener(R.id.attention);

    }

    /*
    * 九宫格适配器
    * */
    private NineGridImageViewAdapter<FeatureBean.DataBean.ListBean.CiBean> mAdapter = new NineGridImageViewAdapter<FeatureBean.DataBean.ListBean.CiBean>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, FeatureBean.DataBean.ListBean.CiBean s) {
            if(s.getCircle_type() == 1){
                RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_testbaner3).error(R.mipmap.a_testbaner3).centerCrop();
                Glide.with(mContext).load(s.getFile_src()).apply(options).into(imageView);
            }
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context context, List<ImageView> mImageViewList, ImageView imageView, int index, List<FeatureBean.DataBean.ListBean.CiBean> list) {

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
    private List<UserViewInfo> getInfoList(List<FeatureBean.DataBean.ListBean.CiBean> list,List<ImageView> mImageViewList){
        List<UserViewInfo> imgList = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            imgList.add(new UserViewInfo(list.get(i).getFile_src()));
            Rect bounds = new Rect();
            mImageViewList.get(i).getGlobalVisibleRect(bounds);
            imgList.get(i).setBounds(bounds);
        }

        return imgList;
    }

}
