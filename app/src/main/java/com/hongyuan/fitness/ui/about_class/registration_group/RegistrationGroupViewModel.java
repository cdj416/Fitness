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
import com.hongyuan.fitness.custom_view.scllor_view.UnitBeanUtils;
import com.hongyuan.fitness.databinding.ActivityRegistrationGroupBinding;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.MissionDetailBean;
import com.hongyuan.fitness.ui.promt_success.V3SuccessActivity;
import com.hongyuan.fitness.ui.promt_success.V3SuccessBeans;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class RegistrationGroupViewModel extends CustomViewModel {

    private ActivityRegistrationGroupBinding binding;
    private MissionDetailBean.DataBean detailBean;

    //配送方式选择工具类
    private UnitBeanUtils rUtils;
    private List<Integer> rList;

    public RegistrationGroupViewModel(CustomActivity mActivity, ActivityRegistrationGroupBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        detailBean = (MissionDetailBean.DataBean)getBundle().getSerializable("MissionDetailBean");

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img).centerCrop();
        Glide.with(mActivity).load(detailBean.getCs_img()).apply(options).into(binding.courseImg);

        binding.courseName.setText(detailBean.getCs_name());
        binding.courseTime.setText(TimeUtil.formatDate(detailBean.getCs_start_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatMDofChinese)
                +"\t"+TimeUtil.formatDate(detailBean.getCs_start_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM)
                +"-"+TimeUtil.formatDate(detailBean.getCs_end_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM));

        binding.courseSignUpNum.setText("已报名"+detailBean.getSign_up_num()+"/"+detailBean.getCs_max_num());
        binding.courseAddress.setText(detailBean.getAddress());
        binding.courseStartTime.setText(TimeUtil.formatDate(detailBean.getCs_start_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMDHM));

        if(detailBean.getIs_select_num() == 1){
            binding.selectLocationBox.setVisibility(View.VISIBLE);
            binding.selectLocationBox.setOnClickListener(v -> {
                CustomDialog.scroller(mActivity, rUtils.getUnitList(rList), "选择位置", (v1, message) -> {
                    binding.locatNum.setText(message);
                });
            });


            //获取团课位置数据
            getClassLocation();
        }else{
            binding.selectLocationBox.setVisibility(View.GONE);
        }

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
        if(detailBean.getIs_select_num() == 1){
            if("请选择".equals(binding.locatNum.getText().toString())){
                CustomDialog.showMessage(mActivity,"请选择位置！");
                return;
            }
            setParams("ocs_number",binding.locatNum.getText().toString());
        }
        Controller.myRequest(Constants.SIGN_UP_COURSE_SUPER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
    * 获取团课位置列表
    * */
    private void getClassLocation(){
        clearParams().setParams("cs_id",String.valueOf(detailBean.getCs_id()));
        Controller.myRequest(Constants.GET_CS_NUMBER,Controller.TYPE_POST,getParams(), GroupLocationBeans.class,this);
    }

    //预约
    public BindingCommand goPay = new BindingCommand(() -> {
        signUp();
    });

    @Override
    public void onSuccess(Object data) {
        if(isSuccess(data) && !(data instanceof GroupLocationBeans)){
            V3SuccessBeans beans = new V3SuccessBeans();
            beans.setTitleText("团课报名");
            beans.setShowText("预约成功");
            beans.setBtn1Text("取消报名");
            beans.setBtn2Text("完成");
            List<V3SuccessBeans.ItemConten> list = new ArrayList<>();

            V3SuccessBeans.ItemConten itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent(detailBean.getCs_name());
            itemConten.setItemTitle("课程名：");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent(detailBean.getOs_name());
            itemConten.setItemTitle("上课场地:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent(detailBean.getCs_start_date());
            itemConten.setItemTitle("上课开始时间:");
            list.add(itemConten);

            beans.setItemContens(list);

            Bundle bundle = new Bundle();
            bundle.putSerializable("successBeans",beans);
            startActivity(V3SuccessActivity.class,bundle);
            mActivity.finish();
        }

        if(data instanceof GroupLocationBeans){
            rList = ((GroupLocationBeans)data).getData().getList();

            rUtils = new UnitBeanUtils<Integer>() {
                @Override
                public String unit(Integer o) {
                    return String.valueOf(o);
                }

                @Override
                public String unit_cn(Integer o) {
                    return String.valueOf(o);
                }
            };
        }
    }
}
