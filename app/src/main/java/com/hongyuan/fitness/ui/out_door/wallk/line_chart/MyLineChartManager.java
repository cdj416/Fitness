package com.hongyuan.fitness.ui.out_door.wallk.line_chart;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class MyLineChartManager {

    private LineChart chart;

    public MyLineChartManager(LineChart chart){
        this.chart = chart;
        setChart();
    }

    /*
    * 初始配置
    * */
    private void setChart(){
        //设置其实位置
        chart.setViewPortOffsets(0, 0, 0, 0);
        //设置背景颜色
        chart.setBackgroundColor(0X00000000);
        //设置为没有文字描述
        chart.getDescription().setEnabled(false);
        //是否可点击
        chart.setTouchEnabled(true);

        chart.setDragEnabled(true);
        chart.setDoubleTapToZoomEnabled(true);

        chart.setScaleEnabled(true);
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(300);

        XAxis x = chart.getXAxis();
        //显示数据个数
        x.setLabelCount(7, false);
        x.setTextColor(Color.WHITE);
        x.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        x.setDrawGridLines(false);

        YAxis y = chart.getAxisLeft();
        y.setEnabled(false);
        /*y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);*/

        chart.getAxisRight().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.animateXY(2000, 2000);
        chart.invalidate();

        //设置一页显示的数据条数，超出的数量需要滑动查看：
        chart.setVisibleXRangeMaximum(100);//需要在设置数据源后生效
        chart.setVisibleXRangeMinimum(7);//设置最少数量，不常用。
    }

    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     *
     * @param lineDataSet 线条
     * @param color       线条颜色
     * @param mode
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);

        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(false);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }

    /*
    * 设置显示值
    * */
    public void setData(int count, float range){
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * (range + 1)) + 20;
            values.add(new Entry(i, val));
        }

        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(values, "");
        //LINEAR 折线图  CUBIC_BEZIER 圆滑曲线
        initLineDataSet(lineDataSet, 0xFFFFFFFF, LineDataSet.Mode.CUBIC_BEZIER);
        LineData lineData = new LineData(lineDataSet);
        chart.setData(lineData);
    }


}
