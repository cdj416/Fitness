package com.hongyuan.fitness.ui.person.fix;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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
import com.hongyuan.fitness.ui.person.waiting_evaluation.editorial_evaluation.EditorialEvaluationActivity;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.PriviteCourseCheckBeans;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.privite_checkin_details.PriviteCourseCheckDetails;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SixpCourseAllFragment extends CustomFragment {

    //状态1待上课 2已取消 3学员签到已完成 5申请取消中 6取消驳回

    private LinearLayout oBox,tBox,qdBox,descriptionBox;
    private RecyclerView tRec;
    private PcourseReservationAdapter tAdapter;
    private TextView courseStartTime,courseName,coachName,showTime,cancelSign,qdText,tTvtext,btvText;
    private RoundedImageView headImg;

    private DanduOneCourseBeans.DataBean.InfoBean infoBean;


    List<MyPriviteCourseBeans.DataBean.ListBean> tList;

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
        tRec = mView.findViewById(R.id.tRec);

        courseStartTime = mView.findViewById(R.id.courseStartTime);
        courseName = mView.findViewById(R.id.courseName);
        coachName = mView.findViewById(R.id.coachName);
        showTime = mView.findViewById(R.id.showTime);
        cancelSign = mView.findViewById(R.id.cancelSign);
        qdText = mView.findViewById(R.id.qdText);
        descriptionBox = mView.findViewById(R.id.descriptionBox);
        headImg = mView.findViewById(R.id.headImg);
        tTvtext = mView.findViewById(R.id.tTvtext);
        btvText = mView.findViewById(R.id.btvText);
        qdBox = mView.findViewById(R.id.qdBox);


        qdText.setOnClickListener(v -> {
            if("评价".equals(qdText.getText().toString())){
                PriviteCourseCheckBeans.DataBean.ListBean listBean = new PriviteCourseCheckBeans.DataBean.ListBean();
                listBean.setCp_img(infoBean.getCp_img());
                listBean.setCp_id(infoBean.getCp_id());
                listBean.setCpa_id(infoBean.getCpa_id());

                Bundle bundle = new Bundle();
                bundle.putSerializable("courseBeans",listBean);
                startActivity(EditorialEvaluationActivity.class,bundle);
            }
            if("签到".equals(qdText.getText().toString())){
                courseQD(String.valueOf(infoBean.getCpa_id()));
            }

        });

        cancelSign.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("cpa_id",String.valueOf(infoBean.getCpa_id()));
            bundle.putString("tel","18173195472");
            startActivity(CancelCourseActivity.class,bundle);
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
     * 课程开始时间处理
     * */
    private String getShowTime(String startTime){
        String showText;
        if(TimeUtil.isToday(startTime,TimeUtil.dateFormatYMDHMS)){
            showText = "上课时间：今天 "+TimeUtil.formatDate(startTime,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM);
        }else{
            showText = "上课时间："+TimeUtil.formatDate(startTime,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormat);
        }
        return showText;
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
        if(tList != null)tList.clear();
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_MEMBER_APPOINTMENT_COURSE_PRIVITE_ONE,Controller.TYPE_POST,getParams(), DanduOneCourseBeans.class,this);

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


    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof DanduOneCourseBeans){
            infoBean = ((DanduOneCourseBeans)data).getData().getInfo();

            if(infoBean == null || !BaseUtil.isValue(infoBean.getStart_time())){
                return;
            }


            RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img).centerCrop();
            Glide.with(mActivity).load(infoBean.getCoach_head()).apply(options).into(headImg);

            courseStartTime.setText(getShowTime(infoBean.getStart_time()));
            courseName.setText(infoBean.getCp_name());
            coachName.setText(infoBean.getCoach_nickname()+"/"+infoBean.getOs_name());

            if(infoBean.getState() == 1){
                qdBox.setVisibility(View.VISIBLE);
                descriptionBox.setVisibility(View.GONE);
            }
            if(infoBean.getState() == 2){
                qdBox.setVisibility(View.GONE);
                descriptionBox.setVisibility(View.VISIBLE);
                tTvtext.setVisibility(View.GONE);
                btvText.setText("课程取消成功");
            }

            if(infoBean.getState() == 3){
                tTvtext.setText("课程已结束");
                cancelSign.setVisibility(View.GONE);
                qdText.setText("评价");
                qdBox.setVisibility(View.VISIBLE);
                descriptionBox.setVisibility(View.GONE);
            }

            if(infoBean.getState() == 5){
                qdBox.setVisibility(View.GONE);
                descriptionBox.setVisibility(View.VISIBLE);
            }

            if(infoBean.getState() == 6){
                qdBox.setVisibility(View.VISIBLE);
                descriptionBox.setVisibility(View.GONE);
                tTvtext.setText("申请已驳回");
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

        if(tList == null || tList.size() == 0){
            setPromtView(SHOW_EMPTY);
        }else{
            setPromtView(SHOW_DATA);
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.CANCEL_COURSE_PRIVITE_APPOINTMENT){

            showSuccess("已取消预约！");
        }

        if(code == ConstantsCode.PRIVITE_COURSE_QD){
            CustomDialog.priviteCoursePunchSuccess(mActivity, TimeUtil.formatDataMsec(TimeUtil.dateFormatDotMD,System.currentTimeMillis()),
                    TimeUtil.getWeek());
        }
    }
}
