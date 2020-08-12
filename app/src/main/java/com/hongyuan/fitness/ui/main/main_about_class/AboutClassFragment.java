package com.hongyuan.fitness.ui.main.main_about_class;

import android.view.View;
import android.widget.ImageView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.about_class.course_search.CourseSearchActivity;
import com.hongyuan.fitness.util.SkinConstants;

public class AboutClassFragment extends CustomFragment {

    private ImageView searchMark,blackThem;
    private TabLayout layoutMenu;
    private ViewPager mViewPager;
    private AboutClassViewPagerAdapter meunAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about_class;
    }

    @Override
    public void initView(View mView) {

        blackThem = mView.findViewById(R.id.blackThem);
        searchMark = mView.findViewById(R.id.searchMark);
        mViewPager = mView.findViewById(R.id.mViewPager);
        layoutMenu = mView.findViewById(R.id.layoutMenu);

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(mActivity.skin))
            blackThem.setImageResource(R.mipmap.back_common);
        if(SkinConstants.SKIN_NAME.BLACK.equals(mActivity.skin))
            blackThem.setImageResource(R.mipmap.white_common);

        meunAdapter = new AboutClassViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(meunAdapter);
        layoutMenu.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(mActivity.getBundle().getInt("position"));
        mViewPager.setOffscreenPageLimit(2);

        searchMark.setOnClickListener(v -> startActivity(CourseSearchActivity.class,null));
        blackThem.setOnClickListener(v -> mActivity.finish());
    }

    @Override
    public void onSuccess(Object data) {

    }

    /*
     * 控制跳转到私教课页面
     * */
    /*@Subscribe(id = ConstantsCode.EB_SHOW_PRIVITE)
    public void starPriviteCourse(String message) {
        mViewPager.setCurrentItem(Integer.valueOf(message));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }*/
}
