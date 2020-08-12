package com.hongyuan.fitness.custom_view.filter_view.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.TextView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.util.SkinConstants;

/**
 * author: baiiu
 * date: on 16/1/17 21:04
 * description:
 */

public class FilterCheckedTextView extends TextView implements Checkable {
    private boolean mChecked;
    private CustomActivity mContext;
    public FilterCheckedTextView(Context context) {
        this(context, null);
    }

    public FilterCheckedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if(context instanceof CustomActivity){
            mContext = (CustomActivity) context;

            if(mContext.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                Drawable drawable = getResources().getDrawable(R.drawable.selector_filter_left);
                setBackground(drawable);
            }
            if(mContext.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                Drawable drawable = getResources().getDrawable(R.drawable.selector_filter_left_black);
                setBackground(drawable);
            }

        }
    }

    @Override
    public void setChecked(boolean checked) {
        if (checked != mChecked) {
            mChecked = checked;
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}

