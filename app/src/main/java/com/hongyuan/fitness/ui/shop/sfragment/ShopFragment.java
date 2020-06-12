package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.tablayout.MyPageTransformer;
import com.hongyuan.fitness.custom_view.tablayout.TabLayout;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.person.mine_message.MineMessageActivity;
import com.hongyuan.fitness.ui.shop.sactivity.MainSearchActivity;
import com.hongyuan.fitness.ui.shop.sactivity.MyShopActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SCartActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMenuActivity;
import com.hongyuan.fitness.ui.shop.sbeans.FirstCategoryBeans;
import com.hongyuan.fitness.ui.shop.sviewpage.ShopViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends CustomFragment {

    private TabLayout layoutMenu;
    private ViewPager mViewPager;
    private ImageView sortMark,mMessage,goCart,goMyShop;
    private LinearLayout searchBox;

    private ShopViewPagerAdapter meunAdapter;

    //分类数据
    private FirstCategoryBeans.DataBean dataBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    public void initView(View mView) {
        searchBox = mView.findViewById(R.id.searchBox);
        layoutMenu = mView.findViewById(R.id.layoutMenu);
        mViewPager = mView.findViewById(R.id.mViewPager);
        sortMark = mView.findViewById(R.id.sortMark);
        mMessage = mView.findViewById(R.id.mMessage);
        goCart = mView.findViewById(R.id.goCart);
        goMyShop = mView.findViewById(R.id.goMyShop);

        sortMark.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("menu",dataBean);
            bundle.putInt("mPosition",-1);
            startActivity(ShopMenuActivity.class,bundle);
        });
        mMessage.setOnClickListener(v -> {
            //startActivity(ShopMessageActivity.class,null);
            startActivity(MineMessageActivity.class,null);
        });




        searchBox.setOnClickListener(v -> {
            //startActivity(ShopSearchActivity.class,null);
            startActivity(MainSearchActivity.class,null);
        });
        goCart.setOnClickListener(v ->{
            startActivity(SCartActivity.class,null);
        });
        goMyShop.setOnClickListener(v -> {
            startActivity(MyShopActivity.class,null);
        });
    }
    /**
     * 将 Tab[index] 放大为初始的 scale 倍
     */
    private void setScale(int index, float scale) {
        LinearLayout ll = (LinearLayout) layoutMenu.getChildAt(0);
        TabLayout.TabView tb = (TabLayout.TabView) ll.getChildAt(0);
        View view  = tb.getTextView();
        view.setScaleX(scale);
        view.setScaleY(scale);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_FIRST_CATEGORY,Controller.TYPE_POST,getParams(), FirstCategoryBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof FirstCategoryBeans){
            dataBean = ((FirstCategoryBeans)data).getData();

            setPageData(dataBean.getList(),dataBean);
        }
    }

    /*
    * 组装viewPage数据
    * */
    private void setPageData(List<FirstCategoryBeans.DataBean.ListBean> mList,FirstCategoryBeans.DataBean itemAll){
        List<CustomFragment> fragments = new ArrayList<>();
        List<TitleBean> beans = new ArrayList<>();

        if(mList != null && mList.size() > 0){
            for(int i = 0 ; i < mList.size() ; i++){
                beans.add(new TitleBean(mList.get(i).getCategory_name(),i));
                fragments.add(new ShopNextFragment().setMyArguments(getBundle(mList.get(i),itemAll,i)));
            }
            beans.add(0,new TitleBean("推荐",0));
            fragments.add(0,new ShopMainFragment().setArguments(""));
        }


        meunAdapter = new ShopViewPagerAdapter(getChildFragmentManager(),fragments,beans);
        mViewPager.setAdapter(meunAdapter);
        layoutMenu.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(dataBean.getList().size());
        layoutMenu.post(() -> setScale(0, MyPageTransformer.MAX_SCALE));
        mViewPager.setPageTransformer(false, new MyPageTransformer(layoutMenu));

    }

    private Bundle getBundle(FirstCategoryBeans.DataBean.ListBean item,FirstCategoryBeans.DataBean itemAll,int mPosition){
        Bundle bundle = new Bundle();
        bundle.putSerializable("item",item);
        bundle.putSerializable("menu",itemAll);
        bundle.putInt("mPosition",mPosition);
        return bundle;
    }

}
