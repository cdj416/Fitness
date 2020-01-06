package com.hongyuan.fitness.ui.encyclopedia.encyclopedia_detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.KeyboardLayout;
import com.hongyuan.fitness.databinding.ActivityEncyclopediaTestDetailBinding;
import com.hongyuan.fitness.ui.encyclopedia.encyclopedia_comment_detail.EncyclopediaCommentDetailsActivity;
import com.hongyuan.fitness.ui.encyclopedia.vthird_change.BaikeTJBeans;
import com.hongyuan.fitness.ui.encyclopedia.vthird_change.V3BaiKeDetialCommentLikeBean;
import com.hongyuan.fitness.ui.encyclopedia.vthird_change.V3BaikeDetialLikeBean;
import com.hongyuan.fitness.ui.encyclopedia.vthird_change.V3CommentBeans;
import com.hongyuan.fitness.ui.encyclopedia.vthird_change.V3EncyclopediaDetailBean;
import com.hongyuan.fitness.ui.encyclopedia.vthird_change.V3SendMeassage;
import com.hongyuan.fitness.ui.encyclopedia.vthird_change.V3TuiJianAdapter;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.ViewChangeUtil;
import com.luck.picture.lib.tools.ScreenUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

import static me.goldze.mvvmhabit.utils.Utils.getContext;

