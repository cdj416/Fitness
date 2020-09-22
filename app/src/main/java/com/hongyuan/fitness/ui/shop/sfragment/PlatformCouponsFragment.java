package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sadapter.PlatformCouponAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.PlatformCouponsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.PlatformCouponsTitleBeans;
import com.hongyuan.fitness.util.BaseUtil;

import java.util.List;

public class PlatformCouponsFragment extends CustomFragment {

    private RecyclerView mRec;
    private PlatformCouponAdapter adapter;

    private List<PlatformCouponsBeans.DataBean.ListBean> mList;

    private PlatformCouponsTitleBeans.DataBean item;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_recylerview;
    }

    @Override
    public void initView(View mView) {
        setEnableOverScrollDrag(true);

        item = (PlatformCouponsTitleBeans.DataBean) getSerializableBeans("item");

        mRec = mView.findViewById(R.id.mRec);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRec.setLayoutManager(manager);
        adapter = new PlatformCouponAdapter();
        mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(mRec));

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            receiveCoupon(String.valueOf(mList.get(position).getCoupon_id()));
        });

    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();

        if(BaseUtil.isValue(item)){
            setParams("coupon_for",String.valueOf(item.getCoupon_for()));
        }
        Controller.myRequest(Constants.OS_COUPON_LIST,Controller.TYPE_POST,getParams(), PlatformCouponsBeans.class,this);
    }

    /*
     * 领取优惠券
     * */
    private void receiveCoupon(String coupon_id){
        mActivity.showLoading();
        clearParams().setParams("coupon_id",coupon_id);
        Controller.myRequest(ConstantsCode.GET_COUPON,Constants.GET_COUPON,Controller.TYPE_POST,getParams(), PlatformCouponsBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof PlatformCouponsBeans){
            List<PlatformCouponsBeans.DataBean.ListBean> list = ((PlatformCouponsBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();

        if(code == ConstantsCode.GET_COUPON){
            lazyLoad();
            showSuccess("领取成功！");
        }
    }
}
