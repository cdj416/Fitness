package com.hongyuan.fitness.ui.look_img;

import android.util.Log;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMyLookImgBinding;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.List;

public class MyLookImgViewModel extends CustomViewModel {

    private MyLookImgPagerAdapter meunAdapter;
    private ActivityMyLookImgBinding binding;
    private List<MyLookImgDataBeans> imgList;
    //当前显示的标题日期
    private String showTitle="";
    //当前照片位置
    private int nowPosition;

    public MyLookImgViewModel(CustomActivity mActivity, ActivityMyLookImgBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        imgList = getBundle().getParcelableArrayList("imgList");
        int mPsoition = getBundle().getInt("mPosition");
        nowPosition = mPsoition;
        showTitle = imgList.get(mPsoition).getShowTitle();
        mActivity.getMainTitle().setCenterTextColor(showTitle,mActivity.getResources().getColor(R.color.color_FFFFFF));
        mActivity.getMainTitle().setRightImage(R.mipmap.white_delete);
        mActivity.getMainTitle().getRightView().setOnClickListener(v -> {
            CustomDialog.promptDialog(mActivity, "确定要删除当前照片？", "在想想", "确定", false, new CustomDialog.DialogClick() {
                @Override
                public void dialogClick(View v) {
                    if(v.getId() == R.id.isCannel){
                        delImg(imgList.get(nowPosition).getImgId());
                    }
                }
            });
        });

        meunAdapter = new MyLookImgPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);

        if(imgList != null && imgList.size() > 0){
            meunAdapter.setData(imgList);
            binding.mViewPager.setOffscreenPageLimit(imgList.size());
            binding.mViewPager.setCurrentItem(mPsoition);
            binding.showPosition.setText((mPsoition+1)+"/"+imgList.size());
            binding.mViewPager.addOnPageChangeListener(getOnPageChangeListener());
        }


    }

    /*
     * viewPage页面变动监听
     * */
    private ViewPager.OnPageChangeListener getOnPageChangeListener(){
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                nowPosition = position;
                binding.showPosition.setText((position+1)+"/"+imgList.size());
                if(!showTitle.equals(imgList.get(position).getShowTitle())){
                    showTitle = imgList.get(position).getShowTitle();
                    mActivity.getMainTitle().setCentreText(showTitle);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }


    /*
    * 删除相片
    * */
    private void delImg(String id){
        mActivity.showLoading();
        clearParams().setParams("id",id);
        Controller.myRequest(ConstantsCode.DEL_BODY_IMG,Constants.DEL_BODY_IMG,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.DEL_BODY_IMG){
            mActivity.showSuccess("删除成功！");
        }
    }

    @Override
    public void onSuccess(Object data) {

    }
}
