package com.example.cofc.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cofc.model.Data_of_course;
import com.example.cofc.model.Data_of_news;
import com.example.cofc.repository.API_Client;
import com.example.cofc.special_model.R_courses_news;
import com.example.cofc.special_model.S_getCourses;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class V_OfflineCourses extends AndroidViewModel {

    public MutableLiveData<R_courses_news> mLive = new MutableLiveData<>();

    public V_OfflineCourses(@NonNull Application application) {
        super(application);
    }

    //GET R_courses_news total which includes List<Data_of_course> courses & List<Data_of_news> news (offline).
    public void get_courses_news(S_getCourses coursesType)
    {
        API_Client.getInstance().getCoursesNews(coursesType).enqueue(new Callback<R_courses_news>() {
            @Override
            public void onResponse(Call<R_courses_news> call, Response<R_courses_news> response) {
                mLive.setValue(response.body());
            }
            @Override
            public void onFailure(Call<R_courses_news> call, Throwable t) {
                t.getMessage();
            }
        });

    }


}

/* List<Data_of_course> arrayList = new ArrayList<>();
        arrayList.add(new Data_of_course(55,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your own business","yesterday","Prof. Shrieff Moharam",null,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png",false,"12 Baker Street",(double)80,true,(float)4));
        arrayList.add(new Data_of_course(55,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your own business","yesterday","Prof. Shrieff Moharam",null,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png",false,"12 Baker Street",(double)80,true,(float)4));
        arrayList.add(new Data_of_course(55,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your own business","yesterday","Prof. Shrieff Moharam",null,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png",false,"12 Baker Street",(double)80,true,(float)4));
        arrayList.add(new Data_of_course(55,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your own business","yesterday","Prof. Shrieff Moharam",null,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png",false,"12 Baker Street",(double)80,true,(float)4));
        arrayList.add(new Data_of_course(55,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your own business","yesterday","Prof. Shrieff Moharam",null,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png",false,"12 Baker Street",(double)80,true,(float)4));
        arrayList.add(new Data_of_course(55,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your own business","yesterday","Prof. Shrieff Moharam",null,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png",false,"12 Baker Street",(double)80,true,(float)4));
        arrayList.add(new Data_of_course(55,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your own business","yesterday","Prof. Shrieff Moharam",null,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png",false,"12 Baker Street",(double)80,true,(float)4));
        arrayList.add(new Data_of_course(55,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your own business","yesterday","Prof. Shrieff Moharam",null,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png",false,"12 Baker Street",(double)80,true,(float)4));
        arrayList.add(new Data_of_course(55,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your own business","yesterday","Prof. Shrieff Moharam",null,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png",false,"12 Baker Street",(double)80,true,(float)4));
        arrayList.add(new Data_of_course(55,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your own business","yesterday","Prof. Shrieff Moharam",null,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png",false,"12 Baker Street",(double)80,true,(float)4));
        arrayList.add(new Data_of_course(55,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your own business","yesterday","Prof. Shrieff Moharam",null,"https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png",false,"12 Baker Street",(double)80,true,(float)4));
        ArrayList<Data_of_news> arrayList2 = new ArrayList<>();
        arrayList2.add(new Data_of_news(21,"How to be a Project Manager","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Mr. Shrieff Moharam",null,"Today","The course is free for 1 week , starts 12/2/2021 and ends 19/2/2021."));
        arrayList2.add(new Data_of_news(21,"AI Concept","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Mr. Fares Hassan","Today",null,"The installment feature has been added. The course now has 4 packages."));
        arrayList2.add(new Data_of_news(21,"Machine Learning from zero to hero","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Mr. Mohamed Maamoon",null,"Today","There is an offer 90% discount on the course , starts 2/2/2021 and ends 8/2/2021."));
        arrayList2.add(new Data_of_news(21,"Cyber Security","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Mr. Maged Hossam","Today",null,"There is an offer 70% discount on the course , starts 20/1/2021 and ends 1/2/2021."));
        arrayList2.add(new Data_of_news(25,"Fundamentals of Data Science","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Mr. Amr Medhat","Today",null,"The course is free for 1 week , starts 1/1/2021 and ends 20/1/2021."));

mLive.setValue(new R_courses_news(arrayList , arrayList2 , null , true , null , null));*/