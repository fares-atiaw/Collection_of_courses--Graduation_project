package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

public class S_edit_Student implements Parcelable {

    //private int studentID;
    private String logo;
    private String username;
    private String phone;
    private String email;

    public S_edit_Student(String logo, String username, String phone, String email) {
        this.logo = logo;
        this.username = username;
        this.phone = phone;
        this.email = email;
    }

    protected S_edit_Student(Parcel in) {
        logo = in.readString();
        username = in.readString();
        phone = in.readString();
        email = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(logo);
        dest.writeString(username);
        dest.writeString(phone);
        dest.writeString(email);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<S_edit_Student> CREATOR = new Creator<S_edit_Student>() {
        @Override
        public S_edit_Student createFromParcel(Parcel in) {
            return new S_edit_Student(in);
        }

        @Override
        public S_edit_Student[] newArray(int size) {
            return new S_edit_Student[size];
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
}
