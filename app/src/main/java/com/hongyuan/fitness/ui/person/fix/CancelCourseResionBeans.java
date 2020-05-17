package com.hongyuan.fitness.ui.person.fix;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class CancelCourseResionBeans extends BaseBean {

    /**
     * data : {"list":[{"reason_id":1,"name":"没有时间去上课"},{"reason_id":2,"name":"教练没有时间"},{"reason_id":3,"name":"其它"}]}
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
             * reason_id : 1
             * name : 没有时间去上课
             */

            private int reason_id;
            private String name;

            public int getReason_id() {
                return reason_id;
            }

            public void setReason_id(int reason_id) {
                this.reason_id = reason_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
