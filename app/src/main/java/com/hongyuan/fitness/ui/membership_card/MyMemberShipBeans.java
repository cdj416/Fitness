package com.hongyuan.fitness.ui.membership_card;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class MyMemberShipBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"my_card_id":1,"m_id":3,"os_id":24,"my_card_type":1,"open_time":0,"my_card_time":1565094254,"last_time":0,"my_card_days":365,"osl_id":0,"os_name":"首玺健身环城西路店","osl_name":null,"card_type_name":"门店卡","open_date":0,"last_date":0,"my_card_date":"2019-08-06 20:24:14"}]}
     */

    private boolean hasmore;
    private int curpage;
    private int page_total;
    private DataBean data;

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public int getCurpage() {
        return curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

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
             * m_id : 3
             * os_id : 24
             * my_card_type : 1
             * open_time : 0
             * my_card_time : 1565094254
             * last_time : 0
             * my_card_days : 365
             * osl_id : 0
             * os_name : 首玺健身环城西路店
             * osl_name : null
             * card_type_name : 门店卡
             * open_date : 0
             * last_date : 0
             * my_card_date : 2019-08-06 20:24:14
             */

            private int my_card_id;
            private int m_id;
            private int os_id;
            private int my_card_type;
            private int open_time;
            private int my_card_time;
            private int last_time;
            private int my_card_days;
            private int osl_id;
            private String os_name;
            private Object osl_name;
            private String card_type_name;
            private String open_date;
            private String last_date;
            private String my_card_date;

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

            public int getOs_id() {
                return os_id;
            }

            public void setOs_id(int os_id) {
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

            public String getOs_name() {
                return os_name;
            }

            public void setOs_name(String os_name) {
                this.os_name = os_name;
            }

            public Object getOsl_name() {
                return osl_name;
            }

            public void setOsl_name(Object osl_name) {
                this.osl_name = osl_name;
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

            public String getMy_card_date() {
                return my_card_date;
            }

            public void setMy_card_date(String my_card_date) {
                this.my_card_date = my_card_date;
            }
        }
    }
}
