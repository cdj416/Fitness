package com.hongyuan.fitness.ui.shop.sfragment;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.comm_title.CommentTitleBeans;
import com.hongyuan.fitness.custom_view.comm_title.CommentTitleView;
import com.hongyuan.fitness.ui.shop.sadapter.SGDCommentsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.ShopCommentBeans;
import com.hongyuan.fitness.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class SshopCommentsFragment extends CustomFragment implements CommentTitleView.ScoreChange{

    private RecyclerView mRec;
    private RelativeLayout childEmpty;
    private CommentTitleView commentTitle;

    private SGDCommentsAdapter adapter;

    private ShopCommentBeans.DataBean dataBean;

    //评论数据
    private List<ShopCommentBeans.DataBean.ListBean> mList;

    //评论级别
    private int score = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sgd_comments;
    }

    @Override
    public void initView(View mView) {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        mRec = mView.findViewById(R.id.mRec);
        childEmpty = mView.findViewById(R.id.childEmpty);
        commentTitle = mView.findViewById(R.id.commentTitle);
        commentTitle.setScoreChange(this);

        //评论的适配
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRec.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.HORIZONTAL_LIST,1,0xffF1F1F1));
        mRec.setLayoutManager(manager);
        adapter = new SGDCommentsAdapter();
        mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(mRec));
    }

    /*
    * 组装头部评论数
    * */
    public void setNums(ShopCommentBeans.DataBean dataBean){
        List<CommentTitleBeans> numList = new ArrayList<>();
        numList.add(new CommentTitleBeans(dataBean.getAll_num(),"全部",0));
        numList.add(new CommentTitleBeans(dataBean.getImg_num(),"有图",-1));
        numList.add(new CommentTitleBeans(dataBean.getAppend_num(),"追评数量",5));
        numList.add(new CommentTitleBeans(dataBean.getGood_num(),"非常满意",4));
        numList.add(new CommentTitleBeans(dataBean.getNormal_num(),"满意",3));
        numList.add(new CommentTitleBeans(dataBean.getBad_num(),"一般",1));
        numList.get(0).setSelect(true);

        commentTitle.setObjectNum(numList);
    }

    @Override
    protected void lazyLoad() {
        getComments();
    }

    @Override
    public void loadMoreData() {
        getComments();
    }

    /*
    * 获取评论
    * */
    private void getComments(){
        mActivity.showLoading();
        //获取商品评论
        clearParams().setParams("g_id",mActivity.getBundle().getString("g_id"));

        if(score == -1){
            setParams("have_img","1");
        }
        if(score == 4){
            setParams("good_level","good");
        }
        if(score == 3){
            setParams("good_level","normal");
        }
        if(score == 1){
            setParams("good_level","bad");
        }

        Controller.myRequest(Constants.GET_GOODS_EVALUATION,Controller.TYPE_POST,getParams(), ShopCommentBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof ShopCommentBeans){
            //设置头部信息(评论数)
            if(dataBean == null){
                dataBean = ((ShopCommentBeans)data).getData();
                setNums(dataBean);
            }


            List<ShopCommentBeans.DataBean.ListBean> list = ((ShopCommentBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                childEmpty.setVisibility(View.GONE);
                mRec.setVisibility(View.VISIBLE);
            }else{
                childEmpty.setVisibility(View.VISIBLE);
                mRec.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void valueChange(int score) {
        this.score = score;
        curPage = FIRST_PAGE;

        getComments();
    }
}
