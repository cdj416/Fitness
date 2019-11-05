package com.hongyuan.fitness.ui.carousel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.startup_page.FistUseBean;
import com.hongyuan.fitness.util.SharedPreferencesUtil;

public class CarouseFragment extends CustomFragment {

    private ImageView showImg;
    private TextView goMain;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_carouse;
    }

    @Override
    public void initView(View mView) {
        showImg = mView.findViewById(R.id.showImg);
        goMain = mView.findViewById(R.id.goMain);

        if("1".equals(getFragType())){
            showImg.setImageResource(R.mipmap.banner_one);
        }
        if("2".equals(getFragType())){
            showImg.setImageResource(R.mipmap.banner_two);
        }
        if("3".equals(getFragType())){
            showImg.setImageResource(R.mipmap.banner_third);
            goMain.setOnClickListener(new View.OnClickListener() {
                @SingleClick
                @Override
                public void onClick(View v) {
                    FistUseBean fistUseBean = new FistUseBean();
                    fistUseBean.setFirst(true);
                    //标识已经显示过引导页
                    SharedPreferencesUtil.putBean(mActivity,"firstUse",fistUseBean);
                    startActivity(MainActivity.class,null);
                    mActivity.finish();
                }
            });
        }
    }


    @Override
    public void onSuccess(Object data) {

    }

}
