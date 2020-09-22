package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SccouponsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.FirstCategoryBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SccouponBeans;
import com.hongyuan.fitness.util.BaseUtil;

import java.util.List;

public class SccouponsFragment extends CustomFragment {

    private RecyclerView mRec;
    private SccouponsAdapter adapter;

    private List<SccouponBeans.DataBean.ListBean> mList;

    private FirstCategoryBeans.DataBean.ListBean item;

    private int couponPosition;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_recylerview;
    }

    @Override
    public void initView(View mView) {
        setEnableOverScrollDrag(true);

        item = (FirstCategoryBeans.DataBean.ListBean) getSerializableBeans("item");

        mRec = mView.findViewById(R.id.mRec);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRec.setLayoutManager(manager);
        adapter = new SccouponsAdapter();
        mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(mRec));

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.goodBox1){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(mList.get(position).getGoods_list().get(0).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }
            if(view.getId() == R.id.goodBox2){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(mList.get(position).getGoods_list().get(1).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }
            if(view.getId() == R.id.goodBox3){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(mList.get(position).getGoods_list().get(2).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }

            if(view.getId() == R.id.recCoupon){
                couponPosition = position;
                receiveCoupon(String.valueOf(mList.get(position).getCoupon_id()));
            }
        });

    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();

        if(BaseUtil.isValue(item)){
            setParams("store_categoryid",String.valueOf(item.getCategory_id()));
        }
        Controller.myRequest(Constants.GET_CATEGORY_COUPON_LIST,Controller.TYPE_POST,getParams(), SccouponBeans.class,this);
    }

    /*
     * 领取优惠券
     * */
    private void receiveCoupon(String coupon_id){
        mActivity.showLoading();
        clearParams().setParams("coupon_id",coupon_id);
        Controller.myRequest(ConstantsCode.GET_COUPON,Constants.GET_COUPON,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();

        if(data instanceof SccouponBeans){
            List<SccouponBeans.DataBean.ListBean> list = ((SccouponBeans)data).getData().getList();
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
