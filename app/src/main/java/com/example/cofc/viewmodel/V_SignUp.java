package com.example.cofc.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.cofc.special_model.R_IsSuccess;
import com.example.cofc.special_model.S_SignUp;

public class V_SignUp extends AndroidViewModel {

    public MutableLiveData<R_IsSuccess> mLive = new MutableLiveData<>();

    public V_SignUp(@NonNull Application application) {
        super(application);
    }

    //Post it to database as S_SignUp class , then get its Is_Success class.
    public void post_signUp(S_SignUp signUp)
    {

        //mLive.setValue(response.body);
    }


}
