package com.example.cofc.ui.activity;

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
import android.view.View;
import android.widget.Toast;

import com.example.cofc.R;
import com.example.cofc.adapter.RV_purchase_adapter;
import com.example.cofc.databinding.ActivityCartBinding;
import com.example.cofc.model.Data_of_package;
import com.example.cofc.model.Data_of_purchase;
import com.example.cofc.special_model.R_cart;
import com.example.cofc.special_model.S_BUY_fromCart;
import com.example.cofc.special_model.S_addToCart;
import com.example.cofc.special_model.S_deleteItem;
import com.example.cofc.viewmodel.V_Cart;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    ActivityCartBinding binding;
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
    R_cart myCart;
    V_Cart mLive;
    S_BUY_fromCart bought_items;
    S_deleteItem deletedItem;
    List<Data_of_purchase> list_course_packages;
    RV_purchase_adapter adapter;
    private int user_id;
    int t_price = 0;
    int newSize;
    List<S_addToCart.PackageID> t_packages = new ArrayList<>();
    List<S_addToCart.PackageID> removedIDs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cart);
        sp = this.getSharedPreferences(ARG_PARAM1 , Context.MODE_PRIVATE);

        mLive = new ViewModelProvider(this).get(V_Cart.class);
        mLive.get_myCart();

        mLive.mLive.observe(this, new Observer<R_cart>() {
            @Override
            public void onChanged(R_cart r_cart) {
                myCart = r_cart;
                list_course_packages = r_cart.getLista();
            }
        });

        newSize = list_course_packages.size();
        fill_RV(list_course_packages);

        binding.tvTotalItems.setText(String.valueOf(list_course_packages.size()));

        for(int i = 0 ; i <= list_course_packages.size()-1 ; i++)
        {
            for (int j = 0 ; j <= list_course_packages.get(i).getPackages().size()-1 ; j++)
            {
                t_price += list_course_packages.get(i).getPackages().get(j).getPackageCost();
                t_packages.add(new S_addToCart.PackageID(list_course_packages.get(i).getPackages().get(j).getPackageID()));
            }
        }
        binding.tvTotalPrice.setText(new StringBuilder().append(t_price).append(" L.E").toString());

        binding.cBtnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLive.post_goBuy(sp.getInt(ID_CC5,-1) , sp.getInt(ID_C7,-1) , new S_BUY_fromCart(t_packages));
                startActivity(new Intent(Cart.this , MainProfile.class));
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mLive.post_deletedItems(new S_deleteItem(sp.getInt(ID_C7,-1) , removedIDs));
    }

    public void fill_RV(List<Data_of_purchase> list)
    {
        adapter = new RV_purchase_adapter(list);
        adapter.setListener(new RV_purchase_adapter.PackagesChanged() {
            @Override
            public void onListChange(String course_name, List<Integer> ids , double price) {
                newSize --;
                t_price -= price;
                binding.tvTotalItems.setText(String.valueOf(newSize));
                binding.tvTotalPrice.setText(String.valueOf(t_price));
                for(int i = 0 ; i <= ids.size()-1 ; i++){
                    removedIDs.add(new S_addToCart.PackageID(ids.get(i)));
                    t_packages.remove((Object)ids.get(i));
                }
            }
        });
        adapter.notifyDataSetChanged();
        binding.rvCart.setAdapter(adapter);
        binding.rvCart.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCart.setItemAnimator(new DefaultItemAnimator());
    }

    /*@Override
    public void onListChange(List<Data_of_purchase> newList) {

        binding.tvTotalItems.setText(String.valueOf(newList.size()));

        int t_price = 0;
        for(int i = 0 ; i<newList.size()-1 ; i++)
        {
            //t_price += newList.get(i).getPrice();
        }
        binding.tvTotalPrice.setText(String.valueOf(t_price));
    }*/
}
/**/