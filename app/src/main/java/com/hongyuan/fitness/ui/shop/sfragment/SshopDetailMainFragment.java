package com.hongyuan.fitness.ui.shop.sfragment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sadapter.SGDcommentAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SGDgoodsAdapter;
import com.hongyuan.fitness.ui.shop.sinterface.GoOtherPageListener;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class SshopDetailMainFragment extends CustomFragment {

    private Banner banner;
    private RecyclerView commentRec,goodsRec;
    private TextView goComments;

    private SGDcommentAdapter sgDcommentAdapter;
    private SGDgoodsAdapter sgDgoodsAdapter;

    private GoOtherPageListener pageListener;

    public SshopDetailMainFragment(GoOtherPageListener pageListener){
        this.pageListener = pageListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sgd_goods_details;
    }

    @Override
    public void initView(View mView) {
        banner = mView.findViewById(R.id.banner);
        commentRec = mView.findViewById(R.id.commentRec);
        goodsRec = mView.findViewById(R.id.goodsRec);
        goComments = mView.findViewById(R.id.goComments);

        LinearLayoutManager comManager = new LinearLayoutManager(mActivity);
        comManager.setOrientation(RecyclerView.VERTICAL);
        commentRec.setLayoutManager(comManager);
        sgDcommentAdapter = new SGDcommentAdapter();
        commentRec.setAdapter(sgDcommentAdapter);
        List<BaseBean> ll = new ArrayList<>();
        ll.add(new BaseBean());
        sgDcommentAdapter.setNewData(ll);

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 3);
        goodsRec.setLayoutManager(layoutManager);
        sgDgoodsAdapter = new SGDgoodsAdapter();
        goodsRec.setAdapter(sgDgoodsAdapter);
        sgDgoodsAdapter.setNewData(getList());

        goComments.setOnClickListener(v -> {
            pageListener.goPage(2);
        });

        setTopBanner(getBannerList());
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
     * 设置顶部banner
     * */
    private void setTopBanner(List<Integer> bannerList){
        banner.setImages(bannerList)
                .setImageLoader(new UseGlideImageLoader())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                .setIndicatorGravity(BannerConfig.CENTER).setOnBannerListener(position -> {

        }).start();
    }

    /*
     * 添加假数据
     * */
    private List<BaseBean> getList(){
        List<BaseBean> mList = new ArrayList<>();
        for(int i = 0 ; i < 6 ; i++){
            BaseBean bean = new BaseBean();
            mList.add(bean);
        }
        return mList;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
