package com.hongyuan.fitness.ui.store.store_list;

import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class.VtwoGroupClassBeans;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsBeans;
import com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home.VtwoStarCoachBean;
import com.hongyuan.fitness.ui.store.StoreDetailBean;

public class StoreListFragment extends CustomFragment {

    private StoreListRecyclerItemView recItem;
    private String os_id = "";
    private String _type = "";
    private VtwoStarCoachBean starCoachBean;
    private VtwoPrivateLessonsBeans vtwoPrivateLessonsBeans;
    private VtwoGroupClassBeans botiqueBeans;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_store_list;
    }

    @Override
    public void initView(View mView) {
        os_id = getFragType("os_id");
        _type = getFragType("_type");

        setEnableRefresh(true);
        if(!_type.equals("5")){
            setEnableLoadMore(true);
        }

        recItem = mView.findViewById(R.id.recItem);
    }

    @Override
    protected void lazyLoad() {
        if(_type.equals("1")){
            //读取门店的教练
            clearParams().setParams("os_ids",os_id);
            Controller.myRequest(Constants.GET_COACH_LIST,Controller.TYPE_POST,getParams(), VtwoStarCoachBean.class,this);
        }
        if(_type.equals("2")){
            //课程--私教课列表(已替换成新接口)
            clearParams().setParams("os_ids",os_id);
            Controller.myRequest(Constants.GET_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), VtwoPrivateLessonsBeans.class,this);
        }
        if(_type.equals("3")){
            //获取门店的团课(已替换成新接口)
            clearParams().setParams("os_ids",os_id);
            Controller.myRequest(Constants.GET_TWO_COURSE_SUPER_LIST,Controller.TYPE_POST,getParams(), VtwoGroupClassBeans.class,this);
        }
        if(_type.equals("4")){

        }
        if(_type.equals("5")){
            //读取门店详情
            clearParams().setParams("os_id",os_id);
            Controller.myRequest(Constants.GET_OFFLINE_STORE_INFO,Controller.TYPE_POST,getParams(), StoreDetailBean.class,this);
        }
    }

    /*
     * 加载更多
     * */
    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    //刷新数据
    @Override
    public void refreshData() {
        lazyLoad();
    }

    @Override
    public void onSuccess(Object data) {
        //设置明星教练
        if(data instanceof VtwoStarCoachBean){
            VtwoStarCoachBean pageData = (VtwoStarCoachBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    starCoachBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    starCoachBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(starCoachBean != null && starCoachBean.getData() != null &&
                    starCoachBean.getData().getList() != null &&
                    starCoachBean.getData().getList().size() > 0){
                recItem.setStarCoach(starCoachBean.getData().getList());
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }

        //设置私教课
        if(data instanceof VtwoPrivateLessonsBeans){
            VtwoPrivateLessonsBeans pageData = (VtwoPrivateLessonsBeans)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    vtwoPrivateLessonsBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    vtwoPrivateLessonsBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(vtwoPrivateLessonsBeans != null && vtwoPrivateLessonsBeans.getData() != null &&
                    vtwoPrivateLessonsBeans.getData().getList() != null &&
                    vtwoPrivateLessonsBeans.getData().getList().size() > 0){
                recItem.setPriviteLeass(vtwoPrivateLessonsBeans.getData().getList());
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }

        //设置精品团课
        if(data instanceof VtwoGroupClassBeans){
            VtwoGroupClassBeans pageData = (VtwoGroupClassBeans)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    botiqueBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    botiqueBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(botiqueBeans != null && botiqueBeans.getData() != null &&
                    botiqueBeans.getData().getList() != null &&
                    botiqueBeans.getData().getList().size() > 0){
                recItem.setBoutiqueGroup(botiqueBeans.getData().getList());
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }

        if(data instanceof StoreDetailBean){
            StoreDetailBean detailBean = (StoreDetailBean)data;
            if(detailBean.getData() == null || detailBean.getData().getBanner() == null ||
                    detailBean.getData().getBanner().size() <= 0){
                setPromtView(SHOW_EMPTY);
            }else{
                recItem.setStoreImg(detailBean.getData().getBanner());
            }

        }
    }
}
