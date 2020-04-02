package com.hongyuan.fitness.ui.shop.sviewmodel;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivitySGoodsDetailBinding;
import com.hongyuan.fitness.ui.shop.sadapter.SGDcommentAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SGDgoodsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SgoodsDetailViewModel extends CustomViewModel {

    private ActivitySGoodsDetailBinding binding;

    private SGDcommentAdapter sgDcommentAdapter;
    private SGDgoodsAdapter sgDgoodsAdapter;

    public SgoodsDetailViewModel(CustomActivity mActivity,ActivitySGoodsDetailBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        LinearLayoutManager comManager = new LinearLayoutManager(mActivity);
        comManager.setOrientation(RecyclerView.VERTICAL);
        binding.commentRec.setLayoutManager(comManager);
        sgDcommentAdapter = new SGDcommentAdapter();
        binding.commentRec.setAdapter(sgDcommentAdapter);
        List<BaseBean> ll = new ArrayList<>();
        ll.add(new BaseBean());
        sgDcommentAdapter.setNewData(ll);

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 3);
        binding.goodsRec.setLayoutManager(layoutManager);
        sgDgoodsAdapter = new SGDgoodsAdapter();
        binding.goodsRec.setAdapter(sgDgoodsAdapter);
        sgDgoodsAdapter.setNewData(getList());
    }

    /*
    * 添加假数据
    * */
    private List<BaseBean> getList(){
        List<BaseBean> mList = new ArrayList<>();
        for(int i = 0 ; i < 6 ; i++){
            BaseBean bean = new BaseBean();
            mList.add(bean);
        }
        return mList;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
