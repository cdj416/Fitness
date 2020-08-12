package com.hongyuan.fitness.ui.store.store_page_list;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityStoreBinding;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_verification_login.VtwoVerificationLoginActivity;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.store.StoreDetailActivity;
import com.hongyuan.fitness.ui.store.more_store.StoreBean;
import com.hongyuan.fitness.ui.webview.WebViewActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.LocationBean;

public class StoreViewModel extends CustomViewModel {

    private ActivityStoreBinding binding;
    private StoreAdapter adapter;
    private StoreBean bean;

    private String os_name;

    public StoreViewModel(CustomActivity mActivity, ActivityStoreBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        LinearLayoutManager menuManager = new LinearLayoutManager(mActivity);
        menuManager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(menuManager);
        adapter = new StoreAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooter16Height(binding.mRecycler));
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(bean.getData().getList().get(position).getOs_type() == 1){
                    Bundle bundle = new Bundle();
                    bundle.putString("os_id",String.valueOf(bean.getData().getList().get(position).getOs_id()));
                    startActivity(StoreDetailActivity.class,bundle);
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putString("url", Constants.WEB_ADDRESS+"/venues_details"+ TokenSingleBean.getInstance().getWebAllParams("")+"&os_id="+bean.getData().getList().get(position).getOs_id());
                    bundle.putString("title","场馆详情");
                    if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                        mActivity.startActivity(WebViewActivity.class,bundle);
                    }else{
                        mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
                    }
                }

            }
        });

        binding.cancelText.setOnClickListener(v -> {
            binding.searchText.setText("");
            binding.cancelText.setVisibility(View.GONE);
            lazyLoad();
        });


        binding.searchText.setOnEditorActionListener((textView, i, keyEvent) -> {
            switch (i) {
                case EditorInfo.IME_ACTION_SEARCH:
                    bean = null;
                    lazyLoad();
                    break;
            }
            return false;
        });

        binding.searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               os_name = s.toString();
               binding.cancelText.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void refreshData(){
        bean = null;
        lazyLoad();
    }
    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("lat", LocationBean.getInstance().getLat())
                .setParams("lng",LocationBean.getInstance().getLng());

        if(BaseUtil.isValue(os_name)){
            setParams("os_name",os_name);
        }
        Controller.myRequest(Constants.GET_OFFLINE_STORE_LIST_JULI,Controller.TYPE_POST,getParams(), StoreBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof StoreBean && isSuccess(data)){
            StoreBean pageData = (StoreBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    bean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    bean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(bean != null && bean.getData() != null &&
                    bean.getData().getList() != null &&
                    bean.getData().getList().size() > 0){
                adapter.setNewData(bean.getData().getList());
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }
    }
}
