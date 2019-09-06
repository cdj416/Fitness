package com.hongyuan.fitness.ui.find.circle.circle_detail;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityCircleDetailsBinding;
import com.hongyuan.fitness.ui.main.main_find.featured.FeatureBean;
import com.hongyuan.fitness.ui.main.main_find.featured.FindContentAdapter;
import com.hongyuan.fitness.util.DividerItemDecoration;

public class CircleDetailsViewModel extends CustomViewModel {

    private ActivityCircleDetailsBinding binding;
    private FindContentAdapter adapter;
    private FeatureBean featureBean;
    public CircleDetailsViewModel(CustomActivity mActivity, ActivityCircleDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager1 = new LinearLayoutManager(mActivity);
        manager1.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.HORIZONTAL_LIST,1,0xffF1F1F1));
        binding.mRecycler.setLayoutManager(manager1);
        adapter = new FindContentAdapter();
        binding.mRecycler.setAdapter(adapter);

    }

    @Override
    protected void lazyLoad() {
        //获取帖子
        clearParams().setParams("page","10").setParams("curpage","1");
        Controller.myRequest(Constants.GET_CIRCLE_LIST,Controller.TYPE_POST,getParams(), FeatureBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof FeatureBean && isSuccess(data)){
            featureBean = (FeatureBean)data;
            adapter.setNewData(featureBean.getData().getList());
        }
    }
}
