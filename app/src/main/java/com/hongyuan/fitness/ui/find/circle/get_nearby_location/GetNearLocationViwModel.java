package com.hongyuan.fitness.ui.find.circle.get_nearby_location;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.MyApplication;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityGetNearbyLocationBinding;
import com.hongyuan.fitness.ui.find.circle.edit_post.EditPostViewModel;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.LocationBean;
import com.hongyuan.fitness.util.MyLocationUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import static me.goldze.mvvmhabit.utils.Utils.getContext;

public class GetNearLocationViwModel extends CustomViewModel implements PoiSearch.OnPoiSearchListener {
    private ActivityGetNearbyLocationBinding binding;
    private GetNearyLocationAdapter adapter;

    //自定义结果集
    private List<NearLocationBeans> mlist = new ArrayList<>();
    //搜索的字符串
    private String searchStr = "";

    public GetNearLocationViwModel(CustomActivity mActivity, ActivityGetNearbyLocationBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        //初始化标题栏
        mActivity.setsetImmersive(0x55000000);
        mActivity.getMainTitle().setLeftImage(R.mipmap.close_heise_img);

        binding.noAddress.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("NearLocationBeans",null);
            bundle.putInt("type", EditPostViewModel.ADDRESS);
            setResult(bundle);
        });

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,0xFFEEEEEE));
        binding.mRecycler.setLayoutManager(manager);
        adapter = new GetNearyLocationAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("NearLocationBeans",mlist.get(position));
                bundle.putInt("type", EditPostViewModel.ADDRESS);
                setResult(bundle);
            }
        });


        binding.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchStr = s.toString();
                curPage = 1;
                doSearchQuery(s.toString());
            }
        });
        setOnRefresh();
    }

    /*
     * 初始化加载控件各个设置
     * */
    private void setOnRefresh(){
        //关闭滚动到底部自动加载
        binding.refresh.setEnableAutoLoadMore(false);
        //设置主题颜色
        binding.refresh.setPrimaryColors(mActivity.getResources().getColor(R.color.color_FFFFFF));
        //初始刷新动画
        binding.refresh.setRefreshHeader(new MaterialHeader(mActivity).setShowBezierWave(true));
        //初始化加载动画
        binding.refresh.setRefreshFooter(new BallPulseFooter(mActivity).setSpinnerStyle(SpinnerStyle.Scale));
        //关闭上拉加载更多
        binding.refresh.setEnableLoadMore(true);
        //加载更多监听
        binding.refresh.setOnLoadMoreListener(onLoadMore());
        //是否开启自动刷新
        binding.refresh.setEnableRefresh(false);
        //是否开启刷新功能
        binding.refresh.setEnableRefresh(true);
        //设置刷新监听事件
        binding.refresh.setOnRefreshListener(onRefresh());
        //手动设置开启分页
        isLoadMore = true;
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

    /*
     * 下拉刷新监听
     * */
    private OnRefreshListener onRefresh() {
        return refreshLayout -> {
            curPage = 1;
            refreshData();
        };
    }

    @Override
    public void refreshData() {
        doSearchQuery(searchStr);
    }

    @Override
    protected void loadMoreData() {
        doSearchQuery(searchStr);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        //加载附近数据
        doSearchQuery("");
    }

    @Override
    public void closeRefresh() {
        if(binding.refresh != null){
            binding.refresh.finishRefresh();
            binding.refresh.finishLoadMore();
        }
    }



    @Override
    public void onSuccess(Object data) {

    }

    /**
     * 搜索操作
     * @param searchContent 搜索类容
     */
    public void doSearchQuery(String searchContent) {
        //获得小点
        LatLonPoint latLonPoint = new LatLonPoint(Double.valueOf(LocationBean.getInstance().getLat()),Double.valueOf(LocationBean.getInstance().getLng()));
        //第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        PoiSearch.Query query = new PoiSearch.Query(searchContent,"",LocationBean.getInstance().getCityName());
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(curPage);// 设置查第一页
        PoiSearch poiSearch = new PoiSearch(MyApplication.getInstance(),query);
        poiSearch.setOnPoiSearchListener(this);//设置回调数据的监听器
        PoiSearch.SearchBound searchBound;
        //点附近2000米内的搜索结果
        if (latLonPoint!=null){
            searchBound = new PoiSearch.SearchBound(latLonPoint,1000);
            poiSearch.setBound(searchBound);
        }
        poiSearch.searchPOIAsyn();//开始搜索
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if(i == MyLocationUtil.SEARCH_SUCCESS){
            if (poiResult != null && poiResult.getQuery() != null){
                // 取得搜索到的poiitems有多少页
                List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                if(curPage == 1){
                    mlist.clear();
                }
                if(poiItems.size() > 0){
                    for(PoiItem poiItem:poiItems){
                        NearLocationBeans beans = new NearLocationBeans();
                        beans.setLatitude(String.valueOf(poiItem.getLatLonPoint().getLatitude()));
                        beans.setLongitude(String.valueOf(poiItem.getLatLonPoint().getLongitude()));
                        beans.setSnippet(poiItem.getSnippet());
                        beans.setTitle(poiItem.getTitle());
                        mlist.add(beans);
                    }
                }


                adapter.setNewData(mlist);
                mActivity.closeLoading();
                closeRefresh();
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
