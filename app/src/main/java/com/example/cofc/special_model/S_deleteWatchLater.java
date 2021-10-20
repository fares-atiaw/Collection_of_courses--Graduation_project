package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

public class S_deleteWatchLater implements Parcelable {

    private int watchLaterID;
    private int courseID;

    public S_deleteWatchLater(int watchLaterID, int courseID) {
        this.watchLaterID = watchLaterID;
        this.courseID = courseID;
    }

    protected S_deleteWatchLater(Parcel in) {
        watchLaterID = in.readInt();
        courseID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(watchLaterID);
        dest.writeInt(courseID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<S_deleteWatchLater> CREATOR = new Creator<S_deleteWatchLater>() {
        @Override
        public S_deleteWatchLater createFromParcel(Parcel in) {
            return new S_deleteWatchLater(in);
        }

        @Override
        public S_deleteWatchLater[] newArray(int size) {
            return new S_deleteWatchLater[size];
        }
    };

    public int getWatchLaterID() {
        return watchLaterID;
    }

    public void setWatchLaterID(int watchLaterID) {
        this.watchLaterID = watchLaterID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
