package com.hongyuan.fitness.ui.person.fix;

import com.hongyuan.fitness.base.BaseBean;

import java.io.Serializable;

public class DanduOneCourseBeans extends BaseBean implements Serializable{

    /**
     * data : {"info":{"cpa_id":11,"m_id":3,"jl_mid":52,"cp_id":3,"os_id":21,"ocp_id":42,"num":1,"state":5,"add_time":1589509240,"start_time":"2020-05-15 10:30:00","end_time":"2020-05-15 11:30:00","xy_qd_time":0,"xy_qt_time":0,"jl_qd_time":0,"jl_qt_time":0,"xy_qd_state":0,"xy_qt_state":0,"jl_qd_state":0,"jl_qt_state":0,"teacher_m_id":0,"admin_id":0,"update_time":0,"bohui_note":"","cancel_note":"","cancel_reason_id":0,"is_evaluation":0,"cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190814/752a64aed66dc895d0ac2b9d148216cc85a86de6_975x2436.jpg","cp_name":"减脂","cp_price":"320.00","mi_realname":"周佳城","coach_nickname":"Jack","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190810/07556b1685e33b04fcd4ffd50cd1727c014512db_200x218.jpg","os_name":"湖州首玺健身爱山店","add_date":"2020-05-15 10:20:40"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * info : {"cpa_id":11,"m_id":3,"jl_mid":52,"cp_id":3,"os_id":21,"ocp_id":42,"num":1,"state":5,"add_time":1589509240,"start_time":"2020-05-15 10:30:00","end_time":"2020-05-15 11:30:00","xy_qd_time":0,"xy_qt_time":0,"jl_qd_time":0,"jl_qt_time":0,"xy_qd_state":0,"xy_qt_state":0,"jl_qd_state":0,"jl_qt_state":0,"teacher_m_id":0,"admin_id":0,"update_time":0,"bohui_note":"","cancel_note":"","cancel_reason_id":0,"is_evaluation":0,"cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190814/752a64aed66dc895d0ac2b9d148216cc85a86de6_975x2436.jpg","cp_name":"减脂","cp_price":"320.00","mi_realname":"周佳城","coach_nickname":"Jack","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190810/07556b1685e33b04fcd4ffd50cd1727c014512db_200x218.jpg","os_name":"湖州首玺健身爱山店","add_date":"2020-05-15 10:20:40"}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean implements Serializable {
            /**
             * cpa_id : 11
             * m_id : 3
             * jl_mid : 52
             * cp_id : 3
             * os_id : 21
             * ocp_id : 42
             * num : 1
             * state : 5
             * add_time : 1589509240
             * start_time : 2020-05-15 10:30:00
             * end_time : 2020-05-15 11:30:00
             * xy_qd_time : 0
             * xy_qt_time : 0
             * jl_qd_time : 0
             * jl_qt_time : 0
             * xy_qd_state : 0
             * xy_qt_state : 0
             * jl_qd_state : 0
             * jl_qt_state : 0
             * teacher_m_id : 0
             * admin_id : 0
             * update_time : 0
             * bohui_note :
             * cancel_note :
             * cancel_reason_id : 0
             * is_evaluation : 0
             * cp_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190814/752a64aed66dc895d0ac2b9d148216cc85a86de6_975x2436.jpg
             * cp_name : 减脂
             * cp_price : 320.00
             * mi_realname : 周佳城
             * coach_nickname : Jack
             * coach_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190810/07556b1685e33b04fcd4ffd50cd1727c014512db_200x218.jpg
             * os_name : 湖州首玺健身爱山店
             * add_date : 2020-05-15 10:20:40
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
            private int teacher_m_id;
            private int admin_id;
            private int update_time;
            private String bohui_note;
            private String cancel_note;
            private int cancel_reason_id;
            private int is_evaluation;
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

            public int getTeacher_m_id() {
                return teacher_m_id;
            }

            public void setTeacher_m_id(int teacher_m_id) {
                this.teacher_m_id = teacher_m_id;
            }

            public int getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(int admin_id) {
                this.admin_id = admin_id;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public String getBohui_note() {
                return bohui_note;
            }

            public void setBohui_note(String bohui_note) {
                this.bohui_note = bohui_note;
            }

            public String getCancel_note() {
                return cancel_note;
            }

            public void setCancel_note(String cancel_note) {
                this.cancel_note = cancel_note;
            }

            public int getCancel_reason_id() {
                return cancel_reason_id;
            }

            public void setCancel_reason_id(int cancel_reason_id) {
                this.cancel_reason_id = cancel_reason_id;
            }

            public int getIs_evaluation() {
                return is_evaluation;
            }

            public void setIs_evaluation(int is_evaluation) {
                this.is_evaluation = is_evaluation;
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
