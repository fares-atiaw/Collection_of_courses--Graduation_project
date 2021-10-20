package com.example.cofc.model;

import android.os.Parcel;
import android.os.Parcelable;

public class M_CommentRating {

    private String missing_Explain;
    private String missing_Answer;
    private float understand_Rate;

    public M_CommentRating(String missing_Explain, String missing_Answer, float understand_Rate) {
        this.missing_Explain = missing_Explain;
        this.missing_Answer = missing_Answer;
        this.understand_Rate = understand_Rate;
    }

    public M_CommentRating() {

    }

    public String getMissing_Explain() {
        return missing_Explain;
    }

    public void setMissing_Explain(String missing_Explain) {
        this.missing_Explain = missing_Explain;
    }

    public String getMissing_Answer() {
        return missing_Answer;
    }

    public void setMissing_Answer(String missing_Answer) {
        this.missing_Answer = missing_Answer;
    }

    public float getUnderstand_Rate() {
        return understand_Rate;
    }

    public void setUnderstand_Rate(float understand_Rate) {
        this.understand_Rate = understand_Rate;
    }
}
