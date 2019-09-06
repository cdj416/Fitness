package com.hongyuan.fitness.ui.person.mine_point.point_rules;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;

public class RulesAdapter extends BaseQuickAdapter<RulesBean, BaseViewHolder> {

    public RulesAdapter(){
        super(R.layout.item_rules);
    }

    @Override
    protected void convert(BaseViewHolder helper, RulesBean item) {
        helper.setText(R.id.rulesName,item.getTaskName()).setText(R.id.reward,item.getReward())
                .setText(R.id.maxReward,item.getMaxReward());

    }
}
