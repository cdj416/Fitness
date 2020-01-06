package com.hongyuan.fitness.ui.person.physical_data.add_physical;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityAddPhysicaldataBinding;

public class AddPhysicaldataViewModel extends CustomViewModel {

    private ActivityAddPhysicaldataBinding binding;
    private AddPhysicaldataPagerAdapter meunAdapter;

    public AddPhysicaldataViewModel(CustomActivity mActivity, ActivityAddPhysicaldataBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        meunAdapter = new AddPhysicaldataPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setOffscreenPageLimit(13);
        binding.mViewPager.setCurrentItem(getBundle().getInt("position"));
    }

    @Override
    public void onSuccess(Object data) {

    }
}
