package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.scllor_view.UnitBeanUtils;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayActivity;
import com.hongyuan.fitness.ui.mall.good_pay.PayDataBean;
import com.hongyuan.fitness.ui.promt_success.V3SuccessBeans;
import com.hongyuan.fitness.ui.shop.sactivity.AftersaleOrderActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ProductReviewActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SnewOrdersAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.CancelOrderReasonBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SnewOrderBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.ArrayList;
import java.util.List;

public class SnewOrdersFragment extends CustomFragment {

    //订单状态 1 待付款 2已取消 3已付款 4已发货5已提货 8已确定 9最终完成

    private RecyclerView mRec;
    private SnewOrdersAdapter adapter;

    //数据集
    private List<MultiItemEntity> mList;

    private UnitBeanUtils utils;
    //原因集
    private List<CancelOrderReasonBeans.DataBean.ListBean> rList;

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
        adapter = new SnewOrdersAdapter(new ArrayList<>());
        mRec.setAdapter(adapter);
        adapter.addFooterView(getFooterHeight(mRec));

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.cancelOrder){
                SnewOrderBeans.DataBean.BottomBean item = (SnewOrderBeans.DataBean.BottomBean)mList.get(position);

                CustomDialog.scroller(mActivity, utils.getUnitList(rList), "订单取消", (v, message) -> {
                    cancelOrder(String.valueOf(item.getO_id()),utils.getUseId(message));
                });
            }

            if(view.getId() == R.id.goPay){
                SnewOrderBeans.DataBean.BottomBean item = (SnewOrderBeans.DataBean.BottomBean)mList.get(position);

                PayDataBean payDataBean = new PayDataBean();
                payDataBean.setO_id(String.valueOf(item.getO_id()));
                payDataBean.setShowPoint("");
                payDataBean.setShowPrice(item.getAllPrice());
                payDataBean.setLavePoint("");
                Bundle bundle = new Bundle();
                bundle.putSerializable("payDataBean",payDataBean);
                bundle.putSerializable("successBeans",getSuccessBeans());
                startActivity(GoodsPayActivity.class,bundle);
            }

            if(view.getId() == R.id.dDelivery){
                CustomDialog.showMessage(mActivity,"已催促商家发货！");
            }

            if(view.getId() == R.id.submitGoods || view.getId() == R.id.selfReceipt){
                SnewOrderBeans.DataBean.BottomBean item = (SnewOrderBeans.DataBean.BottomBean)mList.get(position);
                CustomDialog.promptDialog(mActivity, "是否确认收货？", "确定收货", "在想想", false, v -> {
                    if(v.getId() == R.id.isOk){
                        confirmOrder(String.valueOf(item.getO_id()));
                    }
                });
            }

            if(view.getId() == R.id.goAginBugy){
                SnewOrderBeans.DataBean.BottomBean item = (SnewOrderBeans.DataBean.BottomBean)mList.get(position);
                if(item.getO_id() != 0){
                    Bundle bundle = new Bundle();
                    bundle.putString("g_id",String.valueOf(item.getG_id()));
                    startActivity(SgoodsDetailActivity.class,bundle);
                }else{
                    CustomDialog.showMessage(mActivity,"商品已下架");
                }

            }

            if(view.getId() == R.id.goEvaluate){
                SnewOrderBeans.DataBean.BottomBean item = (SnewOrderBeans.DataBean.BottomBean)mList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("o_id",String.valueOf(item.getO_id()));
                startActivity(ProductReviewActivity.class,bundle);
            }

            if(view.getId() == R.id.titleBox){
                SnewOrderBeans.DataBean.ListBean oneBean = (SnewOrderBeans.DataBean.ListBean) mList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("o_id",String.valueOf(oneBean.getO_id()));
                startActivity(AftersaleOrderActivity.class,bundle);
            }

            if(view.getId() == R.id.goodsBox){
                SnewOrderBeans.DataBean.ListBean.GoodsListBean goodsListBean = (SnewOrderBeans.DataBean.ListBean.GoodsListBean) mList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("o_id",String.valueOf(goodsListBean.getG_oid()));
                startActivity(AftersaleOrderActivity.class,bundle);
            }

            if(view.getId() == R.id.lookSelfAddress){
                SnewOrderBeans.DataBean.BottomBean item = (SnewOrderBeans.DataBean.BottomBean)mList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("o_id",String.valueOf(item.getO_id()));
                startActivity(AftersaleOrderActivity.class,bundle);
            }

        });
    }

    @Override
    public void refreshData() {
        lazyLoad();
    }

    /*
     * 组装订单显示信息
     * */
    private V3SuccessBeans getSuccessBeans(){
        V3SuccessBeans beans = new V3SuccessBeans();
        beans.setType(V3SuccessBeans.TYPE.BUYGOODS);
        beans.setTitleText("订单");
        beans.setShowText("购买成功");
        beans.setBtn2Text("完成");

        return beans;
    }

    //取消订单
    private void cancelOrder(String o_id,String reason_id){
        mActivity.showLoading();
        clearParams().setParams("o_id",o_id).setParams("reason_id",reason_id);
        Controller.myRequest(ConstantsCode.CANCLE_ORDER,Constants.CANCLE_ORDER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    //确认订单
    private void confirmOrder(String o_id){
        mActivity.showLoading();
        clearParams().setParams("o_id",o_id);
        Controller.myRequest(ConstantsCode.CONFIRM_ORDER,Constants.CONFIRM_ORDER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("o_type_code","o_goods");
        if(BaseUtil.isValue(getFragType()) && !"is_evaluation".equals(getFragType()) && !"is_refund".equals(getFragType())){
            setParams("o_state",getFragType());
        }

        if("is_evaluation".equals(getFragType())){
           setParams("wait_pj","1");
        }

        if("is_refund".equals(getFragType())){
            setParams("is_refund","1");
        }

        Controller.myRequest(Constants.GET_ORDER_LIST,Controller.TYPE_POST,getParams(), SnewOrderBeans.class,this);

        if(!BaseUtil.isValue(getFragType()) || "1".equals(getFragType())){
            getReason();
        }
    }

    //取消订单原因
    private void getReason(){
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_CANCEL_REASON_LIST,Controller.TYPE_POST,getParams(), CancelOrderReasonBeans.class,this);
    }

    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof SnewOrderBeans){
            SnewOrderBeans.DataBean dataBean = ((SnewOrderBeans)data).getData();

            if(curPage == FIRST_PAGE){
                mList = dataBean.getmList();
            }else{
                if(dataBean.getmList() != null && dataBean.getmList().size() > 0){
                    mList.addAll(dataBean.getmList());
                }else{
                    refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                //打开所有子项列表
                adapter.expandAll();

                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }

        if(data instanceof CancelOrderReasonBeans){
            rList = ((CancelOrderReasonBeans)data).getData().getList();

            utils = new UnitBeanUtils<CancelOrderReasonBeans.DataBean.ListBean>() {
                @Override
                public String unit(CancelOrderReasonBeans.DataBean.ListBean o) {
                    return String.valueOf(o.getReason_id());
                }

                @Override
                public String unit_cn(CancelOrderReasonBeans.DataBean.ListBean o) {
                    return o.getName();
                }
            };
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();

        if(code == ConstantsCode.CANCLE_ORDER){
            //刷新数据
            lazyLoad();
            showSuccess("订单已取消");
        }

        if(code == ConstantsCode.CONFIRM_ORDER){
            //刷新数据
            lazyLoad();
            showSuccess("已确认收货");
        }
    }
}
