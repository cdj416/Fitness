package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityGroupChatUserBinding;
import com.hongyuan.fitness.ui.shop.sadapter.GroupChatUserAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.GroupChatUserBeans;

public class GroupChatUserViewModel extends CustomViewModel {

    private ActivityGroupChatUserBinding binding;

    private GroupChatUserAdapter adapter;

    private GroupChatUserBeans dataBean;

    public GroupChatUserViewModel(CustomActivity mActivity, ActivityGroupChatUserBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        GridLayoutManager rihtManager = new GridLayoutManager(mActivity, 5);
        rihtManager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(rihtManager);
        adapter = new GroupChatUserAdapter();
        binding.mRec.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {

        });
    }

    @Override
    protected void lazyLoad() {
        //mActivity.showLoading();
        //请求分类
        clearParams().setParams("group_chat_id",getBundle().getString("group_chat_id"));
        Controller.myRequest(Constants.SPORT_CHAT_MEMBER_LIST,Controller.TYPE_POST,getParams(), GroupChatUserBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.showLoading();

        if(data instanceof GroupChatUserBeans){
            dataBean = (GroupChatUserBeans)data;
            if(dataBean.getData() != null && dataBean.getData().size() > 0){
                adapter.setNewData(dataBean.getData());
            }
        }

    }
}
