package com.hongyuan.fitness.ui.about_class.registration_group;

import android.annotation.SuppressLint;
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
import com.hongyuan.fitness.databinding.ActivityRegistrationGroupBinding;
import com.hongyuan.fitness.ui.about_class.class_success.SuccessClassActivity;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.MissionDetailBean;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.TimeUtil;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class RegistrationGroupViewModel extends CustomViewModel {

    private ActivityRegistrationGroupBinding binding;
    private MissionDetailBean.DataBean detailBean;

    public RegistrationGroupViewModel(CustomActivity mActivity, ActivityRegistrationGroupBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        detailBean = (MissionDetailBean.DataBean)getBundle().getSerializable("MissionDetailBean");

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
        Glide.with(mActivity).load(detailBean.getCs_img()).apply(options).into(binding.courseImg);

        binding.courseName.setText(detailBean.getCs_name());
        binding.courseTime.setText(TimeUtil.formatDate(detailBean.getCs_start_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatMDofChinese)
                +"\t"+TimeUtil.formatDate(detailBean.getCs_start_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM)
                +"-"+TimeUtil.formatDate(detailBean.getCs_end_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM));

        binding.courseSignUpNum.setText("已报名"+detailBean.getSign_up_num()+"/"+detailBean.getCs_max_num());
        binding.courseAddress.setText(detailBean.getAddress());
        binding.courseStartTime.setText(TimeUtil.formatDate(detailBean.getCs_start_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMDHM));

        binding.callTel.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                CustomDialog.callTel(mActivity, detailBean.getOs_tel(), new CustomDialog.DialogClick() {
                    @SingleClick
                    @Override
                    public void dialogClick(View v) {
                        callTel(detailBean.getOs_tel());
                    }
                });
            }
        });
    }

    /*
     * 报名团课
     * */
    private void signUp(){
        clearParams().setParams("cs_id",String.valueOf(detailBean.getCs_id()));
        Controller.myRequest(Constants.SIGN_UP_COURSE_SUPER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    //预约
    public BindingCommand goPay = new BindingCommand(() -> {
        signUp();
    });

    @Override
    public void onSuccess(Object data) {
        if(isSuccess(data)){
            Bundle bundle = new Bundle();
            bundle.putString("titleName","团课报名");
            bundle.putString("successText","预约成功");
            bundle.putString("buttonText","完成");
            startActivity(SuccessClassActivity.class,bundle);
        }
    }
}
