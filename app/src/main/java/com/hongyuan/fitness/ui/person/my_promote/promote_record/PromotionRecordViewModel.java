package com.hongyuan.fitness.ui.person.my_promote.promote_record;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityPromotionRecordBinding;

public class PromotionRecordViewModel extends CustomViewModel {

    private ActivityPromotionRecordBinding binding;

    private PromotionAdapter adapter;
    private PromotionRecordBeans.DataBean dataBean;

    public PromotionRecordViewModel(CustomActivity mActivity, ActivityPromotionRecordBinding binding) {
        super(mActivity);
        this.binding = binding;
        lazyLoad();
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new PromotionAdapter();
        binding.mRecycler.setAdapter(adapter);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_TG_MEMBER_LIST,Controller.TYPE_POST,getParams(), PromotionRecordBeans.class,this);
    }

    @Override
    protected void setData() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mActivity).load(dataBean.getMytg().getMi_head()).apply(options).into(binding.superiorHead);

        binding.superiorName.setText(dataBean.getMytg().getM_name());
        binding.superiorTel.setText(dataBean.getMytg().getM_mobile());
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof PromotionRecordBeans){
            dataBean = ((PromotionRecordBeans)data).getData();

            setData();

            if(dataBean.getList() != null && dataBean.getList().size() > 0){
                adapter.setNewData(dataBean.getList());
            }else{
                binding.subordinateBox.setVisibility(View.GONE);
            }
        }
    }
}
