package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.CustomRecyclerView;
import com.hongyuan.fitness.ui.shop.sactivity.SstoreActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SDMimgAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SGDcommentAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SGDgoodsAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.ShopMainGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.ScouponsBean;
import com.hongyuan.fitness.ui.shop.sbeans.SgoodsDeailStoreBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SgoodsDetailBeans;
import com.hongyuan.fitness.ui.shop.sbeans.ShopCommentBeans;
import com.hongyuan.fitness.ui.shop.sinterface.GoOtherPageListener;
import com.hongyuan.fitness.ui.shop.sinterface.GoodsDetailIntener;
import com.hongyuan.fitness.ui.shop.sinterface.ScollChangeLinstener;
import com.hongyuan.fitness.ui.shop.smyview.SGoodsDetailsHeadView;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DensityUtil;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.makeramen.roundedimageview.RoundedImageView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import java.util.Arrays;
import java.util.List;

public class SshopDetailMainFragment extends CustomFragment {

    private Banner banner;
    private RecyclerView commentRec,goodsRec,detailsImg;
    private CustomRecyclerView sgRec1;
    private TextView goComments,shopName,collectionNum,collectStore,goStore,userName,cComtent;
    private SGoodsDetailsHeadView goodHeads;
    private RoundedImageView shopImg,cheadImg;
    private RatingBar myRat;
    private AppBarLayout topBar;
    private LinearLayout commentBox;

    private SGDcommentAdapter sgDcommentAdapter;
    private SGDgoodsAdapter sgDgoodsAdapter;
    private SDMimgAdapter imgAdapter;
    private ShopMainGoodsAdapter stroeAdapter;

    private GoOtherPageListener pageListener;
    private GoodsDetailIntener returnListener;
    private ScollChangeLinstener changeLinstener;

    //商品详情数据
    private SgoodsDetailBeans.DataBean.InfoBean infoBean;
    //红包数据
    private List<ScouponsBean.DataBean> couponList;
    //推荐的店铺相关数据
    private SgoodsDeailStoreBeans.DataBean storeBean;

    //商品id
    private String g_id;

    public SshopDetailMainFragment(GoOtherPageListener pageListener, GoodsDetailIntener returnListener, ScollChangeLinstener changeLinstener){
        this.pageListener = pageListener;
        this.returnListener = returnListener;
        this.changeLinstener = changeLinstener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sgd_goods_details;
    }

    @Override
    public void initView(View mView) {
        g_id = mActivity.getBundle().getString("g_id");

        banner = mView.findViewById(R.id.banner);
        //commentRec = mView.findViewById(R.id.commentRec);
        goodsRec = mView.findViewById(R.id.goodsRec);
        detailsImg = mView.findViewById(R.id.detailsImg);
        goComments = mView.findViewById(R.id.goComments);
        goodHeads = mView.findViewById(R.id.goodHeads);
        sgRec1 = mView.findViewById(R.id.sgRec1);
        shopImg = mView.findViewById(R.id.shopImg);
        shopName = mView.findViewById(R.id.shopName);
        myRat = mView.findViewById(R.id.myRat);
        collectionNum = mView.findViewById(R.id.collectionNum);
        collectStore = mView.findViewById(R.id.collectStore);
        goStore = mView.findViewById(R.id.goStore);
        cheadImg = mView.findViewById(R.id.cheadImg);
        userName = mView.findViewById(R.id.userName);
        cComtent = mView.findViewById(R.id.cComtent);
        topBar = mView.findViewById(R.id.topBar);
        commentBox = mView.findViewById(R.id.commentBox);

        /*LinearLayoutManager comManager = new LinearLayoutManager(mActivity);
        comManager.setOrientation(RecyclerView.VERTICAL);
        commentRec.setLayoutManager(comManager);
        sgDcommentAdapter = new SGDcommentAdapter();
        commentRec.setAdapter(sgDcommentAdapter);
        List<BaseBean> ll = new ArrayList<>();
        ll.add(new BaseBean());
        sgDcommentAdapter.setNewData(ll);*/

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 3);
        goodsRec.setLayoutManager(layoutManager);
        sgDgoodsAdapter = new SGDgoodsAdapter();
        goodsRec.setAdapter(sgDgoodsAdapter);

        LinearLayoutManager imgManager = new LinearLayoutManager(mActivity);
        imgManager.setOrientation(RecyclerView.VERTICAL);
        detailsImg.setLayoutManager(imgManager);
        imgAdapter = new SDMimgAdapter(DensityUtil.getScreensWith(mActivity));
        detailsImg.setAdapter(imgAdapter);


