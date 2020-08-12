package com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.filter_view.DropDownMenu;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class VtwoPrivateLessonsFragment extends CustomFragment implements FilterPriviteLessonsAdapter.OnFilterDoneListener, FilterPriviteLessonsAdapter.OnFilterContentListener {

    private DropDownMenu dropDownMenu;
    private SmartRefreshLayout mFilterContentView;
    private RecyclerView mRecycler;

    private VtwoPrivateLessonsAdapter adapter;
    private VtwoPrivateLessonsBeans bean;


    //错误信息控件
    private RelativeLayout load_box;
    private TextView isEmpty,isTvErr;

    //条件筛选
    private String ft_ids;
    private String os_ids;
    private String region_code;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_vtwo_private_lessons;
    }

    @Override
    public void initView(View mView) {
        //skin = mActivity.skin;

        dropDownMenu = mView.findViewById(R.id.dropDownMenu);
        mFilterContentView = mView.findViewById(R.id.mFilterContentView);
        mRecycler = mView.findViewById(R.id.mRecycler);

        //加载错误页面信息
        initPrompt(mView);

        //初始化筛选菜单
        String[] titleList = new String[] { TokenSingleBean.getInstance().getRegion_name(), "筛选课程" };
        dropDownMenu.setMenuAdapter(new FilterPriviteLessonsAdapter(mActivity, titleList, this,this));

        //初始化筛选菜单
        //String[] titleList = new String[] { TokenSingleBean.getInstance().getRegion_name()+"全城", "筛选课程" };
        //dropDownMenu.setMenuAdapter(new FilterPriviteLessonsAdapter(mActivity, titleList, this,this));

        //加载刷新控件
        setOnRefresh();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new VtwoPrivateLessonsAdapter();
        mRecycler.setAdapter(adapter);
        adapter.addFooterView(getFooterHeight(mRecycler));

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("cp_id",String.valueOf(bean.getData().getList().get(position).getCp_id()));
                startActivity(CourseDetailsActivity.class,bundle);
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
        mFilterContentView.setEnableAutoLoadMore(false);
        //设置刷新监听
        mFilterContentView.setOnRefreshListener(onRefresh());
        //加载更多监听
        mFilterContentView.setOnLoadMoreListener(onLoadMore());
        //设置主题颜色
        mFilterContentView.setPrimaryColors(getResources().getColor(R.color.color_F5F6FB));
        //初始刷新动画
        mFilterContentView.setRefreshHeader(new MaterialHeader(mActivity).setShowBezierWave(true));
        //初始化加载动画
        mFilterContentView.setRefreshFooter(new BallPulseFooter(mActivity).setSpinnerStyle(SpinnerStyle.Scale));
        //开启上拉加载更多
        mFilterContentView.setEnableLoadMore(true);
        //开启刷新功能
        mFilterContentView.setEnableRefresh(true);

    }

    /*
     * 下拉刷新监听
     * */
    private OnRefreshListener onRefresh() {
        return refreshLayout -> {
            //刷新，初始化页数为1
            curPage = FIRST_PAGE;
            bean = null;
            getCourserData();
        };
    }

    /*
     * 上啦加载更多监听
     * */
    private OnLoadMoreListener onLoadMore(){
        return refreshLayout -> {
            curPage++;
            loadMoreData();
        };
    }

    @Override
    protected void lazyLoad() {
        getCourserData();
    }

    @Override
    public void loadMoreData() {
        getCourserData();
    }

    public void getCourserData(){
        mActivity.showLoading();
        clearParams().setParams("city_name", TokenSingleBean.getInstance().getRegion_name());
        //课程--私教课列表
        if(BaseUtil.isValue(ft_ids)){
            setParams("ft_ids",ft_ids);
        }
        if(BaseUtil.isValue(os_ids)){
            setParams("os_ids",os_ids);
        }
        if(BaseUtil.isValue(region_code)){
            setParams("region_code",region_code);
        }
        Controller.myRequest(Constants.GET_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), VtwoPrivateLessonsBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        VtwoPrivateLessonsBeans pageData = (VtwoPrivateLessonsBeans)data;
        if(curPage == FIRST_PAGE){
            if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                bean = pageData;
            }
        }else{
            if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                bean.getData().getList().addAll(pageData.getData().getList());
            }
        }

        if(bean != null && bean.getData() != null &&
                bean.getData().getList() != null &&
                bean.getData().getList().size() > 0){
            adapter.setNewData(bean.getData().getList());

            setPromtView(SHOW_DATA);
        }else{
            setPromtView(SHOW_EMPTY);
        }
    }

    @Override
    public void closeRefresh() {
        if(mFilterContentView != null){
            mFilterContentView.finishRefresh();
            mFilterContentView.finishLoadMore();
        }
    }

    @Override
    public void onError(int err_code, String description) {
        setPromtView(SHOW_ERR);
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

    @Override
    public void onFilterDone(String ft_ids, String os_ids, String region_code) {
        dropDownMenu.close();
        this.ft_ids = ft_ids;
        this.os_ids = os_ids;
        this.region_code = region_code;

        bean = null;
        getCourserData();
    }

    /*
    * 条件筛选的显示回调
    * */
    @Override
    public void onFilterContent(int position, String changeText) {
        dropDownMenu.setPositionIndicatorText(position,changeText);
    }

    /*
     * 刷新定位城市
     * */
    /*@Subscribe(id = ConstantsCode.EB_HOME_LOCATION)
    public void changeLocation(String message) {
        //城市切换了去刷新下数据
        bean = null;
        getCourserData();

        //初始化筛选菜单
        String[] titleList = new String[] { TokenSingleBean.getInstance().getRegion_name()+"全城", "筛选课程" };
        if(filterAdapter == null){
            filterAdapter = new FilterPriviteLessonsAdapter(mActivity, titleList, this,this);
            dropDownMenu.setMenuAdapter(filterAdapter);
        }else{
            filterAdapter.changTitles(titleList);
        }


    }*/

    /*@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }*/
}
