package com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class;

import com.hongyuan.fitness.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class PriviteCourseCheckBeans extends BaseBean implements Serializable {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"cpa_id":6,"m_id":3,"jl_mid":3,"cp_id":2,"os_id":16,"ocp_id":2,"num":1,"state":1,"add_time":1565161927,"start_time":"1970-01-01 08:00:00","end_time":"1970-01-01 08:00:00","xy_qd_time":0,"xy_qt_time":0,"jl_qd_time":0,"jl_qt_time":0,"xy_qd_state":0,"xy_qt_state":0,"jl_qd_state":0,"jl_qt_state":0,"cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/21e7d3e7fa354e8f2d76b59c865a476cf807b6f6_1242x699.jpg","cp_name":"啦啦","cp_price":"0.01","mi_realname":"陈道明","coach_nickname":"小明","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","os_name":"首玺健身（环城西路店）","add_date":"2019-08-07 15:12:07"},{"cpa_id":4,"m_id":3,"jl_mid":3,"cp_id":2,"os_id":16,"ocp_id":2,"num":1,"state":1,"add_time":1565074517,"start_time":"1970-01-01 08:00:00","end_time":"1970-01-01 08:00:00","xy_qd_time":0,"xy_qt_time":0,"jl_qd_time":0,"jl_qt_time":0,"xy_qd_state":0,"xy_qt_state":0,"jl_qd_state":0,"jl_qt_state":0,"cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/21e7d3e7fa354e8f2d76b59c865a476cf807b6f6_1242x699.jpg","cp_name":"啦啦","cp_price":"0.01","mi_realname":"陈道明","coach_nickname":"小明","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","os_name":"首玺健身（环城西路店）","add_date":"2019-08-06 14:55:17"},{"cpa_id":3,"m_id":3,"jl_mid":3,"cp_id":2,"os_id":16,"ocp_id":2,"num":1,"state":1,"add_time":1565073545,"start_time":"1970-01-01 08:00:00","end_time":"1970-01-01 08:00:00","xy_qd_time":0,"xy_qt_time":0,"jl_qd_time":0,"jl_qt_time":0,"xy_qd_state":0,"xy_qt_state":0,"jl_qd_state":0,"jl_qt_state":0,"cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/21e7d3e7fa354e8f2d76b59c865a476cf807b6f6_1242x699.jpg","cp_name":"啦啦","cp_price":"0.01","mi_realname":"陈道明","coach_nickname":"小明","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","os_name":"首玺健身（环城西路店）","add_date":"2019-08-06 14:39:05"},{"cpa_id":2,"m_id":3,"jl_mid":3,"cp_id":2,"os_id":16,"ocp_id":2,"num":3,"state":1,"add_time":1565073224,"start_time":"1970-01-01 08:00:00","end_time":"1970-01-01 08:00:00","xy_qd_time":0,"xy_qt_time":0,"jl_qd_time":0,"jl_qt_time":0,"xy_qd_state":0,"xy_qt_state":0,"jl_qd_state":0,"jl_qt_state":0,"cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/21e7d3e7fa354e8f2d76b59c865a476cf807b6f6_1242x699.jpg","cp_name":"啦啦","cp_price":"0.01","mi_realname":"陈道明","coach_nickname":"小明","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","os_name":"首玺健身（环城西路店）","add_date":"2019-08-06 14:33:44"},{"cpa_id":1,"m_id":3,"jl_mid":3,"cp_id":1,"os_id":16,"ocp_id":1,"num":1,"state":1,"add_time":1565072062,"start_time":"2019-08-06 14:30:00","end_time":"2019-08-06 15:00:00","xy_qd_time":0,"xy_qt_time":0,"jl_qd_time":0,"jl_qt_time":0,"xy_qd_state":0,"xy_qt_state":0,"jl_qd_state":0,"jl_qt_state":0,"cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/5dfcb8afa85d19ffb9a0124d810cd574306ba534_1706x2208.jpg","cp_name":"嗯哼","cp_price":"0.01","mi_realname":"陈道明","coach_nickname":"小明","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","os_name":"首玺健身（环城西路店）","add_date":"2019-08-06 14:14:22"}]}
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

    public static class DataBean implements Serializable{
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * cpa_id : 6
             * m_id : 3
             * jl_mid : 3
             * cp_id : 2
             * os_id : 16
             * ocp_id : 2
             * num : 1
             * state : 1
             * add_time : 1565161927
             * start_time : 1970-01-01 08:00:00
             * end_time : 1970-01-01 08:00:00
             * xy_qd_time : 0
             * xy_qt_time : 0
             * jl_qd_time : 0
             * jl_qt_time : 0
             * xy_qd_state : 0
             * xy_qt_state : 0
             * jl_qd_state : 0
             * jl_qt_state : 0
             * cp_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/21e7d3e7fa354e8f2d76b59c865a476cf807b6f6_1242x699.jpg
             * cp_name : 啦啦
             * cp_price : 0.01
             * mi_realname : 陈道明
             * coach_nickname : 小明
             * coach_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg
             * os_name : 首玺健身（环城西路店）
             * add_date : 2019-08-07 15:12:07
             */

            private int cpa_id;
            private int m_id;
            private int jl_mid;
            private int cp_id;
            private int os_id;
            private int ocp_id;
            private int num;
            private int state;
            private int add_time;
            private String start_time;
            private String end_time;
            private int xy_qd_time;
            private int xy_qt_time;
            private int jl_qd_time;
            private int jl_qt_time;
            private int xy_qd_state;
            private int xy_qt_state;
            private int jl_qd_state;
            private int jl_qt_state;
            private String cp_img;
            private String cp_name;
            private String cp_price;
            private String mi_realname;
            private String coach_nickname;
            private String coach_head;
            private String os_name;
            private String add_date;

            public int getCpa_id() {
                return cpa_id;
            }

            public void setCpa_id(int cpa_id) {
                this.cpa_id = cpa_id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getJl_mid() {
                return jl_mid;
            }

            public void setJl_mid(int jl_mid) {
                this.jl_mid = jl_mid;
            }

            public int getCp_id() {
                return cp_id;
            }

            public void setCp_id(int cp_id) {
                this.cp_id = cp_id;
            }

            public int getOs_id() {
                return os_id;
            }

            public void setOs_id(int os_id) {
                this.os_id = os_id;
            }

            public int getOcp_id() {
                return ocp_id;
            }

            public void setOcp_id(int ocp_id) {
                this.ocp_id = ocp_id;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public int getXy_qd_time() {
                return xy_qd_time;
            }

            public void setXy_qd_time(int xy_qd_time) {
                this.xy_qd_time = xy_qd_time;
            }

            public int getXy_qt_time() {
                return xy_qt_time;
            }

            public void setXy_qt_time(int xy_qt_time) {
                this.xy_qt_time = xy_qt_time;
            }

            public int getJl_qd_time() {
                return jl_qd_time;
            }

            public void setJl_qd_time(int jl_qd_time) {
                this.jl_qd_time = jl_qd_time;
            }

            public int getJl_qt_time() {
                return jl_qt_time;
            }

            public void setJl_qt_time(int jl_qt_time) {
                this.jl_qt_time = jl_qt_time;
            }

            public int getXy_qd_state() {
                return xy_qd_state;
            }

            public void setXy_qd_state(int xy_qd_state) {
                this.xy_qd_state = xy_qd_state;
            }

            public int getXy_qt_state() {
                return xy_qt_state;
            }

            public void setXy_qt_state(int xy_qt_state) {
                this.xy_qt_state = xy_qt_state;
            }

            public int getJl_qd_state() {
                return jl_qd_state;
            }

            public void setJl_qd_state(int jl_qd_state) {
                this.jl_qd_state = jl_qd_state;
            }

            public int getJl_qt_state() {
                return jl_qt_state;
            }

            public void setJl_qt_state(int jl_qt_state) {
                this.jl_qt_state = jl_qt_state;
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

            public String getCp_price() {
                return cp_price;
            }

            public void setCp_price(String cp_price) {
                this.cp_price = cp_price;
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

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }
        }
    }
}
