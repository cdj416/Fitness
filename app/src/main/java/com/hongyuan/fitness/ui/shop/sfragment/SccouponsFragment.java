package com.hongyuan.fitness.ui.shop.sfragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sadapter.SccouponsAdapter;
import java.util.ArrayList;
import java.util.List;

public class SccouponsFragment extends CustomFragment {

    private RecyclerView mRec;
    private SccouponsAdapter adapter;

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
        adapter = new SccouponsAdapter();
        mRec.setAdapter(adapter);
        adapter.setNewData(getList());
        adapter.setFooterView(mActivity.getFooterHeight(mRec));

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            //startActivity(SstoreActivity.class,null);
        });

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
