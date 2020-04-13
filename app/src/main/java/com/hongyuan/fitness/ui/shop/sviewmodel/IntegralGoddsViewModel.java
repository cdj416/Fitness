package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityIntegralGoodsBinding;
import com.hongyuan.fitness.ui.shop.sactivity.IntegralGoodsDetailsActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SMGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

public class IntegralGoddsViewModel extends CustomViewModel {

    private ActivityIntegralGoodsBinding binding;
    private SMGoodsAdapter gAdapter;

    public IntegralGoddsViewModel(CustomActivity mActivity, ActivityIntegralGoodsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        GridLayoutManager rihtManager = new GridLayoutManager(mActivity, 2);
        rihtManager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(rihtManager);
        gAdapter = new SMGoodsAdapter();
        binding.mRec.setAdapter(gAdapter);
        gAdapter.setNewData(getList());

        gAdapter.setOnItemChildClickListener((adapter, view, position) ->
                startActivity(IntegralGoodsDetailsActivity.class,null)
        );
    }

    /*
     * 组装假数据
     * */
    private List<BaseBean> getList(){
        List<BaseBean> mList = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            mList.add(new BaseBean());
        }
        return mList;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
