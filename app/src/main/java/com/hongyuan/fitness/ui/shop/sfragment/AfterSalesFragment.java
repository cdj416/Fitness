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
import com.hongyuan.fitness.ui.shop.sactivity.EdLogisticsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.RefundPricesActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ReturnPriceDetailsActivity;
import com.hongyuan.fitness.ui.shop.sadapter.AfterSlesListAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.AfterSalesOrderListBeans;
import com.hongyuan.fitness.ui.shop.sbeans.AftersaleOrderBeans;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.List;

public class AfterSalesFragment extends CustomFragment {
    //订单状态 1 待付款 2已取消 3已付款 4已发货5已提货 8已确定 9最终完成

    private RecyclerView mRec;
    private AfterSlesListAdapter adapter;

    //数据集
    private List<AfterSalesOrderListBeans.DataBean.ListBean> mList;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_recylerview;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        mRec = mView.findViewById(R.id.mRec);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRec.setLayoutManager(manager);
        adapter = new AfterSlesListAdapter();
        mRec.setAdapter(adapter);
        adapter.addFooterView(getFooterHeight(mRec));

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.cancelReturn){
                CustomDialog.promptDialog(mActivity, "确定要撤销？", "确定", "取消", false, v -> {
                    if(v.getId() == R.id.isOk){
                        cancelReturn(String.valueOf(mList.get(position).getOpg_id()));
                    }
                });
            }
            if(view.getId() == R.id.againApply){
                AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean bean = new AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean();
                bean.setBuy_num(mList.get(position).getBuy_num());
                bean.setG_id(mList.get(position).getG_id());
                bean.setG_img(mList.get(position).getG_img());
                bean.setG_name(mList.get(position).getG_name());
                bean.setGp_point(mList.get(position).getPoint());
                bean.setGp_price(mList.get(position).getRefund_money());
                bean.setOpg_id(mList.get(position).getOpg_id());
                bean.setRefund_state(mList.get(position).getRefund_state());
                bean.setSku_names(mList.get(position).getSku_names());
                Bundle bundle = new Bundle();
                bundle.putSerializable("item",bean);
                bundle.putInt("is_refund",1);
                bundle.putInt("state",0);
                startActivity(RefundPricesActivity.class,bundle);
            }

            if(view.getId() == R.id.updateApply){
                AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean bean = new AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean();
                bean.setBuy_num(mList.get(position).getBuy_num());
                bean.setG_id(mList.get(position).getG_id());
                bean.setG_img(mList.get(position).getG_img());
                bean.setG_name(mList.get(position).getG_name());
                bean.setGp_point(mList.get(position).getPoint());
                bean.setGp_price(mList.get(position).getRefund_money());
                bean.setOpg_id(mList.get(position).getOpg_id());
                bean.setRefund_state(mList.get(position).getRefund_state());
                bean.setSku_names(mList.get(position).getSku_names());
                Bundle bundle = new Bundle();
                bundle.putSerializable("item",bean);
                bundle.putInt("is_refund",-1);
                bundle.putInt("state",0);
                startActivity(RefundPricesActivity.class,bundle);
            }

            if(view.getId() == R.id.etLogtesg){
                AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean bean = new AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean();
                bean.setBuy_num(mList.get(position).getBuy_num());
                bean.setG_id(mList.get(position).getG_id());
                bean.setG_img(mList.get(position).getG_img());
                bean.setG_name(mList.get(position).getG_name());
                bean.setGp_point(mList.get(position).getPoint());
                bean.setGp_price(mList.get(position).getRefund_money());
                bean.setOpg_id(mList.get(position).getOpg_id());
                bean.setRefund_state(mList.get(position).getRefund_state());
                bean.setSku_names(mList.get(position).getSku_names());

                Bundle bundle = new Bundle();
                bundle.putString("opg_id",String.valueOf(mList.get(position).getOpg_id()));
                bundle.putSerializable("item",bean);
                startActivity(EdLogisticsActivity.class,bundle);
            }

            if(view.getId() == R.id.lookDetail || view.getId() == R.id.goDetail){
                Bundle bundle = new Bundle();
                bundle.putString("opg_id",String.valueOf(mList.get(position).getOpg_id()));
                startActivity(ReturnPriceDetailsActivity.class,bundle);
            }
        });
    }

    /*
    * 撤销退款
    * */
    private void cancelReturn(String opg_id){
        clearParams().setParams("opg_id",opg_id);
        Controller.myRequest(ConstantsCode.CANCEL_APPLY_REFUND,Constants.CANCEL_APPLY_REFUND,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }


    @Override
    public void refreshData() {
        lazyLoad();
    }


    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("o_type_code","o_goods").setParams("is_refund","1");

        Controller.myRequest(Constants.GET_REFUND_ORDER_LIST,Controller.TYPE_POST,getParams(), AfterSalesOrderListBeans.class,this);

    }

    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof AfterSalesOrderListBeans){

            List<AfterSalesOrderListBeans.DataBean.ListBean> list = ((AfterSalesOrderListBeans)data).getData().getList();
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
        if(code == ConstantsCode.CANCEL_APPLY_REFUND){
            lazyLoad();
            showSuccess("撤销成功！");
        }
    }
}
