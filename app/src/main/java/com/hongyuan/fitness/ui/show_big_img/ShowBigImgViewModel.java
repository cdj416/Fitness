package com.hongyuan.fitness.ui.show_big_img;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityShowBigImgBinding;

public class ShowBigImgViewModel extends CustomViewModel {

    private ActivityShowBigImgBinding binding;

    private int imgId;
    private String title;

    public ShowBigImgViewModel(CustomActivity mActivity, ActivityShowBigImgBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        imgId = getBundle().getInt("img");
        title = getBundle().getString("title");

        mActivity.getMainTitle().setCentreText(title);
        binding.ivBig.setImageResource(imgId);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
