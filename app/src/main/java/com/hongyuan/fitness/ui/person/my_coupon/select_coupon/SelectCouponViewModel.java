package com.hongyuan.fitness.ui.person.my_coupon.select_coupon;

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
import com.hongyuan.fitness.databinding.ActivitySelectCouponBinding;
import com.hongyuan.fitness.ui.person.my_coupon.CouponListBeans;
import com.hongyuan.fitness.ui.person.my_coupon.receive_coupon_list.ReceiveCouponListActivity;

import java.util.List;

public class SelectCouponViewModel extends CustomViewModel {

    private ActivitySelectCouponBinding binding;
    private SelectCouponAdapter adapter;
    private List<CouponListBeans.DataBean.ListBean> mList;

    public SelectCouponViewModel(CustomActivity mActivity, ActivitySelectCouponBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        mActivity.getMainTitle().setRightText("领取");
        mActivity.getMainTitle().getRightView().setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                startActivity(ReceiveCouponListActivity.class,null);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new SelectCouponAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.addFooterView(mActivity.getFooterHeight(binding.mRecycler));

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("coupon",mList.get(position));
                setResult(bundle);
            }
        });

        binding.noUse.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("coupon",null);
            setResult(bundle);
        });
    }

    @Override
    public void refreshData() {
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("is_use","0").setParams("coupon_for",getBundle("bundle").getString("couponFor"))
                .setParams("total_money",getBundle("bundle").getString("totalMoney"));
        Controller.myRequest(Constants.MY_COUPON_LIST,Controller.TYPE_POST,getParams(), CouponListBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof CouponListBeans){
            List<CouponListBeans.DataBean.ListBean> list = ((CouponListBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    mActivity.refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                setSelect(mList);
                adapter.setNewData(mList);
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }
    }

    /*
    * 修改选中项
    * */
    private void setSelect(List<CouponListBeans.DataBean.ListBean> mList){
        for (int i = 0 ; i < mList.size() ; i++){
            if(getBundle("bundle").getInt("couponId") == mList.get(i).getCoupon_id()){
                mList.get(i).setSelect(true);
                return;
            }
        }
    }
}
