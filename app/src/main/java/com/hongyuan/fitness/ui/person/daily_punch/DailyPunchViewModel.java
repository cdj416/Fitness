package com.hongyuan.fitness.ui.person.daily_punch;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityDailyPunchBinding;
import com.hongyuan.fitness.ui.mall.good_order_details.PointBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.List;

public class DailyPunchViewModel extends CustomViewModel {

    private ActivityDailyPunchBinding binding;
    private DailyPunchdapter adapter;

    //连续打卡天数
    private int lxDayx;
    //打卡获取的积分数
    private String pointNum;

    public DailyPunchViewModel(CustomActivity mActivity, ActivityDailyPunchBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity,7);
        binding.mRecycler.setLayoutManager(gridLayoutManager);
        adapter = new DailyPunchdapter();
        binding.mRecycler.setAdapter(adapter);

        binding.punchBox.setOnClickListener(v -> checkIn());
    }

    /*
    * 会员签到
    * */
    private void checkIn(){
        Controller.myRequest(ConstantsCode.ADD_QD,Constants.ADD_QD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    protected void lazyLoad() {
        getMonthQd();
        //检查是否签到
        Controller.myRequest(Constants.CHECK_IS_QD,Controller.TYPE_POST,getParams(), DailyPunchCheckBean.class,this);
        //获取我的积分
        Controller.myRequest(Constants.GET_MEMBER_POINT,Controller.TYPE_POST,getParams(), PointBean.class,this);
    }

    /*
    * 查询签到列表
    * */
    private void getMonthQd(){
        //签到列表
        Controller.myRequest(Constants.GET_MONTH_QD,Controller.TYPE_POST,getParams(), DailyPunchDataListBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof DailyPunchDataListBean){
            DailyPunchDataListBean dataBeans = (DailyPunchDataListBean)data;
            adapter.setNewData(setEmpty(dataBeans.getData().getList()));
        }
        if(data instanceof DailyPunchCheckBean){
            DailyPunchCheckBean dailyPunchCheckBean = (DailyPunchCheckBean)data;
            lxDayx = dailyPunchCheckBean.getData().getItem().getLx_day();
            pointNum = String.valueOf(dailyPunchCheckBean.getData().getItem().getPoint());

            binding.punchNum.setText("已连续打卡"+lxDayx+"天");
            binding.obtainNum.setText(String.valueOf(dailyPunchCheckBean.getData().getItem().getPoint()));
            binding.buttonShow.setText("已连续打卡"+lxDayx+"天");
            changeQD(dailyPunchCheckBean.getData().getItem().getIs_qd());
        }
        if(data instanceof PointBean){
            PointBean pointBean = (PointBean)data;
            binding.pointNum.setText(BaseUtil.getNoZoon(pointBean.getData().getPoint()));
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.ADD_QD){
            getMonthQd();
            CustomDialog.dailyPunchSuccess(mActivity,"+"+pointNum,String.valueOf(lxDayx+1));
            changeQD("1");
        }
    }

    /*
    * 更改是否可签到样式
    * */
    private void changeQD(String type){
        if("1".equals(type)){
            binding.qdText.setText("今日已打卡");
            binding.punchBox.setClickable(false);
            binding.punchBox.setBackgroundResource(R.drawable.shape_radius5_cccccc);
        }else{
            binding.punchBox.setClickable(true);
            binding.punchBox.setBackgroundResource(R.drawable.shape_gradient_v_radiu5_login);
        }
    }

    /*
    * 给前面添加空数据，用来站位
    * */
    private List<DailyPunchDataListBean.DataBean.ListBean> setEmpty(List<DailyPunchDataListBean.DataBean.ListBean> mList){
        int empterNum = Integer.valueOf(mList.get(0).getWeek());
        if(empterNum == 0){
            empterNum = 7;
        }
        for(int i = 1 ; i < empterNum ; i++){
            mList.add((i-1),new DailyPunchDataListBean.DataBean.ListBean());
        }
        return mList;
    }
}
