package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.InputOrSlectView;
import com.hongyuan.fitness.databinding.ActivitySOrderDetailsBinding;
import com.hongyuan.fitness.ui.shop.sactivity.ShopAddressActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SorderDetailsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.AddressInfoBeans;
import com.hongyuan.fitness.ui.shop.sbeans.OrderJsonBeans;
import com.hongyuan.fitness.ui.shop.sbeans.PickUpAddress;
import com.hongyuan.fitness.ui.shop.sbeans.ShopAddressBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SorderCouponBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SorderDetailBeans;
import com.hongyuan.fitness.ui.shop.sinterface.ChangeBottomAllPriceListener;
import com.hongyuan.fitness.ui.shop.sinterface.ViewRequestListener;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.SorderBottomViewModel;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.GsonUtil;
import com.hongyuan.fitness.util.LocationBean;

import java.util.ArrayList;
import java.util.List;

public class SorderDetailViewModel extends CustomViewModel implements ViewRequestListener, ChangeBottomAllPriceListener {

    private ActivitySOrderDetailsBinding binding;
    //底部viewModel
    private SorderBottomViewModel bottomViewModel;
    private SorderDetailsAdapter adapter;


    //地址选择之后回调的数据
    private ShopAddressBeans.DataBean addressBeans;
    //详情数据
    private AddressInfoBeans.DataBean infoData;

    public SorderDetailViewModel(CustomActivity mActivity, ActivitySOrderDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    public void setBottomViewModel(SorderBottomViewModel bottomViewModel){
        this.bottomViewModel = bottomViewModel;
    }

    @Override
    protected void initView() {


        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new SorderDetailsAdapter(new ArrayList<>(),this,this);
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRec));

        adapter.setOnItemChildClickListener((adapter, view, position) -> {

        });

