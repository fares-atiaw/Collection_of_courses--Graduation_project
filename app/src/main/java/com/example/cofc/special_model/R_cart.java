package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cofc.model.Data_of_purchase;

import java.time.LocalDateTime;
import java.util.List;

public class R_cart {

    private List<Data_of_purchase> lista;
    private String message;
    private boolean isSuccess;
    private List<String> error;
    private LocalDateTime expireDate;

    public R_cart(List<Data_of_purchase> lista, String message, boolean isSuccess, List<String> error, LocalDateTime expireDate) {
        this.lista = lista;
        this.message = message;
        this.isSuccess = isSuccess;
        this.error = error;
        this.expireDate = expireDate;
    }

    public List<Data_of_purchase> getLista() {
        return lista;
    }

    public void setLista(List<Data_of_purchase> lista) {
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
