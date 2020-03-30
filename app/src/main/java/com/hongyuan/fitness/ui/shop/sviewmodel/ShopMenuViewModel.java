package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityShopMenuBinding;
import com.hongyuan.fitness.ui.shop.sadapter.SMenuLeftAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SMenuRightAdapter;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class ShopMenuViewModel extends CustomViewModel {

    private ActivityShopMenuBinding binding;

    private SMenuLeftAdapter leftAdapter;
    private SMenuRightAdapter rightAdapter;

    //左边菜单头部banner图
    private Banner sBanner;

    public ShopMenuViewModel(CustomActivity mActivity,ActivityShopMenuBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
    }

    @Override
    protected void initView() {

        LinearLayoutManager leftManager = new LinearLayoutManager(mActivity);
        leftManager.setOrientation(RecyclerView.VERTICAL);
        binding.leftRec.setLayoutManager(leftManager);
        leftAdapter = new SMenuLeftAdapter();
        binding.leftRec.setAdapter(leftAdapter);

        leftAdapter.setNewData(getMlist());

        GridLayoutManager rihtManager = new GridLayoutManager(mActivity, 3);
        rihtManager.setOrientation(RecyclerView.VERTICAL);
        binding.rightRec.setLayoutManager(rihtManager);
        rightAdapter = new SMenuRightAdapter();
        binding.rightRec.setAdapter(rightAdapter);
        rightAdapter.setHeaderView(getBannerHead());

        rightAdapter.setNewData(getMlist());
    }

    /*
    * 添加右边rec的头部banner布局
    * */
    private View getBannerHead(){
        View bannerView = mActivity.getLayoutInflater().inflate(R.layout.view_head_banner,null);
        sBanner = bannerView.findViewById(R.id.banner);
        //设置假数据
        setSBanner();
        return bannerView;
    }

    /*
     * 设置顶部banner
     * */
    private void setSBanner(){
        sBanner.setImages(getBannerList())
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
    }

    /*
     * 获取banner本地数据
     * */
    private List<Integer> getBannerList(){
        List<Integer> bList = new ArrayList<>();
        bList.add(R.drawable.banner_test1);
        bList.add(R.drawable.banner_test2);
        bList.add(R.drawable.banner_test3);
        bList.add(R.drawable.banner_test4);
        return bList;
    }

    /*
     * 获取右边假数据
     * */
    private List<BaseBean> getMlist(){
        List<BaseBean> mList = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i++){
            BaseBean bean = new BaseBean();
            mList.add(bean);
        }
        return mList;
    }

    @Override
    public void onSuccess(Object data) {

    }


}
