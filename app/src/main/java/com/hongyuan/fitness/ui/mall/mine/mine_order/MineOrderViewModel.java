package com.hongyuan.fitness.ui.mall.mine.mine_order;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMineOrderBinding;
import com.hongyuan.fitness.ui.main.MainActivity;

public class MineOrderViewModel extends CustomViewModel {

    private ActivityMineOrderBinding binding;
    private MineOrderPagerAdapter viewPagerAdapter;

    public MineOrderViewModel(CustomActivity mActivity, ActivityMineOrderBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        mActivity.getMainTitle().getLeftView().setOnClickListener(v -> {
            startActivity(MainActivity.class,null);
        });

        viewPagerAdapter = new MineOrderPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(viewPagerAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setOffscreenPageLimit(5);
    }


    @Override
    public void onSuccess(Object data) {

    }
}
