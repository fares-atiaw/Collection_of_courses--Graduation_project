package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Data_of_purchase implements Parcelable {

    private String course_name;
    private List<Data_of_package> packages;

    public Data_of_purchase(String course_name, List<Data_of_package> packages) {
        this.course_name = course_name;
        this.packages = packages;
    }

    protected Data_of_purchase(Parcel in) {
        course_name = in.readString();
    }

    public static final Creator<Data_of_purchase> CREATOR = new Creator<Data_of_purchase>() {
        @Override
        public Data_of_purchase createFromParcel(Parcel in) {
            return new Data_of_purchase(in);
        }

        @Override
        public Data_of_purchase[] newArray(int size) {
            return new Data_of_purchase[size];
        }
    };

    public String getCourse_name() { return course_name; }
    public void setCourse_name(String course_name) { this.course_name = course_name; }

    public List<Data_of_package> getPackages() { return packages; }
    public void setPackages(List<Data_of_package> packages) { this.packages = packages; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(course_name);
    }
}
