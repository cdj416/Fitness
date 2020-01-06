package com.hongyuan.fitness.ui.look_img;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;

public class MyLookImgFragment extends CustomFragment {

    private ImageView showImg;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_look_img;
    }

    @Override
    public void initView(View mView) {

        showImg = mView.findViewById(R.id.showImg);

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mActivity).load(getFragType()).apply(options).into(showImg);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
