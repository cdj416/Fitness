package com.hongyuan.fitness.ui.carousel;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.advertising.AdvertisingPageActivity;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeBannerBean;
import com.hongyuan.fitness.ui.startup_page.FistUseBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.SharedPreferencesUtil;

public class CarouseFragment extends CustomFragment {

    private ImageView showImg;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_carouse;
    }

    @Override
    public void initView(View mView) {
        //广告实体类
        HomeBannerBean.DataBean.ListBean advertImgBean = (HomeBannerBean.DataBean.ListBean) getArguments().getSerializable("advertImgBean");

        showImg = mView.findViewById(R.id.showImg);

        if(BaseUtil.isValue(getFragType("imgUrl"))){
            //显示网络图片
            RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.banner_one).error(R.mipmap.banner_one);
            Glide.with(mActivity).load(getFragType("imgUrl")).apply(options).into(showImg);
        }else{
            showImg.setImageResource(getArguments().getInt("imgId"));
        }



        if(getBoolenFrag("last")){
            showImg.setOnClickListener(v -> {
                FistUseBean fistUseBean = new FistUseBean();
                fistUseBean.setFirst(true);
                //标识已经显示过引导页
                SharedPreferencesUtil.putBean(mActivity,"firstUse",fistUseBean);

                if(advertImgBean != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("advertImgBean",advertImgBean);
                    startActivity(AdvertisingPageActivity.class,bundle);
                }else{
                    startActivity(MainActivity.class,null);
                }
                mActivity.finish();

            });
        }
    }


    @Override
    public void onSuccess(Object data) {

    }

}
