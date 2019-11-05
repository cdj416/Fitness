package com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.privite_checkin_details;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.StickyScrollView;
import com.hongyuan.fitness.databinding.ActivtiyPriviteCourseSiginDetailsBinding;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachKongTimeBeans;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsBean;
import com.hongyuan.fitness.ui.webview.WebViewActivity;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.TimeUtil;

public class PriviteCourseCheckDetailsViewModel extends CustomViewModel implements StickyScrollView.ScrollViewListener {

    private ActivtiyPriviteCourseSiginDetailsBinding binding;
    //课程详情
    private CourseDetailsBean detailsBean;

    //渐变高度
    private int height;

    public PriviteCourseCheckDetailsViewModel(CustomActivity mActivity, ActivtiyPriviteCourseSiginDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        binding.cancelReservation.setOnClickListener(v -> {
            CustomDialog.promptDialog(mActivity, "确定取消报名该课程吗？", "确定取消", "暂不取消", false, v1 -> {
                if(v1.getId() == R.id.isOk){
                    cancelRese();
                }
            });
        });
        binding.checkIn.setOnClickListener(v -> {
            courseQD(getBundle().getString("cpa_id"));
        });

        binding.desDetails.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url","http://www.hongyuangood.com/courseNotice/index.html");
            bundle.putString("title","私教课上课须知");
            startActivity(WebViewActivity.class,bundle);
        });

        initListeners();
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

                binding.mScroll.setScrollViewListener(PriviteCourseCheckDetailsViewModel.this);
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
        //获取课程详情
        clearParams().setParams("cp_id",getBundle().getString("cp_id"));
        Controller.myRequest(Constants.GET_COURSE_PRIVITE_INFO,Controller.TYPE_POST,getParams(), CourseDetailsBean.class,this);
    }

    /*
    * 取消私教课预约
    * */
    private void cancelRese(){
        clearParams().setParams("cpa_id",getBundle().getString("cpa_id"));
        Controller.myRequest(ConstantsCode.CANCEL_COURSE_PRIVITE_APPOINTMENT,Constants.CANCEL_COURSE_PRIVITE_APPOINTMENT,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
     * 签到签退
     * */
    private void courseQD(String cpa_id){
        clearParams().setParams("cpa_id",cpa_id).setParams("mtype","xy").setParams("type","qd");
        Controller.myRequest(ConstantsCode.PRIVITE_COURSE_QD,Constants.PRIVITE_COURSE_QD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof CourseDetailsBean && isSuccess(data)){
            detailsBean = (CourseDetailsBean)data;
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img).centerCrop();
            Glide.with(mActivity).load(detailsBean.getData().getCp_img()).apply(options).into(binding.headBg);
            RequestOptions optionsHead = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
            Glide.with(mActivity).load(detailsBean.getData().getCoach_head()).apply(optionsHead).into(binding.headImg);

            binding.courseType.setText(detailsBean.getData().getCp_name());
            binding.coachName.setText(detailsBean.getData().getCoach_nickname());
            binding.buyNum.setText(detailsBean.getData().getCp_num()+"节起售");
            binding.participateNum.setText("参课"+detailsBean.getData().getBnum()+"人");
            binding.courseAddress.setText(detailsBean.getData().getOs_name());
            binding.courseContent.setText(detailsBean.getData().getCp_info());
            binding.coursePeople.setText(detailsBean.getData().getCp_people());
            binding.courseTime.setText(detailsBean.getData().getCp_duration());

            if(TimeUtil.isToday(getBundle().getString("showTime"),TimeUtil.dateFormatYMDHMS)){
                binding.coachTime.setText("今天 "+TimeUtil.formatDate(getBundle().getString("showTime"),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM));
            }else{
                binding.coachTime.setText(TimeUtil.formatDate(getBundle().getString("showTime"),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatMDHM));
            }

            int time = TimeUtil.getOffectMinutes(getBundle().getString("showTime"),TimeUtil.dateFormatYMDHMS);

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
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.CANCEL_COURSE_PRIVITE_APPOINTMENT){
            mActivity.showSuccess("取消预约成功！");
        }

        if(code == ConstantsCode.PRIVITE_COURSE_QD){
            CustomDialog.priviteCoursePunchSuccess(mActivity, TimeUtil.formatDataMsec(TimeUtil.dateFormatDotMD,System.currentTimeMillis()),
                    TimeUtil.getWeek());
            binding.checkIn.setClickable(false);
            binding.checkIn.setBackgroundResource(R.drawable.shape_radius6_999999);
            binding.checkIn.setText("已签到");
        }
    }

}
