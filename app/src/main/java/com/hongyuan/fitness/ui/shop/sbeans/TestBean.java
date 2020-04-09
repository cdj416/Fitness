package com.hongyuan.fitness.ui.shop.sbeans;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.ui.shop.sadapter.SCartAdapter;

public class TestBean implements MultiItemEntity {

    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    @Override
    public int getItemType() {
        return SCartAdapter.TYPE_TWO;
    }
}
