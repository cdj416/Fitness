package com.hongyuan.fitness.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.ReturnDetailsBeans;
import com.hongyuan.fitness.ui.shop.sinterface.MyClickListener;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.TimeUtil;

public class ReturnGoodsStaticView extends RelativeLayout implements View.OnClickListener {

    //按钮点击回调接口
    private MyClickListener clickListener;

    //退款状态 1已申请 2已取消 3已驳回 4重新申请 5商家同意退款 6买家发货退回 7卖家拒绝确认收货 8卖家确认收到货  9已完成

    private ImageView statusImg,firstImg,secondImg,thirdImg,thourthImg,fiveImg;
    private View firstLine,secondLine,thirdLine,thourthLine;
    private TextView statusDes,showDownTime,shutDownText,denialReason,time1,time2,time3,time4,time5,
            cancelApply4,cancelApply3,cancelApply2,cancelApply1,editApply3,editLogics,editApply1,
            returnUseName,returnUseTel,returnAddress,companyName,delverNum;
    private LinearLayout waiteDesBox,agreeReturnBox,refusalDesBox,shutDownBox,waitingDeliveryBox,completeDesBox,inBoxOne,inBoxTwo;

    private ItemCloumView returnPrice;

    //数据源
    private ReturnDetailsBeans.DataBean.InfoBean detailsBeans;

    public ReturnGoodsStaticView(Context context) {
        super(context);
        initLayoutView();
    }

