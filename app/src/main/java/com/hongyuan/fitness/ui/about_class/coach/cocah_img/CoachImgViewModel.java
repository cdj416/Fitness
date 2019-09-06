package com.hongyuan.fitness.ui.about_class.coach.cocah_img;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityCoachPhotoBinding;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;

import java.util.ArrayList;
import java.util.List;

public class CoachImgViewModel extends CustomViewModel {

    private ActivityCoachPhotoBinding binding;
    private CocahImageAdapter adapter;
    private CoachImgBeans imgBeans;

    public CoachImgViewModel(CustomActivity mActivity, ActivityCoachPhotoBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);
        mActivity.getMainTitle().setCentreText(getBundle().getString("title"));
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        binding.mRecycler.setLayoutManager(layoutManager);
        adapter = new CocahImageAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            //点击查看大图功能
            GPreviewBuilder.from(mActivity)
                    .setData(getInfoList(imgBeans.getData().getList(),view))
                    .setCurrentIndex(position)
                    .setType(GPreviewBuilder.IndicatorType.Dot)
                    .start();
        });
    }

    /*
     * 获取图片集和图片所处位置
     * */
    private List<UserViewInfo> getInfoList(List<CoachImgBeans.DataBean.ListBean> list, View view){
        List<UserViewInfo> imgList = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            imgList.add(new UserViewInfo(list.get(i).getImg_src()));
            Rect bounds = new Rect();
            view.getGlobalVisibleRect(bounds);
            imgList.get(i).setBounds(bounds);
        }

        return imgList;
    }

    @Override
    public void refreshData(){
        imgBeans = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("coach_mid",getBundle().getString("coach_mid"))
                .setParams("photo_category_id",getBundle().getString("photo_category_id"));
        Controller.myRequest(Constants.GET_COACH_PHOTO_LIST,Controller.TYPE_POST,getParams(), CoachImgBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof CoachImgBeans){
            CoachImgBeans pageData = (CoachImgBeans)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    imgBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    imgBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(imgBeans != null && imgBeans.getData() != null &&
                    imgBeans.getData().getList() != null &&
                    imgBeans.getData().getList().size() > 0){
                adapter.setNewData(imgBeans.getData().getList());
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView( mActivity.SHOW_EMPTY);
            }
        }
    }
}
