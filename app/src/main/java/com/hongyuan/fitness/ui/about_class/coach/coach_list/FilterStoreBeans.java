package com.hongyuan.fitness.ui.about_class.coach.coach_list;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class FilterStoreBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"os_id":16,"os_name":"首玺健身（环城西路店）"},{"os_id":18,"os_name":"首玺健身汎港店"},{"os_id":21,"os_name":"湖州首玺健身爱山店"},{"os_id":22,"os_name":"湖州首玺健身管理有限公司湖东店"},{"os_id":24,"os_name":"首玺健身环城西路店"},{"os_id":26,"os_name":"首玺健身金色水岸店"},{"os_id":27,"os_name":"fdsafdsafdsa"}]}
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
             * os_id : 16
             * os_name : 首玺健身（环城西路店）
             */

            private int os_id;
            private String os_name;
            private boolean select;

            public int getOs_id() {
                return os_id;
            }

            public void setOs_id(int os_id) {
                this.os_id = os_id;
            }

            public String getOs_name() {
                return os_name;
            }

            public void setOs_name(String os_name) {
                this.os_name = os_name;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }
        }
    }
}
