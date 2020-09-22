package com.hongyuan.fitness.ui.main.main_find.featured;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
import com.hongyuan.fitness.ui.find.topic.SlectTopicLeftBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DensityUtil;
import com.hongyuan.fitness.util.MMStaggeredGridLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class FeaturedFragment extends CustomFragment {

    private CustomRecyclerView topRecycler;
    private RecyclerView mRecycler;
    private FindTopFriendsAdapter friendsAdapter;
    private V2FindContentAdapter adapter;
    private FeatureBean featureBean;
    private TextView topTitle,moreFriends;
    private RelativeLayout topBox,topicBox;

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
        setEnableLoadMore(true,true);

        mRecycler = mView.findViewById(R.id.mRecycler);
        topTitle = mView.findViewById(R.id.topTitle);
        moreFriends = mView.findViewById(R.id.moreFriends);
        topBox = mView.findViewById(R.id.topBox);
        //topicBox = mView.findViewById(R.id.topicBox);

        topRecycler = mView.findViewById(R.id.topRecycler);


        moreFriends.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                startActivity(FriendsActivity.class,null);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topRecycler.setLayoutManager(manager);
        friendsAdapter = new FindTopFriendsAdapter();
        topRecycler.setAdapter(friendsAdapter);
        friendsAdapter.setOnItemChildClickListener((adapter, view, position) -> startActivity(CircleDetailsActivity.class,null));


        MMStaggeredGridLayoutManager layoutManager =
                new MMStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecycler.setLayoutManager(layoutManager);
        adapter = new V2FindContentAdapter(DensityUtil.getColumnWhith(mActivity,38,2));
        mRecycler.setAdapter(adapter);
        adapter.setFooterView(getFooterHeight(mRecycler));


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
        if(featureBean == null || featureBean.getData() == null || featureBean.getData().getList() == null || featureBean.getData().getList().size() <=0){
            CustomDialog.showMessage(mActivity,"数据获取失败，请下拉刷新！");
            return;
        }

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
    protected void lazyLoad() {
        featureBean = null;
        if(mActivity.userToken.getM_id() != null && "gz".equals(getFragType())){
            try {
                //获取好友列表
                Controller.myRequest(Constants.GET_MY_FRIENDS,Controller.TYPE_POST,getParams(), FriendsBeans.class,this);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if("tj".equals(getFragType())){
            clearParams().setParams("page","4").setParams("curpage","");
            Controller.myRequest(Constants.GET_CIRCLE_CATEGORY_LIST,Controller.TYPE_POST,getParams(), SlectTopicLeftBeans.class,this);
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
        try {
            clearParams().setParams("circle_state","1").setParams("city_name",mActivity.userToken.getRegion_name());
            //获取帖子
            if("tj".equals(getFragType())){
                setParams("is_tj","1");
            }
            if(BaseUtil.isValue(getFragType())){
                setParams("circle_type",getFragType());
            }
            Controller.myRequest(Constants.GET_CIRCLE_LIST,Controller.TYPE_POST,getParams(), FeatureBean.class,this);
        }catch (Exception e){
            CustomDialog.showMessage(mActivity,"请下拉刷新");
        }

    }

    /*
     * 不需要做区分走这里
     * */
    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

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
                if(friendsAdapter != null){
                    friendsAdapter.setNewData(friendsBeans.getData().getList());
                }
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
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.ADD_CIRCLE_PRAISE){
            featureBean.getData().getList().get(mPosition).setIs_praise(1);
            featureBean.getData().getList().get(mPosition).setPraise_num(featureBean.getData().getList().get(mPosition).getPraise_num()+1);
            adapter.setNewData(featureBean.getData().getList());
            EventBus.getDefault().post(ConstantsCode.EB_HOME_REFRESH,"同步数据");
            showSuccess("点赞成功！");
        }
        if(code == ConstantsCode.CANCEL_CIRCLE_PRAISE){
            featureBean.getData().getList().get(mPosition).setIs_praise(0);
            featureBean.getData().getList().get(mPosition).setPraise_num(featureBean.getData().getList().get(mPosition).getPraise_num()-1);
            adapter.setNewData(featureBean.getData().getList());
            EventBus.getDefault().post(ConstantsCode.EB_HOME_REFRESH,"同步数据");
            showSuccess("已取消点赞！");
        }
    }

    /*
     * 刷新下数据
     * */
    @Subscribe(id = ConstantsCode.EB_ADD_CIRCLE)
    public void result(String message) {
        featureBean = null;
        lazyLoad();
    }

    /*
     * 刷新定位城市
     * */
    @Subscribe(id = ConstantsCode.EB_HOME_LOCATION)
    public void changeLocation(String message) {
        //城市切换了去刷新下数据
        featureBean = null;
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
