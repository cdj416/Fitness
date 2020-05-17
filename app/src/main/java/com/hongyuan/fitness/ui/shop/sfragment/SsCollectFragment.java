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
import com.hongyuan.fitness.ui.shop.sactivity.SstoreActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SstoreCollectAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.SsCollectBeans;
import java.util.List;

public class SsCollectFragment extends CustomFragment {

    private RecyclerView mRec;
    private SstoreCollectAdapter adapter;

    private List<SsCollectBeans.DataBean.ListBean> mList;

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
        adapter = new SstoreCollectAdapter();
        mRec.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.img1 && mList.get(position).getInfo().getGoods_list().size() >= 1){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(mList.get(position).getInfo().getGoods_list().get(0).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }
            if(view.getId() == R.id.img2 && mList.get(position).getInfo().getGoods_list().size() >= 2){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(mList.get(position).getInfo().getGoods_list().get(1).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }
            if(view.getId() == R.id.img3 && mList.get(position).getInfo().getGoods_list().size() >= 3){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(mList.get(position).getInfo().getGoods_list().get(2).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }
            if(view.getId() == R.id.goStore){
                Bundle bundle = new Bundle();
                bundle.putString("store_id",String.valueOf(mList.get(position).getInfo().getStore_id()));
                startActivity(SstoreActivity.class,bundle);
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
        mActivity.closeLoading();

        if(data instanceof SsCollectBeans){
            List<SsCollectBeans.DataBean.ListBean> list = ((SsCollectBeans)data).getData().getList();
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
