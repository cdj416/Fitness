package com.hongyuan.fitness.ui.main.main_about_class;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.about_class.course_search.CourseSearchActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AboutClassFragment extends CustomFragment {

    private ImageView searchMark;
    private TabLayout layoutMenu;
    private ViewPager mViewPager;
    private AboutClassViewPagerAdapter meunAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about_class;
    }

    @Override
    public void initView(View mView) {
        searchMark = mView.findViewById(R.id.searchMark);
        mViewPager = mView.findViewById(R.id.mViewPager);
        layoutMenu = mView.findViewById(R.id.layoutMenu);

        meunAdapter = new AboutClassViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(meunAdapter);
        layoutMenu.setupWithViewPager(mViewPager);

        mViewPager.setOffscreenPageLimit(2);

        searchMark.setOnClickListener(v -> startActivity(CourseSearchActivity.class,null));
    }

    @Override
    public void onSuccess(Object data) {

    }

    /*
     * 控制跳转到私教课页面
     * */
    @Subscribe(id = ConstantsCode.EB_SHOW_PRIVITE)
    public void starPriviteCourse(String message) {
        mViewPager.setCurrentItem(Integer.valueOf(message));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
