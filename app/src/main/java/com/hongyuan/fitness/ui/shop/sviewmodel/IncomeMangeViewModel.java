package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityIncomeMangeBinding;
import com.hongyuan.fitness.ui.person.my_promote.PromotionCodeActivity;
import com.hongyuan.fitness.ui.person.my_promote.promote_record.PromotionRecordActivity;
import com.hongyuan.fitness.ui.shop.sactivity.IncomeFormActivity;
import com.hongyuan.fitness.ui.shop.sactivity.PromateOrdersActivity;
import com.hongyuan.fitness.ui.shop.sactivity.RewardWithdrawalActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMessageActivity;
import com.hongyuan.fitness.ui.shop.sbeans.IncomeHomeBeans;
import com.hongyuan.fitness.util.BaseUtil;

public class IncomeMangeViewModel extends CustomViewModel {

    private AcitivityIncomeMangeBinding binding;

    public IncomeMangeViewModel(CustomActivity mActivity, AcitivityIncomeMangeBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        mActivity.getMainTitle().setRightImage(R.mipmap.person_message_mark)
                .setOnClickListener(v -> startActivity(ShopMessageActivity.class,null));

        binding.goIncomeForm.setOnClickListener(v -> startActivity(IncomeFormActivity.class,null));
        //binding.goMyUsers.setOnClickListener(v -> startActivity(MyUsersActivity.class,null));
        binding.goMyUsers.setOnClickListener(v -> mActivity.startActivity(PromotionRecordActivity.class));
        //binding.goPromote.setOnClickListener(v -> startActivity(StartPromoteActivity.class,null));
        binding.goPromote.setOnClickListener(v -> startActivity(PromotionCodeActivity.class,null));
        binding.goPromateBox.setOnClickListener(v -> startActivity(PromateOrdersActivity.class,null));
        binding.goRewardWithdrawal.setOnClickListener(v -> startActivity(RewardWithdrawalActivity.class,null));
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_INDEX_INCOME_DATA,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof IncomeHomeBeans){
            IncomeHomeBeans.DataBean dataBean = ((IncomeHomeBeans)data).getData();
            binding.allIncome.setText(BaseUtil.getNoZoon(dataBean.getAll_income()));
            binding.dangZhang.setText(BaseUtil.getNoZoon(dataBean.getAll_wait_income()));
            binding.yuE.setText(BaseUtil.getNoZoon(dataBean.getM_income()));
        }
    }
}
