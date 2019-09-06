package com.hongyuan.fitness.ui.mall.mine;


import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMallMineBinding;
import com.hongyuan.fitness.ui.mall.good_order_details.PointBean;
import com.hongyuan.fitness.ui.mall.mine.mine_order.MineOrderActivity;
import com.hongyuan.fitness.ui.mall.mine.point_bill.PointBillActivity;
import com.hongyuan.fitness.util.BaseUtil;

public class MallMineViewModel extends CustomViewModel {

    private ActivityMallMineBinding binding;
    public MallMineViewModel(CustomActivity mActivity, ActivityMallMineBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        binding.pointBox.setOnClickListener(v -> {
            startActivity(PointBillActivity.class,null);
        });
        binding.mineOrder.setOnClickListener(v -> startActivity(MineOrderActivity.class,null));
    }

    @Override
    protected void lazyLoad() {
        Controller.myRequest(Constants.GET_MEMBER_POINT,Controller.TYPE_POST,getParams(), PointBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PointBean){
            PointBean pointBean = (PointBean)data;
            binding.pointText.setText(BaseUtil.getNoZoon(pointBean.getData().getPoint()));
        }
    }
}
