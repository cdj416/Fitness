package com.hongyuan.fitness.ui.person.edit_information;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityEditInformationBinding;
import com.hongyuan.fitness.ui.find.circle.edit_post.FileBean;
import com.hongyuan.fitness.ui.main.main_person.PersonBean;
import com.hongyuan.fitness.ui.main.main_person.RetrunImgBean;
import com.hongyuan.fitness.ui.person.edit_information.take_photo.TakePhotoActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BasisTimesUtils;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.GlideEngine;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

public class EditInformationViewModel extends CustomViewModel {

    private ActivityEditInformationBinding binding;
    private PersonBean.DataBean.InfoBean personMessageBeans;
    private  Dialog dialog;

    //生日使用的年月日
    private int birthYear = 1991;
    private int birthMonth = 12;
    private int birthDay = 12;

    //昵称的修改回调
    private String userNameText;
    //个性签名的修改回调
    private String userSignText;
    //真实姓名
    private String actualName;

    public EditInformationViewModel(CustomActivity mActivity, ActivityEditInformationBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        binding.headImgBox.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                upHeadimg();
            }
        });
        binding.modifyNameBox.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                CustomDialog.updateName(mActivity, (v1, message) -> {
                    userNameText = message;
                    sendName(message);
                });
            }
        });
        binding.sexBox.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                CustomDialog.updateSex(mActivity,personMessageBeans.getMi_sex(), (v12, message) -> {
                    sendSex(message);
                });
            }
        });
        binding.shengriBox.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                BasisTimesUtils.showDatePickerDialog(mActivity, BasisTimesUtils.THEME_HOLO_DARK, "请选择年月日", birthYear, birthMonth, birthDay, new BasisTimesUtils.OnDatePickerListener() {

                    @Override
                    public void onConfirm(int year, int month, int dayOfMonth) {
                        birthYear = year;
                        birthMonth = month;
                        birthDay = dayOfMonth;

                        sendBirth(year+"-"+month+"-"+dayOfMonth);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });
        binding.signBox.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                CustomDialog.updateSign(mActivity, (v1, message) -> {
                    userSignText = message;
                    sendSign(message);
                });
            }
        });

        binding.actualNameBox.setOnClickListener(v -> {
            if(!BaseUtil.isValue(personMessageBeans.getMi_realname())){
                CustomDialog.updateName(mActivity, (v1, message) -> {
                    actualName = message;
                    upNmae(message);
                });
            }else{
                CustomDialog.showMessage(mActivity,"不可二次更改！");
            }
        });

    }

    /*
    * 修改个性签名
    * */
    private void sendSign(String sign){
        clearParams().setParams("sign",sign);
        Controller.myRequest(ConstantsCode.UPDATE_MEMBER_SIGN,Constants.UPDATE_MEMBER_SIGN,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
    * 修改昵称
    * */
    private void sendName(String name){
        clearParams().setParams("m_name",name);
        Controller.myRequest(ConstantsCode.UPDATE_MEMBER_NAME,Constants.UPDATE_MEMBER_NAME,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
    * 修改性别请求
    * */
    private void sendSex(String sex){
        clearParams().setParams("sex",sex);
        Controller.myRequest(ConstantsCode.UPDATE_MEMBER_SEX,Constants.UPDATE_MEMBER_SEX,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
    * 修改生日请求
    * */
    private void sendBirth(String birth_date){
        clearParams().setParams("birth_date",birth_date);
        Controller.myRequest(ConstantsCode.UPDATE_MEMBER_BIRTH,Constants.UPDATE_MEMBER_BIRTH,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
    * 修改真实姓名，只能改一次
    * */
    private void upNmae(String mi_realname){
        clearParams().setParams("mi_realname",mi_realname)
                .setParams("mi_sex",String.valueOf(personMessageBeans.getMi_sex()))
                .setParams("mi_birth",personMessageBeans.getBirth());
        Controller.myRequest(ConstantsCode.UPDATE_M_INFO,Constants.UPDATE_M_INFO,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
     * 更换头像
     * */
    public void upHeadimg(){
        if(dialog == null){
            dialog = new Dialog(mActivity, R.style.DialogTheme);
            View view = View.inflate(mActivity, R.layout.dialog_updata_header,null);
            dialog.setContentView(view);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.bottom_in_out);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.show();

            TextView camera = view.findViewById(R.id.camera);
            TextView Album = view.findViewById(R.id.Album);

            camera.setOnClickListener(v -> {
                openSection();
            });
            Album.setOnClickListener(v -> {
                openSection();
            });
            view.findViewById(R.id.closeDialog).setOnClickListener(v -> dialog.dismiss());
        }else{
            dialog.show();
        }

    }

    /*
     * 打开相机还是相册
     * */
    private void openSection(){
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .isZoomAnim(true)//图片列表点击 缩放效果 默认true
                .forResult(PictureConfig.CHOOSE_REQUEST);
        dialog.dismiss();
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
                    Glide.with(mActivity).load(selectList.get(0).getCompressPath()).into(binding.headImg);

                    if(selectList.get(0).getCompressPath() != null){
                        shangChuan(new File(selectList.get(0).getCompressPath()));
                    }
                    break;
            }
        }
    }

    /*
    * 上传头像文件
    * */
    public void shangChuan(File imgFile) {
        clearParams().setParams("type","1");
        FileBean fileBean = new FileBean();
        fileBean.setFileKey("oss_file");
        fileBean.setmFile(imgFile);
        Controller.myRequest(Constants.UPFILE_OSS,Controller.TYPE_POST,getParams(),fileBean, RetrunImgBean.class,this);
    }


    /*
     * 修改头像
     * */
    public void upHeand(String headImg){
        clearParams().setParams("head_src",headImg);
        Controller.myRequest(Constants.UPDATE_MEMBER_HEAD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    protected void setData() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mActivity).load(personMessageBeans.getMi_head()).apply(options).into(binding.headImg);
        binding.userName.setText(personMessageBeans.getM_name());
        binding.actualName.setText(personMessageBeans.getMi_realname()+"");
        binding.userSignature.setText(personMessageBeans.getMi_sign());
        binding.birthday.setText(personMessageBeans.getBirth());
        binding.addressView.setShowAddress(this,personMessageBeans.getArea());

        if(personMessageBeans.getMi_sex() == 1){
            binding.sex.setText("男");
        }else{
            binding.sex.setText("女");
        }
        if(BaseUtil.isValue(personMessageBeans.getMi_face())){
            binding.faceText.setText("已录入");
        }else{
            binding.faceText.setText("未录入");
            //弹出拍照提示框
            binding.takePhoto.setOnClickListener(v -> CustomDialog.showTakePhoto(mActivity, v13 -> {
                startActivityForResult(TakePhotoActivity.class,null);
            }));
        }

        try {
            //初始化dialog显示的数据
            birthYear = Integer.valueOf(personMessageBeans.getBirth().substring(0,4));
            birthMonth = Integer.valueOf(personMessageBeans.getBirth().substring(5,7));
            birthDay = Integer.valueOf(personMessageBeans.getBirth().substring(8));
        }catch (Exception e){
            e.printStackTrace();
        }

        //判断是否显示红点
        if(!BaseUtil.isValue(personMessageBeans.getMi_head())){
            binding.noChange1.setVisibility(View.VISIBLE);
        }else{
            binding.noChange1.setVisibility(View.GONE);
        }
        if(personMessageBeans.getIs_index_name() == 1){
            binding.noChange2.setVisibility(View.VISIBLE);
        }else{
            binding.noChange2.setVisibility(View.GONE);
        }
        if(!BaseUtil.isValue(personMessageBeans.getBirth())){
            binding.noChange5.setVisibility(View.VISIBLE);
        }else{
            binding.noChange5.setVisibility(View.GONE);
        }
        if(personMessageBeans.getMi_sex() == 0){
            binding.noChange4.setVisibility(View.VISIBLE);
        }else{
            binding.noChange4.setVisibility(View.GONE);
        }

    }

    @Override
    public void forResult(Bundle bundle) {
        boolean isSuccess = bundle.getBoolean("isSuccess");
        if(isSuccess){
            binding.takePhoto.setClickable(false);
            binding.faceText.setText("已录入");
        }

    }

    @Override
    protected void lazyLoad() {
        Controller.myRequest(Constants.GET_MEMBER_INDEX_INFO,Controller.TYPE_POST,getParams(), PersonBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof RetrunImgBean){
            RetrunImgBean imgBean = (RetrunImgBean)data;
            upHeand(imgBean.getData().getFile_url());
        }

        if(data instanceof PersonBean){
            personMessageBeans = ((PersonBean)data).getData().getInfo();
            setData();
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        if(code == ConstantsCode.UPDATE_MEMBER_SEX){
            showSuccess("性别修改成功！");
            if(personMessageBeans.getMi_sex() == 1){
                personMessageBeans.setMi_sex(2);
                binding.sex.setText("女");
            }else{
                personMessageBeans.setMi_sex(1);
                binding.sex.setText("男");
            }
        }
        if(code == ConstantsCode.UPDATE_MEMBER_BIRTH){
            showSuccess("修改生日成功！");
            binding.birthday.setText(birthYear+"-"+birthMonth+"-"+birthDay);
        }
        if(code == ConstantsCode.UPDATE_MEMBER_NAME){
            showSuccess("昵称修改成功！");
            binding.userName.setText(userNameText);
        }
        if(code == ConstantsCode.UPDATE_MEMBER_SIGN){
            showSuccess("个性签名修改成功！");
            binding.userSignature.setText(userSignText);
        }

        if(code == ConstantsCode.UPDATE_M_INFO){
            showSuccess("姓名修改成功！");
            binding.actualName.setText(actualName);
        }
    }

}
