package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;

import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivitySearchFindBinding;
import com.hongyuan.fitness.ui.shop.sviewpage.SearchFindViewPagerAdapter;

import org.greenrobot.eventbus.EventBus;

public class SearchFindViewModel extends CustomViewModel {

    private ActivitySearchFindBinding binding;

    private String searchText = "";

    public SearchFindViewModel(CustomActivity mActivity,ActivitySearchFindBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
    }

    @Override
    protected void initView() {
        SearchFindViewPagerAdapter meunAdapter = new SearchFindViewPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setOffscreenPageLimit(3);

        binding.searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchText = s.toString();
            }
        });

        binding.searchText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                EventBus.getDefault().post(ConstantsCode.EB_FIND_SEARCH,searchText);
                return true;
            }
            return false;
        });

        binding.cancelText.setOnClickListener(v -> mActivity.finish());
    }

    @Override
    public void onSuccess(Object data) {

    }
}
