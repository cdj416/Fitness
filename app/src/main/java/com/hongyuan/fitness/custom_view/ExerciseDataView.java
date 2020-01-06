package com.hongyuan.fitness.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;

public class ExerciseDataView extends RelativeLayout {

    private CustomActivity mActivity;

    private Drawable imageDrawble;
    private boolean isShow;
    private ImageView markImg;
    private TextView titleName,value1,value1Title,value2,value2Title;
    private View line;


    public ExerciseDataView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity)context;
        }

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PersonItem);
        imageDrawble = a.getDrawable(R.styleable.PersonItem_leftDrawable);
        isShow = a.getBoolean(R.styleable.PersonItem_showItemLine,false);

        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_exercise_data, this);
        markImg = view.findViewById(R.id.markImg);
        titleName = view.findViewById(R.id.titleName);
        value1 = view.findViewById(R.id.value1);
        value1Title = view.findViewById(R.id.value1Title);
        value2 = view.findViewById(R.id.value2);
        value2Title = view.findViewById(R.id.value2Title);
        line = view.findViewById(R.id.line);

        if(isShow){
            line.setVisibility(VISIBLE);
        }
        if(imageDrawble != null){
            markImg.setImageDrawable(imageDrawble);
        }
    }

    public void setValue1(String text){
        value1.setText(text);
    }
    public void setValue1Title(String text){
        value1Title.setText(text);
    }
    public void setValue2(String text){
        value2.setText(text);
    }
    public void setValue2Title(String text){
        value2Title.setText(text);
    }
    public void setTitleName(String text){
        titleName.setText(text);
    }
}
