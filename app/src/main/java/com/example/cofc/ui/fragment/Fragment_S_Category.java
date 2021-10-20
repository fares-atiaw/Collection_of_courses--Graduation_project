package com.example.cofc.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.example.cofc.R;
import com.example.cofc.adapter.RV_SubCategory_adapter;
import com.example.cofc.interfaces.Listener_ID;
import com.example.cofc.model.Data_of_course;
import com.example.cofc.model.M_SubCategory;
import com.example.cofc.special_model.S_Add_favorites;
import com.example.cofc.viewmodel.V_Sub_Category;

import java.util.ArrayList;
import java.util.List;

public class Fragment_S_Category extends Fragment {

    SharedPreferences sp;
    private static final String ID_F4 = "Favorite_ID";
    private static final String ARG_PARAM1 = "USER_IDs";
    private static final String ARG_PARAM2 = "course ID";
    private static final String ARG_PARAM3 = "category type";
    private static final String CHOICE = "choice";
    private int type;
    RecyclerView rv;
    RV_SubCategory_adapter adapter;
    List<M_SubCategory> categories = new ArrayList<>();
    V_Sub_Category mLive;

    public Fragment_S_Category() {
        // Required empty public constructor
    }

    public static Fragment_S_Category newInstance(List<M_SubCategory> categories) {
        Fragment_S_Category fragment = new Fragment_S_Category();
        Bundle args = new Bundle();

        args.putParcelableArrayList(ARG_PARAM3, (ArrayList<? extends Parcelable>) categories);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__s__category, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sp = getActivity().getSharedPreferences(ARG_PARAM1 , getContext().MODE_PRIVATE);

        rv = view.findViewById(R.id.f_sc_rv);
        mLive = new ViewModelProvider(this).get(V_Sub_Category.class);

        adapter = new RV_SubCategory_adapter(getContext() , categories);
        adapter.setListener(new Listener_ID() {
            @Override
            public void onClick(int category_ID, String name) {
                DF_Choices df = DF_Choices.newInstance(category_ID , name);
                df.show(getActivity().getSupportFragmentManager() , null);
            }
            @Override
            public void onLongClick(int category_ID, String name, Data_of_course data , int position) {

                if(sp.getInt(ARG_PARAM1 ,-1) == -1)
                    Toast.makeText(rv.getContext(), "Sign in first to add this category to your favorites", Toast.LENGTH_SHORT).show();
                else{
                    mLive.post_favorite(new S_Add_favorites(category_ID , sp.getInt(ID_F4 , -1 )));
                    Toast.makeText(rv.getContext(), "Added to your favorites successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.hasFixedSize();
    }

}