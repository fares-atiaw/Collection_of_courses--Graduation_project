package com.example.cofc.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cofc.R;
import com.example.cofc.adapter.RV_C_adapter;
import com.example.cofc.interfaces.CourseClickListener;
import com.example.cofc.model.Data_of_course;
import com.example.cofc.special_model.S_deleteWatchLater;
import com.example.cofc.ui.activity.CourseDetails;
import com.example.cofc.ui.activity.MainProfile;
import com.example.cofc.viewmodel.V_MainProfile;

import java.util.ArrayList;
import java.util.List;

public class DF_WatchLater extends DialogFragment {

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
    List<Data_of_course> list_watchLater = new ArrayList<>();
    S_deleteWatchLater item;
    V_MainProfile mLive;
    RecyclerView rv;
    RV_C_adapter adapter;

    public DF_WatchLater() {
        // Required empty public constructor
    }

    public static DF_WatchLater newInstance(List<Data_of_course> list_watchLater) {
        DF_WatchLater fragment = new DF_WatchLater();
        Bundle args = new Bundle();

        args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) list_watchLater);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list_watchLater = getArguments().getParcelableArrayList(ARG_W);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.df_watch_later, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sp = getActivity().getSharedPreferences(ARG_PARAM1 , Context.MODE_PRIVATE);
        mLive = new ViewModelProvider(this).get(V_MainProfile.class);

        rv = view.findViewById(R.id.rv_watchlater);

        adapter = new RV_C_adapter(getContext() , list_watchLater);
        adapter.setListener(new CourseClickListener() {
            @Override
            public void onClick(Data_of_course data) {
                Intent i = new Intent(getContext() , CourseDetails.class);
                i.putExtra(ARG_PARAM2 , data.getCourseID());
                i.putExtra("name" , data.getCourseName());
                startActivity(i);
            }
            @Override
            public void onLongClick(Data_of_course data , int position) {
                // POST S_deleteWatchLater item by sp.getInt(ID_W6 , -1) & data.getCourseID() , then Toast exit profile to be successful
                adapter.getArrayList().remove(position);
                adapter.notifyItemRemoved(position);
                item = new S_deleteWatchLater(sp.getInt(ID_W6 , -1) , data.getCourseID());
                mLive.post_deletedWatchLater(item);
            }
        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.hasFixedSize();

    }

}
/*List<Data_of_course> list = new ArrayList<>();
        arrayList.add(new Data_of_course("5","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Embbeded System from Scratch","12/12/2019","Fares Hassan",null,true,"",(double)26,true,(float)3));
        arrayList.add(new Data_of_course("55","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your farm","yesterday","عمدة المزاريطة: الكبير أوي",null,false,"جنوب المنيا",(double)80,true,(float)4));
*/