package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Data_of_channels implements Parcelable {

    private int channelID;
    private String logo;
    private String courseName;
    private String channelName;
    private String link;

    public Data_of_channels(int channelID, String logo, String courseName, String channelName, String link) {
        this.channelID = channelID;
        this.logo = logo;
        this.courseName = courseName;
        this.channelName = channelName;
        this.link = link;
    }

    protected Data_of_channels(Parcel in) {
        channelID = in.readInt();
        logo = in.readString();
        courseName = in.readString();
        channelName = in.readString();
        link = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(channelID);
        dest.writeString(logo);
        dest.writeString(courseName);
        dest.writeString(channelName);
        dest.writeString(link);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data_of_channels> CREATOR = new Creator<Data_of_channels>() {
        @Override
        public Data_of_channels createFromParcel(Parcel in) {
            return new Data_of_channels(in);
        }

        @Override
        public Data_of_channels[] newArray(int size) {
            return new Data_of_channels[size];
        }
    };

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public String getLogo() {
        return "http://teamcoc-001-site1.btempurl.com/images/"+logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
