package com.hongyuan.fitness.ui.person.mine_point;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMinePointBinding;
import com.hongyuan.fitness.ui.mall.good_order_details.PointBean;
import com.hongyuan.fitness.ui.mall.mine.mine_order.MineOrderActivity;
import com.hongyuan.fitness.ui.mall.mine.point_bill.PointBillActivity;
import com.hongyuan.fitness.ui.person.daily_punch.DailyPunchActivity;
import com.hongyuan.fitness.ui.person.mine_point.point_rules.RulesActivity;
import com.hongyuan.fitness.util.BaseUtil;

public class MinePointViewModel extends CustomViewModel {
    private ActivityMinePointBinding binding;
    public MinePointViewModel(CustomActivity mActivity, ActivityMinePointBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        binding.pointBill.setOnClickListener(v -> {
            startActivity(PointBillActivity.class,null);
        });
        binding.pointRules.setOnClickListener(v -> startActivity(RulesActivity.class,null));
        binding.goDaKa.setOnClickListener(v -> startActivity(DailyPunchActivity.class,null));
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
