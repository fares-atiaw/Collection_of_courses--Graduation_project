package com.example.cofc.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.cofc.model.M_Profile_Center;
import com.example.cofc.model.M_Profile_Instructor;
import com.example.cofc.model.M_Profile_Student;
import com.example.cofc.repository.API_Client;
import com.example.cofc.special_model.R_IsSuccess;
import com.example.cofc.special_model.S_deleteFavorite;
import com.example.cofc.special_model.S_deleteWatchLater;
import com.example.cofc.special_model.S_edit_Center;
import com.example.cofc.special_model.S_edit_Instructor;
import com.example.cofc.special_model.S_edit_Student;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class V_MainProfile extends AndroidViewModel {

    public MutableLiveData<M_Profile_Student> mLive_student = new MutableLiveData<>();
    public MutableLiveData<M_Profile_Instructor> mLive_instructor = new MutableLiveData<>();
    public MutableLiveData<M_Profile_Center> mLive_center = new MutableLiveData<>();
    public MutableLiveData<R_IsSuccess> mLive_success = new MutableLiveData<>();

    public V_MainProfile(@NonNull Application application) {
        super(application);
    }

    // GET by sp.getInt(ID_I2 , -1) the M_Profile_Instructor profile_I.
    public void get_Profile_Instructor(int instructorID)
    {
        API_Client.getInstance().getProfileInstructor(instructorID).enqueue(new Callback<M_Profile_Instructor>() {
            @Override
            public void onResponse(Call<M_Profile_Instructor> call, Response<M_Profile_Instructor> response) {
                Log.v("ggwpggwp" , "gsgrggggggggggga");
                mLive_instructor.setValue(response.body());
                Log.v("ggwp" , "gsgrggggggggggga");
            }
            @Override
            public void onFailure(Call<M_Profile_Instructor> call, Throwable t) {
                t.getMessage();
            }
        });
    }
    // GET by sp.getInt(ID_C3 , -1) the M_Profile_Center profile_C.
    public void get_Profile_Center(int centerID)
    {
        API_Client.getInstance().getProfileCenter(centerID).enqueue(new Callback<M_Profile_Center>() {
            @Override
            public void onResponse(Call<M_Profile_Center> call, Response<M_Profile_Center> response) {
                mLive_center.setValue(response.body());
            }
            @Override
            public void onFailure(Call<M_Profile_Center> call, Throwable t) {
                t.getMessage();
            }
        });
    }
    // GET by sp.getInt(ID_S1 , -1) the M_Profile_Student profile_S.
    public void get_Profile_Student(int studentID)
    {
        API_Client.getInstance().getProfileStudent(studentID).enqueue(new Callback<M_Profile_Student>() {
            @Override
            public void onResponse(Call<M_Profile_Student> call, Response<M_Profile_Student> response) {
                Log.v("ok3" , "String.valueOf(call.isCanceled())");
                mLive_student.setValue(response.body());
                Log.v("ggg" , response.body().getEmail());
                Log.v("gggg" , "response.body().getEmail()");
            }
            @Override
            public void onFailure(Call<M_Profile_Student> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    // PATCH S_edit_Student edit_S
    public void patch_edit_S (int studentID , S_edit_Student edit_S)
    {
        //API_Client.getInstance().patchStudent()
        //mLive_success.setValue(response.body);
        Toast.makeText(getApplication(), "Edit saved successfully", Toast.LENGTH_SHORT).show();
    }
    // PATCH S_edit_Instructor edit_I
    public void patch_edit_I (int instructorID , S_edit_Instructor edit_I)
    {

        //mLive_success.setValue(response.body);
        Toast.makeText(getApplication(), "Edit saved successfully", Toast.LENGTH_SHORT).show();
    }
    // PATCH S_edit_Center edit_C
    public void patch_edit_C (int centerID , S_edit_Center edit_C)
    {
        
        //mLive_success.setValue(response.body);
        Toast.makeText(getApplication(), "Edit saved successfully", Toast.LENGTH_SHORT).show();
    }

    // POST S_deleteFavorite item by sp.getInt(ID_F4 , -1) & category_ID , then Toast exit profile to be successful
    public void post_deletedFavorite(S_deleteFavorite favorite)
    {


        //mLive_success.setValue(response.body);
        Toast.makeText(getApplication(), "Deleted successfully", Toast.LENGTH_SHORT).show();
    }
    // POST S_deleteWatchLater item by sp.getInt(ID_W6 , -1) & data.getCourseID() , then Toast exit profile to be successful
    public void post_deletedWatchLater(S_deleteWatchLater watchLater)
    {


        //mLive_success.setValue(response.body);
        Toast.makeText(getApplication(), "Deleted successfully", Toast.LENGTH_SHORT).show();
    }


}
