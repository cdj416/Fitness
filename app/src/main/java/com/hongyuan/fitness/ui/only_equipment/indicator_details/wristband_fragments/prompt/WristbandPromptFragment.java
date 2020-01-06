package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.prompt;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.WristbandStautsUtils;
import com.hongyuan.fitness.ui.show_big_img.ShowBigImgActivity;

public class WristbandPromptFragment extends CustomFragment{

    private ViewPager mViewPager;
    private TabLayout layoutMenu;
    private TextView goSearch,goDetail;

    //设备搜索连接绑定操作类
    private WristbandStautsUtils stautsUtils;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wristband_prompt;
    }

    @Override
    public void initView(View mView) {
        stautsUtils = WristbandStautsUtils.getInstance(mActivity);

        mViewPager = mView.findViewById(R.id.mViewPager);
        layoutMenu = mView.findViewById(R.id.layoutMenu);
        goSearch = mView.findViewById(R.id.goSearch);
        goDetail = mView.findViewById(R.id.goDetail);

        WristbandPromptPageAdapter meunAdapter = new WristbandPromptPageAdapter(getChildFragmentManager());
        mViewPager.setAdapter(meunAdapter);
        layoutMenu.setupWithViewPager(mViewPager);

        goDetail.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("img",R.mipmap.product_manual_img);
            bundle.putString("title","产品说明");
            startActivity(ShowBigImgActivity.class,bundle);
        });

        goSearch.setOnClickListener(new View.OnClickListener() {
            @SingleClick(5000)
            @Override
            public void onClick(View v) {
                stautsUtils.goSearch();
            }
        });

    }



    @Override
    public void onSuccess(Object data) {

    }

}
