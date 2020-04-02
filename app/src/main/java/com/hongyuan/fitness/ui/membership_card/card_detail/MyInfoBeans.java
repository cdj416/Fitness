package com.hongyuan.fitness.ui.membership_card.card_detail;

import com.hongyuan.fitness.base.BaseBean;

public class MyInfoBeans extends BaseBean {

    /**
     * data : {"m_id":3,"mi_birth":"1990-01-03","mi_sex":1,"mi_realname":"陈道明"}
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
         * m_id : 3
         * mi_birth : 1990-01-03
         * mi_sex : 1
         * mi_realname : 陈道明
         */

        private int m_id;
        private String mi_birth;
        private int mi_sex;
        private String mi_realname;

        public int getM_id() {
            return m_id;
        }

        public void setM_id(int m_id) {
            this.m_id = m_id;
        }

        public String getMi_birth() {
            return mi_birth;
        }

        public void setMi_birth(String mi_birth) {
            this.mi_birth = mi_birth;
        }

        public int getMi_sex() {
            return mi_sex;
        }

        public void setMi_sex(int mi_sex) {
            this.mi_sex = mi_sex;
        }

        public String getMi_realname() {
            return mi_realname;
        }

        public void setMi_realname(String mi_realname) {
            this.mi_realname = mi_realname;
        }
    }
}
