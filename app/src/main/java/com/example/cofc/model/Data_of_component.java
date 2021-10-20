package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Data_of_component implements Parcelable {

    private int componentID;
    private String componentName;
    private List<Data_of_video> video;

    public Data_of_component(int componentID, String componentName, List<Data_of_video> video) {
        this.componentID = componentID;
        this.componentName = componentName;
        this.video = video;
    }

    protected Data_of_component(Parcel in) {
        componentID = in.readInt();
        componentName = in.readString();
        video = in.createTypedArrayList(Data_of_video.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(componentID);
        dest.writeString(componentName);
        dest.writeTypedList(video);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data_of_component> CREATOR = new Creator<Data_of_component>() {
        @Override
        public Data_of_component createFromParcel(Parcel in) {
            return new Data_of_component(in);
        }

        @Override
        public Data_of_component[] newArray(int size) {
            return new Data_of_component[size];
        }
    };

    public int getComponentID() {
        return componentID;
    }

    public void setComponentID(int componentID) {
        this.componentID = componentID;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public List<Data_of_video> getVideo() {
        return video;
    }

    public void setVideo(List<Data_of_video> video) {
        this.video = video;
    }
}
