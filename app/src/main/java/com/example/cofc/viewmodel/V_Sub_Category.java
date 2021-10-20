package com.example.cofc.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cofc.model.Data_of_news;
import com.example.cofc.model.M_SubCategory;
import com.example.cofc.repository.API_Client;
import com.example.cofc.special_model.R_mainCategories;
import com.example.cofc.special_model.R_subCategories;
import com.example.cofc.special_model.S_Add_favorites;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class V_Sub_Category extends AndroidViewModel {

    public MutableLiveData<R_subCategories> mLive = new MutableLiveData<>();

    public V_Sub_Category(@NonNull Application application) {
        super(application);
    }

    // GET R_subCategories r which includes List<M_SubCategory> categories & List<Data_of_news> news
    public void get_categories_news(int id)
    {
        API_Client.getInstance().getSubCategories(id).enqueue(new Callback<R_subCategories>() {
            @Override
            public void onResponse(Call<R_subCategories> call, Response<R_subCategories> response) {
                mLive.setValue(response.body());
            }
            @Override
            public void onFailure(Call<R_subCategories> call, Throwable t) {

            }
        });

    }

    //Add to favorites : Post with sp.getInt(ID_F4,-1) and data.getCategoryID()
    public void post_favorite(S_Add_favorites addition)
    {

    }



}




//mLive.setValue(new R_subCategories(arrayList , arrayList2 , null , true , null , null));
/* ArrayList<M_SubCategory> arrayList = new ArrayList<>();
        arrayList.add(new M_SubCategory(4 , "Web1" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Android1" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Web2" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Android2" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Art" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Machine Learning" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Seamstress" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Engineering" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Desktop Application" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Application" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Cooking" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Craft" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Art" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Fitness & Gym" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Seamstress" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Engineering" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Desktop Application" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        ArrayList<Data_of_news> arrayList2 = new ArrayList<>();
        arrayList2.add(new Data_of_news(21,"How to be a Project Manager","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Mr. Shrieff Moharam",null,"Today","The course is free for 1 week , starts 12/2/2021 and ends 19/2/2021."));
        arrayList2.add(new Data_of_news(21,"AI Concept","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Mr. Fares Hassan","Today",null,"The installment feature has been added. The course now has 4 packages."));
        arrayList2.add(new Data_of_news(21,"Machine Learning from zero to hero","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Mr. Mohamed Maamoon",null,"Today","There is an offer 90% discount on the course , starts 2/2/2021 and ends 8/2/2021."));
        arrayList2.add(new Data_of_news(21,"Cyber Security","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Mr. Maged Hossam","Today",null,"There is an offer 70% discount on the course , starts 20/1/2021 and ends 1/2/2021."));
        arrayList2.add(new Data_of_news(25,"Fundamentals of Data Science","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Mr. Amr Medhat","Today",null,"The course is free for 1 week , starts 1/1/2021 and ends 20/1/2021."));
*/