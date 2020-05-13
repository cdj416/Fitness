package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityStartPromoteBinding;
import com.hongyuan.fitness.ui.shop.sadapter.StartPromoteAdapter;

import java.util.ArrayList;
import java.util.List;

public class StartPromoteViewModel extends CustomViewModel {

    private AcitivityStartPromoteBinding binding;

    private StartPromoteAdapter adapter;

    public StartPromoteViewModel(CustomActivity mActivity, AcitivityStartPromoteBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
    }

    @Override
    protected void initView() {
        adapter = new StartPromoteAdapter();
        binding.banner.setAdapter(adapter);
        adapter.setNewData(getList());
    }

    /*
     * 获取假数据
     * */
    private List<BaseBean> getList(){
        List<BaseBean> mList = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i++){
            BaseBean baseBean = new BaseBean();
            mList.add(baseBean);
        }
        return mList;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
