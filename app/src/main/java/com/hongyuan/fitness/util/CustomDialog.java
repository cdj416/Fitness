package com.hongyuan.fitness.util;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.AddFoodView;
import com.hongyuan.fitness.custom_view.CountdownView;
import com.hongyuan.fitness.custom_view.WheelView;
import com.hongyuan.fitness.ui.heat.ItemClikeBean;
import com.hongyuan.fitness.ui.heat.UpdataFoodView;
import com.hongyuan.fitness.ui.heat.add_food.AddFoodBean;
import com.hongyuan.fitness.ui.heat.heat_detail.HeatDetailBean;
import com.hongyuan.fitness.ui.main.ScanCardsListAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeRightBeans;
import com.hongyuan.fitness.ui.person.my_coupon.CouponAdapter;
import com.hongyuan.fitness.ui.person.my_coupon.CouponListBeans;
import com.hongyuan.fitness.ui.person.my_coupon.main_receive_coupon.DialogReceiveCouponAdapter;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class CustomDialog {
    /*
     * 点击事件的回调 接口
     * */
    public interface DialogClick{
        void dialogClick(View v);
    }

    /*
    * 用于修改的接口回调
    * */
    public interface DialogClickMessage{
        void dialogClick(View v,String message);
    }

    /*
    * 用于刷新列表数据
    * */
    public interface DialogClickList{
        void dialogClick(View v,int position, BaseQuickAdapter adapter);
    }

    /*
    * 用于返回选择的字符串
    * */
    public interface DialogClickGuWen{
        void dialogClick(String selectText);
    }


    /*
    * 黑框提示信息
    * */
    public static void showMessage(Context mContext,String message){
        final Dialog dialog = new Dialog(mContext, R.style.MessageTheme);
        View view = View.inflate(mContext, R.layout.dialog_show_message,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        HourMeterUtil hourMeterUtil = new HourMeterUtil();
        TextView mengCheng = view.findViewById(R.id.mengCheng);
        TextView messages = view.findViewById(R.id.messages);
        mengCheng.setText(message);
        messages.setText(message);
        hourMeterUtil.startCount();
        hourMeterUtil.setTimeCallBack(passedTime -> {
            if(passedTime >= 3){
                dialog.dismiss();
                hourMeterUtil.stopCount();
            }
        });
    }

    /*
     * 蒙层弹框点击关闭软键盘
     *
     * */
    public static void showMengCeng(Context mContext){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_mengceng,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        view.findViewById(R.id.mengCheng).setOnClickListener(v -> dialog.dismiss());
    }

    /*
    * 倒计时弹框
    * */
    public static void countdown(Context mContext, CountdownView.AnimationListenr animationListenr){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_countdown,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        CountdownView cView = view.findViewById(R.id.countDownView);
        cView.startAnimation(animationListenr,dialog);
    }

    /*
    * 1.普通对话弹框
    * 2.有两个取消和确定两个按钮，并按钮有回调。
    * */
    public static void promptDialog(Context mContext,String messagesText,String isOkText,String isCannelText,boolean outSide,DialogClick dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_have_yes_no_prompt,null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(outSide);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView messages = view.findViewById(R.id.messages);
        TextView isOk = view.findViewById(R.id.isOk);
        TextView isCannel = view.findViewById(R.id.isCannel);

        isOk.setText(isOkText);
        isCannel.setText(isCannelText);
        messages.setText(messagesText);

        isOk.setOnClickListener(v -> {
            if(dialogClick != null){
                dialogClick.dialogClick(v);
            }
            dialog.dismiss();
        });
        isCannel.setOnClickListener(v -> {
            if(dialogClick != null){
                dialogClick.dialogClick(v);
            }
            dialog.dismiss();
        });
    }

    /*
    * 添加餐里面的滚动标尺弹框
    * */
    public static void addFood(Context mContext, AddFoodBean.DataBean.ListBean data, AddFoodView.SelectData selectData){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_add_food,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        AddFoodView addFood = view.findViewById(R.id.addFood);
        addFood.setData(data,selectData,dialog);
    }

    /*
    * 修改加餐餐里面的滚动标尺弹框
    * */
    public static void updataFood(Context mContext, ItemClikeBean setData, HeatDetailBean.DataBean detailBean, UpdataFoodView.UpdataData selectData){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_updata_food,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        UpdataFoodView addFood = view.findViewById(R.id.upDataFood);
        addFood.setHeatData(setData,detailBean,selectData,dialog);
    }

    /*
    * 选择加餐类型弹框
    * */
    public static void selectAddFood(Context mContext,DialogClick dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_select_addfood_type,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView addBreakfast = view.findViewById(R.id.addBreakfast);
        TextView addLunch = view.findViewById(R.id.addLunch);
        TextView addDinner = view.findViewById(R.id.addDinner);
        TextView closeDialog = view.findViewById(R.id.closeDialog);

        addBreakfast.setOnClickListener(v -> {dialogClick.dialogClick(v);dialog.dismiss();});
        addLunch.setOnClickListener(v -> {dialogClick.dialogClick(v);dialog.dismiss();});
        addDinner.setOnClickListener(v -> {dialogClick.dialogClick(v);dialog.dismiss();});
        closeDialog.setOnClickListener(v -> dialog.dismiss());
    }

    /*
    * 选择定位地址弹框
    * */
    public static void selectLocation(Context mContext){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_select_location,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        view.findViewById(R.id.closeImg).setOnClickListener(v -> dialog.dismiss());
        view.findViewById(R.id.locationAddress).setOnClickListener(v -> dialog.dismiss());
        view.findViewById(R.id.nearbyAddress).setOnClickListener(v -> dialog.dismiss());
    }
    /*
    * 关注/取关
    * */
    public static void attentionMore(Context mContext,int type,DialogClick dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_atttention,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView attention = view.findViewById(R.id.attention);

        if(type == 0){
            ViewChangeUtil.changeTopDrawable(mContext,attention,R.mipmap.attention_img);
            attention.setText("关注ta");
        }else{
            ViewChangeUtil.changeTopDrawable(mContext,attention,R.mipmap.unsubscribe_img);
            attention.setText("取消关注ta");
        }
        view.findViewById(R.id.goDetails).setOnClickListener(v -> {
            dialogClick.dialogClick(v);
            dialog.dismiss();
        });
        attention.setOnClickListener(v -> {
            dialogClick.dialogClick(v);
            dialog.dismiss();
        });
        view.findViewById(R.id.report).setOnClickListener(v -> {
            dialogClick.dialogClick(v);
            dialog.dismiss();
        });
    }


    /*
    * 拨打电话弹框
    * */
    public static void callTel(Context mContext,String telNum ,DialogClick dialogClick ){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_call_tel,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView telContent= view.findViewById(R.id.telContent);
        telContent.setText(telNum);
        view.findViewById(R.id.cancel).setOnClickListener(v -> dialog.dismiss());
        view.findViewById(R.id.call).setOnClickListener(v -> {
            if(dialogClick != null){
                dialogClick.dialogClick(v);
            }
            dialog.dismiss();
        });
    }

    /*
    * 个人中心每日签到---成功弹框
    * */
    public static void dailyPunchSuccess(Context mContext,String point,String days){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_daily_punch_success,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView pointNum= view.findViewById(R.id.pointNum);
        TextView lxDays= view.findViewById(R.id.lxDays);
        pointNum.setText(point);
        lxDays.setText("连续打卡"+days+"天，再接再厉");
        view.findViewById(R.id.close).setOnClickListener(v -> dialog.dismiss());
    }

    /*
    * 个人中心待上课---私教课签到成功弹框
    * */
    public static void priviteCoursePunchSuccess(Context mContext,String date,String week){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_privitecourse_check_success,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView showDate = view.findViewById(R.id.showDate);
        TextView showWeek = view.findViewById(R.id.showWeek);
        showDate.setText(date);
        showWeek.setText(week);

        view.findViewById(R.id.close).setOnClickListener(v -> dialog.dismiss());
    }

    /*
    * 个人中心待上课---私教课签到成功弹框
    * */
    public static void groupCoursePunchSuccess(Context mContext,String date,String week){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_groupcourse_check_success,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView showDate = view.findViewById(R.id.showDate);
        TextView showWeek = view.findViewById(R.id.showWeek);
        showDate.setText(date);
        showWeek.setText(week);

        view.findViewById(R.id.close).setOnClickListener(v -> dialog.dismiss());
    }

    /*
    * 编辑资料---修改昵称
    * */
    public static void updateName(Context mContext,DialogClickMessage dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_update_user_name,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        EditText content = view.findViewById(R.id.content);

        view.findViewById(R.id.userNameSubmit).setOnClickListener(v -> {
            if(!BaseUtil.isValue(content.getText().toString())){
                showMessage(mContext,"请输入昵称！");
            }else{
                dialog.dismiss();
                dialogClick.dialogClick(v,content.getText().toString());
            }

        });
    }
    /*
    * 编辑资料---修改个性签名
    * */
    public static void updateSign(Context mContext,DialogClickMessage dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_update_user_sign,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        EditText content = view.findViewById(R.id.content);

        view.findViewById(R.id.userSignSubmit).setOnClickListener(v -> {
            if(!BaseUtil.isValue(content.getText().toString())){
                showMessage(mContext,"请输入个性签名！");
            }else{
                dialog.dismiss();
                dialogClick.dialogClick(v,content.getText().toString());
            }

        });
    }

    /*
    * 编辑资料---修改性别
    * */
    public static void updateSex(Context mContext,int sex,DialogClickMessage dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_person_update_sex,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView clickMan = view.findViewById(R.id.clickMan);
        TextView clickWoman = view.findViewById(R.id.clickWoman);


        if(sex == MAN_CLICK){
            click_num = MAN_CLICK;
            changeSex(mContext,clickMan,clickWoman,click_num);
        }else{
            click_num = WOMAN_CLICK;
            changeSex(mContext,clickMan,clickWoman,click_num);
        }

        clickMan.setOnClickListener(v -> {
            if(click_num != MAN_CLICK){
                click_num = MAN_CLICK;
                changeSex(mContext,clickMan,clickWoman,click_num);
            }
        });
        clickWoman.setOnClickListener(v -> {
            if(click_num != WOMAN_CLICK){
                click_num = WOMAN_CLICK;
                changeSex(mContext,clickMan,clickWoman,click_num);
            }
        });

        view.findViewById(R.id.userSexSubmit).setOnClickListener(v -> {
            if(sex != click_num){
                dialog.dismiss();
                dialogClick.dialogClick(v,String.valueOf(click_num));
            }else{
                showMessage(mContext,"请选择性别！");
            }

        });
    }

    /*
    * 优惠券领取
    * */
    public static void receiveCoupon(Context mContext, List<CouponListBeans.DataBean.ListBean> mList,DialogClickList dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_receive_coupon,null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        RecyclerView mRecycler = view.findViewById(R.id.mRecycler);
        Button receiveAll = view.findViewById(R.id.receiveAll);
        ImageView closeImg = view.findViewById(R.id.closeImg);

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        mRecycler.addItemDecoration(new DividerItemDecoration(
                mContext, DividerItemDecoration.HORIZONTAL_LIST,32,0x00000000));
        DialogReceiveCouponAdapter adapter = new DialogReceiveCouponAdapter();
        mRecycler.setAdapter(adapter);

        adapter.setNewData(mList);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                dialogClick.dialogClick(view,position,adapter);
            }
        });

        receiveAll.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                if(dialogClick != null){

                }
            }
        });

        closeImg.setOnClickListener(v -> dialog.dismiss());

    }


    //标识男
    public static final int MAN_CLICK = 1;
    //标识nv
    public static final int WOMAN_CLICK = 2;
    //标识当前点击值
    private static int click_num = MAN_CLICK;

    /*
     * 改变性别选择效果状态
     * */
    private static void changeSex(Context mActivity,TextView clickMan,TextView clickWoman,int click_num){

        Animation amplification = AnimationUtils.loadAnimation(mActivity,R.anim.scale_amplification);
        Animation narrow = AnimationUtils.loadAnimation(mActivity,R.anim.scale_narrow);
        if(click_num == MAN_CLICK){
            clickMan.startAnimation(amplification);
            clickWoman.startAnimation(narrow);
            clickMan.setTextColor(0xFFEF5B48);
            clickWoman.setTextColor(0xFF999999);
        }
        if(click_num == WOMAN_CLICK){
            clickMan.startAnimation(narrow);
            clickWoman.startAnimation(amplification);
            clickMan.setTextColor(0xFF999999);
            clickWoman.setTextColor(0xFFEF5B48);
        }
    }

    /*
    * 二维码展示弹框
    * */
    public static Dialog showQRScan(Context mContext,String qrImg,String titleTxt,String isIn){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        dialog.setCanceledOnTouchOutside(false);
        View view = View.inflate(mContext, R.layout.dialog_scan_qr,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView titleName = view.findViewById(R.id.titleName);
        TextView promptTxt = view.findViewById(R.id.promptTxt);
        ImageView qrCodeImg = view.findViewById(R.id.qrCodeImg);
        ImageView closeImg = view.findViewById(R.id.closeImg);

        titleName.setText(titleTxt);
        promptTxt.setText(isIn.equals("0") ? "进店出示二维码" : "离店出示二维码");
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mContext).load(qrImg).apply(options).into(qrCodeImg);

        closeImg.setOnClickListener(v -> {
            dialog.dismiss();
        });

        return dialog;

    }

    static String selectText = "";

    /*
    * 滚动选择器弹框
    * */
    public static Dialog showScollSelect(Context mContext,String title,List<String> mArrayList,DialogClickGuWen dialogClickGuWen){
        selectText = mArrayList.get(0);

        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_scoll_select_data,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView titleText = view.findViewById(R.id.titleText);
        ImageView closeImg = view.findViewById(R.id.closeImg);
        WheelView mWheelView = view.findViewById(R.id.mWheelView);
        Button submit = view.findViewById(R.id.submit);

        titleText.setText(title);
        // 在这里可以设置滚轮的偏移量
        //mWheelView.setOffset(2);
        //设置每一个Item中的数据 mArrayList中装着的是一堆String字符串
        mWheelView.setItems(mArrayList);
        mWheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                //selectedIndex当前高亮的位置
                //item当前高亮的位置的内容
                selectText = item;
            }
        });

        closeImg.setOnClickListener(v -> {
            dialog.dismiss();
        });

        submit.setOnClickListener(v -> {
            dialog.dismiss();
            dialogClickGuWen.dialogClick(selectText);
        });

        return dialog;

    }

    /*
     * 优惠券领取
     * */
    public static void showCards(Context mContext, List<HomeRightBeans.DataBean.ListBean> mList, DialogClickList dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_scan_cards_list,null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        RecyclerView mRecycler = view.findViewById(R.id.mRecycler);
        ImageView closeImg = view.findViewById(R.id.closeImg);

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        mRecycler.addItemDecoration(new DividerItemDecoration(
                mContext, DividerItemDecoration.HORIZONTAL_LIST,32,0x00000000));
        ScanCardsListAdapter adapter = new ScanCardsListAdapter();
        mRecycler.setAdapter(adapter);

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                dialog.dismiss();
                dialogClick.dialogClick(view,position,adapter);
            }
        });

        closeImg.setOnClickListener(v -> dialog.dismiss());

    }


}
