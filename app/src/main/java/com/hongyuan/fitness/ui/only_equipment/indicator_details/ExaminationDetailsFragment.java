package com.hongyuan.fitness.ui.only_equipment.indicator_details;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.ExaminationDetailsView;
import com.hongyuan.fitness.ui.about_class.coach.coach_list.CoachListActivity;
import com.hongyuan.fitness.ui.main.main_person.PersonBean;
import com.hongyuan.fitness.ui.only_equipment.smart_historical_data.SmartLineChartActivity;
import com.hongyuan.fitness.ui.scan.ScanActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class ExaminationDetailsFragment extends CustomFragment {

    private ExaminationDetailsBeans.DataBean.InfoBean detailsBeans;

    private ExaminationDetailsView bodyWeight,skeletalMuscleRate,bodyFat,bodyWater,fatLoss,visceralFatGrade
            ,proteinQuality,BMI,obesity,basalMetabolism,bodyFatPercentage,fatControl,muscleControl;

    private TextView goScan,record,bodyTypeText,bodyAge,name,addDataText,goNoScan;
    private RoundedImageView headImg;
    private FrameLayout dataBox;
    private RelativeLayout noDataBox,goHistoryBox,goCoach;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_examination_details;
    }

    @Override
    public void initView(View mView) {
        //开启苹果越界拖动效果
        setEnableOverScrollDrag(true);

        bodyWeight = mView.findViewById(R.id.bodyWeight);
        skeletalMuscleRate = mView.findViewById(R.id.skeletalMuscleRate);
        bodyFat = mView.findViewById(R.id.bodyFat);
        bodyWater = mView.findViewById(R.id.bodyWater);
        fatLoss = mView.findViewById(R.id.fatLoss);
        visceralFatGrade = mView.findViewById(R.id.visceralFatGrade);
        proteinQuality = mView.findViewById(R.id.proteinQuality);
        BMI = mView.findViewById(R.id.BMI);
        obesity = mView.findViewById(R.id.obesity);
        basalMetabolism = mView.findViewById(R.id.basalMetabolism);
        bodyFatPercentage = mView.findViewById(R.id.bodyFatPercentage);
        fatControl = mView.findViewById(R.id.fatControl);
        muscleControl = mView.findViewById(R.id.muscleControl);
        goScan = mView.findViewById(R.id.goScan);
        record = mView.findViewById(R.id.record);
        bodyTypeText = mView.findViewById(R.id.bodyTypeText);
        bodyAge = mView.findViewById(R.id.bodyAge);
        headImg = mView.findViewById(R.id.headImg);
        name = mView.findViewById(R.id.name);
        addDataText = mView.findViewById(R.id.addDataText);
        dataBox = mView.findViewById(R.id.dataBox);
        noDataBox = mView.findViewById(R.id.noDataBox);
        goNoScan = mView.findViewById(R.id.goNoScan);
        goHistoryBox = mView.findViewById(R.id.goHistoryBox);
        goCoach = mView.findViewById(R.id.goCoach);

        //startActivity(SmartDeviceActivity.class,null)
        goScan.setOnClickListener(v -> startActivity(ScanActivity.class,null));
        goNoScan.setOnClickListener(v -> startActivity(ScanActivity.class,null));
        goHistoryBox.setOnClickListener(v -> startActivity(SmartLineChartActivity.class,null));
        goCoach.setOnClickListener(v -> startActivity(CoachListActivity.class,null));

    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        if(mActivity.userToken.getM_id() != null){
            //读取个人信息
            clearParams();
            Controller.myRequest(Constants.GET_MEMBER_INDEX_INFO,Controller.TYPE_POST,getParams(), PersonBean.class,this);
        }

        clearParams();
        Controller.myRequest(Constants.GET_NEW_BODY_FAT_INFO,Controller.TYPE_POST,getParams(), ExaminationDetailsBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof  ExaminationDetailsBeans){
            detailsBeans = ((ExaminationDetailsBeans)data).getData().getInfo();

            if(detailsBeans != null && BaseUtil.isValue(detailsBeans.getWeight_1_str())){
                record.setText(BaseUtil.getNoZoon(detailsBeans.getBf_record()));
                bodyTypeText.setText(detailsBeans.getShape_str());
                bodyAge.setText(String.valueOf(detailsBeans.getBody_age()));
                addDataText.setText(TimeUtil.formatDate(detailsBeans.getAdd_date(),TimeUtil.dateFormat,TimeUtil.dateFormatYMD));

                assembly();

                dataBox.setVisibility(View.VISIBLE);
                noDataBox.setVisibility(View.GONE);
                goNoScan.setVisibility(View.GONE);
            }else{
                dataBox.setVisibility(View.GONE);
                noDataBox.setVisibility(View.VISIBLE);
                goNoScan.setVisibility(View.VISIBLE);
            }
        }

        if(data instanceof PersonBean){
            PersonBean personBean = (PersonBean)data;
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
            Glide.with(getContext()).load(personBean.getData().getInfo().getMi_head()).apply(options).into(headImg);
            name.setText(personBean.getData().getInfo().getM_name());
        }
    }

    /*
     * 数据组装
     * */
    private void assembly(){

        ExaminationDetailsUseData useData;

        //组装体重
        useData = new ExaminationDetailsUseData();
        useData.setDes(detailsBeans.getWeight_desc());
        useData.setFirstBoundaryValue(String.valueOf(detailsBeans.getWeight_1()));
        useData.setSecondBoundaryValue(String.valueOf(detailsBeans.getWeight_2()));
        useData.setFirthBoundaryValue(String.valueOf(detailsBeans.getWeight_3()));
        useData.setFourthBoundaryValue(String.valueOf(detailsBeans.getWeight_4()));
        useData.setGradeTitle1(detailsBeans.getWeight_1_str());
        useData.setGradeTitle2(detailsBeans.getWeight_2_str());
        useData.setGradeTitle3(detailsBeans.getWeight_3_str());
        useData.setGradeTitle4(detailsBeans.getWeight_4_str());
        useData.setGradeTitle5(detailsBeans.getWeight_5_str());
        useData.setNormText(BaseUtil.getNoZoon(detailsBeans.getWeight())+"kg");
        useData.setNormTitle("体重");
        useData.setLevelText(detailsBeans.getWeight_str());
        useData.setLevelColor(detailsBeans.getWeight_color());

        bodyWeight.setContent(useData);

        //骨骼肌率
        useData = new ExaminationDetailsUseData();
        useData.setDes(detailsBeans.getSkeletal_muscle_rate_desc());
        useData.setFirstBoundaryValue(String.valueOf(detailsBeans.getSkeletal_muscle_rate_1()));
        useData.setSecondBoundaryValue(String.valueOf(detailsBeans.getSkeletal_muscle_rate_2()));
        useData.setFirthBoundaryValue(null);
        useData.setFourthBoundaryValue(null);
        useData.setGradeTitle1(detailsBeans.getSkeletal_muscle_rate_1_str());
        useData.setGradeTitle2(detailsBeans.getSkeletal_muscle_rate_2_str());
        useData.setGradeTitle3(detailsBeans.getSkeletal_muscle_rate_3_str());
        useData.setGradeTitle4(null);
        useData.setGradeTitle5(null);
        useData.setNormText(BaseUtil.getNoZoon(detailsBeans.getSkeletal_muscle_rate())+"%");
        useData.setNormTitle("骨骼肌率");
        useData.setLevelText(detailsBeans.getSkeletal_muscle_rate_str());
        useData.setLevelColor(detailsBeans.getSkeletal_muscle_rate_color());

        skeletalMuscleRate.setContent(useData);

        //体脂肪
        useData = new ExaminationDetailsUseData();
        useData.setDes(detailsBeans.getFat_weight_desc());
        useData.setFirstBoundaryValue(detailsBeans.getFat_weight_1());
        useData.setSecondBoundaryValue(detailsBeans.getFat_weight_2());
        useData.setFirthBoundaryValue(detailsBeans.getFat_weight_3());
        useData.setFourthBoundaryValue(null);
        useData.setGradeTitle1(detailsBeans.getFat_weight_1_str());
        useData.setGradeTitle2(detailsBeans.getFat_weight_2_str());
        useData.setGradeTitle3(detailsBeans.getFat_weight_3_str());
        useData.setGradeTitle4(detailsBeans.getFat_weight_4_str());
        useData.setGradeTitle5(null);
        useData.setNormText(BaseUtil.getNoZoon(detailsBeans.getFat_weight())+"kg");
        useData.setNormTitle("脂肪重量");
        useData.setLevelText(detailsBeans.getFat_weight_str());
        useData.setLevelColor(detailsBeans.getFat_weight_color());

        bodyFat.setContent(useData);

        //体水分
        useData = new ExaminationDetailsUseData();
        useData.setDes(detailsBeans.getBody_water_per_desc());
        useData.setFirstBoundaryValue(String.valueOf(detailsBeans.getBody_water_per_1()));
        useData.setSecondBoundaryValue(String.valueOf(detailsBeans.getBody_water_per_2()));
        useData.setFirthBoundaryValue(null);
        useData.setFourthBoundaryValue(null);
        useData.setGradeTitle1(detailsBeans.getBody_water_per_1_str());
        useData.setGradeTitle2(detailsBeans.getBody_water_per_2_str());
        useData.setGradeTitle3(detailsBeans.getBody_water_per_3_str());
        useData.setGradeTitle4(null);
        useData.setGradeTitle5(null);
        useData.setNormText(BaseUtil.getNoZoon(detailsBeans.getBody_water_per())+"%");
        useData.setNormTitle("体水分");
        useData.setLevelText(detailsBeans.getBody_water_per_str());
        useData.setLevelColor(detailsBeans.getBody_water_per_color());

        bodyWater.setContent(useData);

        //去脂体重
        useData = new ExaminationDetailsUseData();
        useData.setDes(detailsBeans.getFfm_desc());
        useData.setFirstBoundaryValue(null);
        useData.setSecondBoundaryValue(null);
        useData.setFirthBoundaryValue(null);
        useData.setFourthBoundaryValue(null);
        useData.setGradeTitle1(null);
        useData.setGradeTitle2(null);
        useData.setGradeTitle3(null);
        useData.setGradeTitle4(null);
        useData.setGradeTitle5(null);
        useData.setNormText(BaseUtil.getNoZoon(detailsBeans.getFfm())+"kg");
        useData.setNormTitle("去脂体重");
        useData.setLevelText(detailsBeans.getFfm_str());
        useData.setLevelColor(detailsBeans.getFfm_color());

        fatLoss.setContent(useData);

        //内脏脂肪等级
        useData = new ExaminationDetailsUseData();
        useData.setDes(detailsBeans.getVisceral_fat_grade_desc());
        useData.setFirstBoundaryValue(detailsBeans.getVisceral_fat_grade_1());
        useData.setSecondBoundaryValue(detailsBeans.getVisceral_fat_grade_2());
        useData.setFirthBoundaryValue(null);
        useData.setFourthBoundaryValue(null);
        useData.setGradeTitle1(detailsBeans.getVisceral_fat_grade_1_str());
        useData.setGradeTitle2(detailsBeans.getVisceral_fat_grade_2_str());
        useData.setGradeTitle3(detailsBeans.getVisceral_fat_grade_3_str());
        useData.setGradeTitle4(null);
        useData.setGradeTitle5(null);
        useData.setNormText(BaseUtil.getNoZoon(detailsBeans.getVisceral_fat_grade()));
        useData.setNormTitle("内脏脂肪等级");
        useData.setLevelText(detailsBeans.getVisceral_fat_grade_str());
        useData.setLevelColor(detailsBeans.getVisceral_fat_grade_color());

        visceralFatGrade.setContent(useData);

        //蛋白质量
        useData = new ExaminationDetailsUseData();
        useData.setDes(detailsBeans.getProtein_quality_desc());
        useData.setFirstBoundaryValue(detailsBeans.getProtein_quality_1());
        useData.setSecondBoundaryValue(detailsBeans.getProtein_quality_2());
        useData.setFirthBoundaryValue(null);
        useData.setFourthBoundaryValue(null);
        useData.setGradeTitle1(detailsBeans.getProtein_quality_1_str());
        useData.setGradeTitle2(detailsBeans.getProtein_quality_2_str());
        useData.setGradeTitle3(detailsBeans.getProtein_quality_3_str());
        useData.setGradeTitle4(null);
        useData.setGradeTitle5(null);
        useData.setNormText(BaseUtil.getNoZoon(detailsBeans.getProtein_quality())+"kg");
        useData.setNormTitle("蛋白质量");
        useData.setLevelText(detailsBeans.getProtein_quality_str());
        useData.setLevelColor(detailsBeans.getProtein_quality_color());

        proteinQuality.setContent(useData);

        //BMI
        useData = new ExaminationDetailsUseData();
        useData.setDes(detailsBeans.getBmi_desc());
        useData.setFirstBoundaryValue(String.valueOf(detailsBeans.getBmi_1()));
        useData.setSecondBoundaryValue(String.valueOf(detailsBeans.getBmi_1()));
        useData.setFirthBoundaryValue(null);
        useData.setFourthBoundaryValue(null);
        useData.setGradeTitle1(detailsBeans.getBmi_1_str());
        useData.setGradeTitle2(detailsBeans.getBmi_2_str());
        useData.setGradeTitle3(detailsBeans.getBmi_3_str());
        useData.setGradeTitle4(null);
        useData.setGradeTitle5(null);
        useData.setNormText(BaseUtil.getNoZoon(detailsBeans.getBmi()));
        useData.setNormTitle("BMI");
        useData.setLevelText(detailsBeans.getBmi_str());
        useData.setLevelColor(detailsBeans.getBmi_color());

        BMI.setContent(useData);

        //肥胖度
        useData = new ExaminationDetailsUseData();
        useData.setDes(detailsBeans.getObesity_desc());
        useData.setFirstBoundaryValue(detailsBeans.getObesity_1());
        useData.setSecondBoundaryValue(detailsBeans.getObesity_2());
        useData.setFirthBoundaryValue(detailsBeans.getObesity_3());
        useData.setFourthBoundaryValue(detailsBeans.getObesity_4());
        useData.setGradeTitle1(detailsBeans.getObesity_1_str());
        useData.setGradeTitle2(detailsBeans.getObesity_2_str());
        useData.setGradeTitle3(detailsBeans.getObesity_3_str());
        useData.setGradeTitle4(detailsBeans.getObesity_4_str());
        useData.setGradeTitle5(detailsBeans.getObesity_5_str());
        useData.setNormText(BaseUtil.getNoZoon(detailsBeans.getObesity())+"%");
        useData.setNormTitle("肥胖度");
        useData.setLevelText(detailsBeans.getObesity_str());
        useData.setLevelColor(detailsBeans.getObesity_color());

        obesity.setContent(useData);

        //基础代谢
        useData = new ExaminationDetailsUseData();
        useData.setDes(detailsBeans.getBasal_metabolism_desc());
        useData.setFirstBoundaryValue(null);
        useData.setSecondBoundaryValue(null);
        useData.setFirthBoundaryValue(null);
        useData.setFourthBoundaryValue(null);
        useData.setGradeTitle1(null);
        useData.setGradeTitle2(null);
        useData.setGradeTitle3(null);
        useData.setGradeTitle4(null);
        useData.setGradeTitle5(null);
        useData.setNormText(BaseUtil.getNoZoon(detailsBeans.getBasal_metabolism())+"kcal");
        useData.setNormTitle("基础代谢");
        useData.setLevelText(detailsBeans.getBasal_metabolism_str());
        useData.setLevelColor(detailsBeans.getBasal_metabolism_color());

        basalMetabolism.setContent(useData);

        //体脂百分比
        useData = new ExaminationDetailsUseData();
        useData.setDes(detailsBeans.getBody_fat_per_desc());
        useData.setFirstBoundaryValue(String.valueOf(detailsBeans.getBody_fat_per_1()));
        useData.setSecondBoundaryValue(String.valueOf(detailsBeans.getBody_fat_per_2()));
        useData.setFirthBoundaryValue(String.valueOf(detailsBeans.getBody_fat_per_3()));
        useData.setFourthBoundaryValue(null);
        useData.setGradeTitle1(detailsBeans.getBody_fat_per_1_str());
        useData.setGradeTitle2(detailsBeans.getBody_fat_per_2_str());
        useData.setGradeTitle3(detailsBeans.getBody_fat_per_3_str());
        useData.setGradeTitle4(detailsBeans.getBody_fat_per_4_str());
        useData.setGradeTitle5(null);
        useData.setNormText(BaseUtil.getNoZoon(detailsBeans.getBody_fat_per())+"%");
        useData.setNormTitle("体脂率");
        useData.setLevelText(detailsBeans.getBody_fat_per_str());
        useData.setLevelColor(detailsBeans.getBody_fat_per_color());

        bodyFatPercentage.setContent(useData);

        //脂肪控制
        useData = new ExaminationDetailsUseData();
        useData.setDes(detailsBeans.getFat_control_desc());
        useData.setFirstBoundaryValue(null);
        useData.setSecondBoundaryValue(null);
        useData.setFirthBoundaryValue(null);
        useData.setFourthBoundaryValue(null);
        useData.setGradeTitle1(null);
        useData.setGradeTitle2(null);
        useData.setGradeTitle3(null);
        useData.setGradeTitle4(null);
        useData.setGradeTitle5(null);
        useData.setNormText(BaseUtil.getNoZoon(detailsBeans.getFat_control())+"kg");
        useData.setNormTitle("脂肪控制");
        useData.setLevelText(detailsBeans.getFat_control_str());
        useData.setLevelColor(detailsBeans.getFat_control_color());

        fatControl.setContent(useData);

        //肌肉控制
        useData = new ExaminationDetailsUseData();
        useData.setDes(detailsBeans.getMuscle_control_desc());
        useData.setFirstBoundaryValue(null);
        useData.setSecondBoundaryValue(null);
        useData.setFirthBoundaryValue(null);
        useData.setFourthBoundaryValue(null);
        useData.setGradeTitle1(null);
        useData.setGradeTitle2(null);
        useData.setGradeTitle3(null);
        useData.setGradeTitle4(null);
        useData.setGradeTitle5(null);
        useData.setNormText(BaseUtil.getNoZoon(detailsBeans.getMuscle_control())+"kg");
        useData.setNormTitle("肌肉控制");
        useData.setLevelText(detailsBeans.getMuscle_control_str());
        useData.setLevelColor(detailsBeans.getMuscle_control_color());

        muscleControl.setContent(useData);


    }
}
