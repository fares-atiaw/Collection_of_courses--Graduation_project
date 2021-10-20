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
import com.example.cofc.adapter.RV_C_adapter;
import com.example.cofc.adapter.RV_N_adapter;
import com.example.cofc.interfaces.CourseClickListener;
import com.example.cofc.model.Data_of_course;
import com.example.cofc.special_model.S_Add_watchLater;
import com.example.cofc.ui.activity.AllCourses;
import com.example.cofc.ui.activity.CourseDetails;
import com.example.cofc.viewmodel.V_AllCourses;

import java.util.ArrayList;
import java.util.List;

public class FragmentCourse extends Fragment {

    SharedPreferences sp;
    private static final String ID_W6 = "WatchLaterID";
    private static final String ARG_PARAM1 = "USER_IDs";
    private static final String ARG_PARAM2 = "course ID";
    private static final String ARG_PARAM3 = "category type";
    List<Data_of_course> courses = new ArrayList<>();
    RecyclerView rv;
    RV_C_adapter adapter;
    V_AllCourses mLive;

    public FragmentCourse() {
        // Required empty public constructor
    }
    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentClickListener)
            listener = (FragmentClickListener) context;
        else
            throw new ClassCastException("This activity doesn't implement FragmentCourse.FragmentClickListener");
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }*/

    public static FragmentCourse newInstance(List<Data_of_course> courses) {
        FragmentCourse fragment = new FragmentCourse();
        Bundle args = new Bundle();

        args.putParcelableArrayList(ARG_PARAM3, (ArrayList<? extends Parcelable>) courses);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courses = getArguments().getParcelableArrayList(ARG_PARAM3);
            Toast.makeText(getContext(), "Total courses : "+courses.size(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp = getActivity().getSharedPreferences(ARG_PARAM1 , getContext().MODE_PRIVATE);
        rv = view.findViewById(R.id.rv_courses);
        mLive = new ViewModelProvider(this).get(V_AllCourses.class);

        adapter = new RV_C_adapter(getContext() , courses);
        adapter.setListener(new CourseClickListener() {
            @Override
            public void onClick(Data_of_course data) {
                Intent i = new Intent(getActivity() , CourseDetails.class);
                i.putExtra(ARG_PARAM2 , data.getCourseID());
                i.putExtra("name" , data.getCourseName());
                startActivity(i);
            }
            @Override
            public void onLongClick(Data_of_course data , int position) {
                if(sp.getInt(ARG_PARAM1 , -1) == -1)
                    Toast.makeText(getContext(), "Sign in first to save this course to your WatchLater list", Toast.LENGTH_SHORT).show();
                else
                {
                    mLive.post_watchLater(new S_Add_watchLater(data.getCourseID() , sp.getInt(ID_W6,-1)));
                    Toast.makeText(getContext(), "Saved in WatchLater list successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.hasFixedSize();
    }


}
/* List<Data_of_course> arrayList = new ArrayList<>();
        arrayList.add(new Data_of_course("5","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Embbeded System from Scratch","12/12/2019","Fares Hassan",null,true,"",(double)26,true,(float)3));
        arrayList.add(new Data_of_course("55","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your farm","yesterday","عمدة المزاريطة: الكبير أوي",null,false,"جنوب المنيا",(double)80,true,(float)4));
        arrayList.add(new Data_of_course("5","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Embbeded System from Scratch","12/12/2019","Fares Hassan",null,true,"",(double)26,true,(float)3));
        arrayList.add(new Data_of_course("55","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your farm","yesterday","عمدة المزاريطة: الكبير أوي",null,false,"جنوب المنيا",(double)80,true,(float)4));
        arrayList.add(new Data_of_course("5","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Embbeded System from Scratch","12/12/2019","Fares Hassan",null,true,"",(double)26,true,(float)3));
        arrayList.add(new Data_of_course("55","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your farm","yesterday","عمدة المزاريطة: الكبير أوي",null,false,"جنوب المنيا",(double)80,true,(float)4));
        arrayList.add(new Data_of_course("5","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Embbeded System from Scratch","12/12/2019","Fares Hassan",null,true,"",(double)26,true,(float)3));
        arrayList.add(new Data_of_course("55","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your farm","yesterday","عمدة المزاريطة: الكبير أوي",null,false,"جنوب المنيا",(double)80,true,(float)4));
        arrayList.add(new Data_of_course("5","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Embbeded System from Scratch","12/12/2019","Fares Hassan",null,true,"",(double)26,true,(float)3));
        arrayList.add(new Data_of_course("55","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your farm","yesterday","عمدة المزاريطة: الكبير أوي",null,false,"جنوب المنيا",(double)80,true,(float)4));
        arrayList.add(new Data_of_course("5","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Embbeded System from Scratch","12/12/2019","Fares Hassan",null,true,"",(double)26,true,(float)3));
        arrayList.add(new Data_of_course("55","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your farm","yesterday","عمدة المزاريطة: الكبير أوي",null,false,"جنوب المنيا",(double)80,true,(float)4));
        arrayList.add(new Data_of_course("5","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Embbeded System from Scratch","12/12/2019","Fares Hassan",null,true,"",(double)26,true,(float)3));
        arrayList.add(new Data_of_course("55","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your farm","yesterday","عمدة المزاريطة: الكبير أوي",null,false,"جنوب المنيا",(double)80,true,(float)4));
        arrayList.add(new Data_of_course("5","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Embbeded System from Scratch","12/12/2019","Fares Hassan",null,true,"",(double)26,true,(float)3));
        arrayList.add(new Data_of_course("55","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your farm","yesterday","عمدة المزاريطة: الكبير أوي",null,false,"جنوب المنيا",(double)80,true,(float)4));
        arrayList.add(new Data_of_course("5","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","Embbeded System from Scratch","12/12/2019","Fares Hassan",null,true,"",(double)26,true,(float)3));
        arrayList.add(new Data_of_course("55","https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png","How to start building your farm","yesterday","عمدة المزاريطة: الكبير أوي",null,false,"جنوب المنيا",(double)80,true,(float)4));
        */