package com.hongyuan.fitness.ui.about_class.group_class.group_details;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.hongyuan.fitness.custom_view.StickyScrollView;
import com.hongyuan.fitness.databinding.ActivityMissionDetailsBinding;
import com.hongyuan.fitness.ui.about_class.registration_group.RegistrationGroupActivity;
import com.hongyuan.fitness.ui.scan.ScanActivity;
import com.hongyuan.fitness.ui.store.StoreDetailActivity;
import com.hongyuan.fitness.ui.webview.WebViewActivity;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.HourMeterUtil;
import com.hongyuan.fitness.util.SkinConstants;
import com.hongyuan.fitness.util.StatusBarUtil;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.ViewChangeUtil;
import com.luck.picture.lib.tools.ScreenUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MissionDetailViewModel extends CustomViewModel implements StickyScrollView.ScrollViewListener{

    private ActivityMissionDetailsBinding binding;
    private MissionDetailBean detailBean;
    private String cs_id;

    private ApplyPersonNumAdapter headAdapter;
    //渐变高度
    private int height;

    //计时器
    private HourMeterUtil hourMeterUtil;
    //是否已设置提醒
    private boolean isRend = false;

    //四种报名状态
    private final int NO_RESERVATION = 0;//不可预约
    private final int CAN_RESERVATION = 1;//可预约
    private final int TANSION = 2;//紧张
    private final int FULL = 3;//满
    private final int REGISTERED = 4;//已预约
    private final int NOT_OPPN = 5;//未开放
    private int toState;//状态

    public MissionDetailViewModel(CustomActivity mActivity, ActivityMissionDetailsBinding binding) {
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

        binding.buyCard.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("os_id",String.valueOf(detailBean.getData().getOs_id()));
            mActivity.startActivity(StoreDetailActivity.class,bundle);
        });

        binding.desDetails.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url","http://www.hongyuangood.com/signUpRule/index.html");
            bundle.putString("title","团课报名规则");
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

                binding.mScroll.setScrollViewListener(MissionDetailViewModel.this);
            }
        });
    }

    @Override
    public void onScrollChanged(StickyScrollView scrollView, int x, int y, int oldx, int oldy) {
        /*if (y <= 0) {   //设置标题的背景颜色
            binding.titleBox.setBackgroundColor(Color.argb((int) 0, 239,91,72));
        } else if (y > 0 && y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
            //binding.titleBox.setTextColor(Color.argb((int) alpha, 255,255,255));
            binding.titleBox.setBackgroundColor(Color.argb((int) alpha, 239,91,72));
        } else {
            binding.titleBox.setBackgroundColor(Color.argb( 255, 239,91,72));
        }*/

        if (y <= 0) {   //设置标题的背景颜色
            binding.myTitle.setCenterTextColor("团课详情",mActivity.getResources().getColor(R.color.color_FFFFFF));
            binding.myTitle.setRightImage(R.mipmap.white_collection_mark);
            binding.myTitle.setLeftImage(R.mipmap.white_common);
            if(mActivity.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                binding.titleBox.setBackgroundColor(Color.argb((int) 0, 51,51,51));
            }else if(mActivity.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                binding.titleBox.setBackgroundColor(Color.argb((int) 0, 255,255,255));
            }
        } else if (y > 0 && y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);

            if(mActivity.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                binding.titleBox.setBackgroundColor(Color.argb((int) alpha, 51,51,51));
            }else if(mActivity.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                binding.titleBox.setBackgroundColor(Color.argb((int) alpha, 255,255,255));
                if(y <= height/2){
                    binding.myTitle.setRightImage(R.mipmap.white_collection_mark);
                    binding.myTitle.setCenterTextColor("团课详情",mActivity.getResources().getColor(R.color.color_FFFFFF));
                    binding.myTitle.setLeftImage(R.mipmap.white_common);
                    StatusBarUtil.setCommonUI(mActivity,false);
                    binding.myTitle.hideLine();
                }else{
                    binding.myTitle.setRightImage(R.mipmap.gray_collection_img);
                    binding.myTitle.setCenterTextColor("团课详情",mActivity.getResources().getColor(R.color.color_FF333333));
                    binding.myTitle.setLeftImage(R.mipmap.theme_left_img);
                    StatusBarUtil.setCommonUI(mActivity,true);
                    binding.myTitle.showLine();
                }
            }
        } else {
            if(mActivity.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                binding.titleBox.setBackgroundColor(Color.argb((int) 255, 51,51,51));
            }else if(mActivity.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                binding.titleBox.setBackgroundColor(Color.argb( 255, 255,255,255));
                binding.myTitle.setRightImage(R.mipmap.gray_collection_img);
                binding.myTitle.setCenterTextColor("团课详情",mActivity.getResources().getColor(R.color.color_FF333333));
                binding.myTitle.setLeftImage(R.mipmap.theme_left_img);
            }

        }
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("cs_id",cs_id);
        Controller.myRequest(Constants.GET_COURSE_SUPER_INFO,Controller.TYPE_POST,getParams(), MissionDetailBean.class,this);
    }

    //报名时间与当前时间差值
    private long subTime;

    @SuppressLint("SetTextI18n")
    @Override
    protected void setData() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img).centerCrop();
        Glide.with(mActivity).load(detailBean.getData().getCs_img()).apply(options).into(binding.headBg);

        binding.courseType.setText(detailBean.getData().getCs_name());
        binding.participateNum.setText("参课人数"+detailBean.getData().getSign_up_num());
        binding.classType.setText(detailBean.getData().getSi_name());
        binding.signUpNum.setText("已报名"+detailBean.getData().getSign_up_num()+"/"+detailBean.getData().getCs_max_num());
        binding.leastSignNum.setText(detailBean.getData().getCs_min_num()+"人起开课");
        binding.courseTime.setText(TimeUtil.formatDate(detailBean.getData().getCs_start_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatMDofChinese)
                        +"\t"+TimeUtil.formatDate(detailBean.getData().getCs_start_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM)
                        +"-"+TimeUtil.formatDate(detailBean.getData().getCs_end_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM));
        binding.storeName.setText(detailBean.getData().getOs_name());
        binding.address.setText(detailBean.getData().getAddress());
        binding.progressText.setText("已报名"+detailBean.getData().getSign_up_num()+"/"+detailBean.getData().getCs_max_num());
        binding.barProgress.setValue(detailBean.getData().getCs_max_num(),detailBean.getData().getSign_up_num());
        binding.suggest.setText(detailBean.getData().getCs_jy()+"");
        //设置报名人的头像显示
        headAdapter.setNewData(detailBean.getData().getMember_ocs());

        //设置webview显示样式
        binding.courseContent.loadDataWithBaseURL(null, getNewData(detailBean.getData().getCs_brief()),"text/html", "utf-8",null);

        toState = Integer.valueOf(detailBean.getData().getTo_state());

        if(toState == CAN_RESERVATION || toState == TANSION){
            if(toState == CAN_RESERVATION){
                //报名状态显示
                binding.goEnterName.setText("报名中");
            }
            if(toState == TANSION){
                //报名状态显示
                binding.goEnterName.setText("报名中(紧张)");
            }
            binding.goEnterName.setClickable(true);
            binding.goEnterName.setBackgroundResource(R.drawable.shape_gradient_v_radiu5_login);
            binding.goEnterName.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("MissionDetailBean",detailBean.getData());
                startActivity(RegistrationGroupActivity.class,bundle);
            });
        }else{
            binding.goEnterName.setBackgroundResource(R.drawable.shape_radius6_999999);
            if(toState == NO_RESERVATION){
                binding.goEnterName.setClickable(false);
                binding.goEnterName.setText("不可预约");
            }else if(toState == FULL){
                binding.goEnterName.setClickable(false);
                binding.goEnterName.setText("已满");
            }else if(toState == REGISTERED){

                binding.goEnterName.setText("已预约");

                //距离课程开始相差的分钟数
                int differenceStart = TimeUtil.getOffectMinutes(detailBean.getData().getCs_start_time()*1000,detailBean.getData().getNow_time()*1000);
                //距离课程结束相差的分钟数
                int differenceEnd = TimeUtil.getOffectMinutes(detailBean.getData().getCs_end_time()*1000,detailBean.getData().getNow_time()*1000);
                if(differenceStart > 30){
                    binding.goEnterName.setClickable(true);

                    binding.goEnterName.setText("取消报名");
                    binding.goEnterName.setOnClickListener(v -> {
                        CustomDialog.promptDialog(mActivity, "确定取消报名该团课吗？", "确定取消", "暂不取消", false, v1 -> {
                            if(v1.getId() == R.id.isOk){
                                //调用取消预约接口
                                getCancel();
                            }
                        });
                    });
                }else if(differenceStart <= 30 && differenceEnd >= 0){

                    if("0".equals(detailBean.getData().getIs_qd())){//未签到时
                        //点击去签到
                        binding.goEnterName.setText("扫码签到");
                        binding.goEnterName.setClickable(true);
                        binding.goEnterName.setBackgroundResource(R.drawable.shape_gradient_v_radiu5_login);
                        binding.goEnterName.setOnClickListener(v -> {
                            mActivity.startActivity(ScanActivity.class,null);
                        });
                    }else if("1".equals(detailBean.getData().getIs_qd())){//已签到
                        //设置不可点击
                        binding.goEnterName.setText("已签到");
                        binding.goEnterName.setClickable(false);
                    }else{
                        //设置不可点击
                        binding.goEnterName.setText("未知状态");
                        binding.goEnterName.setClickable(false);
                    }

                }else{
                    if("0".equals(detailBean.getData().getIs_qd())) {//未签到时
                        binding.goEnterName.setText("未签到");
                        binding.goEnterName.setClickable(false);
                    }else if("1".equals(detailBean.getData().getIs_qd())){//已签到
                        //设置不可点击
                        binding.goEnterName.setText("已签到");
                        binding.goEnterName.setClickable(false);
                    }else{
                        //设置不可点击
                        binding.goEnterName.setText("未知状态");
                        binding.goEnterName.setClickable(false);
                    }
                }

            }else if(toState == NOT_OPPN){
                binding.goEnterName.setText("未开放");
                binding.goEnterName.setClickable(false);
            }else{
                binding.goEnterName.setText("不可预约");
                binding.goEnterName.setClickable(false);
            }
        }

        //报名时间与当前时间差值
        subTime = detailBean.getData().getBm_time() - detailBean.getData().getNow_time();
        //是否显示报名倒计时提醒（）
        if(detailBean.getData().getBm_time() != 0 && subTime > 0){
            binding.goEnterName.setText("未到报名时间");
            binding.goEnterName.setClickable(false);

            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(binding.remindBox,"translationX",0f,120f,0f);
            objectAnimator.setDuration(500);
            objectAnimator.start();

            binding.remindBox.setVisibility(View.VISIBLE);
            hourMeterUtil = new HourMeterUtil();
            //毫秒差
            //long haoMiao = TimeUtil.getDifferenceNow(detailBean.getData().getAdd_date(),TimeUtil.dateFormatYMDHMS);
            hourMeterUtil.setTimeCallBack(passedTime -> {
                subTime--;

                if(subTime > 0){
                    binding.showCountdown.setText(TimeUtil.getDaysTime(subTime));
                }else{
                    binding.remindBox.setVisibility(View.GONE);
                    //去刷新下页面，让其可以报名
                    lazyLoad();
                }

            });
            hourMeterUtil.startCount();

            binding.setRemind.setOnClickListener(v -> {
                if(isRend){
                    ViewChangeUtil.changeLeftDrawable(mActivity,binding.setRemind,R.mipmap.baise_yuanxing_default_mark);
                    isRend = false;
                }else{
                    ViewChangeUtil.changeLeftDrawable(mActivity,binding.setRemind,R.mipmap.baise_yuanxing_select_mark);
                    isRend = true;
                }
                getRemind(isRend);
            });
        }else{
            binding.remindBox.setVisibility(View.GONE);
        }

        //是否是会员
        if("1".equals(detailBean.getData().getIs_hy())){
            binding.signUpBox.setVisibility(View.VISIBLE);
            binding.buyCardBox.setVisibility(View.GONE);
        }else{
            binding.signUpBox.setVisibility(View.GONE);
            binding.buyCardBox.setVisibility(View.VISIBLE);
        }

        //设置提醒
        if("1".equals(detailBean.getData().getIs_remind())){
            isRend = true;
            ViewChangeUtil.changeLeftDrawable(mActivity,binding.setRemind,R.mipmap.baise_yuanxing_select_mark);
            binding.setRemind.setText("取消提醒");
        }else{
            isRend = false;
            ViewChangeUtil.changeLeftDrawable(mActivity,binding.setRemind,R.mipmap.baise_yuanxing_default_mark);
            binding.setRemind.setText("设置提醒");
        }

        mActivity.closeLoading();
    }

    /*
     * 签到签退
     * */
    public void courseQD(){
        if("0".equals(detailBean.getData().getIs_qd())){
            clearParams().setParams("ocs_id",String.valueOf(detailBean.getData().getOcs_id())).setParams("type","qd");
            Controller.myRequest(ConstantsCode.SUPER_COURSE_XY_QD,Constants.SUPER_COURSE_XY_QD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }
    }

    /*
    * 取消预约接口
    * */
    private void getCancel(){
        clearParams().setParams("ocs_id",String.valueOf(detailBean.getData().getOcs_id()));
        Controller.myRequest(ConstantsCode.CANCEL_COURSE_SUPER_ORDER,Constants.CANCEL_COURSE_SUPER_ORDER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
    * 课程提醒的开启关闭
    * */
    private void getRemind(boolean flag){
        clearParams().setParams("cs_id",String.valueOf(detailBean.getData().getCs_id()));
        if(flag){
            Controller.myRequest(ConstantsCode.ADD_REMIND_CS,Constants.ADD_REMIND_CS,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }else{
            Controller.myRequest(ConstantsCode.DEL_REMIND_CS,Constants.DEL_REMIND_CS,Controller.TYPE_POST,getParams(), BaseBean.class,this);
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
        super.onSuccess(code,data);

        if(code == ConstantsCode.CANCEL_COURSE_SUPER_ORDER){
            showSuccess("团课取消成功！");
            lazyLoad();
        }
        if(code == ConstantsCode.ADD_REMIND_CS){
            binding.setRemind.setText("取消提醒");
        }
        if(code == ConstantsCode.DEL_REMIND_CS){
            binding.setRemind.setText("设置提醒");
        }

        if(code == ConstantsCode.SUPER_COURSE_XY_QD){

            binding.goEnterName.setClickable(false);
            binding.goEnterName.setBackgroundResource(R.drawable.shape_radius6_999999);
            binding.goEnterName.setText("已签到");
            CustomDialog.groupCoursePunchSuccess(mActivity, TimeUtil.formatDataMsec(TimeUtil.dateFormatDotMD,System.currentTimeMillis()),
                    TimeUtil.getWeek());
        }
    }

    /**
     * 设置img标签下的width为手机屏幕宽度，height自适应
     *
     * @param data html字符串
     * @return 更新宽高属性后的html字符串
     */
    private String getNewData(String data) {
        Document document = Jsoup.parse(data);

        Elements pElements = document.select("p:has(img)");
        for (Element pElement : pElements) {
            pElement.attr("style", "text-align:center");
            pElement.attr("max-width", ScreenUtils.getScreenWidth(mActivity) + "px")
                    .attr("height", "auto");
        }
        Elements imgElements = document.select("img");
        for (Element imgElement : imgElements) {
            //重新设置宽高
            imgElement.attr("max-width", "100%")
                    .attr("height", "auto");
            imgElement.attr("style", "max-width:100%;height:auto");
        }
        return document.toString();
    }

    /*
    * 关闭计时
    * */
    public void closeDownTime(){
        if(hourMeterUtil != null){
            hourMeterUtil.onDestory();
        }
    }
}
