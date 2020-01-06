package com.hongyuan.fitness.ui.person.exercise_data;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class ExeriseDataTopBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"max_step":8359,"max_date":"2019-12-31 00:00","list":[{"sport_id":8,"m_id":13,"record_time":1577721600,"sumStep":8359,"sumCalories":270,"sumDistance":6420,"sumActiveTime":0,"record_date":"2019-12-31 00:00"},{"sport_id":7,"m_id":13,"record_time":1577635200,"sumStep":4363,"sumCalories":139,"sumDistance":3320,"sumActiveTime":0,"record_date":"2019-12-30 00:00"},{"sport_id":6,"m_id":13,"record_time":1514908800,"sumStep":2260,"sumCalories":72,"sumDistance":1720,"sumActiveTime":0,"record_date":"2018-01-03 00:00"},{"sport_id":5,"m_id":13,"record_time":1514822400,"sumStep":0,"sumCalories":0,"sumDistance":0,"sumActiveTime":0,"record_date":"2018-01-02 00:00"},{"sport_id":4,"m_id":13,"record_time":0,"sumStep":60,"sumCalories":1,"sumDistance":50,"sumActiveTime":0,"record_date":"1970-01-01 08:00"}]}
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
        /**
         * max_step : 8359
         * max_date : 2019-12-31 00:00
         * list : [{"sport_id":8,"m_id":13,"record_time":1577721600,"sumStep":8359,"sumCalories":270,"sumDistance":6420,"sumActiveTime":0,"record_date":"2019-12-31 00:00"},{"sport_id":7,"m_id":13,"record_time":1577635200,"sumStep":4363,"sumCalories":139,"sumDistance":3320,"sumActiveTime":0,"record_date":"2019-12-30 00:00"},{"sport_id":6,"m_id":13,"record_time":1514908800,"sumStep":2260,"sumCalories":72,"sumDistance":1720,"sumActiveTime":0,"record_date":"2018-01-03 00:00"},{"sport_id":5,"m_id":13,"record_time":1514822400,"sumStep":0,"sumCalories":0,"sumDistance":0,"sumActiveTime":0,"record_date":"2018-01-02 00:00"},{"sport_id":4,"m_id":13,"record_time":0,"sumStep":60,"sumCalories":1,"sumDistance":50,"sumActiveTime":0,"record_date":"1970-01-01 08:00"}]
         */

        private int max_step;
        private String max_date;
        private List<ListBean> list;

        public int getMax_step() {
            return max_step;
        }

        public void setMax_step(int max_step) {
            this.max_step = max_step;
        }

        public String getMax_date() {
            return max_date;
        }

        public void setMax_date(String max_date) {
            this.max_date = max_date;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * sport_id : 8
             * m_id : 13
             * record_time : 1577721600
             * sumStep : 8359
             * sumCalories : 270
             * sumDistance : 6420
             * sumActiveTime : 0
             * record_date : 2019-12-31 00:00
             */

            private int sport_id;
            private int m_id;
            private int record_time;
            private int sumStep;
            private int sumCalories;
            private int sumDistance;
            private int sumActiveTime;
            private String record_date;

            public int getSport_id() {
                return sport_id;
            }

            public void setSport_id(int sport_id) {
                this.sport_id = sport_id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getRecord_time() {
                return record_time;
            }

            public void setRecord_time(int record_time) {
                this.record_time = record_time;
            }

            public int getSumStep() {
                return sumStep;
            }

            public void setSumStep(int sumStep) {
                this.sumStep = sumStep;
            }

            public int getSumCalories() {
                return sumCalories;
            }

            public void setSumCalories(int sumCalories) {
                this.sumCalories = sumCalories;
            }

            public int getSumDistance() {
                return sumDistance;
            }

            public void setSumDistance(int sumDistance) {
                this.sumDistance = sumDistance;
            }

            public int getSumActiveTime() {
                return sumActiveTime;
            }

            public void setSumActiveTime(int sumActiveTime) {
                this.sumActiveTime = sumActiveTime;
            }

            public String getRecord_date() {
                return record_date;
            }

            public void setRecord_date(String record_date) {
                this.record_date = record_date;
            }
        }
    }
}
