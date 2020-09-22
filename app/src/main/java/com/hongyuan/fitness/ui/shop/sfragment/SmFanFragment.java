package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.person.person_message.PersonAttentionBeans;
import com.hongyuan.fitness.ui.person.person_message.PersonMessageActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SmfanAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.IsFriendsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SmFanBeans;
import java.util.List;

public class SmFanFragment extends CustomFragment {

    private RecyclerView mRec;
    private SmfanAdapter adapter;

    //数据集
    private List<SmFanBeans.DataBean.ListBean> mList;

    //当前点击的是第几的一个对象
    private int mPosition;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_recylerview;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        mRec = mView.findViewById(R.id.mRec);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRec.setLayoutManager(manager);
        adapter = new SmfanAdapter();
        mRec.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            mPosition = position;

            getIsFriends(String.valueOf(mList.get(position).getM_id()));
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_FRIEND_MSG_LIST,Controller.TYPE_POST,getParams(), SmFanBeans.class,this);
    }

    @Override
    public void refreshData() {
        lazyLoad();
    }

    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    //查询当前粉丝是否已关注
    private void getIsFriends(String f_mid){
        mActivity.showLoading();

        clearParams().setParams("f_mid",f_mid);
        Controller.myRequest(Constants.IS_MY_FRIEND,Controller.TYPE_POST,getParams(), IsFriendsBeans.class,this);
    }


    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();

        if(data instanceof SmFanBeans){

            List<SmFanBeans.DataBean.ListBean> list = ((SmFanBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }

        }

        if(data instanceof IsFriendsBeans){
            IsFriendsBeans.DataBean isFriendsBeans = ((IsFriendsBeans)data).getData();

            PersonAttentionBeans attentionBeans = new PersonAttentionBeans();
            attentionBeans.setIs_friend(isFriendsBeans.getIs_friend());
            attentionBeans.setM_id(mList.get(mPosition).getM_id());
            attentionBeans.setM_mobile(mList.get(mPosition).getM_mobile());
            attentionBeans.setM_name(mList.get(mPosition).getM_name());
            attentionBeans.setMi_head(mList.get(mPosition).getMi_head());

            Bundle bundle = new Bundle();
            bundle.putSerializable("otherPerson",attentionBeans);
            startActivity(PersonMessageActivity.class,bundle);
        }
    }
}
