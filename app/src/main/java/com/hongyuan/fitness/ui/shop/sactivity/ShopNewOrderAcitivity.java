package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShopNewordersBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ShopNewOrderViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class ShopNewOrderAcitivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_neworders;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"商城订单");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"商城订单");

        ActivityShopNewordersBinding binding = ActivityShopNewordersBinding.bind(mView);
        ShopNewOrderViewModel viewModel = new ShopNewOrderViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

   /* @Override
    public void finish() {
        EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"3");
        startActivity(MainActivity.class);
        super.finish();
    }*/

    //安卓重写返回键事件
   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"3");
            startActivity(MainActivity.class);
            return false;
        }
        return true;
    }*/
}
