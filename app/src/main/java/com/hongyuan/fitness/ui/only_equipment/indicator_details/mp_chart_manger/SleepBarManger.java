package com.hongyuan.fitness.ui.only_equipment.indicator_details.mp_chart_manger;

import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_sleep.WristbandSleepBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class SleepBarManger {
    private BarChart barChart;
    private YAxis leftAxis;             //左侧Y轴
    private YAxis rightAxis;            //右侧Y轴
    private XAxis xAxis;                //X轴
    private Legend legend;

    public SleepBarManger(BarChart chart){
        this.barChart = chart;
        leftAxis = this.barChart.getAxisLeft();
        rightAxis = this.barChart.getAxisRight();
        xAxis = this.barChart.getXAxis();
        initBarChart(chart);

    }


    /**
     * 初始化BarChart图表
     */
    private void initBarChart(BarChart barChart) {
        /***图表设置***/
        //背景颜色
        barChart.setBackgroundColor(Color.TRANSPARENT);
        //不显示图表网格
        barChart.setDrawGridBackground(false);
        //背景阴影
        barChart.setDrawBarShadow(false);
        barChart.setHighlightFullBarEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        //禁止拖拽
        barChart.setDragEnabled(false);
        //X轴或Y轴禁止缩放
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);
        barChart.setScaleEnabled(false);
        //禁止所有事件
        //barChart.setTouchEnabled(false);
        //不显示边框
        barChart.setDrawBorders(false);
        //不显示右下角描述内容
        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);
        //设置动画效果
        barChart.animateY(1000, Easing.Linear);
        barChart.animateX(1000, Easing.Linear);


        /***XY轴的设置***/
        //X轴设置显示位置在底部
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.parseColor("#FFFFFFFF"));
        leftAxis.setTextColor(Color.parseColor("#FFFFFFFF"));
        leftAxis = barChart.getAxisLeft();
        rightAxis = barChart.getAxisRight();
        leftAxis.setGridColor(0);
        //保证Y轴从0开始，不然会上移一点
        //xAxis.setAxisMinimum(0f);
        //leftAxis.setAxisMinimum(0f);
        //保证Y轴从0开始，不然会上移一点
        //leftAxis.setAxisMinimum(0f);
        //rightAxis.setAxisMinimum(0f);

        //不绘制X Y轴线条
        xAxis.setDrawAxisLine(false);
        leftAxis.setDrawAxisLine(false);
        rightAxis.setDrawAxisLine(false);
        //不显示左侧Y轴
        leftAxis.setEnabled(false);
        //不显示右侧Y轴
        rightAxis.setEnabled(false);
        //不显示X轴网格线
        xAxis.setDrawGridLines(false);
        //右侧Y轴网格线设置为虚线
        //rightAxis.enableGridDashedLine(10f, 10f, 0f);
        //左侧侧Y轴网格线设置为虚线
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        /***折线图例 标签 设置***/
        legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
        //是否显示
        legend.setEnabled(false);
    }

    public void showBarChart(List<WristbandSleepBeans.DataBean.ListBean> mList, CustomActivity mActivity){

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < mList.size(); i++) {

            values.add(new BarEntry(
                    i,
                    new float[]{mList.get(i).getSumDeepSleepMinute(), mList.get(i).getSumLightSleepMinute(), mList.get(i).getSumSoberMinute()},
                    mActivity.getResources().getDrawable(R.drawable.star)));
        }

        BarDataSet set1;

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "");
            set1.setDrawIcons(false);
            set1.setColors(getColors());

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            //设置X轴的刻度数
            xAxis.setLabelCount(mList.size() - 1, false);
            //X轴自定义值
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    String a="";
                    if(BaseUtil.isValue(mList.get((int) value).getRecord_date())){
                        a = TimeUtil.formatDate(mList.get((int) value).getRecord_date(),TimeUtil.dateFormat,TimeUtil.dateFormatMD);
                    }
                    return a;
                }
            });
            data.setValueTextColor(Color.WHITE);
            data.setDrawValues(false);

            barChart.setData(data);
        }

        barChart.setFitBars(true);
        barChart.invalidate();
    }

    private int[] getColors() {

        // have as many colors as stack-values per entry
        int[] colors = new int[]{Color.parseColor("#FF806DEF"),Color.parseColor("#FFA6A3FF"),Color.parseColor("#FF34CBD0")};

        return colors;
    }

}
