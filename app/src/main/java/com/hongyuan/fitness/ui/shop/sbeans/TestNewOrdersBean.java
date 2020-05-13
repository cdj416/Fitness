package com.hongyuan.fitness.ui.shop.sbeans;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class TestNewOrdersBean implements MultiItemEntity {

    //item类型
    private int fieldType;

    public TestNewOrdersBean(int fieldType) {
        //将传入的type赋值
        this.fieldType = fieldType;
    }

    @Override
    public int getItemType() {
        return fieldType;
    }
}
