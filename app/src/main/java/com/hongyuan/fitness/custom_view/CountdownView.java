package com.hongyuan.fitness.custom_view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;

public class CountdownView extends RelativeLayout {

    private TextView showText;
    private Animation mAnimation;
    private int count = 5;
    private Dialog dialog;

    public interface AnimationListenr{
        void carryOut();
    }

    private AnimationListenr animationListenr;


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {

                if(count>0)
                {
                    showText.setText("" + count);
                    handler.sendEmptyMessageDelayed(0, 1000);
                    addMyAnimation();
                    count--;
                }else {
                    showText.setVisibility(GONE);
                    dialog.dismiss();
                    if(animationListenr != null){
                        animationListenr.carryOut();
                    }
                }

            }
        }
    };

    public CountdownView(Context context) {
        super(context);
        initLayoutView();
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    private void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_countdown, this);
        showText = view.findViewById(R.id.showText);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.count_down_exit);
    }

    private void addMyAnimation() {
        mAnimation.reset();
        showText.startAnimation(mAnimation);
    }

    public void startAnimation(AnimationListenr animationListenr,Dialog dialog){
        this.dialog = dialog;
        this.animationListenr = animationListenr;
        handler.sendEmptyMessageDelayed(0, 1000);
    }

}
