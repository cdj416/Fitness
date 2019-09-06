package com.hongyuan.fitness.ui.encyclopedia.encyclopedia_detail;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.ViewChangeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class EncyclopediatDetailsCommentAdapter extends BaseQuickAdapter<EncyclopediaDetailCommentBean.DataBean.ListBean, BaseViewHolder> {

    public interface ReturnClick{
        void returnClick(int partentPosition, int position);
    }
    private ReturnClick returnClick;


    public EncyclopediatDetailsCommentAdapter(ReturnClick returnClick){
        super(R.layout.item_father_comment);
        this.returnClick = returnClick;
    }

    @Override
    protected void convert(final BaseViewHolder helper, EncyclopediaDetailCommentBean.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.fName,item.getM_name()).setText(R.id.commentContent,item.getBr_content())
        .setText(R.id.attention,String.valueOf(item.getPraise_num())).setText(R.id.timeAfter, TimeUtil.friendly_time(item.getAdd_date()));

        if(item.getIs_praise() == 0){
            ViewChangeUtil.changeLeftDrawable(mContext,helper.getView(R.id.attention),R.mipmap.like_huise_img);
        }else{
            ViewChangeUtil.changeLeftDrawable(mContext,helper.getView(R.id.attention),R.mipmap.like_chengse_img);
        }

        if(item.getReply_num() > 2){
            helper.getView(R.id.commentNum).setVisibility(View.VISIBLE);
            helper.setText(R.id.commentNum,"共"+item.getReply_num()+"条回复>>");
        }else{
            helper.getView(R.id.commentNum).setVisibility(View.GONE);
        }

        if(item.getList_s() == null || item.getList_s().size() <=0){
            helper.getView(R.id.childBox).setVisibility(View.GONE);
        }else{
            helper.getView(R.id.childBox).setVisibility(View.VISIBLE);
        }

        RecyclerView childRecycler = helper.getView(R.id.childRecycler);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(RecyclerView.VERTICAL);
        childRecycler.setLayoutManager(manager);
        EncyclopediaDetailsChildsCommentAdapter adapter = new EncyclopediaDetailsChildsCommentAdapter();
        childRecycler.setAdapter(adapter);

        adapter.setNewData(item.getList_s());

        helper.addOnClickListener(R.id.topBox).addOnClickListener(R.id.commentContent).addOnClickListener(R.id.commentNum)
        .addOnClickListener(R.id.attention);

        //子项点击事件返回
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(returnClick != null){
                    returnClick.returnClick(helper.getPosition(),position);
                }
            }
        });
    }

}
