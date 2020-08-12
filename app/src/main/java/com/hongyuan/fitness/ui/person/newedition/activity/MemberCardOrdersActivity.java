package com.hongyuan.fitness.ui.person.newedition.activity;

import android.view.KeyEvent;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityMemberCardOrdersBinding;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.person.newedition.viewmodel.MemberCardOrdersViewModel;
import com.hongyuan.fitness.util.SkinConstants;

import org.greenrobot.eventbus.EventBus;

public class MemberCardOrdersActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_member_card_orders;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"会员卡订单");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"会员卡订单");

        AcitivityMemberCardOrdersBinding binding = AcitivityMemberCardOrdersBinding.bind(mView);
        MemberCardOrdersViewModel viewModel = new MemberCardOrdersViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    public void finish() {
        EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"3");
        startActivity(MainActivity.class);
        super.finish();
    }

    //安卓重写返回键事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"3");
            startActivity(MainActivity.class);
            return false;
        }
        return true;
    }
}
