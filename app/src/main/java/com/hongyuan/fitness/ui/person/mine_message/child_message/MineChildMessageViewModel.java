package com.hongyuan.fitness.ui.person.mine_message.child_message;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityMineChildMessageBinding;
import com.hongyuan.fitness.util.JumpUtils;

public class MineChildMessageViewModel extends CustomViewModel {

    private ActivityMineChildMessageBinding binding;
    private MineChildMessageAdapter adapter;

    private MineChildMessageBeans mineChildMessageBeans;

    //记录点击的位置
    private int mPosition;

    public MineChildMessageViewModel(CustomActivity mActivity, ActivityMineChildMessageBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        mActivity.setTitleBar(mActivity.TYPE_BAR3,0,getBundle().getString("titleName"));

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new MineChildMessageAdapter();
        binding.mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mPosition = position;
                getRead(String.valueOf(mineChildMessageBeans.getData().getList().get(position).getMsg_id()));
            }
        });
    }

    @Override
    public void refreshData(){
        mineChildMessageBeans = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("msg_category_id",getBundle().getString("msg_category_id"));
        Controller.myRequest(Constants.GET_MSG_LIST,Controller.TYPE_POST,getParams(), MineChildMessageBeans.class,this);
    }

    /*
    * 调取已读接口
    * */
    private void getRead(String msg_id){
        clearParams().setParams("msg_id",msg_id);
        Controller.myRequest(ConstantsCode.UPDATE_MSG_READ,Constants.UPDATE_MSG_READ,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MineChildMessageBeans){
            MineChildMessageBeans pageData = (MineChildMessageBeans)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    mineChildMessageBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    mineChildMessageBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(mineChildMessageBeans != null && mineChildMessageBeans.getData() != null &&
                    mineChildMessageBeans.getData().getList() != null &&
                    mineChildMessageBeans.getData().getList().size() > 0){
                adapter.setNewData(mineChildMessageBeans.getData().getList());
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.UPDATE_MSG_READ){
            mineChildMessageBeans.getData().getList().get(mPosition).setIs_read(1);
            adapter.setNewData(mineChildMessageBeans.getData().getList());

            //跳转
            if(mineChildMessageBeans.getData().getList().get(mPosition).getHref_type() == 1){
                JumpUtils.goAtherPage(this,mineChildMessageBeans.getData().getList().get(mPosition).getHref_code(),
                        String.valueOf(mineChildMessageBeans.getData().getList().get(mPosition).getHref_id()));
            }
        }
    }
}
