package com.hongyuan.fitness.ui.main;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMainBinding;
import com.hongyuan.fitness.ui.login.vtwo_login.VtwoLoginActivity;
import com.hongyuan.fitness.util.PackageUtils;

import me.majiajie.pagerbottomtabstrip.NavigationController;

public class MainViewModel extends CustomViewModel {
    private ActivityMainBinding binding;
    private MyViewPagerAdapter myViewPagerAdapter;
    private CheckVersionBeans.DataBean.InfoBean versionBeans;





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
                    startActivity(VtwoLoginActivity.class,null);
                }
                if(position == 4){
                    mActivity.setTitleBar(mActivity.TYPE_BAR2,R.drawable.shape_gradient_h_39_4a,"");
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

    @Override
    public void onSuccess(Object data) {
        if(data instanceof CheckVersionBeans){
            versionBeans = ((CheckVersionBeans)data).getData().getInfo();
            //版本检测更新
            binding.versionView.startChange(versionBeans);
        }
    }


}
