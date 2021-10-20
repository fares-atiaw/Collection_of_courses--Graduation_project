package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Data_of_package implements Parcelable {

    public static class Component{
        private String componentName;

        public Component(String componentName) {
            this.componentName = componentName;
        }

        public String getComponentName() {
            return componentName;
        }

        public void setComponentName(String componentName) {
            this.componentName = componentName;
        }
    }

    private int packageID;
    private int packageNumber;
    private double packageCost;
    private List<Component> components;

    public Data_of_package(int packageID, int packageNumber, double packageCost, List<Component> components) {
        this.packageID = packageID;
        this.packageNumber = packageNumber;
        this.packageCost = packageCost;
        this.components = components;
    }

    protected Data_of_package(Parcel in) {
        packageID = in.readInt();
        packageNumber = in.readInt();
        packageCost = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(packageID);
        dest.writeInt(packageNumber);
        dest.writeDouble(packageCost);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data_of_package> CREATOR = new Creator<Data_of_package>() {
        @Override
        public Data_of_package createFromParcel(Parcel in) {
            return new Data_of_package(in);
        }

        @Override
        public Data_of_package[] newArray(int size) {
            return new Data_of_package[size];
        }
    };

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public int getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(int packageNumber) {
        this.packageNumber = packageNumber;
    }

    public double getPackageCost() {
        return packageCost;
    }

    public void setPackageCost(double packageCost) {
        this.packageCost = packageCost;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
