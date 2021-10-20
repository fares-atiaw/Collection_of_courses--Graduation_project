package com.example.cofc.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.example.cofc.R;
import com.example.cofc.adapter.RV_channel_adapter;
import com.example.cofc.databinding.HelpfulChannelsBinding;
import com.example.cofc.model.Data_of_channels;
import com.example.cofc.special_model.R_channels;
import com.example.cofc.ui.fragment.DF_Notification;
import com.example.cofc.viewmodel.V_HelpFul_Channels;

import java.util.ArrayList;
import java.util.List;

public class Helpful_Channels extends AppCompatActivity {

    HelpfulChannelsBinding binding;
    SharedPreferences sp;
    private static final String ARG_PARAM1 = "USER_IDs";
    private static final String ARG_PARAM2 = "course ID";
    private static final String ARG_PARAM3 = "category type";
    int sub_id;
    String sub_name;
    R_channels channels;
    V_HelpFul_Channels mLive;
    RV_channel_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.helpful_channels);
        sp = this.getSharedPreferences(ARG_PARAM1 , Context.MODE_PRIVATE);
        setSupportActionBar(binding.helpfulToolbar);

        mLive = new ViewModelProvider(this).get(V_HelpFul_Channels.class);

        if (getIntent().getExtras() != null)
        {
            Bundle bundle = getIntent().getExtras();
            sub_id = bundle.getInt(ARG_PARAM3);
            sub_name = bundle.getString("name");
            binding.helpfulToolbar.setTitle(sub_name);
            mLive.get_ChannelsCourses(sub_id);
        }

        mLive.mLive.observe(this, new Observer<R_channels>() {
            @Override
            public void onChanged(R_channels r_channels) {
                channels = r_channels;
                if(channels != null)
                {
                    adapter = new RV_channel_adapter(getBaseContext() , channels.getLista());
                    binding.rvHelpful.setAdapter(adapter);
                }
            }
        });

        binding.rvHelpful.setLayoutManager(new LinearLayoutManager(this));
        binding.rvHelpful.setHasFixedSize(true);

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
                Intent i = new Intent(Helpful_Channels.this, SignIn.class);
                startActivity(i);
            });
            ab.setNegativeButton(R.string.sign_up, (dialog, which) -> {
                Intent i = new Intent(Helpful_Channels.this, SignUp.class);
                startActivity(i);
            });
            ab.setNeutralButton(R.string.cancel, (dialog, which) -> {
            });
            ab.create().show();
        }
    }
}