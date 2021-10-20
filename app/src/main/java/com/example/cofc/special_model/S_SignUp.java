package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

public class S_SignUp implements Parcelable {

    private String userName;
    private String email;
    private String password;
    private String confirmPassword;

    public S_SignUp(String userName, String email, String password, String confirmPassword) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    protected S_SignUp(Parcel in) {
        userName = in.readString();
        email = in.readString();
        password = in.readString();
        confirmPassword = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(confirmPassword);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<S_SignUp> CREATOR = new Creator<S_SignUp>() {
        @Override
        public S_SignUp createFromParcel(Parcel in) {
            return new S_SignUp(in);
        }

        @Override
        public S_SignUp[] newArray(int size) {
            return new S_SignUp[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
