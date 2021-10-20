package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Data_of_news implements Parcelable {

    private int id;
    private String course_name;
    private String logo;
    private String instructor_name;
    private String center_name;
    private String modified_date;
    private String what_is_new;

    public Data_of_news(int id, String course_name, String logo, String instructor_name,
                        String center_name, String modified_date, String what_is_new) {
        this.id = id;
        this.course_name = course_name;
        this.logo = logo;
        this.instructor_name = instructor_name;
        this.center_name = center_name;
        this.modified_date = modified_date;
        this.what_is_new = what_is_new;
    }

    protected Data_of_news(Parcel in) {
        id = in.readInt();
        course_name = in.readString();
        logo = in.readString();
        instructor_name = in.readString();
        center_name = in.readString();
        modified_date = in.readString();
        what_is_new = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(course_name);
        dest.writeString(logo);
        dest.writeString(instructor_name);
        dest.writeString(center_name);
        dest.writeString(modified_date);
        dest.writeString(what_is_new);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data_of_news> CREATOR = new Creator<Data_of_news>() {
        @Override
        public Data_of_news createFromParcel(Parcel in) {
            return new Data_of_news(in);
        }

        @Override
        public Data_of_news[] newArray(int size) {
            return new Data_of_news[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getLogo() {
        return "http://teamcoc-001-site1.btempurl.com/images/"+logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getInstructor_name() {
        return instructor_name;
    }

    public void setInstructor_name(String instructor_name) {
        this.instructor_name = instructor_name;
    }

    public String getCenter_name() {
        return center_name;
    }

    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }

    public String getModified_date() {
        return modified_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    public String getWhat_is_new() {
        return what_is_new;
    }

    public void setWhat_is_new(String what_is_new) {
        this.what_is_new = what_is_new;
    }
}