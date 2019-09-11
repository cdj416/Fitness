package com.hongyuan.fitness.ui.encyclopedia.select_encyclopedia_type;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.base.BaseDataSingle;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityEncyclopediaTypeBinding;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.MenuPrivateLessonsBean;
import com.hongyuan.fitness.util.DividerItemDecoration;

import static me.goldze.mvvmhabit.utils.Utils.getContext;

public class EncyclopediaTypeViewModel extends CustomViewModel {

    private ActivityEncyclopediaTypeBinding binding;
    private EncyclopediaTyepAdapter adapter;
    private MenuPrivateLessonsBean typeBean;

    public EncyclopediaTypeViewModel(CustomActivity mActivity, ActivityEncyclopediaTypeBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,0xFFEEEEEE));
        binding.mRecycler.setLayoutManager(manager);
        adapter = new EncyclopediaTyepAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            BaseDataSingle.getInstance().setBkType(typeBean.getData().get(position));
            mActivity.finish();
        });
    }

    @Override
    protected void lazyLoad() {
        //加载私教课类型
        Controller.myRequest(Constants.GET_FIT_TYPE_LIST,Controller.TYPE_POST,getParams(), MenuPrivateLessonsBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MenuPrivateLessonsBean && isSuccess(data)){
            typeBean = (MenuPrivateLessonsBean)data;
            adapter.setNewData(typeBean.getData());
        }
    }
}
