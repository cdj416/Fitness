package com.hongyuan.fitness.ui.shop.sfragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sadapter.SMGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SNMenuAdapter;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class ShopNextFragment extends CustomFragment {

    private RecyclerView menuRec,mRec;
    private Banner sBanner;

    private SNMenuAdapter menuAdapter;
    private SMGoodsAdapter gAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_next;
    }

    @Override
    public void initView(View mView) {
        menuRec = mView.findViewById(R.id.menuRec);
        mRec = mView.findViewById(R.id.mRec);
        sBanner = mView.findViewById(R.id.sBanner);

        GridLayoutManager menuManager =
                new GridLayoutManager(mActivity,5);
        menuRec.setLayoutManager(menuManager);
        menuAdapter = new SNMenuAdapter();
        menuRec.setAdapter(menuAdapter);
        menuAdapter.setNewData(getList());

        GridLayoutManager layoutManager =
                new GridLayoutManager(mActivity,2);
        mRec.setLayoutManager(layoutManager);
        gAdapter = new SMGoodsAdapter();
        mRec.setAdapter(gAdapter);
        gAdapter.setNewData(getList());

        setSBanner(getBannerList());

    }

    /*
     * 设置顶部banner
     * */
    private void setSBanner(List<Integer> bannerList){
        sBanner.setImages(bannerList)
                .setImageLoader(new UseGlideImageLoader())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                .setIndicatorGravity(BannerConfig.CENTER).setOnBannerListener(position -> {

            /*JumpUtils.JumpBeans jumpBeans = new JumpUtils.JumpBeans();
            jumpBeans.setImg_href_type(bannerList.get(position).getImg_href_type());
            jumpBeans.setHref_code(bannerList.get(position).getImg_href_code());
            jumpBeans.setHref_id(String.valueOf(bannerList.get(position).getImg_href_id()));

            JumpUtils.goAtherPage(mActivity,jumpBeans);*/
        }).start();
    }

    /*
     * 获取banner本地数据
     * */
    private List<Integer> getBannerList(){
        List<Integer> bList = new ArrayList<>();
        bList.add(R.drawable.banner_test1);
        bList.add(R.drawable.banner_test2);
        bList.add(R.drawable.banner_test3);
        bList.add(R.drawable.banner_test4);
        return bList;
    }

    /*
     * 组装假数据
     * */
    private List<BaseBean> getList(){
        List<BaseBean> mList = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            mList.add(new BaseBean());
        }
        return mList;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
