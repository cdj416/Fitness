package com.hongyuan.fitness.ui.about_class.check_payment_method;

import com.hongyuan.fitness.base.BaseBean;

public class OrderBean extends BaseBean {

    /**
     * data : {"o_id":"3"}
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
         * o_id : 3
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
