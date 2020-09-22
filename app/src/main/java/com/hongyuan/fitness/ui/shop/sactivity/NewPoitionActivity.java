package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityNewPoitionBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.NewPoitionViwModel;

public class NewPoitionActivity extends CustomActivity {

    private NewPoitionViwModel viwModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_poition;
    }

    @Override
    protected void initView() {
        setTypeBar("我的积分",R.drawable.shape_soid_ffef5b48);
        //getMainTitle().setRightTextColor("规则",getResources().getColor(R.color.color_FFFFFF));

        /*if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            getMainTitle().setRightTextColor("规则",getResources().getColor(R.color.color_FF333333));
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            getMainTitle().setRightTextColor("规则",getResources().getColor(R.color.color_FFFFFF));*/
        /*getMainTitle().getRightView().setOnClickListener(v -> {
            startActivity(RulesActivity.class,null);
        });*/

        ActivityNewPoitionBinding binding = ActivityNewPoitionBinding.bind(mView);
        viwModel = new NewPoitionViwModel(this,binding);
        binding.setViewModel(viwModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viwModel.refreshData();
    }
}
