package com.example.cofc.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cofc.model.Data_of_package;
import com.example.cofc.model.Data_of_purchase;
import com.example.cofc.special_model.R_cart;
import com.example.cofc.special_model.S_BUY_fromCart;
import com.example.cofc.special_model.S_deleteItem;

import java.util.ArrayList;
import java.util.List;

public class V_Cart extends AndroidViewModel {

    public MutableLiveData<R_cart> mLive = new MutableLiveData<>();

    public V_Cart(@NonNull Application application) {
        super(application);
    }

    // GET R_cart myCart from database by sp.getInt(ID_C7 , -1)
    public void get_myCart()
    {
        List<Data_of_package> listinin = new ArrayList<>();
        listinin.add(new Data_of_package(1 , 23 , 22.0 , null));
        List<Data_of_purchase> list = new ArrayList<>();
        list.add(new Data_of_purchase("Abo111" , listinin));
        list.add(new Data_of_purchase("Abo222" , listinin));
        list.add(new Data_of_purchase("Abo333" , listinin));
        list.add(new Data_of_purchase("44444" , listinin));
        list.add(new Data_of_purchase("55555555" , listinin));
        list.add(new Data_of_purchase("666666" , listinin));
        list.add(new Data_of_purchase("77777" , listinin));
        list.add(new Data_of_purchase("888888" , listinin));

        //mLive.setValue(response.body);
        mLive.setValue(new R_cart(list , null , true , null , null));
    }

    // POST S_deleteItem deletedItem with sp.getInt(ID_C7 , -1) & removedIDs.
    public void post_deletedItems(S_deleteItem deleted)
    {
        //هكتبهم منفصلين
    }

    //POST S_BUY_fromCart bought_items including (sp.getInt(ID_CC5 , -1) & t_price)
    public void post_goBuy(int currentCoursesID , int cartID , S_BUY_fromCart bought_items)
    {

    }

}

