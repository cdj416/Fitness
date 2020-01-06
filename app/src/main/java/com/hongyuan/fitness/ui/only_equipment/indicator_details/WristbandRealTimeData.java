package com.hongyuan.fitness.ui.only_equipment.indicator_details;

public class WristbandRealTimeData {

    private static WristbandRealTimeData realTimeData;

    private WristbandRealTimeData(){}

    public static WristbandRealTimeData getInstance(){
        if(realTimeData == null){
            realTimeData = new WristbandRealTimeData();
        }

        return realTimeData;
    }

    //步数
    private int step;
    //卡路里
    private int calories;
    //距离
    private int distance;
    //运动时间
    private int active;
    //心率数据
    private int heartRate;
    //睡眠时间
    private int sleep;

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }
}
