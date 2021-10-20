package com.example.cofc.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cofc.R;
import com.example.cofc.adapter.RV_C_adapter;
import com.example.cofc.model.Data_of_course;
import com.example.cofc.ui.activity.CourseDetails;

import java.util.ArrayList;

public class DF_CurrentCourses extends DialogFragment {

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
    RecyclerView rv;
    RV_C_adapter adapter;

    public DF_CurrentCourses() {
        // Required empty public constructor
    }

    public static DF_CurrentCourses newInstance(Integer user_id) {
        DF_CurrentCourses fragment = new DF_CurrentCourses();
        Bundle args = new Bundle();

        args.putInt(ARG_PARAM1, user_id);

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.df_current_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.rv_currentcourses);
        sp = getActivity().getSharedPreferences("USER_ID" , Context.MODE_PRIVATE);
//I have the sp.user_id to open his current courses

        ArrayList<Data_of_course> arrayList = new ArrayList<>();
        /*arrayList.add(new Data_of_course("5","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Embbeded System from Scratch","12/12/2019","Fares Hassan",null,true,"",(double)26,true,(float)3));
        arrayList.add(new Data_of_course("55","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your farm","yesterday","عمدة المزاريطة: الكبير أوي",null,false,"جنوب المنيا",(double)80,true,(float)4));
*/
        adapter = new RV_C_adapter(getContext() , arrayList);
       /* adapter.setListener(new RV_C_adapter.ItemCLickListener() {
            @Override
            public void onClick(Data_of_course data) {
                Intent i = new Intent(getContext() , CourseDetails.class);
                i.putExtra(ARG_PARAM2 , data.getCourseID());
                i.putExtra("name" , data.getCourseName());
                startActivity(i);
            }
            @Override
            public void onLongClick(Data_of_course data) {
                //Delete this item from the list
            }
        });*/

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.hasFixedSize();


    }
}