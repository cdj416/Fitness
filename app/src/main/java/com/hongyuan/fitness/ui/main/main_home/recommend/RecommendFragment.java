package com.hongyuan.fitness.ui.main.main_home.recommend;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.reflect.TypeToken;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.HomeColumItemView;
import com.hongyuan.fitness.custom_view.StickyScrollView;
import com.hongyuan.fitness.custom_view.TitleView;
import com.hongyuan.fitness.ui.about_class.GroupClassActivity;
import com.hongyuan.fitness.ui.about_class.PriviteClassActivity;
import com.hongyuan.fitness.ui.about_class.coach.coach_list.CoachListActivity;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsLikeBean;
import com.hongyuan.fitness.ui.heat.HeatActivity;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_verification_login.VtwoVerificationLoginActivity;
import com.hongyuan.fitness.ui.main.AllCitysActivity;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthour.HomeIndexBeans;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthour.V4HomeBeans;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthour.V4HomeRecyclerItemView;
import com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home.GymCategoryBeans;
import com.hongyuan.fitness.ui.out_door.RunActivity;
import com.hongyuan.fitness.ui.out_door.about_you.AboutYouActivity;
import com.hongyuan.fitness.ui.training_plan.PlanInfoBeans;
import com.hongyuan.fitness.ui.training_plan.TrainingPlanActivity;
import com.hongyuan.fitness.ui.training_plan.plan_details.PlanDetailsActivity;
import com.hongyuan.fitness.ui.webview.WebViewActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.GsonUtil;
import com.hongyuan.fitness.util.JumpUtils;
import com.hongyuan.fitness.util.SharedPreferencesUtil;
import com.hongyuan.fitness.util.SkinConstants;
import com.hongyuan.fitness.util.StatusBarUtil;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.hongyuan.fitness.util.ViewChangeUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends CustomFragment implements HomeColumItemView.ClickReturn, V4HomeRecyclerItemView.Commback,StickyScrollView.ScrollViewListener {

    private LinearLayout titleTopViw,gym1Box,gym2Box,gym3Box,gym4Box,faqiBox,canjiaBox,goClassBox1,goClassBox2,goClassBox3,goClassBox4;
    private ImageView gymImg,gymImg1,gymImg2,gymImg3,gymImg4,aboutSportsImg,goClassPage;
    private TextView gymText1,gymText2,gymText3,gymText4;
    private TitleView myTitle;
    private int height;
    private StickyScrollView nScroll;

    private Banner banner;
    private HomeColumItemView homeColum;
    private TextView addressName;
    private V4HomeRecyclerItemView storeItem,yueYunDong,jianZhi,LiLiang,groupCours,PeiXun,goods,topic,finds;

    private V4HomeBeans.DataBean infoData;
    //当前（点赞/取消点赞/关注/取消关注）等操作的数据位置
    private int mPosition;


    //默认点击项（运动）
    private int clickType = HomeColumItemView.RUN_CLICK;

    //头部标题栏右边试图
    private HomeRightView rightView;

    //场馆数据
    private List<GymCategoryBeans.DataBean.ListBean> categorys;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initView(View mView) {
        //开启刷新功能
        setEnableRefresh(true);
        nScroll = mView.findViewById(R.id.nScroll);
        myTitle = mView.findViewById(R.id.myTitle);
        titleTopViw = mView.findViewById(R.id.titleTopViw);

        myTitle.addLeftContentView(getLocation());
        rightView = new HomeRightView(mActivity,this);
        myTitle.addRightContentView(rightView);
        addressName.setOnClickListener(v -> {
            startActivity(AllCitysActivity.class,null);
            //老路径弹框选择
            //CustomDialog.selectLocation(mActivity);
        });
        //getmTitle().getRightView().setOnClickListener(v -> startActivity(ScanActivity.class,null));

        //addressName.setText(LocationBean.getInstance().getCityName());


        banner = mView.findViewById(R.id.banner);
        homeColum = mView.findViewById(R.id.homeColum);

        storeItem = mView.findViewById(R.id.storeItem);
        yueYunDong = mView.findViewById(R.id.yueYunDong);
        jianZhi = mView.findViewById(R.id.jianZhi);
        LiLiang = mView.findViewById(R.id.LiLiang);
        groupCours = mView.findViewById(R.id.groupCours);
        PeiXun = mView.findViewById(R.id.PeiXun);
        goods = mView.findViewById(R.id.goods);
        topic = mView.findViewById(R.id.topic);
        finds = mView.findViewById(R.id.finds);

        gym1Box = mView.findViewById(R.id.gym1Box);
        gym2Box = mView.findViewById(R.id.gym2Box);
        gym3Box = mView.findViewById(R.id.gym3Box);
        gym4Box = mView.findViewById(R.id.gym4Box);
        gymImg1 = mView.findViewById(R.id.gymImg1);
        gymImg2 = mView.findViewById(R.id.gymImg2);
        gymImg3 = mView.findViewById(R.id.gymImg3);
        gymImg4 = mView.findViewById(R.id.gymImg4);
        gymText1 = mView.findViewById(R.id.gymText1);
        gymText2 = mView.findViewById(R.id.gymText2);
        gymText3 = mView.findViewById(R.id.gymText3);
        gymText4 = mView.findViewById(R.id.gymText4);

        gymImg = mView.findViewById(R.id.gymImg);
        faqiBox = mView.findViewById(R.id.faqiBox);
        canjiaBox = mView.findViewById(R.id.canjiaBox);
        aboutSportsImg = mView.findViewById(R.id.aboutSportsImg);

        goClassPage = mView.findViewById(R.id.goClassPage);
        goClassBox1 = mView.findViewById(R.id.goClassBox1);
        goClassBox2 = mView.findViewById(R.id.goClassBox2);
        goClassBox3 = mView.findViewById(R.id.goClassBox3);
        goClassBox4 = mView.findViewById(R.id.goClassBox4);

        homeColum.setClickReturn(this);
        //渐变
        initListeners();

        gymImg.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", Constants.WEB_ADDRESS+"/"+TokenSingleBean.getInstance().getWebAllParams(""));
            bundle.putString("title","场馆");
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }
        });

        gym1Box.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", Constants.WEB_ADDRESS+"/?gc_id="+categorys.get(0).getGc_id()+"&gc_name="+categorys.get(0).getGc_name()+TokenSingleBean.getInstance().getWebAllParams("?"));
            bundle.putString("title","场馆");
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }
        });
        gym2Box.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", Constants.WEB_ADDRESS+"/?gc_id="+categorys.get(1).getGc_id()+"&gc_name="+categorys.get(1).getGc_name()+TokenSingleBean.getInstance().getWebAllParams("?"));
            bundle.putString("title","场馆");
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }
        });
        gym3Box.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", Constants.WEB_ADDRESS+"/?gc_id="+categorys.get(2).getGc_id()+"&gc_name="+categorys.get(2).getGc_name()+TokenSingleBean.getInstance().getWebAllParams("?"));
            bundle.putString("title","场馆");
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }
        });
        gym4Box.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", Constants.WEB_ADDRESS+"/?gc_id="+categorys.get(3).getGc_id()+"&gc_name="+categorys.get(3).getGc_name()+TokenSingleBean.getInstance().getWebAllParams("?"));
            bundle.putString("title","场馆");
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }
        });

        aboutSportsImg.setOnClickListener(v -> {
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.WEB_ADDRESS+ "/Asportslist"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","约运动列表");
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }
        });
        faqiBox.setOnClickListener(v -> {
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.WEB_ADDRESS+ "/Asportslist"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","发起运动");
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }
        });
        canjiaBox.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", Constants.WEB_ADDRESS+ "/Asportslist"+TokenSingleBean.getInstance().getWebAllParams(""));
            bundle.putString("title","参加运动");
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }
        });

        goClassPage.setOnClickListener(v -> {
            //EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"2");
            //需要显示私教课
            //EventBus.getDefault().post(ConstantsCode.EB_SHOW_PRIVITE,"0");
            startActivity(PriviteClassActivity.class,null);
        });
        goClassBox1.setOnClickListener(v -> {
            startActivity(PriviteClassActivity.class,null);
        });
        goClassBox3.setOnClickListener(v -> {
            //通过EventBus去通知MainActivity显示第三页
            //EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"2");
            //需要显示团课
            //EventBus.getDefault().post(ConstantsCode.EB_SHOW_PRIVITE,"1");
            startActivity(GroupClassActivity.class,null);
        });
        goClassBox2.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", Constants.WEB_ADDRESS+"/train"+TokenSingleBean.getInstance().getWebAllParams(""));
            bundle.putString("title","培训课");
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }
        });

        goClassBox4.setOnClickListener(v -> {
            mActivity.startActivity(CoachListActivity.class,null);
        });

    }

    /*
     * 获取头部图片的高度
     * */
    private void initListeners() {
        ViewTreeObserver vto = banner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                banner.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = banner.getHeight();

                nScroll.setScrollViewListener(RecommendFragment.this);
            }
        });
    }

    @Override
    public void onScrollChanged(StickyScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
            if(mActivity.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                titleTopViw.setBackgroundColor(Color.argb((int) 0, 51,51,51));
            }else if(mActivity.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                titleTopViw.setBackgroundColor(Color.argb((int) 0, 255,255,255));

                rightView.changeBlackImg(false);
                addressName.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                ViewChangeUtil.changeLeftDrawable(getContext(),addressName,R.mipmap.location_baise_img);
            }
        } else if (y > 0 && y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);

            if(mActivity.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                titleTopViw.setBackgroundColor(Color.argb((int) alpha, 51,51,51));
            }else if(mActivity.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                titleTopViw.setBackgroundColor(Color.argb((int) alpha, 255,255,255));
                if(y <= height/2){
                    rightView.changeBlackImg(false);
                    addressName.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                    ViewChangeUtil.changeLeftDrawable(getContext(),addressName,R.mipmap.location_baise_img);

                    StatusBarUtil.setCommonUI(mActivity,false);
                }else{
                    rightView.changeBlackImg(true);
                    addressName.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                    ViewChangeUtil.changeLeftDrawable(getContext(),addressName,R.mipmap.location_black_img);

                    StatusBarUtil.setCommonUI(mActivity,true);
                }
            }
        } else {
            if(mActivity.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                titleTopViw.setBackgroundColor(Color.argb((int) 255, 51,51,51));
            }else if(mActivity.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                titleTopViw.setBackgroundColor(Color.argb( 255, 255,255,255));

                addressName.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                ViewChangeUtil.changeLeftDrawable(getContext(),addressName,R.mipmap.location_black_img);
                rightView.changeBlackImg(true);
            }

        }
    }

    @Override
    protected void onMyResume() {
        super.onMyResume();
        //需要自动刷新
        if(isAutoRefresh("refreshTime")){
            lazyLoad();
            //存储当前刷新时间
            inAutoRefreshTime("refreshTime");
        }
    }

    /*
     * 加载坐标定位视图控件
     * */
    private View getLocation(){
        View view = View.inflate(getContext(), R.layout.view_left_location_address,null);
        addressName = view.findViewById(R.id.addressName);
        return view;
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();

        //读取banner
        clearParams().setParams("img_code","index_hd").setParams("img_num","8");
        Controller.myRequest(Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), HomeBannerBean.class,this);

        //获取首页约运动和精品培训课数据
        clearParams().setParams("ct_code",BaseUtil.isValue(TokenSingleBean.getInstance().getRegion_code()) ? TokenSingleBean.getInstance().getRegion_code() : "3505");
        Controller.myRequest(Constants.INDEX,Controller.TYPE_POST,getParams(), HomeIndexBeans.class,this);

        getData();
    }

    /*
    * 获取首页数据
    * */
    private void getData(){
        //获取首页数据
        clearParams();
        Controller.myRequest(Constants.GET_INDEX_OS_PC_BUSINESS_CIRCLE,Controller.TYPE_POST,getParams(), V4HomeBeans.class,this);

        //获取首页场馆类型列表
        clearParams();
        Controller.myRequest(Constants.INDEX_GYM_CATEGORY,Controller.TYPE_POST,getParams(), GymCategoryBeans.class,this);
    }


    /*
     * 轮播图的设定
     * */
    private void setBanner(List<HomeBannerBean.DataBean.ListBean> bannerList){
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
    public void onSuccess(Object data) {

        if(data instanceof HomeBannerBean){
            HomeBannerBean homeBannerBean = (HomeBannerBean)data;
            setBanner(homeBannerBean.getData().getList());
        }

        if(data instanceof V4HomeBeans){
            mActivity.closeLoading();

            infoData = ((V4HomeBeans)data).getData();

            /*if(infoData.getOffline_store() != null && infoData.getOffline_store().size() > 0){
                storeItem.setVisibility(View.VISIBLE);
                storeItem.setStore(infoData.getOffline_store());
            }else{
                storeItem.setVisibility(View.GONE);
            }

            if(infoData.getCp1() != null && infoData.getCp1().size() > 0){
                jianZhi.setVisibility(View.VISIBLE);
                jianZhi.setJianzhi(infoData.getCp1());
            }else{
                jianZhi.setVisibility(View.GONE);
            }

            if(infoData.getCp2() != null && infoData.getCp2().size() > 0){
                LiLiang.setVisibility(View.VISIBLE);
                LiLiang.setLiLiang(infoData.getCp2());
            }else{
                LiLiang.setVisibility(View.GONE);
            }

            if(infoData.getSuper_course() != null && infoData.getSuper_course().size() > 0){
                groupCours.setVisibility(View.VISIBLE);
                groupCours.setGroup(infoData.getSuper_course());
            }else{
                groupCours.setVisibility(View.GONE);
            }*/

            if(infoData.getGoods() != null && infoData.getGoods().size() > 0){
                goods.setVisibility(View.VISIBLE);
                goods.setGoods(infoData.getGoods());
            }else{
                goods.setVisibility(View.GONE);
            }

            if(infoData.getCircle_category() != null && infoData.getCircle_category().size() > 0){
                topic.setVisibility(View.VISIBLE);
                topic.setTopic(infoData.getCircle_category());
            }else{
                topic.setVisibility(View.GONE);
            }

            if(infoData.getCircle() != null && infoData.getCircle().size() > 0){
                finds.setVisibility(View.VISIBLE);
                finds.setFind(infoData.getCircle(),this);
            }else{
                finds.setVisibility(View.GONE);
            }

        }

        if(data instanceof HomeIndexBeans){
            HomeIndexBeans.DataBean indexBeans = ((HomeIndexBeans)data).getData();

            /*if(indexBeans.getSport() != null && indexBeans.getSport().size() > 0){
                yueYunDong.setVisibility(View.VISIBLE);
                yueYunDong.setYueYundong(indexBeans.getSport());
            }else{
                yueYunDong.setVisibility(View.GONE);
            }

            if(indexBeans.getCourse_train() != null && indexBeans.getCourse_train().size() > 0){
                PeiXun.setVisibility(View.VISIBLE);
                PeiXun.setPeiXun(indexBeans.getCourse_train());
            }else{
                PeiXun.setVisibility(View.GONE);
            }*/


        }

        if(data instanceof PlanInfoBeans){
            PlanInfoBeans.DataBean dataBean = ((PlanInfoBeans)data).getData();
            if(dataBean.getInfo() != null && BaseUtil.isValue(dataBean.getInfo().getHeight())){
                startActivity(PlanDetailsActivity.class,null);
            }else{
                startActivity(TrainingPlanActivity.class,null);
            }
        }

        if(data instanceof GymCategoryBeans){
            categorys = ((GymCategoryBeans)data).getData().getList();

            if(categorys != null && categorys.size() > 0){
                if(categorys.size() >= 4){
                    gym1Box.setVisibility(View.VISIBLE);
                    gym2Box.setVisibility(View.VISIBLE);
                    gym3Box.setVisibility(View.VISIBLE);
                    gym4Box.setVisibility(View.VISIBLE);

                    RequestOptions options = new RequestOptions().placeholder(R.mipmap.theme_jiansheng_mark).error(R.mipmap.theme_jiansheng_mark);
                    if(SkinConstants.SKIN_NAME.DEFAULT.equals(mActivity.skin)){
                        Glide.with(mActivity).load(categorys.get(0).getGc_img_day()).apply(options).into(gymImg1);
                        Glide.with(mActivity).load(categorys.get(1).getGc_img_day()).apply(options).into(gymImg2);
                        Glide.with(mActivity).load(categorys.get(2).getGc_img_day()).apply(options).into(gymImg3);
                        Glide.with(mActivity).load(categorys.get(3).getGc_img_day()).apply(options).into(gymImg4);
                        gymText1.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                        gymText1.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                        gymText2.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                        gymText3.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                    }
                    if(SkinConstants.SKIN_NAME.BLACK.equals(mActivity.skin)) {
                        Glide.with(mActivity).load(categorys.get(0).getGc_img_night()).apply(options).into(gymImg1);
                        Glide.with(mActivity).load(categorys.get(1).getGc_img_night()).apply(options).into(gymImg2);
                        Glide.with(mActivity).load(categorys.get(2).getGc_img_night()).apply(options).into(gymImg3);
                        Glide.with(mActivity).load(categorys.get(3).getGc_img_night()).apply(options).into(gymImg4);
                        gymText1.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                        gymText2.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                        gymText3.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                        gymText4.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                    }
                    gymText1.setText(categorys.get(0).getGc_name());
                    gymText2.setText(categorys.get(1).getGc_name());
                    gymText3.setText(categorys.get(2).getGc_name());
                    gymText4.setText(categorys.get(3).getGc_name());
                }else if(categorys.size() == 3){
                    gym1Box.setVisibility(View.VISIBLE);
                    gym2Box.setVisibility(View.VISIBLE);
                    gym3Box.setVisibility(View.VISIBLE);
                    gym4Box.setVisibility(View.INVISIBLE);

                    RequestOptions options = new RequestOptions().placeholder(R.mipmap.theme_jiansheng_mark).error(R.mipmap.theme_jiansheng_mark);
                    if(SkinConstants.SKIN_NAME.DEFAULT.equals(mActivity.skin)){
                        Glide.with(mActivity).load(categorys.get(0).getGc_img_day()).apply(options).into(gymImg1);
                        Glide.with(mActivity).load(categorys.get(1).getGc_img_day()).apply(options).into(gymImg2);
                        Glide.with(mActivity).load(categorys.get(2).getGc_img_day()).apply(options).into(gymImg3);
                        //Glide.with(mActivity).load(categorys.get(3).getGc_img_day()).apply(options).into(gymImg4);
                        gymText1.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                        gymText1.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                        gymText2.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                        //gymText3.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                    }
                    if(SkinConstants.SKIN_NAME.BLACK.equals(mActivity.skin)) {
                        Glide.with(mActivity).load(categorys.get(0).getGc_img_night()).apply(options).into(gymImg1);
                        Glide.with(mActivity).load(categorys.get(1).getGc_img_night()).apply(options).into(gymImg2);
                        Glide.with(mActivity).load(categorys.get(2).getGc_img_night()).apply(options).into(gymImg3);
                        //Glide.with(mActivity).load(categorys.get(3).getGc_img_night()).apply(options).into(gymImg4);
                        gymText1.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                        gymText2.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                        gymText3.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                        //gymText4.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                    }
                    gymText1.setText(categorys.get(0).getGc_name());
                    gymText2.setText(categorys.get(1).getGc_name());
                    gymText3.setText(categorys.get(2).getGc_name());
                    //gymText4.setText(categorys.get(3).getGc_name());
                }else if(categorys.size() == 2){
                    gym1Box.setVisibility(View.VISIBLE);
                    gym2Box.setVisibility(View.VISIBLE);
                    gym3Box.setVisibility(View.INVISIBLE);
                    gym4Box.setVisibility(View.INVISIBLE);

                    RequestOptions options = new RequestOptions().placeholder(R.mipmap.theme_jiansheng_mark).error(R.mipmap.theme_jiansheng_mark);
                    if(SkinConstants.SKIN_NAME.DEFAULT.equals(mActivity.skin)){
                        Glide.with(mActivity).load(categorys.get(0).getGc_img_day()).apply(options).into(gymImg1);
                        Glide.with(mActivity).load(categorys.get(1).getGc_img_day()).apply(options).into(gymImg2);
                        //Glide.with(mActivity).load(categorys.get(2).getGc_img_day()).apply(options).into(gymImg3);
                        //Glide.with(mActivity).load(categorys.get(3).getGc_img_day()).apply(options).into(gymImg4);
                        gymText1.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                        gymText1.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                        //gymText2.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                        //gymText3.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                    }
                    if(SkinConstants.SKIN_NAME.BLACK.equals(mActivity.skin)) {
                        Glide.with(mActivity).load(categorys.get(0).getGc_img_night()).apply(options).into(gymImg1);
                        Glide.with(mActivity).load(categorys.get(1).getGc_img_night()).apply(options).into(gymImg2);
                        //Glide.with(mActivity).load(categorys.get(2).getGc_img_night()).apply(options).into(gymImg3);
                        //Glide.with(mActivity).load(categorys.get(3).getGc_img_night()).apply(options).into(gymImg4);
                        gymText1.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                        gymText2.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                        //gymText3.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                        //gymText4.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                    }
                    gymText1.setText(categorys.get(0).getGc_name());
                    gymText2.setText(categorys.get(1).getGc_name());
                    //gymText3.setText(categorys.get(2).getGc_name());
                    //gymText4.setText(categorys.get(3).getGc_name());
                }else if(categorys.size() == 1){
                    gym1Box.setVisibility(View.VISIBLE);
                    gym2Box.setVisibility(View.INVISIBLE);
                    gym3Box.setVisibility(View.INVISIBLE);
                    gym4Box.setVisibility(View.INVISIBLE);

                    RequestOptions options = new RequestOptions().placeholder(R.mipmap.theme_jiansheng_mark).error(R.mipmap.theme_jiansheng_mark);
                    if(SkinConstants.SKIN_NAME.DEFAULT.equals(mActivity.skin)){
                        Glide.with(mActivity).load(categorys.get(0).getGc_img_day()).apply(options).into(gymImg1);
                        //Glide.with(mActivity).load(categorys.get(1).getGc_img_day()).apply(options).into(gymImg2);
                        //Glide.with(mActivity).load(categorys.get(2).getGc_img_day()).apply(options).into(gymImg3);
                        //Glide.with(mActivity).load(categorys.get(3).getGc_img_day()).apply(options).into(gymImg4);
                        gymText1.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                        //gymText1.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                        //gymText2.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                        //gymText3.setTextColor(mActivity.getResources().getColor(R.color.color_FF333333));
                    }
                    if(SkinConstants.SKIN_NAME.BLACK.equals(mActivity.skin)) {
                        Glide.with(mActivity).load(categorys.get(0).getGc_img_night()).apply(options).into(gymImg1);
                        //Glide.with(mActivity).load(categorys.get(1).getGc_img_night()).apply(options).into(gymImg2);
                        //Glide.with(mActivity).load(categorys.get(2).getGc_img_night()).apply(options).into(gymImg3);
                        //Glide.with(mActivity).load(categorys.get(3).getGc_img_night()).apply(options).into(gymImg4);
                        //gymText1.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                        //gymText2.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                        //gymText3.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                        //gymText4.setTextColor(mActivity.getResources().getColor(R.color.color_FFFFFF));
                    }
                    gymText1.setText(categorys.get(0).getGc_name());
                    //gymText2.setText(categorys.get(1).getGc_name());
                    //gymText3.setText(categorys.get(2).getGc_name());
                    //gymText4.setText(categorys.get(3).getGc_name());
                }else{
                    gym1Box.setVisibility(View.INVISIBLE);
                    gym2Box.setVisibility(View.INVISIBLE);
                    gym3Box.setVisibility(View.INVISIBLE);
                    gym4Box.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.CHECK_MEMBER_BOBY_INDEX){
            try {
                BaseBean baseBean = GsonUtil.getGson().fromJson(data.toString(), new TypeToken<BaseBean>(){}.getType());
                if(baseBean.getStatus().getError_code() == ISLOGIN && baseBean.getStatus().getError_desc().contains("登录")){
                    startActivity(VtwoVerificationLoginActivity.class,null);
                }

                JSONObject object = new JSONObject(data.toString());
                JSONObject jsonObject = (JSONObject) object.get("data");
                if(BaseUtil.isJsonValue(jsonObject.get("info"))){
                    if(clickType == HomeColumItemView.RUN_CLICK){
                        startActivity(RunActivity.class,null);
                    }else{
                        startActivity(HeatActivity.class,null);
                    }
                }else{
                    Bundle bundle = new Bundle();
                    if(clickType == HomeColumItemView.RUN_CLICK){
                        bundle.putString("pagType","run");
                    }else{
                        bundle.putString("pagType","heat");
                    }
                    startActivity(AboutYouActivity.class,bundle);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(code == ConstantsCode.ADD_CIRCLE_PRAISE){
            infoData.getCircle().get(mPosition).setIs_praise(1);
            infoData.getCircle().get(mPosition).setPraise_num(infoData.getCircle().get(mPosition).getPraise_num()+1);
            finds.setFind(infoData.getCircle(),this);
            //通知刷新数据，让数据同步
            EventBus.getDefault().post(ConstantsCode.EB_ADD_CIRCLE,"同步数据");
            showSuccess("点赞成功！");
        }
        if(code == ConstantsCode.CANCEL_CIRCLE_PRAISE){
            infoData.getCircle().get(mPosition).setIs_praise(0);
            infoData.getCircle().get(mPosition).setPraise_num(infoData.getCircle().get(mPosition).getPraise_num()-1);
            finds.setFind(infoData.getCircle(),this);
            //通知刷新数据，让数据同步
            EventBus.getDefault().post(ConstantsCode.EB_ADD_CIRCLE,"同步数据");
            showSuccess("已取消点赞！");
        }


    }

    /*
    * 检查是否完善个人信息
    * */
    @Override
    public void itemClick(int code) {
        if(code == HomeColumItemView.TRAINING_PLAN){
            //检查是否有计划目标
            clearParams();
            Controller.myRequest(Constants.GET_PLAN_INFO,Controller.TYPE_POST,getParams(), PlanInfoBeans.class,this);
        }else{
            clickType = code;
            Controller.myRequest(ConstantsCode.CHECK_MEMBER_BOBY_INDEX,Constants.CHECK_MEMBER_BOBY_INDEX,Controller.TYPE_POST,getParams(),this);
        }

    }

    @Override
    public void commback(int position,String datas) {
        mPosition = position;
        clearParams().setParams("circle_id",String.valueOf(infoData.getCircle().get(position).getCircle_id()));
        if(infoData.getCircle().get(position).getIs_praise() == 0){
            Controller.myRequest(ConstantsCode.ADD_CIRCLE_PRAISE,Constants.ADD_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }else{
            Controller.myRequest(ConstantsCode.CANCEL_CIRCLE_PRAISE,Constants.CANCEL_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }
    }

    /*
     * 刷新定位城市
     * */
    @Subscribe(id = ConstantsCode.EB_HOME_LOCATION)
    public void changeLocation(String message) {
        addressName.setText(TokenSingleBean.getInstance().getRegion_name());

        //去存储一下当前使用的开通城市
        SharedPreferencesUtil.putString(mActivity,"region_name",TokenSingleBean.getInstance().getRegion_name());
        SharedPreferencesUtil.putString(mActivity,"region_code",TokenSingleBean.getInstance().getRegion_code());

        //城市切换了去刷新下数据
        lazyLoad();
    }

    /*
     * 刷新下数据
     * */
    @Subscribe(id = ConstantsCode.EB_HOME_REFRESH)
    public void result(String message) {
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
