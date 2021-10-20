package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

public class S_edit_Instructor implements Parcelable {

    //private int instructorID;
    private String logo;
    private String username;
    private String phone;
    private String email;
    private String cv;

    public S_edit_Instructor(String logo, String username, String phone, String email, String cv) {
        this.logo = logo;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.cv = cv;
    }

    protected S_edit_Instructor(Parcel in) {
        logo = in.readString();
        username = in.readString();
        phone = in.readString();
        email = in.readString();
        cv = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(logo);
        dest.writeString(username);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(cv);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<S_edit_Instructor> CREATOR = new Creator<S_edit_Instructor>() {
        @Override
        public S_edit_Instructor createFromParcel(Parcel in) {
            return new S_edit_Instructor(in);
        }

        @Override
        public S_edit_Instructor[] newArray(int size) {
            return new S_edit_Instructor[size];
        }
    };

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
}
