package com.hongyuan.fitness.ui.shop.smyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sinterface.MyClickListener;

public class SCartTitleRightView extends LinearLayout {

    private TextView operatText;
    private ImageView message;
    private MyClickListener clickListener;

    public SCartTitleRightView(Context context,MyClickListener clickListener){
        super(context);
        this.clickListener = clickListener;
        initLayoutView();
    }

    public SCartTitleRightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_cart_title_right, this);
        operatText = view.findViewById(R.id.operatText);
        message = view.findViewById(R.id.message);

        operatText.setOnClickListener(v -> {
            clickListener.onMyClick(v);
        });
        message.setOnClickListener(v -> {
            clickListener.onMyClick(v);
        });
    }

    /*
    * 设置显示文字内容
    * */
    public void setOperatText(String showText){
        operatText.setText(showText);
    }
}
