package com.hongyuan.fitness.ui.membership_card.card_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityCardDetailsBinding;
import com.hongyuan.fitness.ui.about_class.check_payment_method.OrderBean;
import com.hongyuan.fitness.ui.about_class.class_success.SuccessClassActivity;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayActivity;
import com.hongyuan.fitness.ui.mall.good_pay.PayDataBean;
import com.hongyuan.fitness.ui.membership_card.card_detail.add_person.CardAddPersonActivity;
import com.hongyuan.fitness.ui.membership_card.card_detail.add_person.CardAddPersonViewModel;
import com.hongyuan.fitness.ui.membership_card.vtwo_my_card_list.VtwoMycardStoreList;
import com.hongyuan.fitness.ui.person.my_coupon.CouponListBeans;
import com.hongyuan.fitness.ui.person.my_coupon.select_coupon.SelectCouponActivity;
import com.hongyuan.fitness.ui.store.consultant.ConsultantBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class CardDetailsViewModel extends CustomViewModel {

    private ActivityCardDetailsBinding binding;
    private CardDetailsBean.DataBean detailsBean;
    private int couponId;

    private CardAddPersonAdapter addPersonAdapter;
    private List<CardAddPersonBeans> addList = new ArrayList<>();
    private int childNum = 0;
    private int personNum = 0;

    private List<ConsultantBeans.DataBean.ListBean> consultantList;

    //测试
    private List<String> selectList;

    //所选优惠券
    private CouponListBeans.DataBean.ListBean coupon;
    //添加的儿童字符串
    private String childParmas = "";
    //添加的成人字符串
    private String personParmas = "";
    //销售顾问id
    private String saler_id = "";


    public CardDetailsViewModel(CustomActivity mActivity, ActivityCardDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        binding.selectCouponBox.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("couponFor","2");
            bundle.putString("totalMoney",binding.allPrice.getText().toString());
            bundle.putInt("couponId",couponId);
            startActivityForResult(SelectCouponActivity.class,bundle);
        });

        LinearLayoutManager menuManager = new LinearLayoutManager(mActivity);
        menuManager.setOrientation(RecyclerView.VERTICAL);
        binding.personRec.setLayoutManager(menuManager);
        addPersonAdapter = new CardAddPersonAdapter();
        binding.personRec.setAdapter(addPersonAdapter);
        addPersonAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.delImg){
                delBeans(addList.get(position).getName());
            }else{
                Bundle bundle = new Bundle();
                bundle.putInt("showType", addList.get(position).getType());
                bundle.putString("beansId",addList.get(position).getBeansId());
                bundle.putSerializable("beans",addList.get(position));
                startActivityForResult(CardAddPersonActivity.class,bundle);
            }
        });

        binding.addPerson.setOnClickListener(v -> {
            if(personNum < detailsBean.getCc_adult_num()){
                Bundle bundle = new Bundle();
                bundle.putInt("showType", CardAddPersonViewModel.PERSON);
                bundle.putString("beansId","person_"+personNum);
                startActivityForResult(CardAddPersonActivity.class,bundle);
            }else{
                CustomDialog.showMessage(mActivity,"已达到最多人数");
            }

        });
        binding.addChild.setOnClickListener(v -> {

            if(childNum < detailsBean.getCc_children_num()){
                Bundle bundle = new Bundle();
                bundle.putInt("showType", CardAddPersonViewModel.CHILD);
                bundle.putString("beansId","child_"+childNum);
                startActivityForResult(CardAddPersonActivity.class,bundle);
            }else{
                CustomDialog.showMessage(mActivity,"已达到最多人数");
            }
        });

        binding.selectGuWenBox.setOnClickListener(v -> {
            CustomDialog.showScollSelect(mActivity, "选择会籍顾问", selectList, selectText -> {
                binding.selectAdviser.setText(selectText);
                searchSaler(selectText);
            });
        });

    }

    /*
    * 查询销售id
    * */
    private void searchSaler(String selectText){
        for(int i = 0 ; i < consultantList.size() ; i++){
            if(consultantList.get(i).getM_name().equals(selectText)){
                saler_id = String.valueOf(consultantList.get(i).getM_id());
                return;
            }
        }
    }

    /*
    * 删除
    * */
    private void delBeans(String name){
        for(int i = 0 ; i < addList.size() ; i++){
            if(addList.get(i).getName().equals(name)){
                if(addList.get(i).getType() == CardAddPersonViewModel.PERSON){
                    personNum--;
                }else{
                    childNum--;
                }
                addList.remove(i);
                break;
            }
        }
        addPersonAdapter.setNewData(addList);
    }

    /*
     * 查询是否有相应的对象
     * */
    private int checkBeans(String beansId){
        for(int i = 0 ; i < addList.size() ; i++){
            if(addList.get(i).getBeansId().equals(beansId)){
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void forResult(Bundle bundle) {

        if(bundle.getSerializable("coupon") instanceof CouponListBeans.DataBean.ListBean){
            coupon = (CouponListBeans.DataBean.ListBean) bundle.getSerializable("coupon");
            if(BaseUtil.isValue(coupon)){
                couponId = coupon.getCoupon_id();
                binding.selectCoupon.setText(coupon.getCoupon_name());
            }else{
                binding.selectCoupon.setText("请选择优惠券");
            }
        }

        if(bundle.getSerializable("addPerson") instanceof CardAddPersonBeans){
            CardAddPersonBeans addBeans = (CardAddPersonBeans) bundle.getSerializable("addPerson");

            if(checkBeans(addBeans.getBeansId()) != -1){
                addList.set(checkBeans(addBeans.getBeansId()),addBeans);
            }else{
                if(addBeans.getType() == CardAddPersonViewModel.CHILD){
                    childNum++;
                }else{
                    personNum++;
                }
                addList.add(addBeans);
            }
            addPersonAdapter.setNewData(addList);
        }

    }

    @Override
    protected void setData() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img);
        Glide.with(mActivity).load(detailsBean.getC_img()).apply(options).into(binding.cardBg);
        binding.cardName.setText(detailsBean.getCard_name());

        binding.cardUseTime.setText(detailsBean.getCard_days()+"天");
        binding.allPrice.setText(BaseUtil.getNoZoon(detailsBean.getCard_sale_price()));
        binding.cardPrice.setText(BaseUtil.getNoZoon(detailsBean.getCard_sale_price()));

        if(Double.valueOf(detailsBean.getCard_sale_price()) <= 0){
            binding.payButton.setText("领取会籍卡");
        }

        if(detailsBean.getCc_adult_num() > 0){
            binding.addPerson.setVisibility(View.VISIBLE);
            binding.addBox.setVisibility(View.VISIBLE);
        }
        if(detailsBean.getCc_children_num() > 0){
            binding.addChild.setVisibility(View.VISIBLE);
            binding.addBox.setVisibility(View.VISIBLE);
        }
    }

    //去支付生产购买订单
    public BindingCommand goPay = new BindingCommand(() -> {
        addCardOrder();
    });

    /*
     * 会员卡添加购买订单
     * */
    private void addCardOrder(){
        //生产添加的人数数据
        setPersonData();

        clearParams().setParams("card_id",String.valueOf(detailsBean.getCard_id()))
                .setParams("oc_num","1");
        if(coupon != null){
            setParams("cm_id",String.valueOf(coupon.getCm_id()));
        }
        if(BaseUtil.isValue(childParmas) && childParmas.length() > 2){
            setParams("children_names",childParmas.substring(0,childParmas.length() -1));
        }
        if(BaseUtil.isValue(personParmas) && personParmas.length() > 2){
            setParams("adult_mobiles",personParmas.substring(0,personParmas.length() -1));
        }
        if(BaseUtil.isValue(saler_id)){
            setParams("saler_id",saler_id);
        }
        Controller.myRequest(Constants.ADD_CARD_ORDER,Controller.TYPE_POST,getParams(), OrderBean.class,this);
    }

    /*
    * 组装添加的人的数据
    * */
    private void setPersonData(){
        childParmas = "";
        personParmas = "";
        for(int i = 0 ; i<addList.size() ; i++){
            if(addList.get(i).getType() == CardAddPersonViewModel.CHILD){
                childParmas+= addList.get(i).getName()+","+addList.get(i).getPhoneOrDays()+";";
            }else{
                personParmas+= addList.get(i).getName()+","+addList.get(i).getPhoneOrDays()+";";
            }
        }
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

        //读取销售顾问
        clearParams().setParams("os_id",getBundle().getString("os_id"));
        Controller.myRequest(Constants.GET_SALER_LIST,Controller.TYPE_POST,getParams(), ConsultantBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof CardDetailsBean){
            detailsBean = ((CardDetailsBean)data).getData();
            if(detailsBean.getCard_type() != 1){
                getStoreList(String.valueOf(detailsBean.getOsl_id()));
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

        if(data instanceof ConsultantBeans){
            consultantList = ((ConsultantBeans)data).getData().getList();
            selectList = new ArrayList<>();
            for(ConsultantBeans.DataBean.ListBean bean : consultantList){
                selectList.add(bean.getM_name());
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
        }

    }
}
