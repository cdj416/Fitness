package com.hongyuan.fitness.ui.person.daily_punch;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.StickyScrollView;
import com.hongyuan.fitness.databinding.ActivityDailyPunchBinding;
import com.hongyuan.fitness.ui.find.circle.edit_post.EditPostActivity;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.out_door.RunActivity;
import com.hongyuan.fitness.ui.person.push_share.PushShareActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BigDecimalUtils;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.HiddenAnimUtils;
import com.hongyuan.fitness.util.TimeUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.List;

public class DailyPunchViewModel extends CustomViewModel implements StickyScrollView.ScrollViewListener {

    private ActivityDailyPunchBinding binding;
    private DailyPunchdapter adapter;
    //任务列表
    private TaskAdapter taskAdapter;
    //任务列表数据
    private List<TaskBeans.DataBean.ListBeanX.ListBean> taskList;

    //获取打卡统计数据
    private DailyPunchCheckBean dailyPunchCheckBean;

    //连续打卡天数
    private int lxDayx;
    //打卡获取的积分数
    private String pointNum;
    //伸缩高度
    private int shrinkHeight;
    //展开收缩动画工具类
    private HiddenAnimUtils animUtils;

    public DailyPunchViewModel(CustomActivity mActivity, ActivityDailyPunchBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        binding.dataText.setText(TimeUtil.getStringByFormat(System.currentTimeMillis(),TimeUtil.dateFormatYMnian));

        //禁止滑动
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity,7){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        binding.mRecycler.setLayoutManager(gridLayoutManager);
        adapter = new DailyPunchdapter();
        binding.mRecycler.setAdapter(adapter);


        LinearLayoutManager taskManager = new LinearLayoutManager(mActivity);
        taskManager.setOrientation(RecyclerView.VERTICAL);
        binding.taskRec.setLayoutManager(taskManager);
        taskAdapter = new TaskAdapter();
        binding.taskRec.setAdapter(taskAdapter);
        taskAdapter.setOnItemChildClickListener((adapter, view, position) -> itemClickDealWith(position));


        binding.punchBox.setOnClickListener(v -> checkIn());
        binding.openBox.setOnClickListener(v -> {
            if(animUtils != null){
                animUtils.toggleHeight();
            }
        });
        binding.sharePush.setOnClickListener(v -> {
            startActivity(PushShareActivity.class,null);
        });

        //设置滚动监听
        binding.scroll.setScrollViewListener(this);
    }

    @Override
    public void onScrollChanged(StickyScrollView scrollView, int x, int y, int oldx, int oldy) {
        if(y != oldy){
            if(animUtils != null && !animUtils.isOpen()){
                animUtils.toggleHeight();
            }
        }
    }

    /*
    * 会员签到
    * */
    private void checkIn(){
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(ConstantsCode.ADD_QD,Constants.ADD_QD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        getMonthQd();
        //检查是否签到
        Controller.myRequest(Constants.CHECK_IS_QD,Controller.TYPE_POST,getParams(), DailyPunchCheckBean.class,this);
    }

    @Override
    public void refreshData() {
        clearParams();
        mActivity.showLoading();
        //积分--获取积分任务列表
        Controller.myRequest(Constants.GETRWLIST,Controller.TYPE_POST,getParams(), TaskBeans.class,this);
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
            List<DailyPunchDataListBean.DataBean.ListBean> mList = setEmpty(dataBeans.getData().getList());
            adapter.setNewData(mList);
            //计算出要收缩的高度
            if(mList.size() % 7 == 0){
                shrinkHeight =  mList.size()/7*32;
            }else{
                shrinkHeight =  (mList.size()/7 + 1)*32;
            }

            //初始化动画对象
            animUtils = HiddenAnimUtils.newInstance(mActivity,binding.mRecycler,binding.openMark,shrinkHeight,32,false);
            mActivity.closeLoading();


        }
        if(data instanceof DailyPunchCheckBean){
            dailyPunchCheckBean = (DailyPunchCheckBean)data;
            lxDayx = dailyPunchCheckBean.getData().getItem().getLx_day();
            pointNum = String.valueOf(dailyPunchCheckBean.getData().getItem().getPoint());

            binding.punchNum.setText("本月已累计打卡"+dailyPunchCheckBean.getData().getItem().getLeiji_days()+"天");
            binding.obtainNum.setText(String.valueOf(dailyPunchCheckBean.getData().getItem().getPoint()));
            binding.buttonShow.setText("已连续打卡"+lxDayx+"天");
            changeQD(dailyPunchCheckBean.getData().getItem().getIs_qd());
        }

        if(data instanceof TaskBeans){
            TaskBeans taskBeans = (TaskBeans)data;
            taskList = taskBeans.getData().getList().getList();
            binding.pointNum.setText(BaseUtil.getNoZoon(taskBeans.getData().getList().getHave_point()));
            binding.bubbleProgress.setProgressWithAnim(Float.valueOf(BigDecimalUtils.div(String.valueOf(taskBeans.getData().getList().getToday_num()),"100",2)));
            taskAdapter.setNewData(taskList);
            mActivity.closeLoading();
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.ADD_QD){
            mActivity.closeLoading();
            refreshData();
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
            binding.punchBox.setBackgroundResource(R.drawable.shape_radius6_cccccc);
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
        String oneNowDate = mList.get(0).getNow_date();
        if(empterNum == 0){
            empterNum = 7;
        }
        for(int i = 1 ; i < empterNum ; i++){
            DailyPunchDataListBean.DataBean.ListBean listBean = new DailyPunchDataListBean.DataBean.ListBean();
            listBean.setIs_cur_date(0);
            listBean.setIs_qd(0);
            listBean.setNow_date(TimeUtil.getStringByOffset(oneNowDate,TimeUtil.dateFormatYMD, Calendar.DATE,i - empterNum));
            listBean.setWeek("-1");
            mList.add((i-1),listBean);
        }
        return mList;
    }



    /*
    * 积分列表获取列表点击事件处理
    * */
    private void itemClickDealWith(int position){
        switch (taskList.get(position).getCode()){
            case "ka_share":
                startActivity(PushShareActivity.class,null);
                break;
            case "qd":
                checkIn();
                break;
            case "gz":
                //通过EventBus去通知MainActivity显示第二页
                EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"1");
                startActivity(MainActivity.class,null);
                break;
            case "run":
                startActivity(RunActivity.class,null);
                break;
            case "add_circle_praise":
                //通过EventBus去通知MainActivity显示第二页
                EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"1");
                startActivity(MainActivity.class,null);
                break;
            case "add_circle":
                startActivity(EditPostActivity.class,null);
                break;
        }
    }


}
