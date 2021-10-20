package com.example.cofc.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cofc.model.Data_of_news;
import com.example.cofc.model.M_MainCategory;
import com.example.cofc.special_model.R_courses_news;
import com.example.cofc.special_model.R_mainCategories;
import com.example.cofc.special_model.S_SignIn;
import com.example.cofc.ui.fragment.DF_Notification;
import com.example.cofc.adapter.Pager_adapter;
import com.example.cofc.R;
import com.example.cofc.databinding.MCategoryBinding;
import com.example.cofc.ui.fragment.FragmentNews;
import com.example.cofc.ui.fragment.Fragment_M_Category;
import com.example.cofc.viewmodel.V_AllCourses;
import com.example.cofc.viewmodel.V_Main_Category;

import java.util.ArrayList;
import java.util.List;

public class Main_Category extends AppCompatActivity {

    MCategoryBinding binding;
    Pager_adapter pager_adapter;
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
    private static final String CHOICE = "choice";
    S_SignIn sign;
    //R_mainCategories r;
    V_Main_Category mLive;
    //List<M_MainCategory> categories = new ArrayList<>();
    //List<Data_of_news> news = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.m_category);
        setSupportActionBar(binding.mcToolbar);
        sp = getSharedPreferences(ARG_PARAM1 , MODE_PRIVATE); //Context.

        mLive = new ViewModelProvider(this).get(V_Main_Category.class);
        pager_adapter = new Pager_adapter(getSupportFragmentManager() , 0);

        mLive.get_categories_news();

        mLive.mLive.observe(this, new Observer<R_mainCategories>() {
            @Override
            public void onChanged(R_mainCategories r_mainCategories) {
                //r = r_mainCategories;
                //categories = r.getLista();
                //news = r.getN_list();
                pager_adapter.add_fragment("Main Category" , Fragment_M_Category.newInstance(r_mainCategories.getLista()));
                pager_adapter.add_fragment("Main News" , FragmentNews.newInstance(r_mainCategories.getN_list() , null));
                binding.mcPager.setAdapter(pager_adapter);

                pager_adapter.notifyDataSetChanged();
            }
        });
        binding.mcTab.setupWithViewPager(binding.mcPager);

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
                Intent i = new Intent(Main_Category.this, SignIn.class);
                startActivity(i);
            });
            ab.setNegativeButton(R.string.sign_up, (dialog, which) -> {
                Intent i = new Intent(Main_Category.this, SignUp.class);
                startActivity(i);
            });
            ab.setNeutralButton(R.string.cancel, (dialog, which) -> {
            });
            ab.create().show();
        }
    }

}