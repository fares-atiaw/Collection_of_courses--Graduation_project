package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class S_BUY_fromCart implements Parcelable {

    //private int currentCoursesID;
    //private int cartID;
    private List<S_addToCart.PackageID> packageIDs;

    public S_BUY_fromCart(List<S_addToCart.PackageID> packageIDs) {
        this.packageIDs = packageIDs;
    }

    protected S_BUY_fromCart(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<S_BUY_fromCart> CREATOR = new Creator<S_BUY_fromCart>() {
        @Override
        public S_BUY_fromCart createFromParcel(Parcel in) {
            return new S_BUY_fromCart(in);
        }

        @Override
        public S_BUY_fromCart[] newArray(int size) {
            return new S_BUY_fromCart[size];
        }
    };

    public List<S_addToCart.PackageID> getPackageIDs() {
        return packageIDs;
    }

    public void setPackageIDs(List<S_addToCart.PackageID> packageIDs) {
        this.packageIDs = packageIDs;
    }
}
