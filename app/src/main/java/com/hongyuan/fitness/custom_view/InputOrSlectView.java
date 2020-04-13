package com.hongyuan.fitness.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.BaseUtil;

public class InputOrSlectView extends LinearLayout {

    //左边标题
    private TextView titleName;
    //右边选择内容
    private TextView selText;
    //输入内容
    private EditText edText;
    //switch
    private Switch twSwitch;
    //底部线
    private View line;
    //存储需要上传的值
    private TextView useValue;

    //控制类型
    private String isvName;
    private String isvHintText;
    private String isvRightText;
    private int isvLeftColor;
    private int isvRightColor;
    private boolean isvLineShow;
    private int isvType;
    private int isvInputType;

    //Switch开关控制回调接口
    public interface SwitchLinstener{
        void switchLinstener(boolean flag);
    }

    private SwitchLinstener switchLinstener;
    public void setSwitchLinstener(SwitchLinstener switchLinstener){
        this.switchLinstener = switchLinstener;
    }


    public InputOrSlectView(Context context) {
        super(context);
        initLayoutView();
    }

    public InputOrSlectView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InputOrSlectView);
        isvName = a.getString(R.styleable.InputOrSlectView_isvName);
        isvRightText = a.getString(R.styleable.InputOrSlectView_isvRightText);
        isvHintText = a.getString(R.styleable.InputOrSlectView_isvHintText);
        isvLineShow = a.getBoolean(R.styleable.InputOrSlectView_isvLineShow,false);
        isvType = a.getInt(R.styleable.InputOrSlectView_isvType,0);
        isvInputType = a.getInt(R.styleable.InputOrSlectView_isvInputType,0);
        isvLeftColor = a.getInt(R.styleable.InputOrSlectView_isvLeftColor,getResources().getColor(R.color.color_FF999999));
        isvRightColor = a.getInt(R.styleable.InputOrSlectView_isvRightColor,getResources().getColor(R.color.color_FF333333));
        initLayoutView();
    }

    public InputOrSlectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    //初始化控件
    private void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_input_or_select, this);
        titleName = view.findViewById(R.id.titleName);
        selText = view.findViewById(R.id.selText);
        edText = view.findViewById(R.id.edText);
        twSwitch = view.findViewById(R.id.twSwitch);
        line = view.findViewById(R.id.line);
        useValue = view.findViewById(R.id.useValue);

        setTitleName(isvName);
        setHintText(isvHintText);
        setLine(isvLineShow);
        setShowType(isvType);
        //设置输入法类型
        setInputType(isvInputType);
        //设置右边显示值
        if(isvType == 1){
            setRightText(isvRightText);
        }

        titleName.setTextColor(isvLeftColor);
        selText.setTextColor(isvRightColor);
        edText.setTextColor(isvRightColor);
    }

    /*
    * 设置标题显示
    * */
    private void setTitleName(String isvName){
        if(BaseUtil.isValue(isvName)) titleName.setText(isvName);
    }

    /*
    * 设置输入框提示内容
    * */
    private void setHintText(String isvHintText){
        if(BaseUtil.isValue(isvHintText)) edText.setHint(isvHintText);
    }

    /*
    * 设置是否显示底部线
    * */
    private void setLine(boolean isvLineShow){
        if(isvLineShow){
            line.setVisibility(VISIBLE);
        }else{
            line.setVisibility(GONE);
        }
    }

    /*
    * 设置输入法类型
    * */
    private void setInputType(int isvInputType){
        if(isvInputType == 0){
            edText.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        if(isvInputType == 1){
            edText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        if(isvInputType == 2){
            edText.setInputType(InputType.TYPE_CLASS_PHONE);
        }

    }

    /*
    * 设置显示类型
    * */
    private void setShowType(int isvType){
        if(isvType == 0){
            edText.setVisibility(VISIBLE);
            selText.setVisibility(GONE);
            twSwitch.setVisibility(GONE);
        }

        if(isvType == 1){
            edText.setVisibility(GONE);
            selText.setVisibility(VISIBLE);
            twSwitch.setVisibility(GONE);
        }

        if(isvType == 2){
            edText.setVisibility(GONE);
            selText.setVisibility(GONE);
            twSwitch.setVisibility(VISIBLE);

            twSwitch.setOnClickListener(view -> {
                if(switchLinstener != null){
                    switchLinstener.switchLinstener(twSwitch.isChecked());
                }
            });
        }
    }

    /*****************************外部设置值******************************************/
    /*
    * 设置可选择种的显示值
    * */
    public void setRightText(String content){
        selText.setText(content);
        setShowType(1);
    }

    /*
    * content:editext的显示值
    * flag:是否可编辑
    * */
    public void setRightText(String content, boolean flag){
        edText.setText(content);
        edText.setEnabled(flag);
        setShowType(0);
    }

    /*
    * 设置需要上传的数据值
    * */
    public void setUseValue(String content){
        useValue.setTag(content);
    }

    /*
    * 获取右边显示值
    * */
    public String getRightText(){
        if(isvType == 0){
            setUseValue(edText.getText().toString());
        }
        return String.valueOf(useValue.getTag());
    }

    /*
    * 控制switch开启和未开启
    * */
    public void setTwSwitch(boolean flag){
        twSwitch.setChecked(flag);
    }
}
