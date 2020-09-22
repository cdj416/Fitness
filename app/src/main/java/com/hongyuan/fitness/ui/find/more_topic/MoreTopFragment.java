package com.hongyuan.fitness.ui.find.more_topic;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.find.circle.circle_detail.CircleDetailsActivity;

import java.util.List;

public class MoreTopFragment extends CustomFragment {

    private RecyclerView mRecycler;
    private MoreTopicAdapter adapter;

    private List<TopicBeans.DataBean.ListBean> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_more_topic;
    }

    @Override
    public void initView(View mView) {
        //开启刷新
        setEnableRefresh(true);
        //开启分页
        setEnableLoadMore(true);

        mRecycler = mView.findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new MoreTopicAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("circle_categoryid",String.valueOf(mList.get(position).getCategory_id()));
                startActivity(CircleDetailsActivity.class,bundle);
            }
        });
    }

    /*
     * 加载更多
     * */
    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if(mList != null){
            mList.clear();
        }

        mActivity.showLoading();
        clearParams().setParams("parent_id",getFragType());
        Controller.myRequest(Constants.GET_CIRCLE_CATEGORY_LIST,Controller.TYPE_POST,getParams(), TopicBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();
        if(data instanceof TopicBeans){
            TopicBeans topicBeans = (TopicBeans)data;

            if(curPage == FIRST_PAGE){
                if(topicBeans.getData().getList() != null && topicBeans.getData().getList().size() > 0){
                    mList = topicBeans.getData().getList();
                }

            }else{
                if(topicBeans.getData().getList() != null && topicBeans.getData().getList().size() > 0){
                    mList.addAll(topicBeans.getData().getList());
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
    }
}
