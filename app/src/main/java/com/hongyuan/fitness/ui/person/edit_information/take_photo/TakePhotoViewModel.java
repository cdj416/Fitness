package com.hongyuan.fitness.ui.person.edit_information.take_photo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.camera_view.CameraStateListener;
import com.hongyuan.fitness.databinding.ActivityTakePhotoBinding;
import com.hongyuan.fitness.ui.find.circle.edit_post.FileBean;
import com.hongyuan.fitness.ui.main.main_person.RetrunImgBean;
import com.hongyuan.fitness.util.FileSizeUtil;
import com.hongyuan.fitness.util.ImageFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

import static com.hongyuan.fitness.custom_view.camera_view.Constants.CAMERA_FACING_BACK;
import static com.hongyuan.fitness.custom_view.camera_view.Constants.CAMERA_FACING_FRONT;

public class TakePhotoViewModel extends CustomViewModel implements CameraStateListener {

    private ActivityTakePhotoBinding binding;
    private static final int REQUEST_CAMERA_PERMISSION = 100;

    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;


    private int mFacing = CAMERA_FACING_FRONT;

    //要上传的图片bitmap
    private Bitmap useBitmap;


    public TakePhotoViewModel(CustomActivity mActivity, ActivityTakePhotoBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {

        binding.mTextureView.setListener(this);

        binding.btnTakePhoto.setOnClickListener(v -> {
            binding.mTextureView.takePicture();
        });

        binding.openLight.setOnClickListener(v -> {

        });
        binding.switchImg.setOnClickListener(v -> {
            if (mFacing == CAMERA_FACING_FRONT){
                mFacing = CAMERA_FACING_BACK;
            } else {
                mFacing = CAMERA_FACING_FRONT;
            }
            binding.mTextureView.setCameraFacing(mFacing);
        });

        binding.reStart.setOnClickListener(v -> {
            binding.takeBox.setVisibility(View.VISIBLE);
           binding.previewBox.setVisibility(View.VISIBLE);
            binding.operatingBox.setVisibility(View.GONE);
            binding.showImg.setVisibility(View.GONE);
        });

        binding.useImg.setOnClickListener(v -> {
            if(useBitmap != null){
                //File useFile = getUseFile(useBitmap);
                //压缩图片
                File useFile = ImageFactory.compressImage(useBitmap);
                shangChuanImg(useFile);
            }

        });
    }


    /*
    * 获取相应的文件
    * */
    private File getUseFile(Bitmap bitmap){
        File file = new File(mActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "picture.jpg");
        try {
            OutputStream os = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);//将图片压缩的流里面
            os.flush();
            os.close();
            bitmap.recycle();
            return file;
        } catch (IOException e) {
            Log.e("cdj", "图像文件写入失败： " + file, e);
        }

        return null;
    }

    /*
    * 上传照片
    * */
    private void shangChuanImg(File useFile){
        Log.e("cdj","===========图片大小======"+ FileSizeUtil.getFileOrFilesSize(useFile.getPath(),2));
        mActivity.showLoading();
        if(useFile != null){
            FileBean fileBean = new FileBean();
            fileBean.setFileKey("oss_file");
            fileBean.setmFile(useFile);
            clearParams().setParams("type","1");
            //上传单文件
            Controller.myRequest(Constants.UPFILE_OSS,Controller.TYPE_POST,getParams(),fileBean, RetrunImgBean.class,this);
        }
    }

    /*
    * 修改用户照片
    * */
    private void updataImg(String fileUrl){
        clearParams().setParams("face",fileUrl);
        Controller.myRequest(ConstantsCode.UPDATE_MEMBER_FACE,Constants.UPDATE_MEMBER_FACE,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        if(data instanceof RetrunImgBean){
            RetrunImgBean imgBean = (RetrunImgBean)data;
            updataImg(imgBean.getData().getFile_url());
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        if(code == ConstantsCode.UPDATE_MEMBER_FACE){
            Bundle bundle = new Bundle();
            bundle.putBoolean("isSuccess",true);
            setResult(bundle);
        }
    }

    @Override
    public void onCameraOpend() {

    }

    @Override
    public void onPreviewStart() {

    }

    @Override
    public void onPreviewStop() {

    }

    @Override
    public void onShutter() {

    }

    @Override
    public void onCupture(Bitmap bitmap) {
        useBitmap = bitmap;

        binding.showImg.setVisibility(View.VISIBLE);
        binding.operatingBox.setVisibility(View.VISIBLE);
        binding.previewBox.setVisibility(View.GONE);
        binding.takeBox.setVisibility(View.GONE);
        Glide.with(mActivity).load(bitmap).into(binding.showImg);
    }

    @Override
    public void onCut(File file) {

    }

    @Override
    public void onCameraClosed() {

    }

    public void onDestory(){
        if (mBackgroundThread == null){
            return;
        }
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            Log.e("cdj", "close: 后台线程关闭失败：", e);
        }
    }
}
