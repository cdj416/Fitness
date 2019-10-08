package com.hongyuan.fitness.ui.encyclopedia.vthird_change;

import com.hongyuan.fitness.base.BaseBean;

public class V3BaiKeDetialCommentLikeBean extends BaseBean {

    /**
     * data : {"arp_id":"17"}
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
         * arp_id : 17
         */

        private String arp_id;

        public String getArp_id() {
            return arp_id;
        }

        public void setArp_id(String arp_id) {
            this.arp_id = arp_id;
        }
    }
}
