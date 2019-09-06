package com.hongyuan.fitness.ui.about_class.coach.show_select_time;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityShowSelectTimeBinding;
import com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail.select_time.TabTimeAdapter;
import com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail.select_time.TabTimeBean;
import com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail.select_time.TimeAdapter;
import com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail.select_time.TimeBean;
import com.hongyuan.fitness.util.DividerItemDecoration;

public class ShowSelectTimeViewModel extends CustomViewModel {

    private ActivityShowSelectTimeBinding binding;
    private TabTimeAdapter tabAdapter;
    private TimeAdapter timeAdapter;

    private TabTimeBean tabTimeBean;

    public ShowSelectTimeViewModel(CustomActivity mActivity, ActivityShowSelectTimeBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        LinearLayoutManager tabManager = new LinearLayoutManager(mActivity);
        tabManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.tabRecyc.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.VERTICAL_LIST,100,0x00000000));
        binding.tabRecyc.setLayoutManager(tabManager);
        tabAdapter = new TabTimeAdapter();
        binding.tabRecyc.setAdapter(tabAdapter);
        tabAdapter.setOnItemChildClickListener((adapter, view1, position) -> {
            changeTab(position);
            reqNewTime(tabTimeBean.getData().get(position).getNow_day());
        });

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 4);
        binding.contentRecyc.setLayoutManager(layoutManager);
        binding.contentRecyc.setLayoutManager(layoutManager);
        timeAdapter = new TimeAdapter();
        binding.contentRecyc.setAdapter(timeAdapter);
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

    @Override
    protected void lazyLoad() {
        //获取头部时间栏
        clearParams();
        Controller.myRequest(Constants.GET_PLAN_DATE,Controller.TYPE_POST,getParams(), TabTimeBean.class,this);
    }

    /*
     * 请求新的教练时间安排
     * */
    private void reqNewTime(String day){
        //去请求教练某天的时间安排
        clearParams().setParams("day",day).setParams("jl_mid",getBundle().getString("jl_mid"));
        Controller.myRequest(Constants.GET_COACH_TIMEPLAN_DAY_LIST,Controller.TYPE_POST,getParams(), TimeBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof TabTimeBean){
            tabTimeBean = (TabTimeBean)data;
            if(tabTimeBean.getData() != null && tabTimeBean.getData().size() > 0){
                tabTimeBean.getData().get(0).setSelect(true);
                tabAdapter.setNewData(tabTimeBean.getData());
                reqNewTime(tabTimeBean.getData().get(0).getNow_day());
            }
        }

        if(data instanceof TimeBean){
            TimeBean timeBean = (TimeBean)data;
            if(timeAdapter != null){
                timeAdapter.setNewData(timeBean.getData());
            }
        }

    }
}
