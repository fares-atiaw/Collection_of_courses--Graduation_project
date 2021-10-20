package com.example.cofc.ui.fragment;

import android.content.Context;
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
import android.widget.Button;
import android.widget.EditText;

import com.example.cofc.R;
import com.example.cofc.adapter.RV_SubCategory_adapter;
import com.example.cofc.interfaces.Listener_ID;
import com.example.cofc.model.Data_of_course;
import com.example.cofc.model.M_SubCategory;
import com.example.cofc.special_model.S_deleteFavorite;
import com.example.cofc.viewmodel.V_MainProfile;

import java.util.ArrayList;
import java.util.List;

public class DF_Favorite extends DialogFragment {

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
    //Button btn;
    //EditText et;
    RecyclerView rv;
    RV_SubCategory_adapter adapter;
    List<M_SubCategory> favorites = new ArrayList<>();
    S_deleteFavorite d_favorite;
    V_MainProfile mLive;

    public DF_Favorite() {
        // Required empty public constructor
    }
    public static DF_Favorite newInstance(List<M_SubCategory> favorites) {
        DF_Favorite fragment = new DF_Favorite();
        Bundle args = new Bundle();

        args.putParcelableArrayList(ARG_F , (ArrayList<? extends Parcelable>) favorites);

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            favorites = getArguments().getParcelableArrayList(ARG_F);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.df_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view , Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sp = getActivity().getSharedPreferences(ARG_PARAM1 , Context.MODE_PRIVATE);
        mLive = new ViewModelProvider(this).get(V_MainProfile.class);

        //btn = view.findViewById(R.id.df_favorites_btn_remove);
        //et = view.findViewById(R.id.df_favorites_et_removed_no);
        rv = view.findViewById(R.id.rv_favorites);

        adapter = new RV_SubCategory_adapter(getContext() , favorites);
        adapter.setListener(new Listener_ID() {
            @Override
            public void onClick(int category_ID, String name) {
                DF_Choices df = DF_Choices.newInstance(category_ID , name);
                df.show(getActivity().getSupportFragmentManager(), null);
            }
            @Override
            public void onLongClick(int category_ID, String name, Data_of_course data , int position) {
                // POST S_deleteFavorite item by sp.getInt(ID_F4 , -1) & category_ID , then Toast exit profile to be successful
                adapter.getArrayList().remove(position);
                adapter.notifyItemRemoved(position);
                d_favorite = new S_deleteFavorite(sp.getInt(ID_F4 , -1) , category_ID);
                mLive.post_deletedFavorite(d_favorite);
            }

        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.hasFixedSize();

    }

}
/*ArrayList<M_SubCategory> arrayList = new ArrayList<>();
        arrayList.add(new M_SubCategory(4 , "Web1" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Android1" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Web2" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Android2" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Art" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Machine Learning" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Seamstress" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Engineering" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Desktop Application" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Application" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Cooking" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Craft" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Art" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Fitness & Gym" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Seamstress" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Engineering" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
        arrayList.add(new M_SubCategory(4 , "Desktop Application" , "https://animasipicture.com/wp-content/uploads/2021/03/Animated-Book-Pictures-1536x639.png"));
*/