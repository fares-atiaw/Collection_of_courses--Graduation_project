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

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cofc.R;
import com.example.cofc.adapter.RV_N_adapter;
import com.example.cofc.interfaces.NewClickListener;
import com.example.cofc.model.Data_of_news;
import com.example.cofc.ui.activity.CourseDetails;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DF_Notification extends DialogFragment {

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
    RV_N_adapter adapter;
    List<Data_of_news> notifications = new ArrayList<>();

    public DF_Notification() {
        // Required empty public constructor
    }
    public static DF_Notification newInstance(List<Data_of_news> notifications) {
        DF_Notification fragment = new DF_Notification();
        Bundle args = new Bundle();

        args.putParcelableArrayList(ARG_N, (ArrayList<? extends Parcelable>) notifications);

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
        notifications = getArguments().getParcelableArrayList(ARG_N);
        //sp = getContext().getSharedPreferences(ARG_PARAM1 , getContext().MODE_PRIVATE);   //OR getActivity. .....
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.df_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sp = getActivity().getSharedPreferences(ARG_PARAM1 , Context.MODE_PRIVATE);

        rv = view.findViewById(R.id.rv_notification);

        adapter = new RV_N_adapter(getContext() , notifications);
        adapter.setListener(new NewClickListener() {
            @Override
            public void onClick(Data_of_news data) {
                Intent i = new Intent(adapter.getC() , CourseDetails.class);
                i.putExtra(ARG_PARAM2 , data.getId());
                i.putExtra("name" , data.getCourse_name());
                startActivity(i);
            }
            @Override
            public void onLongClick(Data_of_news data) {

            }
        });
        rv.setAdapter(adapter);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);
    }
}
/*List<Data_of_news> list = new ArrayList<>();
        list.add(new Data_of_news(5 , "fafy" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png" , "fdfdfdfdfdfd" ,"12/11/2020" , null ));
        list.add(new Data_of_news(5 , "fafy" , null , "fdfdfdfdfdfd" ,"12/11/2020" , "ffffffffffffffffff fffffffffffffffffff ffffffffffffffffff fffffffffffffffffff  \n ffffffffffffffffff fffffffffffffffffff " ));
        list.add(new Data_of_news(5 , "fafy" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png" , "fdfdfdfdfdfd" ,"12/11/2020" , null ));
        list.add(new Data_of_news(5 , "fafy" , null , "fdfdfdfdfdfd" ,"12/11/2020" , null ));
*/