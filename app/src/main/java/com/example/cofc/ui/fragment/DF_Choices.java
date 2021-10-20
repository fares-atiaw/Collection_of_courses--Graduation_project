package com.example.cofc.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cofc.R;
import com.example.cofc.databinding.DfChoicesBinding;
import com.example.cofc.ui.activity.AllCourses;
import com.example.cofc.ui.activity.Helpful_Channels;
import com.example.cofc.ui.activity.OfflineCourses;
import com.example.cofc.ui.activity.OnlineCourses;

import java.util.logging.Logger;

import javax.security.auth.login.LoginException;

public class DF_Choices extends DialogFragment {

    private static final String ARG_PARAM1 = "USER_IDs";
    private static final String ARG_PARAM2 = "course ID";
    private static final String ARG_PARAM3 = "category type";
    private static final String CHOICE = "choice";
    private int type;
    private String category_name;
    DfChoicesBinding binding;

    public DF_Choices() {
        // Required empty public constructor
    }

    public static DF_Choices newInstance(int type , String category_name) {
        DF_Choices fragment = new DF_Choices();
        Bundle args = new Bundle();

        args.putInt(ARG_PARAM3, type);
        args.putString("name", category_name);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(ARG_PARAM3);
            category_name = getArguments().getString("name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DfChoicesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.choicesBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (binding.choices4.getCheckedRadioButtonId())
                {
                    case (R.id.choices_rb_online):
                        Intent i = new Intent(getActivity() , OnlineCourses.class);
                        i.putExtra(ARG_PARAM3 , type);
                        i.putExtra("name" , category_name);
                        startActivity(i);
                        break;
                    case (R.id.choices_rb_offline):
                        Intent ii = new Intent(getActivity() , OfflineCourses.class);
                        ii.putExtra(ARG_PARAM3 , type);
                        ii.putExtra("name"  , category_name);
                        startActivity(ii);
                        break;
                    case (R.id.choices_rb_all):
                        Intent iii = new Intent(getActivity() , AllCourses.class);
                        iii.putExtra(ARG_PARAM3 , type);
                        iii.putExtra("name"  , category_name);
                        startActivity(iii);
                        break;
                    case (R.id.choices_rb_helpful):
                        Intent iiii = new Intent(getActivity() , Helpful_Channels.class);
                        iiii.putExtra(ARG_PARAM3 , type);
                        iiii.putExtra("name"  , category_name);
                        startActivity(iiii);
                        break;
                }
            }
        });

        binding.choicesBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            getDialog().getWindow().setDimAmount(0);    //???
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            //int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, 900);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);  //???
        }

    }
}