package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityIncomeFormBinding;
import com.hongyuan.fitness.ui.shop.sactivity.IncomeDetailsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMessageActivity;
import com.hongyuan.fitness.ui.shop.sbeans.IncomeFormBeans;
import com.hongyuan.fitness.util.BaseUtil;

public class IncomeFormViewModel extends CustomViewModel {

    private AcitivityIncomeFormBinding binding;

    public IncomeFormViewModel(CustomActivity mActivity,AcitivityIncomeFormBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        mActivity.getMainTitle().setRightImage(R.mipmap.person_message_mark)
                .setOnClickListener(v -> startActivity(ShopMessageActivity.class,null));

        binding.goDetail.setOnClickListener(v -> {
            startActivity(IncomeDetailsActivity.class,null);
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_INCOME_FORMS_DATA,Controller.TYPE_POST,getParams(), IncomeFormBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof IncomeFormBeans){
            IncomeFormBeans.DataBean dataBean = ((IncomeFormBeans)data).getData();
            binding.yuE.setText(BaseUtil.getNoZoon(dataBean.getM_income()));
            binding.allPayNum.setText(BaseUtil.getNoZoon(dataBean.getAll_income()));
            binding.allIncome.setText(BaseUtil.getNoZoon(dataBean.getAll_wait_income()));
            binding.todayNum.setText(BaseUtil.getNoZoon(dataBean.getToday_wait_income_count()));
            binding.todayIncome.setText(BaseUtil.getNoZoon(dataBean.getToday_wait_income()));
            binding.yestodayNUm.setText(BaseUtil.getNoZoon(dataBean.getYesterday_wait_income_count()));
            binding.yestodayIncome.setText(BaseUtil.getNoZoon(dataBean.getYesterday_wait_income()));
        }
    }
}
