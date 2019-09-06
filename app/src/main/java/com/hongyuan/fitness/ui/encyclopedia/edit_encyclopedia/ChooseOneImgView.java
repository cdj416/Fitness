package com.hongyuan.fitness.ui.encyclopedia.edit_encyclopedia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.util.List;

public class ChooseOneImgView extends LinearLayout {

    private RoundedImageView img;

    //图片标识
    public static final int SINGLE_IMG = 0X1;

    //选择的图片文件
    private File imgFile;

    public ChooseOneImgView(Context context) {
        super(context);
        initLayoutView();
    }

    public ChooseOneImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public ChooseOneImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_chonse_one_img, this);
        img = view.findViewById(R.id.img);
    }

    /*
    * 打开选择图片或者视频节目
    * */
    public void selectContent(){
        PictureSelector.create((Activity) getContext())
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .isZoomAnim(true)//图片列表点击 缩放效果 默认true
                .forResult(SINGLE_IMG);
    }

    /*
     * 图片选择回调
     * */
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SINGLE_IMG:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if(selectList.get(0).getCompressPath() != null){
                        imgFile = new File(selectList.get(0).getCompressPath());
                        RequestOptions options = new RequestOptions().error(R.mipmap.a_testbaner3);
                        Glide.with(getContext()).load(imgFile).apply(options).into(img);
                    }
                    break;
            }
        }
    }

    public File getImgFile() {
        return imgFile;
    }
}
