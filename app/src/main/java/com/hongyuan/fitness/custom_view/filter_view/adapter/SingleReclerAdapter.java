package com.hongyuan.fitness.custom_view.filter_view.adapter;

import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.SkinConstants;

public abstract class SingleReclerAdapter<DATA> extends BaseQuickAdapter<DATA, BaseViewHolder> {

    private String skin;

    public SingleReclerAdapter(String skin){
        super(R.layout.item_filter_single_recyler);
        this.skin = skin;
    }

    @Override
    protected void convert(BaseViewHolder helper, DATA item) {
       helper.setText(R.id.content,provideText(item));
       if(isSelect(item)){
           helper.setTextColor(R.id.content,mContext.getResources().getColor(R.color.color_EF5B48));
       }else{
           if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin)){
               helper.setTextColor(R.id.content,mContext.getResources().getColor(R.color.color_FF333333));
           }else if(SkinConstants.SKIN_NAME.BLACK.equals(skin)){
               helper.setTextColor(R.id.content,mContext.getResources().getColor(R.color.color_FFFFFF));
           }

       }
       helper.addOnClickListener(R.id.content);
    }

    public abstract String provideText(DATA t);
    public abstract boolean isSelect(DATA t);

    /*
    * 更改skin
    * */
    public void changeSkin(String skin){
        if(!skin.equals(this.skin)){
            this.skin = skin;
            notifyDataSetChanged();
        }
    }

}
