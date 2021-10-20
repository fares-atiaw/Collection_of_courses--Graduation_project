package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Data_of_course implements Parcelable {

    private int courseID;
    private String logo;
    private String courseName;
    //private String releasedDate;
    private String relasedDate;
    private String instructorName;
    private String centerName;
    private String qA_Following;
    private boolean online;
    private String address;
    private Double price;
    private boolean options;
    private Float stars;

    public Data_of_course(int courseID, String logo, String courseName, String relasedDate, String instructorName, String centerName,
                          String qA_Following, boolean online, String address, Double price, boolean options, Float stars) {
        this.courseID = courseID;
        this.logo = logo;
        this.courseName = courseName;
        this.relasedDate = relasedDate;
        this.instructorName = instructorName;
        this.centerName = centerName;
        this.qA_Following = qA_Following;
        this.online = online;
        this.address = address;
        this.price = price;
        this.options = options;
        this.stars = stars;
    }

    protected Data_of_course(Parcel in) {
        courseID = in.readInt();
        logo = in.readString();
        courseName = in.readString();
        relasedDate = in.readString();
        instructorName = in.readString();
        centerName = in.readString();
        qA_Following = in.readString();
        online = in.readByte() != 0;
        address = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        options = in.readByte() != 0;
        if (in.readByte() == 0) {
            stars = null;
        } else {
            stars = in.readFloat();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(courseID);
        dest.writeString(logo);
        dest.writeString(courseName);
        dest.writeString(relasedDate);
        dest.writeString(instructorName);
        dest.writeString(centerName);
        dest.writeString(qA_Following);
        dest.writeByte((byte) (online ? 1 : 0));
        dest.writeString(address);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(price);
        }
        dest.writeByte((byte) (options ? 1 : 0));
        if (stars == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(stars);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data_of_course> CREATOR = new Creator<Data_of_course>() {
        @Override
        public Data_of_course createFromParcel(Parcel in) {
            return new Data_of_course(in);
        }

        @Override
        public Data_of_course[] newArray(int size) {
            return new Data_of_course[size];
        }
    };

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
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

    public String getRelasedDate() {
        return relasedDate;
    }

    public void setRelasedDate(String relasedDate) {
        this.relasedDate = relasedDate;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getqA_Following() {
        return qA_Following;
    }

    public void setqA_Following(String qA_Following) {
        this.qA_Following = qA_Following;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isOptions() {
        return options;
    }

    public void setOptions(boolean options) {
        this.options = options;
    }

    public Float getStars() {
        return stars;
    }

    public void setStars(Float stars) {
        this.stars = stars;
    }
}
