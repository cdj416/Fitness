package com.hongyuan.fitness.custom_view.filter_view.typeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.filter_view.adapter.BaseBaseAdapter;
import com.hongyuan.fitness.custom_view.filter_view.adapter.DoubleListAdapter;
import com.hongyuan.fitness.custom_view.filter_view.adapter.SimpleTextAdapter;
import com.hongyuan.fitness.custom_view.filter_view.util.CommonUtil;

import java.util.List;

/**
 * 双列ListView
 */
public class DoubleListView<LEFTD, RIGHTD> extends LinearLayout implements AdapterView.OnItemClickListener {

    private BaseBaseAdapter<LEFTD> mLeftAdapter;
    private BaseBaseAdapter<RIGHTD> mRightAdapter;
    private ListView lv_left;
    private ListView lv_right;
    private Button submit,reset;

    public DoubleListView(Context context) {
        this(context, null);
    }

    public DoubleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DoubleListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.view_filter_double_list, this);

        lv_left = findViewById(R.id.lv_left);
        lv_right = findViewById(R.id.lv_right);
        submit = findViewById(R.id.submit);
        reset = findViewById(R.id.reset);


        lv_left.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv_right.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lv_left.setOnItemClickListener(this);
        lv_right.setOnItemClickListener(this);

        //重置
        reset.setOnClickListener(v -> {
            if(mOnRestClickListener != null){
                mOnRestClickListener.onRestClick(mRightAdapter.getList(),this);
            }
        });
        //重置
        submit.setOnClickListener(v -> {
            if(mOnInquireClickListener != null){
                mOnInquireClickListener.onInquireClickListener(mRightAdapter.getList());
            }
        });
    }


    public DoubleListView<LEFTD, RIGHTD> leftAdapter(SimpleTextAdapter<LEFTD> leftAdapter) {
        mLeftAdapter = leftAdapter;
        lv_left.setAdapter(leftAdapter);
        return this;
    }

    public DoubleListView<LEFTD, RIGHTD> rightAdapter(DoubleListAdapter<RIGHTD> rightAdapter) {
        mRightAdapter = rightAdapter;
        lv_right.setAdapter(rightAdapter);
        return this;
    }

    public void setLeftList(List<LEFTD> list, int checkedPosition) {
        mLeftAdapter.setList(list);

        if (checkedPosition != -1) {
//            lv_left.performItemClick(mLeftAdapter.getView(checkedPositoin, null, null), checkedPositoin, mLeftAdapter.getItemId(checkedPositoin));//调用此方法相当于点击.第一次进来时会触发重复加载.
            lv_left.setItemChecked(checkedPosition, true);
        }
    }

    public void setRightList(List<RIGHTD> list, int checkedPosition) {
        mRightAdapter.setList(list);
        if (checkedPosition != -1) {
            lv_right.setItemChecked(checkedPosition, true);
        }
    }

    private OnLeftItemClickListener<LEFTD> mOnLeftItemClickListener;
    private OnRightItemClickListener<LEFTD, RIGHTD> mOnRightItemClickListener;
    //重置监听
    private OnRestClickListener mOnRestClickListener;
    //开始查询
    private OnInquireClickListener mOnInquireClickListener;

    public interface OnLeftItemClickListener<LEFTD> {
        void provideRightList(LEFTD item,int position);
    }

    public interface OnRightItemClickListener<LEFTD, RIGHTD> {
        void onRightItemClick(LEFTD item, RIGHTD childItem);
    }

    //重置监听接口
    public interface OnRestClickListener<LEFTD,RIGHTD>{
        void onRestClick(List<RIGHTD> rightList,DoubleListView<LEFTD, RIGHTD> listView);
    }

    //开始查询接口
    public interface OnInquireClickListener<RIGHTD>{
        void onInquireClickListener(List<RIGHTD> rightList);
    }

    public DoubleListView<LEFTD, RIGHTD> onLeftItemClickListener(OnLeftItemClickListener<LEFTD> onLeftItemClickListener) {
        this.mOnLeftItemClickListener = onLeftItemClickListener;
        return this;
    }

    public DoubleListView<LEFTD, RIGHTD> onRestClickListener(OnRestClickListener<LEFTD,RIGHTD> mOnRestClickListener){
        this.mOnRestClickListener = mOnRestClickListener;
        return this;
    }

    public DoubleListView<LEFTD, RIGHTD> onInquireClickListener(OnInquireClickListener<RIGHTD> mOnInquireClickListener){
        this.mOnInquireClickListener = mOnInquireClickListener;
        return this;
    }

    public DoubleListView<LEFTD, RIGHTD> onRightItemClickListener(OnRightItemClickListener<LEFTD, RIGHTD> onRightItemClickListener) {
        this.mOnRightItemClickListener = onRightItemClickListener;
        return this;
    }

    public ListView getLeftListView() {
        return lv_left;
    }

    public ListView getRightListView() {
        return lv_right;
    }

    //========================点击事件===================================
    private int mRightLastChecked;
    private int mLeftLastPosition;
    private int mLeftLastCheckedPosition;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (CommonUtil.isFastDoubleClick()) {
            return;
        }

        if (mLeftAdapter == null || mRightAdapter == null) {
            return;
        }

        if (parent == lv_left) {
            mLeftLastPosition = position;

            if (mOnLeftItemClickListener != null) {
                LEFTD item = mLeftAdapter.getItem(position);
                mOnLeftItemClickListener.provideRightList(item, position);
            }

            lv_right.setItemChecked(mRightLastChecked, mLeftLastCheckedPosition == position);
        } else {
            mLeftLastCheckedPosition = mLeftLastPosition;
            mRightLastChecked = position;

            if (mOnRightItemClickListener != null) {
                mOnRightItemClickListener.onRightItemClick(mLeftAdapter.getItem(mLeftLastCheckedPosition), mRightAdapter.getItem(mRightLastChecked));
                mRightAdapter.notifyDataSetChanged();
            }
        }
    }

    /*
    * 刷新数据
    * */
    public void notifyDataChanged(){
        mRightAdapter.notifyDataSetChanged();
    }
}
