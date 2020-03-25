package com.hongyuan.fitness.ui.store;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.FlowLayoutManager;
import com.hongyuan.fitness.custom_view.HomeRecyclerItemView;
import com.hongyuan.fitness.databinding.ActivityStoreDetailBinding;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.membership_card.v4_mycard_list.V4CardsListActivity;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.ChatPageActivity;
import com.hongyuan.fitness.ui.person.my_coupon.CouponListBeans;
import com.hongyuan.fitness.ui.store.consultant.ConsultantBeans;
import com.hongyuan.fitness.ui.store.store_list.StoreListActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;


public class StoreDetailViewModel extends CustomViewModel implements HomeRecyclerItemView.IntReceiveCoupon {

    private ActivityStoreDetailBinding binding;
    private MarkTextAdapter mtAdapter;
    private StoreDetailBean detailBean;

    private String os_id,osl_id;
    private int receivePosition;
    private List<CouponListBeans.DataBean.ListBean> couponList;

    private List<ConsultantBeans.DataBean.ListBean> consultantList;

    //测试
    private List<String> selectList;

    public StoreDetailViewModel(CustomActivity mActivity, ActivityStoreDetailBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);

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


        //占时用死数据
        //binding.priviteCourse.setPriviteCourse(getData());
        //binding.priviteCourse.setVisibility(View.VISIBLE);

        binding.goClass.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("os_id",os_id);
            bundle.putInt("showPosition",2);
            startActivity(StoreListActivity.class,bundle);
        });
        binding.goCoach.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("os_id",os_id);
            bundle.putInt("showPosition",1);
            startActivity(StoreListActivity.class,bundle);
        });
        binding.goCards.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("os_id",os_id);
            bundle.putString("osl_id",osl_id);
            startActivity(V4CardsListActivity.class,bundle);
        });

        binding.goChat.setOnClickListener(v -> {

            if(selectList != null && selectList.size() > 0){
                CustomDialog.showScollSelect(mActivity, "选择会籍顾问", selectList, selectText -> {
                    int mPosition = getPosition(selectText);

                    HuanXinUtils.getInstance().setBaseData(TokenSingleBean.getInstance().getM_mobile(),TokenSingleBean.getInstance().getHeadUrl()
                            ,consultantList.get(mPosition).getM_name(),consultantList.get(mPosition).getMi_head());
                    Bundle bundle = new Bundle();
                    bundle.putString("title",consultantList.get(mPosition).getM_name());
                    bundle.putString("username",consultantList.get(mPosition).getM_mobile());
                    bundle.putString("lastMsgId",null);
                    startActivity(ChatPageActivity.class,bundle);
                });
            }else{
                CustomDialog.showMessage(mActivity,"暂无会籍顾问！");
            }

        });
        binding.goCall.setOnClickListener(v -> {
            if(selectList != null && selectList.size() > 0) {
                CustomDialog.showScollSelect(mActivity, "选择会籍顾问", selectList, selectText -> {
                    CustomDialog.callTel(mActivity, consultantList.get(getPosition(selectText)).getM_mobile(), v1 -> {
                        callTel(consultantList.get(getPosition(selectText)).getM_mobile());
                    });
                });
            }else{
                CustomDialog.showMessage(mActivity,"暂无会籍顾问！");
            }
        });
    }

    /*
    * 获取选中的顾问所属的对象游标
    * */
    private int getPosition(String selectText){
        for(int i = 0 ; i < consultantList.size() ; i++){
            if(selectText.equals(consultantList.get(i).getM_name())){
                return i;
            }
        }
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void setData() {

        if(BaseUtil.isValue(detailBean.getData().getBanner())){
            //设置轮播图
            binding.banner.setImages(detailBean.getData().getBanner())
                    .setImageLoader(new UseGlideImageLoader())
                    .setDelayTime(3000)
                    .isAutoPlay(true)
                    .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                    .setOnBannerListener(position -> {
                        //点击查看大图功能
                        GPreviewBuilder.from(mActivity)
                                .setData(getInfoList(detailBean.getData().getBanner()))
                                .setCurrentIndex(position)
                                .setType(GPreviewBuilder.IndicatorType.Dot)
                                .start();
                    })
                    .setIndicatorGravity(BannerConfig.CENTER).start();
        }else{
            binding.banner.setVisibility(View.GONE);
        }

        //设置门店设施
        mtAdapter.setNewData(detailBean.getData().getOsf());

        //设置标签
        Glide.with(mActivity).load(detailBean.getData().getOsl_img()).into(binding.storeMark);
        binding.storeName.setText(detailBean.getData().getOs_name());
        binding.storeAddress.setText(detailBean.getData().getOs_address());
        binding.businessHours.setText("周一至周日 "+ TimeUtil.formatDate(detailBean.getData().getOs_start_time(),TimeUtil.dateFormatHMS,TimeUtil.dateFormatHM)+
                "-"+TimeUtil.formatDate(detailBean.getData().getOs_end_time(),TimeUtil.dateFormatHMS,TimeUtil.dateFormatHM));
        binding.callTel.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                CustomDialog.callTel(mActivity, detailBean.getData().getOs_tel(), new CustomDialog.DialogClick() {
                    @SingleClick
                    @Override
                    public void dialogClick(View v) {
                        callTel(detailBean.getData().getOs_tel());
                    }
                });

            }
        });
    }

    /*
     * 获取图片集和图片所处位置
     * */
    private List<UserViewInfo> getInfoList(List<String> list){
        List<UserViewInfo> imgList = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            imgList.add(new UserViewInfo(list.get(i)));
            Rect bounds = new Rect();
            binding.banner.getGlobalVisibleRect(bounds);
            imgList.get(i).setBounds(bounds);
        }

        return imgList;
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        //读取门店详情
        clearParams().setParams("os_id",os_id);
        Controller.myRequest(Constants.GET_OFFLINE_STORE_INFO,Controller.TYPE_POST,getParams(), StoreDetailBean.class,this);

        //查询能领取的优惠价
        clearParams().setParams("os_id",os_id);
        Controller.myRequest(Constants.ALL_COUPON_LIST,Controller.TYPE_POST,getParams(), CouponListBeans.class,this);

        //读取销售顾问
        clearParams().setParams("os_id",os_id);
        Controller.myRequest(Constants.GET_SALER_LIST,Controller.TYPE_POST,getParams(), ConsultantBeans.class,this);
    }


    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof StoreDetailBean){
            detailBean = (StoreDetailBean)data;
            //设置门店等级id
            osl_id = String.valueOf(detailBean.getData().getOsl_id());
            setData();
        }

        //可领取的优惠价
        if(data instanceof CouponListBeans){
           couponList = ((CouponListBeans)data).getData().getList();
            if(couponList != null && couponList.size() > 0){
                binding.storeCoupon.setStoreCoupon(couponList,this);
            }else{
                binding.storeCoupon.setVisibility(View.GONE);
            }
        }

        if(data instanceof ConsultantBeans){
            consultantList = ((ConsultantBeans)data).getData().getList();
            selectList = new ArrayList<>();
            for(ConsultantBeans.DataBean.ListBean bean : consultantList){
                selectList.add(bean.getM_name());
            }
        }

    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.GET_COUPON){
            couponList.get(receivePosition).setReceive(true);
            binding.storeCoupon.setStoreCoupon(couponList,this);
            showSuccess("领取成功！");
        }
    }

    @Override
    public void goReceive(String couponId,int position) {
        receivePosition = position;
        clearParams().setParams("coupon_id",couponId);
        Controller.myRequest(ConstantsCode.GET_COUPON,Constants.GET_COUPON,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }
}
