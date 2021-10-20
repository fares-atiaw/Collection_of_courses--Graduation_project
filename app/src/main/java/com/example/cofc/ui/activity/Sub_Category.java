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

import com.example.cofc.model.M_MainCategory;
import com.example.cofc.model.M_SubCategory;
import com.example.cofc.special_model.R_mainCategories;
import com.example.cofc.special_model.R_subCategories;
import com.example.cofc.ui.fragment.DF_CurrentCourses;
import com.example.cofc.ui.fragment.DF_Notification;
import com.example.cofc.ui.fragment.DF_WatchLater;
import com.example.cofc.R;
import com.example.cofc.adapter.Pager_adapter;
import com.example.cofc.databinding.SCategoryBinding;
import com.example.cofc.model.Data_of_news;
import com.example.cofc.ui.fragment.FragmentNews;
import com.example.cofc.ui.fragment.Fragment_M_Category;
import com.example.cofc.ui.fragment.Fragment_S_Category;
import com.example.cofc.viewmodel.V_Main_Category;
import com.example.cofc.viewmodel.V_Sub_Category;

import java.util.ArrayList;
import java.util.List;

public class Sub_Category extends AppCompatActivity {

    SCategoryBinding binding;
    Pager_adapter pager_adapter;
    SharedPreferences sp;
    private static final String ARG_PARAM1 = "USER_IDs";
    private static final String ARG_PARAM2 = "course ID";
    private static final String ARG_PARAM3 = "category type";
    private static final String CHOICE = "choice";
    int type = 0;
    String categoryName;
    //R_subCategories r;
    V_Sub_Category mLive;
    //List<M_SubCategory> categories;
    //List<Data_of_news> news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.s_category);
        setSupportActionBar(binding.scToolbar);
        sp = getSharedPreferences(ARG_PARAM1 , MODE_PRIVATE);//Context.

        mLive = new ViewModelProvider(this).get(V_Sub_Category.class);
        pager_adapter = new Pager_adapter(getSupportFragmentManager() , 0);

        if (getIntent().getExtras() != null)
        {
            Bundle bundle = getIntent().getExtras();
            type = bundle.getInt(ARG_PARAM3);
            categoryName = bundle.getString("name");
            Toast.makeText(this, categoryName , Toast.LENGTH_SHORT).show();
            mLive.get_categories_news(type);
        }

        mLive.mLive.observe(this, new Observer<R_subCategories>() {
            @Override
            public void onChanged(R_subCategories r_subCategories) {
                //r = r_subCategories;
                //categories = r.getLista();
                //news = r.getN_list();
                pager_adapter.add_fragment(categoryName+" category" , Fragment_S_Category.newInstance(r_subCategories.getLista()));
                pager_adapter.add_fragment(categoryName+" news" , FragmentNews.newInstance(r_subCategories.getN_list() , null));
                binding.scPager.setAdapter(pager_adapter);

                pager_adapter.notifyDataSetChanged();
            }
        });

        binding.scTabs.setupWithViewPager(binding.scPager);

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
                Intent i = new Intent(Sub_Category.this, SignIn.class);
                startActivity(i);
            });
            ab.setNegativeButton(R.string.sign_up, (dialog, which) -> {
                Intent i = new Intent(Sub_Category.this, SignUp.class);
                startActivity(i);
            });
            ab.setNeutralButton(R.string.cancel, (dialog, which) -> {
            });
            ab.create().show();
        }
    }
}