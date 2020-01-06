package com.hongyuan.fitness.ui.only_equipment.indicator_details;

import com.hongyuan.fitness.util.GsonUtil;
import com.hongyuan.fitness.util.TimeUtil;
import com.yolanda.health.qnblesdk.bean.QNExercise;
import com.yolanda.health.qnblesdk.bean.QNExerciseItem;
import com.yolanda.health.qnblesdk.bean.QNHeartRate;
import com.yolanda.health.qnblesdk.bean.QNHeartRateItem;
import com.yolanda.health.qnblesdk.bean.QNSleep;
import com.yolanda.health.qnblesdk.bean.QNSleepItem;
import com.yolanda.health.qnblesdk.bean.QNSport;
import com.yolanda.health.qnblesdk.bean.QNSportItem;

import java.util.ArrayList;
import java.util.List;

public class WristbandHistoryUtils {
    private static WristbandHistoryUtils historyUtils = null;

    private WristbandHistoryUtils(){

    }

    public static WristbandHistoryUtils getInstance(){
        if(historyUtils == null){
            historyUtils = new WristbandHistoryUtils();
        }

        return historyUtils;
    }

    /*********************************************************************************************/
    private boolean sleep;
    private boolean heartRate;
    private boolean sport;
    private boolean exercise;

    public boolean isExercise() {
        return exercise;
    }

    public void setExercise(boolean exercise) {
        this.exercise = exercise;
    }

    public boolean isSport() {
        return sport;
    }

    public void setSport(boolean sport) {
        this.sport = sport;
    }

    public boolean isSleep() {
        return sleep;
    }

    public void setSleep(boolean sleep) {
        this.sleep = sleep;
    }

    public boolean isHeartRate() {
        return heartRate;
    }

    public void setHeartRate(boolean heartRate) {
        this.heartRate = heartRate;
    }

    /*
    * 组装睡眠数据
    * */
    public String getSleep(List<QNSleep> data){
        List<SDsleepBeans> mList = new ArrayList<>();
        for(QNSleep sleep:data){
            SDsleepBeans beans = new SDsleepBeans();
            beans.setRecord_time(TimeUtil.getStringByFormat(sleep.getDate(),TimeUtil.dateFormatYMDHMS));
            beans.setSumDeepSleepMinute(sleep.getSumDeepSleepMinute());
            beans.setSumLightSleepMinute(sleep.getSumLightSleepMinute());
            beans.setSumSleepMinute(sleep.getSumSleepMinute());
            beans.setSumSoberMinute(sleep.getSumSoberMinute());
            beans.setSumSportMinute(sleep.getSumSportMinute());

            List<SDsleepBeans.ItemStrBean> itemList = new ArrayList<>();
            for(QNSleepItem qnSleepItem : sleep.getSleepItems()){
                SDsleepBeans.ItemStrBean itemStrBean = new SDsleepBeans.ItemStrBean();
                itemStrBean.setCurCNT(String.valueOf(qnSleepItem.getCurCNT()));
                itemStrBean.setEndTime(TimeUtil.getStringByFormat(qnSleepItem.getEndDate(),TimeUtil.dateFormatYMDHMS));
                itemStrBean.setSleepTime(String.valueOf(qnSleepItem.getSleepMinute()));
                itemStrBean.setSleepType(String.valueOf(qnSleepItem.getSleepType()));
                itemStrBean.setStartTime(TimeUtil.getStringByFormat(qnSleepItem.getStartDate(),TimeUtil.dateFormatYMDHMS));
                itemList.add(itemStrBean);
            }
            beans.setItem_str(itemList);

            mList.add(beans);
        }

        return GsonUtil.getGson().toJson(mList);
    }

    public static class SDsleepBeans {

        /**
         * record_time : 2019-12-27 8:00:00
         * sumSleepMinute : 50
         * sumDeepSleepMinute : 30
         * sumLightSleepMinute : 20
         * sumSoberMinute : 10
         * sumSportMinute : 50
         * item_str : [{"curCNT":"1","startTime":"2019-12-27 8:00:00","endTime":"2019-12-27 8:00:00","sleepType":"2","sleepTime":"480"},{"curCNT":"2","startTime":"2019-12-27 8:00:00","endTime":"2019-12-27 8:00:00","sleepType":"1","sleepTime":"480"}]
         */

        private String record_time;
        private int sumSleepMinute;
        private int sumDeepSleepMinute;
        private int sumLightSleepMinute;
        private int sumSoberMinute;
        private int sumSportMinute;
        private List<ItemStrBean> item_str;

