package com.hongyuan.fitness.ui.shop.sactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.custom_view.time_selecter.get_address.GetAddressDataService;
import com.hongyuan.fitness.custom_view.time_selecter.get_address.JsonDataBean;
import com.hongyuan.fitness.databinding.ActivityShopSearchBinding;
import com.hongyuan.fitness.ui.shop.sfragment.SearchDrawerFragment;
import com.hongyuan.fitness.ui.shop.sviewmodel.ShopSearchViewModel;
import com.hongyuan.fitness.util.SkinConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ShopSearchActivity extends CustomActivity {

    private ActivityShopSearchBinding binding;

    private LocalBroadcastManager mLocalBroadcastManager;
    private MyBroadcastReceiver mBroadcastReceiver;
    public final static String ACTION_TYPE_THREAD = "action.type.thread";

    private SearchDrawerFragment searchFilter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_search;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR1,R.drawable.theme_shape_soid_ffffff,"");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR8,R.drawable.theme_shape_soid_ffffff_black,"");

        binding = ActivityShopSearchBinding.bind(mView);
        ShopSearchViewModel viewModel = new ShopSearchViewModel(this,binding);
        binding.setViewModel(viewModel);

        //注册广播
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TYPE_THREAD);
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
        //启动服务获取json文件里面的地址数据
        Intent intent = new Intent(ShopSearchActivity.this, GetAddressDataService.class);
        startService(intent);

        FragmentManager fragmentManager = getSupportFragmentManager();
        searchFilter = (SearchDrawerFragment)fragmentManager.findFragmentById(R.id.searchFilter);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case ACTION_TYPE_THREAD:
                    JsonDataBean jsonFileBean = (JsonDataBean) intent.getSerializableExtra("address");
                    searchFilter.setProData(jsonFileBean.getProvinceList());
                    break;
            }
        }
    }

    /*
     * 控制跳转到指定页面
     * */
    @Subscribe(id = ConstantsCode.EB_CLOSE_SERARCH)
    public void finshActivity(String message) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
}
