package com.hongyuan.fitness.ui.out_door.run.run_plan;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.util.TimeUtil;

public class RunThirdFragment extends CustomFragment {

    private ImageView sub,add;
    private TextView showText;

    private int baseData = 1;
    private int nowData = baseData;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_run_third;
    }

    @Override
    public void initView(View mView) {
        setCustomBg(R.color.transparent);

        sub = mView.findViewById(R.id.sub);
        add = mView.findViewById(R.id.add);
        showText = mView.findViewById(R.id.showText);

        sub.setOnClickListener(v -> {
            nowData = nowData - baseData;
            if(nowData < baseData){
                nowData = baseData;
            }
            showText.setText(nowData+".00km");
        });

        add.setOnClickListener(v ->{
            nowData = nowData + baseData;
            showText.setText(nowData+".00km");
        });
    }

    @Override
    public void onSuccess(Object data) {

    }
}
