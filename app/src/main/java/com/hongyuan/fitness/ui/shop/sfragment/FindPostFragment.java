package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsActivity;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsLikeBean;
import com.hongyuan.fitness.ui.main.main_find.featured.FeatureBean;
import com.hongyuan.fitness.ui.main.main_find.featured.V2FindContentAdapter;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DensityUtil;
import com.hongyuan.fitness.util.MMStaggeredGridLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class FindPostFragment extends CustomFragment {

    private RecyclerView mRec;
    private V2FindContentAdapter adapter;
    private List<FeatureBean.DataBean.ListBean> mList;
    //当前（点赞/取消点赞/关注/取消关注）等操作的数据位置
    private int mPosition;

    //搜索添加筛选
    private String seachText;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_recylerview;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        mRec = mView.findViewById(R.id.mRec);

        MMStaggeredGridLayoutManager layoutManager =
                new MMStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRec.setLayoutManager(layoutManager);
        adapter = new V2FindContentAdapter(DensityUtil.getColumnWhith(mActivity,38,2));
        mRec.setAdapter(adapter);
        adapter.setFooterView(getFooterHeight(mRec));


        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.jumpDetails){
                Bundle bundle = new Bundle();
                bundle.putString("circle_id",String.valueOf(mList.get(position).getCircle_id()));
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
        if(mList == null || mList.size() <=0){
            CustomDialog.showMessage(mActivity,"数据获取失败，请下拉刷新！");
            return;
        }

        mActivity.showLoading();
        mPosition = position;
        clearParams().setParams("circle_id",String.valueOf(mList.get(position).getCircle_id()));
        if(mList.get(position).getIs_praise() == 0){
            Controller.myRequest(ConstantsCode.ADD_CIRCLE_PRAISE, Constants.ADD_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }else{
            Controller.myRequest(ConstantsCode.CANCEL_CIRCLE_PRAISE,Constants.CANCEL_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }
    }

    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        if(BaseUtil.isValue(seachText)){
            setParams("search_name",seachText);
        }
        Controller.myRequest(Constants.FIND_CIRCLE_LIST,Controller.TYPE_POST,getParams(), FeatureBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof FeatureBean){

            List<FeatureBean.DataBean.ListBean> list = ((FeatureBean)data).getData().getList();
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
    }

    /*
     * 需要做区分走这里
     * */
    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.ADD_CIRCLE_PRAISE){
            mList.get(mPosition).setIs_praise(1);
            mList.get(mPosition).setPraise_num(mList.get(mPosition).getPraise_num()+1);
            adapter.setNewData(mList);
            EventBus.getDefault().post(ConstantsCode.EB_HOME_REFRESH,"同步数据");
            showSuccess("点赞成功！");
        }
        if(code == ConstantsCode.CANCEL_CIRCLE_PRAISE){
            mList.get(mPosition).setIs_praise(0);
            mList.get(mPosition).setPraise_num(mList.get(mPosition).getPraise_num()-1);
            adapter.setNewData(mList);
            EventBus.getDefault().post(ConstantsCode.EB_HOME_REFRESH,"同步数据");
            showSuccess("已取消点赞！");
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
