package com.hongyuan.fitness.ui.person.my_coupon.newcoupon.cbeans;

public class CouponStatusBeans {
    private String status;
    private String statusName;
    private boolean select;

    public CouponStatusBeans(String status, String statusName, boolean select) {
        this.status = status;
        this.statusName = statusName;
        this.select = select;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
