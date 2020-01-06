package com.hongyuan.fitness.util;

import android.app.Activity;
import android.content.Intent;
import android.media.ExifInterface;
import android.util.Log;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.ui.find.circle.edit_post.FileBean;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectImgUtils {

    public interface SelectionDoneListener{
        void doneListener(List<FileBean> mList,String creatTime);
    }

    private SelectionDoneListener doneListener;

    public void setDoneListener(SelectionDoneListener doneListener){
        this.doneListener = doneListener;
    }

    //选择图片显示的集合
    private List<FileBean> mList = new ArrayList<>();

    /*
     * 打开选择图片或者视频节目
     * */
    public void selectImg(CustomActivity mActivity,int imgNum){
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .maxSelectNum(imgNum)// 最大图片选择数量 int
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .previewVideo(true)//是否可预览视频
                .isZoomAnim(true)//图片列表点击 缩放效果 默认true
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK) {
            mList.clear();
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia bean:selectList){
                        FileBean imageBean = new FileBean();
                        imageBean.setFileType(bean.getPictureType());
                        if(bean.getPictureType().contains("image")){
                            if(bean.isCompressed()){
                                imageBean.setmFile(new File(bean.getCompressPath()));
                                imageBean.setFileKey("oss_file[]");
                            }else{
                                imageBean.setmFile(new File(bean.getPath()));
                                imageBean.setFileKey("oss_file[]");
                            }

                            mList.add(imageBean);
                        }

                    }
                    break;
            }

            if(doneListener != null && mList.size() > 0){
                doneListener.doneListener(mList,getCrateTime(mList.get(0).getmFile()));
            }
        }
    }

    /*
    * 获取文件创建时间
    * */
    public String getCrateTime(File file){
        String watermarkTime = new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date(file.lastModified()));

        return watermarkTime;
    }
}
