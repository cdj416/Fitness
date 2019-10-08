package com.hongyuan.fitness.util;

public class LocationBean {

    private static LocationBean locationBean = null;

    private LocationBean(){

    }

    public static LocationBean getInstance(){
        if(locationBean == null){
            locationBean = new LocationBean();
        }
        return locationBean;
    }

    private String cityName;
    private String lat;
    private String lng;
    private int os_id;//距离最近的门店id

    public String getLat() {
        if(BaseUtil.isValue(lat)){
            return lat;
        }
        return "30.863069";
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        if(BaseUtil.isValue(lng)){
            return lng;
        }
        return "120.099863";
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getOs_id() {
        return os_id;
    }

    public void setOs_id(int os_id) {
        this.os_id = os_id;
    }

    public String getCityName() {
        if(BaseUtil.isValue(cityName)){
            return cityName;
        }
        return "湖州市";
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
