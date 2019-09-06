package com.hongyuan.fitness.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongyuan.fitness.R;

public class SubAddNumView extends LinearLayout {

    private ImageView subNum,addNum;
    private TextView goodNum;

    //可选的最大数量，和最小数量
    private int mixNum,maxNum;

    //数量加减的回调
    public interface NumChange{
        void changeNum(String num);
    }
    private NumChange numChange;

    public SubAddNumView(Context context) {
        super(context);
        initLayoutView();
    }

    public SubAddNumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public SubAddNumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_sub_add_num, this);
        subNum = view.findViewById(R.id.subNum);
        addNum = view.findViewById(R.id.addNum);
        goodNum = view.findViewById(R.id.goodNum);


        subNum.setOnClickListener(v1 -> {
            int num = Integer.valueOf(goodNum.getText().toString());
            num--;
            if(num < mixNum){
                num = mixNum;
            }
            goodNum.setText(String.valueOf(num));

            if(numChange != null){
                numChange.changeNum(goodNum.getText().toString());
            }

        });
        addNum.setOnClickListener(v1 -> {
            int num = Integer.valueOf(goodNum.getText().toString());
            num++;
            if(num > maxNum){
                num = maxNum;
            }
            goodNum.setText(String.valueOf(num));

            if(numChange != null){
                numChange.changeNum(goodNum.getText().toString());
            }
        });
    }

    /*
    * 初始化可选数量
    * */
    public void setMiMaNum(int miMaNum, int maxNum, NumChange numChange){
        this.maxNum = maxNum;
        this.mixNum = miMaNum;
        this.numChange = numChange;
        goodNum.setText(String.valueOf(mixNum));
    }

    /*
    * 获取当前选中数量
    * */
    public int getSelectNum(){
        return Integer.valueOf(goodNum.getText().toString());
    }

}
