package com.hongyuan.fitness.ui.about_class.privite_class.course_details;

import com.hongyuan.fitness.base.BaseBean;

public class PriceDataBeans extends BaseBean {

    /**
     * data : {"price":"600.00"}
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
         * price : 600.00
         */

        private String price;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
