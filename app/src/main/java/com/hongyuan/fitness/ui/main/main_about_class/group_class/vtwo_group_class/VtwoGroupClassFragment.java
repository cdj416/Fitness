package com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.CustomRecyclerView;
import com.hongyuan.fitness.custom_view.filter_view.DropDownMenu;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.MissionDetailActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail.select_time.TabTimeBean;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class VtwoGroupClassFragment extends CustomFragment implements FilterGoupClassAdapter.OnFilterDoneListener, FilterGoupClassAdapter.OnFilterContentListener {

    private DropDownMenu dropDownMenu;
    private LinearLayout mFilterContentView;
    private CustomRecyclerView timeRec;
    private RecyclerView mRecycler;
    private SmartRefreshLayout refreshLayout;

    private VtwoGroupClassTimeAdapter timeAdapter;
    private TabTimeBean tabTimeBean;

    private FilterGoupClassAdapter filterAdapter;

    private VtwoGroupClassAdapter adapter;
    private VtwoGroupClassBeans.DataBean classBeans;

    //条件筛选
    private String prefixData = "";
    private String start_date = "";
    private String end_date = "";
    private String si_ids = "";
    private String os_ids = "";

    //错误信息控件
    private RelativeLayout load_box;
    private TextView isEmpty,isTvErr;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_vtwo_group_class;
    }

    @Override
    public void initView(View mView) {
        skin = mActivity.skin;

        dropDownMenu = mView.findViewById(R.id.dropDownMenu);
        mFilterContentView = mView.findViewById(R.id.mFilterContentView);
        timeRec = mView.findViewById(R.id.timeRec);
        mRecycler = mView.findViewById(R.id.mRecycler);
        refreshLayout = mView.findViewById(R.id.refreshLayout);

        //初始化筛选菜单
        String[] titleList = new String[] { TokenSingleBean.getInstance().getRegion_name(), "筛选课程","开始时间" };
        dropDownMenu.setMenuAdapter(new FilterGoupClassAdapter(mActivity, titleList, this,this));

        //加载刷新控件
        setOnRefresh();
        //加载错误页面信息
        initPrompt(mView);
        //初始化时间选择栏
        setTimeRec();
        //初始化显示内容控件
        inintContent();
    }

    /*
     * 初始化时间选择栏
     * */
    private void setTimeRec(){
        //时间坐标
        LinearLayoutManager tabManager = new LinearLayoutManager(getContext());
        tabManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        timeRec.setLayoutManager(tabManager);
        timeAdapter = new VtwoGroupClassTimeAdapter();
        timeRec.setAdapter(timeAdapter);
        timeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick()
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                prefixData = changeTab(position);
                //拼接时间
                start_date = prefixData +" 00:00";
                end_date = prefixData +" 23:59";

                //刷新，初始化页数为1
                curPage = FIRST_PAGE;
                classBeans = null;
                getGroupClass();
            }
        });
    }

    /*
    * 初始化页面内容控件
    * */
    private void inintContent(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new VtwoGroupClassAdapter();
        mRecycler.setAdapter(adapter);
        adapter.addFooterView(getFooterHeight(mRecycler));

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("cs_id",String.valueOf(classBeans.getList().get(position).getCs_id()));
                    startActivity(MissionDetailActivity.class,bundle);
                }catch (Exception e){
                    CustomDialog.showMessage(mActivity,"请下拉刷新数据！");
                }

            }
        });
    }

    /*
     * 初始化加载控件各个设置
     * */
    private void setOnRefresh(){
        //单独开启页数控制
        isLoadMore = true;
        //关闭滚动到底部自动加载
        refreshLayout.setEnableAutoLoadMore(false);
        //设置刷新监听
        refreshLayout.setOnRefreshListener(onRefresh());
        //加载更多监听
        refreshLayout.setOnLoadMoreListener(onLoadMore());
        //设置主题颜色
        refreshLayout.setPrimaryColors(getResources().getColor(R.color.transparent));
        //初始刷新动画
        refreshLayout.setRefreshHeader(new MaterialHeader(mActivity).setShowBezierWave(true));
        //初始化加载动画
        refreshLayout.setRefreshFooter(new BallPulseFooter(mActivity).setSpinnerStyle(SpinnerStyle.Scale));
        //开启上拉加载更多
        refreshLayout.setEnableLoadMore(true);
        //开启刷新功能
        refreshLayout.setEnableRefresh(true);

    }

    /*
     * 下拉刷新监听
     * */
    private OnRefreshListener onRefresh() {
        return refreshLayout -> {
            //刷新，初始化页数为1
            curPage = FIRST_PAGE;
            classBeans = null;
            getGroupClass();
        };
    }

    /*
     * 上啦加载更多监听
     * */
    private OnLoadMoreListener onLoadMore(){
        return refreshLayout -> {
            curPage++;
            getGroupClass();
        };
    }

    @Override
    protected void lazyOnceLoad() {
        //请求时间
        Controller.myRequest(Constants.GET_PLAN_DATE,Controller.TYPE_POST,getParams(), TabTimeBean.class,this);
    }

    /*
    * 请求团课
    * */
    private void getGroupClass(){
        mActivity.showLoading();
        clearParams();
        if(BaseUtil.isValue(start_date)){
            setParams("start_date",start_date);
        }
        if(BaseUtil.isValue(end_date)){
            setParams("end_date",end_date);
        }
        if(BaseUtil.isValue(si_ids)){
            setParams("si_ids",si_ids);
        }
        if(BaseUtil.isValue(os_ids)){
            setParams("os_ids",os_ids);
        }
        //请求团课
        Controller.myRequest(Constants.GET_TWO_COURSE_SUPER_LIST,Controller.TYPE_POST,getParams(), VtwoGroupClassBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof TabTimeBean){
            tabTimeBean = (TabTimeBean)data;
            //更改默认选中项
            prefixData = changeTab(getTodayPosition(tabTimeBean.getData()));
            //拼接时间
            start_date = prefixData +" 00:00";
            end_date = prefixData +" 23:59";

            timeAdapter.setNewData(tabTimeBean.getData());
            //请求团课
            getGroupClass();
        }

        if(data instanceof VtwoGroupClassBeans){
            VtwoGroupClassBeans.DataBean pageData = ((VtwoGroupClassBeans)data).getData();
            if(curPage == FIRST_PAGE){
                if(pageData.getList() != null && pageData.getList().size() > 0){
                    classBeans = pageData;
                }
            }else{
                if(pageData.getList() != null && pageData.getList().size() > 0){
                    classBeans.getList().addAll(pageData.getList());
                }else{
                    //refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }

            if(classBeans != null  &&
                    classBeans.getList() != null &&
                    classBeans.getList().size() > 0){
                adapter.setNewData(classBeans.getList());

                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }
    }

    @Override
    public void closeRefresh() {
        if(refreshLayout != null){
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }
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
        timeAdapter.setNewData(tabTimeBean.getData());
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

    /*
     * 加载页面显示效果
     * */
    private void initPrompt(View mView){
        load_box = mView.findViewById(R.id.load_box);
        isEmpty = mView.findViewById(R.id.isEmpty);
        isTvErr = mView.findViewById(R.id.isErr);
    }

    /*
     * 显示页面效果切换
     * */
    public void setPromtView(int type){
        if(type == SHOW_ERR){
            mRecycler.setVisibility(View.GONE);
            load_box.setVisibility(View.VISIBLE);
            isTvErr.setVisibility(View.VISIBLE);
            isEmpty.setVisibility(View.GONE);
        }else if(type == SHOW_EMPTY){
            mRecycler.setVisibility(View.GONE);
            load_box.setVisibility(View.VISIBLE);
            isTvErr.setVisibility(View.GONE);
            isEmpty.setVisibility(View.VISIBLE);
        }else{
            load_box.setVisibility(View.GONE);
            mRecycler.setVisibility(View.VISIBLE);
        }
    }


    /*
     * 条件筛选回调
     * */
    @Override
    public void onFilterDone(String si_ids, String os_ids, String start_date, String end_date) {
        dropDownMenu.close();
        this.si_ids = si_ids;
        this.os_ids = os_ids;
        if(BaseUtil.isValue(start_date) && BaseUtil.isValue(end_date)){
            this.start_date = prefixData+" "+start_date;
            this.end_date = prefixData+" "+end_date;
        }else{
            this.start_date = prefixData+" 00:00";
            this.end_date = prefixData+" 23:59";
        }

        classBeans = null;
        getGroupClass();
    }

    /*
    * 条件筛选之后菜单栏所显示的类容
    * */
    @Override
    public void onFilterContent(int position,String showText) {
        dropDownMenu.setPositionIndicatorText(position,showText);
    }

    /*
     * 刷新定位城市
     * */
    @Subscribe(id = ConstantsCode.EB_HOME_LOCATION)
    public void changeLocation(String message) {
        //城市切换了去刷新下数据
        //classBeans = null;
        //getGroupClass();

        /*//初始化筛选菜单
        String[] titleList = new String[] { TokenSingleBean.getInstance().getRegion_name()+"全城", "筛选课程","开始时间" };
        if(filterAdapter == null){
            filterAdapter = new FilterGoupClassAdapter(mActivity, titleList, this,this);
            dropDownMenu.setMenuAdapter(filterAdapter);
        }else{
            filterAdapter.changTitles(titleList);
        }*/


    }

    private String skin;

    @Override
    public void onResume() {
        super.onResume();
        //每次来，去刷新下当前模式
        if(!skin.equals(mActivity.skin) && filterAdapter != null){
            skin = mActivity.skin;
            //从新绘制头部视图
            dropDownMenu.setContentView(mFilterContentView);
            //从新添加数据源
            dropDownMenu.setMenuAdapter(filterAdapter);
            //刷新下显示样式
            filterAdapter.changeSkin();
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
