package com.hongyuan.fitness.ui.person.fix;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course.MyPriviteCourseBeans;
import com.hongyuan.fitness.ui.person.waiting_evaluation.editorial_evaluation.EditorialEvaluationActivity;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.PriviteCourseCheckBeans;
import java.util.List;

public class SixAssessFragment extends CustomFragment {

    private RecyclerView mRec;
    private CourseCompleteAdapter adapter;

    private List<MyPriviteCourseBeans.DataBean.ListBean> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_recylerview;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        mRec = mView.findViewById(R.id.mRec);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRec.setLayoutManager(manager);
        adapter = new CourseCompleteAdapter();
        mRec.setAdapter(adapter);
        adapter.addFooterView(getFooterHeight(mRec));
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("cp_id",String.valueOf(mList.get(position).getCp_id()));
            startActivity(CourseDetailsActivity.class,bundle);
        });
    }

    /*
     * 加载更多
     * */
    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    //刷新数据
    @Override
    public void refreshData() {
        if(mList != null)mList.clear();
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("have_num","0");
        Controller.myRequest(Constants.GET_MY_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), MyPriviteCourseBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();

        if(data instanceof MyPriviteCourseBeans){
            List<MyPriviteCourseBeans.DataBean.ListBean> list = ((MyPriviteCourseBeans)data).getData().getList();
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
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }
    }
}
