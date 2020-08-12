package com.hongyuan.fitness.ui.shop.smyview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.RetrofitListener;
import com.hongyuan.fitness.ui.main.main_person.RetrunImgBean;
import com.hongyuan.fitness.ui.shop.sinterface.SwitchLinstener;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.GlideEngine;
import com.hongyuan.fitness.util.SkinConstants;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.makeramen.roundedimageview.RoundedImageView;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;

import java.io.File;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ScheckInView extends LinearLayout implements RetrofitListener {

    private CustomActivity mContext;
    private CustomViewModel viewModel;

    private RelativeLayout etsBox,img1box,img2box,img3box;
    private LinearLayout imgsBox;
    private TextView titleName,useValue,selText,imgName,upTv1,upTv2,upTv3;
    private ImageView closeImg3,closeImg2,closeImg1,passwordImg;
    private RoundedImageView showImg1,showImg2,showImg3;
    private EditText edText;
    private Switch twSwitch;
    private View line;


    //控制类型
    private String sciEtsTitleName,sciEtsHintText,sciImgTitleName,sciImgFshowName,sciImgSshowName,sciImgTshowName;
    private int sciImgShowNum,sciType,sciInputType,sciTag;
    private boolean sciLineShow;

    //功能需要的
    private boolean passwordShow = false;

    //图片类型需要存储的三个图片路径字段
    private String imgUrl1,imgUrl2,imgUrl3;

    //三个常亮标识点击的三个图片框
    private final int FIRSET_IMG = 0X01;
    private final int SECOND_IMG = 0X02;
    private final int THIRD_IMG = 0X03;

    //当前点击的是第几张图片
    private int nowCheck = FIRSET_IMG;

    //发送给服务器需要的值
    private String useValues;

    public ScheckInView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if(context instanceof CustomActivity){
            mContext = (CustomActivity) context;
        }

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShopCheckInView);
        sciEtsTitleName = a.getString(R.styleable.ShopCheckInView_sciEtsTitleName);
        sciEtsHintText = a.getString(R.styleable.ShopCheckInView_sciEtsHintText);
        sciImgTitleName = a.getString(R.styleable.ShopCheckInView_sciImgTitleName);
        sciImgFshowName = a.getString(R.styleable.ShopCheckInView_sciImgFshowName);
        sciImgSshowName = a.getString(R.styleable.ShopCheckInView_sciImgSshowName);
        sciImgTshowName = a.getString(R.styleable.ShopCheckInView_sciImgTshowName);

        sciImgShowNum = a.getInt(R.styleable.ShopCheckInView_sciImgShowNum,0);
        sciType = a.getInt(R.styleable.ShopCheckInView_sciType,0);
        sciInputType = a.getInt(R.styleable.ShopCheckInView_sciInputType,0);
        sciTag = a.getInt(R.styleable.ShopCheckInView_sciTag,0);

        sciLineShow = a.getBoolean(R.styleable.ShopCheckInView_sciLineShow,true);

        initLayoutView();
    }


    //初始化控件
    private void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_shop_checkin, this);

        etsBox = view.findViewById(R.id.etsBox);
        imgsBox = view.findViewById(R.id.imgsBox);
        img1box = view.findViewById(R.id.img1box);
        img2box = view.findViewById(R.id.img2box);
        img3box = view.findViewById(R.id.img3box);
        titleName = view.findViewById(R.id.titleName);
        useValue = view.findViewById(R.id.useValue);
        selText = view.findViewById(R.id.selText);
        imgName = view.findViewById(R.id.imgName);
        upTv1 = view.findViewById(R.id.upTv1);
        upTv2 = view.findViewById(R.id.upTv2);
        upTv3 = view.findViewById(R.id.upTv3);
        closeImg3 = view.findViewById(R.id.closeImg3);
        closeImg2 = view.findViewById(R.id.closeImg2);
        closeImg1 = view.findViewById(R.id.closeImg1);
        showImg1 = view.findViewById(R.id.showImg1);
        showImg2 = view.findViewById(R.id.showImg2);
        showImg3 = view.findViewById(R.id.showImg3);
        passwordImg = view.findViewById(R.id.passwordImg);
        edText = view.findViewById(R.id.edText);
        twSwitch = view.findViewById(R.id.twSwitch);


        line = view.findViewById(R.id.line);

        //设置显示类型
        setShowType(sciType);

    }


    /*
    * 设置显示类型
    * */
    public void setShowType(int type){
        if(type == 0){
            etsBox.setVisibility(VISIBLE);
            imgsBox.setVisibility(GONE);

            edText.setVisibility(VISIBLE);
            selText.setVisibility(GONE);
            twSwitch.setVisibility(GONE);
            passwordImg.setVisibility(GONE);
        }

        if(type == 1){
            etsBox.setVisibility(VISIBLE);
            imgsBox.setVisibility(GONE);

            edText.setVisibility(GONE);
            selText.setVisibility(VISIBLE);
            twSwitch.setVisibility(GONE);
            passwordImg.setVisibility(GONE);
        }

        if(type == 2){
            etsBox.setVisibility(VISIBLE);
            imgsBox.setVisibility(GONE);

            edText.setVisibility(GONE);
            selText.setVisibility(GONE);
            twSwitch.setVisibility(VISIBLE);
            passwordImg.setVisibility(GONE);
        }

        if(type == 3){
            etsBox.setVisibility(GONE);
            imgsBox.setVisibility(VISIBLE);
        }

        if(type == 4){
            etsBox.setVisibility(VISIBLE);
            imgsBox.setVisibility(GONE);

            edText.setVisibility(VISIBLE);
            selText.setVisibility(GONE);
            twSwitch.setVisibility(GONE);
            passwordImg.setVisibility(VISIBLE);
        }

        //当类型为0,1,2,4时需要设置文字类型标题
        if(type == 0 || type == 1 || type == 2 || type == 4){
            setTitleName(sciEtsTitleName);
        }

        //当类型为3时需要设置图片类型标题
        if(type == 3){
            setImgsTitleName(sciImgTitleName);
        }

        //设置提示语
        if(BaseUtil.isValue(sciEtsHintText)){
            setHintText(sciEtsHintText);
        }

        //设置图片显示框中的提示语
        if(BaseUtil.isValue(sciImgFshowName))setImgFshowName(sciImgFshowName);
        if(BaseUtil.isValue(sciImgSshowName))setImgSshowName(sciImgSshowName);
        if(BaseUtil.isValue(sciImgTshowName))setImgTshowName(sciImgTshowName);

        //设置图片展示的个数
        setShowImgNum(sciImgShowNum);

        //设置输入法显示类型
        setInputType(sciInputType);

        //是否显示底部线
        setShowLine(sciLineShow);

        //设置选择图片点击事件
        upTv1.setOnClickListener(v -> oppenImgs(FIRSET_IMG));
        upTv2.setOnClickListener(v -> oppenImgs(SECOND_IMG));
        upTv3.setOnClickListener(v -> oppenImgs(THIRD_IMG));

        //设置页面删除图片点击事件
        closeImg1.setOnClickListener(v -> {

            if(mContext.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                showImg1.setImageResource(R.color.color_FFFFFF);
            }
            if(mContext.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                showImg1.setImageResource(R.color.theme_color3);
            }

            closeImg1.setVisibility(GONE);
            upTv1.setVisibility(VISIBLE);
            imgUrl1 = null;
        });
        closeImg2.setOnClickListener(v -> {

            if(mContext.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                showImg2.setImageResource(R.color.color_FFFFFF);
            }
            if(mContext.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                showImg2.setImageResource(R.color.theme_color3);
            }

            closeImg2.setVisibility(GONE);
            upTv2.setVisibility(VISIBLE);
            imgUrl2 = null;
        });
        closeImg3.setOnClickListener(v -> {

            if(mContext.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                showImg3.setImageResource(R.color.color_FFFFFF);
            }
            if(mContext.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                showImg3.setImageResource(R.color.theme_color3);
            }

            closeImg3.setVisibility(GONE);
            upTv3.setVisibility(VISIBLE);
            imgUrl3 = null;
        });

        //点击图片查看大图
        showImg1.setOnClickListener(v -> {
            if(BaseUtil.isValue(imgUrl1)){
                lookBigImg(imgUrl1,(ImageView) v);
            }
        });
        showImg2.setOnClickListener(v -> {
            if(BaseUtil.isValue(imgUrl2)){
                lookBigImg(imgUrl2,(ImageView) v);
            }
        });
        showImg3.setOnClickListener(v -> {
            if(BaseUtil.isValue(imgUrl3)){
                lookBigImg(imgUrl3,(ImageView) v);
            }
        });

        //明文非明文控制
        passwordImg.setOnClickListener(v ->pwdIsVisible(passwordShow));
    }

    /*
    * 设置文字类型标题
    * */
    private void setTitleName(String content){
        titleName.setText(content);
    }

    /*
    * 设置图片类型标题
    * */
    private void setImgsTitleName(String content){
        imgName.setText(content);
    }

    /*
    * 设置提示语
    * */
    private void setHintText(String content){
        edText.setHint(content);
        selText.setHint(content);
    }

    /*
    * 设置第一张图片显示框中的提示语
    * */
    private void setImgFshowName(String content){
        upTv1.setText(content);
    }

    /*
    * 设置第二张图片显示框中的提示语
    * */
    private void setImgSshowName(String content){
        upTv2.setText(content);
    }

    /*
    * 设置第三张图片显示框中的提示语
    * */
    private void setImgTshowName(String content){
        upTv3.setText(content);
    }


    /*
    * 控制输入法类型
    * */
    private void setInputType(int inputType){
        if(inputType == 0) edText.setInputType(InputType.TYPE_CLASS_TEXT);
        if(inputType == 1) edText.setInputType(InputType.TYPE_CLASS_NUMBER);
        if(inputType == 2) edText.setInputType(InputType.TYPE_CLASS_PHONE);
        if(inputType == 3) edText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);

        if(inputType == 4){
            edText.setKeyListener(new DigitsKeyListener() {// pwdEditText 为 EditText 控件
                @Override
                public int getInputType() {
                    return InputType.TYPE_TEXT_VARIATION_PASSWORD;
                }
                @Override
                protected char[] getAcceptedChars() {
                    char[] data = getResources().getString(R.string.login_only_can_input).toCharArray();
                    return data;
                }
            });
        }
    }

    /*
     * 是否显示底部线条
     * */
    private void setShowLine(boolean showLine){
        if(showLine){
            line.setVisibility(VISIBLE);
        }else{
            line.setVisibility(GONE);
        }
    }

    /*
    * 打开相册选择要上传的图片
    * */
    private void oppenImgs(int code){
        nowCheck = code;

        PictureSelector.create((Activity) getContext())
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .isZoomAnim(true)//图片列表点击 缩放效果 默认true
                .forResult(sciTag);
    }

    /*
    * 点击图片查看大图
    * */
    private void lookBigImg(String imgUrl,ImageView imageView){
        Rect bounds = new Rect();
        imageView.getGlobalVisibleRect(bounds);
        UserViewInfo viewInfo = new UserViewInfo(imgUrl);
        viewInfo.setBounds(bounds);
        //点击查看大图功能
        GPreviewBuilder.from((Activity) getContext())
                .setSingleData(viewInfo)
                .setCurrentIndex(0)
                .setType(GPreviewBuilder.IndicatorType.Dot)
                .start();
    }

    /*
     * 设置swicth的点击回调函数
     * */
    public void setSwicthListener(SwitchLinstener swicthListener){
        twSwitch.setOnClickListener(v -> swicthListener.switchLinstener(twSwitch.isChecked()));
    }

    /*
    * 设置viewmode
    * */
    public void setViewModel(CustomViewModel viewModel){
        this.viewModel = viewModel;
    }

    /*
     * 设置显示图片框的个数
     * */
    public void setShowImgNum(int num){
        if(num == 1){
            img1box.setVisibility(VISIBLE);
            img2box.setVisibility(GONE);
            img3box.setVisibility(GONE);
        }
        if(num == 2){
            img1box.setVisibility(VISIBLE);
            img2box.setVisibility(VISIBLE);
            img3box.setVisibility(GONE);
        }
        if(num == 3){
            img1box.setVisibility(VISIBLE);
            img2box.setVisibility(VISIBLE);
            img3box.setVisibility(VISIBLE);
        }
    }

    /*
    * 图片选择结果集回调
    * */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            if(requestCode == sciTag){
                images = PictureSelector.obtainMultipleResult(data);
                if (nowCheck == FIRSET_IMG) {// 图片选择结果回调
                    imgUrl1 = images.get(0).getCompressPath();
                    Glide.with(getContext()).load(images.get(0).getCompressPath()).into(showImg1);
                    closeImg1.setVisibility(VISIBLE);
                    upTv1.setVisibility(GONE);

                    //去上传图片
                    uploadImg(imgUrl1);
                }
                if (nowCheck == SECOND_IMG) {// 图片选择结果回调
                    imgUrl2 = images.get(0).getCompressPath();
                    Glide.with(getContext()).load(images.get(0).getCompressPath()).into(showImg2);
                    closeImg2.setVisibility(VISIBLE);
                    upTv2.setVisibility(GONE);

                    //去上传图片
                    uploadImg(imgUrl2);
                }
                if (nowCheck == THIRD_IMG) {// 图片选择结果回调
                    imgUrl3 = images.get(0).getCompressPath();
                    Glide.with(getContext()).load(images.get(0).getCompressPath()).into(showImg3);
                    closeImg3.setVisibility(VISIBLE);
                    upTv3.setVisibility(GONE);

                    //去上传图片
                    uploadImg(imgUrl3);
                }
            }
        }
    }

    /*
     * 明文密码和非明文密码的切换
     * */
    private void pwdIsVisible(boolean isShow){
        if(!isShow){
            passwordShow = true;

            if(mContext.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                passwordImg.setImageResource(R.mipmap.vtwo_login_eye_outline_img_);
            }
            if(mContext.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                passwordImg.setImageResource(R.mipmap.vtwo_login_eye_outline_img_black);
            }

            edText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            Editable etext = edText.getText();
            Selection.setSelection(etext, etext.length());
        }else{
            passwordShow = false;

            if(mContext.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                passwordImg.setImageResource(R.mipmap.vtwo_login_eye_off_img_);
            }
            if(mContext.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                passwordImg.setImageResource(R.mipmap.vtwo_login_eye_off_img__black);
            }
            edText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            Editable etext = edText.getText();
            Selection.setSelection(etext, etext.length());
        }
    }

    /*
     * 获取tag值，sciTag是标识当前点击的哪一个
     * */
    public int getSciTag(){
        return this.sciTag;
    }

    /*******************************************文件上传******************************************/

    /*
    * 上传选中的图片
    * */
    public void uploadImg(String imgUrl){
        viewModel.clearParams().setParams("type","1");
        Controller.myRequest(Constants.UPFILE_OSS,Controller.TYPE_POST,viewModel.getParams(),viewModel.getFileBean("oss_file",new File(imgUrl)), RetrunImgBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof RetrunImgBean){
            RetrunImgBean imgBean = (RetrunImgBean)data;
            if(nowCheck == FIRSET_IMG){
                imgUrl1 = imgBean.getData().getFile_url();
            }
            if(nowCheck == SECOND_IMG){
                imgUrl2 = imgBean.getData().getFile_url();
            }
            if(nowCheck == THIRD_IMG){
                imgUrl3 = imgBean.getData().getFile_url();
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {

    }

    @Override
    public void onError(int error_code, String description) {

    }

    @Override
    public void closeRefresh() {

    }


    /*******************************************外部设置值******************************************/

    /*
    * 设置显示值
    * */
    public void setUseValues(String showValues,String useValues){
        this.useValues = useValues;

        if(sciType == 0){
            edText.setText(showValues);
        }

        if(sciType == 1){
            selText.setText(showValues);
        }

        if(sciType == 2){
            twSwitch.setChecked("1".equals(useValues));
        }

        if(sciType == 4){
            edText.setText(showValues);
        }
    }

    /*
    * 获取使用值
    * */
    public String getUseValues(){
        if(sciType == 0 || sciType == 4){
            return edText.getText().toString();
        }
        return this.useValues;
    }

    /*
    * 设置显示的图片值
    * */
    public void setShowImg1(String imgUrl){
        this.imgUrl1 = imgUrl;
        Glide.with(getContext()).load(imgUrl).into(showImg1);
        closeImg1.setVisibility(VISIBLE);
        upTv1.setVisibility(GONE);
    }
    public void setShowImg2(String imgUrl){
        this.imgUrl2 = imgUrl;
        Glide.with(getContext()).load(imgUrl).into(showImg2);
        closeImg2.setVisibility(VISIBLE);
        upTv2.setVisibility(GONE);
    }
    public void setShowImg3(String imgUrl){
        this.imgUrl3 = imgUrl;
        Glide.with(getContext()).load(imgUrl).into(showImg3);
        closeImg3.setVisibility(VISIBLE);
        upTv3.setVisibility(GONE);
    }

    /*
    * 获取上传的图片路径
    * */
    public String getImgUrl1() {
        return imgUrl1;
    }
    public String getImgUrl2() {
        return imgUrl2;
    }
    public String getImgUrl3() {
        return imgUrl3;
    }
}
