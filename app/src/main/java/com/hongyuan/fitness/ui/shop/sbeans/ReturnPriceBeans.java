package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

public class ReturnPriceBeans extends BaseBean {

    /**
     * data : {"max_refund":199}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * max_refund : 199
         */

        private double max_refund;

        public double getMax_refund() {
            return max_refund;
        }

        public void setMax_refund(double max_refund) {
            this.max_refund = max_refund;
        }
    }
}
