package com.hongyuan.fitness.ui.main.main_home.recommend;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class BoutiqueGroupBean extends BaseBean {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"os_name":"首玺健身(旗舰店)","cs_id":3,"os_id":12,"cs_start_time":1561256952,"cs_end_time":1561264152,"cs_name":"夏天游泳比赛","add_time":1561029020,"si_name":"瑜伽","cs_state":1,"cs_max_num":50,"cs_min_num":10,"cs_img":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190620/23d5d75867773d1731e9042d3b2fbf961aa13aa5_800x600.jpg","state_name":"报名中","to_state":"1","cs_sign_up_num":1,"add_date":"2019-06-20 19:10:20","cs_start_date":"2019-06-23 10:29:12","cs_end_date":"2019-06-23 12:29:12"},{"os_name":"首玺健身(旗舰店)","cs_id":2,"os_id":12,"cs_start_time":1561737600,"cs_end_time":1564502400,"cs_name":"游泳全能","add_time":1560735105,"si_name":"瑜伽","cs_state":1,"cs_max_num":50,"cs_min_num":10,"cs_img":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190620/dbc5a57569f77c34ee896a8a8c618d65ecbd2520_648x454.jpg","state_name":"报名中","to_state":"1","cs_sign_up_num":2,"add_date":"2019-06-17 09:31:45","cs_start_date":"2019-06-29 00:00:00","cs_end_date":"2019-07-31 00:00:00"},{"os_name":"首玺健身(旗舰店)","cs_id":1,"os_id":12,"cs_start_time":1559264400,"cs_end_time":1559268000,"cs_name":"跑步机超课","add_time":1559198860,"si_name":"跑步机","cs_state":1,"cs_max_num":50,"cs_min_num":10,"cs_img":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190620/9cfc42dc3a89089458a5932eb2c0cb74a4ec5670_600x338.jpg","state_name":"报名中","to_state":"1","cs_sign_up_num":0,"add_date":"2019-05-30 14:47:40","cs_start_date":"2019-05-31 09:00:00","cs_end_date":"2019-05-31 10:00:00"}]}
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
             * os_name : 首玺健身(旗舰店)
             * cs_id : 3
             * os_id : 12
             * cs_start_time : 1561256952
             * cs_end_time : 1561264152
             * cs_name : 夏天游泳比赛
             * add_time : 1561029020
             * si_name : 瑜伽
             * cs_state : 1
             * cs_max_num : 50
             * cs_min_num : 10
             * cs_img : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190620/23d5d75867773d1731e9042d3b2fbf961aa13aa5_800x600.jpg
             * state_name : 报名中
             * to_state : 1
             * cs_sign_up_num : 1
             * add_date : 2019-06-20 19:10:20
             * cs_start_date : 2019-06-23 10:29:12
             * cs_end_date : 2019-06-23 12:29:12
             */

            private String os_name;
            private int cs_id;
            private int os_id;
            private int cs_start_time;
            private int cs_end_time;
            private String cs_name;
            private int add_time;
            private String si_name;
            private int cs_state;
            private int cs_max_num;
            private int cs_min_num;
            private String cs_img;
            private String state_name;
            private String to_state;
            private int cs_sign_up_num;
            private String add_date;
            private String cs_start_date;
            private String cs_end_date;

            public String getOs_name() {
                return os_name;
            }

            public void setOs_name(String os_name) {
                this.os_name = os_name;
            }

            public int getCs_id() {
                return cs_id;
            }

            public void setCs_id(int cs_id) {
                this.cs_id = cs_id;
            }

            public int getOs_id() {
                return os_id;
            }

            public void setOs_id(int os_id) {
                this.os_id = os_id;
            }

            public int getCs_start_time() {
                return cs_start_time;
            }

            public void setCs_start_time(int cs_start_time) {
                this.cs_start_time = cs_start_time;
            }

            public int getCs_end_time() {
                return cs_end_time;
            }

            public void setCs_end_time(int cs_end_time) {
                this.cs_end_time = cs_end_time;
            }

            public String getCs_name() {
                return cs_name;
            }

            public void setCs_name(String cs_name) {
                this.cs_name = cs_name;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public String getSi_name() {
                return si_name;
            }

            public void setSi_name(String si_name) {
                this.si_name = si_name;
            }

            public int getCs_state() {
                return cs_state;
            }

            public void setCs_state(int cs_state) {
                this.cs_state = cs_state;
            }

            public int getCs_max_num() {
                return cs_max_num;
            }

            public void setCs_max_num(int cs_max_num) {
                this.cs_max_num = cs_max_num;
            }

            public int getCs_min_num() {
                return cs_min_num;
            }

            public void setCs_min_num(int cs_min_num) {
                this.cs_min_num = cs_min_num;
            }

            public String getCs_img() {
                return cs_img;
            }

            public void setCs_img(String cs_img) {
                this.cs_img = cs_img;
            }

            public String getState_name() {
                return state_name;
            }

            public void setState_name(String state_name) {
                this.state_name = state_name;
            }

            public String getTo_state() {
                return to_state;
            }

            public void setTo_state(String to_state) {
                this.to_state = to_state;
            }

            public int getCs_sign_up_num() {
                return cs_sign_up_num;
            }

            public void setCs_sign_up_num(int cs_sign_up_num) {
                this.cs_sign_up_num = cs_sign_up_num;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public String getCs_start_date() {
                return cs_start_date;
            }

            public void setCs_start_date(String cs_start_date) {
                this.cs_start_date = cs_start_date;
            }

            public String getCs_end_date() {
                return cs_end_date;
            }

            public void setCs_end_date(String cs_end_date) {
                this.cs_end_date = cs_end_date;
            }
        }
    }
}
