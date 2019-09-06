package com.hongyuan.fitness.ui.heat;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.time_selecter.TimePickerBuilder;
import com.hongyuan.fitness.custom_view.time_selecter.TimePickerView;
import com.hongyuan.fitness.databinding.ActivityHeatBinding;
import com.hongyuan.fitness.ui.heat.add_food.AddFoodActivity;
import com.hongyuan.fitness.ui.heat.heat_detail.HeatDetailBean;
import com.hongyuan.fitness.ui.heat.heat_main_adapter.Heat01Adapter;
import com.hongyuan.fitness.ui.heat.heat_main_adapter.Heat02Adapter;
import com.hongyuan.fitness.ui.heat.heat_main_adapter.Heat03Adapter;
import com.hongyuan.fitness.ui.heat.heat_main_adapter.Heat04Adapter;
import com.hongyuan.fitness.ui.heat.heat_main_adapter.Heat05Adapter;
import com.hongyuan.fitness.ui.heat.heat_main_adapter.Heat06Adapter;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.TimeUtil;

import java.util.Calendar;
import java.util.Date;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

/*
* 日 数据结构有问题，老子又懒得从新组装数据结构，占时也没啥好办法.(妈的，看来越懒，写得越多)
* */
public class HeatViewModel extends CustomViewModel implements CustomDialog.DialogClick, UpdataFoodView.UpdataData {

    private ActivityHeatBinding binding;
    private Heat01Adapter adapter01;
    private Heat02Adapter adapter02;
    private Heat03Adapter adapter03;
    private Heat04Adapter adapter04;
    private Heat05Adapter adapter05;
    private Heat06Adapter adapter06;

    private TimePickerView pvTime;
    private String showTime;
    private HeatBean heatBean;
    private HeatDetailBean detailBean;
    private ItemClikeBean myItemClickBean;

    public HeatViewModel(CustomActivity mActivity, ActivityHeatBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager01 = new LinearLayoutManager(mActivity);
        manager01.setOrientation(RecyclerView.VERTICAL);
        binding.breakfastRec.setLayoutManager(manager01);
        adapter01 = new Heat01Adapter();
        binding.breakfastRec.setAdapter(adapter01);

        LinearLayoutManager manager02 = new LinearLayoutManager(mActivity);
        manager02.setOrientation(RecyclerView.VERTICAL);
        binding.lunchRec.setLayoutManager(manager02);
        adapter02 = new Heat02Adapter();
        binding.lunchRec.setAdapter(adapter02);

        LinearLayoutManager manager03 = new LinearLayoutManager(mActivity);
        manager03.setOrientation(RecyclerView.VERTICAL);
        binding.diannerRec.setLayoutManager(manager03);
        adapter03 = new Heat03Adapter();
        binding.diannerRec.setAdapter(adapter03);

        LinearLayoutManager manager04 = new LinearLayoutManager(mActivity);
        manager04.setOrientation(RecyclerView.VERTICAL);
        binding.addBreakfastRec.setLayoutManager(manager04);
        adapter04 = new Heat04Adapter();
        binding.addBreakfastRec.setAdapter(adapter04);

        LinearLayoutManager manager05 = new LinearLayoutManager(mActivity);
        manager05.setOrientation(RecyclerView.VERTICAL);
        binding.addLunchRec.setLayoutManager(manager05);
        adapter05 = new Heat05Adapter();
        binding.addLunchRec.setAdapter(adapter05);

        LinearLayoutManager manager06 = new LinearLayoutManager(mActivity);
        manager06.setOrientation(RecyclerView.VERTICAL);
        binding.addDinnerRec.setLayoutManager(manager06);
        adapter06 = new Heat06Adapter();
        binding.addDinnerRec.setAdapter(adapter06);

        adapter01.setOnItemChildClickListener((adapter, view, position) ->{
            myItemClickBean = getItemData(heatBean.getData().getList().get_$1(),position);
            getFoodDetail(String.valueOf(heatBean.getData().getList().get_$1().getList().get(position).getF_id()));
        });
        adapter02.setOnItemChildClickListener((adapter, view, position) ->{
            myItemClickBean = getItemData(heatBean.getData().getList().get_$2(),position);
            getFoodDetail(String.valueOf(heatBean.getData().getList().get_$2().getList().get(position).getF_id()));
        });
        adapter03.setOnItemChildClickListener((adapter, view, position) ->{
            myItemClickBean = getItemData(heatBean.getData().getList().get_$3(),position);
            getFoodDetail(String.valueOf(heatBean.getData().getList().get_$3().getList().get(position).getF_id()));
        });
        adapter04.setOnItemChildClickListener((adapter, view, position) ->{
            myItemClickBean = getItemData(heatBean.getData().getList().get_$4(),position);
            getFoodDetail(String.valueOf(heatBean.getData().getList().get_$4().getList().get(position).getF_id()));
        });
        adapter05.setOnItemChildClickListener((adapter, view, position) ->{
            myItemClickBean = getItemData(heatBean.getData().getList().get_$5(),position);
            getFoodDetail(String.valueOf(heatBean.getData().getList().get_$5().getList().get(position).getF_id()));
        });
        adapter06.setOnItemChildClickListener((adapter, view, position) ->{
            myItemClickBean = getItemData(heatBean.getData().getList().get_$6(),position);
            getFoodDetail(String.valueOf(heatBean.getData().getList().get_$6().getList().get(position).getF_id()));
        });

        showTime = TimeUtil.getStringByFormat(new Date(System.currentTimeMillis()),TimeUtil.dateFormatYMD);
        //初始化时间显示
        binding.tvTime.setText(showTime);
        //初始化时间控件
        initTimePicker();
    }

