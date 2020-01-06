package com.hongyuan.fitness.ui.membership_card.v4_mycard_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.FlowLayoutManager;
import com.hongyuan.fitness.databinding.ActivityV4MycardDetailsBinding;
import com.hongyuan.fitness.ui.membership_card.card_detail.CardDetailsActivity;
import com.hongyuan.fitness.ui.membership_card.card_detail.CardDetailsBean;
import com.hongyuan.fitness.ui.membership_card.my_card_detail.MyCardDetailsBeans;
import com.hongyuan.fitness.ui.store.MarkTextAdapter;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.DividerItemDecoration;

public class V4MyCardDetailViewModel extends CustomViewModel {

    //buy card
    public static final int BUY_AISLE = 0x01;
    //my card
    public static final int MY_AISLE = 0x02;

    private ActivityV4MycardDetailsBinding binding;

    private MarkTextAdapter mtAdapter;
    private MyCardDetailsBeans.DataBean.InfoBean detailsBeans;
    private CardDetailsBean.DataBean buyCardsBeans;

    //门店id
    private String os_id;

    public V4MyCardDetailViewModel(CustomActivity mActivity, ActivityV4MycardDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        os_id = getBundle().getString("os_id");

        //门店设施标签
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        binding.nestRec.setLayoutManager(flowLayoutManager);
        binding.nestRec.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.VERTICAL_LIST,20,0x00000000));
        binding.nestRec.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.HORIZONTAL_LIST,20,0x00000000));
        binding.nestRec.setLayoutManager(flowLayoutManager);
        mtAdapter = new MarkTextAdapter();
        binding.nestRec.setAdapter(mtAdapter);

        binding.cardButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("card_id",String.valueOf(buyCardsBeans.getCard_id()));
            bundle.putString("os_id",os_id);
            startActivity(CardDetailsActivity.class,bundle);
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        if(getBundle().getInt("aisle") == MY_AISLE){
            clearParams().setParams("my_card_id",getBundle().getString("my_card_id"));
            Controller.myRequest(Constants.GET_MY_CARD_INFO,Controller.TYPE_POST,getParams(), MyCardDetailsBeans.class,this);
        }else{
            clearParams().setParams("card_id",getBundle().getString("card_id"));
            Controller.myRequest(Constants.GET_CARD_INFO,Controller.TYPE_POST,getParams(), CardDetailsBean.class,this);
        }
    }

    @Override
    protected void setData() {

        if(getBundle().getInt("aisle") == MY_AISLE){
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img);
            Glide.with(mActivity).load(detailsBeans.getCard_img()).apply(options).into(binding.cardImg);
            binding.useDays.setText(detailsBeans.getMy_card_days()+"天");
            binding.useStores.setText(detailsBeans.getOs_names());

            binding.cardName.setVisibility(View.GONE);
            binding.cardData.setVisibility(View.GONE);
            binding.cardPriceBox.setVisibility(View.GONE);
            binding.cardButton.setText("暂未开通停卡退卡功能\n如有需要请联系线下客服人员");
            binding.cardButton.setBackgroundResource(R.drawable.shape_radius5_cccccc);
            binding.cardButton.setClickable(false);
        }else{
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img);
            Glide.with(mActivity).load(buyCardsBeans.getC_img()).apply(options).into(binding.cardImg);
            binding.useDays.setText(buyCardsBeans.getCard_days()+"天");
            binding.useStores.setText(buyCardsBeans.getOs_names());
            binding.cardPrice.setText(BaseUtil.getNoZoon(buyCardsBeans.getCard_sale_price()));
            binding.cardName.setText(buyCardsBeans.getCard_name());

            if(BaseUtil.isValue(buyCardsBeans.getCc_note())){
                binding.cardBuyDes.setVisibility(View.VISIBLE);
                binding.cardBuyDesTitle.setVisibility(View.VISIBLE);
                binding.cardBuyDes.setText(buyCardsBeans.getCc_note());
            }

            if(BaseUtil.isValue(buyCardsBeans.getCard_note())){
                binding.cardDesTitle.setVisibility(View.VISIBLE);
                binding.cardDes.setText(buyCardsBeans.getCard_note());
                binding.cardDes.setVisibility(View.VISIBLE);
            }

            binding.cardName.setVisibility(View.VISIBLE);
            binding.cardData.setVisibility(View.VISIBLE);
            binding.cardPriceBox.setVisibility(View.VISIBLE);
            binding.cardButton.setText("购买");
            binding.cardButton.setBackgroundResource(R.drawable.shape_gradient_v_radiu5_login);
            binding.cardButton.setClickable(true);
        }
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof MyCardDetailsBeans){
            detailsBeans = ((MyCardDetailsBeans)data).getData().getInfo();
            setData();
        }

        if(data instanceof CardDetailsBean){
            buyCardsBeans = ((CardDetailsBean)data).getData();
            setData();
        }
    }
}
