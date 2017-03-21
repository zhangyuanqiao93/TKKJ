package com.tkkj.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TKKJ on 2017/3/21.
 */

public class data implements  Parcelable{
    /**
     * summary : 简介
     * title : 标题
     * url : http://www.ceshi.com
     */

    private String summary;
    private String title;
    private String url;

    public data(){
    }

    protected data(Parcel in) {
        summary = in.readString();
        title = in.readString();
        url = in.readString();
    }

    public static final Creator<data> CREATOR = new Creator<data>() {
        @Override
        public data createFromParcel(Parcel in) {
            return new data(in);
        }

        @Override
        public data[] newArray(int size) {
            return new data[size];
        }
    };

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(summary);
        dest.writeString(title);
        dest.writeString(url);
    }
}
