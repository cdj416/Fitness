package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_sleep;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class WristbandSleepBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"sleep_id":10,"m_id":13,"record_time":1577808000,"sumSleepMinute":164,"sumDeepSleepMinute":45,"sumLightSleepMinute":111,"sumSoberMinute":8,"sumSportMinute":0,"record_date":"2020-01-01 00:00"},{"sleep_id":8,"m_id":13,"record_time":1577786101,"sumSleepMinute":50,"sumDeepSleepMinute":50,"sumLightSleepMinute":50,"sumSoberMinute":60,"sumSportMinute":70,"record_date":"2019-12-31 17:55"},{"sleep_id":7,"m_id":13,"record_time":1577785498,"sumSleepMinute":50,"sumDeepSleepMinute":50,"sumLightSleepMinute":50,"sumSoberMinute":60,"sumSportMinute":70,"record_date":"2019-12-31 17:44"},{"sleep_id":9,"m_id":13,"record_time":1577721600,"sumSleepMinute":526,"sumDeepSleepMinute":79,"sumLightSleepMinute":424,"sumSoberMinute":23,"sumSportMinute":0,"record_date":"2019-12-31 00:00"}]}
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
             * sleep_id : 10
             * m_id : 13
             * record_time : 1577808000
             * sumSleepMinute : 164
             * sumDeepSleepMinute : 45
             * sumLightSleepMinute : 111
             * sumSoberMinute : 8
             * sumSportMinute : 0
             * record_date : 2020-01-01 00:00
             */

            private int sleep_id;
            private int m_id;
            private int record_time;
            private int sumSleepMinute;
            private int sumDeepSleepMinute;
            private int sumLightSleepMinute;
            private int sumSoberMinute;
            private int sumSportMinute;
            private String record_date;

            public int getSleep_id() {
                return sleep_id;
            }

            public void setSleep_id(int sleep_id) {
                this.sleep_id = sleep_id;
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

            public int getSumSleepMinute() {
                return sumSleepMinute;
            }

            public void setSumSleepMinute(int sumSleepMinute) {
                this.sumSleepMinute = sumSleepMinute;
            }

            public int getSumDeepSleepMinute() {
                return sumDeepSleepMinute;
            }

            public void setSumDeepSleepMinute(int sumDeepSleepMinute) {
                this.sumDeepSleepMinute = sumDeepSleepMinute;
            }

            public int getSumLightSleepMinute() {
                return sumLightSleepMinute;
            }

            public void setSumLightSleepMinute(int sumLightSleepMinute) {
                this.sumLightSleepMinute = sumLightSleepMinute;
            }

            public int getSumSoberMinute() {
                return sumSoberMinute;
            }

            public void setSumSoberMinute(int sumSoberMinute) {
                this.sumSoberMinute = sumSoberMinute;
            }

            public int getSumSportMinute() {
                return sumSportMinute;
            }

            public void setSumSportMinute(int sumSportMinute) {
                this.sumSportMinute = sumSportMinute;
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
