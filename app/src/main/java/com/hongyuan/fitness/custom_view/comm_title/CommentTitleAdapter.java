package com.hongyuan.fitness.custom_view.comm_title;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.SkinConstants;

public class CommentTitleAdapter extends BaseQuickAdapter<CommentTitleBeans, BaseViewHolder> {

    String skin;
    public CommentTitleAdapter(String skin){
        super(R.layout.item_comment_top);
        this.skin = skin;
    }
    @Override
    protected void convert(BaseViewHolder helper, CommentTitleBeans item) {
        helper.setText(R.id.itemName,item.getName()).setText(R.id.itemNum,String.valueOf(item.getNum()));
        helper.addOnClickListener(R.id.selectBox);

        if(item.isSelect()){
            helper.setTextColor(R.id.itemName,0xFFFFFFFF)
                    .setTextColor(R.id.itemNum,0xFFFFFFFF)
                    .setBackgroundRes(R.id.selectBox,R.drawable.shape_radius16_ef5b48);
        }else{

            if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin)){
                helper.setTextColor(R.id.itemName,0xFF5F5F5F)
                        .setTextColor(R.id.itemNum,0xFFEF5B48)
                        .setBackgroundRes(R.id.selectBox,R.drawable.shape_radius16_ffffff_stroke_979797);
            }else if(SkinConstants.SKIN_NAME.BLACK.equals(skin)){
                helper.setTextColor(R.id.itemName,0xffffffff)
                        .setTextColor(R.id.itemNum,0xffffffff)
                        .setBackgroundRes(R.id.selectBox,R.drawable.shape_radius16_f1f1f8_black);
            }

        }
    }
}
