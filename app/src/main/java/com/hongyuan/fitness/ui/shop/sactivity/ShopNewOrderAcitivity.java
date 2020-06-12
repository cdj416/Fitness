package com.hongyuan.fitness.ui.shop.sactivity;

import android.view.KeyEvent;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShopNewordersBinding;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.shop.sviewmodel.ShopNewOrderViewModel;

import org.greenrobot.eventbus.EventBus;

public class ShopNewOrderAcitivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_neworders;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"商城订单");

        ActivityShopNewordersBinding binding = ActivityShopNewordersBinding.bind(mView);
        ShopNewOrderViewModel viewModel = new ShopNewOrderViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            startActivity(MainActivity.class);
        }
        return true;
    }
}
