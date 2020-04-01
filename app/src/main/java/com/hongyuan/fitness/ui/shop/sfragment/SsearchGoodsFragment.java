package com.hongyuan.fitness.ui.shop.sfragment;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sadapter.SsearchGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sinterface.SearchOCDrawerListener;

import java.util.ArrayList;
import java.util.List;

public class SsearchGoodsFragment extends CustomFragment {

    private RecyclerView mRec;
    private SsearchGoodsAdapter adapter;

    //头部控件
    private LinearLayout openFilter;

    //筛选栏的打开操作
    private SearchOCDrawerListener drawerListener;

    public SsearchGoodsFragment(SearchOCDrawerListener drawerListener){
        this.drawerListener = drawerListener;
    }

    @Override
    protected int getHeadLayoutId() {
        return R.layout.fragment_headview_searchgoods;
    }

    @Override
    protected void initHeadView(View headLayt) {
        openFilter = headLayt.findViewById(R.id.openFilter);

        openFilter.setOnClickListener(v -> drawerListener.openOrClose(true));
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
        adapter = new SsearchGoodsAdapter();
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
