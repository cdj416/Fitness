package com.hongyuan.fitness.ui.about_class.privite_class.preservation_course;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityPriviteCourseReservationDetailsBinding;
import com.hongyuan.fitness.ui.about_class.class_success.SuccessClassActivity;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachHomePageActivity;
import com.hongyuan.fitness.util.CustomDialog;

public class ReservationDetailsViewModel extends CustomViewModel {

    private ActivityPriviteCourseReservationDetailsBinding binding;
    private ReservationBeans detailsBean;

    public ReservationDetailsViewModel(CustomActivity mActivity, ActivityPriviteCourseReservationDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        //跳转到教练主页
        binding.goCoachProfile.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("coach_mid",String.valueOf(detailsBean.getData().getM_id()));
                startActivity(CoachHomePageActivity.class,bundle);
            }
        });

        //电话咨询
        binding.telBox.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                CustomDialog.callTel(mActivity, detailsBean.getData().getM_mobile(), v1 -> {
                    callTel(detailsBean.getData().getM_mobile());
                });
            }
        });

        //开始约课
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                reservationCourse();
            }
        });
    }

    @Override
    protected void lazyLoad() {
        //查询私教课详情
        clearParams().setParams("cp_id",getBundle().getString("cp_id"));
        Controller.myRequest(Constants.GET_COURSE_PRIVITE_INFO_RESERVE,Controller.TYPE_POST,getParams(), ReservationBeans.class,this);
    }

    /*
    * 约课
    * */
    private void reservationCourse(){
        if(!isValue(binding.selectTime.getStartTime())){
            onError(1,"请选择约课时间！");
            return;
        }

        //请求约课
        clearParams().setParams("start_date",binding.selectTime.getStartTime())
                .setParams("end_date",binding.selectTime.getEndTime())
                .setParams("jl_mid",String.valueOf(detailsBean.getData().getM_id()))
                .setParams("num","1")
                .setParams("cp_id",String.valueOf(detailsBean.getData().getCp_id()));
        Controller.myRequest(Constants.ADD_COURSE_PRIVITE_APPOINTMENT,Controller.TYPE_POST,getParams(), ReservationSuccessBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof ReservationBeans){
            detailsBean = (ReservationBeans)data;
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img).centerCrop();
            Glide.with(mActivity).load(detailsBean.getData().getCp_img()).apply(options).into(binding.courseImg);

            binding.courseName.setText(detailsBean.getData().getCp_name());
            binding.useCourseNum.setText(String.valueOf(detailsBean.getData().getBnum()-detailsBean.getData().getHave_num()));
            binding.haveNum.setText("节 / 共"+detailsBean.getData().getBnum()+"节");
            binding.courseType.setText(detailsBean.getData().getFt_name());
            binding.coachName.setText(detailsBean.getData().getMi_realname());
            binding.courseAddress.setText(detailsBean.getData().getOs_name());
            binding.selectTime.setData(String.valueOf(detailsBean.getData().getM_id()),this,1);
        }

        if(data instanceof ReservationSuccessBeans){
            Bundle bundle = new Bundle();
            bundle.putString("titleName","预约私教课");
            bundle.putString("successText","预约成功！");
            bundle.putString("buttonText","返回");
            startActivity(SuccessClassActivity.class,bundle);
            mActivity.finish();
        }
    }
}
