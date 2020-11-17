package com.hongyuan.fitness.ui.person.person_message;

import com.hongyuan.fitness.base.BaseBean;

public class PersonMessageBeans extends BaseBean {


    /**
     * data : {"m_name":"m85173","m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190816/77d7582491d44365f762aabbe4cf2b2513d0a245_1356x987.png","mi_sex":1,"pid":35,"cid":3505,"mi_sign":"","mi_birth":1563782049,"gz_num":1,"fs_num":0,"birth":"2019-07-22","area":"浙江省湖州市"}
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
         * m_name : m85173
         * m_mobile : 18183185173
         * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190816/77d7582491d44365f762aabbe4cf2b2513d0a245_1356x987.png
         * mi_sex : 1
         * pid : 35
         * cid : 3505
         * mi_sign :
         * mi_birth : 1563782049
         * gz_num : 1
         * fs_num : 0
         * birth : 2019-07-22
         * area : 浙江省湖州市
         */

        private String m_name;
        private String m_mobile;
        private String mi_head;
        private int mi_sex;
        private int pid;
        private int cid;
        private String mi_sign;
        private long mi_birth;
        private int gz_num;
        private int fs_num;
        private String birth;
        private String area;
        private int role_id;
        private int m_id;

        public int getM_id() {
            return m_id;
        }

        public void setM_id(int m_id) {
            this.m_id = m_id;
        }

        public int getRole_id() {
            return role_id;
        }

        public void setRole_id(int role_id) {
            this.role_id = role_id;
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

        public String getMi_head() {
            return mi_head;
        }

        public void setMi_head(String mi_head) {
            this.mi_head = mi_head;
        }

        public int getMi_sex() {
            return mi_sex;
        }

        public void setMi_sex(int mi_sex) {
            this.mi_sex = mi_sex;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getMi_sign() {
            return mi_sign;
        }

        public void setMi_sign(String mi_sign) {
            this.mi_sign = mi_sign;
        }

        public long getMi_birth() {
            return mi_birth;
        }

        public void setMi_birth(long mi_birth) {
            this.mi_birth = mi_birth;
        }

        public int getGz_num() {
            return gz_num;
        }

        public void setGz_num(int gz_num) {
            this.gz_num = gz_num;
        }

        public int getFs_num() {
            return fs_num;
        }

        public void setFs_num(int fs_num) {
            this.fs_num = fs_num;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
    }
}
