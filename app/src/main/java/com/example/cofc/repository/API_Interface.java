package com.example.cofc.repository;

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
import com.example.cofc.special_model.S_edit_Center;
import com.example.cofc.special_model.S_edit_Instructor;
import com.example.cofc.special_model.S_edit_Student;
import com.example.cofc.ui.activity.Helpful_Channels;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API_Interface {        //https://reqbin.com/

    //@FormUrlEncoded
    @POST("api/auth/Login")
   // @Headers({ "Content-Type: application/json; charset=utf-8"})
    public Call<R_7_IDs> postSignIn(@Body S_SignIn signIn);
//MainCategory ----------------------------------------------------------------------------------------
    @GET("api/Course/ReturnMainCategory")   //Works
    public Call<R_mainCategories> getMainCategories();
//SubCategory ----------------------------------------------------------------------------------------
    @GET("api/Course/ReturnSubCategory")    //Works
    public Call<R_subCategories> getSubCategories(@Query("MainCategoryID") int mainCategoryID);
//Online/Offline/AllCourses ----------------------------------------------------------------------------------------
    @GET("api/Course/RetrunListOfCourcesAndNews")   //Works
    public Call<R_courses_news> getCoursesNews(@Query("SubCategoryID") int SubCategoryID , @Query("choice") String choice);
//HelpfulChannels ----------------------------------------------------------------------------------------
    @GET("api/Course/ReturnListChannel")    //Works
    public Call<R_channels> getHelpfulChannels(@Query("SubCategoryID") int subCategoryID);
//CoursesDetails ----------------------------------------------------------------------------------------
    @GET("api/Course/ReturnCourseDetails")  //Works
    public Call<M_CourseDetails> getCourseDetails(@Query("CourseID") int courseID);
//PriceDetails ----------------------------------------------------------------------------------------
    @GET("api/Course/ReturnPackage")    //Works
    public Call<R_packages> getCoursePackages(@Query("CourseID") int courseID);
//MainProfile ----------------------------------------------------------------------------------------
    @GET("api/auth/ReturnStudentProfile")   //Student
    public Call<M_Profile_Student> getProfileStudent(@Query("StudentID") int studentID);
    @GET("api/Inst/ReturnInstructorProfile")    //Instructor
    public Call<M_Profile_Instructor> getProfileInstructor(@Query("(InstructorID") int instructorID);
    @GET("api/Center/ReturnCenterProfile")  //Center
    public Call<M_Profile_Center> getProfileCenter(@Query("CenterID") int centerID);

  //  ------------------------------------------------------------------------------------------------------

    @GET("api/Course/AddCourseToWatchLater")
    public Call<R_IsSuccess> addToWatchLater(@Query("CourseID") int courseID , @Query("WatchLaterID") int watchLaterID);

    @GET("api/Course/AddSubCategoryToFavorite")
    public Call<R_IsSuccess> addToFavorites(@Query("subCategoryID") int subCategoryID , @Query("favoriteID") int favoriteID);



    @FormUrlEncoded
    @POST("api/auth/AddProfileData")
    public Call<S_edit_Student> patchStudent(@Field("studentID") int studentID);

    @FormUrlEncoded
    @POST("api/Inst/EditInstructor")
    public Call<S_edit_Instructor> patchInstructor(@Field("instructorID") int instructorID);

    @FormUrlEncoded
    @POST("api/Center/EditCenter")
    public Call<S_edit_Center> patchCenter(@Field("centerID") int centerID);




}
