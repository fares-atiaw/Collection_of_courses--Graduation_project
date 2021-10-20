package com.example.cofc.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cofc.repository.API_Client;
import com.example.cofc.special_model.R_packages;
import com.example.cofc.special_model.S_addToCart;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class V_PriceDetails extends AndroidViewModel {

    public MutableLiveData<R_packages> mLive = new MutableLiveData<>();

    public V_PriceDetails(@NonNull Application application) {
        super(application);
    }

    // GET R_packages packages by course_id , then implement this data in List<Data_of_package> packageList.
    public void get_packages(int course_id)
    {
        API_Client.getInstance().getCoursePackages(course_id).enqueue(new Callback<R_packages>() {
            @Override
            public void onResponse(Call<R_packages> call, Response<R_packages> response) {
                mLive.setValue(response.body());
                Log.v("packages" , mLive.toString());
            }
            @Override
            public void onFailure(Call<R_packages> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    // POST S_addToCart adds , then receive R_IsSuccess and use it in a Toast.
    public void post_packagesToCart(int cartID , S_addToCart addition)
    {


    }

}
