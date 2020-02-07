package com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class VtwoStarCoachBean extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","coach_nickname":"小明","mi_sex":1,"m_id":3,"coach_ft_ids":"7,6,4,3","os_name":"首玺健身（环城西路店）","os_id":16,"distance_um":1778,"total_course":6,"cp_price":0.01,"ft_str":"塑形/POS/拉伸/康复","last_kong_date":"2019-08-17 10:30"}]}
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
             * coach_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg
             * coach_nickname : 小明
             * mi_sex : 1
             * m_id : 3
             * coach_ft_ids : 7,6,4,3
             * os_name : 首玺健身（环城西路店）
             * os_id : 16
             * distance_um : 1778
             * total_course : 6
             * cp_price : 0.01
             * ft_str : 塑形/POS/拉伸/康复
             * last_kong_date : 2019-08-17 10:30
             */

            private String coach_head;
            private String coach_nickname;
            private int mi_sex;
            private int m_id;
            private String coach_ft_ids;
            private String os_name;
            private int os_id;
            private int distance_um;
            private int total_course;
            private double cp_price;
            private String ft_str;
            private String last_kong_date;

            public String getCoach_head() {
                //涉及到图片的地方都需要处理下，节省流量
                return coach_head+"?x-oss-process=image/resize,h_200,w_200";
            }

            public void setCoach_head(String coach_head) {
                this.coach_head = coach_head;
            }

            public String getCoach_nickname() {
                return coach_nickname;
            }

            public void setCoach_nickname(String coach_nickname) {
                this.coach_nickname = coach_nickname;
            }

            public int getMi_sex() {
                return mi_sex;
            }

            public void setMi_sex(int mi_sex) {
                this.mi_sex = mi_sex;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getCoach_ft_ids() {
                return coach_ft_ids;
            }

            public void setCoach_ft_ids(String coach_ft_ids) {
                this.coach_ft_ids = coach_ft_ids;
            }

            public String getOs_name() {
                return os_name;
            }

            public void setOs_name(String os_name) {
                this.os_name = os_name;
            }

            public int getOs_id() {
                return os_id;
            }

            public void setOs_id(int os_id) {
                this.os_id = os_id;
            }

            public int getDistance_um() {
                return distance_um;
            }

            public void setDistance_um(int distance_um) {
                this.distance_um = distance_um;
            }

            public int getTotal_course() {
                return total_course;
            }

            public void setTotal_course(int total_course) {
                this.total_course = total_course;
            }

            public double getCp_price() {
                return cp_price;
            }

            public void setCp_price(double cp_price) {
                this.cp_price = cp_price;
            }

            public String getFt_str() {
                return ft_str;
            }

            public void setFt_str(String ft_str) {
                this.ft_str = ft_str;
            }

            public String getLast_kong_date() {
                return last_kong_date;
            }

            public void setLast_kong_date(String last_kong_date) {
                this.last_kong_date = last_kong_date;
            }
        }
    }
}
