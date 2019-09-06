package com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class FilterGoupClassTypeBeans extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * si_id : 2
         * si_name : 瑜伽
         */

        private int si_id;
        private String si_name;
        private boolean select;

        public int getSi_id() {
            return si_id;
        }

        public void setSi_id(int si_id) {
            this.si_id = si_id;
        }

        public String getSi_name() {
            return si_name;
        }

        public void setSi_name(String si_name) {
            this.si_name = si_name;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }
    }
}
