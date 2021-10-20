package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

public class S_deleteFavorite implements Parcelable {

    private int favoriteID;
    private int subCategoryID;

    public S_deleteFavorite(int favoriteID, int subCategoryID) {
        this.favoriteID = favoriteID;
        this.subCategoryID = subCategoryID;
    }

    protected S_deleteFavorite(Parcel in) {
        favoriteID = in.readInt();
        subCategoryID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(favoriteID);
        dest.writeInt(subCategoryID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<S_deleteFavorite> CREATOR = new Creator<S_deleteFavorite>() {
        @Override
        public S_deleteFavorite createFromParcel(Parcel in) {
            return new S_deleteFavorite(in);
        }

        @Override
        public S_deleteFavorite[] newArray(int size) {
            return new S_deleteFavorite[size];
        }
    };

    public int getFavoriteID() {
        return favoriteID;
    }

    public void setFavoriteID(int favoriteID) {
        this.favoriteID = favoriteID;
    }

    public int getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(int subCategoryID) {
        this.subCategoryID = subCategoryID;
    }
}
