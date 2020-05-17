package com.hongyuan.fitness.ui.person.fix;

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
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.PriviteCourseCheckBeans;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.TimeUtil;

import java.util.List;

/*
* 待上课
* */
public class SixReservationFragment extends CustomFragment {

    private RecyclerView mRec;
    private PcourseSiginAdapter adapter;

    List<PriviteCourseCheckBeans.DataBean.ListBean> mList;

    //记录当前签到的是哪一项
    private int mPosition;
    //记录当前取消的哪一项
    private int cPosition;

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
        adapter = new PcourseSiginAdapter();
        mRec.setAdapter(adapter);
        adapter.addFooterView(getFooterHeight(mRec));

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //签到
                if(view.getId() == R.id.qdText && mList.get(position).getXy_qd_state() != 1){
                    mPosition = position;
                    courseQD(String.valueOf(mList.get(position).getCpa_id()));
                }

                if(view.getId() == R.id.cancelSign){
                    Bundle bundle = new Bundle();
                    bundle.putString("cpa_id",String.valueOf(mList.get(position).getCpa_id()));
                    startActivity(CancelCourseActivity.class,bundle);
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

    //获取待上课数据
    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("state_str","1");
        Controller.myRequest(Constants.GET_MEMBER_APPOINTMENT_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), PriviteCourseCheckBeans.class,this);
    }

    /*
     * 签到签退
     * */
    private void courseQD(String cpa_id){
        clearParams().setParams("cpa_id",cpa_id).setParams("mtype","xy").setParams("type","qd");
        Controller.myRequest(ConstantsCode.PRIVITE_COURSE_QD,Constants.PRIVITE_COURSE_QD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
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

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.CANCEL_COURSE_PRIVITE_APPOINTMENT){
            mList.remove(cPosition);
            adapter.setNewData(mList);
            showSuccess("已取消预约！");
        }

        if(code == ConstantsCode.PRIVITE_COURSE_QD){
            mList.get(mPosition).setXy_qd_state(1);
            adapter.setNewData(mList);
            CustomDialog.priviteCoursePunchSuccess(mActivity, TimeUtil.formatDataMsec(TimeUtil.dateFormatDotMD,System.currentTimeMillis()),
                    TimeUtil.getWeek());
        }
    }
}
