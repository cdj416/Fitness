package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopSearchActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SMGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SNMenuAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.FirstCategoryBeans;
import com.hongyuan.fitness.ui.shop.sbeans.GoodsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.ShopNextCetegoryBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopNextFragment extends CustomFragment {

    private FirstCategoryBeans.DataBean.ListBean item;

    private RecyclerView menuRec,mRec;
    private Banner sBanner;
    private RelativeLayout childEmpty;

    private SNMenuAdapter menuAdapter;
    private SMGoodsAdapter gAdapter;

    //子分类数据
    private List<ShopNextCetegoryBeans.DataBean> menuList;

    //商品数据
    private List<GoodsBeans.DataBean.ListBean> mList;

    //选择的分类id
    private String third_category_id;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_next;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        item = (FirstCategoryBeans.DataBean.ListBean) getSerializableBeans("item");

        menuRec = mView.findViewById(R.id.menuRec);
        mRec = mView.findViewById(R.id.mRec);
        sBanner = mView.findViewById(R.id.sBanner);
        childEmpty = mView.findViewById(R.id.childEmpty);

        GridLayoutManager menuManager =
                new GridLayoutManager(mActivity,5);
        menuRec.setLayoutManager(menuManager);
        menuAdapter = new SNMenuAdapter();
        menuRec.setAdapter(menuAdapter);
        menuAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            for(ShopNextCetegoryBeans.DataBean menu : menuList){
                menu.setSelect(false);
            }
            menuList.get(position).setSelect(true);
            menuAdapter.notifyDataSetChanged();

            Bundle bundle = new Bundle();
            bundle.putString("first_category_id",String.valueOf(item.getCategory_id()));
            bundle.putString("third_category_id",String.valueOf(menuList.get(position).getCategory_id()));
            bundle.putString("showText",menuList.get(position).getCategory_name());
            startActivity(ShopSearchActivity.class,bundle);
        });

        GridLayoutManager layoutManager =
                new GridLayoutManager(mActivity,2);
        mRec.setLayoutManager(layoutManager);
        gAdapter = new SMGoodsAdapter<GoodsBeans.DataBean.ListBean>() {
            @Override
            public String getImg(GoodsBeans.DataBean.ListBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(GoodsBeans.DataBean.ListBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(GoodsBeans.DataBean.ListBean item) {
                return item.getG_price();
            }
        };
        mRec.setAdapter(gAdapter);
        gAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(mList.get(position).getG_id()));
            startActivity(SgoodsDetailActivity.class,bundle);
        });

        //设置banner的显示
        setSBanner();

    }

    /*
     * 设置顶部banner
     * */
    private void setSBanner(){
        List<String> bList = new ArrayList<>();
        if(BaseUtil.isValue(item.getBanner_img())){
            bList = Arrays.asList(item.getBanner_img().split(","));
            sBanner.setImages(bList)
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
        }else{
            sBanner.setVisibility(View.GONE);
        }
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("first_id",String.valueOf(item.getCategory_id()));
        Controller.myRequest(Constants.GET_THIRD_CATEGORY_BY_FIRST_ID,Controller.TYPE_POST,getParams(), ShopNextCetegoryBeans.class,this);

        third_category_id = "";
        getData();
    }

    /*
     * 加载更多
     * */
    @Override
    public void loadMoreData() {
        getData();
    }

    /*
    * 获取商品列表
    * */
    private void getData(){
        mActivity.showLoading();
        clearParams().setParams("first_category_id",String.valueOf(item.getCategory_id()));
        if(BaseUtil.isValue(third_category_id)){
            setParams("third_category_id",third_category_id);
        }
        Controller.myRequest(Constants.GET_GOODS_LIST_SIX,Controller.TYPE_POST,getParams(), GoodsBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof ShopNextCetegoryBeans){
            menuList = ((ShopNextCetegoryBeans)data).getData();
            menuAdapter.setNewData(menuList);
        }
        if(data instanceof GoodsBeans){
            List<GoodsBeans.DataBean.ListBean> list = ((GoodsBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                gAdapter.setNewData(mList);
                childEmpty.setVisibility(View.GONE);
                mRec.setVisibility(View.VISIBLE);
            }else{
                childEmpty.setVisibility(View.VISIBLE);
                mRec.setVisibility(View.GONE);
            }
        }
    }
}
