package com.hongyuan.fitness.ui.main.main_home.near_store;

public class NearbyStoreBean {

    private int nearImg;
    private String storeName;
    private String distance;
    private String price;


    public NearbyStoreBean(int nearImg, String storeName, String distance, String price) {
        this.nearImg = nearImg;
        this.storeName = storeName;
        this.distance = distance;
        this.price = price;
    }

    public int getNearImg() {
        return nearImg;
    }

    public void setNearImg(int nearImg) {
        this.nearImg = nearImg;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
