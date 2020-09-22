package com.hongyuan.fitness.ui.find.circle.topic_participant;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityTopicParticipantBinding;
import com.hongyuan.fitness.ui.find.circle.post_details.AttentionBean;
import com.hongyuan.fitness.ui.person.person_message.PersonAttentionBeans;
import com.hongyuan.fitness.ui.person.person_message.PersonMessageActivity;
import com.hongyuan.fitness.util.CustomDialog;

public class TopicParticipantViewModel extends CustomViewModel {

    private ActivityTopicParticipantBinding binding;
    private TopicParticipantAdapter adapter;
    private TopicParticpantBeans particpantBeans;

    //操作数据的项
    private int mPosition;

    public TopicParticipantViewModel(CustomActivity mActivity , ActivityTopicParticipantBinding binding) {
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
        adapter = new TopicParticipantAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mPosition = position;
                if(view.getId() == R.id.box){
                    PersonAttentionBeans attentionBeans = new PersonAttentionBeans();
                    attentionBeans.setIs_friend(particpantBeans.getData().getList().get(position).getIs_friend());
                    attentionBeans.setM_id(particpantBeans.getData().getList().get(position).getM_id());
                    attentionBeans.setM_mobile(particpantBeans.getData().getList().get(position).getM_mobile());
                    attentionBeans.setM_name(particpantBeans.getData().getList().get(position).getM_name());
                    attentionBeans.setMi_head(particpantBeans.getData().getList().get(position).getMi_head());

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("otherPerson",attentionBeans);
                    startActivity(PersonMessageActivity.class,bundle);
                }else{
                    if(particpantBeans.getData().getList().get(position).getIs_friend() == 1){
                        CustomDialog.promptDialog(mActivity, "确定要取消关注吗？", "暂不取消", "取消关注", false, v -> {
                            if(v.getId() == R.id.isCannel){
                                sendAttention(position);
                            }
                        });
                    }else{
                        sendAttention(position);
                    }
                }
            }
        });
    }

    @Override
    public void refreshData() {
        particpantBeans = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {

        //圈子--读取参加主题的用户列表
        clearParams().setParams("circle_categoryid",getBundle().getString("circle_categoryid"));
        Controller.myRequest(Constants.GET_CIRCLE_MEMBER_LIST,Controller.TYPE_POST,getParams(), TopicParticpantBeans.class,this);
    }

    /*
     *关注、取消
     * */
    private void sendAttention(int position){
        mActivity.showLoading();
        clearParams().setParams("f_mid",String.valueOf(particpantBeans.getData().getList().get(position).getM_id()));
        if(particpantBeans.getData().getList().get(position).getIs_friend() == 1){
           setParams("f_type","reduce");
        }else{
            setParams("f_type","add");
        }
        Controller.myRequest(ConstantsCode.ADD_FRIEND,Constants.ADD_FRIEND,Controller.TYPE_POST,getParams(), AttentionBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        if(data instanceof TopicParticpantBeans){
            TopicParticpantBeans pageData = (TopicParticpantBeans)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    particpantBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    particpantBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(particpantBeans != null && particpantBeans.getData() != null &&
                    particpantBeans.getData().getList() != null &&
                    particpantBeans.getData().getList().size() > 0){
                adapter.setNewData(particpantBeans.getData().getList());
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView( mActivity.SHOW_EMPTY);
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.ADD_FRIEND){
            if(particpantBeans.getData().getList().get(mPosition).getIs_friend() == 1){
                particpantBeans.getData().getList().get(mPosition).setIs_friend(0);
                showSuccess("取关成功！");
            }else{
                particpantBeans.getData().getList().get(mPosition).setIs_friend(1);
                showSuccess("关注成功！");
            }
            adapter.notifyDataSetChanged();
        }
    }
}
