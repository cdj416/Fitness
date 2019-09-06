package com.hongyuan.fitness.ui.mall.mine.mine_order.mine_order_list;

public class TypeBeans {
    private String showText;
    private String typeId;
    private boolean select;

    public TypeBeans(String showText, String typeId, boolean select) {
        this.showText = showText;
        this.typeId = typeId;
        this.select = select;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
