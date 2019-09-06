package com.hongyuan.fitness.ui.store.store_list;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityStoreListBinding;

public class StoreListViewModel extends CustomViewModel {

    private ActivityStoreListBinding binding;
    private StoreListViewPagerAdapter meunAdapter;
    private int showPosition;

    public StoreListViewModel(CustomActivity mActivity, ActivityStoreListBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        showPosition = getBundle().getInt("showPosition");

        meunAdapter = new StoreListViewPagerAdapter(mActivity.getSupportFragmentManager(),getBundle().getString("os_id"));
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);

        binding.mViewPager.setOffscreenPageLimit(5);
        //初始化选择状态
        binding.mViewPager.setCurrentItem(showPosition);
        //初始化自定义样式
        setTabViw(showPosition);
        getTabListener();
    }

    /*
     * 自定义菜单栏样式
     * */
    private void setTabViw(int showPosition){
        //设置自定义tab
        for (int i = 0; i < meunAdapter.getBeans().size(); i++){
            TabLayout.Tab tab = binding.layoutMenu.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(meunAdapter.getTabView(i,mActivity));
            }
        }
        //设置第一页为选中状态时的tab文字颜色为红色
        View view=binding.layoutMenu.getTabAt(showPosition).getCustomView();
        TextView textView= view.findViewById(R.id.name);
        LinearLayout linearLayout = view.findViewById(R.id.box);
        textView.setTextColor(0xffffffff);
        linearLayout.setBackgroundResource(R.drawable.shape_radius16_ef5b48);
    }

    /*
     * tabview监听
     * */
    private void getTabListener(){
        //监听事件
        binding.layoutMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中了tab的逻辑
                View view=tab.getCustomView();
                TextView textView= view.findViewById(R.id.name);
                LinearLayout linearLayout = view.findViewById(R.id.box);
                textView.setTextColor(0xffffffff);
                linearLayout.setBackgroundResource(R.drawable.shape_radius16_ef5b48);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中tab的逻辑
                View view=tab.getCustomView();
                TextView textView= view.findViewById(R.id.name);
                LinearLayout linearLayout = view.findViewById(R.id.box);
                textView.setTextColor(0xff5F5F5F);
                linearLayout.setBackgroundResource(R.drawable.shape_radius16_ffffff_stroke_979797);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //再次选中tab的逻辑
            }
        });
    }

    @Override
    public void onSuccess(Object data) {

    }
}
