package com.hongyuan.fitness.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.ruler_selector.RulerView;

public class MyRulerView extends RelativeLayout {

    private RulerView ruler;

    public interface SelectChange{
        void valueChange(int whichCode,float value);
    }

    public MyRulerView(Context context) {
        super(context);
        initLayoutView();
    }

    public MyRulerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public MyRulerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_ruler, this);
        ruler = view.findViewById(R.id.ruler);

    }

    public void setDataRuler(int whichCode,int selectNum,int minValue,int maxValue,SelectChange selectChange){
        //设置尺码选择器的基础值
        ruler.setValue(selectNum, minValue, maxValue, 1);
        //尺码选择器的回调
        ruler.setOnValueChangeListener(value -> {
            selectChange.valueChange(whichCode,value);
        });
    }
}
