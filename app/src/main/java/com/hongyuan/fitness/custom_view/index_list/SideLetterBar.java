package com.hongyuan.fitness.custom_view.index_list;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.hongyuan.fitness.R;

import java.util.List;

public class SideLetterBar extends View {
    private List<String> b = null;
    private int choose = -1;
    private Paint paint = new Paint();
    private boolean showBg = false;
    private OnLetterChangedListener onLetterChangedListener;
    private TextView overlay;

    public SideLetterBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SideLetterBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideLetterBar(Context context) {
        super(context);
    }

    /**
     * 设置悬浮的textview
     *
     * @param overlay
     */
    public void setOverlay(TextView overlay) {
        this.overlay = overlay;
    }

    @Override
    public void postInvalidate() {
        super.postInvalidate();
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(b == null){
            return;
        }

        if (showBg) {
            canvas.drawColor(Color.TRANSPARENT);
        }

        int height = getHeight();
        int width = getWidth();
        int singleHeight = 100;
        for (int i = 0; i < b.size(); i++) {
            paint.setTextSize(getResources().getDimension(R.dimen.sp_12));
            if (i == 0 || i == 1) {
                paint.setColor(Color.parseColor("#FFD24B"));
            } else {
                paint.setColor(Color.parseColor("#999999"));
            }
            paint.setAntiAlias(true);
            if (i == choose) {
                paint.setColor(Color.parseColor("#5c5c5c"));
                paint.setFakeBoldText(true);  //加粗
            }
            float xPos = width / 2 - paint.measureText(b.get(i)) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b.get(i), xPos, yPos, paint);
            paint.reset();
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnLetterChangedListener listener = onLetterChangedListener;
        final int c = (int) (y / getHeight() * b.size());

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                showBg = true;
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < b.size()) {
                        listener.onLetterChanged(b.get(c));
                        choose = c;
                        invalidate();
                        if (overlay != null) {
                            overlay.setVisibility(VISIBLE);
                            overlay.setText(b.get(c));
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < b.size()) {
                        listener.onLetterChanged(b.get(c));
                        choose = c;
                        invalidate();
                        if (overlay != null) {
                            overlay.setVisibility(VISIBLE);
                            overlay.setText(b.get(c));
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                showBg = false;
                choose = -1;
                invalidate();
                if (overlay != null) {
                    overlay.setVisibility(GONE);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setOnLetterChangedListener(OnLetterChangedListener onLetterChangedListener) {
        this.onLetterChangedListener = onLetterChangedListener;
    }

    public interface OnLetterChangedListener {
        void onLetterChanged(String letter);
    }

    /*
    * 从新绘制列表数据
    * */
    public void changeDate(List<String> titles){
        this.b = titles;
        postInvalidate();
    }

}
