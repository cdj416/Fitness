package com.hongyuan.fitness.ui.main.main_find;

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
import com.hongyuan.fitness.ui.find.circle.edit_post.EditPostActivity;
import com.hongyuan.fitness.ui.login.vtwo_login.VtwoLoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class FindFragment extends CustomFragment {

    private TabLayout layoutMenu;
    private ViewPager mViewPager;

    private FindViewPagerAdapter meunAdapter;

    private ImageView releasePost;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView(View mView) {
        layoutMenu = mView.findViewById(R.id.layoutMenu);
        mViewPager = mView.findViewById(R.id.mViewPager);
        releasePost = mView.findViewById(R.id.releasePost);

        meunAdapter = new FindViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(meunAdapter);
        layoutMenu.setupWithViewPager(mViewPager);

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(1);
        releasePost.setOnClickListener(v -> startActivity(EditPostActivity.class,null));

        mViewPager.addOnPageChangeListener(getOnPageChangeListener());
    }

    /*
     * viewPage页面变动监听
     * */
    private ViewPager.OnPageChangeListener getOnPageChangeListener(){
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0 && mActivity.userToken.getM_mobile() == null){
                    startActivity(VtwoLoginActivity.class,null);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    @Override
    public void onSuccess(Object data) {

    }

    /*
     * 设置初始显示页面
     * */
    @Subscribe(id = ConstantsCode.EB_START_MAIN)
    public void result(String message) {
        mViewPager.setCurrentItem(1);
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
