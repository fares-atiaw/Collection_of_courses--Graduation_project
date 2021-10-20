package com.example.cofc.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cofc.repository.API_Client;
import com.example.cofc.special_model.R_7_IDs;
import com.example.cofc.special_model.S_SignIn;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class V_SignIn extends AndroidViewModel {

    public MutableLiveData<R_7_IDs> mLive = new MutableLiveData<>();

    public V_SignIn(@NonNull Application application) {
        super(application);
    }

//check from database, then get its id_s from database
    public void post_login(S_SignIn login)
    {Log.v("DataHere1" , "mLive.toString()");
        API_Client.getInstance().postSignIn(login).enqueue(new Callback<R_7_IDs>() {
            @Override
            public void onResponse(@NonNull Call<R_7_IDs> call, Response<R_7_IDs> response) {
                mLive.setValue(response.body());
                Log.v("DataHere" , mLive.toString());
            }
            @Override
            public void onFailure(Call<R_7_IDs> call, Throwable t) {
                t.getMessage();
                Log.v("Heree" , "mLive.getValue().getMessage()");
            }
        });
        //mLive.setValue(respose.body);
    }




}
