package com.hongyuan.fitness.ui.about_class.group_class.group_list;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMyGroupClassBinding;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.MyGroupClassBean;

public class MyGroupClassViewModel extends CustomViewModel {

    private ActivityMyGroupClassBinding binding;
    private MyGroupCourseeAdapter adapter;
    public MyGroupClassViewModel(CustomActivity mActivity, ActivityMyGroupClassBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new MyGroupCourseeAdapter();
        binding.mRecycler.setAdapter(adapter);

    }

    @Override
    protected void lazyLoad() {
        //已报名的团课
        clearParams().setParams("page","10").setParams("curpage","1");
        Controller.myRequest(Constants.GET_MEMBER_SIGN_UP_COURSE_SUPER_LIST,Controller.TYPE_POST,getParams(), MyGroupClassBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MyGroupClassBean){
            MyGroupClassBean myGroupClassBean = (MyGroupClassBean)data;
            adapter.setNewData(myGroupClassBean.getData().getList());
        }
    }
}
