package com.hongyuan.fitness.ui.heat.add_food;

import android.os.Bundle;
import android.util.Log;
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
import com.hongyuan.fitness.custom_view.AddFoodView;
import com.hongyuan.fitness.util.CustomDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AddFoodFragment extends CustomFragment {

    private RecyclerView mRecycler;
    private AddFoodAdapter adapter;
    private AddFoodBean addFoodBean;

    private AddFoodView.SelectData selectData;

    //搜索添加筛选
    private String seachText;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_addfood;
    }

    @Override
    public void initView(View mView) {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        mRecycler = mView.findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new AddFoodAdapter();
        mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> CustomDialog.addFood(mActivity,addFoodBean.getData().getList().get(position),selectData));

    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("fc_id",getFragType());
        if(isValue(seachText)){
            setParams("search_name",seachText);
        }
        Controller.myRequest(Constants.GET_FOOD_LIST,Controller.TYPE_POST,getParams(), AddFoodBean.class,this);
    }

    /*
     * 加载更多
     * */
    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    /*
    * 刷新数据
    * */
    @Override
    public void refreshData() {
        lazyLoad();
    }

    /*
     * 搜索
     * */
    @Subscribe(id = ConstantsCode.EB_FOOD_SEARCH)
    public void search(String message) {
        //并且把数据清空
        addFoodBean = null;

        //每次搜索初始化页数为1
        curPage = FIRST_PAGE;
        seachText = message;
        lazyLoad();
    }
    //EventBus.getDefault().postSticky(new MessageEvent(null));

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


    @Override
    public void onSuccess(Object data) {
        if(data instanceof AddFoodBean && isSuccess(data)){
            AddFoodBean pageData = (AddFoodBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    addFoodBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    addFoodBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(addFoodBean != null && addFoodBean.getData() != null &&
                    addFoodBean.getData().getList() != null &&
                    addFoodBean.getData().getList().size() > 0){
                adapter.setNewData(addFoodBean.getData().getList());
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }
    }

    public AddFoodFragment setSelectData(AddFoodView.SelectData selectData) {
        this.selectData = selectData;
        return this;
    }
}
