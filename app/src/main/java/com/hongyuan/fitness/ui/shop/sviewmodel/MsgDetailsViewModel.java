package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMsgDetailsBinding;
import com.hongyuan.fitness.ui.person.fix.SixPriviteCourseActivity;
import com.hongyuan.fitness.ui.person.newedition.activity.GroupCourseOrdersActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopNewOrderAcitivity;
import com.hongyuan.fitness.ui.shop.sadapter.MsgDetailsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.MsgDetailsBeans;
import com.hongyuan.fitness.ui.webview.WebViewActivity;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.JumpUtils;

import java.util.List;

public class MsgDetailsViewModel extends CustomViewModel {

    private ActivityMsgDetailsBinding binding;
    private MsgDetailsAdapter adapter;

    private List<MsgDetailsBeans.DataBean.ListBean> mList;

    //消息类型id,1:系统私信,3:取商城私信,4:课程私信,5:赛事私信,6:场馆私信
    private String msg_category_id;

    public MsgDetailsViewModel(CustomActivity mActivity, ActivityMsgDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        msg_category_id = getBundle().getString("msg_category_id");

        setEnableLoadMore(true);
        setEnableRefresh(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new MsgDetailsAdapter(Integer.parseInt(msg_category_id));
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRec));
        adapter.setOnItemChildClickListener((adapter, view, position) -> {

            /*if(Integer.parseInt(msg_category_id) == 1 || Integer.parseInt(msg_category_id) == 6 || Integer.parseInt(msg_category_id) == 5){

                if(mList.get(position).getHref_id() != 0){//不为0的时候表示可以跳转，跳转到h5
                    Bundle bundle = new Bundle();
                    bundle.putString("url", mList.get(position).getHref());
                    bundle.putString("title","");
                    mActivity.startActivity(WebViewActivity.class,bundle);
                }

            }else if(Integer.parseInt(msg_category_id) == 3){
                mActivity.startActivity(ShopNewOrderAcitivity.class,null);
            }else if(Integer.parseInt(msg_category_id) == 4){

                if(mList.get(position).getHref_type() == 1){//1原生2h5
                    if("cpa".equals(mList.get(position).getHref_code())){
                        startActivity(SixPriviteCourseActivity.class,null);
                    }else if("super".equals(mList.get(position).getHref_code())){
                        startActivity(GroupCourseOrdersActivity.class,null);
                    }else{
                        CustomDialog.showMessage(mActivity,"未知去向！");
                    }
                }else if(mList.get(position).getHref_type() == 2){
                    Bundle bundle = new Bundle();
                    bundle.putString("url", mList.get(position).getHref());
                    bundle.putString("title","");
                    mActivity.startActivity(WebViewActivity.class,bundle);
                }else{
                    CustomDialog.showMessage(mActivity,"未知类型！");
                }

            }else{
                CustomDialog.showMessage(mActivity,"未知msg_category_id！");
            }*/

            jumpPage(mList.get(position));
        });
    }

    /*
    * 跳转约定
    * */
    public void jumpPage(MsgDetailsBeans.DataBean.ListBean bean){
        JumpUtils.JumpBeans jumpBeans = new JumpUtils.JumpBeans();
        jumpBeans.setImg_href_type(bean.getHref_type());
        jumpBeans.setImg_href(bean.getHref());
        jumpBeans.setHref_code(bean.getHref_code());
        jumpBeans.setHref_id(String.valueOf(bean.getHref_id()));

        JumpUtils.goAtherPage(mActivity,jumpBeans);
    }

    @Override
    public void refreshData(){
        curPage = FIRST_PAGE;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }
    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("msg_category_id",msg_category_id);
        Controller.myRequest(Constants.GET_MSG_LIST,Controller.TYPE_POST,getParams(), MsgDetailsBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof MsgDetailsBeans){

            List<MsgDetailsBeans.DataBean.ListBean> list = ((MsgDetailsBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    mActivity.refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }

        }
    }
}
