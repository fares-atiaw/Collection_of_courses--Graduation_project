package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class S_addToCart implements Parcelable {

    public static class PackageID{
        private int packageID;

        public PackageID(int packageID) {
            this.packageID = packageID;
        }

        public int getPackageID() {
            return packageID;
        }
        public void setPackageID(int packageID) {
            this.packageID = packageID;
        }
    }

    //private int cartID;
    private List<PackageID> packageIDs;

    public S_addToCart(List<PackageID> packageIDs) {
        this.packageIDs = packageIDs;
    }

    protected S_addToCart(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<S_addToCart> CREATOR = new Creator<S_addToCart>() {
        @Override
        public S_addToCart createFromParcel(Parcel in) {
            return new S_addToCart(in);
        }

        @Override
        public S_addToCart[] newArray(int size) {
            return new S_addToCart[size];
        }
    };

    public List<PackageID> getPackageIDs() {
        return packageIDs;
    }

    public void setPackageIDs(List<PackageID> packageIDs) {
        this.packageIDs = packageIDs;
    }
}
