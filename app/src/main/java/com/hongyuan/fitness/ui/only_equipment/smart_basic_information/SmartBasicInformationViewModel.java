package com.hongyuan.fitness.ui.only_equipment.smart_basic_information;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.MyRulerView;
import com.hongyuan.fitness.custom_view.time_selecter.time_data.GetTimeData;
import com.hongyuan.fitness.databinding.ActivitySmartBasicInformationBinding;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.ExaminationDetailsBeans;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.IndicatorDetailsActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.TimeUtil;
import com.yolanda.health.qnblesdk.constant.QNIndicator;
import com.yolanda.health.qnblesdk.out.QNBleApi;
import com.yolanda.health.qnblesdk.out.QNScaleItemData;
import com.yolanda.health.qnblesdk.out.QNShareData;
import com.yolanda.health.qnblesdk.out.QNUser;
import com.yolanda.health.qnblesdk.out.QNUtils;

import java.util.Date;

public class SmartBasicInformationViewModel extends CustomViewModel implements MyRulerView.SelectChange {

    private ActivitySmartBasicInformationBinding binding;

    //标识身高
    private final int HEIGHT_RUBLER = 0X2;
    //标识男
    private final int MAN_CLICK = 1;
    //标识nv
    private final int WOMAN_CLICK = 2;
    //标识当前点击值
    private int click_num = MAN_CLICK;
    //放大动画
    private Animation amplification;
    //缩小动画
    private Animation narrow;
    //时间选择器
    private GetTimeData timeData;
    //扫描获得的数据url
    private String scanUrl;
    //数据解析器
    private QNBleApi mQNBleApi;
    //选择的生日
    private Date dayDate;

    //最新生体数据
    private ExaminationDetailsBeans.DataBean.InfoBean detailsBeans;

    public SmartBasicInformationViewModel(CustomActivity mActivity, ActivitySmartBasicInformationBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        scanUrl = getBundle().getString("url");
        mQNBleApi = QNBleApi.getInstance(mActivity);

        amplification = AnimationUtils.loadAnimation(mActivity, R.anim.scale_amplification);
        narrow = AnimationUtils.loadAnimation(mActivity,R.anim.scale_narrow);

        //初始化时间选择器
        timeData = new GetTimeData(mActivity, (date, v) -> {
            dayDate = date;
            binding.selectDays.setText(TimeUtil.getStringByFormat(date,TimeUtil.dateFormatYMD));
        });
        //初始化默认选中值
        changeSex(click_num);

        binding.heightRuler.setDataRuler(HEIGHT_RUBLER,180,0,240,this);
        binding.clickMan.setOnClickListener(v -> {
            if(click_num != MAN_CLICK){
                click_num = MAN_CLICK;
                changeSex(click_num);
            }
        });
        binding.clickWoman.setOnClickListener(v -> {
            if(click_num != WOMAN_CLICK){
                click_num = WOMAN_CLICK;
                changeSex(click_num);
            }
        });
        binding.selectDays.setOnClickListener(v -> timeData.showTime());

        binding.addBoxBut.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                if(dayDate == null){
                    CustomDialog.showMessage(mActivity,"请选择生日");
                    return;
                }

                QNUser qnUser = mQNBleApi.buildUser(userToken.getM_mobile(),
                        Integer.valueOf(binding.heightNum.getText().toString()),
                        click_num == MAN_CLICK ? "male" : "female",dayDate, (code, msg) ->
                                Log.e("cdj", "====创建用户信息返回:" + msg));

                QNShareData qnShareData = QNUtils.decodeShareData(scanUrl, 0, qnUser, (code, msg) -> Log.e("cdj","==========结果======"+msg));

                //上传数据
                updateData(qnShareData);

                //startActivity(IndicatorDetailsActivity.class,null);
            }
        });

    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_NEW_BODY_FAT_INFO,Controller.TYPE_POST,getParams(), ExaminationDetailsBeans.class,this);
    }

    /*
    * 上传数据
    * */
    private void updateData(QNShareData qnShareData){
        mActivity.showLoading();
        clearParams();
        for(QNScaleItemData itemData : qnShareData.getQNScaleData().getAllItem()){
            if(itemData.getType() == QNIndicator.TYPE_WEIGHT){
                setParams("weight",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_BMI){
                setParams("bmi",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_BODYFAT){
                setParams("body_fat_per",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_WATER){
                setParams("body_water_per",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_MUSCLE){
                setParams("skeletal_muscle_rate",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_MUSCLE_MASS){
                setParams("muscle_mass",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_BONE){
                setParams("bone_mass",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_LBM){
                setParams("ffm",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_PROTEIN){
                setParams("protein",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_SUBFAT){
                setParams("subcutaneous_fat",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_VISFAT){
                setParams("visceral_fat_grade",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_BODY_AGE){
                setParams("body_age",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_BODY_SHAPE){
                setParams("shape",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_BMR){
                setParams("basal_metabolism",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_FAT_CONTROL_INDEX){
                setParams("fat_control",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_MUSCLE_MASS_RATE_INDEX){
                setParams("muscle_ratio",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_MUSCLE_CONTROL_INDEX){
                setParams("muscle_control",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_WATER_CONTENT_INDEX){
                setParams("water_content",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_WEIGHT_CONTROL_INDEX){
                setParams("weight_control",String.valueOf(itemData.getValue()));
            }
            if(itemData.getType() == QNIndicator.TYPE_SCORE){
                setParams("bf_record",String.valueOf(itemData.getValue()));
            }
        }
        setParams("height",binding.heightNum.getText().toString());
        setParams("m_birth",binding.selectDays.getText().toString());
        setParams("sex",click_num == MAN_CLICK ? "1" : "2");
        Controller.myRequest(ConstantsCode.ADD_BODY_FAT,Constants.ADD_BODY_FAT,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }


    @Override
    public void valueChange(int whichCode, float value) {
        if(whichCode == HEIGHT_RUBLER){
            binding.heightNum.setText(BaseUtil.getNoZoon(value));
        }
    }

    /*
     * 改变性别选择效果状态
     * */
    private void changeSex(int click_num){
        if(click_num == MAN_CLICK){
            binding.clickMan.startAnimation(amplification);
            binding.clickWoman.startAnimation(narrow);
            binding.clickMan.setTextColor(0xFFEF5B48);
            binding.clickWoman.setTextColor(0xFF999999);
        }
        if(click_num == WOMAN_CLICK){
            binding.clickMan.startAnimation(narrow);
            binding.clickWoman.startAnimation(amplification);
            binding.clickMan.setTextColor(0xFF999999);
            binding.clickWoman.setTextColor(0xFFEF5B48);
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.ADD_BODY_FAT){
            Bundle bundle = new Bundle();
            bundle.putInt("showPosition",1);
            startActivity(IndicatorDetailsActivity.class,bundle);
        }
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof  ExaminationDetailsBeans){
            detailsBeans = ((ExaminationDetailsBeans)data).getData().getInfo();

            if(detailsBeans != null && BaseUtil.isValue(detailsBeans.getWeight_1_str())){
                dayDate = new Date();
                dayDate.setTime(TimeUtil.getDatelongMills(TimeUtil.dateFormatYMD,detailsBeans.getBirth()));

                binding.selectDays.setText(detailsBeans.getBirth());
                binding.heightNum.setText(detailsBeans.getHeight()+"");
                binding.heightRuler.setDataRuler(HEIGHT_RUBLER,detailsBeans.getHeight(),0,240,this);
            }
        }
    }
}
