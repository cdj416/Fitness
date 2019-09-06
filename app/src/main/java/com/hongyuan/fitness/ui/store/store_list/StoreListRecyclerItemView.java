package com.hongyuan.fitness.ui.store.store_list;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachHomePageActivity;
import com.hongyuan.fitness.ui.about_class.coach.coach_list.CoachListAdapter;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.MissionDetailActivity;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class.VtwoGroupClassAdapter;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class.VtwoGroupClassBeans;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.PrivateLessonsAdapter;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.PrivateLessonsBean;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsAdapter;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsBeans;
import com.hongyuan.fitness.ui.main.main_home.recommend.BoutiqueGroupBean;
import com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home.VtwoStarCoachBean;
import com.hongyuan.fitness.ui.membership_card.card_detail.CardDetailsActivity;
import com.hongyuan.fitness.ui.store.CardItemBean;
import com.hongyuan.fitness.ui.store.StoreMembershipCardAdapter;
import com.hongyuan.fitness.ui.store.StoreStarCoachAdapter;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;

import java.util.ArrayList;
import java.util.List;

public class StoreListRecyclerItemView extends LinearLayout {

    private TextView name,more;
    private RecyclerView mRecycler;
    private RelativeLayout titleBox;

    public StoreListRecyclerItemView(Context context) {
        super(context);
        initLayoutView();
    }

    public StoreListRecyclerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public StoreListRecyclerItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_store_recycleview_item, this);
        name = view.findViewById(R.id.name);
        more = view.findViewById(R.id.more);
        mRecycler = view.findViewById(R.id.mRecycler);
        titleBox = view.findViewById(R.id.titleBox);

        titleBox.setVisibility(GONE);

    }

    /*
    * 精品团课--门店使用
    * */
    public void setBoutiqueGroup(List<VtwoGroupClassBeans.DataBean.ListBean> mList){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);

        VtwoGroupClassAdapter adapter = new VtwoGroupClassAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("cs_id",String.valueOf(mList.get(position).getCs_id()));
            ((CustomActivity)getContext()).startActivity(MissionDetailActivity.class,bundle);
        });
    }
    /*
    * 明星教练---门店使用
    * */
    public void setStarCoach(List<VtwoStarCoachBean.DataBean.ListBean> mList){

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);

        CoachListAdapter adapter = new CoachListAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);

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
    * 明星教练---门店使用
    * */
    public void setPriviteLeass(List<VtwoPrivateLessonsBeans.DataBean.ListBean> mList){

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);

        VtwoPrivateLessonsAdapter adapter = new VtwoPrivateLessonsAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);

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
    public void setCardList(List<CardItemBean.DataBean.ListBean> mList){

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecycler.setLayoutManager(layoutManager);

        StoreMembershipCardAdapter adapter = new StoreMembershipCardAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("card_id",String.valueOf(mList.get(position).getCard_id()));
            ((CustomActivity)getContext()).startActivity(CardDetailsActivity.class,bundle);
        });

    }

    /*
    * 门店-图片
    * */
    public void setStoreImg(List<String> imgs){
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecycler.setLayoutManager(layoutManager);

        StoreListImageAdapter adapter = new StoreListImageAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(imgs);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            //点击查看大图功能
            GPreviewBuilder.from((CustomActivity)getContext())
                    .setData(getInfoList(imgs,view))
                    .setCurrentIndex(position)
                    .setType(GPreviewBuilder.IndicatorType.Dot)
                    .start();
        });
    }

    /*
     * 获取图片集和图片所处位置
     * */
    private List<UserViewInfo> getInfoList(List<String> list,View view){
        List<UserViewInfo> imgList = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            imgList.add(new UserViewInfo(list.get(i)));
            Rect bounds = new Rect();
            view.getGlobalVisibleRect(bounds);
            imgList.get(i).setBounds(bounds);
        }

        return imgList;
    }

}
