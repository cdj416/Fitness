package com.hongyuan.fitness.ui.main.main_home.recommend;

import com.hongyuan.fitness.base.BaseBean;

public class CheckBobyIndexBean extends BaseBean {


    /**
     * data : {"info":{"mbi_id":92,"mbi_height":"180.00","mbi_weight":"70","m_id":3,"mbi_birth":649479023,"mbi_sex":1},"is_cs":1}
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
         * info : {"mbi_id":92,"mbi_height":"180.00","mbi_weight":"70","m_id":3,"mbi_birth":649479023,"mbi_sex":1}
         * is_cs : 1
         */

        private InfoBean info;
        private int is_cs;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public int getIs_cs() {
            return is_cs;
        }

        public void setIs_cs(int is_cs) {
            this.is_cs = is_cs;
        }

        public static class InfoBean {
            /**
             * mbi_id : 92
             * mbi_height : 180.00
             * mbi_weight : 70
             * m_id : 3
             * mbi_birth : 649479023
             * mbi_sex : 1
             */

            private int mbi_id;
            private String mbi_height;
            private String mbi_weight;
            private int m_id;
            private int mbi_birth;
            private int mbi_sex;

            public int getMbi_id() {
                return mbi_id;
            }

            public void setMbi_id(int mbi_id) {
                this.mbi_id = mbi_id;
            }

            public String getMbi_height() {
                return mbi_height;
            }

            public void setMbi_height(String mbi_height) {
                this.mbi_height = mbi_height;
            }

            public String getMbi_weight() {
                return mbi_weight;
            }

            public void setMbi_weight(String mbi_weight) {
                this.mbi_weight = mbi_weight;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getMbi_birth() {
                return mbi_birth;
            }

            public void setMbi_birth(int mbi_birth) {
                this.mbi_birth = mbi_birth;
            }

            public int getMbi_sex() {
                return mbi_sex;
            }

            public void setMbi_sex(int mbi_sex) {
                this.mbi_sex = mbi_sex;
            }
        }
    }
}
