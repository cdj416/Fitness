package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SgoodsCollectAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.SgCollectGoodsBeans;
import java.util.List;

public class SgCollectFragment extends CustomFragment {

    private RecyclerView mRec;
    private SgoodsCollectAdapter adapter;

    private List<SgCollectGoodsBeans.DataBean.ListBean> mList;

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
        adapter = new SgoodsCollectAdapter();
        mRec.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.box){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(mList.get(position).getInfo().getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("collection_code",getFragType());
        Controller.myRequest(Constants.GET_COLLECTION_LIST,Controller.TYPE_POST,getParams(), getMyClass(),this);
    }

    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();

        if(data instanceof SgCollectGoodsBeans){
            List<SgCollectGoodsBeans.DataBean.ListBean> list = ((SgCollectGoodsBeans)data).getData().getList();
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
