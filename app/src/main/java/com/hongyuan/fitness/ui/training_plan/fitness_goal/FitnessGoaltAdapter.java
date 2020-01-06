package com.hongyuan.fitness.ui.training_plan.fitness_goal;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;

public class FitnessGoaltAdapter extends BaseQuickAdapter<FitnessGoaltBeans.DataBean, BaseViewHolder> {

    public FitnessGoaltAdapter(){
        super(R.layout.item_fitness_goal);
    }
    @Override
    protected void convert(BaseViewHolder helper, FitnessGoaltBeans.DataBean item) {
        helper.setText(R.id.showName,item.getName());

        if(item.isSelect()){
            helper.setVisible(R.id.selectImg,true);
        }else{
            helper.setVisible(R.id.selectImg,false);
        }

        helper.addOnClickListener(R.id.box);
    }
}
