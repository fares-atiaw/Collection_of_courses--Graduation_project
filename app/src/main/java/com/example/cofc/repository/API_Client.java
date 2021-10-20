package com.example.cofc.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.cofc.model.M_CourseDetails;
import com.example.cofc.model.M_Profile_Center;
import com.example.cofc.model.M_Profile_Instructor;
import com.example.cofc.model.M_Profile_Student;
import com.example.cofc.special_model.R_7_IDs;
import com.example.cofc.special_model.R_IsSuccess;
import com.example.cofc.special_model.R_channels;
import com.example.cofc.special_model.R_courses_news;
import com.example.cofc.special_model.R_mainCategories;
import com.example.cofc.special_model.R_packages;
import com.example.cofc.special_model.R_subCategories;
import com.example.cofc.special_model.S_SignIn;
import com.example.cofc.special_model.S_edit_Student;
import com.example.cofc.special_model.S_getCourses;
import com.example.cofc.ui.activity.Helpful_Channels;
import com.example.cofc.ui.activity.MainProfile;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public class API_Client {

    private static final String Base_URL = "http://teamcoc-001-site1.btempurl.com/";
    private final API_Interface api_interface;
    private static API_Client Instance;

    public API_Client() {   //Build the retrofit and fill the interface that has the request type with it

        Retrofit retro = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api_interface = retro.create(API_Interface.class);
    }

    public static API_Client getInstance()  // Object has ready variables
    {
        if(Instance == null) {
            Instance = new API_Client();
        }
        return Instance;
    }

    public Call<R_7_IDs> postSignIn(S_SignIn signIn)   //Method of requesting
    {
        return api_interface.postSignIn(signIn);    //Call-->
    }
    public Call<R_mainCategories> getMainCategories()   //Method of requesting  Done
    {
        return api_interface.getMainCategories();    //Call-->
    }
    public Call<R_subCategories> getSubCategories(int mainCategoryID)   //Method of requesting  Done
    {
        return api_interface.getSubCategories(mainCategoryID);    //Call-->
    }
    public Call<R_courses_news> getCoursesNews(S_getCourses coursesType)    //Method of requesting  Done
    {
        return api_interface.getCoursesNews(coursesType.getSubCategoryID() , coursesType.getChoice());
    }
    public Call<R_channels> getHelpfulChannels(int subCategoryID)     //Method of requesting    Done
    {
        return api_interface.getHelpfulChannels(subCategoryID);
    }
    public Call<M_CourseDetails> getCourseDetails(int courseID)   //Method of requesting    Done
    {
        return api_interface.getCourseDetails(courseID);    //Call-->
    }
    public Call<R_packages> getCoursePackages(int courseID) //Method of requesting  Done
    {
        return api_interface.getCoursePackages(courseID);    //Call-->
    }

    public Call<M_Profile_Student> getProfileStudent(int studentID)
    {
        return api_interface.getProfileStudent(studentID);
    }
    public Call<M_Profile_Instructor> getProfileInstructor(int instructorID)
    {
        return api_interface.getProfileInstructor(instructorID);
    }
    public Call<M_Profile_Center> getProfileCenter(int centerID)
    {
        return api_interface.getProfileCenter(centerID);
    }
    public Call<S_edit_Student> patchStudent(S_edit_Student edit)
    {
        return api_interface.patchStudent(5);
    }

}
