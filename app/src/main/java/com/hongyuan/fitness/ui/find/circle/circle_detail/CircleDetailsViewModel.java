package com.hongyuan.fitness.ui.find.circle.circle_detail;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityCircleDetailsBinding;
import com.hongyuan.fitness.ui.find.circle.edit_post.EditPostActivity;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsActivity;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsLikeBean;
import com.hongyuan.fitness.ui.find.circle.topic_participant.TopicParticipantActivity;
import com.hongyuan.fitness.ui.find.topic.SlectTopicRighttBeans;
import com.hongyuan.fitness.ui.main.main_find.featured.FeatureBean;
import com.hongyuan.fitness.ui.main.main_find.featured.V2FindContentAdapter;
import com.hongyuan.fitness.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class CircleDetailsViewModel extends CustomViewModel {

    private ActivityCircleDetailsBinding binding;
    private CircleDetailsBeans.DataBean detailsBeans;

    private V2FindContentAdapter adapter;
    private FeatureBean featureBean;

    private PersonHeaderAdapter headerAdapter;
    private List<String> headList = new ArrayList<>();

    //点赞取消记录下标
    private int mPosition;


    public CircleDetailsViewModel(CustomActivity mActivity, ActivityCircleDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        //开启刷新功能
        setEnableRefresh(true);
        //开启加载更多功能
        setEnableLoadMore(true);

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        binding.mRecycler.setLayoutManager(layoutManager);
        adapter = new V2FindContentAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.jumpDetails){
                Bundle bundle = new Bundle();
                bundle.putString("circle_id",String.valueOf(featureBean.getData().getList().get(position).getCircle_id()));
                startActivity(PostDetailsActivity.class,bundle);
            }
            if(view.getId() == R.id.attention){
                //帖子点赞/取消点赞
                getBaikeLike(position);
            }
        });

        LinearLayoutManager headMag = new LinearLayoutManager(mActivity);
        headMag.setOrientation(RecyclerView.HORIZONTAL);
        binding.headRec.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.VERTICAL_LIST,20,mActivity.getResources().getColor(R.color.transparent)));
        binding.headRec.setLayoutManager(headMag);
        headerAdapter = new PersonHeaderAdapter();
        binding.headRec.setAdapter(headerAdapter);
        headerAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("circle_categoryid",String.valueOf(detailsBeans.getCategory_id()));
            startActivity(TopicParticipantActivity.class,bundle);
        });

        binding.goEdit.setOnClickListener(v -> {
            SlectTopicRighttBeans.DataBean.ListBean topBean = new SlectTopicRighttBeans.DataBean.ListBean();
            topBean.setCategory_id(detailsBeans.getCategory_id());
            topBean.setCategory_name(detailsBeans.getCategory_name());
            topBean.setParent_id(detailsBeans.getParent_id());
            topBean.setCount(detailsBeans.getCount());
            topBean.setCategory_note(detailsBeans.getCategory_note());
            topBean.setCategory_img(detailsBeans.getCategory_img());

            Bundle bundle = new Bundle();
            bundle.putSerializable("topBeans",topBean);
            startActivity(EditPostActivity.class,bundle);
        });
    }

    @Override
    protected void setData() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mActivity).load(detailsBeans.getCategory_img()).apply(options).into(binding.topicImg);

        binding.topicName.setText(detailsBeans.getCategory_name());
        binding.topicPeople.setText(detailsBeans.getCount()+"人参与");
        binding.topicDes.setText(detailsBeans.getCategory_note());

        headList.clear();
        headList.addAll(detailsBeans.getHead());
        headList.add(null);
        headerAdapter.setNewData(headList);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();

        clearParams().setParams("circle_categoryid",getBundle().getString("circle_categoryid"));
        Controller.myRequest(Constants.GET_CIRCLE_CATEGORY_INFO,Controller.TYPE_POST,getParams(), CircleDetailsBeans.class,this);

        getCircleList();
    }

    @Override
    protected void loadMoreData() {
        getCircleList();
    }

    @Override
    public void refreshData() {
        featureBean = null;
        getCircleList();
    }

    /*
     * 获取发现帖子
     * */
    private void getCircleList(){
        mActivity.showLoading();
        //获取帖子
        clearParams().setParams("circle_state","1").setParams("city_name","湖州市")
                .setParams("circle_categoryid",getBundle().getString("circle_categoryid"));
        Controller.myRequest(Constants.GET_CIRCLE_LIST,Controller.TYPE_POST,getParams(), FeatureBean.class,this);

    }

    /*
     * 帖子点赞/取消
     * */
    private void getBaikeLike(int position){
        mActivity.showLoading();
        mPosition = position;
        clearParams().setParams("circle_id",String.valueOf(featureBean.getData().getList().get(position).getCircle_id()));
        if(featureBean.getData().getList().get(position).getIs_praise() == 0){
            Controller.myRequest(ConstantsCode.ADD_CIRCLE_PRAISE,Constants.ADD_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }else{
            Controller.myRequest(ConstantsCode.CANCEL_CIRCLE_PRAISE,Constants.CANCEL_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof FeatureBean && isSuccess(data)){
            mActivity.closeLoading();
            FeatureBean pageData = (FeatureBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    featureBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    featureBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(featureBean != null && featureBean.getData() != null &&
                    featureBean.getData().getList() != null &&
                    featureBean.getData().getList().size() > 0){
                adapter.setNewData(featureBean.getData().getList());
                binding.mRecycler.setVisibility(View.VISIBLE);
                binding.emptyBox.setVisibility(View.GONE);
            }else{
                binding.mRecycler.setVisibility(View.GONE);
                binding.emptyBox.setVisibility(View.VISIBLE);
            }

        }

        if(data instanceof CircleDetailsBeans){
            detailsBeans = ((CircleDetailsBeans) data).getData();
            setData();
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();
        if(code == ConstantsCode.ADD_CIRCLE_PRAISE){
            featureBean.getData().getList().get(mPosition).setIs_praise(1);
            featureBean.getData().getList().get(mPosition).setPraise_num(featureBean.getData().getList().get(mPosition).getPraise_num()+1);
            adapter.notifyDataSetChanged();
            showSuccess("点赞成功！");
        }
        if(code == ConstantsCode.CANCEL_CIRCLE_PRAISE){
            featureBean.getData().getList().get(mPosition).setIs_praise(0);
            featureBean.getData().getList().get(mPosition).setPraise_num(featureBean.getData().getList().get(mPosition).getPraise_num()-1);
            adapter.notifyDataSetChanged();
            showSuccess("已取消点赞！");
        }
    }
}
