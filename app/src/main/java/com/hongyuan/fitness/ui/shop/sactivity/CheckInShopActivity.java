package com.hongyuan.fitness.ui.shop.sactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.custom_view.time_selecter.get_address.GetAddressDataService;
import com.hongyuan.fitness.custom_view.time_selecter.get_address.JsonDataBean;
import com.hongyuan.fitness.databinding.AcitivityCheckInShopBinding;
import com.hongyuan.fitness.databinding.ActivityBottomCheckinBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.CheckInShopViewModel;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.CheckInBottomViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class CheckInShopActivity extends CustomActivity {

    private AcitivityCheckInShopBinding binding;
    private CheckInShopViewModel viewModel;

    private LocalBroadcastManager mLocalBroadcastManager;
    private MyBroadcastReceiver mBroadcastReceiver;
    public final static String ACTION_TYPE_THREAD = "action.type.thread";

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_check_in_shop;
    }

    @Override
    public int getBottomLayoutId() {
        return R.layout.activity_bottom_checkin;
    }

    @Override
    protected void initView() {
        //注册广播
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TYPE_THREAD);
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"商城入驻");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"商城入驻");

        binding = AcitivityCheckInShopBinding.bind(mView);
        viewModel = new CheckInShopViewModel(this,binding);
        binding.setViewModel(viewModel);

        ActivityBottomCheckinBinding bottomCheckinBinding = ActivityBottomCheckinBinding.bind(bottomChildView);
        CheckInBottomViewModel bottomViewModel = new CheckInBottomViewModel(this,viewModel,bottomCheckinBinding);
        bottomCheckinBinding.setViewModel(bottomViewModel);

        //启动服务获取json文件里面的地址数据
        Intent intent = new Intent(CheckInShopActivity.this, GetAddressDataService.class);
        startService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == binding.license.getSciTag()){
            binding.license.onActivityResult(requestCode,resultCode,data);
        }
        if(requestCode == binding.cardImg.getSciTag()){
            binding.cardImg.onActivityResult(requestCode,resultCode,data);
        }
        if(requestCode == binding.tissueImg.getSciTag()){
            binding.tissueImg.onActivityResult(requestCode,resultCode,data);
        }
        if(requestCode == binding.taxypayerImg.getSciTag()){
            binding.taxypayerImg.onActivityResult(requestCode,resultCode,data);
        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case ACTION_TYPE_THREAD:
                    JsonDataBean jsonFileBean = (JsonDataBean) intent.getSerializableExtra("address");
                    viewModel.setAddressList(jsonFileBean);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    }
}
