package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivitySGoodsDetailBinding;
import com.hongyuan.fitness.ui.shop.sactivity.SCartActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMessageActivity;
import com.hongyuan.fitness.ui.shop.sinterface.GoOtherPageListener;
import com.hongyuan.fitness.ui.shop.sviewpage.SshopDetailsViewPagerAdapter;

public class SgoodsDetailViewModel extends CustomViewModel implements GoOtherPageListener {

    private ActivitySGoodsDetailBinding binding;



    public SgoodsDetailViewModel(CustomActivity mActivity,ActivitySGoodsDetailBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {


        SshopDetailsViewPagerAdapter meunAdapter = new SshopDetailsViewPagerAdapter(mActivity.getSupportFragmentManager(),this);
        binding.viewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOffscreenPageLimit(3);


        binding.cart.setOnClickListener(v -> {
            startActivity(SCartActivity.class,null);
        });
        binding.message.setOnClickListener(v -> {
            startActivity(ShopMessageActivity.class,null);
        });

        binding.addCart.setOnClickListener(v -> {
            meunAdapter.showGuiGe();
        });
        binding.goBuy.setOnClickListener(v -> {
            meunAdapter.showGuiGe();
        });
    }



    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void goPage(int pageNum) {
        binding.viewPager.setCurrentItem(pageNum);
    }
}
