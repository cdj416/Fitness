package com.hongyuan.fitness.ui.person.waiting_for_class.about_group_course.group_checkin_details;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.hongyuan.fitness.custom_view.StickyScrollView;
import com.hongyuan.fitness.databinding.ActivityGroupcourseCheckDetailsBinding;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.ApplyPersonNumAdapter;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.MissionDetailBean;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.TimeUtil;

public class GroupCourseCheckDetailsViewModel extends CustomViewModel implements StickyScrollView.ScrollViewListener{
    private ActivityGroupcourseCheckDetailsBinding binding;
    private MissionDetailBean detailBean;
    private String cs_id;
    private ApplyPersonNumAdapter headAdapter;

    //渐变高度
    private int height;

    public GroupCourseCheckDetailsViewModel(CustomActivity mActivity, ActivityGroupcourseCheckDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        cs_id = getBundle().getString("cs_id");

        LinearLayoutManager personMage = new LinearLayoutManager(mActivity);
        personMage.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.applyNum.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.VERTICAL_LIST,20,0x00000000));
        binding.applyNum.setLayoutManager(personMage);
        headAdapter = new ApplyPersonNumAdapter();
        binding.applyNum.setAdapter(headAdapter);

        binding.cancelReservation.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                CustomDialog.promptDialog(mActivity, "是否取消预约？", "确定取消", "暂不取消", false, v1 -> {
                    if(v1.getId() == R.id.isOk){
                        //调用取消预约接口
                        getCancel();
                    }
                });
            }
        });

        binding.checkIn.setOnClickListener(v -> {
            courseQD(String.valueOf(detailBean.getData().getOcs_id()));
        });

        initListeners();
    }

    /*
     * 取消预约接口
     * */
    private void getCancel(){
        clearParams().setParams("ocs_id",String.valueOf(detailBean.getData().getOcs_id()));
        Controller.myRequest(ConstantsCode.CANCEL_COURSE_SUPER_ORDER,Constants.CANCEL_COURSE_SUPER_ORDER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
     * 获取头部图片的高度
     * */
    private void initListeners() {
        ViewTreeObserver vto = binding.headBg.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.titleBox.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = binding.headBg.getHeight();

                binding.mScroll.setScrollViewListener(GroupCourseCheckDetailsViewModel.this);
            }
        });
    }

    @Override
    public void onScrollChanged(StickyScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
            binding.titleBox.setBackgroundColor(Color.argb((int) 0, 239,91,72));
        } else if (y > 0 && y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
            //binding.titleBox.setTextColor(Color.argb((int) alpha, 255,255,255));
            binding.titleBox.setBackgroundColor(Color.argb((int) alpha, 239,91,72));
        } else {
            binding.titleBox.setBackgroundColor(Color.argb( 255, 239,91,72));
        }
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("cs_id",cs_id);
        Controller.myRequest(Constants.GET_COURSE_SUPER_INFO,Controller.TYPE_POST,getParams(), MissionDetailBean.class,this);
    }

    /*
     * 签到签退
     * */
    private void courseQD(String ocs_id){
        clearParams().setParams("ocs_id",ocs_id).setParams("type","qd");
        Controller.myRequest(ConstantsCode.SUPER_COURSE_XY_QD,Constants.SUPER_COURSE_XY_QD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void setData() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
        Glide.with(mActivity).load(detailBean.getData().getCs_img()).apply(options).into(binding.headBg);

        binding.courseType.setText(detailBean.getData().getCs_name());
        binding.participateNum.setText("参课人数"+detailBean.getData().getSign_up_num());
        binding.classType.setText(detailBean.getData().getSi_name());
        binding.signUpNum.setText("已报名"+detailBean.getData().getSign_up_num()+"/"+detailBean.getData().getCs_max_num());
        binding.leastSignNum.setText(detailBean.getData().getCs_min_num()+"人成团");
        binding.courseTime.setText(TimeUtil.formatDate(detailBean.getData().getCs_start_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatMDofChinese)
                +"\t"+TimeUtil.formatDate(detailBean.getData().getCs_start_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM)
                +"-"+TimeUtil.formatDate(detailBean.getData().getCs_end_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM));
        binding.storeName.setText(detailBean.getData().getOs_name());
        binding.address.setText(detailBean.getData().getAddress());
        binding.courseContent.setText(detailBean.getData().getCs_brief());
        //设置报名人的头像显示
        headAdapter.setNewData(detailBean.getData().getMember_ocs());


        int time = TimeUtil.getOffectMinutes(detailBean.getData().getCs_start_date(),TimeUtil.dateFormatYMDHMS);

        if(time > 0 && time < 60){
            binding.checkIn.setClickable(true);
            binding.checkIn.setText("签到");
            binding.checkIn.setBackgroundResource(R.drawable.shape_gradient_v_radiu5_login);
        }else if(time < 0){
            binding.checkIn.setClickable(false);
            binding.checkIn.setText("未签到");
            binding.checkIn.setBackgroundResource(R.drawable.shape_radius6_999999);
        }else{
            binding.checkIn.setClickable(false);
            binding.checkIn.setText("签到(开课前一小时可签到)");
            binding.checkIn.setBackgroundResource(R.drawable.shape_radius6_999999);
        }
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MissionDetailBean){
            detailBean = (MissionDetailBean)data;
            setData();
        }
    }

    @Override
    public void onSuccess(int code, Object data) {

        if(code == ConstantsCode.CANCEL_COURSE_SUPER_ORDER){
            mActivity.showSuccess("取消报名成功！");
        }

        if(code == ConstantsCode.SUPER_COURSE_XY_QD){
            binding.checkIn.setClickable(false);
            binding.checkIn.setBackgroundResource(R.drawable.shape_radius6_999999);
            binding.checkIn.setText("已签到");
            CustomDialog.groupCoursePunchSuccess(mActivity, TimeUtil.formatDataMsec(TimeUtil.dateFormatDotMD,System.currentTimeMillis()),
                    TimeUtil.getWeek());
        }
    }
}
