package com.hongyuan.fitness.ui.only_equipment.smart_historical_data;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;

import java.util.List;

public class SmartLineChartFragment extends CustomFragment {

    private LineChart lineChart;
    private SmartLineChartManager chartManager;

    private List<SmartHistoricalUseData> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_smart_linechart;
    }

    @Override
    public void initView(View mView) {

        mList = getArguments().getParcelableArrayList("useData");

        lineChart = mView.findViewById(R.id.lineChart);
        chartManager = new SmartLineChartManager(lineChart,mList);
        chartManager.setData(mList);

    }


    @Override
    public void onSuccess(Object data) {

    }
}
