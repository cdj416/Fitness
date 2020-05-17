package com.hongyuan.fitness.ui.shop.sviewmodel;

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
import com.hongyuan.fitness.ui.shop.sbeans.ShopStoreBeans;
import com.hongyuan.fitness.ui.shop.sviewpage.SstoreViewPagerAdapter;

public class SstoreViewModel extends CustomViewModel {

    private ActivityShopStoreBinding binding;

    private SstoreViewPagerAdapter pageApter;

    //店铺首页数据
    private ShopStoreBeans.DataBean storeBeans;

    public SstoreViewModel(CustomActivity mActivity, ActivityShopStoreBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        pageApter = new SstoreViewPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(pageApter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setOffscreenPageLimit(2);

        binding.collectStore.setOnClickListener(v -> {
            addCollection(String.valueOf(storeBeans.getStore_id()));
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

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("store_id",getBundle().getString("store_id"));
        Controller.myRequest(Constants.GET_STORE_INFO,Controller.TYPE_POST,getParams(), ShopStoreBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
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
            binding.myRat.setRating(Float.valueOf(storeBeans.getG_score()));

            pageApter.setImgs(storeBeans.getStore_content());
        }

    }

    @Override
    public void onSuccess(int code, Object data) {
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
    }
}
