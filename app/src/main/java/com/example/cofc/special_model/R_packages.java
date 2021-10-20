package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cofc.model.Data_of_package;
import java.time.LocalDateTime;
import java.util.List;

public class R_packages implements Parcelable {

    private List<Data_of_package> lista;
    private String message;
    private boolean isSuccess;
    private List<String> error;
    private LocalDateTime expireDate;

    public R_packages(List<Data_of_package> lista, String message, boolean isSuccess, List<String> error, LocalDateTime expireDate) {
        this.lista = lista;
        this.message = message;
        this.isSuccess = isSuccess;
        this.error = error;
        this.expireDate = expireDate;
    }

    protected R_packages(Parcel in) {
        lista = in.createTypedArrayList(Data_of_package.CREATOR);
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

    public static final Creator<R_packages> CREATOR = new Creator<R_packages>() {
        @Override
        public R_packages createFromParcel(Parcel in) {
            return new R_packages(in);
        }

        @Override
        public R_packages[] newArray(int size) {
            return new R_packages[size];
        }
    };

    public List<Data_of_package> getLista() {
        return lista;
    }

    public void setLista(List<Data_of_package> lista) {
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
