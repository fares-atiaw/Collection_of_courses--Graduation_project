package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

public class S_getCourses implements Parcelable {

    private int subCategoryID;
    private String choice;

    public S_getCourses(int subCategoryID, String choice) {
        this.subCategoryID = subCategoryID;
        this.choice = choice;
    }

    protected S_getCourses(Parcel in) {
        subCategoryID = in.readInt();
        choice = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(subCategoryID);
        dest.writeString(choice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<S_getCourses> CREATOR = new Creator<S_getCourses>() {
        @Override
        public S_getCourses createFromParcel(Parcel in) {
            return new S_getCourses(in);
        }

        @Override
        public S_getCourses[] newArray(int size) {
            return new S_getCourses[size];
        }
    };

    public int getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(int subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
}
