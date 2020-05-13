package com.hongyuan.fitness.ui.person.fix.coures_record;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityPersonCourseRecordBinding;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.person.fix.SixAssessAdapter;
import com.hongyuan.fitness.ui.person.waiting_evaluation.editorial_evaluation.EditorialEvaluationActivity;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.PriviteCourseCheckBeans;

import java.util.List;

public class PriviteCourseRecordViewModel extends CustomViewModel {

    private ActivityPersonCourseRecordBinding binding;
    private SixAssessAdapter adapter;

    private List<PriviteCourseCheckBeans.DataBean.ListBean> mList;

    public PriviteCourseRecordViewModel(CustomActivity mActivity, ActivityPersonCourseRecordBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new SixAssessAdapter();
        binding.mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.goDetails){
                Bundle bundle = new Bundle();
                bundle.putString("cp_id",String.valueOf(mList.get(position).getCp_id()));
                startActivity(CourseDetailsActivity.class,bundle);
            }else{
                Bundle bundle = new Bundle();
                bundle.putSerializable("courseBeans",mList.get(position));
                startActivity(EditorialEvaluationActivity.class,bundle);
            }
        });
    }

    @Override
    public void refreshData(){
        if(mList != null)mList.clear();
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("state_str","1,2,3");
        Controller.myRequest(Constants.GET_MEMBER_APPOINTMENT_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), PriviteCourseCheckBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof PriviteCourseCheckBeans){
            List<PriviteCourseCheckBeans.DataBean.ListBean> list = ((PriviteCourseCheckBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    mActivity.refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }
    }
}
