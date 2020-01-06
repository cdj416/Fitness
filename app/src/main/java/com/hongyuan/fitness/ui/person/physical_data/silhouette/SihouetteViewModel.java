package com.hongyuan.fitness.ui.person.physical_data.silhouette;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivitySilhouetteBinding;
import com.hongyuan.fitness.ui.look_img.MyLookImgActivity;
import com.hongyuan.fitness.ui.look_img.MyLookImgDataBeans;

import java.util.ArrayList;
import java.util.List;

import static com.hongyuan.fitness.base.CustomActivity.SHOW_DATA;
import static com.hongyuan.fitness.base.CustomActivity.SHOW_EMPTY;

public class SihouetteViewModel extends CustomViewModel {

    private ActivitySilhouetteBinding binding;

    private SihouetteAdapter adapter;
    private List<SihouetteBeans.DataBean.ListBean> mList;
    //查看大图的数据集
    private List<MyLookImgDataBeans> parmList;

    public SihouetteViewModel(CustomActivity mActivity, ActivitySilhouetteBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new SihouetteAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRecycler));
        adapter.setOnItemClickListener((SihouetteAdapter.OnItemClickListener) (view, partPosition, childPosition) -> {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("imgList", (ArrayList<? extends Parcelable>) parmList);
            bundle.putInt("mPosition",getPosition(partPosition, childPosition));
            animStartActivity(MyLookImgActivity.class,view,bundle, R.anim.abc_fade_in, R.anim.abc_fade_out);
        });
    }

    @Override
    public void refreshData() {
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_BODY_IMG_LIST,Controller.TYPE_POST,getParams(), SihouetteBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof SihouetteBeans){
            List<SihouetteBeans.DataBean.ListBean> list = ((SihouetteBeans)data).getData().getList();
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
                mActivity.setPromtView(SHOW_DATA);
            }else{
                mActivity.setPromtView(SHOW_EMPTY);
            }

            setParmList();
        }
    }

    /*
    * 组装传递的数据
    * */
    private void setParmList(){
        int position = 0;

        if(parmList == null){
            parmList = new ArrayList<>();
        }
        parmList.clear();

        for(int i = 0 ; i < mList.size() ; i++){
            for(int j = 0 ; j < mList.get(i).getImgs().size() ; j++){
                parmList.add(new MyLookImgDataBeans(String.valueOf(position),
                        mList.get(i).getImgs().get(j).getAdd_date(),
                        mList.get(i).getImgs().get(j).getImg(),
                        mList.get(i).getImgs().get(j).getId()));
                position++;
            }
        }
    }

    /*
    * 获取显示下标
    * */
    private int getPosition(int partPosition,int childPosition){
        int mPosition = 0;

        for(int i = 0 ; i < parmList.size() ; i++){
            if(parmList.get(i).getImgId().equals(mList.get(partPosition).getImgs().get(childPosition).getId())){
                mPosition  = i;
            }
        }
        return mPosition;
    }
}
