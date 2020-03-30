package com.hongyuan.fitness.ui.main;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMainBinding;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_verification_login.VtwoVerificationLoginActivity;
import com.hongyuan.fitness.ui.mall.good_details.GoodDetailsActivity;
import com.hongyuan.fitness.ui.mall.home_goods.HomeGoodsBeans;
import com.hongyuan.fitness.ui.person.my_coupon.CouponListBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.PackageUtils;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;

public class MainViewModel extends CustomViewModel {
    private ActivityMainBinding binding;
    private MyViewPagerAdapter myViewPagerAdapter;
    private CheckVersionBeans.DataBean.InfoBean versionBeans;
    private BaseQuickAdapter couponAdapter;
    private BaseQuickAdapter goodsAdapter;
    private List<CouponListBeans.DataBean.ListBean> mList;
    private int mPosition;
    //免费领取商品的数据
    private HomeGoodsBeans.DataBean goodsBeans;


    public MainViewModel(CustomActivity mActivity, ActivityMainBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView(){

        NavigationController navigationController = binding.tab.material()
                .addItem(R.mipmap.home_default, R.mipmap.home_select,"首页")
                .addItem(R.mipmap.find_default,R.mipmap.find_select, "发现",0xFFEF5B48)
                .addItem(R.mipmap.course_default_icon_img,R.mipmap.course_default_icon_img, "课程",0xFFEF5B48)
                .addItem(R.mipmap.mall_default,R.mipmap.mall_select,"商城")
                .addItem(R.mipmap.person_default, R.mipmap.person_select,"我的",0xFFEF5B48)
                .build();

        myViewPagerAdapter = new MyViewPagerAdapter(mActivity.getSupportFragmentManager());
        binding.viewPager.setAdapter(myViewPagerAdapter);
        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(getOnPageChangeListener());
        binding.viewPager.setOffscreenPageLimit(5);

        //启动各个平台的推送服务
        HuanXinUtils.getInstance().setPush();

    }

    /*
    * 获取个人中心Fragment对象
    * */
    public Fragment getLastFragment(){
        return myViewPagerAdapter.getLastFragment();
    }


    /*
    * viewPage页面变动监听
    * */
    private ViewPager.OnPageChangeListener getOnPageChangeListener(){
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 4 && userToken.getM_mobile() == null){
                    startActivity(VtwoVerificationLoginActivity.class,null);
                }
                if(position == 3){
                    mActivity.setTitleBar(mActivity.TYPE_BAR8,R.drawable.shape_soid_ffef5b48,"");
                }else{
                    mActivity.setTitleBar(mActivity.TYPE_BAR1,R.drawable.shape_soid_ffffff,"");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    @Override
    protected void lazyLoad() {
        //检测是否需要更新版本
        clearParams().setParams("app_version", PackageUtils.getVersionName(mActivity)).setParams("app_type","1");
        Controller.myRequest(Constants.CHECK_APP_VERSION, Controller.TYPE_POST,getParams(), CheckVersionBeans.class,this);
    }

    /*
    * 去检查是否有可领取的优惠券
    * */
    private void getHomeCoupon(){
        mActivity.showLoading();
        //检查是否有需要领取的优惠券
        clearParams();
        Controller.myRequest(Constants.INDEX_COUPON_LIST, Controller.TYPE_POST,getParams(), CouponListBeans.class,this);
    }

    /*
    * 去检查是否有可免费领取的商品
    * */
    private void getHomeGoods(){
        Log.e("cnn","===========来了几次啊=========");
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_FREE_GODDS, Controller.TYPE_POST,getParams(), HomeGoodsBeans.class,this);
    }

    /*
    * 领取优惠券
    * */
    private void getReceive(String couponId){
        mActivity.showLoading();
        clearParams().setParams("coupon_id",couponId);
        Controller.myRequest(ConstantsCode.GET_COUPON,Constants.GET_COUPON,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof CheckVersionBeans){
            versionBeans = ((CheckVersionBeans)data).getData().getInfo();

            if(BaseUtil.isValue(versionBeans)&&versionBeans.getIs_new() == 1){
                //版本检测更新
                binding.versionView.startChange(versionBeans);
            }else{
                //获取优惠券弹框
                getHomeCoupon();
                //获取首页商品弹框
                getHomeGoods();
            }

        }

        if(data instanceof CouponListBeans){
            mList = ((CouponListBeans)data).getData().getList();

            if(mList != null && mList.size() > 0){
                CustomDialog.receiveCoupon(mActivity, mList, (v, position, adapter) -> {
                     couponAdapter = adapter;
                     mPosition = position;
                    getReceive(String.valueOf(mList.get(position).getCoupon_id()));
                });
            }

        }

        if(data instanceof HomeGoodsBeans){
            goodsBeans = ((HomeGoodsBeans)data).getData();
            if(goodsBeans != null && goodsBeans.getList() != null && goodsBeans.getList().size() > 0){
                //谈出领取免费商品弹框
                CustomDialog.receiveGoods(mActivity, goodsBeans.getList(), (v, position, adapter) -> {
                    goodsAdapter = adapter;
                    Bundle bundle = new Bundle();
                    bundle.putString("g_id",String.valueOf(goodsBeans.getList().get(position).getG_id()));
                    mActivity.startActivity(GoodDetailsActivity.class,bundle);
                });
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();
        if(code == ConstantsCode.GET_COUPON){
            mList.get(mPosition).setReceive(true);
            couponAdapter.notifyDataSetChanged();
            showSuccess("领取成功！");
        }
    }

    /*
    * 刷新免费领取商品的显示数据
    * */
    public void changeGoods(int g_id){
        for(HomeGoodsBeans.DataBean.ListBean bean : goodsBeans.getList()){
            if(g_id == bean.getG_id()){
                bean.setReceive(true);
            }
        }

        if(goodsAdapter != null){
            goodsAdapter.notifyDataSetChanged();
        }
    }
}
