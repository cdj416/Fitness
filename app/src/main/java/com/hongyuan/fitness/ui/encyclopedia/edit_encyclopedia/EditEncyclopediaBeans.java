package com.hongyuan.fitness.ui.encyclopedia.edit_encyclopedia;

import com.hongyuan.fitness.base.BaseBean;

public class EditEncyclopediaBeans extends BaseBean {

    /**
     * data : {"baike_id":"1"}
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
         * baike_id : 1
         */

        private String baike_id;

        public String getBaike_id() {
            return baike_id;
        }

        public void setBaike_id(String baike_id) {
            this.baike_id = baike_id;
        }
    }
}
