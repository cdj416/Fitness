package com.hongyuan.fitness.ui.main.main_home.recommend;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class HomeRightBeans extends BaseBean {

    /**
     * data : {"list":[{"my_card_id":1,"m_id":13,"os_id":"26","my_card_type":1,"open_time":1565193600,"my_card_time":1572851179,"last_time":1596807775,"my_card_days":0,"osl_id":0,"card_img":"","card_name":"","card_money":"0.00","day_money":"0.00","o_id":0,"card_id":0,"card_type_name":"门店卡","open_date":"2019-08-08 00:00:00","last_date":"2020-08-07 21:42:55","is_first":1,"my_card_date":"2019-11-04 15:06:19","os_names":"首玺健身金色水岸店"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * my_card_id : 1
             * m_id : 13
             * os_id : 26
             * my_card_type : 1
             * open_time : 1565193600
             * my_card_time : 1572851179
             * last_time : 1596807775
             * my_card_days : 0
             * osl_id : 0
             * card_img :
             * card_name :
             * card_money : 0.00
             * day_money : 0.00
             * o_id : 0
             * card_id : 0
             * card_type_name : 门店卡
             * open_date : 2019-08-08 00:00:00
             * last_date : 2020-08-07 21:42:55
             * is_first : 1
             * my_card_date : 2019-11-04 15:06:19
             * os_names : 首玺健身金色水岸店
             */

            private int my_card_id;
            private int m_id;
            private String os_id;
            private int my_card_type;
            private int open_time;
            private int my_card_time;
            private int last_time;
            private int my_card_days;
            private int osl_id;
            private String card_img;
            private String card_name;
            private String card_money;
            private String day_money;
            private int o_id;
            private int card_id;
            private String card_type_name;
            private String open_date;
            private String last_date;
            private int is_first;
            private String my_card_date;
            private String os_names;

            public int getMy_card_id() {
                return my_card_id;
            }

            public void setMy_card_id(int my_card_id) {
                this.my_card_id = my_card_id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getOs_id() {
                return os_id;
            }

            public void setOs_id(String os_id) {
                this.os_id = os_id;
            }

            public int getMy_card_type() {
                return my_card_type;
            }

            public void setMy_card_type(int my_card_type) {
                this.my_card_type = my_card_type;
            }

            public int getOpen_time() {
                return open_time;
            }

            public void setOpen_time(int open_time) {
                this.open_time = open_time;
            }

            public int getMy_card_time() {
                return my_card_time;
            }

            public void setMy_card_time(int my_card_time) {
                this.my_card_time = my_card_time;
            }

            public int getLast_time() {
                return last_time;
            }

            public void setLast_time(int last_time) {
                this.last_time = last_time;
            }

            public int getMy_card_days() {
                return my_card_days;
            }

            public void setMy_card_days(int my_card_days) {
                this.my_card_days = my_card_days;
            }

            public int getOsl_id() {
                return osl_id;
            }

            public void setOsl_id(int osl_id) {
                this.osl_id = osl_id;
            }

            public String getCard_img() {
                return card_img;
            }

            public void setCard_img(String card_img) {
                this.card_img = card_img;
            }

            public String getCard_name() {
                return card_name;
            }

            public void setCard_name(String card_name) {
                this.card_name = card_name;
            }

            public String getCard_money() {
                return card_money;
            }

            public void setCard_money(String card_money) {
                this.card_money = card_money;
            }

            public String getDay_money() {
                return day_money;
            }

            public void setDay_money(String day_money) {
                this.day_money = day_money;
            }

            public int getO_id() {
                return o_id;
            }

            public void setO_id(int o_id) {
                this.o_id = o_id;
            }

            public int getCard_id() {
                return card_id;
            }

            public void setCard_id(int card_id) {
                this.card_id = card_id;
            }

            public String getCard_type_name() {
                return card_type_name;
            }

            public void setCard_type_name(String card_type_name) {
                this.card_type_name = card_type_name;
            }

            public String getOpen_date() {
                return open_date;
            }

            public void setOpen_date(String open_date) {
                this.open_date = open_date;
            }

            public String getLast_date() {
                return last_date;
            }

            public void setLast_date(String last_date) {
                this.last_date = last_date;
            }

            public int getIs_first() {
                return is_first;
            }

            public void setIs_first(int is_first) {
                this.is_first = is_first;
            }

            public String getMy_card_date() {
                return my_card_date;
            }

            public void setMy_card_date(String my_card_date) {
                this.my_card_date = my_card_date;
            }

            public String getOs_names() {
                return os_names;
            }

            public void setOs_names(String os_names) {
                this.os_names = os_names;
            }
        }
    }
}
