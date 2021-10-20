package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class S_deleteItem implements Parcelable {

    private int cartID;
    private List<S_addToCart.PackageID> packageIDs;

    public S_deleteItem(int cartID, List<S_addToCart.PackageID> packageIDs) {
        this.cartID = cartID;
        this.packageIDs = packageIDs;
    }

    protected S_deleteItem(Parcel in) {
        cartID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cartID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<S_deleteItem> CREATOR = new Creator<S_deleteItem>() {
        @Override
        public S_deleteItem createFromParcel(Parcel in) {
            return new S_deleteItem(in);
        }

        @Override
        public S_deleteItem[] newArray(int size) {
            return new S_deleteItem[size];
        }
    };

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public List<S_addToCart.PackageID> getPackageIDs() {
        return packageIDs;
    }

    public void setPackageIDs(List<S_addToCart.PackageID> packageIDs) {
        this.packageIDs = packageIDs;
    }
}
