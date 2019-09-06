package com.hongyuan.fitness.ui.main.main_about_class.group_class;

public class TimeSelectBean {
    private String startTime;
    private String endTime;
    private String time;
    private boolean select;

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

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getTime(){
        return this.startTime+"-"+this.endTime;
    }
}
