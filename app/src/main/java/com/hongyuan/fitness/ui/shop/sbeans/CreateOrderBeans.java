package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

public class CreateOrderBeans extends BaseBean {

    /**
     * data : {"o_ids":"19271;19272"}
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
         * o_ids : 19271;19272
         */

        private String o_ids;

        public String getO_ids() {
            return o_ids;
        }

        public void setO_ids(String o_ids) {
            this.o_ids = o_ids;
        }
    }
}
