package com.hongyuan.fitness.ui.find.topic;
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
import com.hongyuan.fitness.databinding.ActivityFindTopicBinding;
import com.hongyuan.fitness.ui.find.circle.edit_post.EditPostViewModel;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class SelectTopicViewModel extends CustomViewModel {

    private ActivityFindTopicBinding binding;
    private SelectTopicLeftAdapter leftAdapter;
    private List<SlectTopicLeftBeans.DataBean.ListBean> leftList;

    private SelectTopicRightdapter rightdapter;
    private List<SlectTopicRighttBeans.DataBean.ListBean> rightList;

    //分页需要传递的参数
    private String parent_id = "0";


    public SelectTopicViewModel(CustomActivity mActivity, ActivityFindTopicBinding binding) {
        super(mActivity);
        this.binding = binding;
        lazyLoad();
        initView();
    }

    @Override
    protected void initView() {
        LinearLayoutManager LeftManager = new LinearLayoutManager(mActivity);
        LeftManager.setOrientation(RecyclerView.VERTICAL);
        binding.leftRec.setLayoutManager(LeftManager);
        leftAdapter = new SelectTopicLeftAdapter(mActivity.skin);
        binding.leftRec.setAdapter(leftAdapter);
        leftAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                for(int i = 0 ; i < leftList.size() ; i++){
                    leftList.get(i).setSelect(false);
                }
                leftList.get(position).setSelect(true);
                leftAdapter.notifyDataSetChanged();
                parent_id = String.valueOf(leftList.get(position).getCategory_id());
                getTopicMenu(String.valueOf(leftList.get(position).getCategory_id()));
            }
        });

        LinearLayoutManager rightManager = new LinearLayoutManager(mActivity);
        rightManager.setOrientation(RecyclerView.VERTICAL);
        binding.rightRec.setLayoutManager(rightManager);
        rightdapter = new SelectTopicRightdapter();
        binding.rightRec.setAdapter(rightdapter);
        rightdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("topicItemBean",rightList.get(position));
                bundle.putInt("type", EditPostViewModel.TOPIC);
                setResult(bundle);
            }
        });
        setOnRefresh();
    }

    /*
     * 初始化加载控件各个设置
     * */
    private void setOnRefresh(){
        //关闭滚动到底部自动加载
        binding.refresh.setEnableAutoLoadMore(false);
        //设置主题颜色
        binding.refresh.setPrimaryColors(0xFFF2F2F2);
        //初始刷新动画
        binding.refresh.setRefreshHeader(new MaterialHeader(mActivity).setShowBezierWave(true));
        //初始化加载动画
        binding.refresh.setRefreshFooter(new BallPulseFooter(mActivity).setSpinnerStyle(SpinnerStyle.Scale));
        //关闭上拉加载更多
        binding.refresh.setEnableLoadMore(true);
        //加载更多监听
        binding.refresh.setOnLoadMoreListener(onLoadMore());
        //是否开启自动刷新
        binding.refresh.setEnableRefresh(false);
        //是否开启刷新功能
        binding.refresh.setEnableRefresh(true);
        //设置刷新监听事件
        binding.refresh.setOnRefreshListener(onRefresh());
        //手动设置开启分页
        isLoadMore = true;
    }

    /*
     * 上啦加载更多监听
     * */
    private OnLoadMoreListener onLoadMore(){
        return refreshLayout -> {
            curPage++;
            loadMoreData();
        };
    }

    /*
     * 下拉刷新监听
     * */
    private OnRefreshListener onRefresh() {
        return refreshLayout -> {
            curPage = 1;
            refreshData();
        };
    }

    @Override
    public void refreshData() {
        getTopicMenu(parent_id);
    }

    @Override
    protected void loadMoreData() {
        getTopicMenu(parent_id);
    }

    @Override
    protected void lazyLoad() {
        getTopicMenu("0");
    }

    @Override
    public void closeRefresh() {
        if(binding.refresh != null){
            binding.refresh.finishRefresh();
            binding.refresh.finishLoadMore();
        }
    }

    /*
    * 获取话题的分类
    * */
    private void getTopicMenu(String parent_id){
        mActivity.showLoading();
        clearParams().setParams("parent_id",parent_id);
        if("0".equals(parent_id)){
            Controller.myRequest(Constants.GET_CIRCLE_CATEGORY_LIST,Controller.TYPE_POST,getParams(), SlectTopicLeftBeans.class,this);
        }else{
            Controller.myRequest(Constants.GET_CIRCLE_CATEGORY_LIST,Controller.TYPE_POST,getParams(), SlectTopicRighttBeans.class,this);
        }
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof  SlectTopicLeftBeans){
            leftList = ((SlectTopicLeftBeans)data).getData().getList();
            leftAdapter.setNewData(leftList);
            leftList.get(0).setSelect(true);
            parent_id = String.valueOf(leftList.get(0).getCategory_id());
            getTopicMenu(String.valueOf(leftList.get(0).getCategory_id()));
        }
        if(data instanceof SlectTopicRighttBeans){
            SlectTopicRighttBeans slectTopicRighttBeans = (SlectTopicRighttBeans)data;

            if(curPage == FIRST_PAGE){
                rightList = slectTopicRighttBeans.getData().getList();
            }else{
                if(slectTopicRighttBeans.getData().getList() != null && slectTopicRighttBeans.getData().getList().size() > 0){
                    rightList.addAll(slectTopicRighttBeans.getData().getList());
                }else{
                    binding.refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(rightList != null && rightList.size() > 0){
                rightdapter.setNewData(rightList);
                binding.promptTxt.setVisibility(View.GONE);
                binding.rightRec.setVisibility(View.VISIBLE);
            }else{
                binding.promptTxt.setVisibility(View.VISIBLE);
                binding.rightRec.setVisibility(View.GONE);
            }

        }
    }
}
