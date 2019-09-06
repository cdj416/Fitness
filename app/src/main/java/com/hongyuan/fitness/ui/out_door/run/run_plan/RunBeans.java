package com.hongyuan.fitness.ui.out_door.run.run_plan;

import com.hongyuan.fitness.base.BaseBean;

public class RunBeans extends BaseBean {


    /**
     * data : {"run_data":30.56}
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
         * run_data : 30.56
         */

        private double run_data;

        public double getRun_data() {
            return run_data;
        }

        public void setRun_data(double run_data) {
            this.run_data = run_data;
        }
    }
}
