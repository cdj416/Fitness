package com.hongyuan.fitness.ui.find.topic;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.SkinConstants;

public class SelectTopicLeftAdapter extends BaseQuickAdapter<SlectTopicLeftBeans.DataBean.ListBean, BaseViewHolder> {

    private String skin;

    public SelectTopicLeftAdapter(String skin){
        super(R.layout.item_selecttopic_left);
        this.skin = skin;
    }
    @Override
    protected void convert(BaseViewHolder helper, SlectTopicLeftBeans.DataBean.ListBean item) {
        if(item.isSelect()){
            helper.setText(R.id.leftTopicName,item.getCategory_name());

            if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin)){
                helper.setTextColor(R.id.leftTopicName,mContext.getResources().getColor(R.color.color_EF5B48))
                        .setBackgroundColor(R.id.leftTopicName,mContext.getResources().getColor(R.color.color_FFFFFF));
            }else if(SkinConstants.SKIN_NAME.BLACK.equals(skin)){
                helper.setTextColor(R.id.leftTopicName,mContext.getResources().getColor(R.color.color_EF5B48))
                        .setBackgroundColor(R.id.leftTopicName,mContext.getResources().getColor(R.color.theme_color1_black));
            }
        }else{
            helper.setText(R.id.leftTopicName,item.getCategory_name());

            if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin)){
                helper.setTextColor(R.id.leftTopicName,mContext.getResources().getColor(R.color.color_FF333333))
                        .setBackgroundColor(R.id.leftTopicName,mContext.getResources().getColor(R.color.color_F5F6FB));
            }else if(SkinConstants.SKIN_NAME.BLACK.equals(skin)){
                helper.setTextColor(R.id.leftTopicName,mContext.getResources().getColor(R.color.color_FFFFFF))
                        .setBackgroundColor(R.id.leftTopicName,mContext.getResources().getColor(R.color.theme_color9_black));
            }
        }
        helper.addOnClickListener(R.id.leftTopicName);
    }

}
