package com.hongyuan.fitness.ui.mall.good_order_details;

import android.os.Bundle;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.MessageEvent;
import com.hongyuan.fitness.databinding.ActivityGoodOrderDetailBinding;
import com.hongyuan.fitness.ui.store.more_store.StoreBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class GoodOrderDetailsActivity extends CustomActivity {

    private ActivityGoodOrderDetailBinding binding;
    private GoodOrderDetailsViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_good_order_detail;
    }

    @Override
    protected void initView() {
        setTitle("确认订单");
        setsetImmersive(0x55000000);
        binding = ActivityGoodOrderDetailBinding.bind(mView);
        viewModel = new GoodOrderDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    /*
     * 获取门店的选择信息
     * */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(MessageEvent message) {
        if(message.getDataBean() instanceof StoreBean.DataBean.ListBean){//选择门店之后需要更改的
            StoreBean.DataBean.ListBean selectStoreBean = (StoreBean.DataBean.ListBean)message.getDataBean();
            binding.quhuoStoreName.setText(selectStoreBean.getOs_name());
            viewModel.paramsBean.setOp_quhuo_osid(String.valueOf(selectStoreBean.getOs_id()));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
