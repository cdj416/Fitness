package com.hongyuan.fitness.ui.person.mine_message.child_message;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class MineChildMessageAdapter extends BaseQuickAdapter<MineChildMessageBeans.DataBean.ListBean, BaseViewHolder> {

    public MineChildMessageAdapter(){
        super(R.layout.item_child_messages);
    }
    @Override
    protected void convert(BaseViewHolder helper, MineChildMessageBeans.DataBean.ListBean item) {
        helper.setText(R.id.messages,item.getMsg_title()).setText(R.id.showTimeText,item.getMsg_date());

        if(item.getIs_read() != 1){
            helper.setVisible(R.id.readStatus,true);
        }else{
            helper.setVisible(R.id.readStatus,false);
        }
        helper.addOnClickListener(R.id.jumpBox);
    }
}
