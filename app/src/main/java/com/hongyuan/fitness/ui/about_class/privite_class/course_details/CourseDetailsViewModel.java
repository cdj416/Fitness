package com.hongyuan.fitness.ui.about_class.privite_class.course_details;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.hongyuan.fitness.custom_view.StickyScrollView;
import com.hongyuan.fitness.custom_view.comm_title.CommentTitleView;
import com.hongyuan.fitness.databinding.ActivtiyCourseDetailsBinding;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachHomePageActivity;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachKongTimeBeans;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CommentAdapter;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CommentBeans;
import com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail.PayOrderDetailActivity;
import com.hongyuan.fitness.ui.webview.WebViewActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.ViewChangeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class CourseDetailsViewModel extends CustomViewModel implements CommentTitleView.ScoreChange, StickyScrollView.ScrollViewListener {
    private ActivtiyCourseDetailsBinding binding;
    private CommentAdapter commentAdapter;
    private CourseDetailsPriceAdapter priceAdapter;
    private SuccessCaseImgAdapter imgAdapter;

    //课程详情
    private CourseDetailsBean detailsBean;

    //评论数据
    private CommentBeans commentBeans;
    //评论分数
    private int coach_s;

    //渐变高度
    private int height;

    public CourseDetailsViewModel(CustomActivity mActivity, ActivtiyCourseDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    //立即购买
    public BindingCommand goPay = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putSerializable("courseDetail",detailsBean.getData());
            startActivity(PayOrderDetailActivity.class,bundle);
        }
    });

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        //价格的适配
        LinearLayoutManager priceManager = new LinearLayoutManager(mActivity);
        priceManager.setOrientation(RecyclerView.VERTICAL);
        binding.priceRec.setLayoutManager(priceManager);
        priceAdapter = new CourseDetailsPriceAdapter();
        binding.priceRec.setAdapter(priceAdapter);

        //成功案例
        LinearLayoutManager imgManager = new LinearLayoutManager(mActivity);
        imgManager.setOrientation(RecyclerView.VERTICAL);
        binding.successCaseImg.setLayoutManager(imgManager);
        imgAdapter = new SuccessCaseImgAdapter();
        binding.successCaseImg.setAdapter(imgAdapter);

        //评论的适配
        LinearLayoutManager manager1 = new LinearLayoutManager(mActivity);
        manager1.setOrientation(RecyclerView.VERTICAL);
        binding.commentRecycler.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.HORIZONTAL_LIST,1,0xffF1F1F1));
        binding.commentRecycler.setLayoutManager(manager1);
        commentAdapter = new CommentAdapter();
        binding.commentRecycler.setAdapter(commentAdapter);

        binding.callTel.setOnClickListener(v -> CustomDialog.callTel(mActivity, detailsBean.getData().getM_mobile(), v1 -> {
            callTel(detailsBean.getData().getM_mobile());
        }));

        binding.coachBox.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("coach_mid",String.valueOf(detailsBean.getData().getM_id()));
                startActivity(CoachHomePageActivity.class,bundle);
            }
        });

        binding.desDetails.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url","http://www.hongyuangood.com/courseNotice/index.html");
            bundle.putString("title","私教课上课须知");
            startActivity(WebViewActivity.class,bundle);
        });

        binding.mainTitle.getRightView().setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                if(detailsBean.getData().getIs_collection() == 1){
                    CustomDialog.promptDialog(mActivity, "确定要取消收藏？", "确定取消", "暂不取消", true, v12 -> {
                        if(v12.getId() == R.id.isOk){
                            setCollect();
                        }
                    });
                }else{
                    setCollect();
                }
            }
        });

        //初始化评论筛选回调函数
        binding.commentTitle.setScoreChange(this);

        //设置滚动监听
        initListeners();
    }

    /*
    * 获取头部图片的高度
    * */
    private void initListeners() {
        ViewTreeObserver vto = binding.headBg.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.titleBox.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = binding.headBg.getHeight();

                binding.mScroll.setScrollViewListener(CourseDetailsViewModel.this);
            }
        });
    }

    @Override
    public void onScrollChanged(StickyScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
            binding.titleBox.setBackgroundColor(Color.argb((int) 0, 239,91,72));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            //binding.titleBox.setTextColor(Color.argb((int) alpha, 255,255,255));
            binding.titleBox.setBackgroundColor(Color.argb((int) alpha, 239,91,72));
        } else {    //滑动到banner下面设置普通颜色
            binding.titleBox.setBackgroundColor(Color.argb( 255, 239,91,72));
        }
    }

    /*
     * 刷新
     * */
    @Override
    public void refreshData(){
        commentBeans = null;
        lazyLoad();
    }

    /*
     * 分页
     * */
    @Override
    protected void loadMoreData() {
        if(coach_s != CommentTitleView.HAVE_IMG_COMMENT){
            //加载教练评论
            getComment();
        }else{
            getHaveImgComment();
        }
    }

    /*
     * 筛选条件的回调
     * */
    @Override
    public void valueChange(int score) {
        curPage = FIRST_PAGE;
        commentBeans = null;
        this.coach_s = score;
        if(coach_s != CommentTitleView.HAVE_IMG_COMMENT){
            //加载教练评论
            getComment();
        }else{
            getHaveImgComment();
        }
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        //获取课程详情
        clearParams().setParams("cp_id",getBundle().getString("cp_id"));
        Controller.myRequest(Constants.GET_COURSE_PRIVITE_INFO,Controller.TYPE_POST,getParams(), CourseDetailsBean.class,this);

        //获取评论数
        clearParams().setParams("cp_id",getBundle().getString("cp_id"));
        Controller.myRequest(Constants.GET_COACH_REVIEW_SUM,Controller.TYPE_POST,getParams(), CommentsNumBeans.class,this);

        if(coach_s != CommentTitleView.HAVE_IMG_COMMENT){
            //加载教练评论
            getComment();
        }else{
            getHaveImgComment();
        }
    }

    /*
    * 读取教练最近有空时间
    * */
    private void getKongTime(String coach_mid){
        //私教--私教教练最近有空时间
        clearParams().setParams("coach_mid",coach_mid);
        Controller.myRequest(Constants.GET_COACH_LAST_KONG_TIME,Controller.TYPE_POST,getParams(), CoachKongTimeBeans.class,this);
    }

    /*
     * 读取私教--评论私教课列表
     * */
    private void getComment(){
        //私教--评论私教课列表
        clearParams().setParams("cp_id",getBundle().getString("cp_id"));
        if(coach_s != CommentTitleView.ALL_COMMENT){
            setParams("coach_s",String.valueOf(coach_s));
        }
        Controller.myRequest(Constants.GET_COACH_REVIEW_LIST,Controller.TYPE_POST,getParams(), CommentBeans.class,this);
    }
    /*
     * 私教--评论私教课列表--有图片
     * */
    private void getHaveImgComment(){
        //私教--评论私教课列表
        clearParams().setParams("cp_id",getBundle().getString("cp_id"));
        Controller.myRequest(Constants.GET_COACH_REVIEW_IMG_LIST,Controller.TYPE_POST,getParams(), CommentBeans.class,this);
    }

    /*
    * 添加/取消 收藏
    * */
    private void setCollect(){
        mActivity.closeLoading();
        if(detailsBean.getData().getIs_collection() == 1){
            clearParams().setParams("out_id",getBundle().getString("cp_id")).setParams("collection_code","cp");
            Controller.myRequest(ConstantsCode.DEL_COLLECTION,Constants.DEL_COLLECTION,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }else{
            clearParams().setParams("id",getBundle().getString("cp_id")).setParams("collection_code","cp");
            Controller.myRequest(ConstantsCode.ADD_COLLECTION,Constants.ADD_COLLECTION,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }

    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof CommentsNumBeans){
            CommentsNumBeans commentsNumBeans = (CommentsNumBeans)data;
            binding.commentTitle.setNum(commentsNumBeans.getData());
            binding.commentAllNum.setText(commentsNumBeans.getData().getAmount()+"条评论>");
        }

        if(data instanceof CoachKongTimeBeans){
            CoachKongTimeBeans timeBeans = (CoachKongTimeBeans)data;
            if(TimeUtil.isToday(timeBeans.getData().getKong_date(),TimeUtil.dateFormatYMDHMS)){
                binding.coachTime.setText("今日可约 "+TimeUtil.formatDate(timeBeans.getData().getKong_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM));
            }else{
                binding.coachTime.setText(TimeUtil.formatDate(timeBeans.getData().getKong_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatMDHM));
            }

        }

        if(data instanceof CourseDetailsBean && isSuccess(data)){
            detailsBean = (CourseDetailsBean)data;
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img).centerCrop();
            Glide.with(mActivity).load(detailsBean.getData().getCp_img()).apply(options).into(binding.headBg);
            RequestOptions optionsHead = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
            Glide.with(mActivity).load(detailsBean.getData().getCoach_head()).apply(optionsHead).into(binding.headImg);

            binding.courseType.setText(detailsBean.getData().getCp_name());
            binding.coachLev.setText(detailsBean.getData().getCp_name());
            binding.coachName.setText(detailsBean.getData().getCoach_nickname());
            binding.buyNum.setText(detailsBean.getData().getCp_num()+"节起售");
            binding.participateNum.setText("参课"+detailsBean.getData().getBnum()+"人");
            binding.courseAddress.setText(detailsBean.getData().getOs_name());
            binding.courseContent.setText(detailsBean.getData().getCp_info());
            binding.coursePeople.setText(detailsBean.getData().getCp_people());
            binding.courseTime.setText(detailsBean.getData().getCp_duration());
            binding.courseSuggest.setText(detailsBean.getData().getCp_suggest());
            binding.selectType.setText(detailsBean.getData().getOs_n() == 1 ? "1对1" : "1对多");

            if(detailsBean.getData().getIs_collection() == 1){
                binding.mainTitle.setRightImage(R.mipmap.orange_collection_mark);
            }else{
                binding.mainTitle.setRightImage(R.mipmap.white_collection_mark);
            }

            //组装数据
            if (detailsBean.getData().getPrice_list() == null || detailsBean.getData().getPrice_list().size() <= 0){
                List<CourseDetailsBean.DataBean.PriceListBean> priceListBeanList = new ArrayList<>();
                CourseDetailsBean.DataBean.PriceListBean priceListBean
                        = new CourseDetailsBean.DataBean.PriceListBean(0,1,100,
                        0,detailsBean.getData().getCp_price());

                priceListBeanList.add(priceListBean);
                priceAdapter.setNewData(priceListBeanList);
            }else{
                priceAdapter.setNewData(detailsBean.getData().getPrice_list());
            }

            //组装成功案例图片
            if(BaseUtil.isValue(detailsBean.getData().getCp_anli_imgs())){
                imgAdapter.setNewData(Arrays.asList(detailsBean.getData().getCp_anli_imgs().split(",")));
            }

            getKongTime(String.valueOf(detailsBean.getData().getM_id()));

            mActivity.closeLoading();
        }

        if(data instanceof CommentBeans){
            CommentBeans pageData = (CommentBeans)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    commentBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    commentBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(commentBeans != null && commentBeans.getData() != null &&
                    commentBeans.getData().getList() != null &&
                    commentBeans.getData().getList().size() > 0){
                commentAdapter.setNewData(commentBeans.getData().getList());
                binding.commentRecycler.setVisibility(View.VISIBLE);
                binding.loadBox.setVisibility(View.GONE);
            }else{
                binding.commentRecycler.setVisibility(View.GONE);
                binding.loadBox.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.DEL_COLLECTION){
            detailsBean.getData().setIs_collection(0);
            binding.mainTitle.setRightImage(R.mipmap.white_collection_mark);
        }
        if(code == ConstantsCode.ADD_COLLECTION){
            detailsBean.getData().setIs_collection(1);
            binding.mainTitle.setRightImage(R.mipmap.orange_collection_mark);
        }
    }
}
