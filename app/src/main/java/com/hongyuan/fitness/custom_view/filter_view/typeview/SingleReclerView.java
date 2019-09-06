package com.hongyuan.fitness.custom_view.filter_view.typeview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.filter_view.adapter.SingleReclerAdapter;
import com.hongyuan.fitness.custom_view.filter_view.util.UIUtil;

import java.util.List;

/**
 * 单list
 */
public class SingleReclerView<DATA> extends LinearLayout {

    private SingleReclerAdapter<DATA> adapter;
    private RecyclerView mRecycler;

    public SingleReclerView(Context context) {
        this(context, null);
    }

    public SingleReclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SingleReclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.view_filter_single_list, this);

        mRecycler = findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(adapter);
    }

    /*
    * 设置recyler的固定高度
    * */
    public void setMyHeight(){
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) mRecycler.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
        linearParams.height = UIUtil.dp(getContext(),343);// 控件的宽强制设成30
        mRecycler.setLayoutParams(linearParams);
    }

    /*
    * 初始化适配器
    * */
    public SingleReclerView<DATA> setMyAdapter(SingleReclerAdapter<DATA> adapter) {
        this.adapter = adapter;
        mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            if(itemClickListener != null){
                itemClickListener.onItemClick(position,adapter);
            }
        });
        return this;
    }

    /*
    * 初始化数据
    * */
    public void setList(List<DATA> mList){
        if(mList.size() > 7){
            setMyHeight();
        }
        adapter.setNewData(mList);
    }

    /***************************************接口回调********************************************/

    private OnItemClickListener itemClickListener;

    //点击事件
    public interface OnItemClickListener<DATA>{
        void onItemClick(int position,SingleReclerAdapter<DATA> adapter);
    }

    public SingleReclerView<DATA> setItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
        return this;
    }

}
