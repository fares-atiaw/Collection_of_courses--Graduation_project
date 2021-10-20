package com.example.cofc.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.adapters.AdapterViewBindingAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cofc.R;
import com.example.cofc.adapter.RV_package_adapter;
import com.example.cofc.databinding.PriceDetailsBinding;
import com.example.cofc.model.Data_of_package;
import com.example.cofc.special_model.R_cart;
import com.example.cofc.special_model.R_packages;
import com.example.cofc.special_model.S_addToCart;
import com.example.cofc.viewmodel.V_PriceDetails;

import java.util.ArrayList;
import java.util.List;

public class PriceDetails extends AppCompatActivity {

    PriceDetailsBinding binding;
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
    int course_id;
    String course_name;
    R_packages packages;
    V_PriceDetails mLive;
    List<Data_of_package> packageList;
    RV_package_adapter adapter;
    List<S_addToCart.PackageID> chosen = new ArrayList<>();
    S_addToCart adds;
    R_cart myCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.price_details);
        sp = this.getSharedPreferences(ARG_PARAM1 , Context.MODE_PRIVATE);

        mLive = new ViewModelProvider(this).get(V_PriceDetails.class);

        if (getIntent().getExtras() != null)
        {
            Bundle bundle = getIntent().getExtras();
            course_id = bundle.getInt(ARG_PARAM2);
            course_name = bundle.getString("name");
            Toast.makeText(this, course_id+"" , Toast.LENGTH_SHORT).show();
            mLive.get_packages(course_id);
        }
        //mLive.get_packages(32);

        mLive.mLive.observe(this, new Observer<R_packages>() {
            @Override
            public void onChanged(R_packages r_packages) {
                packages = r_packages;

                packageList = packages.getLista();

                RV_fill(packageList);

                binding.pdBtnAddToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*chosen = adapter.getChosen_packages();
                        adds = new S_addToCart(chosen);*/
                        Toast.makeText(PriceDetails.this, "You don't have a cart" , Toast.LENGTH_SHORT).show();
                       /* mLive.post_packagesToCart(sp.getInt(ID_C7 , -1) , adds);
                        // POST S_addToCart adds , then receive R_IsSuccess and use it in a Toast.
                        // Toast.makeText(PriceDetails.this, chosen.get(0)+"\n Add to your cart successfully \uD83D\uDCE5" , Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PriceDetails.this , MainProfile.class));*/
                    }
                });

            }
        });

    }

    public void RV_fill(List<Data_of_package> list)
    {Log.v("wp2" , "2222222");
        if (list.size() > 1)
            binding.pdTvInfo.setText(R.string.purchasing_info1);
        else
            binding.pdTvInfo.setText(R.string.purchasing_info2);
        Log.v("wp3" , "3333333");
        adapter = new RV_package_adapter(list);
        binding.pdRvPackages.setAdapter(adapter);
        binding.pdRvPackages.setLayoutManager(new LinearLayoutManager(this));
        binding.pdRvPackages.setHasFixedSize(true);
        binding.pdRvPackages.setItemAnimator(new DefaultItemAnimator());
    }

}
/*for(int i = 0 ; i <= myCart.getLista().size()-1 ; i++)
                {
                    if (myCart.getLista().get(i).getCourse_name().equals(course_name))
                    {
                        for(int j = 0 ; j <= myCart.getLista().get(i).getPackages().size()-1 ; j++)
                        {
                            for(int k = 0 ; k <= myCart.getLista().get(i).getPackages().size()-1 ; k++)
                                if(myCart.getLista().get(i).getPackages().get(j).getPackageID() == adds.getPackageIDs().get(k))
                                    Toast.makeText(PriceDetails.this, "You have selected a package that was previously purchased.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }*/
/*List<Data_of_package.Component> lista = new ArrayList<>();
            lista.add(new Data_of_package.Component("dd"));
            lista.add(new Data_of_package.Component("dd"));
            lista.add(new Data_of_package.Component("dd"));
            lista.add(new Data_of_package.Component("dd"));
            lista.add(new Data_of_package.Component("dd"));

            List<Data_of_package> list = new ArrayList<>();
            list.add(new Data_of_package(1,4, 50 , lista));
            list.add(new Data_of_package(1,4, 50 , lista));
            list.add(new Data_of_package(1,4, 50 , lista));
            list.add(new Data_of_package(1,4, 50 , lista));*/


/*// GET R_cart myCart to check if he has the same course with the same packages before we add it in his cart
                for(int i = 0 ; i<= myCart.getLista().size()-1 ; i++)
                {
                    if(myCart.getLista().get(i).getCourse_name().equals(course_name))
                    {
                        for(int j = 0 ; j <= myCart.getLista().get(i).getPackages().size()-1 ; j++)
                        {
                            for(int k = 0 ; k <= adds.getPackageIDs().size()-1 ; k++)
                            {
                                if(myCart.getLista().get(i).getPackages().get(j).getPackageID() == adds.getPackageIDs().get(k))
                                {
                                    Toast.makeText(PriceDetails.this,
                                            "You have selected a package that was previously purchased.",
                                            Toast.LENGTH_SHORT).show();
                                    flag = false;
                                    break;
                                }
                            }
                        }
                    }
                }*/