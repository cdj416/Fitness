package com.hongyuan.fitness.ui.membership_card.v4_mycard_list;

import com.hongyuan.fitness.base.BaseBean;

public class V4ScanSuccess extends BaseBean {

    /**
     * data : {"is_ok":0}
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
         * is_ok : 0
         */

        private int is_ok;

        public int getIs_ok() {
            return is_ok;
        }

        public void setIs_ok(int is_ok) {
            this.is_ok = is_ok;
        }
    }
}
