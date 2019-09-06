package com.hongyuan.fitness.ui.store.punch;

import com.hongyuan.fitness.base.BaseBean;

public class PunchBeans  extends BaseBean {

    /**
     * data : {"is_in":"1","item":{"card_days":1,"cp_num":0,"cs_num":0,"in_os_days":2,"in_os_days_week":0,"in_num":1}}
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
         * is_in : 1
         * item : {"card_days":1,"cp_num":0,"cs_num":0,"in_os_days":2,"in_os_days_week":0,"in_num":1}
         */

        private String is_in;
        private ItemBean item;

        public String getIs_in() {
            return is_in;
        }

        public void setIs_in(String is_in) {
            this.is_in = is_in;
        }

        public ItemBean getItem() {
            return item;
        }

        public void setItem(ItemBean item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * card_days : 1
             * cp_num : 0
             * cs_num : 0
             * in_os_days : 2
             * in_os_days_week : 0
             * in_num : 1
             */

            private int card_days;
            private int cp_num;
            private int cs_num;
            private int in_os_days;
            private int in_os_days_week;
            private int in_num;

            public int getCard_days() {
                return card_days;
            }

            public void setCard_days(int card_days) {
                this.card_days = card_days;
            }

            public int getCp_num() {
                return cp_num;
            }

            public void setCp_num(int cp_num) {
                this.cp_num = cp_num;
            }

            public int getCs_num() {
                return cs_num;
            }

            public void setCs_num(int cs_num) {
                this.cs_num = cs_num;
            }

            public int getIn_os_days() {
                return in_os_days;
            }

            public void setIn_os_days(int in_os_days) {
                this.in_os_days = in_os_days;
            }

            public int getIn_os_days_week() {
                return in_os_days_week;
            }

            public void setIn_os_days_week(int in_os_days_week) {
                this.in_os_days_week = in_os_days_week;
            }

            public int getIn_num() {
                return in_num;
            }

            public void setIn_num(int in_num) {
                this.in_num = in_num;
            }
        }
    }
}
