package com.hongyuan.fitness.ui.person.my_collection;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachHomePageActivity;
import com.hongyuan.fitness.ui.about_class.coach.coach_list.CoachListAdapter;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.encyclopedia.encyclopedia_detail.EncyclopediaDetailActivity;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsAdapter;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsBeans;
import com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home.VtwoStarCoachBean;
import com.hongyuan.fitness.ui.person.my_collection.collection_baike.CollectionBaiKeAdapter;
import com.hongyuan.fitness.ui.person.my_collection.collection_baike.CollectionBaikeBeans;

import java.util.List;

public class MyCollectionFragment extends CustomFragment {


    private RecyclerView mRecycler;

    //私教课适配器
    private VtwoPrivateLessonsAdapter myCourseAdapter;
    private List<VtwoPrivateLessonsBeans.DataBean.ListBean> myCourseList;

    //百科适配器
    private CollectionBaiKeAdapter myBaiKeAdapter;
    private List<CollectionBaikeBeans.DataBean.ListBean> myBaiKeList;

    //私教适配器
    private CoachListAdapter myCoachAdapter;
    private List<VtwoStarCoachBean.DataBean.ListBean> myCoachList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        mRecycler = mView.findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);

        setAdapter();
    }

    private void setAdapter(){
        if("cp".equals(getFragType())){

            myCourseAdapter = new VtwoPrivateLessonsAdapter();
            mRecycler.setAdapter(myCourseAdapter);

            myCourseAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @SingleClick
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("cp_id",String.valueOf(myCourseList.get(position).getCp_id()));
                    startActivity(CourseDetailsActivity.class,bundle);
                }
            });
        }
        if("baike".equals(getFragType())){
            myBaiKeAdapter = new CollectionBaiKeAdapter();
            mRecycler.setAdapter(myBaiKeAdapter);

            myBaiKeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @SingleClick (2000)
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("article_id",String.valueOf(myBaiKeList.get(position).getArticle_id()));
                    startActivity(EncyclopediaDetailActivity.class,bundle);
                }
            });
        }
        if("coach".equals(getFragType())){
            myCoachAdapter = new CoachListAdapter();
            mRecycler.setAdapter(myCoachAdapter);
            myCoachAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @SingleClick
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("coach_mid",String.valueOf(myCoachList.get(position).getM_id()));
                    startActivity(CoachHomePageActivity.class,bundle);
                }
            });
        }
    }

    @Override
    protected void lazyLoad() {
        refreshData();
    }

    @Override
    public void loadMoreData() {
        getData();
    }

    @Override
    public void refreshData() {
        if("cp".equals(getFragType()) && myCourseList != null){
            myCourseList.clear();
        }
        if("baike".equals(getFragType()) && myBaiKeList != null){
            myBaiKeList.clear();
        }
        if("coach".equals(getFragType()) && myCoachList != null){
            myCoachList.clear();
        }
        getData();
    }

    /*
    * 请求收藏接口
    * */
    private void getData(){
        mActivity.showLoading();
        clearParams().setParams("collection_code",getFragType());
        Controller.myRequest(Constants.GET_COLLECTION_LIST,Controller.TYPE_POST,getParams(), getMyClass(),this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof VtwoPrivateLessonsBeans){
            List<VtwoPrivateLessonsBeans.DataBean.ListBean> courseList =  ((VtwoPrivateLessonsBeans) data).getData().getList();
            if(curPage == FIRST_PAGE){
                myCourseList = courseList;
            }else{
                if(courseList != null && courseList.size() > 0){
                    myCourseList.addAll(courseList);
                }else{
                    refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(myCourseList != null && myCourseList.size() > 0){
                myCourseAdapter.setNewData(myCourseList);
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }

        if(data instanceof CollectionBaikeBeans){
            List<CollectionBaikeBeans.DataBean.ListBean> baiKeList = ((CollectionBaikeBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                myBaiKeList = baiKeList;
            }else{
                if(baiKeList != null && baiKeList.size() > 0){
                    myBaiKeList.addAll(baiKeList);
                }else{
                    refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(myBaiKeList != null && myBaiKeList.size() > 0){
                myBaiKeAdapter.setNewData(myBaiKeList);
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }

        if(data instanceof VtwoStarCoachBean){
            List<VtwoStarCoachBean.DataBean.ListBean> coachList = ((VtwoStarCoachBean)data).getData().getList();
            if(curPage == FIRST_PAGE){
                myCoachList = coachList;
            }else{
                if(coachList != null && coachList.size() > 0){
                    myCoachList.addAll(coachList);
                }else{
                    refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(myCoachList != null && myCoachList.size() > 0){
                myCoachAdapter.setNewData(myCoachList);
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }
    }
}
