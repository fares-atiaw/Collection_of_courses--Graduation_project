package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cofc.model.Data_of_news;
import com.example.cofc.model.M_MainCategory;
import java.time.LocalDateTime;
import java.util.List;

public class R_mainCategories implements Parcelable {

    private List<M_MainCategory> lista;
    private List<Data_of_news> n_list;
    private String message;
    private boolean isSuccess;
    private List<String> error;
    private LocalDateTime expireDate;

    public R_mainCategories() {
    }

    public R_mainCategories(List<M_MainCategory> lista, List<Data_of_news> n_list, String message,
                            boolean isSuccess, List<String> error, LocalDateTime expireDate) {
        this.lista = lista;
        this.n_list = n_list;
        this.message = message;
        this.isSuccess = isSuccess;
        this.error = error;
        this.expireDate = expireDate;
    }

    protected R_mainCategories(Parcel in) {
        lista = in.createTypedArrayList(M_MainCategory.CREATOR);
        n_list = in.createTypedArrayList(Data_of_news.CREATOR);
        message = in.readString();
        isSuccess = in.readByte() != 0;
        error = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(lista);
        dest.writeTypedList(n_list);
        dest.writeString(message);
        dest.writeByte((byte) (isSuccess ? 1 : 0));
        dest.writeStringList(error);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<R_mainCategories> CREATOR = new Creator<R_mainCategories>() {
        @Override
        public R_mainCategories createFromParcel(Parcel in) {
            return new R_mainCategories(in);
        }

        @Override
        public R_mainCategories[] newArray(int size) {
            return new R_mainCategories[size];
        }
    };

    public List<M_MainCategory> getLista() {
        return lista;
    }

    public void setLista(List<M_MainCategory> lista) {
        this.lista = lista;
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
