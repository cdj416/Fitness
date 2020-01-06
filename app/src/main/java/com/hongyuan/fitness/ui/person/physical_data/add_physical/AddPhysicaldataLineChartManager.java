package com.hongyuan.fitness.ui.person.physical_data.add_physical;

import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.hongyuan.fitness.ui.only_equipment.smart_historical_data.SmartHistoricalUseData;

import java.util.ArrayList;
import java.util.List;

public class AddPhysicaldataLineChartManager {

    private List<PhysicaldataListBeans.DataBean.ListBean> mList;
    private LineChart chart;

    public AddPhysicaldataLineChartManager(LineChart chart, List<PhysicaldataListBeans.DataBean.ListBean> mList){
        this.chart = chart;
        this.mList = mList;
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
        //设置x轴位置
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        //x.setDrawGridLines(false);//设置网格线是否绘制
        x.setLabelRotationAngle(20f);//设置文字与x轴之间的角度
        x.setLabelCount(mList.size(), false);
        x.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.e("cdj","============value===="+value);
                return "2017/11/29";
            }
        });
        //显示数据个数
        /*x.setLabelCount(7, false);
        x.setTextColor(Color.WHITE);
        x.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        x.setDrawGridLines(false);*/

        YAxis y = chart.getAxisLeft();
        y.setDrawGridLines(false);
        /*y.setEnabled(false);
        y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);*/

        chart.getAxisRight().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.animateXY(2000, 2000);
        chart.invalidate();

        //设置一页显示的数据条数，超出的数量需要滑动查看：
        chart.setVisibleXRangeMaximum(10);//需要在设置数据源后生效
        chart.setVisibleXRangeMinimum(1);//设置最少数量，不常用。
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
        //设置是否显示每个点的值
        lineDataSet.setDrawValues(false);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(true);
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
    public void setData(List<PhysicaldataListBeans.DataBean.ListBean> mList){
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < mList.size(); i++) {
            values.add(new Entry(i, Float.valueOf(mList.get(i).getBody_value())));
        }

        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(values, "");
        //LINEAR 折线图  CUBIC_BEZIER 圆滑曲线
        initLineDataSet(lineDataSet, 0xFFEF5B48, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        chart.setData(lineData);
    }
}