    public ReturnGoodsStaticView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public ReturnGoodsStaticView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_return_goods_description, this);
        statusImg = view.findViewById(R.id.statusImg);
        statusDes = view.findViewById(R.id.statusDes);
        showDownTime = view.findViewById(R.id.showDownTime);

        shutDownText = view.findViewById(R.id.shutDownText);
        denialReason = view.findViewById(R.id.denialReason);

        firstImg = view.findViewById(R.id.firstImg);
        secondImg = view.findViewById(R.id.secondImg);
        thirdImg = view.findViewById(R.id.thirdImg);
        thourthImg = view.findViewById(R.id.thourthImg);
        fiveImg = view.findViewById(R.id.fiveImg);

        firstLine = view.findViewById(R.id.firstLine);
        secondLine = view.findViewById(R.id.secondLine);
        thirdLine = view.findViewById(R.id.thirdLine);
        thourthLine = view.findViewById(R.id.thourthLine);

        inBoxOne = view.findViewById(R.id.inBoxOne);
        inBoxTwo = view.findViewById(R.id.inBoxTwo);

        time1 = view.findViewById(R.id.time1);
        time2 = view.findViewById(R.id.time2);
        time3 = view.findViewById(R.id.time3);
        time4 = view.findViewById(R.id.time4);
        time5 = view.findViewById(R.id.time5);

        returnPrice = view.findViewById(R.id.returnPrice);

        cancelApply4 = view.findViewById(R.id.cancelApply4);
        cancelApply3 = view.findViewById(R.id.cancelApply3);
        cancelApply2 = view.findViewById(R.id.cancelApply2);
        cancelApply1 = view.findViewById(R.id.cancelApply1);
        editApply3 = view.findViewById(R.id.editApply3);
        editLogics = view.findViewById(R.id.editLogics);
        editApply1 = view.findViewById(R.id.editApply1);

        returnUseName = view.findViewById(R.id.returnUseName);
        returnUseTel = view.findViewById(R.id.returnUseTel);
        returnAddress = view.findViewById(R.id.returnAddress);

        companyName = view.findViewById(R.id.companyName);
        delverNum = view.findViewById(R.id.delverNum);

        waiteDesBox = view.findViewById(R.id.waiteDesBox);
        agreeReturnBox = view.findViewById(R.id.agreeReturnBox);
        refusalDesBox = view.findViewById(R.id.refusalDesBox);
        shutDownBox = view.findViewById(R.id.shutDownBox);
        waitingDeliveryBox = view.findViewById(R.id.waitingDeliveryBox);
        completeDesBox = view.findViewById(R.id.completeDesBox);

        cancelApply4.setOnClickListener(this);
        cancelApply3.setOnClickListener(this);
        cancelApply2.setOnClickListener(this);
        cancelApply1.setOnClickListener(this);
        editApply3.setOnClickListener(this);
        editLogics.setOnClickListener(this);
        editApply1.setOnClickListener(this);
    }

    public void setStatus(ReturnDetailsBeans.DataBean.InfoBean detailsBeans,MyClickListener clickListener){
        this.detailsBeans = detailsBeans;
        this.clickListener = clickListener;

        //处理显示时间
        if(detailsBeans.getRefund_state() == 1 || detailsBeans.getRefund_state() == 5 || detailsBeans.getRefund_state() == 6 || detailsBeans.getRefund_state() == 8){
            statusImg.setImageResource(R.mipmap.return_wait_img);

            if(detailsBeans.getRefund_state() == 1){
                showDownTime.setText("还剩"+getDays(detailsBeans.getApply_refund_time(),detailsBeans.getZd_agree_apply_day())+"天"+getHours(detailsBeans.getApply_refund_time())+"小时"+getMines(detailsBeans.getApply_refund_time())+"分钟");
            }
            if(detailsBeans.getRefund_state() == 5){
                showDownTime.setText("还剩"+getDays(detailsBeans.getAgree_time(),detailsBeans.getZd_deliver_day())+"天"+getHours(detailsBeans.getAgree_time())+"小时"+getMines(detailsBeans.getAgree_time())+"分钟");
            }
            if(detailsBeans.getRefund_state() == 6){
                showDownTime.setText("还剩"+getDays(detailsBeans.getDeliver_time(),detailsBeans.getZd_confirm_day())+"天"+getHours(detailsBeans.getDeliver_time())+"小时"+getMines(detailsBeans.getDeliver_time())+"分钟");
            }
            if(detailsBeans.getRefund_state() == 8){
                showDownTime.setText("还剩"+getDays(detailsBeans.getAgree_time(),detailsBeans.getZd_agree_day())+"天"+getHours(detailsBeans.getAgree_time())+"小时"+getMines(detailsBeans.getAgree_time())+"分钟");
            }
            showDownTime.setVisibility(View.VISIBLE);
        }else{
            showDownTime.setVisibility(View.GONE);
        }
        //处理提示文字
        statusDes.setText(getReturnStr2(detailsBeans.getRefund_state()));


        //处理状态描述
        if(detailsBeans.getRefund_state() == 1){
            waiteDesBox.setVisibility(VISIBLE);
            shutDownBox.setVisibility(GONE);
            refusalDesBox.setVisibility(GONE);
            agreeReturnBox.setVisibility(GONE);
            waitingDeliveryBox.setVisibility(GONE);
            completeDesBox.setVisibility(GONE);

        }
        if(detailsBeans.getRefund_state() == 2){
            statusImg.setImageResource(R.mipmap.return_close_img);
            if(Integer.parseInt(detailsBeans.getDeliver_date().substring(0,4)) >= 2020 && isLongTimeClose(detailsBeans.getNow_time(),detailsBeans.getDeliver_time(),detailsBeans.getZd_deliver_day())){
                shutDownText.setText("因您超时未处理，本次申请关闭");
            }else{
                shutDownText.setText("因您主动申请关闭，本次申请关闭");
            }
            waiteDesBox.setVisibility(GONE);
            shutDownBox.setVisibility(VISIBLE);
            refusalDesBox.setVisibility(GONE);
            agreeReturnBox.setVisibility(GONE);
            waitingDeliveryBox.setVisibility(GONE);
            completeDesBox.setVisibility(GONE);
        }
        if(detailsBeans.getRefund_state() == 3 || detailsBeans.getRefund_state() == 4 || detailsBeans.getRefund_state() == 7){
            statusImg.setImageResource(R.mipmap.return_close_img);

            if(detailsBeans.getRefund_state() == 3 || detailsBeans.getRefund_state() == 4 ){
                denialReason.setText("拒绝原因："+detailsBeans.getRefuse_reason());
            }else{
                denialReason.setText("拒绝原因："+detailsBeans.getRefuse_confirm_deliver_reason());
            }

            waiteDesBox.setVisibility(GONE);
            shutDownBox.setVisibility(GONE);
            refusalDesBox.setVisibility(VISIBLE);
            agreeReturnBox.setVisibility(GONE);
            waitingDeliveryBox.setVisibility(GONE);
            completeDesBox.setVisibility(GONE);

        }
        if(detailsBeans.getRefund_state() == 5){
            waiteDesBox.setVisibility(GONE);
            shutDownBox.setVisibility(GONE);
            refusalDesBox.setVisibility(GONE);
            agreeReturnBox.setVisibility(VISIBLE);
            waitingDeliveryBox.setVisibility(GONE);
            completeDesBox.setVisibility(GONE);
        }

        if(detailsBeans.getRefund_state() == 6 || detailsBeans.getRefund_state() == 8){
            waiteDesBox.setVisibility(GONE);
            shutDownBox.setVisibility(GONE);
            refusalDesBox.setVisibility(GONE);
            agreeReturnBox.setVisibility(GONE);
            waitingDeliveryBox.setVisibility(VISIBLE);
            completeDesBox.setVisibility(GONE);

            companyName.setText("物流公司："+detailsBeans.getDeliver_company());
            delverNum.setText("物流单号："+detailsBeans.getDeliver_num());
        }

        if(detailsBeans.getRefund_state() == 9){
            statusImg.setImageResource(R.mipmap.return_complete_img);

            waiteDesBox.setVisibility(GONE);
            shutDownBox.setVisibility(GONE);
            refusalDesBox.setVisibility(GONE);
            agreeReturnBox.setVisibility(GONE);
            waitingDeliveryBox.setVisibility(GONE);
            completeDesBox.setVisibility(VISIBLE);
        }

        //处理显示进度
        if(detailsBeans.getRefund_state() == 1 ){
            firstImg.setImageResource(R.mipmap.return_complete_mark);
            firstLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EF5B48));

            secondImg.setImageResource(R.mipmap.return_no_comeplete_mark);
            secondLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EEEEEE));

            thirdImg.setImageResource(R.mipmap.return_no_comeplete_mark);
            thirdLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EEEEEE));

            thourthImg.setImageResource(R.mipmap.return_no_comeplete_mark);
            thourthLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EEEEEE));

            fiveImg.setImageResource(R.mipmap.return_no_comeplete_mark);

            time1.setText(detailsBeans.getApply_refund_date().substring(2));
            time2.setVisibility(INVISIBLE);
            time3.setVisibility(INVISIBLE);
            time4.setVisibility(INVISIBLE);
            time5.setVisibility(INVISIBLE);
        }
        if(detailsBeans.getRefund_state() == 2 || detailsBeans.getRefund_state() == 3 || detailsBeans.getRefund_state() == 4){
            firstImg.setImageResource(R.mipmap.return_complete_mark);
            firstLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EEEEEE));

            secondImg.setImageResource(R.mipmap.return_no_comeplete_mark);
            secondLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EEEEEE));

            thirdImg.setImageResource(R.mipmap.return_no_comeplete_mark);
            thirdLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EEEEEE));

            thourthImg.setImageResource(R.mipmap.return_no_comeplete_mark);
            thourthLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EEEEEE));

            fiveImg.setImageResource(R.mipmap.return_no_comeplete_mark);

            time1.setText(detailsBeans.getApply_refund_date().substring(2));

            if(detailsBeans.getRefund_state() == 2 && Integer.parseInt(detailsBeans.getCancle_date().substring(0,4)) >= 2020){
                time2.setText(detailsBeans.getCancle_date().substring(2));
            }else if(detailsBeans.getRefund_state() == 3){
                time2.setText(detailsBeans.getRefuse_date().substring(2));
            }else{
                time2.setText(detailsBeans.getAgree_date().substring(2));
            }

            time3.setVisibility(INVISIBLE);
            time4.setVisibility(INVISIBLE);
            time5.setVisibility(INVISIBLE);
        }
        if(detailsBeans.getRefund_state() == 5){
            firstImg.setImageResource(R.mipmap.return_complete_mark);
            firstLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EF5B48));

            secondImg.setImageResource(R.mipmap.return_complete_mark);
            secondLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EEEEEE));

            thirdImg.setImageResource(R.mipmap.return_no_comeplete_mark);
            thirdLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EEEEEE));

            thourthImg.setImageResource(R.mipmap.return_no_comeplete_mark);
            thourthLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EEEEEE));

            fiveImg.setImageResource(R.mipmap.return_no_comeplete_mark);

            time1.setText(detailsBeans.getApply_refund_date().substring(2));
            time2.setText(detailsBeans.getAgree_date().substring(2));
            time3.setVisibility(INVISIBLE);
            time4.setVisibility(INVISIBLE);
            time5.setVisibility(INVISIBLE);

            returnUseName.setText("收货人："+detailsBeans.getContacts());
            returnUseTel.setText("联系方式："+detailsBeans.getTel());
            returnAddress.setText("收货地址："+detailsBeans.getProvince()+" "+detailsBeans.getCity()+" "+detailsBeans.getDistinct()+" "+detailsBeans.getAddress());
        }
        if(detailsBeans.getRefund_state() == 6 || detailsBeans.getRefund_state() == 7){
            firstImg.setImageResource(R.mipmap.return_complete_mark);
            firstLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EF5B48));

            secondImg.setImageResource(R.mipmap.return_complete_mark);
            secondLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EF5B48));

            thirdImg.setImageResource(R.mipmap.return_complete_mark);
            thirdLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EEEEEE));

            thourthImg.setImageResource(R.mipmap.return_no_comeplete_mark);
            thourthLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EEEEEE));

            fiveImg.setImageResource(R.mipmap.return_no_comeplete_mark);


            time1.setText(detailsBeans.getApply_refund_date().substring(2));
            time2.setText(detailsBeans.getAgree_date().substring(2));
            time3.setText(detailsBeans.getDeliver_date().substring(2));
            if(detailsBeans.getRefund_state() == 7){
                time4.setText(detailsBeans.getRefuse_confirm_deliver_date().substring(2));
            }else{
                time4.setVisibility(INVISIBLE);
            }

            time5.setVisibility(INVISIBLE);
        }
        if(detailsBeans.getRefund_state() == 8){
            firstImg.setImageResource(R.mipmap.return_complete_mark);
            firstLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EF5B48));

            secondImg.setImageResource(R.mipmap.return_complete_mark);
            secondLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EF5B48));

            thirdImg.setImageResource(R.mipmap.return_complete_mark);
            thirdLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EF5B48));

            thourthImg.setImageResource(R.mipmap.return_complete_mark);
            thourthLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EEEEEE));

            fiveImg.setImageResource(R.mipmap.return_no_comeplete_mark);

            time1.setText(detailsBeans.getApply_refund_date().substring(2));
            time2.setText(detailsBeans.getAgree_date().substring(2));
            time3.setText(detailsBeans.getDeliver_date().substring(2));
            time4.setText(detailsBeans.getConfirm_date().substring(2));
            time5.setVisibility(INVISIBLE);
        }
        if(detailsBeans.getRefund_state() == 9){
            firstImg.setImageResource(R.mipmap.return_complete_mark);
            firstLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EF5B48));

            secondImg.setImageResource(R.mipmap.return_complete_mark);
            secondLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EF5B48));

            thirdImg.setImageResource(R.mipmap.return_complete_mark);
            thirdLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EF5B48));

            thourthImg.setImageResource(R.mipmap.return_complete_mark);
            thourthLine.setBackgroundColor(getContext().getResources().getColor(R.color.color_EF5B48));

            fiveImg.setImageResource(R.mipmap.return_complete_mark);

            time1.setText(detailsBeans.getApply_refund_date().substring(2));
            time2.setText(detailsBeans.getAgree_date().substring(2));
            time3.setText(detailsBeans.getDeliver_date().substring(2));
            time4.setText(detailsBeans.getConfirm_date().substring(2));
            time5.setText(detailsBeans.getFinished_date().substring(2));

            returnPrice.setRightText("¥"+ BaseUtil.getNoZoon(detailsBeans.getRefund_money()));
        }

        //处理仅退款还是退货退款
        if(detailsBeans.getIs_return_goods() != 1){
            inBoxOne.setVisibility(GONE);
            inBoxTwo.setVisibility(GONE);
            thirdLine.setVisibility(GONE);
            thourthLine.setVisibility(GONE);
        }else{
            inBoxOne.setVisibility(VISIBLE);
            inBoxTwo.setVisibility(VISIBLE);
            thirdLine.setVisibility(VISIBLE);
            thourthLine.setVisibility(VISIBLE);
        }


        //按钮回调处理

    }

    /*
     * 退款状态二级描述
     * */
    private String getReturnStr2(int status){
        if(status == 1){
            return "等待商家处理";
        }
        if(status == 2){
            return "退款已关闭";
        }
        if(status == 3){
            return "申请被驳回";
        }
        if(status == 4){
            return "请重新申请退款";
        }
        if(status == 5){
            return "请寄回退货商品";
        }
        if(status == 6){
            return "等待商家收货";
        }
        if(status == 7){
            return "卖家已拒绝";
        }
        if(status == 8){
            return "等待商家处理退款";
        }
        if(status == 9){
            return "退款成功";
        }

        return "未知状态值";
    }

    /*
     * 两个时间相差多少天
     * */
    private int getDays(long useLongTime,long useDays){
        int days = TimeUtil.getOffectDay((useLongTime*1000+useDays*86400000),detailsBeans.getNow_time()*1000) -1;
        return days > 0 ? days : 0;
    }

    /*
     * 获取小时数
     * */
    private int getHours(long useLongTime){
        int useMine=TimeUtil.getOffectMinutes(detailsBeans.getNow_time()*1000,useLongTime*1000);
        return 23-useMine/60;
    }

    /*
     * 获取分钟数
     * */
    private int getMines(long useLongTime){
        int useMine= TimeUtil.getOffectMinutes(detailsBeans.getNow_time()*1000,useLongTime*1000);
        return 59-useMine%60;
    }

    /*
    * 是否超时关闭
    * */
    private boolean isLongTimeClose(long firstTime,long secondTime,int useDays){
        return TimeUtil.getOffectDay(firstTime*1000,secondTime*1000) > useDays;
    }

    /*
    * 按钮点击事件
    * */
    @Override
    public void onClick(View v) {
        if(clickListener != null){
            clickListener.onMyClick(v);
        }
    }
}
