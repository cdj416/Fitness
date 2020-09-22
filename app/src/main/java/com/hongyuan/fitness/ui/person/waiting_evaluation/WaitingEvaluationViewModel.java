package com.hongyuan.fitness.ui.person.waiting_evaluation;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityWaitEvaluationBinding;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.person.waiting_evaluation.editorial_evaluation.EditorialEvaluationActivity;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.PriviteCourseCheckBeans;

public class WaitingEvaluationViewModel extends CustomViewModel {

    private ActivityWaitEvaluationBinding binding;
    private WaitingEvaluationAdapter adapter;

    private PriviteCourseCheckBeans courseBeans;

    public WaitingEvaluationViewModel(CustomActivity mActivity, ActivityWaitEvaluationBinding binding) {
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
        adapter = new WaitingEvaluationAdapter();
        binding.mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.goDetails){
                //startActivity(EvalutationDetailsActivity.class,null);
                Bundle bundle = new Bundle();
                bundle.putString("cp_id",String.valueOf(courseBeans.getData().getList().get(position).getCp_id()));
                startActivity(CourseDetailsActivity.class,bundle);
            }else{
                Bundle bundle = new Bundle();
                bundle.putSerializable("courseBeans",courseBeans.getData().getList().get(position));
                startActivity(EditorialEvaluationActivity.class,bundle);
            }
        });
    }

    @Override
    public void refreshData(){
        courseBeans = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("state_str","1,2");
        Controller.myRequest(Constants.GET_MEMBER_APPOINTMENT_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), PriviteCourseCheckBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        if(data instanceof PriviteCourseCheckBeans){
            PriviteCourseCheckBeans pageData = (PriviteCourseCheckBeans)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    courseBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    courseBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(courseBeans != null && courseBeans.getData() != null &&
                    courseBeans.getData().getList() != null &&
                    courseBeans.getData().getList().size() > 0){
                adapter.setNewData(courseBeans.getData().getList());
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView( mActivity.SHOW_EMPTY);
            }
        }
    }
}
