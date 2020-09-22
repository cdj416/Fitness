package com.hongyuan.fitness.ui.person.my_coupon.receive_coupon_list;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.person.my_coupon.CouponListBeans;
import java.util.List;

public class ReceiveCouponListFragment extends CustomFragment {


    private RecyclerView mRecycler;

    //优惠券适配器
    private ReceiveListCouponAdapter adapter;
    private List<CouponListBeans.DataBean.ListBean> mList;
    private int mPosition;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_receive_coupon;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);


        mRecycler = mView.findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new ReceiveListCouponAdapter();
        mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mPosition = position;
                getRecevie(String.valueOf(mList.get(position).getCoupon_id()));
            }
        });
    }

    @Override
    protected void lazyLoad() {
        getData();
    }

    @Override
    public void loadMoreData() {
        getData();
    }

    /*
    * 请求优惠券
    * */
    private void getData(){
        mActivity.showLoading();
        clearParams().setParams("coupon_type",getFragType())
                .setParams("os_id", mActivity.getBundle().getString("os_id"));
        Controller.myRequest(Constants.ALL_COUPON_LIST,Controller.TYPE_POST,getParams(), CouponListBeans.class,this);
    }

    /*
    * 领取优惠券
    * */
    private void getRecevie(String couponId){
        mActivity.showLoading();
        clearParams().setParams("coupon_id",couponId);
        Controller.myRequest(ConstantsCode.GET_COUPON,Constants.GET_COUPON,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();

        if(data instanceof CouponListBeans){
            List<CouponListBeans.DataBean.ListBean> list = ((CouponListBeans)data).getData().getList();
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
            mList.get(mPosition).setReceive(true);
            adapter.notifyDataSetChanged();
            showSuccess("领取成功！");
        }
    }
}
