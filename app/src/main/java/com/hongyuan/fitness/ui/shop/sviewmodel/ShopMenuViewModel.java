package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityShopMenuBinding;
import com.hongyuan.fitness.ui.shop.sactivity.ShopSearchActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SMenuLeftAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SMenuRightAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.FirstCategoryBeans;
import com.hongyuan.fitness.ui.shop.sbeans.ShopNextCetegoryBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopMenuViewModel extends CustomViewModel {

    private ActivityShopMenuBinding binding;

    private SMenuLeftAdapter leftAdapter;
    private SMenuRightAdapter rightAdapter;

    //左边菜单头部banner图
    private Banner sBanner;

    //一级分类数据
    private FirstCategoryBeans.DataBean dataBean;
    //当前选择的一级分类下标
    private int firtPostion = 0;
    //二级分类数据
    private List<ShopNextCetegoryBeans.DataBean> menuList;

    public ShopMenuViewModel(CustomActivity mActivity,ActivityShopMenuBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
    }

    @Override
    protected void initView() {
        dataBean = (FirstCategoryBeans.DataBean) getBundle().getSerializable("menu");

        LinearLayoutManager leftManager = new LinearLayoutManager(mActivity);
        leftManager.setOrientation(RecyclerView.VERTICAL);
        binding.leftRec.setLayoutManager(leftManager);
        leftAdapter = new SMenuLeftAdapter();
        binding.leftRec.setAdapter(leftAdapter);
        leftAdapter.setNewData(dataBean.getList());
        leftAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            firtPostion = position;

            for(FirstCategoryBeans.DataBean.ListBean listBean : dataBean.getList()){
                listBean.setSelect(false);
            }
            dataBean.getList().get(position).setSelect(true);
            adapter.notifyDataSetChanged();
            getSecond(String.valueOf(dataBean.getList().get(position).getCategory_id()));
            //设置头部的banner
            setSBanner(dataBean.getList().get(position).getBanner_img());
        });

        GridLayoutManager rihtManager = new GridLayoutManager(mActivity, 3);
        rihtManager.setOrientation(RecyclerView.VERTICAL);
        binding.rightRec.setLayoutManager(rihtManager);
        rightAdapter = new SMenuRightAdapter();
        binding.rightRec.setAdapter(rightAdapter);
        rightAdapter.setHeaderView(getBannerHead());
        rightAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("first_category_id",String.valueOf(dataBean.getList().get(firtPostion).getCategory_id()));
            bundle.putString("third_category_id",String.valueOf(menuList.get(position).getCategory_id()));
            bundle.putString("showText",menuList.get(position).getCategory_name());
            startActivity(ShopSearchActivity.class,bundle);
        });

        if(dataBean != null && dataBean.getList() != null && dataBean.getList().size() > 0){
            dataBean.getList().get(0).setSelect(true);
            //获取第一个分类的子分类
            getSecond(String.valueOf(dataBean.getList().get(0).getCategory_id()));
            //设置头部的banner
            setSBanner(dataBean.getList().get(0).getBanner_img());
        }
    }

    /*
    * 添加右边rec的头部banner布局
    * */
    private View getBannerHead(){
        View bannerView = mActivity.getLayoutInflater().inflate(R.layout.view_head_banner,null);
        sBanner = bannerView.findViewById(R.id.banner);
        return bannerView;
    }

    /*
     * 设置顶部banner
     * */
    private void setSBanner(String banner){
        List<String> bList = new ArrayList<>();
        if(BaseUtil.isValue(banner)){
            bList = Arrays.asList(banner.split(","));
            sBanner.setImages(bList)
                    .setImageLoader(new UseGlideImageLoader())
                    .setDelayTime(3000)
                    .isAutoPlay(true)
                    .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                    .setIndicatorGravity(BannerConfig.CENTER).setOnBannerListener(position -> {

            /*JumpUtils.JumpBeans jumpBeans = new JumpUtils.JumpBeans();
            jumpBeans.setImg_href_type(bannerList.get(position).getImg_href_type());
            jumpBeans.setHref_code(bannerList.get(position).getImg_href_code());
            jumpBeans.setHref_id(String.valueOf(bannerList.get(position).getImg_href_id()));

            JumpUtils.goAtherPage(mActivity,jumpBeans);*/
            }).start();
        }else{
            sBanner.setVisibility(View.GONE);
        }
    }

    /*
    * 获取二级分类数据
    * */
    private void getSecond(String first_id){
        mActivity.showLoading();
        clearParams().setParams("first_id",first_id);
        Controller.myRequest(Constants.GET_THIRD_CATEGORY_BY_FIRST_ID,Controller.TYPE_POST,getParams(), ShopNextCetegoryBeans.class,this);

    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof ShopNextCetegoryBeans){
            menuList = ((ShopNextCetegoryBeans)data).getData();
            rightAdapter.setNewData(menuList);
        }
    }

}
