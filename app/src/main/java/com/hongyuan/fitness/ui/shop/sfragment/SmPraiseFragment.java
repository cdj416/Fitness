package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sactivity.SmParaiseDetailsActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SmPraiseAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.SmPraiseBeans;

import java.util.List;

public class SmPraiseFragment extends CustomFragment {

    private RecyclerView mRec;
    private SmPraiseAdapter adapter;

    //数据集
    private List<SmPraiseBeans.DataBean.ListBean> mList;

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
        adapter = new SmPraiseAdapter();
        mRec.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("circle_id",String.valueOf(mList.get(position).getCircle_id()));
            startActivity(SmParaiseDetailsActivity.class,bundle);
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_PRAISE_MSG_LIST,Controller.TYPE_POST,getParams(), SmPraiseBeans.class,this);
    }

    @Override
    public void refreshData() {
        lazyLoad();
    }

    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();

        if(data instanceof SmPraiseBeans){

            List<SmPraiseBeans.DataBean.ListBean> list = ((SmPraiseBeans)data).getData().getList();
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
}
