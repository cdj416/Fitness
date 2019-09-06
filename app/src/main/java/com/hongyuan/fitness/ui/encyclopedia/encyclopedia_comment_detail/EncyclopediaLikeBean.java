package com.hongyuan.fitness.ui.encyclopedia.encyclopedia_comment_detail;

import com.hongyuan.fitness.base.BaseBean;

public class EncyclopediaLikeBean extends BaseBean {

    /**
     * data : {"brp_id":"5"}
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
         * brp_id : 5
         */

        private String brp_id;

        public String getBrp_id() {
            return brp_id;
        }

        public void setBrp_id(String brp_id) {
            this.brp_id = brp_id;
        }
    }
}
