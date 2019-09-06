package com.hongyuan.fitness.ui.person.waiting_evaluation.editorial_evaluation;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class EvaluationBeans extends BaseBean {


    /**
     * data : {"cr_info":{"cr_id":8,"m_id":3,"cr_content":"铜模看看疾控中心","cr_time":1565184377,"cp_id":2,"coach_s":"5.00","cr_state":1,"coach_mid":3,"cpa_id":6,"m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/3b2bb712582f72eaeb9cf7e0fd7370ffad2e7b37_968x1425.jpg","cp_name":"啦啦","cri":[],"cr_date":"2019-08-07 21:26:17","is_review":1}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cr_info : {"cr_id":8,"m_id":3,"cr_content":"铜模看看疾控中心","cr_time":1565184377,"cp_id":2,"coach_s":"5.00","cr_state":1,"coach_mid":3,"cpa_id":6,"m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/3b2bb712582f72eaeb9cf7e0fd7370ffad2e7b37_968x1425.jpg","cp_name":"啦啦","cri":[],"cr_date":"2019-08-07 21:26:17","is_review":1}
         */

        private CrInfoBean cr_info;

        public CrInfoBean getCr_info() {
            return cr_info;
        }

        public void setCr_info(CrInfoBean cr_info) {
            this.cr_info = cr_info;
        }

        public static class CrInfoBean {
            /**
             * cr_id : 8
             * m_id : 3
             * cr_content : 铜模看看疾控中心
             * cr_time : 1565184377
             * cp_id : 2
             * coach_s : 5.00
             * cr_state : 1
             * coach_mid : 3
             * cpa_id : 6
             * m_mobile : 18183185173
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190807/3b2bb712582f72eaeb9cf7e0fd7370ffad2e7b37_968x1425.jpg
             * cp_name : 啦啦
             * cri : []
             * cr_date : 2019-08-07 21:26:17
             * is_review : 1
             */

            private int cr_id;
            private int m_id;
            private String cr_content;
            private int cr_time;
            private int cp_id;
            private String coach_s;
            private int cr_state;
            private int coach_mid;
            private int cpa_id;
            private String m_mobile;
            private String mi_head;
            private String cp_name;
            private String cr_date;
            private int is_review;
            private List<?> cri;

            public int getCr_id() {
                return cr_id;
            }

            public void setCr_id(int cr_id) {
                this.cr_id = cr_id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getCr_content() {
                return cr_content;
            }

            public void setCr_content(String cr_content) {
                this.cr_content = cr_content;
            }

            public int getCr_time() {
                return cr_time;
            }

            public void setCr_time(int cr_time) {
                this.cr_time = cr_time;
            }

            public int getCp_id() {
                return cp_id;
            }

            public void setCp_id(int cp_id) {
                this.cp_id = cp_id;
            }

            public String getCoach_s() {
                return coach_s;
            }

            public void setCoach_s(String coach_s) {
                this.coach_s = coach_s;
            }

            public int getCr_state() {
                return cr_state;
            }

            public void setCr_state(int cr_state) {
                this.cr_state = cr_state;
            }

            public int getCoach_mid() {
                return coach_mid;
            }

            public void setCoach_mid(int coach_mid) {
                this.coach_mid = coach_mid;
            }

            public int getCpa_id() {
                return cpa_id;
            }

            public void setCpa_id(int cpa_id) {
                this.cpa_id = cpa_id;
            }

            public String getM_mobile() {
                return m_mobile;
            }

            public void setM_mobile(String m_mobile) {
                this.m_mobile = m_mobile;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public String getCp_name() {
                return cp_name;
            }

            public void setCp_name(String cp_name) {
                this.cp_name = cp_name;
            }

            public String getCr_date() {
                return cr_date;
            }

            public void setCr_date(String cr_date) {
                this.cr_date = cr_date;
            }

            public int getIs_review() {
                return is_review;
            }

            public void setIs_review(int is_review) {
                this.is_review = is_review;
            }

            public List<?> getCri() {
                return cri;
            }

            public void setCri(List<?> cri) {
                this.cri = cri;
            }
        }
    }
}
