package com.example.cofc.special_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cofc.model.M_CommentRating;

public class S_comment{

    private int studentID;
    private int videoID;
    private M_CommentRating body;

    public S_comment(int studentID, int videoID, M_CommentRating body) {
        this.studentID = studentID;
        this.videoID = videoID;
        this.body = body;
    }


    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getVideoID() {
        return videoID;
    }

    public void setVideoID(int videoID) {
        this.videoID = videoID;
    }

    public M_CommentRating getBody() {
        return body;
    }

    public void setBody(M_CommentRating body) {
        this.body = body;
    }
}
