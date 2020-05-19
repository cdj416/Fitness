package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class AtherSearchBeans extends BaseBean {


    /**
     * data : {"list":[{"word_name":"水果"},{"word_name":"运动"}]}
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
             * word_name : 水果
             */

            private String word_name;

            public String getWord_name() {
                return word_name;
            }

            public void setWord_name(String word_name) {
                this.word_name = word_name;
            }
        }
    }
}
