package com.hongyuan.fitness.ui.shop.sfragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sadapter.SGDCommentsAdapter;
import com.hongyuan.fitness.util.DividerItemDecoration;

public class SshopCommentsFragment extends CustomFragment {

    private RecyclerView mRec;
    private SGDCommentsAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sgd_comments;
    }

    @Override
    public void initView(View mView) {
        mRec = mView.findViewById(R.id.mRec);

        //评论的适配
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRec.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.HORIZONTAL_LIST,1,0xffF1F1F1));
        mRec.setLayoutManager(manager);
        adapter = new SGDCommentsAdapter();
        mRec.setAdapter(adapter);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
