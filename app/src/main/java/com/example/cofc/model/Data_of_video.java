package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Data_of_video implements Parcelable {

    private Integer videoID;
    private String videoName;
    private String videoURL;

    public Data_of_video(Integer videoID, String videoName, String videoURL) {
        this.videoID = videoID;
        this.videoName = videoName;
        this.videoURL = videoURL;
    }

    protected Data_of_video(Parcel in) {
        if (in.readByte() == 0) {
            videoID = null;
        } else {
            videoID = in.readInt();
        }
        videoName = in.readString();
        videoURL = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (videoID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(videoID);
        }
        dest.writeString(videoName);
        dest.writeString(videoURL);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data_of_video> CREATOR = new Creator<Data_of_video>() {
        @Override
        public Data_of_video createFromParcel(Parcel in) {
            return new Data_of_video(in);
        }

        @Override
        public Data_of_video[] newArray(int size) {
            return new Data_of_video[size];
        }
    };

    public Integer getVideoID() {
        return videoID;
    }

    public void setVideoID(Integer videoID) {
        this.videoID = videoID;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoURL() {
        return "http://teamcoc-001-site1.btempurl.com/images/"+videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
