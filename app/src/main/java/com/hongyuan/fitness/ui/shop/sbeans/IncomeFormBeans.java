package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

public class IncomeFormBeans extends BaseBean {

    /**
     * data : {"all_income":0,"all_wait_income":1.2,"m_income":"0.00","today_wait_income_count":0,"today_wait_income":0,"yesterday_wait_income_count":0,"yesterday_wait_income":0}
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
         * all_income : 0
         * all_wait_income : 1.2
         * m_income : 0.00
         * today_wait_income_count : 0
         * today_wait_income : 0
         * yesterday_wait_income_count : 0
         * yesterday_wait_income : 0
         */

        private int all_income;
        private double all_wait_income;
        private String m_income;
        private int today_wait_income_count;
        private int today_wait_income;
        private int yesterday_wait_income_count;
        private int yesterday_wait_income;

        public int getAll_income() {
            return all_income;
        }

        public void setAll_income(int all_income) {
            this.all_income = all_income;
        }

        public double getAll_wait_income() {
            return all_wait_income;
        }

        public void setAll_wait_income(double all_wait_income) {
            this.all_wait_income = all_wait_income;
        }

        public String getM_income() {
            return m_income;
        }

        public void setM_income(String m_income) {
            this.m_income = m_income;
        }

        public int getToday_wait_income_count() {
            return today_wait_income_count;
        }

        public void setToday_wait_income_count(int today_wait_income_count) {
            this.today_wait_income_count = today_wait_income_count;
        }

        public int getToday_wait_income() {
            return today_wait_income;
        }

        public void setToday_wait_income(int today_wait_income) {
            this.today_wait_income = today_wait_income;
        }

        public int getYesterday_wait_income_count() {
            return yesterday_wait_income_count;
        }

        public void setYesterday_wait_income_count(int yesterday_wait_income_count) {
            this.yesterday_wait_income_count = yesterday_wait_income_count;
        }

        public int getYesterday_wait_income() {
            return yesterday_wait_income;
        }

        public void setYesterday_wait_income(int yesterday_wait_income) {
            this.yesterday_wait_income = yesterday_wait_income;
        }
    }
}
