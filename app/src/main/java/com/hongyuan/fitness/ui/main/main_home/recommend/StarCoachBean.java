package com.hongyuan.fitness.ui.main.main_home.recommend;

import java.util.List;

public class StarCoachBean {

    /**
     * data : [{"coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190717/a455f72302f2f2cd76d7433131bff2ecc9640c6f_800x600.jpg","coach_nickname":"肖","mi_sex":0,"m_id":7,"coach_ft_ids":"7,6,4,3,2,1","distance_um":2535,"total_course":5,"cp_price":0.01,"ft_str":"减脂/增肌/塑形/POS/拉伸/康复"}]
     * status : {"succeed":"1"}
     */

    private StatusBean status;
    private List<DataBean> data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class StatusBean {
        /**
         * succeed : 1
         */

        private String succeed;

        public String getSucceed() {
            return succeed;
        }

        public void setSucceed(String succeed) {
            this.succeed = succeed;
        }
    }

    public static class DataBean {
        /**
         * coach_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190717/a455f72302f2f2cd76d7433131bff2ecc9640c6f_800x600.jpg
         * coach_nickname : 肖
         * mi_sex : 0
         * m_id : 7
         * coach_ft_ids : 7,6,4,3,2,1
         * distance_um : 2535
         * total_course : 5
         * cp_price : 0.01
         * ft_str : 减脂/增肌/塑形/POS/拉伸/康复
         */

        private String coach_head;
        private String coach_nickname;
        private int mi_sex;
        private int m_id;
        private String coach_ft_ids;
        private int distance_um;
        private int total_course;
        private double cp_price;
        private String ft_str;

        public String getCoach_head() {
            return coach_head;
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
    }
}
