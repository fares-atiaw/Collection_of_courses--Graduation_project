package com.example.cofc.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.cofc.model.M_CourseDetails;
import com.example.cofc.repository.API_Client;
import com.example.cofc.special_model.S_Add_watchLater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class V_CourseDetails extends AndroidViewModel {

    public MutableLiveData<M_CourseDetails> mLive = new MutableLiveData<>();

    public V_CourseDetails(@NonNull Application application) {
        super(application);
    }

    // GET M_CourseDetails details by course_id from database
    public void get_courseDetails(int courseID)
    {
        API_Client.getInstance().getCourseDetails(courseID).enqueue(new Callback<M_CourseDetails>() {
            @Override
            public void onResponse(Call<M_CourseDetails> call, Response<M_CourseDetails> response) {
                mLive.setValue(response.body());
            }
            @Override
            public void onFailure(Call<M_CourseDetails> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    // Add to watch later : Post with sp.getInt(ID_W6,-1) and data.getCourseID()
    public void post_watchLater(S_Add_watchLater addition)
    {

    }


}
