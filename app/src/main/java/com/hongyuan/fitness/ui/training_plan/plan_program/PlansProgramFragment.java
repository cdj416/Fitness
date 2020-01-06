package com.hongyuan.fitness.ui.training_plan.plan_program;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;

public class PlansProgramFragment extends CustomFragment {

    private RecyclerView mRecycler;
    private PlanProgramAdapter adapter;

    private PlansProgramDetailsBeans.DataBean.InfoBean.ItemBean itemBean;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_plans_details;
    }

    @Override
    public void initView(View mView) {

        itemBean = (PlansProgramDetailsBeans.DataBean.InfoBean.ItemBean) getSerializableBeans("item");

        mRecycler = mView.findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new PlanProgramAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(itemBean.getContent());


    }

    @Override
    public void onSuccess(Object data) {

    }
}
