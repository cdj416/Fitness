package com.hongyuan.fitness.ui.look_img;

import android.os.Parcel;
import android.os.Parcelable;

public class MyLookImgDataBeans implements Parcelable {
    private String mPosition;
    private String showTitle;
    private String imgUrl;
    private String imgId;

    public MyLookImgDataBeans(String mPosition, String showTitle, String imgUrl, String imgId) {
        this.mPosition = mPosition;
        this.showTitle = showTitle;
        this.imgUrl = imgUrl;
        this.imgId = imgId;
    }

    protected MyLookImgDataBeans(Parcel in) {
        mPosition = in.readString();
        showTitle = in.readString();
        imgUrl = in.readString();
        imgId = in.readString();
    }

    public static final Creator<MyLookImgDataBeans> CREATOR = new Creator<MyLookImgDataBeans>() {
        @Override
        public MyLookImgDataBeans createFromParcel(Parcel in) {
            return new MyLookImgDataBeans(in);
        }

        @Override
        public MyLookImgDataBeans[] newArray(int size) {
            return new MyLookImgDataBeans[size];
        }
    };

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getmPosition() {
        return mPosition;
    }

    public void setmPosition(String mPosition) {
        this.mPosition = mPosition;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPosition);
        dest.writeString(showTitle);
        dest.writeString(imgUrl);
        dest.writeString(imgId);
    }
}
