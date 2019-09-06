package com.hongyuan.fitness.ui.find.circle.choose_circlet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityChooseCircletBinding;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.GroupClassBean;
import com.hongyuan.fitness.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ChooseCirletViewModel extends CustomViewModel {
    private ActivityChooseCircletBinding binding;
    private ChooseCircletAdapter adapter;
    public ChooseCirletViewModel(CustomActivity mActivity, ActivityChooseCircletBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.HORIZONTAL_LIST,1,0xFFE5E5E5));
        binding.mRecycler.setLayoutManager(manager);
        adapter = new ChooseCircletAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setNewData(getmeunList());
    }

    @Override
    public void onSuccess(Object data) {

    }

    /*
     * 假数据(菜单)
     * */
    private List<GroupClassBean> getmeunList(){
        List<GroupClassBean> mList = new ArrayList<>();
        mList.add(new GroupClassBean());
        mList.add(new GroupClassBean());
        mList.add(new GroupClassBean());
        mList.add(new GroupClassBean());
        mList.add(new GroupClassBean());
        mList.add(new GroupClassBean());

        return mList;
    }
}