    /*
    * 给留个适配器设置值
    * */
    private void setRecData(HeatBean heatBean){

        binding.budgetNum.setText("预算"+BaseUtil.getNoZoon(heatBean.getData().getOk_cal_all()));
        binding.weightNum.setText(heatBean.getData().getBody_info().getMbi_weight());
        binding.myStep.setMaxValue(Integer.valueOf(heatBean.getData().getOk_cal_all()));
        binding.myStep.setValue((int)heatBean.getData().getCal_all());
        binding.eatNum.setText(BaseUtil.getNoZoon(heatBean.getData().getCal_all()));
        binding.consume.setText(BaseUtil.getNoZoon(heatBean.getData().getConsume_heat()));
        binding.canEatNum.setText(getCanEat());

        if(heatBean.getData().getList().get_$1().getCal() == 0 &&
                heatBean.getData().getList().get_$2().getCal() == 0 &&
                heatBean.getData().getList().get_$3().getCal() == 0 &&
                heatBean.getData().getList().get_$4().getCal() == 0 &&
                heatBean.getData().getList().get_$5().getCal() == 0 &&
                heatBean.getData().getList().get_$6().getCal() == 0){
            binding.dataBox.setVisibility(View.GONE);
            binding.llEmpty.setVisibility(View.VISIBLE);
            return;
        }else{
            binding.dataBox.setVisibility(View.VISIBLE);
            binding.llEmpty.setVisibility(View.GONE);
        }

        if(heatBean.getData().getList().get_$1().getList() != null && heatBean.getData().getList().get_$1().getList().size() > 0){
            binding.breakfastBox.setVisibility(View.VISIBLE);
            binding.breakfastKcal.setText(BaseUtil.getNoZoon(heatBean.getData().getList().get_$1().getCal())+"千卡");
            adapter01.setNewData(heatBean.getData().getList().get_$1().getList());
        }else{
            binding.breakfastBox.setVisibility(View.GONE);
        }
        if(heatBean.getData().getList().get_$2().getList() != null && heatBean.getData().getList().get_$2().getList().size() > 0){
            binding.lunchBox.setVisibility(View.VISIBLE);
            binding.lunchKcal.setText(BaseUtil.getNoZoon(heatBean.getData().getList().get_$2().getCal())+"千卡");
            adapter02.setNewData(heatBean.getData().getList().get_$2().getList());
        }else{
            binding.lunchBox.setVisibility(View.GONE);
        }
        if(heatBean.getData().getList().get_$3().getList() != null && heatBean.getData().getList().get_$3().getList().size() > 0){
            binding.dinnerBox.setVisibility(View.VISIBLE);
            binding.dinnerKcal.setText(BaseUtil.getNoZoon(heatBean.getData().getList().get_$3().getCal())+"千卡");
            adapter03.setNewData(heatBean.getData().getList().get_$3().getList());
        }else{
            binding.dinnerBox.setVisibility(View.GONE);
        }
        if(heatBean.getData().getList().get_$4().getList() != null && heatBean.getData().getList().get_$4().getList().size() > 0){
            binding.addBreakfastBox.setVisibility(View.VISIBLE);
            binding.addBreakfastKcal.setText(BaseUtil.getNoZoon(heatBean.getData().getList().get_$4().getCal())+"千卡");
            adapter04.setNewData(heatBean.getData().getList().get_$4().getList());
        }else{
            binding.addBreakfastBox.setVisibility(View.GONE);
        }
        if(heatBean.getData().getList().get_$5().getList() != null && heatBean.getData().getList().get_$5().getList().size() > 0){
            binding.addLunchBox.setVisibility(View.VISIBLE);
            binding.addLunchKcal.setText(BaseUtil.getNoZoon(heatBean.getData().getList().get_$5().getCal())+"千卡");
            adapter05.setNewData(heatBean.getData().getList().get_$5().getList());
        }else{
            binding.addLunchBox.setVisibility(View.GONE);
        }
        if(heatBean.getData().getList().get_$6().getList() != null && heatBean.getData().getList().get_$6().getList().size() > 0){
            binding.addDinnerBox.setVisibility(View.VISIBLE);
            binding.addDinnerKcal.setText(BaseUtil.getNoZoon(heatBean.getData().getList().get_$6().getCal())+"千卡");
            adapter06.setNewData(heatBean.getData().getList().get_$6().getList());
        }else{
            binding.addDinnerBox.setVisibility(View.GONE);
        }


    }

