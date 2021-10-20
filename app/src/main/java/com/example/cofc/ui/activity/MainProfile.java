package com.example.cofc.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cofc.adapter.RV_C_adapter;
import com.example.cofc.databinding.MainProfileBinding;
import com.example.cofc.model.Data_of_course;
import com.example.cofc.model.Data_of_news;
import com.example.cofc.model.M_Profile_Center;
import com.example.cofc.model.M_Profile_Instructor;
import com.example.cofc.model.M_Profile_Student;
import com.example.cofc.model.M_SubCategory;
import com.example.cofc.special_model.R_IsSuccess;
import com.example.cofc.special_model.S_edit_Center;
import com.example.cofc.special_model.S_edit_Instructor;
import com.example.cofc.special_model.S_edit_Student;
import com.example.cofc.ui.fragment.DF_CurrentCourses;
import com.example.cofc.ui.fragment.DF_Favorite;
import com.example.cofc.ui.fragment.DF_Notification;
import com.example.cofc.ui.fragment.DF_WatchLater;
import com.example.cofc.R;
import com.example.cofc.viewmodel.V_MainProfile;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

public class MainProfile extends AppCompatActivity {

    MainProfileBinding binding;
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
    M_Profile_Student profile_S;
    M_Profile_Instructor profile_I;
    M_Profile_Center profile_C;
    V_MainProfile mLive;
    int user_type = 1;
    S_edit_Student edit_S;
    S_edit_Instructor edit_I;
    S_edit_Center edit_C;
    RV_C_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_profile);
        sp = getSharedPreferences(ARG_PARAM1, Context.MODE_PRIVATE);

        mLive = new ViewModelProvider(this).get(V_MainProfile.class);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            user_type = bundle.getInt("user_type");
        }

        check_which_user();

        mLive.mLive_student.observe(this, new Observer<M_Profile_Student>() {
            @Override
            public void onChanged(M_Profile_Student m_profile_student) {
                profile_S = m_profile_student;
                if(profile_S.isSuccess())
                {
                    /*Actions*/action_4_texts();
                    /*Buttons*/implement_buttons();
                }
            }
        });
        mLive.mLive_instructor.observe(this, new Observer<M_Profile_Instructor>() {
            @Override
            public void onChanged(M_Profile_Instructor m_profile_instructor) {
                profile_I = m_profile_instructor;
                if(profile_I.isSuccess())
                {
                    /*Actions*/action_4_texts();
                    /*Buttons*/implement_buttons();
                }
            }
        });
        mLive.mLive_center.observe(this, new Observer<M_Profile_Center>() {
            @Override
            public void onChanged(M_Profile_Center m_profile_center) {
                profile_C = m_profile_center;
                if(profile_C.isSuccess())
                {
                    /*Actions*/action_4_texts();
                    /*Buttons*/implement_buttons();
                }
            }
        });

    }
    /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
    void check_which_user()
    {
        if (user_type == 2) {            // GET by sp.getInt(ID_I2 , -1) the M_Profile_Instructor profile_I.
            mLive.get_Profile_Instructor(sp.getInt(ID_I2 , -1));
            Toast.makeText(this, "As Instructor", Toast.LENGTH_SHORT).show();
            implement_user(2);
        } else if (user_type == 3) {            // GET by sp.getInt(ID_C3 , -1) the M_Profile_Center profile_C.
            mLive.get_Profile_Center(sp.getInt(ID_C3 , -1));
            Toast.makeText(this, "As Center", Toast.LENGTH_SHORT).show();
            implement_user(3);
        } else if (user_type == 1) {            // GET by sp.getInt(ID_S1 , -1) the M_Profile_Student profile_S.
            //mLive.get_Profile_Student(sp.getInt(ID_S1, -1));
            mLive.get_Profile_Student(3005);
            Toast.makeText(this, "As Student", Toast.LENGTH_SHORT).show();
            implement_user(1);
        }
    }

    void edit_finish (TextView t , EditText e , boolean o){
        if (o == true) {
            t.setVisibility(View.GONE);
            e.setVisibility(View.VISIBLE);
            e.setText(t.getText());
        } else {
            t.setVisibility(View.VISIBLE);
            t.setText(e.getText());
            e.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPause () {
        super.onPause();
        if (user_type == 1) {
            edit_S = new S_edit_Student(//sp.getInt(ID_S1, -1),
                    null,
                    binding.mpTvName.getText().toString(),
                    binding.mpTvPhone.getText().toString(),
                    binding.mpTvEmail.getText().toString());     //Then use PATCH S_edit_Student edit_S
            mLive.patch_edit_S(sp.getInt(ID_S1, -1) , edit_S);
        }
        else if (user_type == 2) {
            edit_I = new S_edit_Instructor(//sp.getInt(ID_I2, -1),
                    null,
                    binding.mpTvName.getText().toString(),
                    binding.mpTvPhone.getText().toString(),
                    binding.mpTvEmail.getText().toString(),
                    null);                                      //Then use PATCH S_edit_Student edit_I
            mLive.patch_edit_I(sp.getInt(ID_I2, -1) , edit_I);
        }
        else if (user_type == 3) {
            edit_C = new S_edit_Center(//sp.getInt(ID_C3, -1),
                    null,
                    binding.mpTvName.getText().toString(),
                    binding.mpTvPhone.getText().toString(),
                    binding.mpTvEmail.getText().toString(),
                    binding.mpTvAddress.getText().toString());     //Then use PATCH S_edit_Student edit_C
            mLive.patch_edit_C(sp.getInt(ID_C3, -1) , edit_C);
        }
    }

    void action_4_texts()
    {
        binding.mpTbtnName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edit_finish(binding.mpTvName, binding.mpEtName, isChecked);
            }
        });
        binding.mpTbtnPhone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edit_finish(binding.mpTvPhone, binding.mpEtPhone, isChecked);
            }
        });
        binding.mpTbtnEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edit_finish(binding.mpTvEmail, binding.mpEtEmail, isChecked);
            }
        });
        binding.mpTbtnAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edit_finish(binding.mpTvAddress, binding.mpEtAddress, isChecked);
            }
        });
    }

    void implement_buttons()
    {
        binding.mpBtnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DF_Favorite f = DF_Favorite.newInstance(profile_S.getFavorites());
                f.show(getSupportFragmentManager() , null);

                /*ArrayList<M_SubCategory> arrayList = new ArrayList<>();
                arrayList.add(new M_SubCategory(4, "Art", "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
                arrayList.add(new M_SubCategory(4, "Machine Learning", "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
                arrayList.add(new M_SubCategory(4, "Seamstress", "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
                arrayList.add(new M_SubCategory(4, "Engineering", "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
                DF_Favorite f = DF_Favorite.newInstance(arrayList);
                f.show(getSupportFragmentManager(), null);*/
            }
        });
        binding.mpIbtnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DF_Notification df = DF_Notification.newInstance(profile_S.getNotifications());
                df.show(getSupportFragmentManager(), null);

                /*ArrayList<Data_of_news> arrayList2 = new ArrayList<>();
                arrayList2.add(new Data_of_news(21, "How to be a Project Manager", "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png", "Mr. Shrieff Moharam", null, "Today", "The course is free for 1 week , starts 12/2/2021 and ends 19/2/2021."));
                arrayList2.add(new Data_of_news(21, "AI Concept", "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png", "Mr. Fares Hassan", "Today", null, "The installment feature has been added. The course now has 4 packages."));
                arrayList2.add(new Data_of_news(21, "Machine Learning from zero to hero", "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png", "Mr. Mohamed Maamoon", null, "Today", "There is an offer 90% discount on the course , starts 2/2/2021 and ends 8/2/2021."));
                arrayList2.add(new Data_of_news(21, "Cyber Security", "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png", "Mr. Maged Hossam", "Today", null, "There is an offer 70% discount on the course , starts 20/1/2021 and ends 1/2/2021."));
                arrayList2.add(new Data_of_news(25, "Fundamentals of Data Science", "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png", "Mr. Amr Medhat", "Today", null, "The course is free for 1 week , starts 1/1/2021 and ends 20/1/2021."));
                DF_Notification df = DF_Notification.newInstance(arrayList2);
                df.show(getSupportFragmentManager(), null);*/
            }
        });
        binding.mpBtnWatchlater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DF_WatchLater df = DF_WatchLater.newInstance(profile_S.getWatchLater());
                df.show(getSupportFragmentManager(), null);

                /*List<Data_of_course> arrayList = new ArrayList<>();
                arrayList.add(new Data_of_course(5, "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png", "Embbeded System from Scratch", "12/12/2019", "Mr. Fares Hassan", null, "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png", true, null, (double) 26, true, (float) 3));
                arrayList.add(new Data_of_course(55, "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png", "How to start building your own business", "yesterday", "Prof. Shrieff Moharam", null, "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png", false, "12 Baker Street", (double) 80, true, (float) 4));
                arrayList.add(new Data_of_course(5, "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png", "Embbeded System from Scratch", "12/12/2019", "Mr. Fares Hassan", null, "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png", true, null, (double) 26, true, (float) 3));
                DF_WatchLater df = DF_WatchLater.newInstance(arrayList);
                df.show(getSupportFragmentManager(), null);*/
            }
        });
        binding.mpBtnCurrentCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainProfile.this, AvailableCourse.class));
            }
        });
        binding.mpBtnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainProfile.this, Cart.class));
            }
        });

        binding.mpBtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.stackoverflow.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        binding.mpBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.stackoverflow.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        binding.mpTbtnMycourses.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    binding.rvMycourses.setVisibility(View.VISIBLE);
                else
                    binding.rvMycourses.setVisibility(View.GONE);
            }
        });

        binding.mpBtnInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainProfile.this, MainProfile.class);
                i.putExtra("user_type", 2);
                startActivity(i);
            }
        });
        binding.mpBtnCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainProfile.this, MainProfile.class);
                i.putExtra("user_type", 3);
                startActivity(i);
            }
        });
        binding.switchLanguage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == false)
                    binding.switchLanguage.setText("English");
                else
                    binding.switchLanguage.setText("عربي");
            }
        });
    }

    // 3 classes , 1 of them implements and make visibility=true & the others are null .
    //  The two buttons are visible if I implements as student but are invisible if I implements as instructor or as center.
    void implement_user(int u)
    {
        if(u==1) {
            RequestOptions defaultOptions = new RequestOptions()
                    .error(R.drawable.green_bubble);
            Glide.with(getBaseContext())
                    .setDefaultRequestOptions(defaultOptions)
                    .load(profile_S.getLogo())//which url
                    .into(binding.mpIvLogo);//which view

            binding.mpTvName.setText(profile_S.getUserName());
            binding.mpTvPhone.setText(profile_S.getPhone());
            binding.mpTvEmail.setText(profile_S.getEmail());

            binding.mpLayoutAddress.setVisibility(View.GONE);
            binding.mpBtnUpload.setVisibility(View.GONE);
            binding.mpBtnEdit.setVisibility(View.GONE);
            binding.mpTbtnMycourses.setVisibility(View.GONE);
        }
        else if(u==2) {
            RequestOptions defaultOptions = new RequestOptions()
                    .error(R.drawable.green_bubble);
            Glide.with(getBaseContext())
                    .setDefaultRequestOptions(defaultOptions)
                    .load(profile_I.getLogo())//which url
                    .into(binding.mpIvLogo);//which view

            binding.mpTvName.setText(profile_I.getUserName());
            binding.mpTvPhone.setText(profile_I.getPhone());
            binding.mpTvEmail.setText(profile_I.getEmail());

            binding.mpLayoutAddress.setVisibility(View.GONE);
            binding.mpLayoutStudentThings.setVisibility(View.GONE);
            binding.mpBtnInstructor.setVisibility(View.GONE);
            binding.mpBtnCenter.setVisibility(View.GONE);

            adapter = new RV_C_adapter(getBaseContext() , profile_I.getMyCourses());
            binding.rvMycourses.setAdapter(adapter);
            binding.rvMycourses.setLayoutManager(new LinearLayoutManager(this));
            binding.rvMycourses.setHasFixedSize(true);
        }
        else if(u==3) {
            RequestOptions defaultOptions = new RequestOptions()
                    .error(R.drawable.green_bubble);
            Glide.with(getBaseContext())
                    .setDefaultRequestOptions(defaultOptions)
                    .load(profile_C.getLogo())//which url
                    .into(binding.mpIvLogo);//which view

            binding.mpTvName.setText(profile_C.getUserName());
            binding.mpTvPhone.setText(profile_C.getPhone());
            binding.mpTvEmail.setText(profile_C.getEmail());
            binding.mpTvAddress.setText(profile_C.getAddress());

            binding.mpLayoutStudentThings.setVisibility(View.GONE);
            binding.mpBtnInstructor.setVisibility(View.GONE);
            binding.mpBtnCenter.setVisibility(View.GONE);

            adapter = new RV_C_adapter(getBaseContext() , profile_C.getMyCourses());
            binding.rvMycourses.setAdapter(adapter);
            binding.rvMycourses.setLayoutManager(new LinearLayoutManager(this));
            binding.rvMycourses.setHasFixedSize(true);
        }
    }
}