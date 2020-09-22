package com.hongyuan.fitness.ui.encyclopedia;

import android.annotation.SuppressLint;
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
import com.hongyuan.fitness.ui.encyclopedia.encyclopedia_detail.EncyclopediaDetailActivity;

public class EncyclopediaNewestFragment extends CustomFragment {
    private RecyclerView mRecycler;
    private EncyclopediaAdapter adapter;
    private EncyclopediaBean listBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_enclopedia;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void initView(View mView) {
        //开启刷新
        setEnableRefresh(true);
        //开启分页
        setEnableLoadMore(true);

        mRecycler = mView.findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new EncyclopediaAdapter();
        mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick (2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("article_id",String.valueOf(listBean.getData().getList().get(position).getArticle_id()));
                startActivity(EncyclopediaDetailActivity.class,bundle);
            }
        });
    }

    @Override
    protected void lazyLoad() {
        getList();
    }

    /*
    * 加载更多
    * */
    @Override
    public void loadMoreData() {
        getList();
    }

    //刷新数据
    /*@Override
    public void refreshData() {
        lazyLoad();
    }*/

    /*
    * 获取百科列表
    * */
    private void getList(){
        mActivity.showLoading();
        Controller.myRequest(Constants.GET_ARTICLE_LIST_NEWS,Controller.TYPE_POST,getParams(), EncyclopediaBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof EncyclopediaBean && isSuccess(data)){
            EncyclopediaBean pageData = (EncyclopediaBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    listBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    listBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(listBean != null && listBean.getData() != null &&
                    listBean.getData().getList() != null &&
                    listBean.getData().getList().size() > 0){

                adapter.setNewData(listBean.getData().getList());
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }

        }
    }
}
