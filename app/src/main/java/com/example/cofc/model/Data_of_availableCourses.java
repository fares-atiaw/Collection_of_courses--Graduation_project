package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Data_of_availableCourses implements Parcelable {

    private String courseName;
    private List<Data_of_component> components;

    public Data_of_availableCourses(String courseName, List<Data_of_component> components) {
        this.courseName = courseName;
        this.components = components;
    }

    protected Data_of_availableCourses(Parcel in) {
        courseName = in.readString();
        components = in.createTypedArrayList(Data_of_component.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseName);
        dest.writeTypedList(components);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data_of_availableCourses> CREATOR = new Creator<Data_of_availableCourses>() {
        @Override
        public Data_of_availableCourses createFromParcel(Parcel in) {
            return new Data_of_availableCourses(in);
        }

        @Override
        public Data_of_availableCourses[] newArray(int size) {
            return new Data_of_availableCourses[size];
        }
    };

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Data_of_component> getComponents() {
        return components;
    }

    public void setComponents(List<Data_of_component> components) {
        this.components = components;
    }
}
