package com.hongyuan.fitness.ui.person.my_coupon.newcoupon.cbeans;

public class CouponTypesBeans {
    private String couponType;
    private String couponTypeName;
    private boolean select;

    public CouponTypesBeans(String couponType, String couponTypeName, boolean select) {
        this.couponType = couponType;
        this.couponTypeName = couponTypeName;
        this.select = select;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getCouponTypeName() {
        return couponTypeName;
    }

    public void setCouponTypeName(String couponTypeName) {
        this.couponTypeName = couponTypeName;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