    /*
    * 获取还可以吃多少的量
    * */
    private String getCanEat(){
        String showNum = "";
        //可吃的总量
        int okCalAll = Integer.valueOf(heatBean.getData().getOk_cal_all());
        //已经吃的量
        double callAll = heatBean.getData().getCal_all();
        //消耗的
        double consumeHeat = heatBean.getData().getConsume_heat();

        if(((okCalAll+consumeHeat) - callAll) > 0){
            showNum = BaseUtil.getNoZoon(String.valueOf(((okCalAll+consumeHeat) - callAll)));
        }else{
            showNum = "0";
        }
        return showNum;
    }

    /*
    * 数据组装
    * */
    /*private AddFoodBean.DataBean.ListBean getDataBean(int type,Object data){

    }*/

    //早餐
    public BindingCommand breakFast = new BindingCommand(() -> goToAdd(showTime,"1"));
    //午餐
    public BindingCommand lunch = new BindingCommand(() -> goToAdd(showTime,"2"));
    //晚餐
    public BindingCommand dinner = new BindingCommand(() -> goToAdd(showTime,"3"));
    //加餐选项
    public BindingCommand showSlectDialog = new BindingCommand(() -> CustomDialog.selectAddFood(mActivity,this));
    //显示时间选择器
    public BindingCommand showSelecter = new BindingCommand(() ->pvTime.show());


    private void initTimePicker() {//Dialog 模式下，在底部弹出
        Calendar startData = Calendar.getInstance();
        Calendar selectData = Calendar.getInstance();
        Calendar endData = Calendar.getInstance();
        startData.setTime(TimeUtil.getDateByFormat("1900-01-01",TimeUtil.dateFormatYMD));
        endData.setTimeInMillis(System.currentTimeMillis());
        selectData.setTimeInMillis(System.currentTimeMillis());
        endData.add(Calendar.DATE, 1);

        pvTime = new TimePickerBuilder(mActivity, (date, v) -> {
            showTime = TimeUtil.getStringByFormat(date,TimeUtil.dateFormatYMD);
            binding.tvTime.setText(showTime);
            lazyLoad();})
                .setTimeSelectChangeListener(date -> Log.i("pvTime", "onTimeSelectChanged"))
                .setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .addOnCancelClickListener(view -> Log.i("pvTime", "onCancelClickListener"))
                .setRangDate(startData,endData)
                .setDate(selectData)
                .build();
        pvTime.setTitleText("选择日期");
        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.hongyuan.fitness.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
    }



