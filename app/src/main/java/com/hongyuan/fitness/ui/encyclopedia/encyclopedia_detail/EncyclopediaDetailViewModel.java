package com.hongyuan.fitness.ui.encyclopedia.encyclopedia_detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.KeyboardLayout;
import com.hongyuan.fitness.databinding.ActivityEncyclopediaTestDetailBinding;
import com.hongyuan.fitness.ui.encyclopedia.EncyclopediaAdapter;
import com.hongyuan.fitness.ui.encyclopedia.EncyclopediaBean;
import com.hongyuan.fitness.ui.encyclopedia.encyclopedia_comment_detail.EncyclopediaCommentDetailsActivity;
import com.hongyuan.fitness.ui.encyclopedia.encyclopedia_comment_detail.EncyclopediaLikeBean;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.ViewChangeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

import static me.goldze.mvvmhabit.utils.Utils.getContext;

public class EncyclopediaDetailViewModel extends CustomViewModel implements View.OnClickListener, KeyboardLayout.onKeyboaddsChangeListener,
        EncyclopediatDetailsCommentAdapter.ReturnClick {

    private ActivityEncyclopediaTestDetailBinding binding;
    private String baike_id;
    private EncyclopediaDetailBean detailBean;
    private EncyclopediaAdapter tuijianAdapter;
    private EncyclopediatDetailsCommentAdapter commentAdapter;
    private EncyclopediaDetailCommentBean commentBean;

    private String br_id_father = "0";//当前帖子的id
    private String first_br_id = "0";//当前帖子的父类id

    public EncyclopediaDetailViewModel(CustomActivity mActivity, ActivityEncyclopediaTestDetailBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        //开启刷新
        setEnableRefresh(true);
        //开启加载更多
        setEnableLoadMore(true);

        baike_id = getBundle().getString("baike_id");
        //推荐的listview
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        tuijianAdapter = new EncyclopediaAdapter();
        binding.mRecycler.setAdapter(tuijianAdapter);


        //评论
        LinearLayoutManager commentManager = new LinearLayoutManager(mActivity);
        commentManager.setOrientation(RecyclerView.VERTICAL);
        binding.mComment.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,0xFFEEEEEE));
        binding.mComment.setLayoutManager(commentManager);
        commentAdapter = new EncyclopediatDetailsCommentAdapter(this);
        binding.mComment.setAdapter(commentAdapter);

        commentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.attention){
                    sendLike(commentBean.getData().getList().get(position).getIs_praise(),
                            commentBean.getData().getList().get(position).getBr_id());
                }else if(view.getId() == R.id.commentNum){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("brBin",commentBean.getData().getList().get(position));
                    startActivity(EncyclopediaCommentDetailsActivity.class,bundle);
                }else{
                    br_id_father = String.valueOf(commentBean.getData().getList().get(position).getBr_id());
                    first_br_id = String.valueOf(commentBean.getData().getList().get(position).getBr_id());
                    showEditext(binding.editText);
                }
            }
        });

        binding.outBox.setOnkbdStateListener(this);
    }

    //打开软键盘输入评论
    public BindingCommand showEditext = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            br_id_father = "0";
            first_br_id = "0";
            showEditext(binding.editText);
        }
    });
    //关闭软键盘输入
    public BindingCommand closeEidt = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            br_id_father = "0";
            first_br_id = "0";
            hideEditext(binding.editText);
        }
    });
    //百科点赞
    public BindingCommand baikeLike = new BindingCommand(() -> getBaikeLike());
    //提交评论
    public BindingCommand sendMessage = new BindingCommand(() -> sendComment());

    /*
    * 百科点赞/取消点赞
    * */
    private void getBaikeLike(){
        clearParams().setParams("baike_id",String.valueOf(baike_id));
        if(detailBean.getData().getIs_praise() == 0){
            Controller.myRequest(Constants.ADD_BAIKE_PRAISE,Controller.TYPE_POST,getParams(), EncyclopediaDetailLikeBean.class,this);
        }else{
            Controller.myRequest(Constants.CANCEL_BAIKE_PRAISE,Controller.TYPE_POST,getParams(), EncyclopediaDetailLikeBean.class,this);
        }
    }

    /*
     * 提交评论信息
     * */
    private void sendComment(){
        if(!isValue(binding.editText.getText().toString())){
            onError(1,"请求输入评论内容！");
            return;
        }

        clearParams().setParams("baike_id",baike_id).setParams("br_id_father",br_id_father)
                .setParams("br_content",binding.editText.getText().toString())
                .setParams("first_br_id",first_br_id);
        Controller.myRequest(Constants.ADD_BAIKE_REVIEW,Controller.TYPE_POST,getParams(), EncyclopediaDetailSendMeassageBean.class,this);
    }

    @Override
    public void refreshData(){
        commentBean = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        getComment();
    }

    @Override
    protected void lazyLoad() {
        //获取推荐数据
        clearParams().setParams("page","5").setParams("curpage","1");
        Controller.myRequest(Constants.GET_BAIKE_LIST_TJ,Controller.TYPE_POST,getParams(), EncyclopediaBean.class,this);

        getComment();
        getBaikeData();
    }
    /*
    * 获取百科基本信息
    * */
    private void getBaikeData(){
        clearParams().setParams("baike_id",baike_id);
        Controller.myRequest(Constants.GET_BAIKE_INFO,Controller.TYPE_POST,getParams(), EncyclopediaDetailBean.class,this);
    }

    /*
    * 获取百科评论列表
    * */
    private void getComment(){
        //读取圈子帖子评论列表
        clearParams().setParams("baike_id",baike_id);
        Controller.myRequest(Constants.GET_BAIKE_REVIEWLIST,Controller.TYPE_POST,getParams(), EncyclopediaDetailCommentBean.class,this);
    }

    /*
     * 点赞/取消点赞
     * */
    private void sendLike(int is_praise,int br_id){
        clearParams().setParams("br_id",String.valueOf(br_id));
        if(is_praise == 0){
            Controller.myRequest(Constants.ADD_BAIKE_REVIEW_PRAISE,Controller.TYPE_POST,getParams(), EncyclopediaLikeBean.class,this);
        }else{
            Controller.myRequest(Constants.CANCEL_BAIKE_REVIEW_PRAISE,Controller.TYPE_POST,getParams(), EncyclopediaLikeBean.class,this);
        }
    }

    /*
    * 赋值
    * */
    private void setData(EncyclopediaDetailBean detailBean){

        //给视频赋值
        if(detailBean.getData().getBaike_type() == 2){
            binding.myVideo.setVisibility(View.VISIBLE);
            binding.myVideo.setMyVideo(detailBean.getData().getBi().get(0).getFile_src(),
                    detailBean.getData().getBaike_img());

            binding.ftName.setText("#"+detailBean.getData().getFt_name()+"/视频");
        }else{
            binding.myVideo.setVisibility(View.GONE);
            binding.ftName.setText("#"+detailBean.getData().getFt_name()+"/图文");
            //图片赋值
            if(detailBean.getData().getBi() != null && detailBean.getData().getBi().size() > 0){
                binding.nineGridImg.setVisibility(View.VISIBLE);
                binding.nineGridImg.setAdapter(new EncNineImgAdapter());
                binding.nineGridImg.setImagesData(detailBean.getData().getBi());
            }
        }

        binding.baikeName.setText(detailBean.getData().getBaike_name());
        binding.baikeContent.setText(detailBean.getData().getBaike_content());
        binding.tvLike.setText(String.valueOf(detailBean.getData().getPraise_num()));
        binding.tvComment.setText(String.valueOf(detailBean.getData().getReview_num()));

        if(detailBean.getData().getIs_praise() == 0){
            ViewChangeUtil.changeLeftDrawable(mActivity,binding.tvLike,R.mipmap.like_huise_img);
        }else{
            ViewChangeUtil.changeLeftDrawable(mActivity,binding.tvLike,R.mipmap.like_chengse_img);
        }
    }

    @Override
    public void onSuccess(Object data) {
        if (data instanceof EncyclopediaDetailBean && isSuccess(data)){
            detailBean = (EncyclopediaDetailBean)data;
            setData(detailBean);
        }

        if(data instanceof EncyclopediaBean && isSuccess(data)){
            EncyclopediaBean diaBean = (EncyclopediaBean)data;
            tuijianAdapter.setNewData(diaBean.getData().getList());
        }

        if(data instanceof EncyclopediaDetailCommentBean && isSuccess(data)){
            EncyclopediaDetailCommentBean pageData = (EncyclopediaDetailCommentBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    commentBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    commentBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(commentBean != null && commentBean.getData() != null &&
                    commentBean.getData().getList() != null &&
                    commentBean.getData().getList().size() > 0){
                commentAdapter.setNewData(commentBean.getData().getList());
                //mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                //mActivity.setPromtView( mActivity.SHOW_EMPTY);
            }
        }

        if(data instanceof EncyclopediaDetailSendMeassageBean && isSuccess(data)){
            //提交成功隐藏软键盘
            hideEditext(binding.editText);
            //刷新下评论
            getComment();
        }

        if(data instanceof EncyclopediaLikeBean && isSuccess(data)){
            //刷新下评论
            getComment();
        }

        if(data instanceof EncyclopediaDetailLikeBean && isSuccess(data)){
            //刷新百科信息
            getBaikeData();
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onKeyBoardStateChange(int state) {
        switch (state) {
            case KeyboardLayout.KEYBOARD_STATE_HIDE:
                br_id_father = "0";
                first_br_id = "0";
                binding.editText.setText("");
                binding.mengCheng.setVisibility(View.GONE);
                break;
            case KeyboardLayout.KEYBOARD_STATE_SHOW:
                binding.mengCheng.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void returnClick(int partentPosition, int position) {
        br_id_father = String.valueOf(commentBean.getData().getList().get(partentPosition).getList_s().get(position).getBr_id());
        first_br_id = String.valueOf(commentBean.getData().getList().get(partentPosition).getBr_id());
        //打开输入框
        showEditext(binding.editText);
    }
}
