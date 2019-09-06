package com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail.select_time;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.RetrofitListener;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.TimeUtil;

import java.util.List;

public class SelectFirstTimeView extends LinearLayout implements View.OnClickListener, RetrofitListener {

    private RelativeLayout selectBox;
    private Dialog dialog;
    private RecyclerView tabRecyc,contentRecyc;
    private TabTimeAdapter tabAdapter;
    private TimeAdapter timeAdapter;
    private CustomViewModel viewModel;
    private String jl_mid;

    private TabTimeBean tabTimeBean;
    private TimeBean timeBean;

    private TextView showTimeText;

    //显示类型
    private int showType = 0;

    private String startTime = "";
    private String endTime = "";

    //当前所选中的日期下标
    private int mPostion;

    public SelectFirstTimeView(Context context) {
        super(context);
        initLayoutView();
    }

    public SelectFirstTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public SelectFirstTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_select_first_time, this);
        selectBox = view.findViewById(R.id.selectBox);
        showTimeText = view.findViewById(R.id.showTimeText);

        selectBox.setOnClickListener(this);
    }


    //确定收款对话框
    public void showTimes(Context mContext){
        if(dialog == null){
            dialog = new Dialog(mContext, R.style.DialogTheme);
            View view = View.inflate(mContext, R.layout.dialog_select_first_appointment,null);
            dialog.setContentView(view);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.bottom_in_out);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

            tabRecyc = view.findViewById(R.id.tabRecyc);
            contentRecyc = view.findViewById(R.id.contentRecyc);

            LinearLayoutManager tabManager = new LinearLayoutManager(getContext());
            tabManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            tabRecyc.addItemDecoration(new DividerItemDecoration(
                    getContext(), DividerItemDecoration.VERTICAL_LIST,100,0x00000000));
            tabRecyc.setLayoutManager(tabManager);
            tabAdapter = new TabTimeAdapter();
            tabRecyc.setAdapter(tabAdapter);


            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
            contentRecyc.setLayoutManager(layoutManager);
            contentRecyc.setLayoutManager(layoutManager);
            timeAdapter = new TimeAdapter();
            contentRecyc.setAdapter(timeAdapter);

            tabAdapter.setOnItemChildClickListener((adapter, view1, position) -> {
                mPostion = position;
                changeTab(position);
                reqNewTime(tabTimeBean.getData().get(position).getNow_day());
            });

            timeAdapter.setOnItemChildClickListener((adapter, view12, position) -> changeTime(position));

            TextView noSelcet = view.findViewById(R.id.noSelcet);
            TextView submit = view.findViewById(R.id.submit);

            if(showType == 1){
                noSelcet.setVisibility(GONE);
            }
            noSelcet.setOnClickListener(v -> {
                startTime = "";
                endTime = "";

                showTimeText.setText("暂不选择");
                dialog.dismiss();
            });

            submit.setOnClickListener(v -> {
                if(getSelectTime().length() < 13){
                    viewModel.onError(1,"请先选择时间");
                }else{
                    showTimeText.setText(getSelectTime());
                    dialog.dismiss();
                }

            });
        }

        try {
            tabAdapter.setNewData(tabTimeBean.getData());
            timeAdapter.setNewData(timeBean.getData());
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /*
    * 更改头部时间选中状态
    * */
    private void changeTab(int position){
        for(int i = 0 ; i < tabTimeBean.getData().size() ; i++){
            if(i == position){
                tabTimeBean.getData().get(i).setSelect(true);
            }else{
                tabTimeBean.getData().get(i).setSelect(false);
            }
        }
        tabAdapter.setNewData(tabTimeBean.getData());
    }

    /*
    * 更改时间段的选中状态
    * */
    private void changeTime(int position){
        for(int i = 0 ; i < timeBean.getData().size() ; i++){
            if(i == position){
                timeBean.getData().get(i).setSelect(true);
            }else{
                timeBean.getData().get(i).setSelect(false);
            }
        }
        timeAdapter.setNewData(timeBean.getData());
    }

    /*
    * 获取当前选择的时间
    * */
    private String getSelectTime(){
        String showTime = "";
        startTime = "";
        endTime = "";
        for(TabTimeBean.DataBean tabTime : tabTimeBean.getData()){
            if(tabTime.isSelect()){
                showTime += tabTime.getNow_day();

                //给起始时间赋值
                startTime += tabTime.getNow_day();
                //给结束时间赋值
                endTime += tabTime.getNow_day();
            }
        }

        for(int i = 0 ; i < timeBean.getData().size() ; i++){
            if(timeBean.getData().get(i).isSelect()){
                showTime += " " + timeBean.getData().get(i).getNow_time();
                showTime = TimeUtil.getStringByFormat(showTime,TimeUtil.dateFormat);
                showTime +="-"+ timeBean.getData().get(i+2).getNow_time().substring(0,5);

                //给起始时间赋值
                startTime += " " + timeBean.getData().get(i).getNow_time();
                //给结束时间赋值
                endTime += " " + timeBean.getData().get(i+2).getNow_time();
                break;
            }
        }
        return showTime;
    }

    /*
    * 获取选中的初始时间
    * */
    public String getStartTime() {
        return startTime;
    }

    /*
    * 获取选中的结束时间
    * */
    public String getEndTime() {
        return endTime;
    }

    /*
    * 请求新的教练时间安排
    * */
    private void reqNewTime(String day){
        //去请求教练某天的时间安排
        viewModel.clearParams().setParams("day",day).setParams("jl_mid",jl_mid);
        Controller.myRequest(Constants.GET_COACH_TIMEPLAN_DAY_LIST,Controller.TYPE_POST,viewModel.getParams(), TimeBean.class,this);
    }

    /*
    * 设置数据
    * */
    public void setData(String jl_mid,CustomViewModel viewModel,int showType){
        this.viewModel = viewModel;
        this.jl_mid = jl_mid;
        this.showType = showType;

        if(showType == 1){
            showTimeText.setText("去选择");
        }
        //为空时需要从小加载数据否则只需要去刷新当前选择日期的时间
        if(tabTimeBean == null){
            Controller.myRequest(Constants.GET_PLAN_DATE,Controller.TYPE_POST,viewModel.getParams(), TabTimeBean.class,this);
        }else{
            reqNewTime(tabTimeBean.getData().get(mPostion).getNow_day());
        }
    }

    @Override
    public void onClick(View v) {
        showTimes(getContext());
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof TabTimeBean){
            tabTimeBean = (TabTimeBean)data;
            if(tabAdapter != null){
                tabAdapter.setNewData(tabTimeBean.getData());
            }else{
                tabTimeBean.getData().get(getTodayPosition(tabTimeBean.getData())).setSelect(true);
            }


            //去请求教练今日的时间安排
            reqNewTime(tabTimeBean.getData().get(getTodayPosition(tabTimeBean.getData())).getNow_day());
        }

        if(data instanceof TimeBean && viewModel.isSuccess(data)){
            timeBean = (TimeBean)data;
            if(timeAdapter != null){
                timeAdapter.setNewData(timeBean.getData());
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {

    }

    @Override
    public void onError(int code_err,String description) {

    }

    @Override
    public void closeRefresh() {

    }

    /*
    * 查询今日时间位置
    * */
    private int getTodayPosition(List<TabTimeBean.DataBean> mList){
        for(int i = 0 ; i < mList.size() ; i++){
            if(mList.get(i).getIs_cur_date() == 1){
                return i;
            }
        }
        return 0;
    }
}
