package com.hongyuan.fitness.ui.about_class.privite_class.preservation_course;

import com.hongyuan.fitness.base.BaseBean;

public class ReservationSuccessBeans extends BaseBean {


    /**
     * data : {"cpa_id":{"jl_mobile":"18183185173","appoint_date":"2019-08-22 19:08","m_name":"嗯哼","m_mobile":"18183185173","cpa_id":"34","cp_name":"tpmpmpm"}}
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
         * cpa_id : {"jl_mobile":"18183185173","appoint_date":"2019-08-22 19:08","m_name":"嗯哼","m_mobile":"18183185173","cpa_id":"34","cp_name":"tpmpmpm"}
         */

        private CpaIdBean cpa_id;

        public CpaIdBean getCpa_id() {
            return cpa_id;
        }

        public void setCpa_id(CpaIdBean cpa_id) {
            this.cpa_id = cpa_id;
        }

        public static class CpaIdBean {
            /**
             * jl_mobile : 18183185173
             * appoint_date : 2019-08-22 19:08
             * m_name : 嗯哼
             * m_mobile : 18183185173
             * cpa_id : 34
             * cp_name : tpmpmpm
             */

            private String jl_mobile;
            private String appoint_date;
            private String m_name;
            private String m_mobile;
            private String cpa_id;
            private String cp_name;

            public String getJl_mobile() {
                return jl_mobile;
            }

            public void setJl_mobile(String jl_mobile) {
                this.jl_mobile = jl_mobile;
            }

            public String getAppoint_date() {
                return appoint_date;
            }

            public void setAppoint_date(String appoint_date) {
                this.appoint_date = appoint_date;
            }

            public String getM_name() {
                return m_name;
            }

            public void setM_name(String m_name) {
                this.m_name = m_name;
            }

            public String getM_mobile() {
                return m_mobile;
            }

            public void setM_mobile(String m_mobile) {
                this.m_mobile = m_mobile;
            }

            public String getCpa_id() {
                return cpa_id;
            }

            public void setCpa_id(String cpa_id) {
                this.cpa_id = cpa_id;
            }

            public String getCp_name() {
                return cp_name;
            }

            public void setCp_name(String cp_name) {
                this.cp_name = cp_name;
            }
        }
    }
}
