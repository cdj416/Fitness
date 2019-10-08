package com.hongyuan.fitness.ui.person.daily_punch;

import com.hongyuan.fitness.base.BaseBean;

public class DailyPunchCheckBean extends BaseBean {

    /**
     * data : {"item":{"is_qd":"1","lx_day":1,"point":10}}
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
         * item : {"is_qd":"1","lx_day":1,"point":10}
         */

        private ItemBean item;

        public ItemBean getItem() {
            return item;
        }

        public void setItem(ItemBean item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * is_qd : 1
             * lx_day : 1
             * point : 10
             */

            private String is_qd;
            private int lx_day;
            private int point;
            private int leiji_days;

            public int getLeiji_days() {
                return leiji_days;
            }

            public void setLeiji_days(int leiji_days) {
                this.leiji_days = leiji_days;
            }

            public String getIs_qd() {
                return is_qd;
            }

            public void setIs_qd(String is_qd) {
                this.is_qd = is_qd;
            }

            public int getLx_day() {
                return lx_day;
            }

            public void setLx_day(int lx_day) {
                this.lx_day = lx_day;
            }

            public int getPoint() {
                return point;
            }

            public void setPoint(int point) {
                this.point = point;
            }
        }
    }
}
