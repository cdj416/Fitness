package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.CustomRecyclerView;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SDMimgAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SMGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SstoreGouponAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.GoodsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.HabitGoddsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.StoreAllGoodsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.StoreCouponBeans;

import java.util.ArrayList;
import java.util.List;

public class SstoreGoodsFragment extends CustomFragment {

    private CustomRecyclerView couponRec;
    private SstoreGouponAdapter topAdapter;

    private List<StoreCouponBeans.DataBean> couponList;
    //记录index
    private int mPosition;

    private RecyclerView mRec;
    private SMGoodsAdapter gAdapter;

    private List<StoreAllGoodsBeans.DataBean.ListBean> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_store_goods;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        couponRec = mView.findViewById(R.id.couponRec);
        mRec = mView.findViewById(R.id.mRec);

        LinearLayoutManager topManager = new LinearLayoutManager(getContext());
        topManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        couponRec.setLayoutManager(topManager);
        topAdapter = new SstoreGouponAdapter();
        couponRec.setAdapter(topAdapter);
        topAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            mPosition = position;
            getReceive(String.valueOf(couponList.get(position).getCoupon_id()));
        });

        GridLayoutManager layoutManager =
                new GridLayoutManager(mActivity,2);
        mRec.setLayoutManager(layoutManager);
        gAdapter = new SMGoodsAdapter<StoreAllGoodsBeans.DataBean.ListBean>() {
            @Override
            public String getImg(StoreAllGoodsBeans.DataBean.ListBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(StoreAllGoodsBeans.DataBean.ListBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(StoreAllGoodsBeans.DataBean.ListBean item) {
                return item.getG_price();
            }

            @Override
            public String getPoint(StoreAllGoodsBeans.DataBean.ListBean item) {
                return String.valueOf(item.getG_point());
            }

            @Override
            public int getShowType(StoreAllGoodsBeans.DataBean.ListBean item) {
                return item.getShowType();
            }
        };
        mRec.setAdapter(gAdapter);
        gAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(mList.get(position).getG_id()));
            startActivity(SgoodsDetailActivity.class,bundle);
        });
    }

    /*
     * 加载更多
     * */
    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("store_id",mActivity.getBundle().getString("store_id"));
        Controller.myRequest(Constants.GET_STORE_GOODS_LIST,Controller.TYPE_POST,getParams(), StoreAllGoodsBeans.class,this);

        //查询优惠卷
        clearParams().setParams("store_id",mActivity.getBundle().getString("store_id"));
        Controller.myRequest(Constants.GET_STORE_COUPON_LIST,Controller.TYPE_POST,getParams(), StoreCouponBeans.class,this);
    }

    /*
     * 领取优惠券
     * */
    private void getReceive(String couponId){
        mActivity.showLoading();
        clearParams().setParams("coupon_id",couponId);
        Controller.myRequest(ConstantsCode.GET_COUPON,Constants.GET_COUPON,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();

        if(data instanceof StoreAllGoodsBeans){
            List<StoreAllGoodsBeans.DataBean.ListBean> list = ((StoreAllGoodsBeans)data).getData().getList();
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
                gAdapter.setNewData(mList);
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }

        if(data instanceof StoreCouponBeans){
            couponList = ((StoreCouponBeans)data).getData();

            if(couponList == null || couponList.size() <= 0){
                couponRec.setVisibility(View.GONE);
            }else{
                topAdapter.setNewData(couponList);
            }

        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.GET_COUPON){
            couponList.remove(mPosition);
            topAdapter.setNewData(couponList);
            showSuccess("领取成功！");
        }
    }
}
