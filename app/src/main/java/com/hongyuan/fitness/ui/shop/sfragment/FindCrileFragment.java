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
import com.hongyuan.fitness.ui.find.circle.circle_detail.CircleDetailsActivity;
import com.hongyuan.fitness.ui.find.more_topic.MoreTopicAdapter;
import com.hongyuan.fitness.ui.find.more_topic.TopicBeans;
import com.hongyuan.fitness.util.BaseUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class FindCrileFragment extends CustomFragment {

    private RecyclerView mRec;
    private MoreTopicAdapter adapter;

    //数据集
    private List<TopicBeans.DataBean.ListBean> mList;

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

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRec.setLayoutManager(manager);
        adapter = new MoreTopicAdapter();
        mRec.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("circle_categoryid",String.valueOf(mList.get(position).getCategory_id()));
            startActivity(CircleDetailsActivity.class,bundle);
        });
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
        Controller.myRequest(Constants.GET_CIRCLE_CATEGORY_LIST,Controller.TYPE_POST,getParams(), TopicBeans.class,this);
    }


    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof TopicBeans){

            List<TopicBeans.DataBean.ListBean> list = ((TopicBeans)data).getData().getList();
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
