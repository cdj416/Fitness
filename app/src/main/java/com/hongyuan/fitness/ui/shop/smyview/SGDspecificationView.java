package com.hongyuan.fitness.ui.shop.smyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sadapter.SGDspecificationAdapter;
import java.util.ArrayList;
import java.util.List;

public class SGDspecificationView extends LinearLayout {

    private RecyclerView mRec;
    private  SGDspecificationAdapter adapter;

    public SGDspecificationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_sgd_specification, this);

        mRec = view.findViewById(R.id.mRec);
        LinearLayoutManager comManager = new LinearLayoutManager(getContext());
        comManager.setOrientation(RecyclerView.VERTICAL);
        mRec.setLayoutManager(comManager);
        adapter = new SGDspecificationAdapter();
        mRec.setAdapter(adapter);
        adapter.setNewData(getList());

        adapter.setFooterView(getFooter());
    }

    private View getFooter(){
        //加载数量加减试图
        View footer = LayoutInflater.from(getContext())
                .inflate(R.layout.view_footer_add_num, (ViewGroup) mRec.getParent(), false);
        TextView addNum = footer.findViewById(R.id.addNum);
        TextView numText = footer.findViewById(R.id.numText);
        TextView subNum = footer.findViewById(R.id.subNum);
        addNum.setOnClickListener(v -> {
            int showNum = Integer.valueOf(numText.getText().toString());
            numText.setText(String.valueOf(showNum+1));
        });
        subNum.setOnClickListener(v -> {
            int showNum = Integer.valueOf(numText.getText().toString());
            if(showNum > 1){
                numText.setText(String.valueOf(showNum-1));
            }else{
                numText.setText("1");
            }
        });

        return footer;
    }

    private List<BaseBean> getList(){
        List<BaseBean> mList = new ArrayList<>();
        for(int i = 0 ; i < 2 ; i++){
            mList.add(new BaseBean());
        }
        return mList;
    }
}
