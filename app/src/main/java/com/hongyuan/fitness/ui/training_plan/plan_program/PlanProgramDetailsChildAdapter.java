package com.hongyuan.fitness.ui.training_plan.plan_program;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class PlanProgramDetailsChildAdapter extends BaseQuickAdapter<PlansProgramDetailsBeans.DataBean.InfoBean.ItemBean.ContentBean.ItemsTypeBean, BaseViewHolder> {

    public PlanProgramDetailsChildAdapter() {
        super(R.layout.item_child_plans_program_details);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlansProgramDetailsBeans.DataBean.InfoBean.ItemBean.ContentBean.ItemsTypeBean item) {
        helper.setText(R.id.desText,item.getItem_content());
    }
}
