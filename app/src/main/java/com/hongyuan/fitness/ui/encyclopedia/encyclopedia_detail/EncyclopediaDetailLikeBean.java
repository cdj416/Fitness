package com.hongyuan.fitness.ui.encyclopedia.encyclopedia_detail;

import com.hongyuan.fitness.base.BaseBean;

public class EncyclopediaDetailLikeBean extends BaseBean {

    /**
     * data : {"baike_id":"53"}
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
         * baike_id : 53
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
