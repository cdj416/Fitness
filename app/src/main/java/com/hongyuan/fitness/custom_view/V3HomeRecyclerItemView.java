package com.hongyuan.fitness.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachHomePageActivity;
import com.hongyuan.fitness.ui.about_class.coach.coach_list.CoachListActivity;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.MissionDetailActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsActivity;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class.VtwoGroupClassBeans;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsBeans;
import com.hongyuan.fitness.ui.main.main_find.featured.FeatureBean;
import com.hongyuan.fitness.ui.main.main_find.featured.V2FindContentAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home.V3HomeFindContentAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home.V3HomeGoodsAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home.V3HomeStoreAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home.V3HomeBoutiqueGroupAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home.V3HomePriviteCourseAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home.V3HomeStarCoachAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home.VtwoStarCoachBean;
import com.hongyuan.fitness.ui.main.main_mall.MallBeans;
import com.hongyuan.fitness.ui.mall.good_details.GoodDetailsActivity;
import com.hongyuan.fitness.ui.store.StoreDetailActivity;
import com.hongyuan.fitness.ui.store.more_store.StoreBean;
import com.hongyuan.fitness.ui.store.store_page_list.StoreActivity;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.makeramen.roundedimageview.RoundedImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class V3HomeRecyclerItemView extends LinearLayout {
    private MainActivity mActivity;

    private RelativeLayout TypeOneBox;
    private ImageView topBgImg;
    private TextView titleText,des;
    private RecyclerView mRecycler;
    private LinearLayout emptyBox;
    private View topHeightView;

    private LinearLayout typeTwoBox;
    private TextView typeTwoTitle,typeTwoRight;
    private CustomRecyclerView mTRecycler;

    private int styleType;

    /*
    * 需要回调请求的接口
    * */
    public interface Commback{
        void commback(int position,String datas);
    }


    public V3HomeRecyclerItemView(Context context) {
        super(context);
        if(context instanceof MainActivity){
            mActivity = (MainActivity)context;
        }
        initLayoutView();
    }

    public V3HomeRecyclerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.homeItem);
        styleType = a.getInt(R.styleable.homeItem_vthird_type,0);

        if(context instanceof MainActivity){
            mActivity = (MainActivity)context;
        }
        initLayoutView();
    }

    public V3HomeRecyclerItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if(context instanceof MainActivity){
            mActivity = (MainActivity)context;
        }
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_vthird_home_page_item, this);
        TypeOneBox = view.findViewById(R.id.TypeOneBox);
        topBgImg = view.findViewById(R.id.topBgImg);
        titleText = view.findViewById(R.id.titleText);
        mRecycler = view.findViewById(R.id.mRecycler);
        emptyBox = view.findViewById(R.id.emptyBox);
        topHeightView = view.findViewById(R.id.topHeightView);
        des = view.findViewById(R.id.des);


        typeTwoBox = view.findViewById(R.id.typeTwoBox);
        typeTwoTitle = view.findViewById(R.id.typeTwoTitle);
        typeTwoRight = view.findViewById(R.id.typeTwoRight);
        mTRecycler = view.findViewById(R.id.mTRecycler);


        //初始化明星教练样式
        if(styleType == 0){
            typeTwoBox.setVisibility(GONE);
            TypeOneBox.setVisibility(VISIBLE);
            initStartCoach();
        }
        //初始化私教课样式
        if(styleType == 1){
            typeTwoBox.setVisibility(GONE);
            TypeOneBox.setVisibility(VISIBLE);
            initPriviteCourse();
        }
        //初始化团课样式
        if(styleType == 2){
            typeTwoBox.setVisibility(GONE);
            TypeOneBox.setVisibility(VISIBLE);
            initGourpCourse();
        }
        //初始化商品样式
        if(styleType == 3){
            TypeOneBox.setVisibility(GONE);
            typeTwoBox.setVisibility(VISIBLE);
            initGood();
        }
        //初始化发现样式
        if(styleType == 4){
            TypeOneBox.setVisibility(GONE);
            typeTwoBox.setVisibility(VISIBLE);
            initFind();
        }
        //初始化门店样式
        if(styleType == 5){
            TypeOneBox.setVisibility(GONE);
            typeTwoBox.setVisibility(VISIBLE);
            initStore();
        }

    }

    /*
    * 初始化明星教练样式
    * */
    @SuppressLint("WrongConstant")
    private void initStartCoach(){
        topHeightView.setVisibility(VISIBLE);
        topBgImg.setImageResource(R.mipmap.item_home_star_coach_bg);
        titleText.setText("明星教练");
        des.setText("11.12-11.19");

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,mActivity.getResources().getColor(R.color.color_EEEEEE)));
        mRecycler.setLayoutManager(manager);

    }

    /*
     * 设置明星教练数据
     * */
    public void setStartCoach(List<VtwoStarCoachBean.DataBean.ListBean> mList){
        if(mList == null || mList.size() <= 0){
            emptyBox.setVisibility(VISIBLE);
            mRecycler.setVisibility(GONE);
            return;
        }else{
            emptyBox.setVisibility(GONE);
            mRecycler.setVisibility(VISIBLE);
        }

        V3HomeStarCoachAdapter adapter = new V3HomeStarCoachAdapter();
        mRecycler.setAdapter(adapter);
        adapter.addFooterView(getFooterItem(mRecycler));

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("coach_mid",String.valueOf(mList.get(position).getM_id()));
                mActivity.startActivity(CoachHomePageActivity.class,bundle);
            }
        });
    }

    /*
    * 初始化私教课样式
    * */
    @SuppressLint("WrongConstant")
    private void initPriviteCourse(){
        topHeightView.setVisibility(GONE);
        topBgImg.setImageResource(R.mipmap.itm_home_privite_course_bg);
        titleText.setText("精品私教课");
        des.setText("专属1v1指导，更加快速达到运动目标");

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,mActivity.getResources().getColor(R.color.color_EEEEEE)));
        mRecycler.setLayoutManager(manager);

    }

    /*
     * 设置私教课数据
     * */
    public void setPriviteCourse(List<VtwoPrivateLessonsBeans.DataBean.ListBean> mList){
        if(mList == null || mList.size() <= 0){
            emptyBox.setVisibility(VISIBLE);
            mRecycler.setVisibility(GONE);
            return;
        }else{
            emptyBox.setVisibility(GONE);
            mRecycler.setVisibility(VISIBLE);
        }

        V3HomePriviteCourseAdapter adapter = new V3HomePriviteCourseAdapter();
        mRecycler.setAdapter(adapter);
        adapter.addFooterView(getFooterItem(mRecycler));

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("cp_id",String.valueOf(mList.get(position).getCp_id()));
                mActivity.startActivity(CourseDetailsActivity.class,bundle);
            }
        });
    }

    /*
    * 初始化团课样式
    * */
    @SuppressLint("WrongConstant")
    private void initGourpCourse(){
        topHeightView.setVisibility(GONE);
        topBgImg.setImageResource(R.mipmap.item_home_goup_course_bg);
        titleText.setText("精品团课");
        des.setText("根据不同需求人群开设的免费课程");

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,mActivity.getResources().getColor(R.color.color_EEEEEE)));
        mRecycler.setLayoutManager(manager);
    }

    /*
     * 设置私教课数据
     * */
    public void setGroupCourse(List<VtwoGroupClassBeans.DataBean.ListBean> mList){
        if(mList == null || mList.size() <= 0){
            emptyBox.setVisibility(VISIBLE);
            mRecycler.setVisibility(GONE);
            return;
        }else{
            emptyBox.setVisibility(GONE);
            mRecycler.setVisibility(VISIBLE);
        }

        V3HomeBoutiqueGroupAdapter adapter = new V3HomeBoutiqueGroupAdapter();
        mRecycler.setAdapter(adapter);
        adapter.addFooterView(getFooterItem(mRecycler));

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("cs_id",String.valueOf(mList.get(position).getCs_id()));
                mActivity.startActivity(MissionDetailActivity.class,bundle);
            }
        });
    }

    /*
     * 获取底部---查看更多视图
     * */
    private View getFooterItem(RecyclerView v){
        View convertView = LayoutInflater
                .from(mActivity)
                .inflate(R.layout.view_bottom_look_moretext, (ViewGroup) v.getParent(), false);
        TextView lookMore = convertView.findViewById(R.id.lookMore);
        lookMore.setOnClickListener(v1 -> {
            if(styleType == 2){
                //通过EventBus去通知MainActivity显示第三页
                EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"2");
                //需要显示团课
                EventBus.getDefault().post(ConstantsCode.EB_SHOW_PRIVITE,"1");
            }
            if(styleType == 1){
                //通过EventBus去通知MainActivity显示第三页
                EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"2");
                //需要显示私教课
                EventBus.getDefault().post(ConstantsCode.EB_SHOW_PRIVITE,"0");
            }
            if(styleType == 0){
                mActivity.startActivity(CoachListActivity.class,null);
            }
        });
        return convertView;
    }


    /****************************************第二种样式的使用******************************************/

    /*
    * 初始化门店样式
    * */
    private void initStore(){
        typeTwoTitle.setText("附近门店");
        typeTwoRight.setText("逛逛更多门店");

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mTRecycler.setLayoutManager(manager);

        typeTwoRight.setOnClickListener(v ->{
            mActivity.startActivity(StoreActivity.class,null);} );
    }

    /*
    * 设置门店数据
    * */
    public void setStore(List<StoreBean.DataBean.ListBean> mList){
        V3HomeStoreAdapter adapter = new V3HomeStoreAdapter();
        mTRecycler.setAdapter(adapter);

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("os_id",String.valueOf(mList.get(position).getOs_id()));
            mActivity.startActivity(StoreDetailActivity.class,bundle);
        });
    }

    /*
    * 初始化商品样式
    * */
    private void initGood(){
        typeTwoTitle.setText("随动商城");
        typeTwoRight.setText("更多");

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mTRecycler.setLayoutManager(manager);

        typeTwoRight.setOnClickListener(v -> {
            mActivity.binding.viewPager.setCurrentItem(3);
        });
    }

    /*
     * 设置门店数据
     * */
    public void setGood(List<MallBeans.DataBean.ListBean> mList){
        V3HomeGoodsAdapter adapter = new V3HomeGoodsAdapter();
        mTRecycler.setAdapter(adapter);

        adapter.setNewData(mList);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(mList.get(position).getG_id()));
            mActivity.startActivity(GoodDetailsActivity.class,bundle);
        });
    }

    /*
    * 初始化发现
    * */
    private void initFind(){
        typeTwoTitle.setText("随动社区");
        typeTwoRight.setText("更多");

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mTRecycler.setLayoutManager(layoutManager);

        typeTwoRight.setOnClickListener(v -> {
            mActivity.binding.viewPager.setCurrentItem(1);
        });
    }

    /*
    * 设置发现数据
    * */
    public void setFind(List<FeatureBean.DataBean.ListBean> mList,Commback commback){
        V3HomeFindContentAdapter adapter = new V3HomeFindContentAdapter();
        mTRecycler.setAdapter(adapter);

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.jumpDetails){
                    Bundle bundle = new Bundle();
                    bundle.putString("circle_id",String.valueOf(mList.get(position).getCircle_id()));
                    mActivity.startActivity(PostDetailsActivity.class,bundle);
                }
                if(view.getId() == R.id.attention){
                    //帖子点赞/取消点赞
                    commback.commback(position,String.valueOf(mList.get(position).getCircle_id()));
                }
            }
        });
    }
}
