package com.hongyuan.fitness.ui.training_plan;

import com.hongyuan.fitness.base.BaseBean;

public class PlanInfoBeans extends BaseBean {

    /**
     * data : {"info":{"plan_id":20,"m_id":13,"add_time":1575958408,"plan_mb":1,"plan_weight":70,"plan_day":4,"plan_state":2,"m_name":"小陈","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg","sex":1,"birth":21,"height":"185.00","weight":"65","mb":"减脂塑性"}}
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
         * info : {"plan_id":20,"m_id":13,"add_time":1575958408,"plan_mb":1,"plan_weight":70,"plan_day":4,"plan_state":2,"m_name":"小陈","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg","sex":1,"birth":21,"height":"185.00","weight":"65","mb":"减脂塑性"}
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
             * plan_id : 20
             * m_id : 13
             * add_time : 1575958408
             * plan_mb : 1
             * plan_weight : 70
             * plan_day : 4
             * plan_state : 2
             * m_name : 小陈
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg
             * sex : 1
             * birth : 21
             * height : 185.00
             * weight : 65
             * mb : 减脂塑性
             */

            private int plan_id;
            private int m_id;
            private int add_time;
            private int plan_mb;
            private int plan_weight;
            private int plan_day;
            private int plan_state;
            private String m_name;
            private String mi_head;
            private int sex;
            private int birth;
            private String height;
            private String weight;
            private String mb;

            public int getPlan_id() {
                return plan_id;
            }

            public void setPlan_id(int plan_id) {
                this.plan_id = plan_id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getPlan_mb() {
                return plan_mb;
            }

            public void setPlan_mb(int plan_mb) {
                this.plan_mb = plan_mb;
            }

            public int getPlan_weight() {
                return plan_weight;
            }

            public void setPlan_weight(int plan_weight) {
                this.plan_weight = plan_weight;
            }

            public int getPlan_day() {
                return plan_day;
            }

            public void setPlan_day(int plan_day) {
                this.plan_day = plan_day;
            }

            public int getPlan_state() {
                return plan_state;
            }

            public void setPlan_state(int plan_state) {
                this.plan_state = plan_state;
            }

            public String getM_name() {
                return m_name;
            }

            public void setM_name(String m_name) {
                this.m_name = m_name;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getBirth() {
                return birth;
            }

            public void setBirth(int birth) {
                this.birth = birth;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getMb() {
                return mb;
            }

            public void setMb(String mb) {
                this.mb = mb;
            }
        }
    }
}
