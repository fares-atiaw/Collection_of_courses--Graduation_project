package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

public class S_Add_favorites implements Parcelable {

    private int subCategoryID;
    private int favoriteID;

    public S_Add_favorites(int subCategoryID, int favoriteID) {
        this.subCategoryID = subCategoryID;
        this.favoriteID = favoriteID;
    }

    protected S_Add_favorites(Parcel in) {
        subCategoryID = in.readInt();
        favoriteID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(subCategoryID);
        dest.writeInt(favoriteID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<S_Add_favorites> CREATOR = new Creator<S_Add_favorites>() {
        @Override
        public S_Add_favorites createFromParcel(Parcel in) {
            return new S_Add_favorites(in);
        }

        @Override
        public S_Add_favorites[] newArray(int size) {
            return new S_Add_favorites[size];
        }
    };

    public int getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(int subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public int getFavoriteID() {
        return favoriteID;
    }

    public void setFavoriteID(int favoriteID) {
        this.favoriteID = favoriteID;
    }
}
