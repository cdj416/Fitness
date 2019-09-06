package com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail.select_time;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class TimeBean extends BaseBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * now_time : 09:00:00
         * is_kong : 0
         */

        private String now_time;
        private int is_kong;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getNow_time() {
            return now_time;
        }

        public void setNow_time(String now_time) {
            this.now_time = now_time;
        }

        public int getIs_kong() {
            return is_kong;
        }

        public void setIs_kong(int is_kong) {
            this.is_kong = is_kong;
        }
    }
}
