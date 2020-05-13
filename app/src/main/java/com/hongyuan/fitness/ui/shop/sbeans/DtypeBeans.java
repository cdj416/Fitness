package com.hongyuan.fitness.ui.shop.sbeans;

public class DtypeBeans {

    public DtypeBeans(String unit, String unit_cn) {
        this.unit = unit;
        this.unit_cn = unit_cn;
    }

    public String unit;
    public String unit_cn;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit_cn() {
        return unit_cn;
    }

    public void setUnit_cn(String unit_cn) {
        this.unit_cn = unit_cn;
    }
}
