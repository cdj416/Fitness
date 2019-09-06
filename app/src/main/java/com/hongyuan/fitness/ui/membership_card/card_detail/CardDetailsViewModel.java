package com.hongyuan.fitness.ui.membership_card.card_detail;

import android.os.Bundle;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.FlowLayoutManager;
import com.hongyuan.fitness.databinding.ActivityCardDetailsBinding;
import com.hongyuan.fitness.ui.about_class.check_payment_method.OrderBean;
import com.hongyuan.fitness.ui.about_class.class_success.SuccessClassActivity;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayActivity;
import com.hongyuan.fitness.ui.mall.good_pay.PayDataBean;
import com.hongyuan.fitness.ui.membership_card.vtwo_my_card_list.VtwoMycardStoreList;
import com.hongyuan.fitness.ui.store.MarkTextAdapter;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BigDecimalUtils;
import com.hongyuan.fitness.util.DividerItemDecoration;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class CardDetailsViewModel extends CustomViewModel {

    private ActivityCardDetailsBinding binding;
    private MarkTextAdapter mtAdapter;
    private CardDetailsBean.DataBean detailsBean;

    public CardDetailsViewModel(CustomActivity mActivity, ActivityCardDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
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

    }

    @Override
    protected void setData() {
        //RequestOptions options = new RequestOptions().placeholder(R.mipmap.monthly_card_img_bg).error(R.mipmap.monthly_card_img_bg);
        //Glide.with(mActivity).load(detailsBean.getData().getCard_img()).apply(options).into(binding.cardBg);

        if(detailsBean.getCard_type() == 1){
            binding.cardBg.setImageResource(R.mipmap.store_dan_card);
        }else{
            if(detailsBean.getOsl_id() == 1){
                binding.cardBg.setImageResource(R.mipmap.commont_card);
            }else if(detailsBean.getOsl_id() == 2){
                binding.cardBg.setImageResource(R.mipmap.store_shenji_card);
            }else if(detailsBean.getOsl_id() == 3){
                binding.cardBg.setImageResource(R.mipmap.zhuanshi_card);
            }

        }

        binding.cardName.setText(detailsBean.getCard_name());

        binding.cardUseTime.setText(detailsBean.getCard_days()+"天");
        binding.cardUseStore.setText(detailsBean.getOs_name());
        binding.allPrice.setText(BaseUtil.getNoZoon(detailsBean.getCard_sale_price()));
        if(Double.valueOf(detailsBean.getCard_sale_price()) <= 0){
            binding.payButton.setText("领取会籍卡");
        }
    }

    //减少购买数量
    public BindingCommand subNum = new BindingCommand(() -> {
        int num = Integer.valueOf(binding.cardNum.getText().toString());
        num--;
        if(num < 1){
            num = 1;
        }
        binding.cardNum.setText(String.valueOf(num));
        String showPrice = BigDecimalUtils.mul(detailsBean.getCard_sale_price(),String.valueOf(num),2);
        binding.allPrice.setText(BaseUtil.getNoZoon(showPrice));
    });
    //添加购买数量
    public BindingCommand addNum = new BindingCommand(() -> {
        int num = Integer.valueOf(binding.cardNum.getText().toString());
        num++;
        binding.cardNum.setText(String.valueOf(num));
        String showPrice = BigDecimalUtils.mul(detailsBean.getCard_sale_price(),String.valueOf(num),2);
        binding.allPrice.setText(BaseUtil.getNoZoon(showPrice));
    });
    //去支付生产购买订单
    public BindingCommand goPay = new BindingCommand(() -> {
        addCardOrder();
    });

    /*
     * 会员卡添加购买订单
     * */
    private void addCardOrder(){
        clearParams().setParams("card_id",String.valueOf(detailsBean.getCard_id())).setParams("oc_num",binding.cardNum.getText().toString());
        Controller.myRequest(Constants.ADD_CARD_ORDER,Controller.TYPE_POST,getParams(), OrderBean.class,this);
    }

    /*
     * 读取卡适用的门店列表
     * */
    private void getStoreList(String osl_id){
        clearParams().setParams("osl_id",osl_id).setParams("city_name","湖州市");
        Controller.myRequest(Constants.GET_SUIT_OS_LIST,Controller.TYPE_POST,getParams(), VtwoMycardStoreList.class,this);
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("card_id",getBundle().getString("card_id"));
        Controller.myRequest(Constants.GET_CARD_INFO,Controller.TYPE_POST,getParams(), CardDetailsBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof CardDetailsBean){
            detailsBean = ((CardDetailsBean)data).getData();
            if(detailsBean.getCard_type() != 1){
                getStoreList(String.valueOf(detailsBean.getOsl_id()));
            }else{
                binding.cardUseStore.setText(detailsBean.getOs_name());
            }

            setData();
        }

        if(data instanceof OrderBean){
            OrderBean orderBean = (OrderBean)data;
            if(Double.valueOf(detailsBean.getCard_sale_price()) <= 0){
                Bundle bundle = new Bundle();
                bundle.putString("titleName","领取结果");
                bundle.putString("successText","领取成功！");
                bundle.putString("buttonText","返回");
                startActivity(SuccessClassActivity.class,bundle);
            }else{
                PayDataBean payDataBean = new PayDataBean();
                payDataBean.setO_id(orderBean.getData().getO_id());
                payDataBean.setShowPoint("0");
                payDataBean.setShowPrice(binding.allPrice.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putSerializable("payDataBean",payDataBean);
                startActivity(GoodsPayActivity.class,bundle);
            }

        }

        if(data instanceof VtwoMycardStoreList){
            VtwoMycardStoreList.DataBean storeList = ((VtwoMycardStoreList)data).getData();
            String showStoreText = "";
            if(storeList != null && storeList.getList() != null && storeList.getList().size() > 0){
                for(VtwoMycardStoreList.DataBean.ListBean bean : storeList.getList()){
                    showStoreText += " / "+bean.getOs_name();
                }
            }
            binding.cardUseStore.setText(showStoreText);
        }

    }
}
