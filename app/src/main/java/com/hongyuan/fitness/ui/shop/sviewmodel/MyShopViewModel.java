package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityMyShopBinding;
import com.hongyuan.fitness.ui.login.vtwo_login.VtwoLoginActivity;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.main.main_person.PersonBean;
import com.hongyuan.fitness.ui.person.my_coupon.MyCouponActivity;
import com.hongyuan.fitness.ui.person.my_coupon.newcoupon.NewCouponActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopAddressActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopNewOrderAcitivity;
import com.hongyuan.fitness.ui.shop.sadapter.SMGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.HabitGoddsBeans;

import java.util.List;

public class MyShopViewModel extends CustomViewModel {

    private AcitivityMyShopBinding binding;

    private SMGoodsAdapter gAdapter;

    //猜你喜欢
    private List<HabitGoddsBeans.DataBean.ListBean> mList;

    public MyShopViewModel(CustomActivity mActivity,AcitivityMyShopBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        GridLayoutManager layoutManager =
                new GridLayoutManager(mActivity,2);
        binding.mRec.setLayoutManager(layoutManager);
        gAdapter = new SMGoodsAdapter<HabitGoddsBeans.DataBean.ListBean>() {
            @Override
            public String getImg(HabitGoddsBeans.DataBean.ListBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(HabitGoddsBeans.DataBean.ListBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(HabitGoddsBeans.DataBean.ListBean item) {
                return item.getG_price();
            }
        };
        binding.mRec.setAdapter(gAdapter);
        gAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(mList.get(position).getG_id()));
            startActivity(SgoodsDetailActivity.class,bundle);
        });

        binding.goAllOrder.setOnClickListener(v -> {
            mActivity.startActivity(ShopNewOrderAcitivity.class,null);
        });
        binding.goPay.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",1);
            mActivity.startActivity(ShopNewOrderAcitivity.class,bundle);
        });
        binding.goWait.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",2);
            mActivity.startActivity(ShopNewOrderAcitivity.class,bundle);
        });
        binding.godelpey.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",3);
            mActivity.startActivity(ShopNewOrderAcitivity.class,bundle);
        });
        binding.goWation.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",4);
            mActivity.startActivity(ShopNewOrderAcitivity.class,bundle);
        });

        binding.goAddress.setOnClickListener(v -> {
            startActivityForResult(ShopAddressActivity.class,null);
        });
        binding.goCoupon.setOnClickListener(v -> {
            //startActivity(MyCouponActivity.class,null);
            startActivity(NewCouponActivity.class,null);
        });

    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_HABIT_GODDS_LIST,Controller.TYPE_POST,getParams(), HabitGoddsBeans.class,this);

        if(mActivity.userToken.getM_id() != null){
            //读取个人信息
            clearParams();
            Controller.myRequest(Constants.GET_MEMBER_INDEX_INFO,Controller.TYPE_POST,getParams(), PersonBean.class,this);
        }else{
            startActivity(VtwoLoginActivity.class,null);
        }
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof HabitGoddsBeans){
            List<HabitGoddsBeans.DataBean.ListBean> list = ((HabitGoddsBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    mActivity.refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                gAdapter.setNewData(mList);
                //setPromtView(SHOW_DATA);
            }else{
                //setPromtView(SHOW_EMPTY);
            }
        }

        if(data instanceof PersonBean){
            PersonBean.DataBean.InfoBean personBean = ((PersonBean)data).getData().getInfo();
            TokenSingleBean.getInstance().setHeadUrl(personBean.getMi_head());

            RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
            Glide.with(mActivity).load(personBean.getMi_head()).apply(options).into(binding.headImg);

            binding.userName.setText(personBean.getMi_realname());
            binding.notes.setText(personBean.getMi_sign());
        }
    }
}
