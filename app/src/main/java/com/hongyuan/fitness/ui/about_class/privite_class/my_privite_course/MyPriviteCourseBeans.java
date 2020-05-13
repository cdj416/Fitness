package com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class MyPriviteCourseBeans extends BaseBean {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"my_course_id":2,"m_id":3,"os_id":16,"jl_mid":3,"have_num":8,"cp_id":2,"cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/21e7d3e7fa354e8f2d76b59c865a476cf807b6f6_1242x699.jpg","cp_name":"啦啦","mi_realname":"陈道明","coach_nickname":"小明","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","os_name":"首玺健身（环城西路店）","bnum":4,"cnum":6}]}
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
             * my_course_id : 2
             * m_id : 3
             * os_id : 16
             * jl_mid : 3
             * have_num : 8
             * cp_id : 2
             * cp_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/21e7d3e7fa354e8f2d76b59c865a476cf807b6f6_1242x699.jpg
             * cp_name : 啦啦
             * mi_realname : 陈道明
             * coach_nickname : 小明
             * coach_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg
             * os_name : 首玺健身（环城西路店）
             * bnum : 4
             * cnum : 6
             */

            private int my_course_id;
            private int m_id;
            private int os_id;
            private int jl_mid;
            private int have_num;
            private int cp_id;
            private String cp_img;
            private String cp_name;
            private String mi_realname;
            private String coach_nickname;
            private String coach_head;
            private String os_name;
            private int bnum;
            private int cnum;
            private String last_kong_date;

            public String getLast_kong_date() {
                return last_kong_date;
            }

            public void setLast_kong_date(String last_kong_date) {
                this.last_kong_date = last_kong_date;
            }

            public int getMy_course_id() {
                return my_course_id;
            }

            public void setMy_course_id(int my_course_id) {
                this.my_course_id = my_course_id;
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

            public int getJl_mid() {
                return jl_mid;
            }

            public void setJl_mid(int jl_mid) {
                this.jl_mid = jl_mid;
            }

            public int getHave_num() {
                return have_num;
            }

            public void setHave_num(int have_num) {
                this.have_num = have_num;
            }

            public int getCp_id() {
                return cp_id;
            }

            public void setCp_id(int cp_id) {
                this.cp_id = cp_id;
            }

            public String getCp_img() {
                return cp_img;
            }

            public void setCp_img(String cp_img) {
                this.cp_img = cp_img;
            }

            public String getCp_name() {
                return cp_name;
            }

            public void setCp_name(String cp_name) {
                this.cp_name = cp_name;
            }

            public String getMi_realname() {
                return mi_realname;
            }

            public void setMi_realname(String mi_realname) {
                this.mi_realname = mi_realname;
            }

            public String getCoach_nickname() {
                return coach_nickname;
            }

            public void setCoach_nickname(String coach_nickname) {
                this.coach_nickname = coach_nickname;
            }

            public String getCoach_head() {
                return coach_head;
            }

            public void setCoach_head(String coach_head) {
                this.coach_head = coach_head;
            }

            public String getOs_name() {
                return os_name;
            }

            public void setOs_name(String os_name) {
                this.os_name = os_name;
            }

            public int getBnum() {
                return bnum;
            }

            public void setBnum(int bnum) {
                this.bnum = bnum;
            }

            public int getCnum() {
                return cnum;
            }

            public void setCnum(int cnum) {
                this.cnum = cnum;
            }
        }
    }
}