        //去选择地址
        binding.goAddress.setOnClickListener(v -> {
            startActivityForResult(ShopAddressActivity.class,null);
        });
    }

    @Override
    public void forResult(Bundle bundle) {
        addressBeans = (ShopAddressBeans.DataBean) bundle.getSerializable("select");
        binding.telNum.setText(addressBeans.getTel());
        binding.address.setText(addressBeans.getProvince()+" "+addressBeans.getCity()+" "+addressBeans.getDistrict());
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_DEFAULT_RECEIVING_ADDRESS_INFO,Controller.TYPE_POST,getParams(), AddressInfoBeans.class,this);

        clearParams().setParams("cart_json",getBundle().getString("cartJson"));
        Controller.myRequest(Constants.GET_COMFIRM_ORDER_INFO,Controller.TYPE_POST,getParams(), SorderDetailBeans.class,this);


    }

    /*
    * 获取创建订单的json数据
    * */
    public String getJsonData(){
        List<OrderJsonBeans> jsonList = new ArrayList<>();
        for(MultiItemEntity itemEntity : adapter.getData()){

            if(itemEntity instanceof SorderDetailBeans.DataBean.ListBean){
                OrderJsonBeans jsonBeans = new OrderJsonBeans();

                SorderDetailBeans.DataBean.ListBean listBean = (SorderDetailBeans.DataBean.ListBean) itemEntity;

                jsonBeans.setStore_id(listBean.getStore_id());
                List<OrderJsonBeans.GoodsListBean> goods_list = new ArrayList<>();
                for(SorderDetailBeans.DataBean.ListBean.GoodsListBean goodsListBean : listBean.getGoods_list()){
                    OrderJsonBeans.GoodsListBean jsonGoodsBean = new OrderJsonBeans.GoodsListBean();
                    jsonGoodsBean.setBuy_num(goodsListBean.getBuy_num());
                    jsonGoodsBean.setCart_id(goodsListBean.getCart_id());
                    jsonGoodsBean.setGp_id(goodsListBean.getGp_id());
                    goods_list.add(jsonGoodsBean);
                }
                jsonBeans.setGoods_list(goods_list);

                jsonList.add(jsonBeans);
            }
            if(itemEntity instanceof SorderDetailBeans.DataBean.BottomBean){
                SorderDetailBeans.DataBean.BottomBean bottomBean = (SorderDetailBeans.DataBean.BottomBean)itemEntity;

                for(OrderJsonBeans jsonBeans : jsonList){
                    if(Integer.parseInt(bottomBean.getStoreId()) == jsonBeans.getStore_id()){
                        jsonBeans.setDeliver_way(bottomBean.getDeliverWay());
                        jsonBeans.setZt_address_id(bottomBean.getZtAddressId());
                        jsonBeans.setCm_id(bottomBean.getCmId());
                        jsonBeans.setOp_note(BaseUtil.isValue(bottomBean.getNote()) ? bottomBean.getNote() : "");
                    }
                }
            }
        }

        return GsonUtil.toJsonStr(jsonList);
    }

    /*
    * 获取收货地址数据
    * */
    public AddressInfoBeans.DataBean getAddressBeans(){
        if(addressBeans != null){
            infoData.setAddress(addressBeans.getAddress());
            infoData.setAddress_id(addressBeans.getAddress_id());
            infoData.setAddtime(addressBeans.getAddtime());
            infoData.setCid(addressBeans.getCid());
            infoData.setCity(addressBeans.getCity());
            infoData.setDid(addressBeans.getDid());
            infoData.setDistrict(addressBeans.getDistrict());
            infoData.setIs_default(addressBeans.getIs_default());
            infoData.setIs_del(addressBeans.getIs_del());
            infoData.setM_id(addressBeans.getM_id());
            infoData.setName(addressBeans.getName());
            infoData.setPid(addressBeans.getPid());
            infoData.setProvince(addressBeans.getProvince());
            infoData.setTel(addressBeans.getTel());
            infoData.setType_id(addressBeans.getType_id());
            infoData.setUpdatetime(addressBeans.getUpdatetime());
        }

        return infoData;
    }


    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof AddressInfoBeans){
            infoData = ((AddressInfoBeans)data).getData();
            binding.telNum.setText(infoData.getTel());
            binding.address.setText(infoData.getProvince()+" "+infoData.getCity()+" "+infoData.getDistrict());

        }

        if(data instanceof SorderDetailBeans){

            SorderDetailBeans.DataBean sorderBeans = ((SorderDetailBeans)data).getData();

            if(sorderBeans.getList() != null && sorderBeans.getList().size() > 0){
                if(sorderBeans.getmList() != null && sorderBeans.getmList().size() > 0){
                    adapter.setNewData(sorderBeans.getmList());

                    //回调总价格
                    if(bottomViewModel != null){
                        bottomViewModel.changeAllPrice(adapter.getAllPrice());
                    }

                    //打开所有子项列表
                    adapter.expandAll();
                    mActivity.setPromtView(mActivity.SHOW_DATA);
                }else{
                    mActivity.setPromtView(mActivity.SHOW_EMPTY);
                }
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }

        if(data instanceof PickUpAddress){
            PickUpAddress upAddress = ((PickUpAddress)data);
            //去记录当前请求的自提点数据
            adapter.setPickUpData(view,item.getStoreId(),upAddress);
            if(upAddress.getData().getList() != null && upAddress.getData().getList().size() > 0){
                item.setZtAddressId(upAddress.getData().getList().get(0).getZt_address_id());
            }
        }

        if(data instanceof SorderCouponBeans){
            List<SorderCouponBeans.DataBean.ListBean> couponList = ((SorderCouponBeans)data).getData().getList();
            adapter.setCouponData(couponView,allpriceView,item,couponList);
        }

    }

    //当前操作的数据源
    private SorderDetailBeans.DataBean.BottomBean item;
    //自提门店控件
    private View view;
    //优惠卷控件
    private InputOrSlectView couponView;
    //底部小计控件
    private TextView allpriceView;


    @Override
    public void listener(View v, View allpriceView, SorderDetailBeans.DataBean.BottomBean item) {
        this.item = item;

        mActivity.showLoading();
        if(v.getId() == R.id.myCoupon){
            couponView = (InputOrSlectView)v;
            this.allpriceView = (TextView) allpriceView;

            clearParams().setParams("store_id",item.getStoreId()).setParams("all_price", item.getAllPrice());
            Controller.myRequest(Constants.GET_ORDER_CONFIRM_COUPON_LIST,Controller.TYPE_POST,getParams(), SorderCouponBeans.class,this);
        }else{
            this.view = v;

            clearParams().setParams("store_id",item.getStoreId()).setParams("lat", LocationBean.getInstance().getLat())
                    .setParams("lng",LocationBean.getInstance().getLng());
            Controller.myRequest(Constants.GET_GOODS_ZT_ADDRESS,Controller.TYPE_POST,getParams(), PickUpAddress.class,this);
        }
    }

    /*
    * 改变底部总价格
    * */
    @Override
    public void changeAllprice(String showAllPrice) {
        if(bottomViewModel != null){
            bottomViewModel.changeAllPrice(showAllPrice);
        }
    }
}
