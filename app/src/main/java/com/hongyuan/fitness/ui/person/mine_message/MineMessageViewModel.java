package com.hongyuan.fitness.ui.person.mine_message;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.SwipeItemLayout;
import com.hongyuan.fitness.databinding.ActivityMineMessageBinding;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.ChatPageActivity;
import com.hongyuan.fitness.ui.person.mine_message.child_message.MineChildMessageActivity;
import com.hongyuan.fitness.util.huanxin.ChatDataBeans;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;

import java.util.List;

public class MineMessageViewModel extends CustomViewModel {

    private ActivityMineMessageBinding binding;
    private MineMessageAdapter adapter;
    private MineMessageBeans messageBeans;

    //消息列表
    private MineChatMessageAdapter messageAdapter;
    //会话数据
    private List<ChatDataBeans> chatDataBeansList;

    public MineMessageViewModel(CustomActivity mActivity, ActivityMineMessageBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        chatDataBeansList = HuanXinUtils.getInstance().getMessageList();

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new MineMessageAdapter();
        binding.mRecycler.setAdapter(adapter);
        binding.mRecycler.setHasFixedSize(true);
        binding.mRecycler.setNestedScrollingEnabled(false);
        binding.mRecycler.setLayoutManager(new LinearLayoutManager(mActivity){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

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

        LinearLayoutManager chatRecManager = new LinearLayoutManager(mActivity);
        chatRecManager.setOrientation(RecyclerView.VERTICAL);
        binding.chatRec.setLayoutManager(chatRecManager);
        messageAdapter = new MineChatMessageAdapter();
        binding.chatRec.setAdapter(messageAdapter);
        messageAdapter.setNewData(chatDataBeansList);
        binding.chatRec.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(mActivity));
        //禁止滑动以及优化卡顿问题
        binding.chatRec.setHasFixedSize(true);
        binding.chatRec.setNestedScrollingEnabled(false);
        binding.chatRec.setLayoutManager(new LinearLayoutManager(mActivity){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        messageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.jumpBox){
                    HuanXinUtils.getInstance().setBaseData(TokenSingleBean.getInstance().getM_mobile(),TokenSingleBean.getInstance().getHeadUrl()
                    ,chatDataBeansList.get(position).getNickname(),chatDataBeansList.get(position).getAvatar());
                    Bundle bundle = new Bundle();
                    bundle.putString("title",chatDataBeansList.get(position).getNickname());
                    bundle.putString("username",chatDataBeansList.get(position).getUsername());
                    bundle.putString("lastMsgId",chatDataBeansList.get(position).getMsgId());
                    startActivity(ChatPageActivity.class,bundle);
                }else{
                    chatDataBeansList.remove(position);
                    messageAdapter.notifyDataSetChanged();
                }

            }
        });


    }

    @Override
    protected void lazyLoad() {
        Controller.myRequest(Constants.GET_MSG_CATEGORY_LIST,Controller.TYPE_POST,getParams(), MineMessageBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        if(data instanceof MineMessageBeans){
            messageBeans = (MineMessageBeans)data;
            adapter.setNewData(messageBeans.getData());
        }
    }

}
