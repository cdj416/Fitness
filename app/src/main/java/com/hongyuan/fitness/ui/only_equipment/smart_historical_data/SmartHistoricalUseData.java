package com.hongyuan.fitness.ui.only_equipment.smart_historical_data;

import android.os.Parcel;
import android.os.Parcelable;

public class SmartHistoricalUseData implements  Parcelable {

    private String add_date;
    private String showText;
    private String unit;

    public SmartHistoricalUseData(){}

    protected SmartHistoricalUseData(Parcel in) {
        add_date = in.readString();
        showText = in.readString();
        unit = in.readString();
    }

    public static final Creator<SmartHistoricalUseData> CREATOR = new Creator<SmartHistoricalUseData>() {
        @Override
        public SmartHistoricalUseData createFromParcel(Parcel in) {
            return new SmartHistoricalUseData(in);
        }

        @Override
        public SmartHistoricalUseData[] newArray(int size) {
            return new SmartHistoricalUseData[size];
        }
    };

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(add_date);
        dest.writeString(showText);
        dest.writeString(unit);
    }

}
