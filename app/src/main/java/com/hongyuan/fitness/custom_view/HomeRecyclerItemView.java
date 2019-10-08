package com.hongyuan.fitness.custom_view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachHomePageActivity;
import com.hongyuan.fitness.ui.about_class.coach.coach_list.CoachListActivity;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.MissionDetailActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class.VtwoGroupClassBeans;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.PrivateLessonsBean;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsBeans;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeBoutiqueGroupAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.BoutiqueGroupBean;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomePriviteCourseAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeStarCoachAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home.V3HomeStoreAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home.VtwoStarCoachBean;
import com.hongyuan.fitness.ui.membership_card.renewal_card.RenewalCardActivity;
import com.hongyuan.fitness.ui.store.StoreBoutiqueGroupAdapter;
import com.hongyuan.fitness.ui.store.StoreDetailActivity;
import com.hongyuan.fitness.ui.store.StoreDetailBean;
import com.hongyuan.fitness.ui.store.StoreStarCoachAdapter;
import com.hongyuan.fitness.ui.store.more_store.StoreBean;
import com.hongyuan.fitness.ui.store.store_list.StoreListActivity;
import com.hongyuan.fitness.ui.store.store_page_list.StoreActivity;
import com.hongyuan.fitness.ui.store.vtwo_card.FixedCardBeans;
import com.hongyuan.fitness.ui.store.vtwo_card.VtwoStoreMembershipCardAdapter;
import com.hongyuan.fitness.util.DividerItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerItemView extends LinearLayout {

    private TextView name,more;
    private CustomRecyclerView mRecycler;
    private String os_id;

    public HomeRecyclerItemView(Context context) {
        super(context);
        initLayoutView();
    }

    public HomeRecyclerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public HomeRecyclerItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_home_recycleview_item, this);
        name = view.findViewById(R.id.name);
        more = view.findViewById(R.id.more);
        mRecycler = view.findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,24,0x00000000));
        mRecycler.setLayoutManager(manager);

    }

    /*
    * 附近门店--首页使用
    * */
    public void setHomeStore(List<StoreBean.DataBean.ListBean> mList){
        name.setText("附近门店");
        V3HomeStoreAdapter adapter = new V3HomeStoreAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);

        more.setOnClickListener(v ->{
            ((CustomActivity)getContext()).startActivity(StoreActivity.class,null);} );
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("os_id",String.valueOf(mList.get(position).getOs_id()));
            ((CustomActivity)getContext()).startActivity(StoreDetailActivity.class,bundle);
        });
    }
    /*
    * 精品团课--首页使用
    * */
    public void setHomeBoutiqueGroup(List<VtwoGroupClassBeans.DataBean.ListBean> mList){
        name.setText("精品团课");
        HomeBoutiqueGroupAdapter adapter = new HomeBoutiqueGroupAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);

        more.setOnClickListener(v ->{
            //通过EventBus去通知MainActivity显示第三页
            EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"2");
            //需要显示团课
            EventBus.getDefault().post(ConstantsCode.EB_SHOW_PRIVITE,"1");
        } );

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("cs_id",String.valueOf(mList.get(position).getCs_id()));
            ((CustomActivity)getContext()).startActivity(MissionDetailActivity.class,bundle);
        });

    }

    /*
    * 精品私教课--首页使用
    * */
    public void setPriviteCourse(List<VtwoPrivateLessonsBeans.DataBean.ListBean> mList){
        name.setText("精品私教课");
        HomePriviteCourseAdapter adapter = new HomePriviteCourseAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);


        more.setOnClickListener(v ->{
            //通过EventBus去通知MainActivity显示第三页
            EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"2");
            //需要显示私教课
            EventBus.getDefault().post(ConstantsCode.EB_SHOW_PRIVITE,"0");
        } );

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("cp_id",String.valueOf(mList.get(position).getCp_id()));
            ((CustomActivity)getContext()).startActivity(CourseDetailsActivity.class,bundle);
        });

    }

    /*
     * 明星教练--首页使用
     * */
    public void setHomeStarCoach(List<VtwoStarCoachBean.DataBean.ListBean> mList){
        name.setText("明星教练");

        HomeStarCoachAdapter adapter = new HomeStarCoachAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);

        more.setOnClickListener(v ->{
            Bundle bundle = new Bundle();
            ((CustomActivity)getContext()).startActivity(CoachListActivity.class,null);} );

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("coach_mid",String.valueOf(mList.get(position).getM_id()));
            ((CustomActivity)getContext()).startActivity(CoachHomePageActivity.class,bundle);
        });
    }

    /*
    * 精品团课--门店使用
    * */
    public void setBoutiqueGroup(List<BoutiqueGroupBean.DataBean.ListBean> mList,String os_id){
        this.os_id = os_id;
        name.setText("精品团课");
        StoreBoutiqueGroupAdapter adapter = new StoreBoutiqueGroupAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);

        more.setOnClickListener(v ->{
            Bundle bundle = new Bundle();
            bundle.putString("os_id",os_id);
            bundle.putInt("showPosition",3);
            ((CustomActivity)getContext()).startActivity(StoreListActivity.class,bundle);} );

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("cs_id",String.valueOf(mList.get(position).getCs_id()));
            ((CustomActivity)getContext()).startActivity(MissionDetailActivity.class,bundle);
        });
    }

    /*
     * 精品私教课--门店使用
     * */
    public void setStorePriviteCourse(List<VtwoPrivateLessonsBeans.DataBean.ListBean> mList){
        name.setText("精品私教课");
        HomePriviteCourseAdapter adapter = new HomePriviteCourseAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);


        more.setOnClickListener(v ->{
            Bundle bundle = new Bundle();
            bundle.putString("os_id",os_id);
            bundle.putInt("showPosition",2);
            ((CustomActivity)getContext()).startActivity(StoreListActivity.class,bundle);
        } );

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("cp_id",String.valueOf(mList.get(position).getCp_id()));
            ((CustomActivity)getContext()).startActivity(CourseDetailsActivity.class,bundle);
        });

    }

    /*
    * 明星教练---门店使用
    * */
    public void setStarCoach(List<PrivateLessonsBean.DataBean.ListBean> mList,String os_id){
        this.os_id = os_id;
        name.setText("明星教练");

        StoreStarCoachAdapter adapter = new StoreStarCoachAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);
        adapter.setOnItemChildClickListener(getItemChildClick());

        more.setOnClickListener(v ->{
            Bundle bundle = new Bundle();
            bundle.putString("os_id",os_id);
            bundle.putInt("showPosition",1);
            ((CustomActivity)getContext()).startActivity(StoreListActivity.class,bundle);} );

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("coach_mid",String.valueOf(mList.get(position).getM_id()));
                ((CustomActivity)getContext()).startActivity(CoachHomePageActivity.class,bundle);
            }
        });
    }

    /*
    * 会籍卡-门店使用
    * */
    public void setCardList(StoreDetailBean.DataBean beans, String os_id){
        this.os_id = os_id;
        name.setText("会籍卡");
        //组装卡类
        List<FixedCardBeans> mList = new ArrayList<>();
        mList.add(new FixedCardBeans(beans.getOsl_id(),beans.getOs_name(),1));
        mList.add(new FixedCardBeans(beans.getOsl_id(),beans.getOs_name(),2));

        VtwoStoreMembershipCardAdapter adapter = new VtwoStoreMembershipCardAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);

        more.setOnClickListener(v ->{
            Bundle bundle = new Bundle();
            bundle.putString("os_id",os_id);
            bundle.putInt("showPosition",0);
            ((CustomActivity)getContext()).startActivity(StoreListActivity.class,bundle);} );

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            if(position == 0){
                bundle.putString("my_card_type",String.valueOf(mList.get(position).getCardType()));
                bundle.putString("id",String.valueOf(beans.getOs_id()));
                bundle.putString("storeName",beans.getOs_name());
                ((CustomActivity)getContext()).startActivity(RenewalCardActivity.class,bundle);
            }else{
                bundle.putString("my_card_type",String.valueOf(mList.get(position).getCardType()));
                bundle.putString("id",String.valueOf(beans.getOsl_id()));
                bundle.putString("storeName",beans.getOs_name());
                ((CustomActivity)getContext()).startActivity(RenewalCardActivity.class,bundle);
            }

        });
    }

    /*
     * 子项点击事件
     * */
    private BaseQuickAdapter.OnItemChildClickListener getItemChildClick(){
        return (adapter, view, position) -> {

        };
    }

}
