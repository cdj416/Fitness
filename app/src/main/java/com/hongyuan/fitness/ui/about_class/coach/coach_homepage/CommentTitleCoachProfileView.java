package com.hongyuan.fitness.ui.about_class.coach.coach_homepage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.hongyuan.fitness.R;

public class CommentTitleCoachProfileView extends LinearLayout {


    public CommentTitleCoachProfileView(Context context) {
        super(context);
        initLayoutView();
    }

    public CommentTitleCoachProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public CommentTitleCoachProfileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_comment_title, this);

    }


}
