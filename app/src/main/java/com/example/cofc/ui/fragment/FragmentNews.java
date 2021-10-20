package com.example.cofc.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
import android.widget.Toast;

import com.example.cofc.R;
import com.example.cofc.adapter.RV_N_adapter;
import com.example.cofc.interfaces.NewClickListener;
import com.example.cofc.model.Data_of_news;
import com.example.cofc.special_model.S_Add_watchLater;
import com.example.cofc.ui.activity.CourseDetails;
import com.example.cofc.ui.activity.Sub_Category;
import com.example.cofc.viewmodel.V_AllCourses;

import java.util.ArrayList;
import java.util.List;

public class FragmentNews extends Fragment {

    SharedPreferences sp;
    private static final String ARG_PARAM1 = "USER_IDs";
    private static final String ARG_PARAM2 = "course ID";
    private static final String ARG_PARAM3 = "category type";
    private static final String CHOICE = "choice";
    private static final String ID_W6 = "WatchLaterID";
    private String choice;
    RecyclerView rv;
    List<Data_of_news> news  = new ArrayList<>();
    RV_N_adapter adapter;
    V_AllCourses mLive;

    public FragmentNews() {
        // Required empty public constructor
    }

    public static FragmentNews newInstance(List<Data_of_news> news , String choice) {
        FragmentNews fragment = new FragmentNews();
        Bundle args = new Bundle();

        args.putParcelableArrayList(ARG_PARAM3, (ArrayList<? extends Parcelable>) news);
        args.putString(CHOICE, choice);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            news = getArguments().getParcelableArrayList(ARG_PARAM3);
            choice = getArguments().getString(CHOICE);
            //Toast.makeText(getContext(), choice , Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view , Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp = getActivity().getSharedPreferences(ARG_PARAM1 , Context.MODE_PRIVATE);
        rv = view.findViewById(R.id.rv_news);
        mLive = new ViewModelProvider(this).get(V_AllCourses.class);

        adapter = new RV_N_adapter(getContext() , news);
        adapter.setListener(new NewClickListener() {
            @Override
            public void onClick(Data_of_news data) {
                Intent i = new Intent(getActivity() , CourseDetails.class);
                i.putExtra(ARG_PARAM2 , data.getId());
                i.putExtra("name" , data.getCourse_name());
                startActivity(i);
            }
            @Override
            public void onLongClick(Data_of_news data) {
                if(sp.getInt(ARG_PARAM1 , -1) == -1)
                    Toast.makeText(getContext(), "Sign in first to save this course to your WatchLater list", Toast.LENGTH_SHORT).show();
                else {
                    mLive.post_watchLater(new S_Add_watchLater(data.getId(), sp.getInt(ID_W6, -1)));
                    Toast.makeText(getContext(), "Saved in WatchLater list successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.hasFixedSize();

    }

}