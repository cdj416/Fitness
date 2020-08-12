package com.hongyuan.fitness.ui.person.fix;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivitySixPriviteCourseBinding;
import com.hongyuan.fitness.ui.person.fix.coures_record.PriviteCourseRecordActivity;
import com.hongyuan.fitness.util.SkinConstants;

public class SixPriviteCourseViewModel extends CustomViewModel {

    private ActivitySixPriviteCourseBinding binding;

    public SixPriviteCourseViewModel(CustomActivity mActivity, ActivitySixPriviteCourseBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(mActivity.skin))
            mActivity.getMainTitle().setRightImage(R.mipmap.clipboard_mark);
        if(SkinConstants.SKIN_NAME.BLACK.equals(mActivity.skin))
            mActivity.getMainTitle().setRightImage(R.mipmap.theme_order_black);
        mActivity.getMainTitle().getRightView().setOnClickListener(v -> startActivity(PriviteCourseRecordActivity.class,null));

        FixpCourseViwPageAdapter viewAdapter = new FixpCourseViwPageAdapter(mActivity.getSupportFragmentManager());
        binding.viewPager.setAdapter(viewAdapter);
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOffscreenPageLimit(4);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
