package com.hongyuan.fitness.ui.person.exercise_data;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityExerciseDataBinding;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.util.SkinConstants;

public class ExeriseDataActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exercise_data;
    }

    @Override
    protected void initView() {
        //setTitleBar(TYPE_BAR4,R.drawable.shape_soid_fff27863,"运动数据");
        //setTypeBar("运动数据",R.drawable.shape_soid_fff27863);

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR4,R.drawable.shape_soid_fff27863,"运动数据");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin)) {
            setTitleBar(TYPE_BAR9, R.drawable.shape_soid_fff27863, "运动数据");
            getMainTitle().getBgView().setBackgroundColor(0xFFF27863);
            getMainTitle().hideLine();
        }



        ActivityExerciseDataBinding binding = ActivityExerciseDataBinding.bind(mView);
        ExeriseDataViewModel viewModel = new ExeriseDataViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
