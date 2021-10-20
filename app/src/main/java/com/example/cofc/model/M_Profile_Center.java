package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDateTime;
import java.util.List;

public class M_Profile_Center implements Parcelable {

    private String logo;
    private String userName;
    private String phone;
    private String email;
    private String address;
    private List<Data_of_course> myCourses;
    private String message;
    private boolean isSuccess;
    private List<String> error;
    private LocalDateTime expireDate;

    public M_Profile_Center(String logo, String userName, String phone, String email, String address, List<Data_of_course> myCourses,
                            String message, boolean isSuccess, List<String> error, LocalDateTime expireDate) {
        this.logo = logo;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.myCourses = myCourses;
        this.message = message;
        this.isSuccess = isSuccess;
        this.error = error;
        this.expireDate = expireDate;
    }

    protected M_Profile_Center(Parcel in) {
        logo = in.readString();
        userName = in.readString();
        phone = in.readString();
        email = in.readString();
        address = in.readString();
        myCourses = in.createTypedArrayList(Data_of_course.CREATOR);
        message = in.readString();
        isSuccess = in.readByte() != 0;
        error = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(logo);
        dest.writeString(userName);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeTypedList(myCourses);
        dest.writeString(message);
        dest.writeByte((byte) (isSuccess ? 1 : 0));
        dest.writeStringList(error);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<M_Profile_Center> CREATOR = new Creator<M_Profile_Center>() {
        @Override
        public M_Profile_Center createFromParcel(Parcel in) {
            return new M_Profile_Center(in);
        }

        @Override
        public M_Profile_Center[] newArray(int size) {
            return new M_Profile_Center[size];
        }
    };

    public String getLogo() {
        return "http://teamcoc-001-site1.btempurl.com/images/"+logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<Data_of_course> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<Data_of_course> myCourses) {
        this.myCourses = myCourses;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }
}
