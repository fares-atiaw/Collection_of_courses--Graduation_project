package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

public class S_Add_watchLater implements Parcelable {

    private int courseID;
    private int watchLaterID;

    public S_Add_watchLater(int courseID, int watchLaterID) {
        this.courseID = courseID;
        this.watchLaterID = watchLaterID;
    }

    protected S_Add_watchLater(Parcel in) {
        courseID = in.readInt();
        watchLaterID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(courseID);
        dest.writeInt(watchLaterID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<S_Add_watchLater> CREATOR = new Creator<S_Add_watchLater>() {
        @Override
        public S_Add_watchLater createFromParcel(Parcel in) {
            return new S_Add_watchLater(in);
        }

        @Override
        public S_Add_watchLater[] newArray(int size) {
            return new S_Add_watchLater[size];
        }
    };

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getWatchLaterID() {
        return watchLaterID;
    }

    public void setWatchLaterID(int watchLaterID) {
        this.watchLaterID = watchLaterID;
    }
}
