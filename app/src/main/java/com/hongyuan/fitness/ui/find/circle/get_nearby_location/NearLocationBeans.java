package com.hongyuan.fitness.ui.find.circle.get_nearby_location;

import java.io.Serializable;

public class NearLocationBeans implements Serializable {
    private String snippet;
    private String title;
    private String Longitude;
    private String Latitude;

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }
}
