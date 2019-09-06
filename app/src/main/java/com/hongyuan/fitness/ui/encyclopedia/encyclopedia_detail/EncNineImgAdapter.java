package com.hongyuan.fitness.ui.encyclopedia.encyclopedia_detail;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.nine_gridimg.NineGridImageViewAdapter;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;

import java.util.ArrayList;
import java.util.List;

public class EncNineImgAdapter extends NineGridImageViewAdapter<EncyclopediaDetailBean.DataBean.BiBean> {
    @Override
    protected void onDisplayImage(Context context, ImageView imageView, EncyclopediaDetailBean.DataBean.BiBean s) {
        if(s.getBaike_type() == 1){
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_testbaner3).error(R.mipmap.a_testbaner3).centerCrop();
            Glide.with(context).load(s.getFile_src()).apply(options).into(imageView);
        }
    }

    @Override
    protected ImageView generateImageView(Context context) {
        return super.generateImageView(context);
    }

    @Override
    protected void onItemImageClick(Context context, List<ImageView> mImageViewList, ImageView imageView, int index, List<EncyclopediaDetailBean.DataBean.BiBean> list) {
        //点击查看大图功能
        GPreviewBuilder.from((Activity) context)
                .setData(getInfoList(list,mImageViewList))
                .setCurrentIndex(index)
                .setType(GPreviewBuilder.IndicatorType.Dot)
                .start();
    }

    /*
     * 获取图片集和图片所处位置
     * */
    private List<UserViewInfo> getInfoList(List<EncyclopediaDetailBean.DataBean.BiBean> list, List<ImageView> mImageViewList){
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
