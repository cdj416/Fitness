package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_alarm_clock.add_alarm_clock;

import com.hongyuan.fitness.base.BaseBean;

public class AlarmIdBeans extends BaseBean {

    /**
     * data : {"alarmId":1}
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
         * alarmId : 1
         */

        private int alarmId;

        public int getAlarmId() {
            return alarmId;
        }

        public void setAlarmId(int alarmId) {
            this.alarmId = alarmId;
        }
    }
}
