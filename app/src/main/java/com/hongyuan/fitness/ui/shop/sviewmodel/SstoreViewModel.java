package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityShopStoreBinding;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMessageActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SMGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SstoreGouponAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.ShopStoreBeans;
import com.hongyuan.fitness.ui.shop.sbeans.StoreAllGoodsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.StoreCouponBeans;
import com.hongyuan.fitness.util.BaseUtil;

import java.util.List;

public class SstoreViewModel extends CustomViewModel {

    private ActivityShopStoreBinding binding;
    //店铺首页数据
    private ShopStoreBeans.DataBean storeBeans;

    private SstoreGouponAdapter topAdapter;

    private List<StoreCouponBeans.DataBean> couponList;
    //记录index
    private int mPosition;
    private SMGoodsAdapter gAdapter;

    private List<StoreAllGoodsBeans.DataBean.ListBean> mList;

    //搜索商品字段
    private String searchText;

    public SstoreViewModel(CustomActivity mActivity, ActivityShopStoreBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        setEnableLoadMore(true);
        setEnableRefresh(true);

        LinearLayoutManager topManager = new LinearLayoutManager(mActivity);
        topManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.couponRec.setLayoutManager(topManager);
        topAdapter = new SstoreGouponAdapter();
        binding.couponRec.setAdapter(topAdapter);
        topAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            mPosition = position;
            getReceive(String.valueOf(couponList.get(position).getCoupon_id()));
        });

        GridLayoutManager layoutManager =
                new GridLayoutManager(mActivity,2);
        binding.mRec.setLayoutManager(layoutManager);
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
        binding.mRec.setAdapter(gAdapter);
        gAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(mList.get(position).getG_id()));
            startActivity(SgoodsDetailActivity.class,bundle);
        });


        binding.collectStore.setOnClickListener(v -> {
            addCollection(String.valueOf(storeBeans.getStore_id()));
        });

        binding.searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchText = s.toString();
                getGoods();
            }
        });

        binding.mMessage.setOnClickListener(v -> {
            startActivity(ShopMessageActivity.class,null);
        });
    }


    /*
     * 收藏商品
     * */
    private void addCollection(String id){
        mActivity.showLoading();
        clearParams().setParams("collection_code","store");
        if(storeBeans.getIs_collection() == 1){
            setParams("out_id",id);
            Controller.myRequest(ConstantsCode.DEL_COLLECTION,Constants.DEL_COLLECTION,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }else{
            setParams("id",id);
            Controller.myRequest(ConstantsCode.ADD_COLLECTION,Constants.ADD_COLLECTION,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }

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
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("store_id",getBundle().getString("store_id"));
        Controller.myRequest(Constants.GET_STORE_INFO,Controller.TYPE_POST,getParams(), ShopStoreBeans.class,this);

        //查询优惠卷
        clearParams().setParams("store_id",getBundle().getString("store_id"));
        Controller.myRequest(Constants.GET_STORE_COUPON_LIST,Controller.TYPE_POST,getParams(), StoreCouponBeans.class,this);

        getGoods();
    }


    @Override
    public void refreshData(){
        curPage = FIRST_PAGE;
        getGoods();
    }
    @Override
    protected void loadMoreData() {
        getGoods();
    }

    /*
    * 查询商品
    * */
    private void getGoods(){
        clearParams().setParams("store_id",getBundle().getString("store_id"));
        if(BaseUtil.isValue(searchText)){
            setParams("search_name",searchText);
        }
        Controller.myRequest(Constants.GET_STORE_GOODS_LIST,Controller.TYPE_POST,getParams(), StoreAllGoodsBeans.class,this);

    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();
        if(data instanceof ShopStoreBeans){
            storeBeans = ((ShopStoreBeans)data).getData();

            RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
            Glide.with(mActivity).load(storeBeans.getStore_logo()).apply(options).into(binding.storeImg);

            //设置背景
            Glide.with(mActivity).load(storeBeans.getStore_background_img()).into(binding.bgImg);
            binding.shopName.setText(storeBeans.getStore_name());
            binding.collectionNum.setText(storeBeans.getCollection_num()+"人收藏");
            binding.collectStore.setText(storeBeans.getIs_collection() == 1 ? "取消" : "收藏");
            //binding.myRat.setRating(Float.valueOf(storeBeans.getG_score()));

            //pageApter.setImgs(storeBeans.getStore_content());
        }

        if(data instanceof StoreAllGoodsBeans){
            List<StoreAllGoodsBeans.DataBean.ListBean> list = ((StoreAllGoodsBeans)data).getData().getList();
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
                gAdapter.setNewData(mList);
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }

        if(data instanceof StoreCouponBeans){
            couponList = ((StoreCouponBeans)data).getData();

            if(couponList == null || couponList.size() <= 0){
                binding.couponRec.setVisibility(View.GONE);
            }else{
                topAdapter.setNewData(couponList);
            }

        }

    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.ADD_COLLECTION){
            showSuccess("收藏成功！");
            binding.collectStore.setText("取消");
            storeBeans.setIs_collection(1);
        }
        if(code == ConstantsCode.DEL_COLLECTION){
            showSuccess("已取消收藏！");
            binding.collectStore.setText("收藏");
            storeBeans.setIs_collection(0);
        }

        if(code == ConstantsCode.GET_COUPON){
            couponList.remove(mPosition);
            topAdapter.setNewData(couponList);
            showSuccess("领取成功！");
        }
    }
}
