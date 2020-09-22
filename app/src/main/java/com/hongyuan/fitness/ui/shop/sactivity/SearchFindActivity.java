package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySearchFindBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.SearchFindViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class SearchFindActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_find;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR1,R.drawable.theme_shape_soid_ffffff,"");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR8,R.drawable.theme_shape_soid_ffffff_black,"");

        ActivitySearchFindBinding binding = ActivitySearchFindBinding.bind(mView);
        SearchFindViewModel viewModel = new SearchFindViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
