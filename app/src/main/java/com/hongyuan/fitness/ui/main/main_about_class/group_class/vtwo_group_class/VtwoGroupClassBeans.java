package com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class VtwoGroupClassBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"os_name":"湖州首玺健身爱山店","cs_id":3,"os_id":21,"cs_start_time":1565312400,"cs_end_time":1565316000,"cs_name":"跑步","add_time":1565091633,"si_name":"跑步机","cs_state":1,"cs_max_num":3,"cs_min_num":2,"cs_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190806/6a0959b37d4df288dc7d55a228c803a6486a208e_600x338.jpg","state_name":"报名中","to_state":"1","cs_sign_up_num":0,"add_date":"2019-08-06 19:40:33","cs_start_date":"2019-08-09 09:00:00","cs_end_date":"2019-08-09 10:00:00"},{"os_name":"首玺健身环城西路店","cs_id":2,"os_id":24,"cs_start_time":1565226000,"cs_end_time":1565229600,"cs_name":"游泳团课","add_time":1565091588,"si_name":"跑步机","cs_state":1,"cs_max_num":2,"cs_min_num":1,"cs_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190806/02f7911161a2917fb89cf19ddc5cecd253b46407_800x600.jpg","state_name":"紧张","to_state":"2","cs_sign_up_num":1,"add_date":"2019-08-06 19:39:48","cs_start_date":"2019-08-08 09:00:00","cs_end_date":"2019-08-08 10:00:00"},{"os_name":"首玺健身环城西路店","cs_id":1,"os_id":24,"cs_start_time":1565139600,"cs_end_time":1565143200,"cs_name":"瑜伽团课","add_time":1565091539,"si_name":"瑜伽","cs_state":1,"cs_max_num":23,"cs_min_num":12,"cs_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190806/bbaff5fd74bc854e44b1e9d6b5521ea0d75be360_648x454.jpg","state_name":"报名中","to_state":"1","cs_sign_up_num":1,"add_date":"2019-08-06 19:38:59","cs_start_date":"2019-08-07 09:00:00","cs_end_date":"2019-08-07 10:00:00"}]}
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
             * os_name : 湖州首玺健身爱山店
             * cs_id : 3
             * os_id : 21
             * cs_start_time : 1565312400
             * cs_end_time : 1565316000
             * cs_name : 跑步
             * add_time : 1565091633
             * si_name : 跑步机
             * cs_state : 1
             * cs_max_num : 3
             * cs_min_num : 2
             * cs_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190806/6a0959b37d4df288dc7d55a228c803a6486a208e_600x338.jpg
             * state_name : 报名中
             * to_state : 1
             * cs_sign_up_num : 0
             * add_date : 2019-08-06 19:40:33
             * cs_start_date : 2019-08-09 09:00:00
             * cs_end_date : 2019-08-09 10:00:00
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
            private long bm_time;

            public long getBm_time() {
                return bm_time;
            }

            public void setBm_time(long bm_time) {
                this.bm_time = bm_time;
            }

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
                //涉及到图片的地方都需要处理下，节省流量
                return cs_img+"?x-oss-process=image/resize,h_200,w_200";
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
