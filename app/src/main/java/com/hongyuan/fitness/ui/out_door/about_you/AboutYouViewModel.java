package com.hongyuan.fitness.ui.out_door.about_you;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.MyRulerView;
import com.hongyuan.fitness.databinding.ActivityAboutYouBinding;
import com.hongyuan.fitness.ui.heat.HeatActivity;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.out_door.RunActivity;
import com.hongyuan.fitness.util.BaseUtil;

public class AboutYouViewModel extends CustomViewModel implements MyRulerView.SelectChange {

    //标识年龄
    private final int AGE_RUBLER = 0X1;
    //标识身高
    private final int HEIGHT_RUBLER = 0X2;
    //标识体重
    private final int WEIGHT_RUBLER = 0X3;

    //标识男
    private final int MAN_CLICK = 1;
    //标识nv
    private final int WOMAN_CLICK = 2;
    //标识当前点击值
    private int click_num = MAN_CLICK;

    private ActivityAboutYouBinding binding;
    //放大动画
    private Animation amplification;
    //缩小动画
    private Animation narrow;

    public AboutYouViewModel(CustomActivity mActivity, ActivityAboutYouBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        amplification = AnimationUtils.loadAnimation(mActivity,R.anim.scale_amplification);
        narrow = AnimationUtils.loadAnimation(mActivity,R.anim.scale_narrow);
        //初始化默认选中值
        changeSex(click_num);

        binding.ageRuler.setDataRuler(AGE_RUBLER,1990,1900,2100,this);
        binding.heightRuler.setDataRuler(HEIGHT_RUBLER,180,0,300,this);
        binding.bodyWeightRuler.setDataRuler(WEIGHT_RUBLER,70,0,300,this);
        binding.clickMan.setOnClickListener(v -> {
            if(click_num != MAN_CLICK){
                click_num = MAN_CLICK;
                changeSex(click_num);
            }
        });
        binding.clickWoman.setOnClickListener(v -> {
            if(click_num != WOMAN_CLICK){
                click_num = WOMAN_CLICK;
                changeSex(click_num);
            }
        });

        binding.addBoxBut.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                addBodyIndex();
            }
        });
    }

    private void addBodyIndex(){
        clearParams().setParams("mbi_height",binding.heightNum.getText().toString())
                .setParams("mbi_weight",binding.weightNum.getText().toString())
                .setParams("mbi_birth",binding.ageNum.getText().toString())
                .setParams("mbi_sex",String.valueOf(click_num));
        Controller.myRequest(Constants.ADD_MEMBER_BODY_INDEX,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        if(code == 1 && data == null){
            if("run".equals(getBundle().getString("pagType"))){
                startActivity(RunActivity.class,null);
            }
            if("heat".equals(getBundle().getString("pagType"))){
                startActivity(HeatActivity.class,null);
            }
            if("main".equals(getBundle().getString("pagType"))){
                startActivity(MainActivity.class,null);
            }
        }
    }

    @Override
    public void valueChange(int whichCode, float value) {
        if(whichCode == AGE_RUBLER){
            binding.ageNum.setText(BaseUtil.getNoZoon(value));
        }
        if(whichCode == HEIGHT_RUBLER){
            binding.heightNum.setText(BaseUtil.getNoZoon(value));
        }
        if(whichCode == WEIGHT_RUBLER){
            binding.weightNum.setText(BaseUtil.getNoZoon(value));
        }
    }

    /*
    * 改变性别选择效果状态
    * */
    private void changeSex(int click_num){
        if(click_num == MAN_CLICK){
            binding.clickMan.startAnimation(amplification);
            binding.clickWoman.startAnimation(narrow);
            binding.clickMan.setTextColor(0xFFEF5B48);
            binding.clickWoman.setTextColor(0xFF999999);
        }
        if(click_num == WOMAN_CLICK){
            binding.clickMan.startAnimation(narrow);
            binding.clickWoman.startAnimation(amplification);
            binding.clickMan.setTextColor(0xFF999999);
            binding.clickWoman.setTextColor(0xFFEF5B48);
        }
    }
}
