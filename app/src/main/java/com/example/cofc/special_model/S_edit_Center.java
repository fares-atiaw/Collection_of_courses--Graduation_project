package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

public class S_edit_Center implements Parcelable {

    //private int centerID;
    private String logo;
    private String username;
    private String phone;
    private String email;
    private String address;

    public S_edit_Center(String logo, String username, String phone, String email, String address) {
        this.logo = logo;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    protected S_edit_Center(Parcel in) {
        logo = in.readString();
        username = in.readString();
        phone = in.readString();
        email = in.readString();
        address = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(logo);
        dest.writeString(username);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(address);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<S_edit_Center> CREATOR = new Creator<S_edit_Center>() {
        @Override
        public S_edit_Center createFromParcel(Parcel in) {
            return new S_edit_Center(in);
        }

        @Override
        public S_edit_Center[] newArray(int size) {
            return new S_edit_Center[size];
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
