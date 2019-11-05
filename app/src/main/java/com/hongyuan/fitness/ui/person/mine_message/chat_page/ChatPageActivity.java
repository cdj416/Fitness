package com.hongyuan.fitness.ui.person.mine_message.chat_page;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityChatPageBinding;

public class ChatPageActivity extends CustomActivity {

    private ActivityChatPageBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_page;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"");
        binding = ActivityChatPageBinding.bind(mView);
        ChatPageViewModel viewModel = new ChatPageViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.chatView.onDestory();
    }
}