public class EncyclopediaDetailViewModel extends CustomViewModel implements View.OnClickListener, KeyboardLayout.onKeyboaddsChangeListener,
        EncyclopediatDetailsCommentAdapter.ReturnClick {

    private ActivityEncyclopediaTestDetailBinding binding;
    private V3EncyclopediaDetailBean detailBean;
    private V3TuiJianAdapter tuijianAdapter;
    private EncyclopediatDetailsCommentAdapter commentAdapter;
    private V3CommentBeans commentBean;

    private String br_id_father = "0";//当前帖子的id
    private String first_br_id = "0";//当前帖子的父类id

    //记录当前点赞的评论下标
    private int commentPositon;

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

        //推荐的listview
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        tuijianAdapter = new V3TuiJianAdapter();
        binding.mRecycler.setAdapter(tuijianAdapter);

        tuijianAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("article_id",String.valueOf(commentBean.getData().getList().get(position).getArticle_id()));
                startActivity(EncyclopediaDetailActivity.class,bundle);
            }
        });


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
                    commentPositon = position;
                    sendLike(commentBean.getData().getList().get(position).getIs_praise(),
                            commentBean.getData().getList().get(position).getAr_id());
                }else if(view.getId() == R.id.commentNum){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("brBin",commentBean.getData().getList().get(position));
                    startActivity(EncyclopediaCommentDetailsActivity.class,bundle);
                }else{
                    br_id_father = String.valueOf(commentBean.getData().getList().get(position).getAr_id());
                    first_br_id = String.valueOf(commentBean.getData().getList().get(position).getAr_id());
                    showEditext(binding.editText);
                }
            }
        });

        binding.myCollection.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                if(detailBean.getData().getIs_collection() == 1){
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
        mActivity.showLoading();
        clearParams().setParams("article_id",getBundle().getString("article_id"));
        if(detailBean.getData().getIs_praise() == 0){
            Controller.myRequest(Constants.ADD_ARTICLE_PRAISE,Controller.TYPE_POST,getParams(), V3BaikeDetialLikeBean.class,this);
        }else{
            Controller.myRequest(Constants.CANCEL_ARTICLE_PRAISE,Controller.TYPE_POST,getParams(), V3BaikeDetialLikeBean.class,this);
        }
    }

    /*
     * 提交评论信息
     * */
    private void sendComment(){
        mActivity.showLoading();
        if(!isValue(binding.editText.getText().toString())){
            onError(1,"请求输入评论内容！");
            return;
        }

        clearParams().setParams("article_id",getBundle().getString("article_id")).setParams("ar_id_father",br_id_father)
                .setParams("ar_content",binding.editText.getText().toString())
                .setParams("first_ar_id",first_br_id);
        Controller.myRequest(Constants.ADD_ARTICLE_REVIEW,Controller.TYPE_POST,getParams(), V3SendMeassage.class,this);
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
        getComment();
        getBaikeData();
    }

    /*
    * 获取推荐数据
    * */
    private void getTuiJian(String baike_categoryid){
        clearParams();
        Map<String,String> parms = getParams();
        //获取推荐数据
        parms.put("page","3");
        parms.put("curpage","1");
        parms.put("baike_categoryid",baike_categoryid);
        Controller.myRequest(Constants.GET_ARTICLE_LIST_TJ,Controller.TYPE_POST,parms, BaikeTJBeans.class,this);
    }

    /*
    * 获取百科基本信息
    * */
    private void getBaikeData(){
        clearParams().setParams("article_id",getBundle().getString("article_id"));
        Controller.myRequest(Constants.GET_ARTICLE_INFO,Controller.TYPE_POST,getParams(), V3EncyclopediaDetailBean.class,this);
    }

    /*
    * 获取百科评论列表
    * */
    private void getComment(){
        //读取圈子帖子评论列表
        clearParams().setParams("article_id",getBundle().getString("article_id"));
        Controller.myRequest(Constants.GET_ARTICLE_REVIEWLIST,Controller.TYPE_POST,getParams(), V3CommentBeans.class,this);
    }

    /*
     * 点赞/取消点赞
     * */
    private void sendLike(int is_praise,int ar_id){
        mActivity.showLoading();
        clearParams().setParams("ar_id",String.valueOf(ar_id));
        if(is_praise == 0){
            Controller.myRequest(Constants.ADD_ARTICLE_REVIEW_PRAISE,Controller.TYPE_POST,getParams(), V3BaiKeDetialCommentLikeBean.class,this);
        }else{
            Controller.myRequest(Constants.CANCEL_ARTICLE_REVIEW_PRAISE,Controller.TYPE_POST,getParams(), V3BaiKeDetialCommentLikeBean.class,this);
        }
    }

    /*
     * 添加/取消 收藏
     * */
    private void setCollect(){
        mActivity.closeLoading();
        if(detailBean.getData().getIs_collection() == 1){
            clearParams().setParams("out_id",getBundle().getString("article_id")).setParams("collection_code","baike");
            Controller.myRequest(ConstantsCode.DEL_COLLECTION,Constants.DEL_COLLECTION,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }else{
            clearParams().setParams("id",getBundle().getString("article_id")).setParams("collection_code","baike");
            Controller.myRequest(ConstantsCode.ADD_COLLECTION,Constants.ADD_COLLECTION,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }

    }

    /*
    * 赋值
    * */
    private void setData(V3EncyclopediaDetailBean detailBean){

        //给视频赋值
        if(detailBean.getData().getType() == 2){
            binding.myVideo.setVisibility(View.VISIBLE);
            binding.myVideo.setMyVideo(detailBean.getData().getArticle_video(),
                    detailBean.getData().getArticle_img());
            binding.baikeContent.setVisibility(View.GONE);
        }else{
            binding.myVideo.setVisibility(View.GONE);
            //设置webview显示样式
            binding.webContent.loadDataWithBaseURL(null, getNewData(detailBean.getData().getArticle_content()),"text/html", "utf-8",null);
            binding.baikeContent.setVisibility(View.VISIBLE);
        }
        binding.ftName.setText(detailBean.getData().getBaike_category_name()+"/"+ TimeUtil.formatDate(detailBean.getData().getAdd_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatYMD));
        binding.baikeName.setText(detailBean.getData().getArticle_title());
        binding.baikeContent.setText(detailBean.getData().getArticle_brief());
        binding.tvLike.setText(String.valueOf(detailBean.getData().getPraise_num()));
        binding.tvComment.setText(String.valueOf(detailBean.getData().getReview_num()));

        if(detailBean.getData().getIs_praise() == 0){
            ViewChangeUtil.changeLeftDrawable(mActivity,binding.tvLike,R.mipmap.like_huise_img);
        }else{
            ViewChangeUtil.changeLeftDrawable(mActivity,binding.tvLike,R.mipmap.like_chengse_img);
        }
        if(detailBean.getData().getIs_collection() == 1){
            binding.collectionImg.setImageResource(R.mipmap.orange_collection_mark);
        }else{
            binding.collectionImg.setImageResource(R.mipmap.gray_collection_img);
        }
    }

    @Override
    public void onSuccess(Object data) {
        if (data instanceof V3EncyclopediaDetailBean){
            detailBean = (V3EncyclopediaDetailBean)data;
            //获取推荐数据
            getTuiJian(String.valueOf(detailBean.getData().getBaike_categoryid()));
            //更新数据
            setData(detailBean);
        }

        if(data instanceof BaikeTJBeans){
            BaikeTJBeans diaBean = (BaikeTJBeans)data;
            tuijianAdapter.setNewData(diaBean.getData().getList());
        }

        if(data instanceof V3CommentBeans){
            V3CommentBeans pageData = (V3CommentBeans)data;
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

        if(data instanceof V3SendMeassage){
            //提交成功隐藏软键盘
            hideEditext(binding.editText);
            //刷新下评论
            getComment();
        }

        if(data instanceof V3BaiKeDetialCommentLikeBean){
            mActivity.closeLoading();
            //刷新下评论
            if(commentBean.getData().getList().get(commentPositon).getIs_praise() == 0){
                commentBean.getData().getList().get(commentPositon).setIs_praise(1);
                commentBean.getData().getList().get(commentPositon).setPraise_num(commentBean.getData().getList().get(commentPositon).getPraise_num()+1);
            }else{
                commentBean.getData().getList().get(commentPositon).setIs_praise(0);
                commentBean.getData().getList().get(commentPositon).setPraise_num(commentBean.getData().getList().get(commentPositon).getPraise_num()-1);
            }
            commentAdapter.notifyDataSetChanged();
        }

        if(data instanceof V3BaikeDetialLikeBean){
            mActivity.closeLoading();
            //刷新百科信息
            if(detailBean.getData().getIs_praise() == 0){
                detailBean.getData().setIs_praise(1);
                ViewChangeUtil.changeLeftDrawable(mActivity,binding.tvLike,R.mipmap.like_chengse_img);
                detailBean.getData().setPraise_num(detailBean.getData().getPraise_num()+1);
                binding.tvLike.setText(String.valueOf(detailBean.getData().getPraise_num()));
            }else{
                detailBean.getData().setIs_praise(0);
                ViewChangeUtil.changeLeftDrawable(mActivity,binding.tvLike,R.mipmap.like_huise_img);
                detailBean.getData().setPraise_num(detailBean.getData().getPraise_num()-1);
                binding.tvLike.setText(String.valueOf(detailBean.getData().getPraise_num()));
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.DEL_COLLECTION){
            detailBean.getData().setIs_collection(0);
            binding.collectionImg.setImageResource(R.mipmap.gray_collection_img);
        }
        if(code == ConstantsCode.ADD_COLLECTION){
            detailBean.getData().setIs_collection(1);
            binding.collectionImg.setImageResource(R.mipmap.orange_collection_mark);
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
        br_id_father = String.valueOf(commentBean.getData().getList().get(partentPosition).getList_s().get(position).getAr_id());
        first_br_id = String.valueOf(commentBean.getData().getList().get(partentPosition).getAr_id());
        //打开输入框
        showEditext(binding.editText);
    }

    /**
     * 设置img标签下的width为手机屏幕宽度，height自适应
     *
     * @param data html字符串
     * @return 更新宽高属性后的html字符串
     */
    private String getNewData(String data) {
        Document document = Jsoup.parse(data);

        Elements pElements = document.select("p:has(img)");
        for (Element pElement : pElements) {
            pElement.attr("style", "text-align:center");
            pElement.attr("max-width", ScreenUtils.getScreenWidth(mActivity) + "px")
                    .attr("height", "auto");
        }
        Elements imgElements = document.select("img");
        for (Element imgElement : imgElements) {
            //重新设置宽高
            imgElement.attr("max-width", "100%")
                    .attr("height", "auto");
            imgElement.attr("style", "max-width:100%;height:auto");
        }
        return document.toString();
    }
}
