package com.hongyuan.fitness.ui.main.main_find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.CustomRecyclerView;
import com.hongyuan.fitness.ui.find.circle.circle_detail.CircleDetailsActivity;
import com.hongyuan.fitness.ui.find.circle.edit_post.EditPostActivity;
import com.hongyuan.fitness.ui.find.more_topic.MoreTopicActivity;
import com.hongyuan.fitness.ui.find.topic.SlectTopicLeftBeans;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_verification_login.VtwoVerificationLoginActivity;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.main.main_find.featured.FindTopicAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeBannerBean;
import com.hongyuan.fitness.ui.webview.WebViewActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.JumpUtils;
import com.hongyuan.fitness.util.SkinConstants;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends CustomFragment {

    private TabLayout layoutMenu;
    private ViewPager mViewPager;
    //private TextView moreTopic;
    //private CustomRecyclerView topRecycler;
    private ImageView goYue,goPei,goSai;

    private FindTopicAdapter topicAdapter;
    private List<SlectTopicLeftBeans.DataBean.ListBean> topicList;

    private FindViewPagerAdapter meunAdapter;

    private Banner banner;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView(View mView) {

        getmTitle().getRightImgView().setOnClickListener(v -> {
            startActivity(EditPostActivity.class,null);
        });

        banner = mView.findViewById(R.id.banner);

        layoutMenu = mView.findViewById(R.id.layoutMenu);
        mViewPager = mView.findViewById(R.id.mViewPager);
        //moreTopic = mView.findViewById(R.id.moreTopic);
        goYue = mView.findViewById(R.id.goYue);
        goPei = mView.findViewById(R.id.goPei);
        goSai = mView.findViewById(R.id.goSai);

        /*topRecycler = mView.findViewById(R.id.topRecycler);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topRecycler.setLayoutManager(manager);
        topicAdapter = new FindTopicAdapter();
        topRecycler.setAdapter(topicAdapter);
        topicAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("circle_categoryid",String.valueOf(topicList.get(position).getCategory_id()));
            startActivity(CircleDetailsActivity.class,bundle);
        });
        moreTopic.setOnClickListener(v -> startActivity(MoreTopicActivity.class,null));*/

        goYue.setOnClickListener(v -> {
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.WEB_ADDRESS+ "/Asportslist"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","约运动列表");
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }

        });
        goPei.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", Constants.WEB_ADDRESS+"/"+TokenSingleBean.getInstance().getWebAllParams(""));
            bundle.putString("title","场馆");
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }

        });
        goSai.setOnClickListener(v -> {
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.WEB_ADDRESS+"/event"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","赛事");
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }

        });


        meunAdapter = new FindViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(meunAdapter);
        layoutMenu.setupWithViewPager(mViewPager);

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(0);

        mViewPager.addOnPageChangeListener(getOnPageChangeListener());
    }

    @Override
    public void onResume() {
        super.onResume();

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(mActivity.skin)) {
            getmTitle().getBgView().setBackgroundColor(mActivity.getResources().getColor(R.color.theme_color1));
            getmTitle().setRightImage(R.mipmap.theme_carme_mark)
                    .setCenterTextColor("发现",mActivity.getResources().getColor(R.color.theme_color3))
                    .showLine();
            layoutMenu.setTabTextColors(mActivity.getResources().getColor(R.color.color_FF333333),mActivity.getResources().getColor(R.color.color_EF5B48));
        }else if(SkinConstants.SKIN_NAME.BLACK.equals(mActivity.skin)){
            getmTitle().getBgView().setBackgroundColor(mActivity.getResources().getColor(R.color.theme_color1_black));
            getmTitle().setRightImage(R.mipmap.theme_carme_mark_black)
                    .setCenterTextColor("发现",mActivity.getResources().getColor(R.color.theme_color3_black))
                    .showLine();
            layoutMenu.setTabTextColors(mActivity.getResources().getColor(R.color.color_FFFFFF),mActivity.getResources().getColor(R.color.color_EF5B48));
        }
    }

    /*
     * 轮播图的设定
     * */
    private void setBanner(List<HomeBannerBean.DataBean.ListBean> bannerList){
        if(bannerList == null || bannerList.size() <= 0){
            banner.setVisibility(View.GONE);
            return;
        }

        List<String> bList = new ArrayList<>();
        for(int i = 0 ; i < bannerList.size() ; i++){
            bList.add(bannerList.get(i).getImg_src());
        }

        banner.setImages(bList)
                .setImageLoader(new UseGlideImageLoader())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                .setIndicatorGravity(BannerConfig.CENTER).setOnBannerListener(position -> {
            JumpUtils.JumpBeans jumpBeans = new JumpUtils.JumpBeans();
            jumpBeans.setImg_href_type(bannerList.get(position).getImg_href_type());
            jumpBeans.setHref_code(bannerList.get(position).getImg_href_code());
            jumpBeans.setHref_id(String.valueOf(bannerList.get(position).getImg_href_id()));
            jumpBeans.setImg_href(bannerList.get(position).getImg_href());
            JumpUtils.goAtherPage(mActivity,jumpBeans);
        }).start();
    }

    @Override
    protected void lazyLoad() {
        /*clearParams().setParams("page","4").setParams("curpage","");
        try {
            Controller.myRequest(Constants.GET_CIRCLE_CATEGORY_LIST,Controller.TYPE_POST,getParams(), SlectTopicLeftBeans.class,this);
        }catch (Exception e){
            e.printStackTrace();
        }*/

        //读取banner
        clearParams().setParams("img_code","circle_banner").setParams("img_num","8");
        Controller.myRequest(Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), HomeBannerBean.class,this);
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
                if(position == 1 && mActivity.userToken.getM_mobile() == null){
                    startActivity(VtwoVerificationLoginActivity.class,null);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof HomeBannerBean){
            HomeBannerBean homeBannerBean = (HomeBannerBean)data;
            setBanner(homeBannerBean.getData().getList());
        }

        if(data instanceof SlectTopicLeftBeans){
            topicList = ((SlectTopicLeftBeans)data).getData().getList();
            if(topicList != null && topicList.size() > 0){
                //topRecycler.setVisibility(View.VISIBLE);
                if(topicAdapter != null){
                    topicAdapter.setNewData(topicList);
                }
            }
        }
    }

    /*
     * 设置初始显示页面
     * */
    @Subscribe(id = ConstantsCode.EB_START_MAIN)
    public void result(String message) {
        mViewPager.setCurrentItem(0);
    }

    /*
     * 刷新定位城市
     * */
    @Subscribe(id = ConstantsCode.EB_HOME_LOCATION)
    public void changeLocation(String message) {
        //城市切换了去刷新下数据
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