    /*
    * 跳转加餐页面
    * */
    private void goToAdd(String addData,String addType){
        Bundle bundle = new Bundle();
        bundle.putString("fe_date",addData);
        bundle.putString("fe_type",addType);
        startActivity(AddFoodActivity.class,bundle);
    }

    /*
    * 请求详情
    * */
    private void getFoodDetail(String f_id){
        clearParams().setParams("f_id",f_id);
        Controller.myRequest(Constants.GET_FOOD_INFO,Controller.TYPE_POST,getParams(), HeatDetailBean.class,this);
    }

    /*
    * 选择结果回调
    * */
    @Override
    public void dialogClick(View v) {
        String addType = "1";
        TextView tv = (TextView)v;
        if("上午加餐".equals(tv.getText().toString())){
            addType = "4";
        }
        if("中午加餐".equals(tv.getText().toString())){
            addType = "5";
        }
        if("晚上加餐".equals(tv.getText().toString())){
            addType = "6";
        }
        goToAdd(showTime,addType);
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("fe_date",showTime);
        Controller.myRequest(Constants.GET_FOOD_EATING_LIST,Controller.TYPE_POST,getParams(), HeatBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof HeatBean && isSuccess(data)){
            heatBean = (HeatBean)data;
            setRecData(heatBean);
        }

        if(data instanceof HeatDetailBean && isSuccess(data)){
            detailBean = (HeatDetailBean)data;
            CustomDialog.updataFood(mActivity,myItemClickBean,detailBean.getData(),this);
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.EDIT_FOOD_EATING){
            //刷新数据
            lazyLoad();
            showSuccess("修改成功！");
        }
        if(code == ConstantsCode.DEL_FOOD_EATING){
            //刷新数据
            lazyLoad();
            showSuccess("删除成功！");
        }
    }

