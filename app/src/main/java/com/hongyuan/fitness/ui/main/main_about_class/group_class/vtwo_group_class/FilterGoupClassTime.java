package com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class;

public class FilterGoupClassTime {
    private String startTime;
    private String endTime;
    private boolean select;

    public FilterGoupClassTime(){

    }

    public FilterGoupClassTime(String startTime, String endTime, boolean select) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.select = select;
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

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
