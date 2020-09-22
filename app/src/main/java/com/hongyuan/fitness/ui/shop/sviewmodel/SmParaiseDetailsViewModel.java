package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivitySmParaiseDetailsBinding;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsActivity;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsTopBean;
import com.hongyuan.fitness.ui.shop.sadapter.SmParaiseDetailsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.SmParaiseDetailsBeans;

import java.util.List;

public class SmParaiseDetailsViewModel extends CustomViewModel {

    private ActivitySmParaiseDetailsBinding binding;

    //帖子详情
    private PostDetailsTopBean postDetails;

    private SmParaiseDetailsAdapter adapter;

    private List<SmParaiseDetailsBeans.DataBean.ListBean> mList;


    public SmParaiseDetailsViewModel(CustomActivity mActivity, ActivitySmParaiseDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new SmParaiseDetailsAdapter();
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRec));

        binding.goDetail.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("circle_id",String.valueOf(postDetails.getData().getCircle_id()));
            startActivity(PostDetailsActivity.class,bundle);
        });

    }

    @Override
    public void refreshData(){
        curPage = FIRST_PAGE;
        getList();
    }

    @Override
    protected void loadMoreData() {
        getList();
    }
    @Override
    protected void lazyLoad() {
        mActivity.showLoading();

        //圈子帖子详情
        clearParams().setParams("circle_id",getBundle().getString("circle_id"));
        Controller.myRequest(Constants.GET_CIRCLE_INFO,Controller.TYPE_POST,getParams(), PostDetailsTopBean.class,this);

        getList();
    }
    private void getList(){
        //读取帖子点赞人员
        clearParams().setParams("circle_id",getBundle().getString("circle_id"));
        Controller.myRequest(Constants.GET_PRAISE_LIST,Controller.TYPE_POST,getParams(), SmParaiseDetailsBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof PostDetailsTopBean && isSuccess(data)){
            postDetails = (PostDetailsTopBean)data;
            RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
            Glide.with(mActivity).load(postDetails.getData().getCircle_img()).apply(options).into(binding.circleImg);

            binding.postContent.setText(postDetails.getData().getCircle_content());
        }

        if(data instanceof SmParaiseDetailsBeans){
            List<SmParaiseDetailsBeans.DataBean.ListBean> list = ((SmParaiseDetailsBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    mActivity.refresh.finishLoadMoreWithNoMoreData();
                }
            }
            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }

        }
    }
}
