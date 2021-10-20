package com.example.cofc.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cofc.special_model.S_comment;

public class V_Video_Comment extends AndroidViewModel {

    public MutableLiveData<S_comment> mLive = new MutableLiveData<>();

    public V_Video_Comment(@NonNull Application application) {
        super(application);
    }

    //POST M_comment post , then a Toast for the successful process.
    public void post_comment(S_comment post)
    {
        // the body will be the comment

    }


}
