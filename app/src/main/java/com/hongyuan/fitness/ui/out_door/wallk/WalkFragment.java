package com.hongyuan.fitness.ui.out_door.wallk;

import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.my_progress.CircleProgress;
import com.hongyuan.fitness.ui.out_door.wallk.line_chart.MyLineChartManager;

public class WalkFragment extends CustomFragment implements TodayStepUtils.ReturnWalkStepData {

    private MyLineChartManager lineChartManager;
    private LineChart lineChart;
    private CircleProgress myStep;
    private TextView distance,calories;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_walk;
    }

    @Override
    public void initView(View mView) {
        setCustomBg(R.color.transparent);
        lineChart = mView.findViewById(R.id.lineChart);
        myStep = mView.findViewById(R.id.myStep);
        distance = mView.findViewById(R.id.distance);
        calories = mView.findViewById(R.id.calories);


        lineChartManager = new MyLineChartManager(lineChart);
        lineChartManager.setData(20,20);

        //获取几步数据
        TodayStepUtils.getInstance().setWalkStepData(this);
    }

    @Override
    public void stepData(int mStepSum, String allDistion, String allCalorie) {
        //设置步数
        myStep.setMaxValue((int)(mStepSum*1.1+10));
        myStep.setValue(mStepSum);
        //设置距离
        distance.setText(allDistion);
        //设置卡路里
        calories.setText(allCalorie);
    }

    @Override
    public void onSuccess(Object data) {

    }

}
