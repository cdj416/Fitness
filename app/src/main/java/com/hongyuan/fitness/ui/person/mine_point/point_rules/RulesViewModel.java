package com.hongyuan.fitness.ui.person.mine_point.point_rules;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityRulesBinding;

import java.util.ArrayList;
import java.util.List;

public class RulesViewModel extends CustomViewModel {

    private ActivityRulesBinding binding;
    private RulesAdapter adapter;

    public RulesViewModel(CustomActivity mActivity, ActivityRulesBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new RulesAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setNewData(getData());
    }

    @Override
    public void onSuccess(Object data) {

    }

    private List<RulesBean> getData(){
        List<RulesBean> mList = new ArrayList<>();
        mList.add(new RulesBean("微信登录/绑定","20","/"));
        mList.add(new RulesBean("首次登录/注册","30","/"));
        mList.add(new RulesBean("连续打卡7天","10","另+10"));
        mList.add(new RulesBean("连续打卡10天","10","另+15"));
        mList.add(new RulesBean("连续打卡15天","10","另+20"));
        mList.add(new RulesBean("连续打卡30天","10","另+50"));
        mList.add(new RulesBean("发圈子(图片/视频)","10","10"));
        mList.add(new RulesBean("点赞","2","10"));
        mList.add(new RulesBean("评论","2","10"));
        mList.add(new RulesBean("关注好友","2","10"));
        mList.add(new RulesBean("户外步行/跑步打卡","10","10"));
        mList.add(new RulesBean("分享至社交平台","10","10"));
        mList.add(new RulesBean("邀请好友","10","10"));
        return mList;
    }
}
