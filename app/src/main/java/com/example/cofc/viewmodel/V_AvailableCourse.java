package com.example.cofc.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.cofc.special_model.R_availableCourses;

public class V_AvailableCourse extends AndroidViewModel {

    public MutableLiveData<R_availableCourses> mLive = new MutableLiveData<>();

    public V_AvailableCourse(@NonNull Application application) {
        super(application);
    }

    //GET R_availableCourses courses which includes List<Data_of_course> courses
    public void get_availableCourses(int currentCourses_ID)
    {

        //mLive.setValue(response.body());
    }

}
