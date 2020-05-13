package com.hongyuan.fitness.ui.person.fix;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivitySixPriviteCourseBinding;
import com.hongyuan.fitness.ui.person.fix.coures_record.PriviteCourseRecordActivity;

public class SixPriviteCourseViewModel extends CustomViewModel {

    private ActivitySixPriviteCourseBinding binding;

    public SixPriviteCourseViewModel(CustomActivity mActivity, ActivitySixPriviteCourseBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        mActivity.getMainTitle().setRightImage(R.mipmap.clipboard_mark).setOnClickListener(v -> startActivity(PriviteCourseRecordActivity.class,null));

        FixpCourseViwPageAdapter viewAdapter = new FixpCourseViwPageAdapter(mActivity.getSupportFragmentManager());
        binding.viewPager.setAdapter(viewAdapter);
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOffscreenPageLimit(4);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
