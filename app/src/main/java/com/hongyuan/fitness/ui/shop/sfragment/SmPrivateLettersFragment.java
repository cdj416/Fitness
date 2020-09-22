package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.SwipeItemLayout;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.person.mine_message.MineChatMessageAdapter;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.ChatPageActivity;
import com.hongyuan.fitness.ui.shop.sactivity.MsgDetailsActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SprivateLettersAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.SmPrivateLettersBeans;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.huanxin.ChatDataBeans;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;
import com.hyphenate.chat.EMMessage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class SmPrivateLettersFragment extends CustomFragment{

    private RecyclerView mRecycler,chatRec;
    private SprivateLettersAdapter adapter;
    private List<SmPrivateLettersBeans.DataBean> mList;

    //系统消息点击的下标
    private int mPosition;

    //聊天消息列表
    private MineChatMessageAdapter messageAdapter;
    //会话数据
    private List<ChatDataBeans> chatDataBeansList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_smprivateletters;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);



        mRecycler = mView.findViewById(R.id.mRecycler);
        chatRec = mView.findViewById(R.id.chatRec);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new SprivateLettersAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            this.mPosition = position;
            setReadMsg(String.valueOf(mList.get(position).getMsg_category_id()));
        });


        //聊天会话消息
        LinearLayoutManager chatRecManager = new LinearLayoutManager(mActivity);
        chatRecManager.setOrientation(RecyclerView.VERTICAL);
        chatRec.setLayoutManager(chatRecManager);
        messageAdapter = new MineChatMessageAdapter();
        chatRec.setAdapter(messageAdapter);
        chatRec.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(mActivity));
        //禁止滑动以及优化卡顿问题
        chatRec.setHasFixedSize(true);
        chatRec.setNestedScrollingEnabled(false);
        chatRec.setLayoutManager(new LinearLayoutManager(mActivity){
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
                    //设为已读,并刷新显示
                    HuanXinUtils.getInstance().clearAllMessageCounts(chatDataBeansList.get(position).getUsername(),chatDataBeansList.get(position).getMsgId());
                    chatDataBeansList.get(position).setUnread(false);
                    chatDataBeansList.get(position).setUnreadNum(0);
                    adapter.notifyDataSetChanged();


                    HuanXinUtils.getInstance().setBaseData(TokenSingleBean.getInstance().getM_mobile(),TokenSingleBean.getInstance().getHeadUrl()
                            ,chatDataBeansList.get(position).getNickname(),chatDataBeansList.get(position).getAvatar());
                    Bundle bundle = new Bundle();
                    bundle.putString("title",chatDataBeansList.get(position).getNickname());
                    bundle.putString("username",chatDataBeansList.get(position).getUsername());
                    bundle.putString("lastMsgId",chatDataBeansList.get(position).getMsgId());
                    startActivity(ChatPageActivity.class,bundle);
                }else{

                    CustomDialog.promptDialog(mActivity, "确定要删除当前会话及聊天记录？", "确定", "舍不得", false, v -> {
                        if(v.getId() == R.id.isOk){
                            HuanXinUtils.getInstance().deleteConversation(chatDataBeansList.get(position).getUsername());

                            chatDataBeansList.remove(position);
                            messageAdapter.notifyDataSetChanged();
                        }
                    });

                }

            }
        });
    }

    //标记已读信息
    public void setReadMsg(String msg_category_id){
        mActivity.showLoading();

        clearParams().setParams("msg_id","0").setParams("msg_category_id",msg_category_id);
        Controller.myRequest(ConstantsCode.UPDATE_MSG_READ,Constants.UPDATE_MSG_READ,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onResume() {
        super.onResume();

        //当前数据每次都要刷新下
        chatDataBeansList = HuanXinUtils.getInstance().getMessageList();
        messageAdapter.setNewData(chatDataBeansList);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_MSG_CT_LIST,Controller.TYPE_POST,getParams(), SmPrivateLettersBeans.class,this);
    }

    @Override
    public void refreshData() {
        lazyLoad();
    }

    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof SmPrivateLettersBeans){
            mList = ((SmPrivateLettersBeans)data).getData();
            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
            }

            if((mList == null || mList.size() <= 0) && (chatDataBeansList == null || chatDataBeansList.size() <= 0)){
                setPromtView(SHOW_EMPTY);
            }
        }
    }



    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();

        if(code == ConstantsCode.UPDATE_MSG_READ){
            //设置为已读信息
            mList.get(mPosition).setCount(0);
            adapter.notifyDataSetChanged();

            Bundle bundle = new Bundle();
            bundle.putString("titleName",mList.get(mPosition).getTitle());
            bundle.putString("msg_category_id",String.valueOf(mList.get(mPosition).getMsg_category_id()));
            startActivity(MsgDetailsActivity.class,bundle);
        }
    }

    /*
     * 收到新消息刷新列表
     * */
    @Subscribe(id = ConstantsCode.EB_MESSAGE)
    public void onEvent(List<EMMessage> messages) {
        //从新获取消息列表数据并更新ui
        //chatDataBeansList = getNewMsg(messages);
        //messageAdapter.setNewData(chatDataBeansList);

        //不能直接去刷新，不然会失效，无法刷新。
        handler.postDelayed(() -> {
            chatDataBeansList = HuanXinUtils.getInstance().getMessageList();
            messageAdapter.setNewData(chatDataBeansList);
        },100);
    }

    //消息处理者,创建一个Handler的子类对象,目的是重写Handler的处理消息的方法(handleMessage())
    private Handler handler = new Handler();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
