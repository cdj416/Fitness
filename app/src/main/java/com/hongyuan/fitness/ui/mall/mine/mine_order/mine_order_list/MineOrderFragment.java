package com.hongyuan.fitness.ui.mall.mine.mine_order.mine_order_list;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.mall.good_order_details.PointBean;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayActivity;
import com.hongyuan.fitness.ui.mall.good_pay.PayDataBean;
import com.hongyuan.fitness.ui.mall.mine.mine_order.order_details.MineOrderDetailsActivity;
import com.hongyuan.fitness.ui.promt_success.V3SuccessBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MineOrderFragment extends CustomFragment {

    private RecyclerView typeRec,mRecycler;
    private TypeAdapter typeAdapter;
    private MineOrderAdapter adapter;
    private LinearLayout topBox;
    private RelativeLayout load_box;

    //用户积分
    private PointBean pointBean;

    //状态栏
    private List<TypeBeans> mList = new ArrayList<>();
    //订单信息
    private List<MineOrderBeans.DataBean.ListBean> orderList;
    //点击取消订单的坐标
    private int mPosition;

    //支付状态
    private String o_pay_state = "0";


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_order;
    }

    @Override
    public void initView(View mView) {
        //开启下拉刷新
        setEnableRefresh(true);
        //开启上啦加载更多
        setEnableLoadMore(true);

        typeRec = mView.findViewById(R.id.typeRec);
        mRecycler = mView.findViewById(R.id.mRecycler);
        topBox = mView.findViewById(R.id.topBox);
        load_box = mView.findViewById(R.id.load_box);

        //初始化状态栏数据
        getStatusList();

        LinearLayoutManager menuManager = new LinearLayoutManager(getContext());
        menuManager.setOrientation(RecyclerView.HORIZONTAL);
        typeRec.setLayoutManager(menuManager);
        typeAdapter = new TypeAdapter();
        typeRec.setAdapter(typeAdapter);
        typeAdapter.setNewData(mList);
        typeRec.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,50,0x00000000));
        typeAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            for (TypeBeans beans : mList){
                beans.setSelect(false);
            }
            mList.get(position).setSelect(true);
            typeAdapter.setNewData(mList);
            //赋值支付状态
            o_pay_state = mList.get(position).getTypeId();
            if(orderList != null){
                orderList.clear();
            }

            getData();
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new MineOrderAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
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
        });

        if(getFragType().equals("o_goods") || getFragType().equals("o_card")){
            topBox.setVisibility(View.VISIBLE);
        }else{
            topBox.setVisibility(View.GONE);
        }
    }

    /*
     * 组装订单显示信息
     * */
    private V3SuccessBeans getSuccessBeans(int mPosition){
        V3SuccessBeans beans = new V3SuccessBeans();

        if("o_card".equals(orderList.get(mPosition).getO_type_code())){
            beans.setTitleText("订单完成");
            beans.setShowText("购买成功");
            beans.setBtn1Text("跳过");
            beans.setBtn2Text("人脸识别录入");
            List<V3SuccessBeans.ItemConten> list = new ArrayList<>();

            V3SuccessBeans.ItemConten itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent(orderList.get(mPosition).getO_name());
            itemConten.setItemTitle("会籍卡名称:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent("￥"+ BaseUtil.getNoZoon(orderList.get(mPosition).getO_price()));
            itemConten.setItemTitle("价格:");
            list.add(itemConten);

            if(BaseUtil.isValue(orderList.get(mPosition).getO_coupon_money()) && Double.valueOf(orderList.get(mPosition).getO_coupon_money()) > 0){
                itemConten = new V3SuccessBeans.ItemConten();
                itemConten.setContent("-¥"+BaseUtil.getNoZoon(orderList.get(mPosition).getO_coupon_money()));
                itemConten.setItemTitle("优惠:");
                list.add(itemConten);
            }
            beans.setItemContens(list);
        }

        if("o_pric".equals(orderList.get(mPosition).getO_type_code())){
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
        }

        if("o_goods".equals(orderList.get(mPosition).getO_type_code())){
            beans.setTitleText("订单");
            beans.setShowText("购买成功");
            beans.setBtn2Text("完成");
            List<V3SuccessBeans.ItemConten> list = new ArrayList<>();

            V3SuccessBeans.ItemConten itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent(orderList.get(mPosition).getO_name());
            itemConten.setItemTitle("商品名:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent("¥"+ BaseUtil.getNoZoon(orderList.get(mPosition).getO_price()));
            itemConten.setItemTitle("单价:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent("x"+orderList.get(mPosition).getO_num());
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
        }



        return beans;
    }

    /*
    * 组装状态栏数据
    * */
    private void getStatusList(){
        TypeBeans typeBeans1 = new TypeBeans("全部","0",true);
        TypeBeans typeBeans2 = new TypeBeans("已支付","1",false);
        TypeBeans typeBeans3 = new TypeBeans("未支付","2",false);

        mList.add(typeBeans1);
        mList.add(typeBeans2);
        mList.add(typeBeans3);

        if(getFragType().equals("o_goods")){
            TypeBeans typeBeans4 = new TypeBeans("已收货","5",false);
            mList.add(typeBeans4);
        }

    }

    /*
    * 上啦加载更多
    * */
    @Override
    public void loadMoreData() {
        getData();
    }

    @Override
    protected void lazyLoad() {
        if(orderList != null){
            orderList.clear();
        }

        getData();
    }

    @Override
    protected void lazyOnceLoad() {
        clearParams();
        Controller.myRequest(Constants.GET_MEMBER_POINT,Controller.TYPE_POST,getParams(), PointBean.class,this);
    }

    /*
     * 取消订单
     * */
    private void getCancelOrder(String o_id){
        clearParams().setParams("o_id",o_id);
        Controller.myRequest(ConstantsCode.CANCLE_ORDER,Constants.CANCLE_ORDER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
    * 请求数据
    * */
    private void getData(){
        clearParams().setParams("o_pay_state",o_pay_state).setParams("o_type_code",getFragType());
        if("5".equals(o_pay_state)){
            setParams("o_state",o_pay_state);
        }
        Controller.myRequest(Constants.GET_ORDER_LIST,Controller.TYPE_POST,getParams(), MineOrderBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PointBean){
            pointBean = (PointBean)data;
        }

        if(data instanceof MineOrderBeans){
            List<MineOrderBeans.DataBean.ListBean> list = ((MineOrderBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                orderList = list;
            }else{
                if(list != null && list.size() > 0){
                    orderList.addAll(list);
                }else{
                    refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(orderList != null && orderList.size() > 0){
                adapter.setNewData(orderList);
                mRecycler.setVisibility(View.VISIBLE);
                load_box.setVisibility(View.GONE);
            }else{
                mRecycler.setVisibility(View.GONE);
                load_box.setVisibility(View.VISIBLE);
            }
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
