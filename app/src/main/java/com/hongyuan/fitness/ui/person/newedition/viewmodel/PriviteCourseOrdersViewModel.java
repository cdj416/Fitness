package com.hongyuan.fitness.ui.person.newedition.viewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.AcitivityPriviteCourseOrdersBinding;
import com.hongyuan.fitness.ui.mall.good_order_details.PointBean;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayActivity;
import com.hongyuan.fitness.ui.mall.good_pay.PayDataBean;
import com.hongyuan.fitness.ui.mall.mine.mine_order.mine_order_list.MineOrderBeans;
import com.hongyuan.fitness.ui.mall.mine.mine_order.order_details.MineOrderDetailsActivity;
import com.hongyuan.fitness.ui.person.newedition.adapter.MemberCardOrdersAdapter;
import com.hongyuan.fitness.ui.promt_success.V3SuccessBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.ArrayList;
import java.util.List;

public class PriviteCourseOrdersViewModel extends CustomViewModel {

    private AcitivityPriviteCourseOrdersBinding binding;

    private MemberCardOrdersAdapter adapter;
    //点击取消订单的坐标
    private int mPosition;
    //订单信息
    private List<MineOrderBeans.DataBean.ListBean> orderList;
    //用户积分
    private PointBean pointBean;

    public PriviteCourseOrdersViewModel(CustomActivity mActivity, AcitivityPriviteCourseOrdersBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new MemberCardOrdersAdapter();
        binding.mRec.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mPosition = position;

                if(view.getId() == R.id.jumpBox){
                    Bundle bundle = new Bundle();
                    bundle.putString("o_id",String.valueOf(orderList.get(position).getO_id()));
                    startActivity(MineOrderDetailsActivity.class,bundle);
                }
                if(view.getId() == R.id.cancelOrder){
                    mPosition = position;
                    CustomDialog.promptDialog(mActivity, "确定要取消订单？", "再想想", "确定", false, v -> {
                        if(v.getId() == R.id.isCannel){
                            getCancelOrder(String.valueOf(orderList.get(position).getO_id()));
                        }
                    });
                }
                if(view.getId() == R.id.goPay){
                    try {
                        PayDataBean payDataBean = new PayDataBean();
                        payDataBean.setO_id(String.valueOf(orderList.get(position).getO_id()));
                        payDataBean.setShowPoint(String.valueOf(orderList.get(position).getO_point()));
                        payDataBean.setShowPrice(orderList.get(position).getO_money());
                        payDataBean.setLavePoint(String.valueOf(pointBean.getData().getPoint()));
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("payDataBean",payDataBean);

                        bundle.putSerializable("successBeans",getSuccessBeans(position));

                        startActivity(GoodsPayActivity.class,bundle);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /*
     * 组装订单显示信息
     * */
    private V3SuccessBeans getSuccessBeans(int mPosition){
        V3SuccessBeans beans = new V3SuccessBeans();

        beans.setTitleText("订单");
        beans.setShowText("购买成功");
        beans.setBtn2Text("完成");
        List<V3SuccessBeans.ItemConten> list = new ArrayList<>();

        V3SuccessBeans.ItemConten itemConten = new V3SuccessBeans.ItemConten();
        itemConten.setContent(orderList.get(mPosition).getO_name()+" / 一对一私教课");
        itemConten.setItemTitle("课程类型:");
        list.add(itemConten);

        itemConten = new V3SuccessBeans.ItemConten();
        itemConten.setContent("¥"+ BaseUtil.getNoZoon(orderList.get(mPosition).getO_price())+"/节");
        itemConten.setItemTitle("单价:");
        list.add(itemConten);

        itemConten = new V3SuccessBeans.ItemConten();
        itemConten.setContent(orderList.get(mPosition).getO_num()+"节");
        itemConten.setItemTitle("数量:");
        list.add(itemConten);

        itemConten = new V3SuccessBeans.ItemConten();
        itemConten.setContent("¥"+BaseUtil.getNoZoon(orderList.get(mPosition).getO_money()));
        itemConten.setItemTitle("总价:");
        list.add(itemConten);

        if(BaseUtil.isValue(orderList.get(mPosition).getO_coupon_money()) && Double.valueOf(orderList.get(mPosition).getO_coupon_money()) > 0){
            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent("-¥"+BaseUtil.getNoZoon(orderList.get(mPosition).getO_coupon_money()));
            itemConten.setItemTitle("优惠:");
            list.add(itemConten);
        }

        beans.setItemContens(list);

        return beans;
    }

    @Override
    public void refreshData(){
        curPage = FIRST_PAGE;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }
    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_MEMBER_POINT,Controller.TYPE_POST,getParams(), PointBean.class,this);

        clearParams().setParams("o_type_code","o_pric");
        Controller.myRequest(Constants.GET_ORDER_LIST,Controller.TYPE_POST,getParams(), MineOrderBeans.class,this);
    }

    /*
     * 取消订单
     * */
    private void getCancelOrder(String o_id){
        clearParams().setParams("o_id",o_id);
        Controller.myRequest(ConstantsCode.CANCLE_ORDER,Constants.CANCLE_ORDER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof MineOrderBeans){
            List<MineOrderBeans.DataBean.ListBean> list = ((MineOrderBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                orderList = list;
            }else{
                if(list != null && list.size() > 0){
                    orderList.addAll(list);
                }else{
                    mActivity.refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(orderList != null && orderList.size() > 0){
                adapter.setNewData(orderList);
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }

        if(data instanceof PointBean){
            pointBean = (PointBean)data;
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.CANCLE_ORDER){
            orderList.remove(mPosition);
            adapter.notifyDataSetChanged();
            showSuccess("成功取消订单！");
        }
    }
}
