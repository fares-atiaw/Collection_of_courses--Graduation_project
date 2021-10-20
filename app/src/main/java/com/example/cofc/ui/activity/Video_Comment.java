package com.example.cofc.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.cofc.R;
import com.example.cofc.databinding.VideoCommentBinding;
import com.example.cofc.model.M_CommentRating;
import com.example.cofc.special_model.S_comment;
import com.example.cofc.viewmodel.V_Video_Comment;

public class Video_Comment extends AppCompatActivity {

    VideoCommentBinding binding;
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
    S_comment post;
    V_Video_Comment mLive;
    private int video_id;
    private String video_name;
    private String video_URL;
    private boolean show;
    M_CommentRating body;
    float rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.video_comment);
        sp = this.getSharedPreferences(ARG_PARAM1 , Context.MODE_PRIVATE);

        mLive = new ViewModelProvider(this).get(V_Video_Comment.class);

        if (getIntent().getExtras() != null)
        {
            Bundle bundle = getIntent().getExtras();
            video_id = bundle.getInt("video_id");
            video_name = bundle.getString("video_name");
            video_URL = bundle.getString("video_URL");
            show = bundle.getBoolean("show");
            Toast.makeText(this, video_name , Toast.LENGTH_SHORT).show();
        }

        if(video_URL != null){
            Log.v("gjgj" , video_URL.toString());
            showVideo();
            implementations();
        }

    }

    void showVideo()
    {
        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(binding.vwVideo);
        controller.setAnchorView(binding.vwVideo);
        binding.vwVideo.setMediaController(controller);
        binding.vwVideo.requestFocus();
        binding.vwVideo.setVideoURI(Uri.parse(video_URL)); //"http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"
        binding.vwVideo.destroyDrawingCache();
        binding.vwVideo.start();
    }

    void implementations()
    {
        //if(show)
        {
            binding.vcStarsUnderstand.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    Log.v("Rating_Here" , rating+"");
                    //rate = rating;
                    if (rating < 4.5) {
                        binding.vcEtMissing.setVisibility(View.VISIBLE);
                        Log.v("Rating_Here", rating + "");
                    }
                    else
                        binding.vcEtMissing.setVisibility(View.GONE);
                }
            });

            binding.vcBtnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(binding.vcBtnYes.getText().equals("Yes")) {
                        binding.vcEtNewVideo.setVisibility(View.VISIBLE);
                    }
                    else
                        binding.vcEtNewVideo.setVisibility(View.GONE);
                }
            });

            binding.vcBtnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    body = new M_CommentRating();
                    body.setUnderstand_Rate(binding.vcStarsUnderstand.getRating());
                    body.setMissing_Explain(binding.vcEtMissing.toString());
                    body.setMissing_Answer(binding.vcEtNewVideo.toString());

                    post = new S_comment(sp.getInt(ID_S1 , -1), video_id , body);
                    //mLive.post_comment(post);
                    startActivity(new Intent(Video_Comment.this , CourseDetails.class));
                }
            });
        }
        /*else
            binding.scrollView2.setVisibility(View.GONE);*/
    }
}












/*<ToggleButton
                        android:id="@+id/vc_btn_no"
                        android:layout_width="150sp"
                        android:layout_height="40sp"
                        android:layout_marginStart="10sp"
                        android:layout_marginEnd="10sp"
                        android:textSize="18sp"
                        android:textOn="@string/no_it_s_good_2"
                        android:textOff="@string/no_it_s_good_1"
                        android:textAllCaps="false"
                        android:textColor="@color/black"
                        android:background="#1A000000"
                        />

binding.vcBtnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.vcBtnNo.getText().equals("No, It's good")) {
                    //binding.vcEtNewVideo.setVisibility(View.VISIBLE);
                }
                else{
                    binding.vcEtNewVideo.setVisibility(View.GONE);
                    if(binding.vcBtnYes.getText().equals(String.valueOf(R.string.yes_then)))
                        binding.vcBtnYes.performClick();
                    else
                        binding.vcBtnYes.performClick();
                    }
            }
        });*/