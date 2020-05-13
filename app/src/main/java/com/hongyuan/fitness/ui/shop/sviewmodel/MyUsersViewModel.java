package com.hongyuan.fitness.ui.shop.sviewmodel;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMyUsersBinding;
import com.hongyuan.fitness.ui.shop.sadapter.MyUsersAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyUsersViewModel extends CustomViewModel {

    private ActivityMyUsersBinding binding;

    private MyUsersAdapter adapter;

    public MyUsersViewModel(CustomActivity mActivity,ActivityMyUsersBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new MyUsersAdapter();
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRec));
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
