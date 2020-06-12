package com.hongyuan.fitness.ui.shop.sviewmodel;

import androidx.recyclerview.widget.GridLayoutManager;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityRewardWithdrawalBinding;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SMGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.HabitGoddsBeans;

import java.util.ArrayList;
import java.util.List;

public class RewardWithdrawalViewModel extends CustomViewModel {

    private ActivityRewardWithdrawalBinding binding;

    private SMGoodsAdapter adapter;

    public RewardWithdrawalViewModel(CustomActivity mActivity, ActivityRewardWithdrawalBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        GridLayoutManager layoutManager =
                new GridLayoutManager(mActivity,2);
        //layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        binding.mRec.setLayoutManager(layoutManager);
        adapter = new SMGoodsAdapter<HabitGoddsBeans.DataBean.ListBean>() {
            @Override
            public String getImg(HabitGoddsBeans.DataBean.ListBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(HabitGoddsBeans.DataBean.ListBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(HabitGoddsBeans.DataBean.ListBean item) {
                return item.getG_price();
            }
            @Override
            public String getPoint(HabitGoddsBeans.DataBean.ListBean item) {
                return String.valueOf(item.getG_point());
            }

            @Override
            public int getShowType(HabitGoddsBeans.DataBean.ListBean item) {
                return item.getShowType();
            }
        };
        binding.mRec.setAdapter(adapter);
        //adapter.setNewData(getList());
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            startActivity(SgoodsDetailActivity.class,null);
        });

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
