package com.hongyuan.fitness.ui.person.physical_data.add_physical;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class PhysicaldataListBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"id":9,"m_id":13,"add_time":1575439740,"body_code":"weight","body_value":"63.00","add_date":"2019-12-04 14:09:00"}]}
     */

    private boolean hasmore;
    private int curpage;
    private int page_total;
    private DataBean data;

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public int getCurpage() {
        return curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

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
             * id : 9
             * m_id : 13
             * add_time : 1575439740
             * body_code : weight
             * body_value : 63.00
             * add_date : 2019-12-04 14:09:00
             */

            private int id;
            private int m_id;
            private int add_time;
            private String body_code;
            private String body_value;
            private String add_date;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getBody_code() {
                return body_code;
            }

            public void setBody_code(String body_code) {
                this.body_code = body_code;
            }

            public String getBody_value() {
                return body_value;
            }

            public void setBody_value(String body_value) {
                this.body_value = body_value;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }
        }
    }
}
