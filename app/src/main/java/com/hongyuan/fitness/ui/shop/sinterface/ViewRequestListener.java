package com.hongyuan.fitness.ui.shop.sinterface;

import android.view.View;

import com.hongyuan.fitness.ui.shop.sbeans.SorderDetailBeans;

public interface ViewRequestListener {

    void listener(View v, View allPrice, SorderDetailBeans.DataBean.BottomBean item);
}
