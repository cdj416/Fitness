package com.hongyuan.fitness.ui.person.my_coupon;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMyCouponBinding;

public class MyCouponViewModel extends CustomViewModel {
    private ActivityMyCouponBinding binding;


    //声明本次使用到的java类
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private StoreMyCouponFragment storeMyCouponFragment;
    private ShopMyCouponFragment shopMyCouponFragment;

    public MyCouponViewModel(CustomActivity mActivity, ActivityMyCouponBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        fragmentManager = mActivity.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        shopMyCouponFragment = new ShopMyCouponFragment();
        storeMyCouponFragment = new StoreMyCouponFragment();

        //通过添加（事务处理的方式）将fragment加到对应的布局中
        fragmentTransaction.add(R.id.fragmentBox,shopMyCouponFragment)
                .add(R.id.fragmentBox,storeMyCouponFragment)
                .hide(shopMyCouponFragment).show(storeMyCouponFragment)
                .commit();

        binding.shopCoupon.setOnClickListener(v -> {
            binding.shopCoupon.setTextColor(mActivity.getResources().getColor(R.color.color_EF5B48));
            binding.storeCoupon.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
            fragmentManager = mActivity.getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                    .hide(storeMyCouponFragment).show(shopMyCouponFragment)
                    .commit();
        });
        binding.storeCoupon.setOnClickListener(v -> {
            binding.shopCoupon.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
            binding.storeCoupon.setTextColor(mActivity.getResources().getColor(R.color.color_EF5B48));
            fragmentManager = mActivity.getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                    .hide(shopMyCouponFragment).show(storeMyCouponFragment)
                    .commit();
        });
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onSuccess(Object data) {

    }
}
