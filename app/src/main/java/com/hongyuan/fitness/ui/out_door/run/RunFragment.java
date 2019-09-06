package com.hongyuan.fitness.ui.out_door.run;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.CountdownView;
import com.hongyuan.fitness.ui.out_door.run.run_plan.RunBeans;
import com.hongyuan.fitness.ui.out_door.wallk.TodayStepUtils;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.HourMeterUtil;

public class RunFragment extends CustomFragment implements View.OnClickListener, CountdownView.AnimationListenr,
        CustomDialog.DialogClick, HourMeterUtil.TimeCallBack, TodayStepUtils.ReturnRunData {

    //private ChildViewPager mViewPager;
    private ViewPager mViewPager;
    private ImageView prevImg,nextImg,mapMark,finshImg,goOnRunImg,runningImg,startRun;
    private LinearLayout runInfoBox,staticBox;
    private RelativeLayout startRunBox;
    private HourMeterUtil hourMeterUtil;
    private TextView showTimeText,tvSpeedNum,tvAllDisant,tvKcalNum,allRunData;

    //当前选中页面的位置
    private int pagePosition;

    //控制开始与结束
    private boolean isStart;

    //当前的秒数
    private int nowSencdsTime;

    //跑步的开始时间
    private String startTime;
    //跑步的结束时间
    private String endTime;

    public interface Operate{
        void operate(View v);
    }
    private Operate oper;
    public void setOper(Operate oper){
        this.oper = oper;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_run;
    }

    @Override
    public void initView(View mView) {
        setCustomBg(R.color.transparent);
        mViewPager = mView.findViewById(R.id.mViewPager);
        prevImg = mView.findViewById(R.id.prevImg);
        nextImg = mView.findViewById(R.id.nextImg);
        runInfoBox = mView.findViewById(R.id.runInfoBox);
        startRunBox = mView.findViewById(R.id.startRunBox);
        mapMark = mView.findViewById(R.id.mapMark);
        startRun = mView.findViewById(R.id.startRun);
        finshImg = mView.findViewById(R.id.finshImg);
        goOnRunImg = mView.findViewById(R.id.goOnRunImg);
        runningImg = mView.findViewById(R.id.runningImg);
        staticBox = mView.findViewById(R.id.staticBox);
        tvAllDisant = mView.findViewById(R.id.tvAllDisant);
        allRunData = mView.findViewById(R.id.allRunData);

        //跑步时的控件
        showTimeText = mView.findViewById(R.id.showTimeText);
        tvSpeedNum = mView.findViewById(R.id.tvSpeedNum);
        tvAllDisant = mView.findViewById(R.id.tvAllDisant);
        tvKcalNum = mView.findViewById(R.id.tvKcalNum);


        mViewPager.setAdapter(new RunPagerAdapter(getChildFragmentManager()));
        prevImg.setOnClickListener(this);
        nextImg.setOnClickListener(this);
        startRun.setOnClickListener(this);
        mapMark.setOnClickListener(this);
        finshImg.setOnClickListener(this);
        goOnRunImg.setOnClickListener(this);
        runningImg.setOnClickListener(this);

        mViewPager.setOffscreenPageLimit(4);
        mViewPager.addOnPageChangeListener(getOnPageChange());

        hourMeterUtil = new HourMeterUtil();
        hourMeterUtil.setTimeCallBack(this);
    }


    /*
    * viewpage页面改变监听
    * */
    private ViewPager.OnPageChangeListener getOnPageChange(){
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagePosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prevImg:
                pagePosition--;
                if(pagePosition < 0){
                    pagePosition = 0;
                }
                mViewPager.setCurrentItem(pagePosition);
                break;
            case R.id.nextImg:
                pagePosition++;
                if(pagePosition > 3){
                    pagePosition = 3;
                }
                mViewPager.setCurrentItem(pagePosition);
                break;
            case R.id.startRun:
                //先执行倒计时动画
                CustomDialog.countdown(mActivity,this::carryOut);

                if(!isStart){
                    //开始跑步
                    if(oper != null){
                        oper.operate(v);
                    }
                    isStart = true;
                }else{
                    Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.dialog_out_anim);
                    runInfoBox.setAnimation(animation);
                    runInfoBox.setVisibility(View.GONE);
                    isStart = false;
                }

                break;
            case R.id.runningImg://点击暂停
                pauseRun();
                //回调暂定定位
                if(oper != null){
                    oper.operate(v);
                }
                break;
            case R.id.goOnRunImg://点击继续
                goOnRuning();
                //回调继续定位
                if(oper != null){
                    oper.operate(v);
                }
                break;
            case R.id.finshImg://点击结束
                //弹出对话框
                CustomDialog.promptDialog(mActivity,"确定结束今天的运动吗?","在跑一会","确定",true,this);
                break;
            case R.id.mapMark:
                //startActivity(MyMapActivity.class,null);
                //回调显示地图
                if(oper != null){
                    oper.operate(v);
                }
                break;
        }
    }

    /*
    * 执行暂定动作
    * */
    private void pauseRun(){
        goOnRunImg.setVisibility(View.VISIBLE);
        finshImg.setVisibility(View.VISIBLE);
        runningImg.setVisibility(View.INVISIBLE);

        Animation animRuning = AnimationUtils.loadAnimation(mActivity, R.anim.translate_right200_move_0);
        goOnRunImg.startAnimation(animRuning);

        Animation animFinsh = AnimationUtils.loadAnimation(mActivity, R.anim.translate_left200_move_0);
        finshImg.startAnimation(animFinsh);

        //暂停计时
        hourMeterUtil.pauseCount();
    }

    /*
    * 点击继续,执行的动作
    * */
    private void goOnRuning(){
        Animation animRuning = AnimationUtils.loadAnimation(mActivity, R.anim.translate_0_move_right200);
        goOnRunImg.startAnimation(animRuning);

        Animation animFinsh = AnimationUtils.loadAnimation(mActivity, R.anim.translate_0_move_left200);
        finshImg.startAnimation(animFinsh);

        animRuning.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                goOnRunImg.setVisibility(View.GONE);
                finshImg.setVisibility(View.GONE);
                runningImg.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //继续计时
        hourMeterUtil.startCount();
    }

    /*
    * 点击结束执行的动作
    * */
    private void finshRuning(){
        Animation animRuning = AnimationUtils.loadAnimation(mActivity, R.anim.translate_0_move_right200);
        goOnRunImg.startAnimation(animRuning);

        Animation animFinsh = AnimationUtils.loadAnimation(mActivity, R.anim.translate_0_move_left200);
        finshImg.startAnimation(animFinsh);

        Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.dialog_out_anim);
        runInfoBox.setAnimation(animation);
        runInfoBox.setVisibility(View.GONE);

        //隐藏出正在跑的图标
        runningImg.setVisibility(View.GONE);
        //显示开始按钮
        startRun.setVisibility(View.VISIBLE);
        //隐藏地图图片
        mapMark.setVisibility(View.GONE);
        staticBox.setVisibility(View.GONE);

        //结束计时
        hourMeterUtil.stopCount();
        //记录结束时间
        endTime = TimeUtil.getStringByFormat(System.currentTimeMillis(),TimeUtil.dateFormatYMDHMS);
    }

    @Override
    public void carryOut() {
        Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.dialog_in_anim);
        runInfoBox.setAnimation(animation);
        runInfoBox.setVisibility(View.VISIBLE);


        //显示出正在跑的图标
        runningImg.setVisibility(View.VISIBLE);
        //隐藏开始按钮
        startRun.setVisibility(View.GONE);
        //显示地图图片
        mapMark.setVisibility(View.VISIBLE);
        goOnRunImg.setVisibility(View.GONE);
        finshImg.setVisibility(View.GONE);
        staticBox.setVisibility(View.VISIBLE);

        //开始计时
        hourMeterUtil.startCount();
        //开始获取跑步数据
        TodayStepUtils.getInstance().setRunData(this);
        //记录开始时间
        startTime = TimeUtil.getStringByFormat(System.currentTimeMillis(),TimeUtil.dateFormatYMDHMS);
    }

    /*
    * 对话框点击回调
    * */
    @Override
    public void dialogClick(View v) {
        if(v.getId() == R.id.isOk){
            goOnRuning();
        }
        if(v.getId() == R.id.isCannel){
            finshRuning();
            //回调结束跑步，停止定位
            if(oper != null){
                oper.operate(v);
            }
        }
    }

    /*
     * 计时时间回调
     * */
    @Override
    public void onTime(int passedTime) {
        this.nowSencdsTime = passedTime;
        //过五秒就去判断下速度是否为0，若为0就停止计时
        if(passedTime%60 == 0){
            //执行暂停状态
            pauseRun();
            //回调暂定定位
            if(oper != null){
                oper.operate(runningImg);
            }
        }
        showTimeText.setText(TimeUtil.getTime(passedTime));
    }

    /*
    * 添加跑步数据
    * */
    private void updataRunData(){
        clearParams().setParams("start_date",startTime).setParams("end_date",endTime)
                .setParams("run_data",tvAllDisant.getText().toString().substring(0,(tvAllDisant.getText().toString().length() - 2)))
                .setParams("walk_data","0").setParams("et_type","1")
                .setParams("start_lng","").setParams("start_lat","")
                .setParams("end_lng","").setParams("end_lat","");
    }

    /*
    * 跑步回调的
    * */
    @Override
    public void stepData(String allDistion, String allCalorie) {
        //设置跑步数据
        tvAllDisant.setText(allDistion+"km");
        tvKcalNum.setText(allCalorie);
        tvSpeedNum.setText(TodayStepUtils.getInstance().getSpeeds(nowSencdsTime));
    }

    @Override
    protected void lazyLoad() {
        //获取每天跑步等数据列表
        Controller.myRequest(Constants.GET_EXERCISE_DAY_LIST,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        //户外--读取个人跑步里程
        clearParams().setParams("start_date","2019-8-1 00:00:00").setParams("end_date", TimeUtil.getCurrentDate(TimeUtil.dateFormatYMDHMS));
        Controller.myRequest(Constants.GET_RUN_DATA,Controller.TYPE_POST,getParams(), RunBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof RunBeans){
            RunBeans runBeans = (RunBeans)data;
            allRunData.setText(BaseUtil.getNoZoon(runBeans.getData().getRun_data())+"km");
        }
    }


}
