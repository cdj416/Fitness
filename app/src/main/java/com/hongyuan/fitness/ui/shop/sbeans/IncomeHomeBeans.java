package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

public class IncomeHomeBeans extends BaseBean {

    /**
     * data : {"all_income":0,"all_wait_income":1.2,"m_income":"0.00"}
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
         */

        private int all_income;
        private double all_wait_income;
        private String m_income;

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
    }
}
