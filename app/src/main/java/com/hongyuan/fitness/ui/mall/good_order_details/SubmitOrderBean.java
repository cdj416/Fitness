package com.hongyuan.fitness.ui.mall.good_order_details;

import com.hongyuan.fitness.base.BaseBean;

public class SubmitOrderBean extends BaseBean {

    /**
     * data : {"o_id":"61"}
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
         * o_id : 61
         */

        private String o_id;

        public String getO_id() {
            return o_id;
        }

        public void setO_id(String o_id) {
            this.o_id = o_id;
        }
    }
}
