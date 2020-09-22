package com.hongyuan.fitness.ui.find.friends;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityFriendsBinding;
import com.hongyuan.fitness.ui.find.circle.post_details.AttentionBean;
import com.hongyuan.fitness.ui.main.main_find.featured.FriendsBeans;
import com.hongyuan.fitness.ui.person.person_message.PersonAttentionBeans;
import com.hongyuan.fitness.ui.person.person_message.PersonMessageActivity;
import com.hongyuan.fitness.ui.shop.sbeans.IsFriendsBeans;
import com.hongyuan.fitness.util.CustomDialog;

import org.greenrobot.eventbus.EventBus;

public class FriendsViewModel extends CustomViewModel {

    private ActivityFriendsBinding binding;
    private FriendsAdapter adapter;
    private FriendsBeans friendsBeans;

    //当前点击的是第几的一个对象
    private int mPosition;

    public FriendsViewModel(CustomActivity mActivity , ActivityFriendsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new FriendsAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {

            if(view.getId() == R.id.box){
                this.mPosition = position;

                getIsFriends(String.valueOf(friendsBeans.getData().getList().get(position).getF_mid()));
            }else{
                CustomDialog.promptDialog(mActivity, "确定要取消关注吗？", "取消", "确定", false, v -> {
                    if(v.getId() == R.id.isCannel){
                        sendAttention(position);
                    }
                });
            }

        });
    }


    //查询当前粉丝是否已关注
    private void getIsFriends(String f_mid){
        mActivity.showLoading();

        clearParams().setParams("f_mid",f_mid);
        Controller.myRequest(Constants.IS_MY_FRIEND,Controller.TYPE_POST,getParams(), IsFriendsBeans.class,this);
    }

    @Override
    public void refreshData() {
        friendsBeans = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        //获取好友列表
        Controller.myRequest(Constants.GET_MY_FRIENDS,Controller.TYPE_POST,getParams(), FriendsBeans.class,this);
    }

    /*
     *取消关注
     * */
    private void sendAttention(int position){
        clearParams().setParams("f_mid",String.valueOf(friendsBeans.getData().getList().get(position).getF_mid()))
        .setParams("f_type","reduce");
        Controller.myRequest(ConstantsCode.ADD_FRIEND,Constants.ADD_FRIEND,Controller.TYPE_POST,getParams(), AttentionBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        if(data instanceof FriendsBeans){
            FriendsBeans pageData = (FriendsBeans)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    friendsBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    friendsBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(friendsBeans != null && friendsBeans.getData() != null &&
                    friendsBeans.getData().getList() != null &&
                    friendsBeans.getData().getList().size() > 0){
                adapter.setNewData(friendsBeans.getData().getList());
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView( mActivity.SHOW_EMPTY);
            }
        }

        if(data instanceof IsFriendsBeans){
            IsFriendsBeans.DataBean isFriendsBeans = ((IsFriendsBeans)data).getData();

            PersonAttentionBeans attentionBeans = new PersonAttentionBeans();
            attentionBeans.setIs_friend(isFriendsBeans.getIs_friend());
            attentionBeans.setM_id(friendsBeans.getData().getList().get(mPosition).getF_mid());
            attentionBeans.setM_mobile(friendsBeans.getData().getList().get(mPosition).getM_mobile());
            attentionBeans.setM_name(friendsBeans.getData().getList().get(mPosition).getM_name());
            attentionBeans.setMi_head(friendsBeans.getData().getList().get(mPosition).getMi_head());

            Bundle bundle = new Bundle();
            bundle.putSerializable("otherPerson",attentionBeans);
            startActivity(PersonMessageActivity.class,bundle);
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        if(code == ConstantsCode.ADD_FRIEND){
            refreshData();
            showSuccess("已取消关注！");
        }
    }
}
