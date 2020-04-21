package com.hongyuan.fitness.ui.main.main_person;

import com.hongyuan.fitness.base.BaseBean;

public class PersonBean extends BaseBean {


    /**
     * data : {"info":{"m_name":"Marlboro，","m_mobile":"18183185173","m_id":3,"mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190905/af424528021948b89ea4374138d0c73788327141_702x1207.jpg","mi_sex":1,"pid":41,"cid":4101,"mi_sign":"天下无妖","mi_birth":631296000,"mi_face":"","mi_point":164,"birth":"1990-01-03","area":"广西南宁市","card_count":2,"fans_num":0,"gz_num":0,"circle_num":0,"weight":"暂未数据","weight_date":"暂未数据","exercise_days":0,"calories":0}}
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
         * info : {"m_name":"Marlboro，","m_mobile":"18183185173","m_id":3,"mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190905/af424528021948b89ea4374138d0c73788327141_702x1207.jpg","mi_sex":1,"pid":41,"cid":4101,"mi_sign":"天下无妖","mi_birth":631296000,"mi_face":"","mi_point":164,"birth":"1990-01-03","area":"广西南宁市","card_count":2,"fans_num":0,"gz_num":0,"circle_num":0,"weight":"暂未数据","weight_date":"暂未数据","exercise_days":0,"calories":0}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * m_name : Marlboro，
             * m_mobile : 18183185173
             * m_id : 3
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190905/af424528021948b89ea4374138d0c73788327141_702x1207.jpg
             * mi_sex : 1
             * pid : 41
             * cid : 4101
             * mi_sign : 天下无妖
             * mi_birth : 631296000
             * mi_face :
             * mi_point : 164
             * birth : 1990-01-03
             * area : 广西南宁市
             * card_count : 2
             * fans_num : 0
             * gz_num : 0
             * circle_num : 0
             * weight : 暂未数据
             * weight_date : 暂未数据
             * exercise_days : 0
             * calories : 0
             */

            private String m_name;
            private String m_mobile;
            private int m_id;
            private String mi_head;
            private int mi_sex;
            private int pid;
            private int cid;
            private String mi_sign;
            private int mi_birth;
            private String mi_face;
            private int mi_point;
            private String birth;
            private String area;
            private int card_count;
            private int fans_num;
            private int gz_num;
            private int circle_num;
            private String weight;
            private String weight_date;
            private int exercise_days;
            private int calories;
            private String mi_realname;


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

            public int getM_id() {
                return m_id;
            }

            public String getMi_realname() {
                return mi_realname;
            }

            public void setMi_realname(String mi_realname) {
                this.mi_realname = mi_realname;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
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

            public int getMi_birth() {
                return mi_birth;
            }

            public void setMi_birth(int mi_birth) {
                this.mi_birth = mi_birth;
            }

            public String getMi_face() {
                return mi_face;
            }

            public void setMi_face(String mi_face) {
                this.mi_face = mi_face;
            }

            public int getMi_point() {
                return mi_point;
            }

            public void setMi_point(int mi_point) {
                this.mi_point = mi_point;
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

            public int getCard_count() {
                return card_count;
            }

            public void setCard_count(int card_count) {
                this.card_count = card_count;
            }

            public int getFans_num() {
                return fans_num;
            }

            public void setFans_num(int fans_num) {
                this.fans_num = fans_num;
            }

            public int getGz_num() {
                return gz_num;
            }

            public void setGz_num(int gz_num) {
                this.gz_num = gz_num;
            }

            public int getCircle_num() {
                return circle_num;
            }

            public void setCircle_num(int circle_num) {
                this.circle_num = circle_num;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getWeight_date() {
                return weight_date;
            }

            public void setWeight_date(String weight_date) {
                this.weight_date = weight_date;
            }

            public int getExercise_days() {
                return exercise_days;
            }

            public void setExercise_days(int exercise_days) {
                this.exercise_days = exercise_days;
            }

            public int getCalories() {
                return calories;
            }

            public void setCalories(int calories) {
                this.calories = calories;
            }
        }
    }
}
