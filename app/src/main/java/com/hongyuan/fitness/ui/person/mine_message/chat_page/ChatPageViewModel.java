package com.hongyuan.fitness.ui.person.mine_message.chat_page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityChatPageBinding;
import com.hongyuan.fitness.ui.shop.sactivity.GroupChatUserActivity;
import com.hongyuan.fitness.util.BaseUtil;

import cn.jiguang.imui.chatinput.ChatInputView;

public class ChatPageViewModel extends CustomViewModel implements View.OnTouchListener {

    private ActivityChatPageBinding binding;

    private InputMethodManager mImm;
    private Window mWindow;

    //耳机检查服务
    //private HeadsetDetectReceiver mReceiver;

    public ChatPageViewModel(CustomActivity mActivity, ActivityChatPageBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView() {
        String title = getBundle().getString("title");

        mActivity.getMainTitle().setCentreText(BaseUtil.isValue(title) ? title : "匿名");

        this.mImm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        mWindow = mActivity.getWindow();

        if(getBundle().getBoolean("isGroup",false)){
            mActivity.getMainTitle().setRightImage(R.mipmap.black_dot_more_mark)
                    .getRightImgView().setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("group_chat_id",getBundle().getString("username"));
                startActivity(GroupChatUserActivity.class,bundle);
                    });
        }

        //初始化查询数据需要的参数值
        binding.chatView.setDataParms(getBundle().getString("username"),getBundle().getString("lastMsgId"));
        //初始化相应的各个复杂的监听事件等等
        binding.chatView.initModule();

        //设置屏幕事件
        binding.chatView.setOnTouchListener(this);
        binding.chatView.getChatInputView().getInputView().setOnTouchListener((view, motionEvent) -> {
            binding.chatView.scrollToBottom();
            return false;
        });
    }


    @Override
    public void onSuccess(Object data) {

    }

    /*
    * 屏幕触碰监听
    * */
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ChatInputView chatInputView = binding.chatView.getChatInputView();
                if (chatInputView.getMenuState() == View.VISIBLE) {
                    chatInputView.dismissMenuLayout();
                }
                //binding.chatView.setMsgListHeight(true);
                try {
                    View v = mActivity.getCurrentFocus();
                    if (mImm != null && v != null) {
                        mImm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                        view.clearFocus();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case MotionEvent.ACTION_UP:
                view.performClick();
                break;
        }
        return false;
    }
}
