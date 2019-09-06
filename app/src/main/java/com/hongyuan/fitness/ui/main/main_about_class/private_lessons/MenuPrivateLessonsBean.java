package com.hongyuan.fitness.ui.main.main_about_class.private_lessons;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class MenuPrivateLessonsBean extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        public DataBean() {
        }

        public DataBean(int ft_id, String ft_name, boolean select) {
            this.ft_id = ft_id;
            this.ft_name = ft_name;
            this.select = select;
        }

        /**
         * ft_id : 7
         * ft_name : 康复
         */

        private int ft_id;
        private String ft_name;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public int getFt_id() {
            return ft_id;
        }

        public void setFt_id(int ft_id) {
            this.ft_id = ft_id;
        }

        public String getFt_name() {
            return ft_name;
        }

        public void setFt_name(String ft_name) {
            this.ft_name = ft_name;
        }
    }
}
