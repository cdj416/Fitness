package com.hongyuan.fitness.ui.out_door.run.run_plan;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.util.TimeUtil;

public class RunTwoFragment extends CustomFragment {

    private ImageView subTime,addTime;
    private TextView timeText;

    //基础时间值为5分钟
    private long baseTiem = 300000;
    //目前时间值
    private long nowTime;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_run_two;
    }

    @Override
    public void initView(View mView) {
        setCustomBg(R.color.transparent);
        subTime = mView.findViewById(R.id.subTime);
        addTime = mView.findViewById(R.id.addTime);
        timeText = mView.findViewById(R.id.timeText);

        nowTime = baseTiem;

        subTime.setOnClickListener(v -> {
            nowTime = nowTime - baseTiem;
            if(nowTime < baseTiem){
                nowTime = baseTiem;
            }
            timeText.setText(TimeUtil.getFriendlyMusicDuration(nowTime));
        });

        addTime.setOnClickListener(v ->{
            nowTime = nowTime + baseTiem;
            timeText.setText(TimeUtil.getFriendlyMusicDuration(nowTime));
        });


    }

    @Override
    public void onSuccess(Object data) {

    }

}
