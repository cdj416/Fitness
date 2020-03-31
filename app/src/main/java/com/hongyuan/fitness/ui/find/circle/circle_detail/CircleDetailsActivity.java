package com.hongyuan.fitness.ui.find.circle.circle_detail;

import android.view.View;
import android.widget.Button;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCircleDetailsBinding;

public class CircleDetailsActivity extends CustomActivity {

    private CircleDetailsViewModel viewModel;
    private Button goEdit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_circle_details;
    }

    @Override
    public int getBottomLayoutId() {
        return R.layout.activity_bottom_circledetails_view;
    }

    @Override
    protected void initBottomView(View bottomChildView) {
        goEdit = bottomChildView.findViewById(R.id.goEdit);
        goEdit.setOnClickListener(v -> {
            viewModel.goEdit();
        });
    }

    @Override
    protected void initView() {
        setTitle("话题详情");
        setsetImmersive(0x55000000);
        ActivityCircleDetailsBinding binding = ActivityCircleDetailsBinding.bind(mView);
        viewModel = new CircleDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