        LinearLayoutManager manager4 = new LinearLayoutManager(getContext());
        manager4.setOrientation(LinearLayoutManager.HORIZONTAL);
        sgRec1.setLayoutManager(manager4);
        stroeAdapter = new ShopMainGoodsAdapter<SgoodsDeailStoreBeans.DataBean.GoodsListBean>() {
            @Override
            public String getImg(SgoodsDeailStoreBeans.DataBean.GoodsListBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(SgoodsDeailStoreBeans.DataBean.GoodsListBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(SgoodsDeailStoreBeans.DataBean.GoodsListBean item) {
                return "";
            }

            @Override
            public boolean isStore() {
                return true;
            }
        };
        sgRec1.setAdapter(stroeAdapter);


        goComments.setOnClickListener(v -> {
            pageListener.goPage(2);
        });
        //收藏店铺
        collectStore.setOnClickListener(v -> {
            addCollection(String.valueOf(storeBean.getStore_id()));
        });

        goStore.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("store_id",String.valueOf(storeBean.getStore_id()));
            startActivity(SstoreActivity.class,bundle);
        });

        //滑动监听
        topBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            changeLinstener.onOffsetChanged(appBarLayout,verticalOffset);
        });

    }

    /*
    * 打开规格弹框
    * */
    public void showGG(){
        CustomDialog.showGoodsSpecification(getContext(),infoBean,this,null);
    }

    /*
     * 设置顶部banner
     * */
    private void setTopBanner(List<String> bannerList){
        banner.setImages(bannerList)
                .setImageLoader(new UseGlideImageLoader())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                .setIndicatorGravity(BannerConfig.CENTER).setOnBannerListener(position -> {

        }).start();
    }

    /*
     * 收藏商品
     * */
    private void addCollection(String id){
        mActivity.showLoading();
        clearParams().setParams("collection_code","store");
        if(storeBean.getIs_collection() == 1){
            setParams("out_id",id);
            Controller.myRequest(ConstantsCode.DEL_COLLECTION,Constants.DEL_COLLECTION,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }else{
            setParams("id",id);
            Controller.myRequest(ConstantsCode.ADD_COLLECTION,Constants.ADD_COLLECTION,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }

    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("g_id",g_id);
        Controller.myRequest(Constants.GET_GOODS_DETAIL_SIX,Controller.TYPE_POST,getParams(), SgoodsDetailBeans.class,this);

        //获取红包列表数据
        clearParams().setParams("g_id",g_id);
        Controller.myRequest(Constants.GET_GOODS_COUPON_LIST,Controller.TYPE_POST,getParams(), ScouponsBean.class,this);

        //商品详情的店铺信息/推荐商品
        clearParams().setParams("g_id",g_id);
        Controller.myRequest(Constants.GET_GOODS_STORE_INFO,Controller.TYPE_POST,getParams(), SgoodsDeailStoreBeans.class,this);

        //获取商品评论
        clearParams().setParams("g_id",g_id);
        Controller.myRequest(Constants.GET_GOODS_EVALUATION,Controller.TYPE_POST,getParams(), ShopCommentBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof SgoodsDetailBeans){
            infoBean = ((SgoodsDetailBeans)data).getData().getInfo();

            //设置banner数据
            setTopBanner(infoBean.getImgs());
            //设置头部数据
            goodHeads.showData(infoBean,this);

            //详情图片集
            if(BaseUtil.isValue(infoBean.getG_desc()) && !infoBean.getG_desc().contains("</p>")){
                String[] imgAry = infoBean.getG_desc().split(",");
                List<String> imgList = Arrays.asList(imgAry);
                imgAdapter.setNewData(imgList);
            }

            if(returnListener != null){
                returnListener.returenData(infoBean);
            }

        }

        if(data instanceof ScouponsBean){
            couponList = ((ScouponsBean)data).getData();
            goodHeads.showCoupons(couponList);
        }

        if(data instanceof SgoodsDeailStoreBeans){
            storeBean = ((SgoodsDeailStoreBeans)data).getData();

            RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
            Glide.with(mActivity).load(storeBean.getStore_logo()).apply(options).into(shopImg);
            shopName.setText(storeBean.getStore_name());
            collectionNum.setText(storeBean.getAll_collection()+"人收藏");
            collectStore.setText(storeBean.getIs_collection() == 1 ? "取消" : "收藏");
            //myRat.setRating(storeBean.get);

            stroeAdapter.setNewData(storeBean.getGoods_list());
            sgDgoodsAdapter.setNewData(storeBean.getTj_goods_list());
        }

        if(data instanceof ShopCommentBeans){
            ShopCommentBeans.DataBean comentBeans = ((ShopCommentBeans)data).getData();
            if(comentBeans.getList() != null && comentBeans.getList().size() > 0){
                commentBox.setVisibility(View.VISIBLE);
                RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
                Glide.with(mActivity).load(comentBeans.getList().get(0).getMi_head()).apply(options).into(cheadImg);

                userName.setText(comentBeans.getList().get(0).getM_name());
                cComtent.setText(comentBeans.getList().get(0).getEvaluation_content());
            }else{
                commentBox.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.ADD_COLLECTION){
            showSuccess("收藏成功！");
            collectStore.setText("取消");
            storeBean.setIs_collection(1);
        }
        if(code == ConstantsCode.DEL_COLLECTION){
            showSuccess("已取消收藏！");
            collectStore.setText("收藏");
            storeBean.setIs_collection(0);
        }
    }
}
