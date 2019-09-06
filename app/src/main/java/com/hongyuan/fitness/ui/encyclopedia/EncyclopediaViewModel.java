package com.hongyuan.fitness.ui.encyclopedia;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityEncyclopediaBinding;
import com.hongyuan.fitness.ui.encyclopedia.edit_encyclopedia.EditEncyclopediaActivity;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.MenuPrivateLessonsBean;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class EncyclopediaViewModel extends CustomViewModel {
    private ActivityEncyclopediaBinding binding;
    private EncyclopediaViewPagerAdapter meunAdapter;
    //菜单数据
    private EncyclopediaMenuBeans menuBean;

    public EncyclopediaViewModel(CustomActivity mActivity, ActivityEncyclopediaBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        List<EncyclopediaMenuBeans.DataBean> mList = new ArrayList<>();
        mList.add(new EncyclopediaMenuBeans.DataBean(0,"全部",true));
        meunAdapter = new EncyclopediaViewPagerAdapter(mActivity,mActivity.getSupportFragmentManager(),mList);
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setCurrentItem(0);

        if("2".equals(userToken.getRole_id())){
            binding.addEncyclopedia.setVisibility(View.VISIBLE);
        }else{
            binding.addEncyclopedia.setVisibility(View.GONE);
        }
    }

    /*
    * 自定义菜单栏样式
    * */
    private void setTabViw(EncyclopediaMenuBeans bean){
        //设置自定义tab
        for (int i = 0; i < bean.getData().size(); i++){
            TabLayout.Tab tab = binding.layoutMenu.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(meunAdapter.getTabView(i));
            }
        }
        //设置第一页为选中状态时的tab文字颜色为红色
        View view=binding.layoutMenu.getTabAt(0).getCustomView();
        TextView textView= view.findViewById(R.id.name);
        LinearLayout linearLayout = view.findViewById(R.id.box);
        textView.setTextColor(0xffffffff);
        linearLayout.setBackgroundResource(R.drawable.shape_radius16_ef5b48);
    }

    /*
    * tabview监听
    * */
    private void getTabListener(){
        //监听事件
        binding.layoutMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中了tab的逻辑
                View view=tab.getCustomView();
                TextView textView= view.findViewById(R.id.name);
                LinearLayout linearLayout = view.findViewById(R.id.box);
                textView.setTextColor(0xffffffff);
                linearLayout.setBackgroundResource(R.drawable.shape_radius16_ef5b48);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中tab的逻辑
                View view=tab.getCustomView();
                TextView textView= view.findViewById(R.id.name);
                LinearLayout linearLayout = view.findViewById(R.id.box);
                textView.setTextColor(0xff5F5F5F);
                linearLayout.setBackgroundResource(R.drawable.shape_radius16_ffffff_stroke_979797);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //再次选中tab的逻辑
            }
        });
    }

    //编辑发布百科
    public BindingCommand goEdit = new BindingCommand(() -> startActivity(EditEncyclopediaActivity.class,null));


    @Override
    protected void lazyLoad() {
        //加载私教课类型
        Controller.myRequest(Constants.GET_BAIKE_CATEGORY_LIST,Controller.TYPE_POST,getParams(), EncyclopediaMenuBeans.class,this);

    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof EncyclopediaMenuBeans){
            menuBean = (EncyclopediaMenuBeans)data;
            menuBean.getData().add(0,new EncyclopediaMenuBeans.DataBean(0,"全部",true));
            meunAdapter.setData(menuBean.getData());
            binding.mViewPager.setOffscreenPageLimit(menuBean.getData().size());

            //初始化自定义样式
            setTabViw(menuBean);
            getTabListener();

            binding.isEmpty.setVisibility(View.GONE);
        }
    }
}
