package com.example.cofc.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cofc.model.Data_of_channels;
import com.example.cofc.repository.API_Client;
import com.example.cofc.special_model.R_channels;
import com.example.cofc.ui.activity.Helpful_Channels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class V_HelpFul_Channels extends AndroidViewModel {

    public MutableLiveData<R_channels> mLive = new MutableLiveData<>();

    public V_HelpFul_Channels(@NonNull Application application) {
        super(application);
    }

    public void get_ChannelsCourses(int subCategoryID)
    {
        API_Client.getInstance().getHelpfulChannels(subCategoryID).enqueue(new Callback<R_channels>() {
            @Override
            public void onResponse(Call<R_channels> call, Response<R_channels> response) {
                mLive.setValue(response.body());
            }
            @Override
            public void onFailure(Call<R_channels> call, Throwable t) {
                t.getMessage();
            }
        });
    }


}

/* List<Data_of_channels> lista = new ArrayList<>();
        lista.add(new Data_of_channels(1 , "https://yt3.ggpht.com/a/AGF-l793hIy7K4gl92M2nX4h6TJMPMXoMMMM6UF3LA=s900-c-k-c0xffffffff-no-rj-mo" , "Web bel3rabi" , "Elzero Web School \n" , "https://www.youtube.com/user/OsamaElzero"));
        lista.add(new Data_of_channels(1 , "https://3.bp.blogspot.com/-rjnqq--niNY/WKLr-WC1EYI/AAAAAAAAAsc/iqJH0z-3c4kJKD-wAyMa1aj_-JbWp9nfgCLcB/s1600/ec.jpg" , "Web bel3rabi" , "Elzero Web School \n" , "https://www.youtube.com/user/OsamaElzero"));
        lista.add(new Data_of_channels(1 , "https://yt3.ggpht.com/a/AGF-l793hIy7K4gl92M2nX4h6TJMPMXoMMMM6UF3LA=s900-c-k-c0xffffffff-no-rj-mo" , "Web bel3rabi" , "Elzero Web School \n" , "https://www.youtube.com/user/OsamaElzero"));
        lista.add(new Data_of_channels(1 , "https://3.bp.blogspot.com/-rjnqq--niNY/WKLr-WC1EYI/AAAAAAAAAsc/iqJH0z-3c4kJKD-wAyMa1aj_-JbWp9nfgCLcB/s1600/ec.jpg" , "Web bel3rabi" , "Elzero Web School \n" , "https://www.youtube.com/user/OsamaElzero"));
        lista.add(new Data_of_channels(1 , "https://yt3.ggpht.com/a/AGF-l793hIy7K4gl92M2nX4h6TJMPMXoMMMM6UF3LA=s900-c-k-c0xffffffff-no-rj-mo" , "Web bel3rabi" , "Elzero Web School \n" , "https://www.youtube.com/user/OsamaElzero"));
        lista.add(new Data_of_channels(1 , "https://3.bp.blogspot.com/-rjnqq--niNY/WKLr-WC1EYI/AAAAAAAAAsc/iqJH0z-3c4kJKD-wAyMa1aj_-JbWp9nfgCLcB/s1600/ec.jpg" , "Web bel3rabi" , "Elzero Web School \n" , "https://www.youtube.com/user/OsamaElzero"));
        lista.add(new Data_of_channels(1 , "https://yt3.ggpht.com/a/AGF-l793hIy7K4gl92M2nX4h6TJMPMXoMMMM6UF3LA=s900-c-k-c0xffffffff-no-rj-mo" , "Web bel3rabi" , "Elzero Web School \n" , "https://www.youtube.com/user/OsamaElzero"));
        lista.add(new Data_of_channels(1 , "https://3.bp.blogspot.com/-rjnqq--niNY/WKLr-WC1EYI/AAAAAAAAAsc/iqJH0z-3c4kJKD-wAyMa1aj_-JbWp9nfgCLcB/s1600/ec.jpg" , "Web bel3rabi" , "Elzero Web School \n" , "https://www.youtube.com/user/OsamaElzero"));
        lista.add(new Data_of_channels(1 , "https://yt3.ggpht.com/a/AGF-l793hIy7K4gl92M2nX4h6TJMPMXoMMMM6UF3LA=s900-c-k-c0xffffffff-no-rj-mo" , "Web bel3rabi" , "Elzero Web School \n" , "https://www.youtube.com/user/OsamaElzero"));
        lista.add(new Data_of_channels(1 , "https://3.bp.blogspot.com/-rjnqq--niNY/WKLr-WC1EYI/AAAAAAAAAsc/iqJH0z-3c4kJKD-wAyMa1aj_-JbWp9nfgCLcB/s1600/ec.jpg" , "Web bel3rabi" , "Elzero Web School \n" , "https://www.youtube.com/user/OsamaElzero"));

        mLive.setValue(new R_channels(lista , null , true , null , null));*/