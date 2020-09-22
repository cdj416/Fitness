package com.hongyuan.fitness.ui.person.physical_data.add_physical;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.List;

public class AddPhysicaldataFragment extends CustomFragment {


    private TextView goEdit,tvTitle,noData;

    //弹框需要的显示标题以及单位
    private String titleName,unitText,showTitle;

    private RecyclerView mRecycler;
    private AddPhysicaldataAdapter adapter;
    private List<PhysicaldataListBeans.DataBean.ListBean> mList;

    private LineChart lineChart;
    private AddPhysicaldataLineChartManager lineChartManager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_add_physicaldata;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        goEdit = mView.findViewById(R.id.goEdit);
        mRecycler = mView.findViewById(R.id.mRecycler);
        lineChart = mView.findViewById(R.id.lineChart);
        tvTitle = mView.findViewById(R.id.tvTitle);
        noData = mView.findViewById(R.id.noData);

        //初始化弹框显示的标题和单位
        setDialogTitleOrUnit(getFragType());
        tvTitle.setText(titleName+"记录");
        goEdit.setText("记录"+titleName);

        goEdit.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                CustomDialog.showAddPhysicaldata(mActivity, "请输入当前"+titleName+"值", unitText, (v1, message) -> {

                    if(BaseUtil.isValue(message)){
                        addData(message);
                    }
                });
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new AddPhysicaldataAdapter();
        mRecycler.setAdapter(adapter);

    }

    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("body_code",getFragType());
        Controller.myRequest(Constants.GET_BODY_DATA_LIST,Controller.TYPE_POST,getParams(), PhysicaldataListBeans.class,this);
    }

    /*
    * 添加身体数据
    * */
    private void  addData(String value){
        mActivity.showLoading();
        clearParams().setParams("body_code",getFragType()).setParams("body_value",value);
        Controller.myRequest(ConstantsCode.ADD_BODY_DATA,Constants.ADD_BODY_DATA,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof PhysicaldataListBeans){
            List<PhysicaldataListBeans.DataBean.ListBean> list = ((PhysicaldataListBeans)data).getData().getList();
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
                lineChartManager = new AddPhysicaldataLineChartManager(lineChart,mList);
                lineChartManager.setData(mList);
                adapter.setNewData(mList);
                noData.setVisibility(View.GONE);
                //setPromtView(SHOW_DATA);
            }else{
                noData.setVisibility(View.VISIBLE);
                //setPromtView(SHOW_EMPTY);
            }
        }

    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.ADD_BODY_DATA){
            lazyLoad();
            showSuccess("添加成功！");
        }
    }

    /*
     * 获取弹框标题和单位
     * */
    private void setDialogTitleOrUnit(String bodyCode){
        switch (bodyCode){
            case "weight":
                titleName = "体重";
                unitText = "kg";
                break;

            case "height":
                titleName = "身高";
                unitText = "cm";
                break;

            case "bmi":
                titleName = "bmi";
                unitText = "";
                break;

            case "xw":
                titleName = "胸围";
                unitText = "cm";
                break;

            case "yw":
                titleName = "腰围";
                unitText = "cm";
                break;

            case "tw":
                titleName = "臀围";
                unitText = "cm";
                break;

            case "dtw":
                titleName = "大腿围";
                unitText = "cm";
                break;

            case "xtw":
                titleName = "小腿围";
                unitText = "cm";
                break;

            case "sbw":
                titleName = "手臂围";
                unitText = "cm";
                break;

            case "bfp":
                titleName = "体脂率";
                unitText = "%";
                break;
        }
    }
}
