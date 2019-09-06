package com.hongyuan.fitness.ui.main.main_about_class.private_lessons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.MessageEvent;
import com.hongyuan.fitness.custom_view.CustomRecyclerView;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachHomePageActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course.MyPriviteCourseBeans;
import com.hongyuan.fitness.ui.store.more_store.MoreStoreActivity;
import com.hongyuan.fitness.ui.store.more_store.StoreBean;
import com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course.MyPriviteCourseActivity;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.LocationBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PrivateLessonsFragment extends CustomFragment {

    private LinearLayout addressBox;
    private RelativeLayout myCourseBox,load_box;
    private TextView addressName;
    private RecyclerView mRecycler;
    private CustomRecyclerView menuRecycler;
    private PrivateLessonsAdapter adapter;
    private MeunPrivateLessonsAdapter meunAdapter;

    //菜单数据
    private MenuPrivateLessonsBean menuBean;
    //门店信息
    private DefaultStoreBean defaultStoreBean;
    //门店id
    private String myOs_id;
    //私教课信息
    private PrivateLessonsBean bean;
    //教练类型
    private String selectft_id = null;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_private_lessons;
    }

    @Override
    public void initView(View mView) {
        //开启刷新
        setEnableRefresh(true);
        //开启加载更多
        setEnableLoadMore(true);

        addressBox = mView.findViewById(R.id.addressBox);
        addressName = mView.findViewById(R.id.addressName);
        mRecycler = mView.findViewById(R.id.mRecycler);
        menuRecycler = mView.findViewById(R.id.menuRecycler);
        myCourseBox = mView.findViewById(R.id.myCourseBox);
        load_box = mView.findViewById(R.id.load_box);


        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        menuRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,24,0x00000000));
        menuRecycler.setLayoutManager(manager);

        meunAdapter = new MeunPrivateLessonsAdapter();
        menuRecycler.setAdapter(meunAdapter);
        meunAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            clearSelect();
            menuBean.getData().get(position).setSelect(true);
            meunAdapter.setNewData(menuBean.getData());
            selectft_id = String.valueOf(menuBean.getData().get(position).getFt_id());
            //去请求
            lazyLoad();
            //自动刷新
            //refresh.autoRefresh();
        });



        LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
        manager2.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager2);
        adapter = new PrivateLessonsAdapter();
        mRecycler.setAdapter(adapter);

        addressBox.setOnClickListener(v -> startActivity(MoreStoreActivity.class,null));

        myCourseBox.setOnClickListener(v -> startActivity(MyPriviteCourseActivity.class,null));

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("coach_mid",String.valueOf(bean.getData().getList().get(position).getM_id()));
            startActivity(CoachHomePageActivity.class,bundle);
        });
    }

    /*
    * 其他页面返回需要从新刷新的接口
    * */
    @Override
    public void refreshData() {
        if(mActivity.userToken.getM_mobile() != null){
            //查询是否已购买私教课
            Controller.myRequest(Constants.GET_MY_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), MyPriviteCourseBeans.class,this);
        }
    }

    /*
    * 只执行一次的接口
    * */
    @Override
    protected void lazyOnceLoad() {
        if(mActivity.userToken.getM_mobile() != null){
            //读取默认门店或者购买门店信息
            clearParams().setParams("lat", LocationBean.getInstance().getLat()).setParams("lng",LocationBean.getInstance().getLng());
            Controller.myRequest(Constants.GET_DEFAULT_OS,Controller.TYPE_POST,getParams(), DefaultStoreBean.class,this);

            //加载私教课类型
            clearParams();
            Controller.myRequest(Constants.GET_FIT_TYPE_LIST_ALL,Controller.TYPE_POST,getParams(), MenuPrivateLessonsBean.class,this);
        }
    }

    @Override
    public void loadMoreData() {
        getCoachType(selectft_id);
    }

    /*
    * 可以刷新的接口
    * */
    @Override
    protected void lazyLoad() {
        bean = null;
        if(myOs_id != null){
            getCoachType(selectft_id);
        }
    }

    /*
    * 请求不同类型的教练列表
    * */
    private void getCoachType(String ft_id){
        //获取门店的教练
        clearParams().setParams("os_id",myOs_id);
        if(ft_id != null){
           setParams("ft_id",ft_id);
        }
        Controller.myRequest(ConstantsCode.GET_OS_COACH_LIST,Constants.GET_OS_COACH_LIST,Controller.TYPE_POST,getParams(), PrivateLessonsBean.class,this);
    }


    @Override
    public void onSuccess(Object data) {
        if(data instanceof MyPriviteCourseBeans && isSuccess(data)){
            MyPriviteCourseBeans courseBeans = (MyPriviteCourseBeans)data;
            if(courseBeans.getData().getList() != null && courseBeans.getData().getList().size() > 0){
                myCourseBox.setVisibility(View.VISIBLE);
            }else{
                myCourseBox.setVisibility(View.GONE);
            }
        }

        if(data instanceof MenuPrivateLessonsBean && isSuccess(data)){
            menuBean = (MenuPrivateLessonsBean)data;
            MenuPrivateLessonsBean.DataBean dataBean = new MenuPrivateLessonsBean.DataBean();
            dataBean.setFt_id(0);
            dataBean.setFt_name("全部");
            dataBean.setSelect(true);
            menuBean.getData().add(0,dataBean);
            //更新下数据
            meunAdapter.setNewData(menuBean.getData());
        }

        if(data instanceof DefaultStoreBean && isSuccess(data)){
            defaultStoreBean = (DefaultStoreBean) data;
            //设置默认的门店id，其他地方需要
            LocationBean.getInstance().setOs_id(defaultStoreBean.getData().getOs_id());
            addressName.setText(defaultStoreBean.getData().getOs_name());
            myOs_id = String.valueOf(defaultStoreBean.getData().getOs_id());
            //获取门店的教练
            getCoachType(null);

            //通知其他地方店面改变
            //EventBus.getDefault().postSticky(new MessageEvent(defaultStoreBean.getData()));
        }

    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.GET_OS_COACH_LIST){
            PrivateLessonsBean pageData = (PrivateLessonsBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    bean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    bean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(bean != null && bean.getData() != null &&
                    bean.getData().getList() != null &&
                    bean.getData().getList().size() > 0){
                adapter.setNewData(bean.getData().getList());
                mRecycler.setVisibility(View.VISIBLE);
                load_box.setVisibility(View.GONE);
            }else{
                mRecycler.setVisibility(View.GONE);
                load_box.setVisibility(View.VISIBLE);
            }
        }
    }

    /*
    * 清空集合中的选中项
    * */
    private void clearSelect(){
        if(menuBean != null && menuBean.getData().size() > 0){
            for(MenuPrivateLessonsBean.DataBean meun : menuBean.getData()){
                meun.setSelect(false);
            }
        }
    }

    /*
    * 登录成功去刷新数据
    * */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(MessageEvent message) {
        if(message.getDataBean() == null){//登录成功之后需要去改变的数据
            //请求下数据
            onFragmentFirstVisible();
        }else if(message.getDataBean() instanceof StoreBean.DataBean.ListBean){//选择门店之后需要更改的
            StoreBean.DataBean.ListBean selectStoreBean = (StoreBean.DataBean.ListBean)message.getDataBean();
            LocationBean.getInstance().setOs_id(selectStoreBean.getOs_id());
            addressName.setText(selectStoreBean.getOs_name());
            myOs_id = String.valueOf(selectStoreBean.getOs_id());
            //清空下，不然会有bug
            bean = null;
            //获取门店的教练
            getCoachType(selectft_id);
        }

    }
    //EventBus.getDefault().postSticky(new MessageEvent(null));

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
    }

}
