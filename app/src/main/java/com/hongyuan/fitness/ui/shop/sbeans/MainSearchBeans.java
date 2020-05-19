package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class MainSearchBeans extends BaseBean {


    /**
     * data : {"list":[{"habit_name":"水果"},{"habit_name":"运动鞋"},{"habit_name":"在"},{"habit_name":"运动"},{"habit_name":"你是在说我"},{"habit_name":"低热量简餐"},{"habit_name":"嘻嘻嘻"},{"habit_name":"洗衣液"},{"habit_name":"户外风衣"},{"habit_name":"速干衣裤"}]}
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
             * habit_name : 水果
             */

            private String habit_name;

            public String getHabit_name() {
                return habit_name;
            }

            public void setHabit_name(String habit_name) {
                this.habit_name = habit_name;
            }
        }
    }
}
