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
import com.hongyuan.fitness.ui.person.exercise_data.ExeriseDataTopBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class WristbandExerciseDataBarManger {
    private BarChart barChart;
    private YAxis leftAxis;             //左侧Y轴
    private YAxis rightAxis;            //右侧Y轴
    private XAxis xAxis;                //X轴
    private Legend legend;

    public WristbandExerciseDataBarManger(BarChart chart){
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

    public void showBarChart(List<ExeriseDataTopBeans.DataBean.ListBean> mList){

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            /**
             * 此处还可传入Drawable对象 BarEntry(float x, float y, Drawable icon)
             * 即可设置柱状图顶部的 icon展示
             */
            BarEntry barEntry = new BarEntry(i, (float) mList.get(i).getSumStep());
            entries.add(barEntry);
        }
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, "");
        //设置渐变色
        //barDataSet.setGradientColor(0xFFFF8B77,0xFFEF5E45);
        barDataSet.setValueTextSize(9f);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);
        barDataSet.setColor(Color.parseColor("#FFFFB3A6"));
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);
        //不显示柱状图顶部值
        barDataSet.setDrawValues(false);
        //设置X轴的刻度数
        xAxis.setLabelCount(mList.size() - 1, false);
        BarData data = new BarData(barDataSet);
        barChart.setData(data);
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
        /*//左侧Y轴自定义值
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(value/10000>1&&value!=0){

                    return df.format(value/10000)+"w";
                }else {
                    if(value>1) {
                        return  df.format((int) value) + "";
                    }else{
                        if(value>=0) {
                            return df.format((double) value) + "";
                        }else{
                            return "";
                        }
                    }
                }
            }
        });*/

        barChart.invalidate();
    }
}
