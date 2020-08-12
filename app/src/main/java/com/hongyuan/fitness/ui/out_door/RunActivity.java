package com.hongyuan.fitness.ui.out_door;

import android.os.Bundle;
import android.view.KeyEvent;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityRunBinding;
import com.hongyuan.fitness.util.SkinConstants;


public class RunActivity extends CustomActivity {

    private ActivityRunBinding binding;
    private RunViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_run;
    }


    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR4,R.drawable.shape_gradient_v_ef_f0,"户外");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"户外");

        binding = ActivityRunBinding.bind(mView);
        viewModel = new RunViewModel(this,binding);
        binding.setViewModel(viewModel);

        //控制返回键
        getMainTitle().getLeftView().setOnClickListener(v -> {
            if(viewModel.mapIsOpen){
                viewModel.closeMap();
            }else{
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.myMap.activityCreated(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        try {
            binding.myMap.activityOnDestroy();
            viewModel.locationUtil.stopLocation();
            super.onDestroy();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        binding.myMap.activityOnResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        binding.myMap.activityOnPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        binding.myMap.activityOnSaveInstanceState(outState);
    }

    @Override
    //安卓重写返回键事件
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(viewModel.mapIsOpen){
                viewModel.closeMap();
            }else{
                finish();
            }
        }
        return true;
    }

}
