package com.hongyuan.fitness.ui.about_class.coach.coach_homepage;

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
import com.hongyuan.fitness.databinding.ActivityCoachHomepageBinding;
import com.hongyuan.fitness.ui.about_class.coach.cocah_img.CoachImgActivity;
import com.hongyuan.fitness.ui.about_class.coach.show_select_time.ShowSelectTimeActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CommentsNumBeans;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.course_list.CourseListActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.course_list.CouseListBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.SkinConstants;
import com.hongyuan.fitness.util.StatusBarUtil;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.ViewChangeUtil;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class CoachHomePageViewModel extends CustomViewModel implements CommentTitleView.ScoreChange, StickyScrollView.ScrollViewListener {
    private ActivityCoachHomepageBinding binding;
    private CoachHomePageCouseListAdapter adapter;
    private CommentAdapter commentAdapter;
    //教练数据实体类
    private CoachHomeBean coachHomeBean;
    //课程实体类
    private CouseListBean listBean;
    //教练id
    private String coach_mid;

    //评论数据
    private CommentBeans commentBeans;
    //评论分数
    private int coach_s;

    //渐变高度
    private int height;

    public CoachHomePageViewModel(CustomActivity mActivity, ActivityCoachHomepageBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        coach_mid = getBundle().getString("coach_mid");


        //教练的全部课程
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.mRecycler.setLayoutManager(layoutManager);
        adapter = new CoachHomePageCouseListAdapter();
        binding.mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("cp_id",String.valueOf(listBean.getData().getList().get(position).getCp_id()));
            startActivity(CourseDetailsActivity.class,bundle);
        });

        //评论的适配
        LinearLayoutManager manager1 = new LinearLayoutManager(mActivity);
        manager1.setOrientation(RecyclerView.VERTICAL);
        binding.commentRecycler.setLayoutManager(manager1);
        commentAdapter = new CommentAdapter();
        binding.commentRecycler.setAdapter(commentAdapter);

        binding.showSelectTime.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("jl_mid",String.valueOf(coachHomeBean.getData().getInfo().getM_id()));
            startActivity(ShowSelectTimeActivity.class,bundle);
        });

        //拨打电话
        binding.callTel.setOnClickListener(v -> {
            if(!BaseUtil.isValue(coachHomeBean)){
                return;
            }
            CustomDialog.callTel(mActivity, coachHomeBean.getData().getInfo().getM_mobile(), v1 -> {
                callTel(coachHomeBean.getData().getInfo().getM_mobile());
            });
        });

        binding.myTitle.getRightView().setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                if(coachHomeBean.getData().getIs_collection() == 1){
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

        //标题渐变
        initListeners();
    }

    /*
     * 获取头部图片的高度
     * */
    private void initListeners() {
        ViewTreeObserver vto = binding.bgImg.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.titleBox.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = binding.bgImg.getHeight();

                binding.nScroll.setScrollViewListener(CoachHomePageViewModel.this);
            }
        });
    }

    @Override
    public void onScrollChanged(StickyScrollView scrollView, int x, int y, int oldx, int oldy) {
        /*if (y <= 0) {   //设置标题的背景颜色
            binding.titleBox.setBackgroundColor(Color.argb((int) 0, 99,130,236));
        } else if (y > 0 && y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
            //binding.titleBox.setTextColor(Color.argb((int) alpha, 255,255,255));
            binding.titleBox.setBackgroundColor(Color.argb((int) alpha, 99,130,236));
        } else {
            binding.titleBox.setBackgroundColor(Color.argb( 255, 99,130,236));
        }*/


        if (y <= 0) {   //设置标题的背景颜色
            binding.myTitle.setCenterTextColor("教练主页",mActivity.getResources().getColor(R.color.color_FFFFFF));
            binding.myTitle.setRightImage(R.mipmap.white_collection_mark);
            binding.myTitle.setLeftImage(R.mipmap.white_common);
            if(mActivity.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                binding.titleBox.setBackgroundColor(Color.argb((int) 0, 51,51,51));
            }else if(mActivity.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                binding.titleBox.setBackgroundColor(Color.argb((int) 0, 255,255,255));
            }
        } else if (y > 0 && y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);

            if(mActivity.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                binding.titleBox.setBackgroundColor(Color.argb((int) alpha, 51,51,51));
            }else if(mActivity.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                binding.titleBox.setBackgroundColor(Color.argb((int) alpha, 255,255,255));
                if(y <= height/2){
                    binding.myTitle.setRightImage(R.mipmap.white_collection_mark);
                    binding.myTitle.setCenterTextColor("教练主页",mActivity.getResources().getColor(R.color.color_FFFFFF));
                    binding.myTitle.setLeftImage(R.mipmap.white_common);
                    StatusBarUtil.setCommonUI(mActivity,false);
                    binding.myTitle.hideLine();
                }else{
                    binding.myTitle.setRightImage(R.mipmap.gray_collection_img);
                    binding.myTitle.setCenterTextColor("教练主页",mActivity.getResources().getColor(R.color.color_FF333333));
                    binding.myTitle.setLeftImage(R.mipmap.theme_left_img);
                    StatusBarUtil.setCommonUI(mActivity,true);
                    binding.myTitle.showLine();
                }
            }
        } else {
            if(mActivity.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                binding.titleBox.setBackgroundColor(Color.argb((int) 255, 51,51,51));
            }else if(mActivity.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                binding.titleBox.setBackgroundColor(Color.argb( 255, 255,255,255));
                binding.myTitle.setRightImage(R.mipmap.gray_collection_img);
                binding.myTitle.setCenterTextColor("教练主页",mActivity.getResources().getColor(R.color.color_FF333333));
                binding.myTitle.setLeftImage(R.mipmap.theme_left_img);
            }

        }
    }

    //查看更多课程
    public BindingCommand moreClass = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("jl_mid",String.valueOf(coach_mid));
            startActivity(CourseListActivity.class,bundle);
        }
    });
    //查看教练简介
    public BindingCommand coachProfile = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("jl_mid",String.valueOf(coach_mid));
            startActivity(CourseListActivity.class,bundle);
        }
    });


    @Override
    protected void lazyLoad() {
        commentBeans = null;
        //私教--私教教练最近有空时间
        clearParams().setParams("coach_mid",coach_mid);
        Controller.myRequest(Constants.GET_COACH_LAST_KONG_TIME,Controller.TYPE_POST,getParams(), CoachKongTimeBeans.class,this);

        //加载教练信息
        clearParams().setParams("coach_mid",coach_mid);
        Controller.myRequest(Constants.GET_COACH_INDEX_INFO,Controller.TYPE_POST,getParams(), CoachHomeBean.class,this);

        //加载私教课
        clearParams().setParams("jl_mid", coach_mid).setParams("page","10")
                .setParams("curpage","1");
        Controller.myRequest(Constants.GET_COACH_COURSE_PRIVITE,Controller.TYPE_POST,getParams(), CouseListBean.class,this);

        //获取评论数
        clearParams().setParams("coach_mid",coach_mid);
        Controller.myRequest(Constants.GET_COACH_REVIEW_SUM,Controller.TYPE_POST,getParams(), CommentsNumBeans.class,this);

        if(coach_s != CommentTitleView.HAVE_IMG_COMMENT){
            //加载教练评论
            getComment();
        }else{
            getHaveImgComment();
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

    /*
    * 读取私教--评论私教课列表
    * */
    private void getComment(){
        //私教--评论私教课列表
        clearParams().setParams("coach_mid",coach_mid);
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
        clearParams().setParams("coach_mid",coach_mid);
        Controller.myRequest(Constants.GET_COACH_REVIEW_IMG_LIST,Controller.TYPE_POST,getParams(), CommentBeans.class,this);
    }

    /*
     * 添加/取消 收藏
     * */
    private void setCollect(){
        mActivity.closeLoading();
        if(coachHomeBean.getData().getIs_collection() == 1){
            clearParams().setParams("out_id",coach_mid).setParams("collection_code","coach");
            Controller.myRequest(ConstantsCode.DEL_COLLECTION,Constants.DEL_COLLECTION,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }else{
            clearParams().setParams("id",coach_mid).setParams("collection_code","coach");
            Controller.myRequest(ConstantsCode.ADD_COLLECTION,Constants.ADD_COLLECTION,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }

    }

    @Override
    protected void setData() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mActivity).load(coachHomeBean.getData().getInfo().getCoach_head()).apply(options).into(binding.headImg);

        binding.coachProfile.setData(coachHomeBean.getData());
        binding.storeName.setText(coachHomeBean.getData().getInfo().getOs_name());
        binding.coachName.setText(coachHomeBean.getData().getInfo().getCoach_nickname());
        binding.coachGrade.setText("私教专业P"+coachHomeBean.getData().getInfo().getCoach_level());
        binding.upClassNum.setText(BaseUtil.getNoZoon(coachHomeBean.getData().getCount_c()));
        binding.evaluationNum.setText(BaseUtil.getNoZoon(coachHomeBean.getData().getInfo().getCoach_s()));
        //binding.myRat.setNumStars(5);
        //binding.myRat.setRating(5);

        if(coachHomeBean.getData().getInfo().getMi_sex() == 1){
            ViewChangeUtil.changeRightDrawable(mActivity,binding.coachName,R.mipmap.person_boby_mark_img);
        }else{
            ViewChangeUtil.changeRightDrawable(mActivity,binding.coachName,R.mipmap.person_girl_mark_img);
        }

        if(coachHomeBean.getData().getIs_collection() == 1){
            binding.myTitle.setRightImage(R.mipmap.orange_collection_mark);
        }else{
            binding.myTitle.setRightImage(R.mipmap.white_collection_mark);
        }

        setPhoto(coachHomeBean);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof CommentsNumBeans){
            CommentsNumBeans commentsNumBeans = (CommentsNumBeans)data;
            binding.commentTitle.setNum(commentsNumBeans.getData());
        }

        if(data instanceof CoachHomeBean){
            coachHomeBean = (CoachHomeBean)data;

            setData();
        }

        if(data instanceof CouseListBean && isSuccess(data)){
            listBean = (CouseListBean)data;
            adapter.setNewData(listBean.getData().getList());
        }

        if(data instanceof CoachKongTimeBeans){
            CoachKongTimeBeans timeBeans = (CoachKongTimeBeans)data;
            if(TimeUtil.isToday(timeBeans.getData().getKong_date(),TimeUtil.dateFormatYMDHMS)){
                binding.coachTime.setText("今日可约 "+TimeUtil.formatDate(timeBeans.getData().getKong_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM));
            }else{
                binding.coachTime.setText(TimeUtil.formatDate(timeBeans.getData().getKong_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatMDHM));
            }
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
        super.onSuccess(code,data);

        if(code == ConstantsCode.DEL_COLLECTION){
            coachHomeBean.getData().setIs_collection(0);
            binding.myTitle.setRightImage(R.mipmap.white_collection_mark);
        }
        if(code == ConstantsCode.ADD_COLLECTION){
            coachHomeBean.getData().setIs_collection(1);
            binding.myTitle.setRightImage(R.mipmap.orange_collection_mark);
        }
    }

    /*
    * 设置相册数据
    * */
    private void setPhoto(CoachHomeBean coachHomeBean){
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.yueka_bg).error(R.mipmap.yueka_bg).centerCrop();

        if(coachHomeBean.getData().getC_photo1() != null && coachHomeBean.getData().getC_photo1().getImg_src() != null){
            Glide.with(mActivity).load(coachHomeBean.getData().getC_photo1().getImg_src()).apply(options).into(binding.coachStyle);
        }
        if(coachHomeBean.getData().getC_photo2() != null && coachHomeBean.getData().getC_photo2().getImg_src() != null){
            Glide.with(mActivity).load(coachHomeBean.getData().getC_photo2().getImg_src()).apply(options).into(binding.successCase);

        }
        if(coachHomeBean.getData().getC_photo3() != null && coachHomeBean.getData().getC_photo3().getImg_src() != null){
            Glide.with(mActivity).load(coachHomeBean.getData().getC_photo3().getImg_src()).apply(options).into(binding.certificateImg);
        }

        binding.styleBox.setOnClickListener(v -> {
            if(coachHomeBean.getData().getC_photo1() != null && coachHomeBean.getData().getC_photo1().getImg_src() != null){
                Bundle bundle = new Bundle();
                bundle.putString("title","教练风采");
                bundle.putString("coach_mid",String.valueOf(coachHomeBean.getData().getInfo().getM_id()));
                bundle.putString("photo_category_id","1");
                startActivity(CoachImgActivity.class,bundle);
            }else{
                onError(1,"暂无详细照片！");
            }
        });

        binding.caseBox.setOnClickListener(v -> {
            if(coachHomeBean.getData().getC_photo2() != null && coachHomeBean.getData().getC_photo2().getImg_src() != null){
                Bundle bundle = new Bundle();
                bundle.putString("title","成功案例");
                bundle.putString("coach_mid",String.valueOf(coachHomeBean.getData().getInfo().getM_id()));
                bundle.putString("photo_category_id","2");
                startActivity(CoachImgActivity.class,bundle);
            }else{
                onError(1,"暂无详细照片！");
            }
        });

        binding.certificateBox.setOnClickListener(v -> {
            if(coachHomeBean.getData().getC_photo3() != null && coachHomeBean.getData().getC_photo3().getImg_src() != null){
                Bundle bundle = new Bundle();
                bundle.putString("title","专业证书");
                bundle.putString("coach_mid",String.valueOf(coachHomeBean.getData().getInfo().getM_id()));
                bundle.putString("photo_category_id","3");
                startActivity(CoachImgActivity.class,bundle);
            }else{
                onError(1,"暂无详细照片！");
            }

        });
    }

}
