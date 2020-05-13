package com.hongyuan.fitness.custom_view.add_imgorvideo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.find.circle.edit_post.FileBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.GlideEngine;
import com.hongyuan.fitness.util.ImageFactory;
import com.hongyuan.fitness.util.JumpUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddImageOrVideoView extends LinearLayout {

    private RecyclerView mRecycler;
    private AddImgAdapter adapter;

    //选择图片显示的集合
    private List<FileBean> mList;
    //要上传的文件集合
    private List<FileBean> upMlist;

    //视频路径
    private String videoPath;
    //视频第一帧文件
    private File videoImgFile;

    //图片标识
    public static final int SHOW_IMG = 0X1;
    //视频标识码
    public static final int SHOW_VEDIO = 0X2;
    public int nowType = SHOW_IMG;

    //是否只允许选择图片
    public boolean flag = false;

    public AddImageOrVideoView(Context context) {
        super(context);
        initLayoutView();
    }

    public AddImageOrVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public AddImageOrVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        mList = new ArrayList<>();
        upMlist = new ArrayList<>();
        mList.add(new FileBean());

        View view = View.inflate(getContext(), R.layout.view_add_images, this);
        mRecycler = view.findViewById(R.id.mRecycler);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,24,0x00000000));
        adapter = new AddImgAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener((adapter, view1, position) -> {
            if(view1.getId() == R.id.closeImg){
                if(mList != null && mList.size() > 0){
                    mList.remove(position);
                    if(mList.size() == 0 || mList.get(mList.size() -1).getmFile() != null){
                        mList.add(new FileBean());
                    }
                    adapter.setNewData(mList);
                }
            }else if(view1.getId() == R.id.tvBg){
                if(mList.size() >= 10 ){
                    CustomDialog.showMessage(getContext(),"最多只能上传9张！");
                }else{
                    selectContent();
                }
            }else if(view1.getId() == R.id.contentImg){
                if(nowType == SHOW_VEDIO){
                    Bundle bundle = new Bundle();
                    bundle.putString("videoPath",videoPath);
                    bundle.putString("videoImgPath",videoImgFile.getPath());
                    JumpUtils.goToVideoPlayer((Activity) getContext(), view1,bundle);
                }else{
                    //点击查看大图功能
                    GPreviewBuilder.from((Activity) getContext())
                            .setData(getInfoList())
                            .setCurrentIndex(position)
                            .setType(GPreviewBuilder.IndicatorType.Dot)
                            .start();
                }
            }

        });
    }

    /*
    * 是否有需要上传的文件
    * */
    public boolean isUpload(){
        for(FileBean bean:mList){
            if(bean.getmFile() != null){
                return true;
            }
        }
        return false;
    }

    /*
    * 提供要上传的数据
    * */
    public List<FileBean> getImgList(){
        if(mList.get(0).getmFile() != null && mList.get(0).getFileType().contains("image")){
            nowType = SHOW_IMG;
            return this.mList;
        }else{
            nowType = SHOW_VEDIO;
            return this.upMlist;
        }
    }

    /*
    * 打开选择图片或者视频节目
    * */
    public void selectContent(){
        PictureSelector.create((Activity) getContext())
                .openGallery(flag ? PictureMimeType.ofImage() : PictureMimeType.ofAll())
                .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .maxSelectNum(10-mList.size())// 最大图片选择数量 int
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .previewVideo(true)//是否可预览视频
                .isZoomAnim(true)//图片列表点击 缩放效果 默认true
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /*
     * 获取图片集和图片所处位置
     * */
    private List<UserViewInfo> getInfoList(){
        List<UserViewInfo> imgList = new ArrayList<>();
        for(int i = 0 ; i < mList.size() ; i++){
            if(BaseUtil.isValue(mList.get(i).getmFile())){
                imgList.add(new UserViewInfo(mList.get(i).getmFile().getPath()));
            }else if(BaseUtil.isValue(mList.get(i).getFilePath())){
                imgList.add(new UserViewInfo(mList.get(i).getFilePath()));
            }

            if(BaseUtil.isValue(mList.get(i).getmFile()) || BaseUtil.isValue(mList.get(i).getFilePath())){
                Rect bounds = new Rect();
                adapter.getViewByPosition(mRecycler,i,R.id.contentImg).getGlobalVisibleRect(bounds);
                imgList.get(i).setBounds(bounds);
            }
        }

        return imgList;
    }

    /*
     * 图片选择回调
     * */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                        for (LocalMedia bean:selectList){
                            FileBean imageBean = new FileBean();
                            imageBean.setFileType(bean.getMimeType());
                            if(bean.getMimeType().contains("image")){
                                nowType = SHOW_IMG;
                                videoPath = null;

                                if(bean.isCompressed()){
                                    imageBean.setmFile(new File(bean.getCompressPath()));
                                    imageBean.setFileKey("oss_file[]");
                                }else{
                                    imageBean.setmFile(new File(bean.getPath()));
                                    imageBean.setFileKey("oss_file[]");
                                }

                                if(mList.size() <= 9){
                                    mList.add((mList.size()-1),imageBean);
                                }
                                if(mList.size() == 10){
                                    mList.remove(9);
                                }
                            }else if(bean.getMimeType().contains("video")){
                                nowType = SHOW_VEDIO;
                                videoPath = selectList.get(0).getPath();

                                //选择视频时就需要清空显示集合中的数据
                                mList.clear();
                                //情况上传视频文件集合
                                upMlist.clear();

                                if(bean.isCompressed()){
                                    imageBean.setmFile(new File(bean.getCompressPath()));
                                    imageBean.setFileKey("oss_file[]");
                                }else{
                                    imageBean.setmFile(new File(bean.getPath()));
                                    imageBean.setFileKey("oss_file[]");
                                }

                                //获取视频第一帧图片
                                MediaMetadataRetriever media = new MediaMetadataRetriever();
                                media.setDataSource(selectList.get(0).getPath());// videoPath 本地视频的路径
                                Bitmap vedioBitmap = media.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC );
                                videoImgFile = ImageFactory.saveImageToGallery(getContext(),vedioBitmap);

                                //把第一帧图片添加到显示集合中
                                imageBean.setmFile(videoImgFile);
                                imageBean.setFileKey("oss_file[]");
                                mList.add(imageBean);

                                //把视频第一帧放入上传文件集合中
                                upMlist.add(imageBean);
                                //视频文件
                                FileBean videoFile = new FileBean();
                                videoFile.setmFile(new File(bean.getPath()));
                                videoFile.setFileKey("oss_file[]");
                                upMlist.add(videoFile);

                                if(selectList.size() > 1){
                                    CustomDialog.showMessage(getContext(),"抱歉！视频只能选一个！");
                                }
                                break;
                            }

                        }
                        adapter.setNewData(mList);

                    break;
            }
        }
    }
}
