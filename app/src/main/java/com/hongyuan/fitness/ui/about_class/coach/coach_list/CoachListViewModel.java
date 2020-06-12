package com.hongyuan.fitness.ui.about_class.coach.coach_list;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityCoachListBinding;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachHomePageActivity;
import com.hongyuan.fitness.ui.about_class.coach.coach_search.CoachSearchActivity;
import com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home.VtwoStarCoachBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class CoachListViewModel extends CustomViewModel implements CoachDropMenuAdapter.OnFilterDoneListener, CoachDropMenuAdapter.OnFilterContentListener {

    private ActivityCoachListBinding binding;
    private VtwoStarCoachBean starCoachBean;
    private CoachListAdapter adapter;

    //筛选条件
    private String sex = "";//性别
    private String ft_ids = "";//类型id
    private String os_ids = "";//门店id
    private String region_code = "";//地区code



    public CoachListViewModel(CustomActivity mActivity, ActivityCoachListBinding binding)  {
        super(mActivity);
        this.binding = binding;
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        ImageView imageView = new ImageView(mActivity);
        imageView.setImageResource(R.mipmap.search_back_mark);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(params);
        mActivity.getMainTitle().addRightContentView(imageView);

        setOnRefresh();
        String[] titleList = new String[] { "湖州全城", "筛选私教" };
        binding.dropDownMenu.setMenuAdapter(new CoachDropMenuAdapter(mActivity, titleList, this,this));

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new CoachListAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("coach_mid",String.valueOf(starCoachBean.getData().getList().get(position).getM_id()));
                startActivity(CoachHomePageActivity.class,bundle);
            }
        });

        mActivity.getMainTitle().getRightView().setOnClickListener(v -> {
            startActivity(CoachSearchActivity.class,null);
        });
    }

    /*
     * 初始化加载控件各个设置
     * */
    private void setOnRefresh(){
        //关闭滚动到底部自动加载
        binding.mFilterContentView.setEnableAutoLoadMore(false);
        //设置主题颜色
        binding.mFilterContentView.setPrimaryColors(0xFFF2F2F2);
        //初始刷新动画
        binding.mFilterContentView.setRefreshHeader(new MaterialHeader(mActivity).setShowBezierWave(true));
        //初始化加载动画
        binding.mFilterContentView.setRefreshFooter(new BallPulseFooter(mActivity).setSpinnerStyle(SpinnerStyle.Scale));
        //关闭上拉加载更多
        binding.mFilterContentView.setEnableLoadMore(true);
        //是否开启自动刷新
        binding.mFilterContentView.setEnableRefresh(false);
        //是否开启刷新功能
        binding.mFilterContentView.setEnableRefresh(true);

        isLoadMore = true;
        binding.mFilterContentView.setOnRefreshListener(onRefresh());
        binding.mFilterContentView.setOnLoadMoreListener(onLoadMore());
    }

    /*
     * 下拉刷新监听
     * */
    private OnRefreshListener onRefresh() {
        return refreshLayout -> {
            curPage = 1;
            refreshData();
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
    public void refreshData(){
        starCoachBean = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        getCoachList();
    }

    /*
    * 查询私教列表
    * */
    private void getCoachList(){
        mActivity.showLoading();
        //首页--读取首页教练
        clearParams().setParams("city_name",userToken.getRegion_name());
        if(BaseUtil.isValue(sex)){
            setParams("sex",sex);
        }
        if(BaseUtil.isValue(ft_ids)){
            setParams("ft_ids",ft_ids);
        }
        if(BaseUtil.isValue(os_ids)){
            setParams("os_ids",os_ids);
        }
        if(BaseUtil.isValue(region_code)){
            setParams("region_code",region_code);
        }
        Controller.myRequest(Constants.GET_COACH_LIST,Controller.TYPE_POST,getParams(), VtwoStarCoachBean.class,this);
    }



    @Override
    public void onSuccess(Object data) {
        if(data instanceof VtwoStarCoachBean){
            VtwoStarCoachBean pageData = (VtwoStarCoachBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    starCoachBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    starCoachBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(starCoachBean != null && starCoachBean.getData() != null &&
                    starCoachBean.getData().getList() != null &&
                    starCoachBean.getData().getList().size() > 0){
                adapter.setNewData(starCoachBean.getData().getList());
                binding.loadBox.setVisibility(View.GONE);
                binding.mRecycler.setVisibility(View.VISIBLE);
            }else{
                binding.loadBox.setVisibility(View.VISIBLE);
                binding.mRecycler.setVisibility(View.GONE);
            }
        }
        mActivity.closeLoading();
    }

    @Override
    public void closeRefresh() {
        binding.mFilterContentView.finishRefresh();
        binding.mFilterContentView.finishLoadMore();
    }

    /*
     * 筛选条件弹框关闭回调
     * */
    @Override
    public void onFilterDone(String region_code, String filterCode, String os_ids, String ft_ids) {
        binding.dropDownMenu.close();
        this.sex = filterCode;
        this.region_code = region_code;
        this.ft_ids = ft_ids;
        this.os_ids = os_ids;

        //清除下原有数据
        starCoachBean = null;
        getCoachList();
    }

    @Override
    public void onFilterContent(int position, String changeText) {
        binding.dropDownMenu.setPositionIndicatorText(position,changeText);
    }
}
