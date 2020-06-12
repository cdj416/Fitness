package com.hongyuan.fitness.ui.main;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityAllCitysBinding;
import com.hongyuan.fitness.util.LocationBean;

import org.greenrobot.eventbus.EventBus;

public class AllCitysViwModel extends CustomViewModel {

    private AcitivityAllCitysBinding binding;

    private AllCitysAdapter adapter;

    private OpenCitysBeans.DataBean dataBean;

    public AllCitysViwModel(CustomActivity mActivity, AcitivityAllCitysBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        binding.locationCity.setText(LocationBean.getInstance().getCityName());
        if(LocationBean.getInstance().isOpen()){
            binding.openText.setText("已开通");
        }else{
            binding.openText.setText("未开通");
        }

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new AllCitysAdapter();
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRec));

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            TokenSingleBean.getInstance().setRegion_name(dataBean.getList().get(position).getRegion_name());
            TokenSingleBean.getInstance().setRegion_code(dataBean.getList().get(position).getRegion_code());
            EventBus.getDefault().post(ConstantsCode.EB_HOME_LOCATION,"");
            mActivity.finish();
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_OPEN_CITYS, Controller.TYPE_POST,getParams(), OpenCitysBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof OpenCitysBeans){
            dataBean = ((OpenCitysBeans)data).getData();
            adapter.setNewData(dataBean.getList());
        }
    }
}
