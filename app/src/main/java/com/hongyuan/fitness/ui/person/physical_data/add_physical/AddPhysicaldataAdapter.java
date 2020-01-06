package com.hongyuan.fitness.ui.person.physical_data.add_physical;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.TimeUtil;

public class AddPhysicaldataAdapter extends BaseQuickAdapter<PhysicaldataListBeans.DataBean.ListBean, BaseViewHolder> {

    public AddPhysicaldataAdapter(){
        super(R.layout.item_add_physicaldata);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhysicaldataListBeans.DataBean.ListBean item) {
        helper.setText(R.id.timeText, TimeUtil.formatDate(item.getAdd_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatYMD))
                .setText(R.id.numberText,item.getBody_value()+setDialogTitleOrUnit(item.getBody_code()));
    }


    /*
     * 获取弹框标题和单位
     * */
    private String setDialogTitleOrUnit(String bodyCode){
        String unitText = "";
        switch (bodyCode){
            case "weight":
                unitText = "kg";
                break;

            case "height":
                unitText = "cm";
                break;

            case "bmi":
                unitText = "";
                break;

            case "xw":
                unitText = "cm";
                break;

            case "yw":
                unitText = "cm";
                break;

            case "tw":
                unitText = "cm";
                break;

            case "dtw":
                unitText = "cm";
                break;

            case "xtw":
                unitText = "cm";
                break;

            case "sbw":
                unitText = "cm";
                break;

            case "bfp":
                unitText = "%";
                break;
        }

        return unitText;
    }


}
