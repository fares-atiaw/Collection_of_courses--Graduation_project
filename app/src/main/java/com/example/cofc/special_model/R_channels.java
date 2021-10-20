package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cofc.model.Data_of_channels;
import java.time.LocalDateTime;
import java.util.List;

public class R_channels implements Parcelable {

    private List<Data_of_channels> lista;
    private String message;
    private boolean isSuccess;
    private List<String> error;
    private LocalDateTime expireDate;

    public R_channels(List<Data_of_channels> lista, String message,
                      boolean isSuccess, List<String> error, LocalDateTime expireDate) {
        this.lista = lista;
        this.message = message;
        this.isSuccess = isSuccess;
        this.error = error;
        this.expireDate = expireDate;
    }

    protected R_channels(Parcel in) {
        lista = in.createTypedArrayList(Data_of_channels.CREATOR);
        message = in.readString();
        isSuccess = in.readByte() != 0;
        error = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(lista);
        dest.writeString(message);
        dest.writeByte((byte) (isSuccess ? 1 : 0));
        dest.writeStringList(error);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<R_channels> CREATOR = new Creator<R_channels>() {
        @Override
        public R_channels createFromParcel(Parcel in) {
            return new R_channels(in);
        }

        @Override
        public R_channels[] newArray(int size) {
            return new R_channels[size];
        }
    };

    public List<Data_of_channels> getLista() {
        return lista;
    }

    public void setLista(List<Data_of_channels> lista) {
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
