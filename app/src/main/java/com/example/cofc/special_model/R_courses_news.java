package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cofc.model.Data_of_course;
import com.example.cofc.model.Data_of_news;
import java.time.LocalDateTime;
import java.util.List;

public class R_courses_news implements Parcelable {

    private List<Data_of_course> c_list;
    private List<Data_of_news> n_list;
    private String message;
    private boolean isSuccess;
    private List<String> error;
    private LocalDateTime expireDate;

    public R_courses_news(List<Data_of_course> c_list, List<Data_of_news> n_list, String message,
                          boolean isSuccess, List<String> error, LocalDateTime expireDate) {
        this.c_list = c_list;
        this.n_list = n_list;
        this.message = message;
        this.isSuccess = isSuccess;
        this.error = error;
        this.expireDate = expireDate;
    }

    protected R_courses_news(Parcel in) {
        c_list = in.createTypedArrayList(Data_of_course.CREATOR);
        n_list = in.createTypedArrayList(Data_of_news.CREATOR);
        message = in.readString();
        isSuccess = in.readByte() != 0;
        error = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(c_list);
        dest.writeTypedList(n_list);
        dest.writeString(message);
        dest.writeByte((byte) (isSuccess ? 1 : 0));
        dest.writeStringList(error);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<R_courses_news> CREATOR = new Creator<R_courses_news>() {
        @Override
        public R_courses_news createFromParcel(Parcel in) {
            return new R_courses_news(in);
        }

        @Override
        public R_courses_news[] newArray(int size) {
            return new R_courses_news[size];
        }
    };

    public List<Data_of_course> getC_list() {
        return c_list;
    }

    public void setC_list(List<Data_of_course> c_list) {
        this.c_list = c_list;
    }

    public List<Data_of_news> getN_list() {
        return n_list;
    }

    public void setN_list(List<Data_of_news> n_list) {
        this.n_list = n_list;
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
