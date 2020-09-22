package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.person.person_message.PersonAttentionBeans;
import com.hongyuan.fitness.ui.person.person_message.PersonMessageActivity;
import com.hongyuan.fitness.ui.shop.sadapter.FindUserAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.FindUserBeans;
import com.hongyuan.fitness.ui.shop.sbeans.IsFriendsBeans;
import com.hongyuan.fitness.util.BaseUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class FindUserFragment extends CustomFragment {

    private RecyclerView mRec;
    private FindUserAdapter adapter;

    //数据集
    private List<FindUserBeans.DataBean.ListBean> mList;

    //搜索添加筛选
    private String seachText;

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
        adapter = new FindUserAdapter();
        mRec.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {

            this.mPosition = position;

            getIsFriends(String.valueOf(mList.get(position).getM_id()));
        });
    }

    //查询当前粉丝是否已关注
    private void getIsFriends(String f_mid){
        mActivity.showLoading();

        clearParams().setParams("f_mid",f_mid);
        Controller.myRequest(Constants.IS_MY_FRIEND,Controller.TYPE_POST,getParams(), IsFriendsBeans.class,this);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        if(BaseUtil.isValue(seachText)){
            setParams("search_name",seachText);
        }
        Controller.myRequest(Constants.FIND_MEMBER_LIST,Controller.TYPE_POST,getParams(), FindUserBeans.class,this);
    }


    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof FindUserBeans){

            List<FindUserBeans.DataBean.ListBean> list = ((FindUserBeans)data).getData().getList();
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

    /*
     * 搜索
     * */
    @Subscribe(id = ConstantsCode.EB_FIND_SEARCH)
    public void search(String message) {

        //每次搜索初始化页数为1
        curPage = FIRST_PAGE;
        seachText = message;
        lazyLoad();
    }

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
