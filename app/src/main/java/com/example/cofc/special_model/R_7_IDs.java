package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDateTime;
import java.util.List;

public class R_7_IDs {

    private int studentID;
    private int instructorID;
    private int centerID;
    private int favoriteID;
    private int currentCoursesID;
    private int watchLaterID;
    private int cartID;
    private String message;
    private boolean isSuccess;
    private boolean admin;
    private List<String> error;
    private LocalDateTime expireDate;

    public R_7_IDs(int studentID, int instructorID, int centerID, int favoriteID, int currentCoursesID, int watchLaterID, int cartID,
                   String message, boolean isSuccess, boolean admin, List<String> error, LocalDateTime expireDate) {
        this.studentID = studentID;
        this.instructorID = instructorID;
        this.centerID = centerID;
        this.favoriteID = favoriteID;
        this.currentCoursesID = currentCoursesID;
        this.watchLaterID = watchLaterID;
        this.cartID = cartID;
        this.message = message;
        this.isSuccess = isSuccess;
        this.admin = admin;
        this.error = error;
        this.expireDate = expireDate;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public int getCenterID() {
        return centerID;
    }

    public void setCenterID(int centerID) {
        this.centerID = centerID;
    }

    public int getFavoriteID() {
        return favoriteID;
    }

    public void setFavoriteID(int favoriteID) {
        this.favoriteID = favoriteID;
    }

    public int getCurrentCoursesID() {
        return currentCoursesID;
    }

    public void setCurrentCoursesID(int currentCoursesID) {
        this.currentCoursesID = currentCoursesID;
    }

    public int getWatchLaterID() {
        return watchLaterID;
    }

    public void setWatchLaterID(int watchLaterID) {
        this.watchLaterID = watchLaterID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
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
