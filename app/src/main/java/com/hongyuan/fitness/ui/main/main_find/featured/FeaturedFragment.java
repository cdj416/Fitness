package com.hongyuan.fitness.ui.main.main_find.featured;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.CustomRecyclerView;
import com.hongyuan.fitness.ui.find.circle.circle_detail.CircleDetailsActivity;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsActivity;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsLikeBean;
import com.hongyuan.fitness.ui.find.friends.FriendsActivity;
import com.hongyuan.fitness.util.DividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class FeaturedFragment extends CustomFragment {

    private CustomRecyclerView topRecycler;
    private RecyclerView mRecycler;
    private FindTopFriendsAdapter topAdapter;
    private V2FindContentAdapter adapter;
    private FeatureBean featureBean;
    private TextView topTitle,moreFriends;
    private RelativeLayout topBox;

    //当前（点赞/取消点赞/关注/取消关注）等操作的数据位置
    private int mPosition;
    //区分当前是请求的添加好友（关注）还是删除好友（取关）
    private String attentionType;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_featured;
    }

    @Override
    public void initView(View mView) {
        //开启刷新功能
        setEnableRefresh(true);
        //开启加载更多功能
        setEnableLoadMore(true);

        topRecycler = mView.findViewById(R.id.topRecycler);
        mRecycler = mView.findViewById(R.id.mRecycler);
        topTitle = mView.findViewById(R.id.topTitle);
        moreFriends = mView.findViewById(R.id.moreFriends);
        topBox = mView.findViewById(R.id.topBox);

        moreFriends.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                startActivity(FriendsActivity.class,null);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,30,0x00000000));
        topRecycler.setLayoutManager(manager);
        topAdapter = new FindTopFriendsAdapter();
        topRecycler.setAdapter(topAdapter);
        //topAdapter.setNewData(getmeunList());

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecycler.setLayoutManager(layoutManager);
        adapter = new V2FindContentAdapter();
        mRecycler.setAdapter(adapter);

        topAdapter.setOnItemChildClickListener((adapter, view, position) -> startActivity(CircleDetailsActivity.class,null));

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

    }

    /*
     * 帖子点赞/取消
     * */
    private void getBaikeLike(int position){
        mPosition = position;
        clearParams().setParams("circle_id",String.valueOf(featureBean.getData().getList().get(position).getCircle_id()));
        if(featureBean.getData().getList().get(position).getIs_praise() == 0){
            Controller.myRequest(ConstantsCode.ADD_CIRCLE_PRAISE,Constants.ADD_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }else{
            Controller.myRequest(ConstantsCode.CANCEL_CIRCLE_PRAISE,Constants.CANCEL_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }
    }

    @Override
    protected void lazyLoad() {
        featureBean = null;
        if(mActivity.userToken.getM_id() != null && "gz".equals(getFragType())){
            //获取好友列表
            Controller.myRequest(Constants.GET_MY_FRIENDS,Controller.TYPE_POST,getParams(), FriendsBeans.class,this);
        }

        //获取推荐圈子
        getCircleList();

    }

    /*
     * 加载更多
     * */
    @Override
    public void loadMoreData() {
        getCircleList();
    }

    /*
    * 获取发现帖子
    * */
    private void getCircleList(){
        mActivity.showLoading();
        //获取帖子
        clearParams().setParams("circle_state","1").setParams("circle_type",getFragType()).setParams("city_name","湖州市");
        Controller.myRequest(Constants.GET_CIRCLE_LIST,Controller.TYPE_POST,getParams(), FeatureBean.class,this);
    }

    /*
    * 不需要做区分走这里
    * */
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
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }

        }
        if(data instanceof FriendsBeans){
            FriendsBeans friendsBeans = (FriendsBeans)data;
            if(friendsBeans.getData().getList() != null && friendsBeans.getData().getList().size() > 0){
                topRecycler.setVisibility(View.VISIBLE);
                topBox.setVisibility(View.VISIBLE);
                topAdapter.setNewData(friendsBeans.getData().getList());
            }else{
                topRecycler.setVisibility(View.GONE);
                topBox.setVisibility(View.GONE);
            }

        }
    }

    /*
    * 需要做区分走这里
    * */
    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.ADD_CIRCLE_PRAISE){
            featureBean.getData().getList().get(mPosition).setIs_praise(1);
            featureBean.getData().getList().get(mPosition).setPraise_num(featureBean.getData().getList().get(mPosition).getPraise_num()+1);
            adapter.setNewData(featureBean.getData().getList());
            showSuccess("点赞成功！");
        }
        if(code == ConstantsCode.CANCEL_CIRCLE_PRAISE){
            featureBean.getData().getList().get(mPosition).setIs_praise(0);
            featureBean.getData().getList().get(mPosition).setPraise_num(featureBean.getData().getList().get(mPosition).getPraise_num()-1);
            adapter.setNewData(featureBean.getData().getList());
            showSuccess("已取消点赞！");
        }
        if(code == ConstantsCode.ADD_FRIEND){
            if("add".equals(attentionType)){
                showSuccess("关注成功！");
            }
            if("reduce".equals(attentionType)){
                showSuccess("取关成功！");
            }
            EventBus.getDefault().post(ConstantsCode.EB_ADD_CIRCLE,"操作成功");
        }
    }

    /*
     * 刷新下数据
     * */
    @Subscribe(id = ConstantsCode.EB_ADD_CIRCLE)
    public void result(String message) {
        featureBean = null;
        refreshData();
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
