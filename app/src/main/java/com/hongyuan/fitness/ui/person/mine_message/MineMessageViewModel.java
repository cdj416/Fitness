package com.hongyuan.fitness.ui.person.mine_message;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityMineMessageBinding;
import com.hongyuan.fitness.ui.person.mine_message.child_message.MineChildMessageActivity;

public class MineMessageViewModel extends CustomViewModel {

    private ActivityMineMessageBinding binding;
    private MineMessageAdapter adapter;
    private MineMessageBeans messageBeans;

    public MineMessageViewModel(CustomActivity mActivity, ActivityMineMessageBinding binding) {
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
        adapter = new MineMessageAdapter();
        binding.mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("titleName",messageBeans.getData().get(position).getMsg_category_name());
                bundle.putString("msg_category_id",String.valueOf(messageBeans.getData().get(position).getMsg_category_id()));
                startActivity(MineChildMessageActivity.class,bundle);
            }
        });
    }

    @Override
    protected void lazyLoad() {
        Controller.myRequest(Constants.GET_MSG_CATEGORY_LIST,Controller.TYPE_POST,getParams(), MineMessageBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MineMessageBeans){
            messageBeans = (MineMessageBeans)data;
            adapter.setNewData(messageBeans.getData());
        }
    }

}