        public String getRecord_time() {
            return record_time;
        }

        public void setRecord_time(String record_time) {
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

        public List<ItemStrBean> getItem_str() {
            return item_str;
        }

        public void setItem_str(List<ItemStrBean> item_str) {
            this.item_str = item_str;
        }

        public static class ItemStrBean {
            /**
             * curCNT : 1
             * startTime : 2019-12-27 8:00:00
             * endTime : 2019-12-27 8:00:00
             * sleepType : 2
             * sleepTime : 480
             */

            private String curCNT;
            private String startTime;
            private String endTime;
            private String sleepType;
            private String sleepTime;

            public String getCurCNT() {
                return curCNT;
            }

            public void setCurCNT(String curCNT) {
                this.curCNT = curCNT;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getSleepType() {
                return sleepType;
            }

            public void setSleepType(String sleepType) {
                this.sleepType = sleepType;
            }

            public String getSleepTime() {
                return sleepTime;
            }

            public void setSleepTime(String sleepTime) {
                this.sleepTime = sleepTime;
            }
        }
    }

    /*
     * 组装心率数据
     * */
    public String getHeartRate(List<QNHeartRate> data){
        List<SDheartRate> mList = new ArrayList<>();
        for(QNHeartRate heartRate:data){
            SDheartRate beans = new SDheartRate();
            beans.setRecord_time(TimeUtil.getStringByFormat(heartRate.getDate(),TimeUtil.dateFormatYMDHMS));
            beans.setAerobicThreshold(String.valueOf(heartRate.getAerobicThreshold()));
            beans.setBurnFatThreshold(String.valueOf(heartRate.getBurnFatThreshold()));
            beans.setLimitThreshold(String.valueOf(heartRate.getLimitThreshold()));
            beans.setSlientHeartRate(heartRate.getSmoothHeartRate());

            List<SDheartRate.ItemStrBean> itemList = new ArrayList<>();
            for(QNHeartRateItem heartRateItem : heartRate.getHeartRateItems()){
                SDheartRate.ItemStrBean itemStrBean = new SDheartRate.ItemStrBean();
                itemStrBean.setCurCNT(String.valueOf(heartRateItem.getCurCNT()));
                itemStrBean.setItem_time(TimeUtil.getStringByFormat(heartRateItem.getDate(),TimeUtil.dateFormatYMDHMS));
                itemStrBean.setHeartRate(String.valueOf(heartRateItem.getHeartRate()));
                itemList.add(itemStrBean);
            }
            beans.setItem_str(itemList);

            mList.add(beans);
        }

        return GsonUtil.getGson().toJson(mList);
    }

    public static class SDheartRate{

        /**
         * slientHeartRate : 1
         * record_time : 2019-12-27 8:00:00
         * burnFatThreshold : 55
         * aerobicThreshold : 77
         * limitThreshold : 88
         * item_str : [{"curCNT":"1","item_time":"2019-12-27 18:00:00","heartRate":"23"},{"curCNT":"2","item_time":"2019-12-28 18:00:00","heartRate":"27"}]
         */

        private int slientHeartRate;
        private String record_time;
        private String burnFatThreshold;
        private String aerobicThreshold;
        private String limitThreshold;
        private List<ItemStrBean> item_str;

        public int getSlientHeartRate() {
            return slientHeartRate;
        }

        public void setSlientHeartRate(int slientHeartRate) {
            this.slientHeartRate = slientHeartRate;
        }

        public String getRecord_time() {
            return record_time;
        }

        public void setRecord_time(String record_time) {
            this.record_time = record_time;
        }

        public String getBurnFatThreshold() {
            return burnFatThreshold;
        }

        public void setBurnFatThreshold(String burnFatThreshold) {
            this.burnFatThreshold = burnFatThreshold;
        }

        public String getAerobicThreshold() {
            return aerobicThreshold;
        }

        public void setAerobicThreshold(String aerobicThreshold) {
            this.aerobicThreshold = aerobicThreshold;
        }

        public String getLimitThreshold() {
            return limitThreshold;
        }

        public void setLimitThreshold(String limitThreshold) {
            this.limitThreshold = limitThreshold;
        }

        public List<ItemStrBean> getItem_str() {
            return item_str;
        }

        public void setItem_str(List<ItemStrBean> item_str) {
            this.item_str = item_str;
        }

        public static class ItemStrBean {
            /**
             * curCNT : 1
             * item_time : 2019-12-27 18:00:00
             * heartRate : 23
             */

            private String curCNT;
            private String item_time;
            private String heartRate;

            public String getCurCNT() {
                return curCNT;
            }

            public void setCurCNT(String curCNT) {
                this.curCNT = curCNT;
            }

            public String getItem_time() {
                return item_time;
            }

            public void setItem_time(String item_time) {
                this.item_time = item_time;
            }

            public String getHeartRate() {
                return heartRate;
            }

            public void setHeartRate(String heartRate) {
                this.heartRate = heartRate;
            }
        }
    }

    /*
     * 组装运动数据
     * */
    public String getSport(List<QNSport> data){
        List<SDsport> mList = new ArrayList<>();
        for(QNSport sport:data){
            SDsport beans = new SDsport();
            beans.setRecord_time(TimeUtil.getStringByFormat(sport.getDate(),TimeUtil.dateFormatYMDHMS));
            beans.setSumActiveTime(sport.getSumActiveMinute());
            beans.setSumCalories(sport.getSumCalories());
            beans.setSumDistance(sport.getSumDistance());
            beans.setSumStep(sport.getSumStep());

            List<SDsport.ItemStrBean> itemList = new ArrayList<>();
            for(QNSportItem itemBean : sport.getSportItems()){
                SDsport.ItemStrBean itemStrBean = new SDsport.ItemStrBean();
                itemStrBean.setCurCNT(String.valueOf(itemBean.getCurCNT()));
                itemStrBean.setStartTime(TimeUtil.getStringByFormat(itemBean.getStartDate(),TimeUtil.dateFormatYMDHMS));
                itemStrBean.setEndTime(TimeUtil.getStringByFormat(itemBean.getEndDate(),TimeUtil.dateFormatYMDHMS));
                itemStrBean.setActiveTime(String.valueOf(itemBean.getActiveMinute()));
                itemStrBean.setCalories(String.valueOf(itemBean.getCalories()));
                itemStrBean.setDistance(String.valueOf(itemBean.getDistance()));
                itemStrBean.setStep(String.valueOf(itemBean.getStep()));
                itemList.add(itemStrBean);
            }
            beans.setItem_str(itemList);

            mList.add(beans);
        }

        return GsonUtil.getGson().toJson(mList);
    }

    public static class SDsport{

        /**
         * sumStep : 1000
         * sumCalories : 50
         * sumDistance : 30
         * sumActiveTime : 20
         * record_time : 2019-12-27 8:00:00
         * item_str : [{"curCNT":"1","startTime":"2019-12-27 8:00:00","endTime":"2019-12-27 8:00:00","step":"2","activeTime":"480","calories":"8","distance":"9"},{"curCNT":"2","startTime":"2019-12-28 8:00:00","endTime":"2019-12-28 8:00:00","step":"6","activeTime":"480","calories":"9","distance":"88"}]
         */

        private int sumStep;
        private int sumCalories;
        private int sumDistance;
        private int sumActiveTime;
        private String record_time;
        private List<ItemStrBean> item_str;

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

        public String getRecord_time() {
            return record_time;
        }

        public void setRecord_time(String record_time) {
            this.record_time = record_time;
        }

        public List<ItemStrBean> getItem_str() {
            return item_str;
        }

        public void setItem_str(List<ItemStrBean> item_str) {
            this.item_str = item_str;
        }

        public static class ItemStrBean {
            /**
             * curCNT : 1
             * startTime : 2019-12-27 8:00:00
             * endTime : 2019-12-27 8:00:00
             * step : 2
             * activeTime : 480
             * calories : 8
             * distance : 9
             */

            private String curCNT;
            private String startTime;
            private String endTime;
            private String step;
            private String activeTime;
            private String calories;
            private String distance;

            public String getCurCNT() {
                return curCNT;
            }

            public void setCurCNT(String curCNT) {
                this.curCNT = curCNT;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getStep() {
                return step;
            }

            public void setStep(String step) {
                this.step = step;
            }

            public String getActiveTime() {
                return activeTime;
            }

            public void setActiveTime(String activeTime) {
                this.activeTime = activeTime;
            }

            public String getCalories() {
                return calories;
            }

            public void setCalories(String calories) {
                this.calories = calories;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }
        }
    }

    /*
     * 组装健身数据
     * */
    public String getExercise(List<QNExercise> data){
        List<SDexercise> mList = new ArrayList<>();
        for(QNExercise sport:data){
            SDexercise beans = new SDexercise();
            beans.setRecord_time(TimeUtil.getStringByFormat(sport.getDate(),TimeUtil.dateFormatYMDHMS));
            beans.setExerciseType(sport.getExerciseType());

            List<SDexercise.ItemStrBean> itemList = new ArrayList<>();
            for(QNExerciseItem itemBean : sport.getExerciseItems()){
                SDexercise.ItemStrBean itemStrBean = new SDexercise.ItemStrBean();
                itemStrBean.setStartTime(TimeUtil.getStringByFormat(itemBean.getStartDate(),TimeUtil.dateFormatYMDHMS));
                itemStrBean.setEndTime(TimeUtil.getStringByFormat(itemBean.getEndDate(),TimeUtil.dateFormatYMDHMS));
                itemStrBean.setStep(String.valueOf(itemBean.getStep()));
                itemStrBean.setCalories(String.valueOf(itemBean.getCalories()));
                itemStrBean.setDistance(String.valueOf(itemBean.getDistance()));
                itemStrBean.setExerciseTime(String.valueOf(itemBean.getExerciseTime()));
                itemStrBean.setHeartRate(String.valueOf(itemBean.getHeartRate()));
                itemStrBean.setMinkm(String.valueOf(itemBean.getMinkm()));
                itemStrBean.setMinHeartRate(String.valueOf(itemBean.getMinHeartRate()));
                itemStrBean.setAveHeartRate(String.valueOf(itemBean.getAveHeartRate()));
                itemList.add(itemStrBean);
            }
            beans.setItem_str(itemList);

            mList.add(beans);
        }

        return GsonUtil.getGson().toJson(mList);
    }

    public static class SDexercise{

        /**
         * exerciseType : 1
         * record_time : 2019-12-27 8:00:00
         * item_str : [{"startTime":"2019-12-27 8:00:00","endTime":"2019-12-27 18:00:00","step":"23","calories":"83","distance":"9333","exerciseTime":"9333","minkm":"9","heartRate":"9","minHeartRate":"480","aveHeartRate":"430"},{"startTime":"2019-12-28 8:00:00","endTime":"2019-12-28 18:00:00","step":"23","calories":"83","distance":"9333","exerciseTime":"9333","minkm":"9","heartRate":"9","minHeartRate":"480","aveHeartRate":"430"}]
         */

        private int exerciseType;
        private String record_time;
        private List<ItemStrBean> item_str;

        public int getExerciseType() {
            return exerciseType;
        }

        public void setExerciseType(int exerciseType) {
            this.exerciseType = exerciseType;
        }

        public String getRecord_time() {
            return record_time;
        }

        public void setRecord_time(String record_time) {
            this.record_time = record_time;
        }

        public List<ItemStrBean> getItem_str() {
            return item_str;
        }

        public void setItem_str(List<ItemStrBean> item_str) {
            this.item_str = item_str;
        }

        public static class ItemStrBean {
            /**
             * startTime : 2019-12-27 8:00:00
             * endTime : 2019-12-27 18:00:00
             * step : 23
             * calories : 83
             * distance : 9333
             * exerciseTime : 9333
             * minkm : 9
             * heartRate : 9
             * minHeartRate : 480
             * aveHeartRate : 430
             */

            private String startTime;
            private String endTime;
            private String step;
            private String calories;
            private String distance;
            private String exerciseTime;
            private String minkm;
            private String heartRate;
            private String minHeartRate;
            private String aveHeartRate;

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getStep() {
                return step;
            }

            public void setStep(String step) {
                this.step = step;
            }

            public String getCalories() {
                return calories;
            }

            public void setCalories(String calories) {
                this.calories = calories;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getExerciseTime() {
                return exerciseTime;
            }

            public void setExerciseTime(String exerciseTime) {
                this.exerciseTime = exerciseTime;
            }

            public String getMinkm() {
                return minkm;
            }

            public void setMinkm(String minkm) {
                this.minkm = minkm;
            }

            public String getHeartRate() {
                return heartRate;
            }

            public void setHeartRate(String heartRate) {
                this.heartRate = heartRate;
            }

            public String getMinHeartRate() {
                return minHeartRate;
            }

            public void setMinHeartRate(String minHeartRate) {
                this.minHeartRate = minHeartRate;
            }

            public String getAveHeartRate() {
                return aveHeartRate;
            }

            public void setAveHeartRate(String aveHeartRate) {
                this.aveHeartRate = aveHeartRate;
            }
        }
    }
}
