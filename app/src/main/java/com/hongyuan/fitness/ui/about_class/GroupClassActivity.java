package com.hongyuan.fitness.ui.about_class;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.ui.about_class.course_search.CourseSearchActivity;
import com.hongyuan.fitness.util.SkinConstants;

public class GroupClassActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_groupclass;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin)){
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"团体课");
            getMainTitle().setRightImage(R.mipmap.theme_search_chengse_mark);
        }

        if(SkinConstants.SKIN_NAME.BLACK.equals(skin)){
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"团体课");
            getMainTitle().setRightImage(R.mipmap.theme_search_chengse_mark_black);
        }

        getMainTitle().getRightView().setOnClickListener(v -> {
            startActivity(CourseSearchActivity.class,null);
        });
    }
}
