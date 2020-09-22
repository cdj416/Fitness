package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.tablayout.MyPageTransformer;
import com.hongyuan.fitness.custom_view.tablayout.TabLayout;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.shop.sactivity.MainSearchActivity;
import com.hongyuan.fitness.ui.shop.sactivity.MyShopActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SCartActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMenuActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMessageActivity;
import com.hongyuan.fitness.ui.shop.sbeans.FirstCategoryBeans;
import com.hongyuan.fitness.ui.shop.sbeans.ReadNumBeans;
import com.hongyuan.fitness.ui.shop.sviewpage.ShopViewPagerAdapter;
import com.hongyuan.fitness.util.SkinConstants;
import com.hongyuan.fitness.util.huanxin.ChatDataBeans;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends CustomFragment {

    private TabLayout layoutMenu;
    private ViewPager mViewPager;
    private ImageView sortMark,goCart,goMyShop;
    private LinearLayout searchBox;
    private RelativeLayout mMessage;
    private TextView newMeassageMark;

    private ShopViewPagerAdapter meunAdapter;

    //分类数据
    private FirstCategoryBeans.DataBean dataBean;

    private String skin;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    public void initView(View mView) {
        skin = mActivity.skin;

        searchBox = mView.findViewById(R.id.searchBox);
        layoutMenu = mView.findViewById(R.id.layoutMenu);
        mViewPager = mView.findViewById(R.id.mViewPager);
        sortMark = mView.findViewById(R.id.sortMark);
        mMessage = mView.findViewById(R.id.mMessage);
        newMeassageMark = mView.findViewById(R.id.newMeassageMark);
        goCart = mView.findViewById(R.id.goCart);
        goMyShop = mView.findViewById(R.id.goMyShop);

        sortMark.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("menu",dataBean);
            bundle.putInt("mPosition",-1);
            startActivity(ShopMenuActivity.class,bundle);
        });
        mMessage.setOnClickListener(v -> {
            startActivity(ShopMessageActivity.class,null);
            //startActivity(MineMessageActivity.class,null);
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

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            layoutMenu.setTabTextColors(getResources().getColor(R.color.color_FF333333),getResources().getColor(R.color.color_EF5B48));
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            layoutMenu.setTabTextColors(getResources().getColor(R.color.color_FFFFFF),getResources().getColor(R.color.color_EF5B48));
    }

    @Override
    public void onResume() {
        super.onResume();
        //每次来，去刷新下当前模式
        if(!skin.equals(mActivity.skin)){
            skin = mActivity.skin;
            if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
                layoutMenu.setTabTextColors(getResources().getColor(R.color.color_FF333333),getResources().getColor(R.color.color_EF5B48));
            if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
                layoutMenu.setTabTextColors(getResources().getColor(R.color.color_FFFFFF),getResources().getColor(R.color.color_EF5B48));
        }

        getMessageNum();
    }

    /*
    * 刷新获取未读消息量
    * */
    private void getMessageNum(){
        if(mActivity.userToken.getM_id() != null) {
            //读取消息未读数据量
            clearParams();
            Controller.myRequest(Constants.GET_MSG_UNREAD_INFO, Controller.TYPE_POST, getParams(), ReadNumBeans.class, this);
        }else{
            newMeassageMark.setVisibility(View.GONE);
        }
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
        super.onSuccess(data);

        mActivity.closeLoading();
        if(data instanceof FirstCategoryBeans){
            dataBean = ((FirstCategoryBeans)data).getData();

            setPageData(dataBean.getList(),dataBean);
        }

        if(data instanceof ReadNumBeans){
            ReadNumBeans.DataBean numBeans = ((ReadNumBeans)data).getData();

            //当前数据每次都要刷新下
            List<ChatDataBeans> chatDataBeansList = HuanXinUtils.getInstance().getMessageList();
            int chatNum = numBeans.getAll();
            for(ChatDataBeans chatBean : chatDataBeansList){
                chatNum += chatBean.getUnreadNum();
            }

            if(chatNum > 0){
                newMeassageMark.setVisibility(View.VISIBLE);
                newMeassageMark.setText(String.valueOf(chatNum));
            }else{
                newMeassageMark.setVisibility(View.GONE);
                newMeassageMark.setText(String.valueOf(0));
            }

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

    /*
     * 刷新下数据
     * */
    @Subscribe(id = ConstantsCode.EB_CHANGE_PERSON)
    public void result(String message) {
        getMessageNum();
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
