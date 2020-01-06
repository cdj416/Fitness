package com.hongyuan.fitness.ui.training_plan.plan_program;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityPlansProgramBinding;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachHomePageActivity;
import com.hongyuan.fitness.ui.person.physical_data.PhysicalDataActivity;

public class PlansProgramViewModel extends CustomViewModel {

    private ActivityPlansProgramBinding binding;

    private PlansProgramDetailsPagerAdapter meunAdapter;
    private PlansProgramDetailsBeans.DataBean.InfoBean infoBean;

    public PlansProgramViewModel(CustomActivity mActivity, ActivityPlansProgramBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);

        mActivity.getMainTitle().setRightText("身体数据");
        mActivity.getMainTitle().getRightView().setOnClickListener(v -> {
            startActivity(PhysicalDataActivity.class,null);
        });

        meunAdapter = new PlansProgramDetailsPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);

        binding.goCoach.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("coach_mid",String.valueOf(infoBean.getJl_id()));
            mActivity.startActivity(CoachHomePageActivity.class,bundle);
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("content_id",getBundle().getString("content_id"));
        Controller.myRequest(Constants.GET_PLAN_CONTENT_INFO,Controller.TYPE_POST,getParams(), PlansProgramDetailsBeans.class,this);
    }

    /*
     * 设置值
     * */
    @Override
    protected void setData() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mActivity).load(infoBean.getMi_head()).apply(options).into(binding.headImg);

        binding.coachName.setText(infoBean.getM_name());
        binding.goodAt.setText(infoBean.getFt_str());
        binding.storeName.setText(infoBean.getOs_name());

        binding.weekNum.setText(String.valueOf(infoBean.getWeek_num()));
        binding.days.setText(String.valueOf(infoBean.getPlan_day()));
        binding.times.setText(String.valueOf(infoBean.getDay_num()));
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof PlansProgramDetailsBeans){
            infoBean = ((PlansProgramDetailsBeans)data).getData().getInfo();
            meunAdapter.setData(infoBean.getItem());
            binding.mViewPager.setOffscreenPageLimit(infoBean.getItem().size());

            setData();
        }
    }
}
