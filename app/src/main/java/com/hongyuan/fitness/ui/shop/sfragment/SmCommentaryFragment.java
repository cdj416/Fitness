package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.MsgDetailsActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SmCommentaryAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.SmCommentaryBeans;
import java.util.List;

public class SmCommentaryFragment extends CustomFragment {

    private RecyclerView mRec;
    private SmCommentaryAdapter adapter;

    //数据集
    private List<SmCommentaryBeans.DataBean.ListBean> mList;

    //系统消息点击的下标
    private int mPosition;

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
        adapter = new SmCommentaryAdapter();
        mRec.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            this.mPosition = position;
            setReadMsg(String.valueOf(mList.get(position).getMsg_id()));
        });

    }

    //标记已读信息
    public void setReadMsg(String msg_id){
        mActivity.showLoading();

        clearParams().setParams("msg_id",msg_id);
        Controller.myRequest(ConstantsCode.UPDATE_MSG_READ,Constants.UPDATE_MSG_READ,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_REVIEW_MSG_LIST,Controller.TYPE_POST,getParams(), SmCommentaryBeans.class,this);
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

        if(data instanceof SmCommentaryBeans){

            List<SmCommentaryBeans.DataBean.ListBean> list = ((SmCommentaryBeans)data).getData().getList();
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

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();

        if(code == ConstantsCode.UPDATE_MSG_READ){
            //设置为已读信息
            mList.get(mPosition).setIs_read(1);
            adapter.notifyDataSetChanged();

            Bundle bundle = new Bundle();
            bundle.putString("circle_id",String.valueOf(mList.get(mPosition).getCircle_id()));
            startActivity(PostDetailsActivity.class,bundle);
        }
    }
}
