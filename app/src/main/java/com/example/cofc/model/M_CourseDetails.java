package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.time.LocalDateTime;
import java.util.List;

public class M_CourseDetails implements Parcelable {

    private int courseID;
    private String logo;
    private String courseName;
    private String relasedDate;
    //private String releasedDate;
    private String instructorName;
    //private String centerName;
    private String cv;
    private String qA_FollowingWN;
    private boolean online;
    private String address;
    private String phone;
    private Double price;
    private boolean options;
    private Float stars;
    private String description;
    private String requirements;
    private String email;
    private List<Data_of_video> freeVideos;
    private List<Data_of_component> components;
    private String message;
    private boolean isSuccess;
    private List<String> error;
    private LocalDateTime expireDate;

    public M_CourseDetails(int courseID, String logo, String courseName, String relasedDate, String instructorName, String cv,
                           String qA_FollowingWN, boolean online, String address, String phone, Double price, boolean options,
                           Float stars, String description, String requirements, String email, List<Data_of_video> freeVideos,
                           List<Data_of_component> components, String message, boolean isSuccess, List<String> error, LocalDateTime expireDate) {
        this.courseID = courseID;
        this.logo = logo;
        this.courseName = courseName;
        this.relasedDate = relasedDate;
        this.instructorName = instructorName;
        this.cv = cv;
        this.qA_FollowingWN = qA_FollowingWN;
        this.online = online;
        this.address = address;
        this.phone = phone;
        this.price = price;
        this.options = options;
        this.stars = stars;
        this.description = description;
        this.requirements = requirements;
        this.email = email;
        this.freeVideos = freeVideos;
        this.components = components;
        this.message = message;
        this.isSuccess = isSuccess;
        this.error = error;
        this.expireDate = expireDate;
    }


    protected M_CourseDetails(Parcel in) {
        courseID = in.readInt();
        logo = in.readString();
        courseName = in.readString();
        relasedDate = in.readString();
        instructorName = in.readString();
        cv = in.readString();
        qA_FollowingWN = in.readString();
        online = in.readByte() != 0;
        address = in.readString();
        phone = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        options = in.readByte() != 0;
        if (in.readByte() == 0) {
            stars = null;
        } else {
            stars = in.readFloat();
        }
        description = in.readString();
        requirements = in.readString();
        email = in.readString();
        freeVideos = in.createTypedArrayList(Data_of_video.CREATOR);
        components = in.createTypedArrayList(Data_of_component.CREATOR);
        message = in.readString();
        isSuccess = in.readByte() != 0;
        error = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(courseID);
        dest.writeString(logo);
        dest.writeString(courseName);
        dest.writeString(relasedDate);
        dest.writeString(instructorName);
        dest.writeString(cv);
        dest.writeString(qA_FollowingWN);
        dest.writeByte((byte) (online ? 1 : 0));
        dest.writeString(address);
        dest.writeString(phone);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(price);
        }
        dest.writeByte((byte) (options ? 1 : 0));
        if (stars == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(stars);
        }
        dest.writeString(description);
        dest.writeString(requirements);
        dest.writeString(email);
        dest.writeTypedList(freeVideos);
        dest.writeTypedList(components);
        dest.writeString(message);
        dest.writeByte((byte) (isSuccess ? 1 : 0));
        dest.writeStringList(error);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<M_CourseDetails> CREATOR = new Creator<M_CourseDetails>() {
        @Override
        public M_CourseDetails createFromParcel(Parcel in) {
            return new M_CourseDetails(in);
        }

        @Override
        public M_CourseDetails[] newArray(int size) {
            return new M_CourseDetails[size];
        }
    };

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getLogo() {
        return "http://teamcoc-001-site1.btempurl.com/images/"+logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRelasedDate() {
        return relasedDate;
    }

    public void setRelasedDate(String relasedDate) {
        this.relasedDate = relasedDate;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getCv() {
        return "http://teamcoc-001-site1.btempurl.com/images/"+cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getqA_FollowingWN() {
        return "http://teamcoc-001-site1.btempurl.com/images/"+qA_FollowingWN;
    }

    public void setqA_FollowingWN(String qA_FollowingWN) {
        this.qA_FollowingWN = qA_FollowingWN;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isOptions() {
        return options;
    }

    public void setOptions(boolean options) {
        this.options = options;
    }

    public Float getStars() {
        return stars;
    }

    public void setStars(Float stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Data_of_video> getFreeVideos() {
        return freeVideos;
    }

    public void setFreeVideos(List<Data_of_video> freeVideos) {
        this.freeVideos = freeVideos;
    }

    public List<Data_of_component> getComponents() {
        return components;
    }

    public void setComponents(List<Data_of_component> components) {
        this.components = components;
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
