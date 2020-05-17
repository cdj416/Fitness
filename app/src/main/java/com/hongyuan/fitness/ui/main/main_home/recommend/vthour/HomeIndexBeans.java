package com.hongyuan.fitness.ui.main.main_home.recommend.vthour;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class HomeIndexBeans extends BaseBean {

    /**
     * data : {"course_train":[{"os_name":"首玺健身环城西路店","ct_name":"测试课1","ct_id":1,"ct_price":"80.00","ct_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200513/4fa1413c1c1a6502a3417806d6da3692462cb385_640x428.jpg"},{"os_name":"首玺健身金色水岸店","ct_name":"测试课2","ct_id":2,"ct_price":"100.00","ct_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200513/b6f85774bec9ed04d3c96bee6938de6c9976220b_144x144.png"}],"sport":[{"gs_declareation":"免费免费","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200515/b50700cf80e7a5d543ea3cb98a29aadfb7f29709_1334x1334.jpg","mi_realname":"大王","gs_people_num":3,"fee_type":0,"sex":0,"gs_id":19,"s_time":1589778000,"e_time":1589779800,"count":0,"fee_type_name":"我请客","sex_name":"男女不限","state_name":"组队中","state":"1"},{"gs_declareation":"111","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200515/b50700cf80e7a5d543ea3cb98a29aadfb7f29709_1334x1334.jpg","mi_realname":"大王","gs_people_num":3,"fee_type":1,"sex":0,"gs_id":17,"s_time":1589623200,"e_time":1589628600,"count":0,"fee_type_name":"AA","sex_name":"男女不限","state_name":"组队中","state":"1"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CourseTrainBean> course_train;
        private List<SportBean> sport;

        public List<CourseTrainBean> getCourse_train() {
            return course_train;
        }

        public void setCourse_train(List<CourseTrainBean> course_train) {
            this.course_train = course_train;
        }

        public List<SportBean> getSport() {
            return sport;
        }

        public void setSport(List<SportBean> sport) {
            this.sport = sport;
        }

        public static class CourseTrainBean {
            /**
             * os_name : 首玺健身环城西路店
             * ct_name : 测试课1
             * ct_id : 1
             * ct_price : 80.00
             * ct_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200513/4fa1413c1c1a6502a3417806d6da3692462cb385_640x428.jpg
             */

            private String os_name;
            private String ct_name;
            private int ct_id;
            private String ct_price;
            private String ct_img;

            public String getOs_name() {
                return os_name;
            }

            public void setOs_name(String os_name) {
                this.os_name = os_name;
            }

            public String getCt_name() {
                return ct_name;
            }

            public void setCt_name(String ct_name) {
                this.ct_name = ct_name;
            }

            public int getCt_id() {
                return ct_id;
            }

            public void setCt_id(int ct_id) {
                this.ct_id = ct_id;
            }

            public String getCt_price() {
                return ct_price;
            }

            public void setCt_price(String ct_price) {
                this.ct_price = ct_price;
            }

            public String getCt_img() {
                return ct_img;
            }

            public void setCt_img(String ct_img) {
                this.ct_img = ct_img;
            }
        }

        public static class SportBean {
            /**
             * gs_declareation : 免费免费
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200515/b50700cf80e7a5d543ea3cb98a29aadfb7f29709_1334x1334.jpg
             * mi_realname : 大王
             * gs_people_num : 3
             * fee_type : 0
             * sex : 0
             * gs_id : 19
             * s_time : 1589778000
             * e_time : 1589779800
             * count : 0
             * fee_type_name : 我请客
             * sex_name : 男女不限
             * state_name : 组队中
             * state : 1
             */

            private String gs_declareation;
            private String mi_head;
            private String mi_realname;
            private int gs_people_num;
            private int fee_type;
            private int sex;
            private int gs_id;
            private int s_time;
            private int e_time;
            private int count;
            private String fee_type_name;
            private String sex_name;
            private String state_name;
            private String state;

            public String getGs_declareation() {
                return gs_declareation;
            }

            public void setGs_declareation(String gs_declareation) {
                this.gs_declareation = gs_declareation;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public String getMi_realname() {
                return mi_realname;
            }

            public void setMi_realname(String mi_realname) {
                this.mi_realname = mi_realname;
            }

            public int getGs_people_num() {
                return gs_people_num;
            }

            public void setGs_people_num(int gs_people_num) {
                this.gs_people_num = gs_people_num;
            }

            public int getFee_type() {
                return fee_type;
            }

            public void setFee_type(int fee_type) {
                this.fee_type = fee_type;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getGs_id() {
                return gs_id;
            }

            public void setGs_id(int gs_id) {
                this.gs_id = gs_id;
            }

            public int getS_time() {
                return s_time;
            }

            public void setS_time(int s_time) {
                this.s_time = s_time;
            }

            public int getE_time() {
                return e_time;
            }

            public void setE_time(int e_time) {
                this.e_time = e_time;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getFee_type_name() {
                return fee_type_name;
            }

            public void setFee_type_name(String fee_type_name) {
                this.fee_type_name = fee_type_name;
            }

            public String getSex_name() {
                return sex_name;
            }

            public void setSex_name(String sex_name) {
                this.sex_name = sex_name;
            }

            public String getState_name() {
                return state_name;
            }

            public void setState_name(String state_name) {
                this.state_name = state_name;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }
        }
    }
}
