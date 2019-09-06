package com.hongyuan.fitness.ui.find.circle.get_nearby_location;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityGetNearbyLocationBinding;
import com.hongyuan.fitness.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import static me.goldze.mvvmhabit.utils.Utils.getContext;

public class GetNearLocationViwModel extends CustomViewModel {
    private ActivityGetNearbyLocationBinding binding;
    private GetNearyLocationAdapter adapter;

    public GetNearLocationViwModel(CustomActivity mActivity, ActivityGetNearbyLocationBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        //初始化标题栏
        mActivity.setTitle("定位");
        mActivity.setsetImmersive(0x55000000);
        mActivity.getMainTitle().setLeftImage(R.mipmap.close_heise_img);
        mActivity.getMainTitle().setRightText("完成");

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,0xFFEEEEEE));
        binding.mRecycler.setLayoutManager(manager);
        adapter = new GetNearyLocationAdapter();
        binding.mRecycler.setAdapter(adapter);

        adapter.setNewData(getmeunList());



    }

    @Override
    public void onSuccess(Object data) {

    }

    /*
     * 假数据(菜单)
     * */
    private List<BaseBean> getmeunList(){
        List<BaseBean> mList = new ArrayList<>();
        mList.add(new BaseBean());
        mList.add(new BaseBean());
        mList.add(new BaseBean());
        mList.add(new BaseBean());
        mList.add(new BaseBean());
        mList.add(new BaseBean());

        return mList;
    }
}
