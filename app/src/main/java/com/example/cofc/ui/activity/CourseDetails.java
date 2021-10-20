package com.example.cofc.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cofc.R;
import com.example.cofc.adapter.RV_component_adapter;
import com.example.cofc.adapter.RV_video_adapter;
import com.example.cofc.databinding.CourseDetailsBinding;
import com.example.cofc.interfaces.VideoItem_onClick;
import com.example.cofc.model.Data_of_component;
import com.example.cofc.model.Data_of_video;
import com.example.cofc.model.M_CourseDetails;
import com.example.cofc.special_model.S_Add_watchLater;
import com.example.cofc.viewmodel.V_CourseDetails;

import java.util.ArrayList;
import java.util.List;

public class CourseDetails extends AppCompatActivity {  // implements Item_onClick

    CourseDetailsBinding binding;
    SharedPreferences sp;
    private static final String ID_W6 = "WatchLaterID";
    private static final String ARG_PARAM1 = "USER_IDs";
    private static final String ARG_PARAM2 = "course ID";
    private static final int REQUEST_CALL = 1;
    int course_id;
    String course_name;
    M_CourseDetails details;
    V_CourseDetails mLive;
    RV_video_adapter adapter_videos;
    RV_component_adapter adapter_components;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.course_details);
        sp = this.getSharedPreferences(ARG_PARAM1, Context.MODE_PRIVATE);
        setSupportActionBar(binding.detailsToolbar);

        mLive = new ViewModelProvider(this).get(V_CourseDetails.class);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            course_id = bundle.getInt(ARG_PARAM2);
            course_name = bundle.getString("name");
            Toast.makeText(this, course_name, Toast.LENGTH_SHORT).show();
            mLive.get_courseDetails(course_id); //19
        }

        mLive.mLive.observe(this, new Observer<M_CourseDetails>() {
            @Override
            public void onChanged(M_CourseDetails m_courseDetails) {
                details = m_courseDetails;
                if(details != null){
                    implementation();   //Implementations
                    filling_Buttons();    //Buttons
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.course_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case (R.id.cd_menu_add):
                if(sp.getInt(ARG_PARAM1 , -1) != -1)
                {
                    mLive.post_watchLater(new S_Add_watchLater(details.getCourseID() , sp.getInt(ID_W6 , -1)));
                    Toast.makeText(this, "Added to your WatchLater successfully", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this, "Sign in first to use your WatchLater list", Toast.LENGTH_LONG).show();
                return true;

            case (R.id.cd_menu_profile):
                Alert("profile");
                return true;

            case (R.id.cd_menu_cart):
                Alert("cart");
                return true;

            case (R.id.cd_menu_login):
                startActivity(new Intent(this, SignIn.class));
                return true;
        }
        return false;
    }

    void implementation()
    {
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.following_rate);
        Glide.with(getBaseContext())
                .setDefaultRequestOptions(defaultOptions)
                .load(details.getLogo())//which url
                .into(binding.cdIvCourseLogo);//which view
        binding.detailsToolbar.setTitle(details.getCourseName());
        binding.cdTvReleaseDate.setText(details.getRelasedDate());

        if (details.getCv() != null) {
            binding.cdTvInstructorName.setText(details.getInstructorName());
            binding.cdBtnCv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = details.getCv();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
            binding.cdLayoutInstructor.setVisibility(View.VISIBLE);
            binding.cdTvCenterName.setVisibility(View.GONE);
        }
        else
        {
            binding.cdLayoutInstructor.setVisibility(View.GONE);
            binding.cdTvCenterName.setText(details.getInstructorName());
            binding.cdTvCenterName.setVisibility(View.VISIBLE);
        }

        if (details.getAddress() != null) {
            binding.cdTvAddress.setText(details.getAddress());
            binding.layoutAddress.setVisibility(View.VISIBLE);
        } else
            binding.layoutAddress.setVisibility(View.GONE);

        binding.cdTvPhone.setText(details.getPhone());

        if (details.isOnline())
            binding.layoutOnline.setVisibility(View.VISIBLE);
        else
            binding.layoutOnline.setVisibility(View.GONE);

        binding.cdStars.setRating(details.getStars());
        Glide.with(getBaseContext())
                .setDefaultRequestOptions(defaultOptions)
                .load(details.getqA_FollowingWN())//which url
                .into(binding.cdIvFollowingRate);//which view

        binding.cdTvPrice.setText(String.valueOf(details.getPrice()));
        if (details.isOptions())
            binding.cdTvOptions.setVisibility(View.VISIBLE);
        else
            binding.cdTvOptions.setVisibility(View.GONE);

        binding.cdTvDescription.setText(details.getDescription());
        binding.cdTvRequirenments.setText(details.getRequirements());
        binding.cdTvEmail.setText(details.getEmail());

        adapter_videos = new RV_video_adapter(details.getFreeVideos());
        adapter_videos.setListener(new VideoItem_onClick() {
            @Override
            public void click(Data_of_video data) {
                Intent i = new Intent(CourseDetails.this , Video_Comment.class);
                i.putExtra("video_id" , data.getVideoID());
                i.putExtra("video_name" , data.getVideoName());
                i.putExtra("video_URL" , data.getVideoURL());
                i.putExtra("show" , false);
                startActivity(i);
            }
        });
        binding.cdRvIntroComponents.setAdapter(adapter_videos);
        binding.cdRvIntroComponents.setLayoutManager(new LinearLayoutManager(this));
        binding.cdRvIntroComponents.setHasFixedSize(true);
        binding.cdRvIntroComponents.setItemAnimator(new DefaultItemAnimator());

        adapter_components = new RV_component_adapter(details.getComponents());
        adapter_components.setListener(new VideoItem_onClick() {
            @Override
            public void click(Data_of_video data) {
                //Toast.makeText(CourseDetails.this, "Buy the package that has this component and watch it in your current courses list", Toast.LENGTH_SHORT).show();
            }
        });
        binding.cdRvComponents.setAdapter(adapter_components);
        binding.cdRvComponents.setLayoutManager(new LinearLayoutManager(this));
        binding.cdRvComponents.setHasFixedSize(true);
        binding.cdRvComponents.setItemAnimator(new DefaultItemAnimator());
    }

    void filling_Buttons()
    {
        binding.cdBtnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alert("code");
            }
        });

        binding.cdBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
                    /*Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+details.getPhone()));
                    startActivity(callIntent);*/
            }
        });

        binding.cdBtnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pd = new Intent(CourseDetails.this, PriceDetails.class);
                pd.putExtra(ARG_PARAM2, course_id);
                pd.putExtra("name", details.getCourseName());
                startActivity(pd);
            }
        });

        binding.cdTbtnIntroComponents.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    binding.cdRvIntroComponents.setVisibility(View.VISIBLE);
                else
                    binding.cdRvIntroComponents.setVisibility(View.GONE);
            }
        });

        binding.cdTbtnComponents.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Alert("component");
                if (isChecked)
                    binding.cdRvComponents.setVisibility(View.VISIBLE);
                else
                    binding.cdRvComponents.setVisibility(View.GONE);
            }
        });
    }

    public void Alert(String w) {
        AlertDialog.Builder ab = new AlertDialog.Builder(CourseDetails.this);
        //Intent ii;
        if (sp.getInt(ARG_PARAM1, -1) != -1) {
            if (w.equals("code")) {
                ab.setTitle("1851356");
                ab.create().show();
            }
            //if (w.equals("component")) { }
            if (w.equals("profile"))
                startActivity(new Intent(CourseDetails.this, MainProfile.class));
            if (w.equals("cart"))
                startActivity(new Intent(CourseDetails.this, Cart.class));
        }
        else {
            ab.setIcon(R.drawable.ic_double_arrow_24);

            if(w.equals("profile"))
                ab.setTitle(R.string.signin_first_forProfile);
            else if (w.equals("code"))
                ab.setTitle(R.string.signin_first_forCode);
                /*if (w.equals("component"))
                    ab.setTitle(R.string.signin_first_forComponent);*/
            else if (w.equals("cart"))
                ab.setTitle(R.string.signin_first_forCart);

            ab.setPositiveButton(R.string.sign_in, (dialog, which) -> {
                startActivity(new Intent(CourseDetails.this, SignIn.class));
            });
            ab.setNegativeButton(R.string.sign_up, (dialog, which) -> {
                startActivity(new Intent(CourseDetails.this, SignUp.class));
            });
            ab.setNeutralButton(R.string.cancel, (dialog, which) -> {
            });
            ab.create().show();
        }
    }

//الله أعلم
    private void makePhoneCall()
    {
        if (ContextCompat.checkSelfPermission(CourseDetails.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(CourseDetails.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        else {
            String dial = "tel:" + details.getPhone();
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                makePhoneCall();
            else
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
        }
    }

}
/*@Override
    public void click(Button btn) {
        //I can check from here or from there
        Intent i = new Intent(CourseDetails.this , Video_Comment.class);
        i.putExtra("course name" , binding.detailsToolbar.getTitle()+"");
        i.putExtra("video name" , btn.getText()+"");
        startActivity(i);
    }*/
/*List<String> ll = new ArrayList<>();
        ll.add("First");
        ll.add("Second");
        List<Data_of_component> list = new ArrayList<>();
        list.add(new Data_of_component("ggg", ll));
        list.add(new Data_of_component("ggg", ll));
        list.add(new Data_of_component("ggg", ll));
        list.add(new Data_of_component("ggg", ll));*/