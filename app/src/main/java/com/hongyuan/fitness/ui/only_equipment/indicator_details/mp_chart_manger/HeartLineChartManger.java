package com.hongyuan.fitness.ui.only_equipment.indicator_details.mp_chart_manger;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.yolanda.health.qnblesdk.bean.QNHeartRateItem;

import java.util.ArrayList;
import java.util.List;

public class HeartLineChartManger {
    private LineChart chart;

    public HeartLineChartManger(LineChart chart){
        this.chart = chart;
        setChart();
    }

    /*
     * 初始配置
     * */
    private void setChart(){

        //设置起始位置
        chart.setViewPortOffsets(0, 0, 0, 0);
        //设置背景颜色
        chart.setBackgroundColor(0X00000000);
        //设置为没有文字描述
        chart.getDescription().setEnabled(false);
        //设置是否可点击
        chart.setTouchEnabled(true);
        // 是否可以拖拽
        chart.setDragEnabled(false);
        // 是否可以缩放
        chart.setScaleEnabled(false);
        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);
        // 是否显示表格颜色
        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(300);
        //x轴线设置
        XAxis x = chart.getXAxis();
        x.setEnabled(false);
        //y轴线设置
        YAxis y = chart.getAxisLeft();
        //设置自定义字体样式
        //y.setTypeface(tfLight);
        //y轴上的标签的显示的个数（第二个参数表示是否强制启用指定个数）
        y.setLabelCount(4, false);
        //设置y轴字体颜色
        y.setTextColor(Color.parseColor("#FFFFFFFF"));
        //y标签相对于图表位置的枚举
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        //是否需要绘制网格线
        y.setDrawGridLines(false);
        //设置网格周围颜色
        y.setAxisLineColor(Color.WHITE);

        chart.getAxisRight().setEnabled(false);
        chart.getLegend().setEnabled(false);
        //设置动画类型
        chart.animateXY(2000, 2000);
        //刷新图纸
        chart.invalidate();
        //需要在设置数据源后生效（一页能看到的最大数据量）
        chart.setVisibleXRangeMaximum(10);
    }

    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     *
     * @param set1 线条
     */
    private void initLineDataSet(LineDataSet set1,int color,LineDataSet.Mode mode) {
        set1.setColor(color);
        set1.setCircleColor(color);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);

        set1.setDrawCircles(false);
        //设置是否显示每个点的值
        set1.setDrawValues(false);
        //设置曲线值的圆点是实心还是空心
        set1.setDrawCircleHole(true);
        set1.setValueTextSize(10f);
        //设置折线图填充
        set1.setDrawFilled(false);
        set1.setFormLineWidth(1f);
        set1.setFormSize(15.f);
        set1.setMode(mode);
    }

    /**
     * 设置线条填充背景颜色
     *
     * @param drawable
     */
    public void setChartFillDrawable(Drawable drawable) {
        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            LineDataSet lineDataSet = (LineDataSet) chart.getData().getDataSetByIndex(0);
            //避免在 initLineDataSet()方法中 设置了 lineDataSet.setDrawFilled(false); 而无法实现效果
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFillDrawable(drawable);
            chart.invalidate();
        }
    }

    /*
     * 设置显示值
     * */
    public void setData(List<QNHeartRateItem> mList){
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < mList.size(); i++) {
            values.add(new Entry(i, Float.valueOf(mList.get(i).getHeartRate())));
        }

        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(values, "");
        //LINEAR 折线图  CUBIC_BEZIER 圆滑曲线
        initLineDataSet(lineDataSet,Color.parseColor("#FFFFFFFF"),LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return chart.getAxisLeft().getAxisMinimum();
            }
        });
        LineData lineData = new LineData(lineDataSet);
        chart.setData(lineData);
        chart.invalidate();
    }
}
