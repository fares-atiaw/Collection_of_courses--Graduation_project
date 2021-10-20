package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;
import com.example.cofc.model.Data_of_availableCourses;
import java.time.LocalDateTime;
import java.util.List;

public class R_availableCourses implements Parcelable {

    private List<Data_of_availableCourses> lista;
    private String message;
    private boolean isSuccess;
    private LocalDateTime expireDate;

    public R_availableCourses(List<Data_of_availableCourses> lista, String message, boolean isSuccess, LocalDateTime expireDate) {
        this.lista = lista;
        this.message = message;
        this.isSuccess = isSuccess;
        this.expireDate = expireDate;
    }

    protected R_availableCourses(Parcel in) {
        lista = in.createTypedArrayList(Data_of_availableCourses.CREATOR);
        message = in.readString();
        isSuccess = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(lista);
        dest.writeString(message);
        dest.writeByte((byte) (isSuccess ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<R_availableCourses> CREATOR = new Creator<R_availableCourses>() {
        @Override
        public R_availableCourses createFromParcel(Parcel in) {
            return new R_availableCourses(in);
        }

        @Override
        public R_availableCourses[] newArray(int size) {
            return new R_availableCourses[size];
        }
    };

    public List<Data_of_availableCourses> getLista() {
        return lista;
    }

    public void setLista(List<Data_of_availableCourses> lista) {
        this.lista = lista;
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

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }
}