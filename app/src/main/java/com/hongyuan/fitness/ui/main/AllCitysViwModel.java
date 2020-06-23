package com.hongyuan.fitness.ui.main;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.android.exoplayer2.metadata.id3.MlltFrame;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.index_list.PinnedHeaderDecoration;
import com.hongyuan.fitness.databinding.AcitivityAllCitysBinding;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.LocationBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllCitysViwModel extends CustomViewModel {

    private AcitivityAllCitysBinding binding;

    private AllCitysAdapter adapter;

    //热门城市数据
    private OpenCitysBeans.DataBean hotBean;
    //各个标题的下标位置
    private HashMap<String, Integer> letterIndexes = new HashMap<>();

    public AllCitysViwModel(CustomActivity mActivity, AcitivityAllCitysBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {


        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new AllCitysAdapter();
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRec));


        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            AllCitysBeans.DataBean.ListBean listBean = (AllCitysBeans.DataBean.ListBean)adapter.getData().get(position);

            if(LocationBean.getInstance().getCityName().equals(listBean.getRegion_name())){
                if(LocationBean.getInstance().isOpen()){
                    TokenSingleBean.getInstance().setRegion_name(listBean.getRegion_name());
                    TokenSingleBean.getInstance().setRegion_code(listBean.getRegion_code());
                    EventBus.getDefault().post(ConstantsCode.EB_HOME_LOCATION,"");
                    mActivity.finish();
                }else{
                    CustomDialog.showMessage(mActivity,"当前城市未开通！");
                }
            }else{
                TokenSingleBean.getInstance().setRegion_name(listBean.getRegion_name());
                TokenSingleBean.getInstance().setRegion_code(listBean.getRegion_code());
                EventBus.getDefault().post(ConstantsCode.EB_HOME_LOCATION,"");
                mActivity.finish();
            }
        });

       binding.mSideBarView.setOnTouchLetterChangeListener(letter -> {
           int pos = getLetterPosition(letter);

           if (pos != -1) {
               binding.mRec.scrollToPosition(pos);
               LinearLayoutManager mLayoutManager =
                       (LinearLayoutManager) binding.mRec.getLayoutManager();
               mLayoutManager.scrollToPositionWithOffset(pos, 0);
           }
       });
    }

    /**
     * 获取字母索引的位置
     *
     * @param letter
     * @return
     */
    public int getLetterPosition(String letter) {
        Integer integer = letterIndexes.get(letter);
        return integer == null ? -1 : integer;
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_OPEN_HOT_CITYS, Controller.TYPE_POST,getParams(), OpenCitysBeans.class,this);

    }

    //获取开通的所有城市列表
    private void getAllCity(){
        clearParams();
        Controller.myRequest(Constants.GET_OPEN_CITYS, Controller.TYPE_POST,getParams(), AllCitysBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {

        if(data instanceof OpenCitysBeans) {
            hotBean = ((OpenCitysBeans) data).getData();

            getAllCity();
        }

        if(data instanceof AllCitysBeans){
            mActivity.closeLoading();

            AllCitysBeans.DataBean dataBean = ((AllCitysBeans)data).getData();

            List<MultiItemEntity> mList = dataBean.getmList();

            //添加热门城市
            AllCitysBeans.DataBean.HeardBeans heardBeans = new AllCitysBeans.DataBean.HeardBeans();
            for(OpenCitysBeans.DataBean.ListBean listBean : hotBean.getList()){
                heardBeans.setTitle("热门城市");
                heardBeans.addSubItem(new AllCitysBeans.DataBean.ListBean(listBean.getRegion_code(),listBean.getRegion_name()));
            }
            mList.add(0,heardBeans);

            //添加定位城市
            AllCitysBeans.DataBean.HeardBeans dwBeans = new AllCitysBeans.DataBean.HeardBeans();
            dwBeans.setTitle("当前定位");
            dwBeans.addSubItem(new AllCitysBeans.DataBean.ListBean(LocationBean.getInstance().getCityCode(),LocationBean.getInstance().getCityName()));
            mList.add(0,dwBeans);

            adapter.setNewData(mList);
            //打开所有子项列表
            adapter.expandAll();

            //刷新右边显示栏
            binding.mSideBarView.setLetters(getTitles(mList));

            //去更新各个标题的位置
            setIndexes(mList);

        }
    }

    /*
    * 获取标题栏数据
    * */
    private List<String> getTitles(List<MultiItemEntity> mList){

        List<String> titles = new ArrayList<>();

        for(MultiItemEntity entity : mList){
            if(entity instanceof AllCitysBeans.DataBean.HeardBeans){
                AllCitysBeans.DataBean.HeardBeans heardBeans = (AllCitysBeans.DataBean.HeardBeans)entity;
                if("当前定位".equals(heardBeans.getTitle())){
                    titles.add("#");
                }else if("热门城市".equals(heardBeans.getTitle())){
                    titles.add("热");
                }else{
                    titles.add(heardBeans.getTitle());
                }
            }
        }

        return titles;
    }

    /*
    * 存储下标
    * */
    private void setIndexes(List<MultiItemEntity> mList){
        for(int i = 0 ; i < mList.size() ; i++){
            if(mList.get(i) instanceof AllCitysBeans.DataBean.HeardBeans){
                AllCitysBeans.DataBean.HeardBeans heardBeans = (AllCitysBeans.DataBean.HeardBeans)mList.get(i);
                if("当前定位".equals(heardBeans.getTitle())){
                    letterIndexes.put("#",i);
                }else if("热门城市".equals(heardBeans.getTitle())){
                    letterIndexes.put("热",i);
                }else{
                    letterIndexes.put(heardBeans.getTitle(),i);
                }
            }
        }
    }
}
