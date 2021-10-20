package com.example.cofc.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cofc.R;
import com.example.cofc.adapter.RV_availables_adapter;
import com.example.cofc.adapter.RV_component_adapter;
import com.example.cofc.databinding.AvailableCourseBinding;
import com.example.cofc.interfaces.VideoItem_onClick;
import com.example.cofc.model.Data_of_video;
import com.example.cofc.special_model.R_availableCourses;
import com.example.cofc.viewmodel.V_AvailableCourse;

public class AvailableCourse extends AppCompatActivity {

    AvailableCourseBinding binding;
    SharedPreferences sp;
    private static final String ID_S1 = "Student_ID";
    private static final String ID_I2 = "Instructor_ID";
    private static final String ID_C3 = "Center_ID";
    private static final String ID_F4 = "Favorite_ID";
    private static final String ID_CC5 = "CurrentCourses_ID";
    private static final String ID_W6 = "WatchLaterID";
    private static final String ID_C7 = "CartID";
    private static final String ARG_PARAM1 = "USER_IDs";
    private static final String ARG_PARAM2 = "course ID";
    private static final String ARG_PARAM3 = "category type";
    private static final String ARG_F = "favorites";
    private static final String ARG_CC = "current courses";
    private static final String ARG_N = "notifications";
    private static final String ARG_W = "watchLater";
    R_availableCourses available_courses;
    V_AvailableCourse mLive;
    RV_availables_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.available_course);
        sp = getSharedPreferences(ARG_PARAM1 , Context.MODE_PRIVATE);

        setSupportActionBar(binding.availableToolbar);
        mLive = new ViewModelProvider(this).get(V_AvailableCourse.class);

        //GET R_availableCourses available_courses.getLista() by sp.getInt(ID_CC5 , -1).
        mLive.get_availableCourses(sp.getInt(ID_CC5 , -1));

        adapter = new RV_availables_adapter(available_courses.getLista());
        adapter.setListener(new VideoItem_onClick() {
            @Override
            public void click(Data_of_video data) {
                Intent i = new Intent(AvailableCourse.this , Video_Comment.class);
                i.putExtra("video_id" , data.getVideoID());
                i.putExtra("video_name" , data.getVideoName());
                i.putExtra("video_URL" , data.getVideoURL());
                i.putExtra("show" , true);
                startActivity(i);
            }
        });
        binding.acRvCourses.setAdapter(adapter);
        binding.acRvCourses.setHasFixedSize(true);
        binding.acRvCourses.setLayoutManager(new LinearLayoutManager(this));
        binding.acRvCourses.setItemAnimator(new DefaultItemAnimator());

        mLive.mLive.observe(this, new Observer<R_availableCourses>() {
            @Override
            public void onChanged(R_availableCourses r_availableCourses) {
                available_courses = r_availableCourses;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.available_course_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case(R.id.ac_menu_profile):
                Intent i = new Intent(AvailableCourse.this, MainProfile.class);
                startActivity(i);
                return true;

            case(R.id.ac_menu_main_categories):
                startActivity(new Intent(this , Main_Category.class));
                return true;

            case(R.id.ac_menu_login):
                startActivity(new Intent(this , SignIn.class));
                return true;
        }
        return false;
    }
}