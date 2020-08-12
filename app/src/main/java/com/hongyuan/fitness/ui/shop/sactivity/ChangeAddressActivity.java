package com.hongyuan.fitness.ui.shop.sactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.custom_view.time_selecter.get_address.GetAddressDataService;
import com.hongyuan.fitness.custom_view.time_selecter.get_address.JsonDataBean;
import com.hongyuan.fitness.databinding.AcitivityChangeAddressBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ChangeAddressViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class ChangeAddressActivity extends CustomActivity {

    private LocalBroadcastManager mLocalBroadcastManager;
    private MyBroadcastReceiver mBroadcastReceiver;
    public final static String ACTION_TYPE_THREAD = "action.type.thread";


    private ChangeAddressViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_change_address;
    }

    @Override
    protected void initView() {

        //注册广播
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TYPE_THREAD);
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);

        AcitivityChangeAddressBinding binding = AcitivityChangeAddressBinding.bind(mView);
        viewModel = new ChangeAddressViewModel(this,binding);
        binding.setViewModel(viewModel);

        //启动服务获取json文件里面的地址数据
        Intent intent = new Intent(ChangeAddressActivity.this, GetAddressDataService.class);
        startService(intent);
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
