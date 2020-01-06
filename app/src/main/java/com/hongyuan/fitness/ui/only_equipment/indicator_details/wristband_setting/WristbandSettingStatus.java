package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting;

public class WristbandSettingStatus {

    private static WristbandSettingStatus settingStatus = null;

    private WristbandSettingStatus(){

    }

    public static WristbandSettingStatus getInstance(){
        if(settingStatus == null){
            settingStatus= new WristbandSettingStatus();
        }
        return settingStatus;
    }

    /*****************************************基本设置信息**************************************/

    private boolean alarmClock;
    private boolean msgNotification;
    private boolean wcMsgNotification;
    private boolean qqMsgNotification;
    private boolean sedReminder;
    private boolean telCall;
    private boolean brightScreen;
    private boolean heartRate;
    private boolean heartRateZoon;
    private boolean shakeTke;
    private String macAddress;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public boolean isWcMsgNotification() {
        return wcMsgNotification;
    }

    public void setWcMsgNotification(boolean wcMsgNotification) {
        this.wcMsgNotification = wcMsgNotification;
    }

    public boolean isQqMsgNotification() {
        return qqMsgNotification;
    }

    public void setQqMsgNotification(boolean qqMsgNotification) {
        this.qqMsgNotification = qqMsgNotification;
    }

    public boolean isAlarmClock() {
        return alarmClock;
    }

    public void setAlarmClock(boolean alarmClock) {
        this.alarmClock = alarmClock;
    }

    public boolean isMsgNotification() {
        return msgNotification;
    }

    public void setMsgNotification(boolean msgNotification) {
        this.msgNotification = msgNotification;
    }

    public boolean isSedReminder() {
        return sedReminder;
    }

    public void setSedReminder(boolean sedReminder) {
        this.sedReminder = sedReminder;
    }

    public boolean isTelCall() {
        return telCall;
    }

    public void setTelCall(boolean telCall) {
        this.telCall = telCall;
    }

    public boolean isBrightScreen() {
        return brightScreen;
    }

    public void setBrightScreen(boolean brightScreen) {
        this.brightScreen = brightScreen;
    }

    public boolean isHeartRate() {
        return heartRate;
    }

    public void setHeartRate(boolean heartRate) {
        this.heartRate = heartRate;
    }

    public boolean isHeartRateZoon() {
        return heartRateZoon;
    }

    public void setHeartRateZoon(boolean heartRateZoon) {
        this.heartRateZoon = heartRateZoon;
    }

    public boolean isShakeTke() {
        return shakeTke;
    }

    public void setShakeTke(boolean shakeTke) {
        this.shakeTke = shakeTke;
    }
}
