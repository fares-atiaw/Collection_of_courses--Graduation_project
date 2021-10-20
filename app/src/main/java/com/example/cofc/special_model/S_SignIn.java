package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

public class S_SignIn implements Parcelable {

    private String email;
    private String password;

    public S_SignIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

    protected S_SignIn(Parcel in) {
        email = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<S_SignIn> CREATOR = new Creator<S_SignIn>() {
        @Override
        public S_SignIn createFromParcel(Parcel in) {
            return new S_SignIn(in);
        }

        @Override
        public S_SignIn[] newArray(int size) {
            return new S_SignIn[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
