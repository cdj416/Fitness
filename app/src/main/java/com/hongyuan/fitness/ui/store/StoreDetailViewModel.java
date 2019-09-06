package com.hongyuan.fitness.ui.store;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.FlowLayoutManager;
import com.hongyuan.fitness.databinding.ActivityStoreDetailBinding;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.PrivateLessonsBean;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsBeans;
import com.hongyuan.fitness.ui.main.main_home.recommend.BoutiqueGroupBean;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;


public class StoreDetailViewModel extends CustomViewModel {

    private ActivityStoreDetailBinding binding;
    private MarkTextAdapter mtAdapter;
    private StoreDetailBean detailBean;

    private String os_id;

    public StoreDetailViewModel(CustomActivity mActivity, ActivityStoreDetailBinding binding) {
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


        //占时用死数据
        //binding.priviteCourse.setPriviteCourse(getData());
        //binding.priviteCourse.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void setData() {
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
        //设置门店设施
        mtAdapter.setNewData(detailBean.getData().getOsf());

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

        //读取门店详情
        clearParams().setParams("os_id",os_id);
        Controller.myRequest(Constants.GET_OFFLINE_STORE_INFO,Controller.TYPE_POST,getParams(), StoreDetailBean.class,this);

        //获取门店的团课
        clearParams().setParams("os_id",os_id).setParams("page","10").setParams("curpage","1");
        Controller.myRequest(Constants.GET_COURSE_SUPER_LIST,Controller.TYPE_POST,getParams(), BoutiqueGroupBean.class,this);

        //读取门店的教练
        clearParams().setParams("os_id",os_id).setParams("page","10").setParams("curpage","1");
        Controller.myRequest(Constants.GET_OS_COACH_LIST,Controller.TYPE_POST,getParams(), PrivateLessonsBean.class,this);

        //课程--私教课列表
        clearParams().setParams("page","5").setParams("curpage","1").setParams("os_ids",os_id);
        Controller.myRequest(Constants.GET_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), VtwoPrivateLessonsBeans.class,this);

        //获取店铺发行的会员卡(目前不需要请求接口)
        //clearParams().setParams("os_id",os_id);
        //Controller.myRequest(Constants.GET_OS_CARD_LIST,Controller.TYPE_POST,getParams(),CardItemBean.class,this);

    }


    @Override
    public void onSuccess(Object data) {

        if(data instanceof StoreDetailBean){
            detailBean = (StoreDetailBean)data;
            //设置汇集卡
            binding.membershipCard.setCardList(detailBean.getData(),os_id);
            setData();
        }

        /*//设置会籍卡
        if(data instanceof CardItemBean){
            CardItemBean cardItemBean = (CardItemBean)data;
            if(cardItemBean.getData() == null ||
                    cardItemBean.getData().getList() == null ||
                    cardItemBean.getData().getList().size() <= 0){
                binding.membershipCard.setVisibility(View.GONE);
            }else{
                binding.membershipCard.setCardList(cardItemBean.getData().getList(),os_id);
            }
        }*/

        //设置精品团课
        if(data instanceof BoutiqueGroupBean){
            BoutiqueGroupBean groupBean = (BoutiqueGroupBean)data;
            if(groupBean.getData() == null ||
                    groupBean.getData().getList() == null ||
                    groupBean.getData().getList().size() <= 0){
                binding.boutiqueGroup.setVisibility(View.GONE);
            }else{
                binding.boutiqueGroup.setBoutiqueGroup(groupBean.getData().getList(),os_id);
            }
        }

        //设置明星教练
        if(data instanceof PrivateLessonsBean){
            PrivateLessonsBean privateLessonsBean = (PrivateLessonsBean)data;

            if(privateLessonsBean.getData() == null ||
                    privateLessonsBean.getData().getList() == null ||
                    privateLessonsBean.getData().getList().size() <= 0){
                binding.starCoach.setVisibility(View.GONE);
            }else{
                binding.starCoach.setStarCoach(privateLessonsBean.getData().getList(),os_id);
            }
        }

        if(data instanceof VtwoPrivateLessonsBeans){
            VtwoPrivateLessonsBeans priviteCourse = (VtwoPrivateLessonsBeans)data;
            if(priviteCourse.getData() == null ||
                    priviteCourse.getData().getList() == null ||
                    priviteCourse.getData().getList().size() <= 0){
                binding.priviteCourse.setVisibility(View.GONE);
            }else{
                binding.priviteCourse.setVisibility(View.VISIBLE);
                binding.priviteCourse.setStorePriviteCourse(priviteCourse.getData().getList());
            }
        }
    }
}
