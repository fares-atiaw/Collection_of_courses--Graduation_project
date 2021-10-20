package com.example.cofc.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cofc.R;
import com.example.cofc.adapter.Pager_adapter;
import com.example.cofc.databinding.CoursesAllBinding;
import com.example.cofc.model.Data_of_course;
import com.example.cofc.model.Data_of_news;
import com.example.cofc.special_model.R_courses_news;
import com.example.cofc.special_model.S_getCourses;
import com.example.cofc.ui.fragment.DF_Notification;
import com.example.cofc.ui.fragment.DF_WatchLater;
import com.example.cofc.ui.fragment.FragmentCourse;
import com.example.cofc.ui.fragment.FragmentNews;
import com.example.cofc.viewmodel.V_AllCourses;

import java.util.ArrayList;
import java.util.List;

public class AllCourses extends AppCompatActivity {

    CoursesAllBinding binding;
    Pager_adapter pager_adapter;
    SharedPreferences sp;
    private static final String ARG_PARAM1 = "USER_IDs";
    private static final String ARG_PARAM2 = "course ID";
    private static final String ARG_PARAM3 = "category type";
    //R_courses_news total;
    V_AllCourses mLive;
    int sub_type;
    String categoryName;
    //List<Data_of_course> courses;
    //List<Data_of_news> news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.courses_all);
        sp = this.getSharedPreferences(ARG_PARAM1 , Context.MODE_PRIVATE);
        setSupportActionBar(binding.allToolbar);

        mLive = new ViewModelProvider(this).get(V_AllCourses.class);

        pager_adapter = new Pager_adapter(getSupportFragmentManager() , 0);

        if (getIntent().getExtras() != null)
        {
            Bundle bundle = getIntent().getExtras();
            sub_type = bundle.getInt(ARG_PARAM3);
            categoryName = bundle.getString("name");
            Toast.makeText(this, categoryName, Toast.LENGTH_LONG).show();
            mLive.get_courses_news(new S_getCourses(sub_type , "all"));
        }

        mLive.mLive.observe(this, new Observer<R_courses_news>() {
            @Override
            public void onChanged(R_courses_news r_courses_news) {
                if(r_courses_news.isSuccess()) {
                    //total = r_courses_news;
                    //courses = total.getC_list();
                    //news = total.getN_list();
                    pager_adapter.add_fragment("All Courses", FragmentCourse.newInstance(r_courses_news.getC_list()));
                    pager_adapter.add_fragment("News", FragmentNews.newInstance(r_courses_news.getN_list(), "all"));
                    binding.allPager.setAdapter(pager_adapter);
                    pager_adapter.notifyDataSetChanged();
                }
            }
        });

        binding.allTabs.setupWithViewPager(binding.allPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case(R.id.menu_profile):
                Alert("profile");
                return true;

            case(R.id.menu_cart):
                Alert("cart");
                return true;

            case(R.id.menu_notification):
                Alert("gg");
                return true;

            case(R.id.menu_login):
                startActivity(new Intent(this , SignIn.class));
                return true;
        }
        return false;
    }

    public void Alert(String w)
    {
        if (sp.getInt(ARG_PARAM1 , -1) != -1)
        {
            if (w.equals("profile"))
                startActivity(new Intent(this, MainProfile.class));
            else if (w.equals("cart"))
                startActivity(new Intent(this, Cart.class));
            else {
                DF_Notification n = new DF_Notification();
                n.show(getSupportFragmentManager() , null);
            }
        }
        else {
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setIcon(R.drawable.ic_double_arrow_24);

            if(w.equals("profile"))
                ab.setTitle(R.string.signin_first_forProfile);
            else if(w.equals("cart"))
                ab.setTitle(R.string.signin_first_forCart);
            else
                ab.setTitle(R.string.signin_first_forNotification);

            ab.setPositiveButton(R.string.sign_in, (dialog, which) -> {
                Intent i = new Intent(AllCourses.this, SignIn.class);
                startActivity(i);
            });
            ab.setNegativeButton(R.string.sign_up, (dialog, which) -> {
                Intent i = new Intent(AllCourses.this, SignUp.class);
                startActivity(i);
            });
            ab.setNeutralButton(R.string.cancel, (dialog, which) -> {
            });
            ab.create().show();
        }
    }
}