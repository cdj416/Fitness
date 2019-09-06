package com.hongyuan.fitness.ui.main.main_home.recommend;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.HomeColumItemView;
import com.hongyuan.fitness.custom_view.HomeRecyclerItemView;
import com.hongyuan.fitness.ui.heat.HeatActivity;
import com.hongyuan.fitness.ui.login.vtwo_login.VtwoLoginActivity;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class.VtwoGroupClassBeans;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsBeans;
import com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home.VtwoStarCoachBean;
import com.hongyuan.fitness.ui.out_door.RunActivity;
import com.hongyuan.fitness.ui.out_door.about_you.AboutYouActivity;
import com.hongyuan.fitness.ui.scan.ScanActivity;
import com.hongyuan.fitness.ui.store.more_store.StoreBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.GsonUtil;
import com.hongyuan.fitness.util.LocationBean;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends CustomFragment implements HomeColumItemView.ClickReturn {

    private HomeRecyclerItemView storeItem,boutiqueGroup,starCoach,priviteCourse;
    private Banner banner;
    private HomeColumItemView homeColum;
    private TextView addressName;


    //默认点击项（运动）
    private int clickType = HomeColumItemView.RUN_CLICK;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initView(View mView) {
        //开启刷新功能
        setEnableRefresh(true);

        getmTitle().setCentreText("首页");
        getmTitle().setRightImage(R.mipmap.scan_code_img);
        getmTitle().addLeftContentView(getLocation());
        addressName.setOnClickListener(v -> CustomDialog.selectLocation(mActivity));
        getmTitle().getRightView().setOnClickListener(v -> startActivity(ScanActivity.class,null));

        //addressName.setText(LocationBean.getInstance().getCityName());

        storeItem = mView.findViewById(R.id.storeItem);
        boutiqueGroup = mView.findViewById(R.id.boutiqueGroup);
        starCoach = mView.findViewById(R.id.starCoach);
        banner = mView.findViewById(R.id.banner);
        homeColum = mView.findViewById(R.id.homeColum);
        homeColum = mView.findViewById(R.id.homeColum);
        priviteCourse = mView.findViewById(R.id.priviteCourse);
        homeColum.setClickReturn(this);

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
        //读取banner
        clearParams().setParams("img_code","index_hd").setParams("img_num","8");
        Controller.myRequest(Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), HomeBannerBean.class,this);

        //读取附近门店
        clearParams().setParams("lat", LocationBean.getInstance().getLat())
                .setParams("lng",LocationBean.getInstance().getLng());
        Controller.myRequest(Constants.GET_OFFLINE_STORE_LIST_JULI,Controller.TYPE_POST,getParams(), StoreBean.class,this);

        //首页--读取首页教练
        clearParams().setParams("city_name","湖州市");
        Controller.myRequest(Constants.GET_COACH_LIST,Controller.TYPE_POST,getParams(), VtwoStarCoachBean.class,this);

        //课程--私教课列表
        clearParams().setParams("page","5").setParams("curpage","1");
        Controller.myRequest(Constants.GET_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), VtwoPrivateLessonsBeans.class,this);

        //获取门店的团课
        clearParams().setParams("city_name","湖州市")
                .setParams("lat", LocationBean.getInstance().getLat())
                .setParams("lng",LocationBean.getInstance().getLng());
        Controller.myRequest(Constants.GET_TWO_COURSE_SUPER_LIST,Controller.TYPE_POST,getParams(), VtwoGroupClassBeans.class,this);
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
                .setIndicatorGravity(BannerConfig.CENTER).start();
    }

    @Override
    public void onSuccess(Object data) {

        if(data instanceof StoreBean){
            StoreBean storeBean = (StoreBean)data;
            storeItem.setVisibility(View.VISIBLE);
            storeItem.setHomeStore(storeBean.getData().getList());
        }

        if(data instanceof VtwoStarCoachBean){
            VtwoStarCoachBean starCoachBean = (VtwoStarCoachBean)data;
            starCoach.setVisibility(View.VISIBLE);
            starCoach.setHomeStarCoach(starCoachBean.getData().getList());
        }

        if(data instanceof VtwoPrivateLessonsBeans){
            VtwoPrivateLessonsBeans privateLessonsBeans = (VtwoPrivateLessonsBeans)data;
            priviteCourse.setVisibility(View.VISIBLE);
            priviteCourse.setPriviteCourse(privateLessonsBeans.getData().getList());
        }

        if(data instanceof VtwoGroupClassBeans){
            VtwoGroupClassBeans groupBean = (VtwoGroupClassBeans)data;
            boutiqueGroup.setVisibility(View.VISIBLE);
            boutiqueGroup.setHomeBoutiqueGroup(groupBean.getData().getList());
        }

        if(data instanceof HomeBannerBean){
            HomeBannerBean homeBannerBean = (HomeBannerBean)data;
            setBanner(homeBannerBean.getData().getList());
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.CHECK_MEMBER_BOBY_INDEX){
            try {
                BaseBean baseBean = GsonUtil.getGson().fromJson(data.toString(), new TypeToken<BaseBean>(){}.getType());
                if(baseBean.getStatus().getError_code() == ISLOGIN && baseBean.getStatus().getError_desc().contains("登录")){
                    startActivity(VtwoLoginActivity.class,null);
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
    }

    /*
    * 检查是否完善个人信息
    * */
    @Override
    public void itemClick(int code) {
        clickType = code;
        Controller.myRequest(ConstantsCode.CHECK_MEMBER_BOBY_INDEX,Constants.CHECK_MEMBER_BOBY_INDEX,Controller.TYPE_POST,getParams(),this);
    }
}
