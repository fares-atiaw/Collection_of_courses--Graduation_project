package com.example.cofc.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.example.cofc.interfaces.Listener_ID;
import com.example.cofc.model.Data_of_course;
import com.example.cofc.model.M_MainCategory;
import com.example.cofc.R;
import com.example.cofc.adapter.RV_MainCategory_adapter;
import com.example.cofc.special_model.R_mainCategories;
import com.example.cofc.ui.activity.Sub_Category;
import com.example.cofc.viewmodel.V_Main_Category;

import java.util.ArrayList;
import java.util.List;

public class Fragment_M_Category extends Fragment {

    private static final String ARG_PARAM1 = "USER_IDs";
    private static final String ARG_PARAM3 = "category type";
    private static final String CHOICE = "choice";
    RecyclerView rv;
    RV_MainCategory_adapter adapter;
    List<M_MainCategory> categories = new ArrayList<>();

    public Fragment_M_Category() {
        // Required empty public constructor
    }

    public static Fragment_M_Category newInstance(List<M_MainCategory> categories) {
        Fragment_M_Category fragment = new Fragment_M_Category();
        Bundle args = new Bundle();

        Log.v("Paar" , categories.toString());
        args.putParcelableArrayList(ARG_PARAM3 , (ArrayList<? extends Parcelable>) categories);
        Log.v("Paarrr" , args.getParcelableArrayList(ARG_PARAM3).toString());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categories = getArguments().getParcelableArrayList(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__m__category, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.f_mc_rv);

        adapter = new RV_MainCategory_adapter(getContext() , categories);
        adapter.setListener(new Listener_ID() {
            @Override
            public void onClick(int category_ID, String name) {
                Intent i = new Intent(getActivity() , Sub_Category.class);
                i.putExtra(ARG_PARAM3 , category_ID);
                i.putExtra("name" , name);
                startActivity(i);
            }
            @Override
            public void onLongClick(int category_ID, String name, Data_of_course data , int position) {
            }
        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.hasFixedSize();

    }



}





/*

    RV_MainCategory_adapter adapter;
    L_M_MainCategory categories;
    //List<M_MainCategory> categories = new ArrayList<>();

    public Fragment_M_Category() {
        // Required empty public constructor
    }

    public static Fragment_M_Category newInstance(L_M_MainCategory categories) {
        Fragment_M_Category fragment = new Fragment_M_Category();
        Bundle args = new Bundle();

        Log.v("Paar" , categories.toString());
        args.putParcelable(ARG_PARAM3 , categories);

        Log.v("Paarrr" , args.getParcelable(ARG_PARAM3).toString());

        args.putBundle(ARG_PARAM3, args);

        Log.v("Paar2" , categories.toString());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Log.v("Paar3" , "Here I am");
            if(getArguments().getBundle(ARG_PARAM3) != null)
                Log.v("Paar5" , "not null");  //Found
                //Log.v("Paar54" , getArguments().getBundle(ARG_PARAM3).toString());  //Found

            Bundle b = getArguments().getParcelable(ARG_PARAM3);
            if(b != null)
                Log.v("Paar55" , "b is not null");  //Found

            //categories = (L_M_MainCategory) b.getParcelable(ARG_PARAM3); // The error is here
            categories = (L_M_MainCategory) getArguments().getBundle(ARG_PARAM3).getParcelable(ARG_PARAM3); // The error is here

                    //Log.v("Paar4" , getArguments().getParcelable(ARG_PARAM3).toString()); error
                    Log.v("Paar44" , categories.getCategories().get(0).toString());  //Found

                    if(getArguments().getParcelable(ARG_PARAM3) == null)
                    Log.v("Paar6" , "ddd");
                    }
                    }
 */