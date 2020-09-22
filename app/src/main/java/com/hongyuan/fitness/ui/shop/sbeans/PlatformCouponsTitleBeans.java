package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class PlatformCouponsTitleBeans extends BaseBean implements Serializable{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * coupon_for : 0
         * coupon_for_name : 全部
         */

        private int coupon_for;
        private String coupon_for_name;

        public int getCoupon_for() {
            return coupon_for;
        }

        public void setCoupon_for(int coupon_for) {
            this.coupon_for = coupon_for;
        }

        public String getCoupon_for_name() {
            return coupon_for_name;
        }

        public void setCoupon_for_name(String coupon_for_name) {
            this.coupon_for_name = coupon_for_name;
        }
    }
}
