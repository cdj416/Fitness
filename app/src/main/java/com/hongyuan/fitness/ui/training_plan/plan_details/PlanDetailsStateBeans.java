package com.hongyuan.fitness.ui.training_plan.plan_details;

import com.hongyuan.fitness.base.BaseBean;

public class PlanDetailsStateBeans extends BaseBean {

    /**
     * data : {"state":8,"info":{"plan_id":27,"m_id":13,"add_time":1576719611,"plan_mb":1,"plan_weight":70,"plan_day":4,"plan_state":8,"mb_str":"减脂塑性","body_index":{"mbi_height":"185.00","mbi_weight":"65","mbi_sex":1,"mbi_birth":684428400,"age":21}}}
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
         * state : 8
         * info : {"plan_id":27,"m_id":13,"add_time":1576719611,"plan_mb":1,"plan_weight":70,"plan_day":4,"plan_state":8,"mb_str":"减脂塑性","body_index":{"mbi_height":"185.00","mbi_weight":"65","mbi_sex":1,"mbi_birth":684428400,"age":21}}
         */

        private int state;
        private InfoBean info;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * plan_id : 27
             * m_id : 13
             * add_time : 1576719611
             * plan_mb : 1
             * plan_weight : 70
             * plan_day : 4
             * plan_state : 8
             * mb_str : 减脂塑性
             * body_index : {"mbi_height":"185.00","mbi_weight":"65","mbi_sex":1,"mbi_birth":684428400,"age":21}
             */

            private int plan_id;
            private int m_id;
            private int add_time;
            private int plan_mb;
            private int plan_weight;
            private int plan_day;
            private int plan_state;
            private String mb_str;
            private BodyIndexBean body_index;

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

            public String getMb_str() {
                return mb_str;
            }

            public void setMb_str(String mb_str) {
                this.mb_str = mb_str;
            }

            public BodyIndexBean getBody_index() {
                return body_index;
            }

            public void setBody_index(BodyIndexBean body_index) {
                this.body_index = body_index;
            }

            public static class BodyIndexBean {
                /**
                 * mbi_height : 185.00
                 * mbi_weight : 65
                 * mbi_sex : 1
                 * mbi_birth : 684428400
                 * age : 21
                 */

                private String mbi_height;
                private String mbi_weight;
                private int mbi_sex;
                private int mbi_birth;
                private int age;

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

                public int getMbi_sex() {
                    return mbi_sex;
                }

                public void setMbi_sex(int mbi_sex) {
                    this.mbi_sex = mbi_sex;
                }

                public int getMbi_birth() {
                    return mbi_birth;
                }

                public void setMbi_birth(int mbi_birth) {
                    this.mbi_birth = mbi_birth;
                }

                public int getAge() {
                    return age;
                }

                public void setAge(int age) {
                    this.age = age;
                }
            }
        }
    }
}
