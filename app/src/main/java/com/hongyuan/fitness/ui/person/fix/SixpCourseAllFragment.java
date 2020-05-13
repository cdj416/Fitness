package com.hongyuan.fitness.ui.person.fix;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course.MyPriviteCourseBeans;
import com.hongyuan.fitness.ui.about_class.privite_class.preservation_course.ReservationDetailsActivity;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.PriviteCourseCheckBeans;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.privite_checkin_details.PriviteCourseCheckDetails;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.TimeUtil;

import java.util.List;

public class SixpCourseAllFragment extends CustomFragment {

    private LinearLayout oBox,tBox;
    private RecyclerView oRec,tRec;
    private PcourseSiginAdapter oAdapter;
    private PcourseReservationAdapter tAdapter;

    List<PriviteCourseCheckBeans.DataBean.ListBean> oList;
    List<MyPriviteCourseBeans.DataBean.ListBean> tList;

    //记录当前签到的是哪一项
    private int mPosition;
    //记录当前取消的哪一项
    private int cPosition;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_six_p_course_all;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        oBox = mView.findViewById(R.id.oBox);
        tBox = mView.findViewById(R.id.tBox);
        oRec = mView.findViewById(R.id.oRec);
        tRec = mView.findViewById(R.id.tRec);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        oRec.setLayoutManager(manager);
        oAdapter = new PcourseSiginAdapter();
        oRec.setAdapter(oAdapter);
        oAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //签到
                if(view.getId() == R.id.qdText && oList.get(position).getXy_qd_state() != 1){
                    mPosition = position;
                    courseQD(String.valueOf(oList.get(position).getCpa_id()));
                }

                if(view.getId() == R.id.wcCancelSign || view.getId() == R.id.cancelSign){
                    CustomDialog.promptDialog(mActivity, "确定取消预约该课程吗？", "确定取消", "暂不取消", false, v1 -> {
                        if(v1.getId() == R.id.isOk){
                            cPosition = position;
                            cancelRese(String.valueOf(oList.get(position).getCpa_id()));
                        }
                    });

                }

                //去课程详情和签到详情
                if(view.getId() == R.id.goSignDetail){
                    Bundle bundle = new Bundle();
                    bundle.putString("cp_id",String.valueOf(oList.get(position).getCp_id()));
                    bundle.putString("cpa_id",String.valueOf(oList.get(position).getCpa_id()));
                    bundle.putString("showTime",oList.get(position).getStart_time());
                    bundle.putBoolean("isSign",oList.get(position).getXy_qd_state() == 1);
                    startActivity(PriviteCourseCheckDetails.class,bundle);
                }
            }
        });


        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        tRec.setLayoutManager(manager1);
        tAdapter = new PcourseReservationAdapter();
        tRec.setAdapter(tAdapter);

        tAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if(view.getId() == R.id.goBuyBox){
                    Bundle bundle = new Bundle();
                    bundle.putString("cp_id",String.valueOf(tList.get(position).getCp_id()));
                    startActivity(CourseDetailsActivity.class,bundle);
                }else{
                    TextView textView = (TextView)view;
                    if("再次购买".equals(textView.getText().toString())){
                        Bundle bundle = new Bundle();
                        bundle.putString("cp_id",String.valueOf(tList.get(position).getCp_id()));
                        startActivity(CourseDetailsActivity.class,bundle);
                    }
                    if("预约".equals(textView.getText().toString())){
                        Bundle bundle = new Bundle();
                        bundle.putString("cp_id",String.valueOf(tList.get(position).getCp_id()));
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
        if(oList != null)oList.clear();
        if(tList != null)tList.clear();
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("state_str","1");
        Controller.myRequest(Constants.GET_MEMBER_APPOINTMENT_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), PriviteCourseCheckBeans.class,this);

        clearParams().clearParams();
        Controller.myRequest(Constants.GET_MY_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), MyPriviteCourseBeans.class,this);
    }

    /*
     * 签到签退
     * */
    private void courseQD(String cpa_id){
        clearParams().setParams("cpa_id",cpa_id).setParams("mtype","xy").setParams("type","qd");
        Controller.myRequest(ConstantsCode.PRIVITE_COURSE_QD,Constants.PRIVITE_COURSE_QD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
     * 取消私教课预约
     * */
    private void cancelRese(String cpa_id){
        clearParams().setParams("cpa_id",cpa_id);
        Controller.myRequest(ConstantsCode.CANCEL_COURSE_PRIVITE_APPOINTMENT,Constants.CANCEL_COURSE_PRIVITE_APPOINTMENT,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof PriviteCourseCheckBeans){
            List<PriviteCourseCheckBeans.DataBean.ListBean> list = ((PriviteCourseCheckBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                oList = list;
            }else{
                if(list != null && list.size() > 0){
                    oList.addAll(list);
                }else{
                    //refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(oList != null && oList.size() > 0){
                oAdapter.setNewData(oList);
                oBox.setVisibility(View.VISIBLE);
            }else{
                oBox.setVisibility(View.GONE);
            }
        }

        if(data instanceof MyPriviteCourseBeans){
            List<MyPriviteCourseBeans.DataBean.ListBean> list = ((MyPriviteCourseBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                tList = list;
            }else{
                if(list != null && list.size() > 0){
                    tList.addAll(list);
                }else{
                    //refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(tList != null && tList.size() > 0){
                tAdapter.setNewData(tList);
                tBox.setVisibility(View.VISIBLE);
            }else{
                tBox.setVisibility(View.GONE);
            }
        }

        if((tList == null || tList.size() == 0) && (oList == null || oList.size() == 0)){
            setPromtView(SHOW_EMPTY);
        }else{
            setPromtView(SHOW_DATA);
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.CANCEL_COURSE_PRIVITE_APPOINTMENT){
            oList.remove(cPosition);
            oAdapter.setNewData(oList);
            showSuccess("已取消预约！");
        }

        if(code == ConstantsCode.PRIVITE_COURSE_QD){
            oList.get(mPosition).setXy_qd_state(1);
            oAdapter.setNewData(oList);
            CustomDialog.priviteCoursePunchSuccess(mActivity, TimeUtil.formatDataMsec(TimeUtil.dateFormatDotMD,System.currentTimeMillis()),
                    TimeUtil.getWeek());
        }
    }
}
