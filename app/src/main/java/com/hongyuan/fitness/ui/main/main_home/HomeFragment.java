package com.hongyuan.fitness.ui.main.main_home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.scan.ScanActivity;
import com.hongyuan.fitness.util.CustomDialog;
public class HomeFragment extends CustomFragment {

    private LinearLayout addressBox;
    private TextView addressName;
    private TabLayout layoutMenu;
    private ImageView scan;
    private ViewPager mViewPager;

    private HomeViewPagerAdapter meunAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View mView) {
        addressBox = mView.findViewById(R.id.addressBox);
        addressName = mView.findViewById(R.id.addressName);
        layoutMenu = mView.findViewById(R.id.layoutMenu);
        scan = mView.findViewById(R.id.scan);
        mViewPager = mView.findViewById(R.id.mViewPager);

        meunAdapter = new HomeViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(meunAdapter);
        layoutMenu.setupWithViewPager(mViewPager);

        mViewPager.setOffscreenPageLimit(2);

        addressBox.setOnClickListener(v -> CustomDialog.selectLocation(mActivity));
        scan.setOnClickListener(v -> startActivity(ScanActivity.class,null));
    }
    @Override
    public void onSuccess(Object data) {

    }
}
