package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class M_MainCategory implements Parcelable, Serializable {

    private int mainCategoryID;
    private String mainCategoryName;
    private String mainCategoryLogo;

    public M_MainCategory(int mainCategoryID, String mainCategoryName, String mainCategoryLogo) {
        this.mainCategoryID = mainCategoryID;
        this.mainCategoryName = mainCategoryName;
        this.mainCategoryLogo = mainCategoryLogo;
    }

    protected M_MainCategory(Parcel in) {
        mainCategoryID = in.readInt();
        mainCategoryName = in.readString();
        mainCategoryLogo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mainCategoryID);
        dest.writeString(mainCategoryName);
        dest.writeString(mainCategoryLogo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<M_MainCategory> CREATOR = new Creator<M_MainCategory>() {
        @Override
        public M_MainCategory createFromParcel(Parcel in) {
            return new M_MainCategory(in);
        }

        @Override
        public M_MainCategory[] newArray(int size) {
            return new M_MainCategory[size];
        }
    };

    public int getMainCategoryID() {
        return mainCategoryID;
    }

    public void setMainCategoryID(int mainCategoryID) {
        this.mainCategoryID = mainCategoryID;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public String getMainCategoryLogo() {
        return "http://teamcoc-001-site1.btempurl.com/images/"+mainCategoryLogo;
    }

    public void setMainCategoryLogo(String mainCategoryLogo) {
        this.mainCategoryLogo = mainCategoryLogo;
    }
}
