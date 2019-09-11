package com.hongyuan.fitness.ui.about_class.coach.coach_search;

import android.animation.ObjectAnimator;
import android.view.View;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityV3SearchCoachBinding;

public class CoachSearchViewModel extends CustomViewModel {

    private ActivityV3SearchCoachBinding binding;

    private ObjectAnimator objectAnimator;

    public CoachSearchViewModel(CustomActivity mActivity, ActivityV3SearchCoachBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        binding.clearText.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                objectAnimator = ObjectAnimator.ofFloat(binding.clearText,"rotation",0f,360f,0f);
                objectAnimator.setDuration(300);
                objectAnimator.start();
                binding.searchContent.setText("");
            }
        });
        binding.finishText.setOnClickListener(v -> mActivity.finish());
    }

    /*
    * 搜索内容
    * */
    private void getSearchData(String searchText){

    }

    @Override
    public void onSuccess(Object data) {

    }
}
