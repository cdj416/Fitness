package com.hongyuan.fitness.ui.shop.sfragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sadapter.SsearchShopAdapter;

import java.util.ArrayList;
import java.util.List;

public class SsearchShopFragment extends CustomFragment {

    private RecyclerView mRec;
    private SsearchShopAdapter adapter;

    @Override
    protected int getHeadLayoutId() {
        return R.layout.fragment_headview_searchshops;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_recylerview;
    }

    @Override
    public void initView(View mView) {
        mRec = mView.findViewById(R.id.mRec);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRec.setLayoutManager(manager);
        adapter = new SsearchShopAdapter();
        mRec.setAdapter(adapter);
        adapter.setNewData(getList());

    }

    /*
    * 获取假数据
    * */
    private List<BaseBean> getList(){
        List<BaseBean> mList = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            BaseBean baseBean = new BaseBean();
            mList.add(baseBean);
        }

        return mList;

    }

    @Override
    public void onSuccess(Object data) {

    }
}
