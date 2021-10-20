package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;

public class M_SubCategory implements Parcelable {

    private int subCategoryID;
    private String subCategoryName;
    private String subCategoryLogo;

    public M_SubCategory(int subCategoryID, String subCategoryName, String subCategoryLogo) {
        this.subCategoryID = subCategoryID;
        this.subCategoryName = subCategoryName;
        this.subCategoryLogo = subCategoryLogo;
    }

    protected M_SubCategory(Parcel in) {
        subCategoryID = in.readInt();
        subCategoryName = in.readString();
        subCategoryLogo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(subCategoryID);
        dest.writeString(subCategoryName);
        dest.writeString(subCategoryLogo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<M_SubCategory> CREATOR = new Creator<M_SubCategory>() {
        @Override
        public M_SubCategory createFromParcel(Parcel in) {
            return new M_SubCategory(in);
        }

        @Override
        public M_SubCategory[] newArray(int size) {
            return new M_SubCategory[size];
        }
    };

    public int getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(int subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategoryLogo() {
        return "http://teamcoc-001-site1.btempurl.com/images/"+subCategoryLogo;
    }

    public void setSubCategoryLogo(String subCategoryLogo) {
        this.subCategoryLogo = subCategoryLogo;
    }
}
