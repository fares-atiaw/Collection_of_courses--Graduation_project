package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDateTime;
import java.util.List;

public class M_Profile_Student implements Parcelable {

    private String logo;
    private String userName;
    private String phone;
    private String email;
    private List<M_SubCategory> favorites;
    private List<Data_of_news> notifications;
    private List<Data_of_course> watchLater;
    private String message;
    private boolean isSuccess;
    private List<String> error;
    private LocalDateTime expireDate;

    public M_Profile_Student(String logo, String userName, String phone, String email, List<M_SubCategory> favorites,
                             List<Data_of_news> notifications, List<Data_of_course> watchLater, String message, boolean isSuccess,
                             List<String> error, LocalDateTime expireDate) {
        this.logo = logo;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.favorites = favorites;
        this.notifications = notifications;
        this.watchLater = watchLater;
        this.message = message;
        this.isSuccess = isSuccess;
        this.error = error;
        this.expireDate = expireDate;
    }

    protected M_Profile_Student(Parcel in) {
        logo = in.readString();
        userName = in.readString();
        phone = in.readString();
        email = in.readString();
        favorites = in.createTypedArrayList(M_SubCategory.CREATOR);
        notifications = in.createTypedArrayList(Data_of_news.CREATOR);
        watchLater = in.createTypedArrayList(Data_of_course.CREATOR);
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
        dest.writeTypedList(favorites);
        dest.writeTypedList(notifications);
        dest.writeTypedList(watchLater);
        dest.writeString(message);
        dest.writeByte((byte) (isSuccess ? 1 : 0));
        dest.writeStringList(error);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<M_Profile_Student> CREATOR = new Creator<M_Profile_Student>() {
        @Override
        public M_Profile_Student createFromParcel(Parcel in) {
            return new M_Profile_Student(in);
        }

        @Override
        public M_Profile_Student[] newArray(int size) {
            return new M_Profile_Student[size];
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

    public List<M_SubCategory> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<M_SubCategory> favorites) {
        this.favorites = favorites;
    }

    public List<Data_of_news> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Data_of_news> notifications) {
        this.notifications = notifications;
    }

    public List<Data_of_course> getWatchLater() {
        return watchLater;
    }

    public void setWatchLater(List<Data_of_course> watchLater) {
        this.watchLater = watchLater;
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
