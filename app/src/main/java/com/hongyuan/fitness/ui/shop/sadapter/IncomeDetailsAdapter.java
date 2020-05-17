package com.hongyuan.fitness.ui.shop.sadapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.IncomeDetailsBeans;

public class IncomeDetailsAdapter extends BaseQuickAdapter<IncomeDetailsBeans.DataBean.ListBean, BaseViewHolder> {

    public IncomeDetailsAdapter(){
        super(R.layout.item_income_details);
    }

    @Override
    protected void convert(BaseViewHolder helper, IncomeDetailsBeans.DataBean.ListBean item) {
        helper.setText(R.id.orderNum,"订单号："+item.getO_sn())
                .setText(R.id.payStatus,"订单状态："+item.getO_pay_state())
                .setText(R.id.incomeStatus,"收益状态："+item.getIncome_state_str())
                .setText(R.id.times,item.getAdd_date())
                .setText(R.id.incomeNum,"收益："+item.getOp_income());
    }
}
