package com.hongyuan.fitness.ui.heat.heat_detail;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityFoodHeatDetailsBinding;
import com.hongyuan.fitness.util.BaseUtil;

public class HeatDetailViewModel extends CustomViewModel {

    private ActivityFoodHeatDetailsBinding binding;
    private HeatDetailBean detailBean;
    private HeatDetailAdapter adapter;

    private String f_id;

    public HeatDetailViewModel(CustomActivity mActivity , ActivityFoodHeatDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();

    }

    @Override
    protected void initView() {
        f_id = getBundle().getString("f_id");

        LinearLayoutManager manager01 = new LinearLayoutManager(mActivity);
        manager01.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager01);
        adapter = new HeatDetailAdapter();
        binding.mRecycler.setAdapter(adapter);
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("f_id",f_id);
        Controller.myRequest(Constants.GET_FOOD_INFO,Controller.TYPE_POST,getParams(), HeatDetailBean.class,this);
    }

    @Override
    protected void setData() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img).centerCrop();
        Glide.with(mActivity).load(detailBean.getData().getF_img()).apply(options).into(binding.foodImg);
        binding.foodName.setText(detailBean.getData().getF_name());
        binding.foodScale.setText(BaseUtil.getNoZoon(Double.valueOf(detailBean.getData().getF_cal())*100)+"千卡/100克");
        binding.foodKcal.setText(BaseUtil.getNoZoon(Double.valueOf(detailBean.getData().getF_cal())*100));
        binding.foodProtein.setMaxValue(Float.valueOf(BaseUtil.getNoZoon(detailBean.getData().getF_protein())));
        binding.proteinNum.setText(BaseUtil.getNoZoon(detailBean.getData().getF_protein()));
        binding.foodFat.setMaxValue(Float.valueOf(BaseUtil.getNoZoon(detailBean.getData().getF_fat())));
        binding.fatNum.setText(BaseUtil.getNoZoon(detailBean.getData().getF_fat()));
        binding.foodCarbide.setMaxValue(Float.valueOf(BaseUtil.getNoZoon(detailBean.getData().getF_carbohydrate())));
        binding.carbideNum.setText(Double.valueOf(detailBean.getData().getF_carbohydrate()) > 100 ? "100" : BaseUtil.getNoZoon(detailBean.getData().getF_carbohydrate()));
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof HeatDetailBean && isSuccess(data)){
            detailBean = (HeatDetailBean)data;
            adapter.setBili(Double.valueOf(detailBean.getData().getF_cal()),detailBean.getData().getF_type());
            adapter.setNewData(detailBean.getData().getFu());
            setData();
        }
    }
}
