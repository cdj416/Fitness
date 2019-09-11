package com.hongyuan.fitness.custom_view.share_view;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;

public class ShareView extends RelativeLayout{

    private CustomActivity mActivity;

    private Drawable showImageDrawble;
    private ImageView showImg;



    public ShareView(Context context) {
        super(context);
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity)context;
        }
        initLayoutView();
    }

    public ShareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity)context;
        }
        initLayoutView();
    }

    public ShareView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity)context;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShareView);
        showImageDrawble = a.getDrawable(R.styleable.ShareView_shareShowImg);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_share, this);
        showImg = view.findViewById(R.id.showImg);

        if(showImageDrawble != null){
            showImg.setImageDrawable(showImageDrawble);
        }
        showImg.setOnClickListener(v -> {
            //selectLocation(getContext());
            ShareUtil.showShare(mActivity);
        });
    }

}
