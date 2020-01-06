package com.hongyuan.fitness.ui.training_plan.plan_program;

import android.graphics.Color;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class PlanProgramAdapter extends BaseQuickAdapter<PlansProgramDetailsBeans.DataBean.InfoBean.ItemBean.ContentBean, BaseViewHolder> {

    public PlanProgramAdapter(){
        super(R.layout.item_plan_program_details);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlansProgramDetailsBeans.DataBean.InfoBean.ItemBean.ContentBean item) {
       helper.setBackgroundColor(R.id.line, Color.parseColor(item.getColor()))
               .setText(R.id.typeName,item.getItems_type_name())
               .setTextColor(R.id.typeName,Color.parseColor(item.getColor()));

        RecyclerView childRecycler = helper.getView(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(RecyclerView.VERTICAL);
        childRecycler.setLayoutManager(manager);
        PlanProgramDetailsChildAdapter childAdapter = new PlanProgramDetailsChildAdapter();
        childRecycler.setAdapter(childAdapter);

        childAdapter.setNewData(item.getItems_type());
    }

}
