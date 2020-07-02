package com.hongyuan.fitness.ui.main.main_about_class.group_class;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class MyGroupClassBean extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"ocs_id":2,"m_id":3,"os_id":12,"add_time":1563852761,"ocs_state":1,"cs_id":2,"xy_qd_time":0,"xy_qd_state":0,"xy_qt_time":0,"xy_qt_state":0,"os_name":"首玺健身(旗舰店)","cs_start_time":1565769600,"cs_end_time":1565773200,"cs_name":"瑜伽团课","si_name":"瑜伽","cs_state":1,"cs_max_num":40,"cs_min_num":23,"cs_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190723/4b41caee65c601d24059a81152563b909ceff164_800x600.jpg","state_name":"报名中","to_state":"1","add_date":"2019-07-23 11:32:41","cs_start_date":"2019-08-14 16:00:00","cs_end_date":"2019-08-14 17:00:00"},{"ocs_id":1,"m_id":3,"os_id":12,"add_time":1563851854,"ocs_state":1,"cs_id":1,"xy_qd_time":0,"xy_qd_state":0,"xy_qt_time":0,"xy_qt_state":0,"os_name":"首玺健身(旗舰店)","cs_start_time":1567044000,"cs_end_time":1567047600,"cs_name":"游泳团课","si_name":"跑步机","cs_state":1,"cs_max_num":30,"cs_min_num":20,"cs_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/192744336c51d78ec0e84c505707b37a00c55951_648x454.jpg","state_name":"报名中","to_state":"1","add_date":"2019-07-23 11:17:34","cs_start_date":"2019-08-29 10:00:00","cs_end_date":"2019-08-29 11:00:00"}]}
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
             * ocs_id : 2
             * m_id : 3
             * os_id : 12
             * add_time : 1563852761
             * ocs_state : 1
             * cs_id : 2
             * xy_qd_time : 0
             * xy_qd_state : 0
             * xy_qt_time : 0
             * xy_qt_state : 0
             * os_name : 首玺健身(旗舰店)
             * cs_start_time : 1565769600
             * cs_end_time : 1565773200
             * cs_name : 瑜伽团课
             * si_name : 瑜伽
             * cs_state : 1
             * cs_max_num : 40
             * cs_min_num : 23
             * cs_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190723/4b41caee65c601d24059a81152563b909ceff164_800x600.jpg
             * state_name : 报名中
             * to_state : 1
             * add_date : 2019-07-23 11:32:41
             * cs_start_date : 2019-08-14 16:00:00
             * cs_end_date : 2019-08-14 17:00:00
             */

            private int ocs_id;
            private int m_id;
            private int os_id;
            private int add_time;
            private int ocs_state;
            private int cs_id;
            private int xy_qd_time;
            private int xy_qd_state;
            private int xy_qt_time;
            private int xy_qt_state;
            private String os_name;
            private int cs_start_time;
            private int cs_end_time;
            private String cs_name;
            private String si_name;
            private int cs_state;
            private int cs_max_num;
            private int cs_min_num;
            private String cs_img;
            private String state_name;
            private String to_state;
            private String add_date;
            private String cs_start_date;
            private String cs_end_date;
            private int ocs_number;

            public int getOcs_number() {
                return ocs_number;
            }

            public void setOcs_number(int ocs_number) {
                this.ocs_number = ocs_number;
            }

            public int getOcs_id() {
                return ocs_id;
            }

            public void setOcs_id(int ocs_id) {
                this.ocs_id = ocs_id;
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

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getOcs_state() {
                return ocs_state;
            }

            public void setOcs_state(int ocs_state) {
                this.ocs_state = ocs_state;
            }

            public int getCs_id() {
                return cs_id;
            }

            public void setCs_id(int cs_id) {
                this.cs_id = cs_id;
            }

            public int getXy_qd_time() {
                return xy_qd_time;
            }

            public void setXy_qd_time(int xy_qd_time) {
                this.xy_qd_time = xy_qd_time;
            }

            public int getXy_qd_state() {
                return xy_qd_state;
            }

            public void setXy_qd_state(int xy_qd_state) {
                this.xy_qd_state = xy_qd_state;
            }

            public int getXy_qt_time() {
                return xy_qt_time;
            }

            public void setXy_qt_time(int xy_qt_time) {
                this.xy_qt_time = xy_qt_time;
            }

            public int getXy_qt_state() {
                return xy_qt_state;
            }

            public void setXy_qt_state(int xy_qt_state) {
                this.xy_qt_state = xy_qt_state;
            }

            public String getOs_name() {
                return os_name;
            }

            public void setOs_name(String os_name) {
                this.os_name = os_name;
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
