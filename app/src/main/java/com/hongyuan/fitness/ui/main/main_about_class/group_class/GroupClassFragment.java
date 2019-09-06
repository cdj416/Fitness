package com.hongyuan.fitness.ui.main.main_about_class.group_class;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.MessageEvent;
import com.hongyuan.fitness.custom_view.CustomRecyclerView;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.MissionDetailActivity;
import com.hongyuan.fitness.ui.about_class.group_class.group_list.MyGroupClassActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail.select_time.TabTimeAdapter;
import com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail.select_time.TabTimeBean;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.DefaultStoreBean;
import com.hongyuan.fitness.ui.main.main_home.recommend.BoutiqueGroupBean;
import com.hongyuan.fitness.ui.store.more_store.MoreStoreActivity;
import com.hongyuan.fitness.ui.store.more_store.StoreBean;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.HiddenAnimUtils;
import com.hongyuan.fitness.util.LocationBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class GroupClassFragment extends CustomFragment {
    private RecyclerView mRecycler,timeRec;
    private CustomRecyclerView menuRecycler;
    private CardView cardViewBox;
    private GroupClassAdapter adapter;
    private TabTimeAdapter tabAdapter;
    private SelectTimeAdapter timeAdapter;

    private LinearLayout addressBox;
    private FrameLayout outBox;
    private RelativeLayout myCourseBox,load_box;
    private TextView addressName,selectTimeBox;

    private TabTimeBean tabTimeBean;
    private BoutiqueGroupBean groupBean;
    //门店id
    private String myOs_id = "";
    //开始时间数组
    private String[] startTime = new String[]{"全天","09:00:00","12:00:00","15:00:00","18:00:00"};
    //结束时间数组
    private String[] endTime = new String[]{"全天","12:00:00","15:00:00","18:00:00","21:00:00"};
    //选中日期
    private String selectData = "";

    //选中坐标
    private int mPosition = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_group_class;
    }

    @Override
    public void initView(View mView) {
        //开启刷新
        setEnableRefresh(true);
        setEnableLoadMore(true);

        mRecycler = mView.findViewById(R.id.mRecycler);
        menuRecycler = mView.findViewById(R.id.menuRecycler);
        addressBox = mView.findViewById(R.id.addressBox);
        addressName = mView.findViewById(R.id.addressName);
        timeRec = mView.findViewById(R.id.timeRec);
        cardViewBox = mView.findViewById(R.id.cardViewBox);
        selectTimeBox = mView.findViewById(R.id.selectTimeBox);
        outBox = mView.findViewById(R.id.outBox);
        myCourseBox = mView.findViewById(R.id.myCourseBox);
        load_box = mView.findViewById(R.id.load_box);

        //时间坐标
        LinearLayoutManager tabManager = new LinearLayoutManager(getContext());
        tabManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        menuRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,80,0x00000000));
        menuRecycler.setLayoutManager(tabManager);
        tabAdapter = new TabTimeAdapter();
        menuRecycler.setAdapter(tabAdapter);
        //头部日期的选择
        tabAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            selectData = changeTab(position);
            //日期变了，去获取一整天的团课列表
            groupBean = null;
            getCourseList(myOs_id,0);
        });


        //时间选择器
        LinearLayoutManager timeManager = new LinearLayoutManager(getContext());
        timeManager.setOrientation(RecyclerView.VERTICAL);
        timeRec.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,0xFFEEEEEE));
        timeRec.setLayoutManager(timeManager);
        timeAdapter = new SelectTimeAdapter();
        timeRec.setAdapter(timeAdapter);
        timeAdapter.setNewData(getTimeData());
        timeAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            List<TimeSelectBean> timeList = (List<TimeSelectBean>)adapter.getData();
            for (TimeSelectBean timeSelectBean : timeList){
                timeSelectBean.setSelect(false);
            }
            timeList.get(position).setSelect(true);
            timeAdapter.setNewData(timeList);
            //获取某一个时间段的团课
            this.mPosition = position;
            //请求
            lazyLoad();
            //关闭假弹框
            cardViewBox.setVisibility(View.GONE);
        });


        //团课列表
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
        manager2.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager2);
        adapter = new GroupClassAdapter();
        mRecycler.setAdapter(adapter);
        adapter.addFooterView(getFooterHeight(mRecycler));
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("cs_id",String.valueOf(groupBean.getData().getList().get(position).getCs_id()));
            ((CustomActivity)getContext()).startActivity(MissionDetailActivity.class,bundle);
        });

        //地址的点击事件
        addressBox.setOnClickListener(v -> startActivity(MoreStoreActivity.class,null));
        selectTimeBox.setOnClickListener(v ->{
            cardViewBox.setVisibility(View.VISIBLE);
        });
        //关闭时间选择框
        outBox.setOnClickListener(v -> cardViewBox.setVisibility(View.GONE));

        //点击进入我的团体课列表
        myCourseBox.setOnClickListener(v -> startActivity(MyGroupClassActivity.class,null));
    }

    /*
    * 组装时间选择数据
    * */
    private List<TimeSelectBean> getTimeData(){
        List<TimeSelectBean> times = new ArrayList<>();
        for(int i = 0 ; i < startTime.length ; i++){
            TimeSelectBean timeSelectBean = new TimeSelectBean();
            timeSelectBean.setEndTime(endTime[i]);
            timeSelectBean.setStartTime(startTime[i]);
            if(i == 0){
                timeSelectBean.setSelect(true);
            }else{
                timeSelectBean.setSelect(false);
            }
            times.add(timeSelectBean);
        }
        return times;
    }

    /*
     * 更改头部日期选中状态,并获取选中日期
     * */
    private String changeTab(int position){
        for(int i = 0 ; i < tabTimeBean.getData().size() ; i++){
            if(i == position){
                tabTimeBean.getData().get(i).setSelect(true);
            }else{
                tabTimeBean.getData().get(i).setSelect(false);
            }
        }
        tabAdapter.setNewData(tabTimeBean.getData());
        return tabTimeBean.getData().get(position).getNow_day();
    }

    /*
    * 查找今日坐标
    * */
    private int getTodayPosition(List<TabTimeBean.DataBean> mList){
        for(int i = 0 ; i < mList.size() ; i++){
            if(mList.get(i).getIs_cur_date() == 1){
                return i;
            }
        }
        return 0;
    }

    @Override
    public void loadMoreData() {
        getCourseList(myOs_id,mPosition);
    }

    @Override
    public void refreshData() {
        //已报名的团课(不要了)
        //clearParams().setParams("page","1").setParams("curpage","1");
        //Controller.myRequest(Constants.GET_MEMBER_SIGN_UP_COURSE_SUPER_LIST,Controller.TYPE_POST,getParams(), MyGroupClassBean.class,this);
    }

    @Override
    protected void lazyOnceLoad() {
        //请求时间
        Controller.myRequest(Constants.GET_PLAN_DATE,Controller.TYPE_POST,getParams(), TabTimeBean.class,this);

        //读取默认门店或者购买门店信息
        clearParams().setParams("lat", LocationBean.getInstance().getLat()).setParams("lng",LocationBean.getInstance().getLng());
        Controller.myRequest(Constants.GET_DEFAULT_OS,Controller.TYPE_POST,getParams(), DefaultStoreBean.class,this);
    }

    @Override
    protected void lazyLoad() {
        if(isValue(myOs_id)){
            groupBean = null;
            getCourseList(myOs_id,mPosition);
        }else{
            closeRefresh();
        }

    }

    /*
    * 获取选定时间的团课列表
    * */
    private void getCourseList(String os_id,int timePosition){
        clearParams().setParams("os_id",os_id);
        if(timePosition != 0){
            setParams("start_date",selectData+" "+startTime[timePosition]).setParams("end_date",selectData+" "+endTime[timePosition]);
        }else{
            setParams("start_date",selectData+" 09:00:00").setParams("end_date",selectData+" 23:59:00");
        }
        Controller.myRequest(ConstantsCode.GET_COURSE_SUPER_LIST,Constants.GET_COURSE_SUPER_LIST,Controller.TYPE_POST,getParams(), BoutiqueGroupBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof DefaultStoreBean && isSuccess(data)){
            DefaultStoreBean defaultStoreBean = (DefaultStoreBean) data;
            myOs_id = String.valueOf(defaultStoreBean.getData().getOs_id());
            addressName.setText(defaultStoreBean.getData().getOs_name());
            //获取团体课
            lazyLoad();
        }

        if(data instanceof TabTimeBean){
            tabTimeBean = (TabTimeBean)data;
            //更改默认选中项
            selectData = changeTab(getTodayPosition(tabTimeBean.getData()));
            tabAdapter.setNewData(tabTimeBean.getData());
            //获取一整天的团课
            getCourseList(myOs_id,0);
        }

        if(data instanceof MyGroupClassBean){
            MyGroupClassBean myGroupClassBean = (MyGroupClassBean)data;
            if(myGroupClassBean.getData().getList() != null && myGroupClassBean.getData().getList().size() > 0){
                myCourseBox.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.GET_COURSE_SUPER_LIST){
            BoutiqueGroupBean pageData = (BoutiqueGroupBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    groupBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    groupBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(groupBean != null && groupBean.getData() != null &&
                    groupBean.getData().getList() != null &&
                    groupBean.getData().getList().size() > 0){
                adapter.setNewData(groupBean.getData().getList());
                mRecycler.setVisibility(View.VISIBLE);
                load_box.setVisibility(View.GONE);
            }else{
                mRecycler.setVisibility(View.GONE);
                load_box.setVisibility(View.VISIBLE);
            }
        }
    }

    /*
     * 登录成功去刷新数据
     * */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(MessageEvent message) {
        if(message.getDataBean() == null){//登录成功之后需要去改变的数据
            //请求下数据
            onFragmentFirstVisible();
        }else if(message.getDataBean() instanceof StoreBean.DataBean.ListBean){//选择门店之后需要更改的
            StoreBean.DataBean.ListBean selectStoreBean = (StoreBean.DataBean.ListBean)message.getDataBean();
            LocationBean.getInstance().setOs_id(selectStoreBean.getOs_id());
            addressName.setText(selectStoreBean.getOs_name());
            myOs_id = String.valueOf(selectStoreBean.getOs_id());
            //门店变了，去重新获取门店里的团课
            getCourseList(myOs_id,0);
        }else if(message.getDataBean() instanceof DefaultStoreBean.DataBean){
            DefaultStoreBean.DataBean defaultStoreBean = (DefaultStoreBean.DataBean)message.getDataBean();
            addressName.setText(defaultStoreBean.getOs_name());
            myOs_id = String.valueOf(defaultStoreBean.getOs_id());
            //门店变了，去重新获取门店里的团课
            getCourseList(myOs_id,0);
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
