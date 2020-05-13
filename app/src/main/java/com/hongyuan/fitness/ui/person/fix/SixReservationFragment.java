package com.hongyuan.fitness.ui.person.fix;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course.MyPriviteCourseBeans;
import com.hongyuan.fitness.ui.about_class.privite_class.preservation_course.ReservationDetailsActivity;

import java.util.List;

public class SixReservationFragment extends CustomFragment {

    private RecyclerView mRec;
    private PcourseReservationAdapter adapter;

    List<MyPriviteCourseBeans.DataBean.ListBean> mList;

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
        adapter = new PcourseReservationAdapter();
        mRec.setAdapter(adapter);
        adapter.addFooterView(getFooterHeight(mRec));

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if(view.getId() == R.id.goBuyBox){
                    Bundle bundle = new Bundle();
                    bundle.putString("cp_id",String.valueOf(mList.get(position).getCp_id()));
                    startActivity(CourseDetailsActivity.class,bundle);
                }else{
                    TextView textView = (TextView)view;
                    if("再次购买".equals(textView.getText().toString())){
                        Bundle bundle = new Bundle();
                        bundle.putString("cp_id",String.valueOf(mList.get(position).getCp_id()));
                        startActivity(CourseDetailsActivity.class,bundle);
                    }
                    if("预约".equals(textView.getText().toString())){
                        Bundle bundle = new Bundle();
                        bundle.putString("cp_id",String.valueOf(mList.get(position).getCp_id()));
                        startActivity(ReservationDetailsActivity.class,bundle);
                    }
                }
            }
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
        clearParams().clearParams();
        Controller.myRequest(Constants.GET_MY_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), MyPriviteCourseBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
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
