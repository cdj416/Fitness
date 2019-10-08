package com.hongyuan.fitness.ui.about_class.coach.coach_search;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityV3SearchCoachBinding;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachHomePageActivity;
import com.hongyuan.fitness.ui.about_class.coach.coach_list.CoachListAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home.VtwoStarCoachBean;
import com.hongyuan.fitness.util.BaseUtil;

public class CoachSearchViewModel extends CustomViewModel {

    private ActivityV3SearchCoachBinding binding;

    private ObjectAnimator objectAnimator;

    private VtwoStarCoachBean starCoachBean;
    private CoachListAdapter adapter;

    public CoachSearchViewModel(CustomActivity mActivity, ActivityV3SearchCoachBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        binding.clearText.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                objectAnimator = ObjectAnimator.ofFloat(binding.clearText,"rotation",0f,360f,0f);
                objectAnimator.setDuration(300);
                objectAnimator.start();
                binding.searchContent.setText("");
            }
        });
        binding.finishText.setOnClickListener(v -> mActivity.finish());

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new CoachListAdapter();
        binding.mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("coach_mid",String.valueOf(starCoachBean.getData().getList().get(position).getM_id()));
                startActivity(CoachHomePageActivity.class,bundle);
            }
        });

        binding.searchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(BaseUtil.isValue(s.toString())){
                    getSearchData(s.toString());
                }
            }
        });
    }

    /*
    * 搜索内容
    * */
    private void getSearchData(String searchText){
        starCoachBean = null;
        mActivity.showLoading();
        clearParams().setParams("search_name",searchText);
        Controller.myRequest(Constants.GET_COACH_LIST,Controller.TYPE_POST,getParams(), VtwoStarCoachBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof VtwoStarCoachBean){
            VtwoStarCoachBean pageData = (VtwoStarCoachBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    starCoachBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    starCoachBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(starCoachBean != null && starCoachBean.getData() != null &&
                    starCoachBean.getData().getList() != null &&
                    starCoachBean.getData().getList().size() > 0){
                adapter.setNewData(starCoachBean.getData().getList());
                binding.loadBox.setVisibility(View.GONE);
                binding.mRecycler.setVisibility(View.VISIBLE);
            }else{
                binding.loadBox.setVisibility(View.VISIBLE);
                binding.mRecycler.setVisibility(View.GONE);
            }
        }
        mActivity.closeLoading();
    }
}
