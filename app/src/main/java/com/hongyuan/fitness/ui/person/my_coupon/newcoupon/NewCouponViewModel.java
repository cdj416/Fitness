package com.hongyuan.fitness.ui.person.my_coupon.newcoupon;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityNewMycouponBinding;
import com.hongyuan.fitness.ui.person.my_coupon.CouponAdapter;
import com.hongyuan.fitness.ui.person.my_coupon.CouponListBeans;
import com.hongyuan.fitness.ui.person.my_coupon.newcoupon.adapter.CouponDropMenuAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class NewCouponViewModel extends CustomViewModel implements CouponDropMenuAdapter.OnFilterDoneListener, CouponDropMenuAdapter.OnFilterContentListener {

    private ActivityNewMycouponBinding binding;

    private CouponAdapter adapter;
    private List<CouponListBeans.DataBean.ListBean> mList;

    //优惠价类型默认商城优惠劵
    private String coupon_way = "1";
    //是否使用默认表示未使用，1表示已使用
    private String is_use = "0";
    //是否过期表示未过期，1表示已过期
    private String is_exp = "0";

    public NewCouponViewModel(CustomActivity mActivity, ActivityNewMycouponBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setOnRefresh();

        String[] titleList = new String[] { "商城优惠劵", "平台优惠价" };
        binding.dropDownMenu.setMenuAdapter(new CouponDropMenuAdapter(mActivity, titleList, this,this));

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new CouponAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    /*
     * 初始化加载控件各个设置
     * */
    private void setOnRefresh(){
        //关闭滚动到底部自动加载
        binding.refresh.setEnableAutoLoadMore(false);
        //设置主题颜色
        binding.refresh.setPrimaryColors(0xFFF2F2F2);
        //初始刷新动画
        binding.refresh.setRefreshHeader(new MaterialHeader(mActivity).setShowBezierWave(true));
        //初始化加载动画
        binding.refresh.setRefreshFooter(new BallPulseFooter(mActivity).setSpinnerStyle(SpinnerStyle.Scale));
        //关闭上拉加载更多
        binding.refresh.setEnableLoadMore(true);
        //是否开启自动刷新
        binding.refresh.setEnableRefresh(false);
        //是否开启刷新功能
        binding.refresh.setEnableRefresh(true);

        isLoadMore = true;
        binding.refresh.setOnRefreshListener(onRefresh());
        binding.refresh.setOnLoadMoreListener(onLoadMore());
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
        //starCoachBean = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }


    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("coupon_way",coupon_way).setParams("is_use",is_use).setParams("is_exp",is_exp);
        Controller.myRequest(Constants.MY_COUPON_LIST,Controller.TYPE_POST,getParams(), CouponListBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof CouponListBeans) {
            List<CouponListBeans.DataBean.ListBean> list = ((CouponListBeans) data).getData().getList();
            if (curPage == FIRST_PAGE) {
                mList = list;
            } else {
                if (list != null && list.size() > 0) {
                    mList.addAll(list);
                } else {
                    binding.refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if (mList != null && mList.size() > 0) {
                adapter.setNewData(mList);
                mActivity.setPromtView(mActivity.SHOW_DATA);
            } else {
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }
    }

    @Override
    public void closeRefresh() {
        binding.refresh.finishRefresh();
        binding.refresh.finishLoadMore();
    }


    @Override
    public void onFilterContent(int position, String changeText) {
        binding.dropDownMenu.setPositionIndicatorText(position,changeText);
    }

    @Override
    public void onFilterDone(String coupon_way, String is_use, String is_exp) {
        this.coupon_way = coupon_way;
        this.is_use = is_use;
        this.is_exp = is_exp;

        lazyLoad();
    }
}