    /*
    * 组装弹框需要的数据
    * */
    private ItemClikeBean getItemData(Object object,int position){
        ItemClikeBean clikeBean = new ItemClikeBean();
        if(object instanceof HeatBean.DataBean.ListBeanXXXXXX._$1Bean){
            HeatBean.DataBean.ListBeanXXXXXX._$1Bean bean = (HeatBean.DataBean.ListBeanXXXXXX._$1Bean)object;
            clikeBean.setF_img(bean.getList().get(position).getF_img());
            clikeBean.setF_name(bean.getList().get(position).getF_name());
            clikeBean.setF_type(bean.getList().get(position).getF_type());
            clikeBean.setFe_cal(bean.getList().get(position).getFe_cal());
            clikeBean.setFe_ge(bean.getList().get(position).getFe_ge());
            clikeBean.setFe_num(bean.getList().get(position).getFe_num());
            clikeBean.setFe_id(bean.getList().get(position).getFe_id());
            clikeBean.setFu_id(bean.getList().get(position).getFu_id());
            clikeBean.setF_id(bean.getList().get(position).getF_id());
        }
        if(object instanceof HeatBean.DataBean.ListBeanXXXXXX._$2Bean){
            HeatBean.DataBean.ListBeanXXXXXX._$2Bean bean = (HeatBean.DataBean.ListBeanXXXXXX._$2Bean)object;
            clikeBean.setF_img(bean.getList().get(position).getF_img());
            clikeBean.setF_name(bean.getList().get(position).getF_name());
            clikeBean.setF_type(bean.getList().get(position).getF_type());
            clikeBean.setFe_cal(bean.getList().get(position).getFe_cal());
            clikeBean.setFe_ge(bean.getList().get(position).getFe_ge());
            clikeBean.setFe_num(bean.getList().get(position).getFe_num());
            clikeBean.setFe_id(bean.getList().get(position).getFe_id());
            clikeBean.setFu_id(bean.getList().get(position).getFu_id());
            clikeBean.setF_id(bean.getList().get(position).getF_id());
        }
        if(object instanceof HeatBean.DataBean.ListBeanXXXXXX._$3Bean){
            HeatBean.DataBean.ListBeanXXXXXX._$3Bean bean = (HeatBean.DataBean.ListBeanXXXXXX._$3Bean)object;
            clikeBean.setF_img(bean.getList().get(position).getF_img());
            clikeBean.setF_name(bean.getList().get(position).getF_name());
            clikeBean.setF_type(bean.getList().get(position).getF_type());
            clikeBean.setFe_cal(bean.getList().get(position).getFe_cal());
            clikeBean.setFe_ge(bean.getList().get(position).getFe_ge());
            clikeBean.setFe_num(bean.getList().get(position).getFe_num());
            clikeBean.setFe_id(bean.getList().get(position).getFe_id());
            clikeBean.setFu_id(bean.getList().get(position).getFu_id());
            clikeBean.setF_id(bean.getList().get(position).getF_id());
        }
        if(object instanceof HeatBean.DataBean.ListBeanXXXXXX._$4Bean){
            HeatBean.DataBean.ListBeanXXXXXX._$4Bean bean = (HeatBean.DataBean.ListBeanXXXXXX._$4Bean)object;
            clikeBean.setF_img(bean.getList().get(position).getF_img());
            clikeBean.setF_name(bean.getList().get(position).getF_name());
            clikeBean.setF_type(bean.getList().get(position).getF_type());
            clikeBean.setFe_cal(bean.getList().get(position).getFe_cal());
            clikeBean.setFe_ge(bean.getList().get(position).getFe_ge());
            clikeBean.setFe_num(bean.getList().get(position).getFe_num());
            clikeBean.setFe_id(bean.getList().get(position).getFe_id());
            clikeBean.setFu_id(bean.getList().get(position).getFu_id());
            clikeBean.setF_id(bean.getList().get(position).getF_id());
        }
        if(object instanceof HeatBean.DataBean.ListBeanXXXXXX._$5Bean){
            HeatBean.DataBean.ListBeanXXXXXX._$5Bean bean = (HeatBean.DataBean.ListBeanXXXXXX._$5Bean)object;
            clikeBean.setF_img(bean.getList().get(position).getF_img());
            clikeBean.setF_name(bean.getList().get(position).getF_name());
            clikeBean.setF_type(bean.getList().get(position).getF_type());
            clikeBean.setFe_cal(bean.getList().get(position).getFe_cal());
            clikeBean.setFe_ge(bean.getList().get(position).getFe_ge());
            clikeBean.setFe_num(bean.getList().get(position).getFe_num());
            clikeBean.setFe_id(bean.getList().get(position).getFe_id());
            clikeBean.setFu_id(bean.getList().get(position).getFu_id());
        }
        if(object instanceof HeatBean.DataBean.ListBeanXXXXXX._$6Bean){
            HeatBean.DataBean.ListBeanXXXXXX._$6Bean bean = (HeatBean.DataBean.ListBeanXXXXXX._$6Bean)object;
            clikeBean.setF_img(bean.getList().get(position).getF_img());
            clikeBean.setF_name(bean.getList().get(position).getF_name());
            clikeBean.setF_type(bean.getList().get(position).getF_type());
            clikeBean.setFe_cal(bean.getList().get(position).getFe_cal());
            clikeBean.setFe_ge(bean.getList().get(position).getFe_ge());
            clikeBean.setFe_num(bean.getList().get(position).getFe_num());
            clikeBean.setFe_id(bean.getList().get(position).getFe_id());
            clikeBean.setFu_id(bean.getList().get(position).getFu_id());
            clikeBean.setF_id(bean.getList().get(position).getF_id());
        }


        return clikeBean;
    }

    /*
    * 修改食物热量记录
    * */
    @Override
    public void updataStr(String fe_id, String fe_num, String fe_ge, String fu_id) {
        clearParams().setParams("fe_id",fe_id).setParams("fe_num",fe_num).setParams("fe_ge",fe_ge)
                .setParams("fu_id",fu_id).setParams("fe_date",showTime);
        Controller.myRequest(ConstantsCode.EDIT_FOOD_EATING,Constants.EDIT_FOOD_EATING,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
    * 删除食物热量记录
    * */
    @Override
    public void detelFood(String fe_id) {
        clearParams().setParams("fe_id",fe_id);
        Controller.myRequest(ConstantsCode.DEL_FOOD_EATING,Constants.DEL_FOOD_EATING,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }
}
