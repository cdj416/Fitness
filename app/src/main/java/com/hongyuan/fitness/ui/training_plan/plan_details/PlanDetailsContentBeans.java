package com.hongyuan.fitness.ui.training_plan.plan_details;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class PlanDetailsContentBeans extends BaseBean {

    /**
     * data : {"list":[{"content_id":17,"jl_id":13,"add_time":1576719703,"add_date":"2019-12-19 09:41:43","m_name":"小陈","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg","ft_str":"塑形/POS/康复","os_name":"首玺健身金色水岸店"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * content_id : 17
             * jl_id : 13
             * add_time : 1576719703
             * add_date : 2019-12-19 09:41:43
             * m_name : 小陈
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg
             * ft_str : 塑形/POS/康复
             * os_name : 首玺健身金色水岸店
             */

            private int content_id;
            private int jl_id;
            private int add_time;
            private String add_date;
            private String m_name;
            private String mi_head;
            private String ft_str;
            private String os_name;

            public int getContent_id() {
                return content_id;
            }

            public void setContent_id(int content_id) {
                this.content_id = content_id;
            }

            public int getJl_id() {
                return jl_id;
            }

            public void setJl_id(int jl_id) {
                this.jl_id = jl_id;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
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

            public String getFt_str() {
                return ft_str;
            }

            public void setFt_str(String ft_str) {
                this.ft_str = ft_str;
            }

            public String getOs_name() {
                return os_name;
            }

            public void setOs_name(String os_name) {
                this.os_name = os_name;
            }
        }
    }
}
